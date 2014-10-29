import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class TSM_Transaction implements Transaction{
   public static final int OP_FAIL = Transaction.OP_FAIL;
   public static final int INITED = Transaction.INITED;
   public static final int OPENING = Transaction.OPENING;
   public static final int OPENED = Transaction.OPENED;
   public static final int CANCELING = Transaction.CANCELING;
   public static final int OPEN_COVERING = Transaction.OPEN_COVERING;
   public static final int COVERING = Transaction.COVERING;
   public static final int CLOSED = Transaction.CLOSED;
   public static final int CLOSED_ERROR = Transaction.CLOSED_ERROR;
   public static final int OP_BUY = Transaction.OP_BUY;
   public static final int OP_SELL = Transaction.OP_SELL;
   protected    OrderRec my_or_b;
   protected    OrderRec my_or_s;
   protected    OrderRec my_or_c;
   private Object[] my_ors;
   private int _op; 
   private int status;
   private int cover_price_setted;
   private int cover_price;
   private float open_price;
   private int cover_null_warning_count;
   private boolean need_to_delay;
   private int _range;
   private int _price;
   private boolean _isEasy;
   public void init(int price, int range, String which_code, boolean isEasy)
   {
       my_ors = new Object[8];
       for(int i=0;i<8;i++)
       {
          my_ors[i] = null;

       }
       my_or_b =  new OrderRec();
       my_or_s =  new OrderRec();
       my_ors[INITED] = my_or_b;

         my_or_b.buy_or_sell = "B";
         my_or_s.buy_or_sell = "S";  

       my_or_b.code = which_code; 
       my_or_s.code = which_code; 
       
       my_or_b.price = String.valueOf((price-range)); 
       my_or_s.price = String.valueOf((price+range)); 
       
       my_or_b.amount = String.valueOf(1);
       my_or_s.amount = String.valueOf(1);
       
       status = INITED;
       cover_price_setted = -1;
       cover_price = -1;
       need_to_delay = false;
       _price = price;
       _range = range;
       _op = -1; 
       _isEasy = isEasy;
   }
   
   public int open()throws Exception
   {
   	  if(status == INITED)
   	  {
         status = OPEN_COVERING;
         getStatus();
         return OPEN_COVERING;
      }
      return OP_FAIL;
   }
   
   public int cancel()throws Exception
   {
      getStatus();
      if(status == OPEN_COVERING)
      {
         int sleep_count = 0;
         my_ors[CANCELING] =  my_ors[OPENING];
         OrderedRec odcancle1 = (OrderedRec)my_ors[OPENING];
         odcancle1.cancel_qty = odcancle1.ord_qty - odcancle1.ord_match_qty;
         OrderedRec odcancle2 = (OrderedRec)my_ors[OPEN_COVERING];
         odcancle2.cancel_qty = odcancle2.ord_qty - odcancle2.ord_match_qty;
         my_ors[CANCELING] =  my_ors[OPEN_COVERING];
         status = CANCELING;
         return status;
      }
      return OP_FAIL;
   }
   
   public int cover(int price)throws Exception
   {
      getStatus();
      OrderRec my_or = null;
      cover_price = price;
      if((status == COVERING || status == OPEN_COVERING) && cover_price_setted != cover_price)
      {
          syncCOVERingWithUnSettled();  
          cover_null_warning_count = 0; 
          return getStatus();   
      } else  if(status == OPENING || status == OPENED)
      {
         if(status == OPENING)
         {
            status = OPEN_COVERING;
         }
         if(status == OPENED)
         {
            status = COVERING;
         }
         syncCOVERingWithUnSettled(); 
         cover_null_warning_count = 0; 
         return getStatus();
      }
      return OP_FAIL;
   }
   
   public int getOp()
   { 
       return _op;
   }
   
   private void processCOVERing(OrderedRec[] ors)throws Exception
   {
/////      System.out.println("processCOVERing");
      if(status == COVERING)
      {   if(my_ors[COVERING] == null)
      	  {
      	     syncCOVERingWithUnSettled();
      	  }
    	    for(int i=0; i<ors.length; i++)
    	    {
    	      	if(my_ors[COVERING] instanceof OrderRec)
    	      	{
    	      		 throw new Exception("Wrong State at Covering");
    	         } else if(my_ors[COVERING] instanceof OrderedRec)
    	         {
    	      	     OrderedRec mo = (OrderedRec)my_ors[COVERING];
   	               setupOrderedRec(mo);
    	             if(!mo.queryValid())
    	             {
    	                 status = CLOSED;
    	                 my_ors[CLOSED] = mo;
    	             }
    	         }
    	    }
      }
   }
   
   abstract public double getCurrentPrice();
   abstract public String getCurrentDateStr();
   abstract public String getCurrentTimeStr();
   
   private void setupOrderedRec(OrderedRec or)
   {
       double curP =  getCurrentPrice();
       double or_p = 0;
       boolean isBuy = true;
       if(or.ord_seq.equals("otest"))
       {
          or_p = Double.parseDouble(my_or_b.price);
       } else if(or.ord_seq.equals("stest"))
       {
          or_p = Double.parseDouble(my_or_s.price);
          isBuy = false;
       }else if(or.ord_seq.equals("covering"))
       {
          or_p = Double.parseDouble(my_or_c.price);
          isBuy = "B".equals(my_or_c.buy_or_sell);
       }
  //        System.out.println("Cur_P:"+curP+" orp:"+or_p+ " isBuy:"+isBuy +" or.ord_match_qty:"+or.ord_match_qty);
       if(isBuy)
       { 
       	  if(_isEasy)
       	  {
       	     if(curP <= or_p)
       	     {
       	        or.ord_match_qty =  or.ord_qty - or.cancel_qty;
       	        or.match_time = getCurrentTimeStr();
       	        _isEasy = !_isEasy;
       	     }
       	  } else
       	  {
       	     if(curP < or_p)
       	     {
       	        or.ord_match_qty =  or.ord_qty - or.cancel_qty;
       	        or.match_time = getCurrentTimeStr();
       	        _isEasy = !_isEasy;
       	     }
       	  }
       } else
       {
       	  if(_isEasy)
       	  {
          	  if(curP >= or_p)
              {
        	       or.ord_match_qty =  or.ord_qty - or.cancel_qty;
        	       or.match_time = getCurrentTimeStr();
        	     }
          } else
          {
          	  if(curP > or_p)
              {
        	       or.ord_match_qty =  or.ord_qty - or.cancel_qty;
        	       or.match_time = getCurrentTimeStr();
       	     }
          } 
       } 
      //     System.out.println("Cur_P:"+curP+" orp:"+or_p+ " isBuy:"+isBuy +" or.ord_match_qty:"+or.ord_match_qty);
  }
   public UnSettledRec getUnSettled() throws Exception
   {
         UnSettledRec usr=null;
/////         System.out.println("getUnSettled -->"+getCurrentTimeStr());
         if( my_ors[OPENED] !=null)
         {
         	 OrderedRec ord = (OrderedRec)my_ors[OPENED];
         	 double curP =  getCurrentPrice();
           usr = new UnSettledRec();
           usr.tdate =  ord.add_date;
           usr.code = ord.stock_id;
           usr.ord_bs = ord.ord_bs.equals("B");
           usr.vol = (float)ord.ord_match_qty;
           if(ord.ord_seq.equals("otest"))
           {
              usr.avg_price = Float.parseFloat(my_or_b.price);
           } else if(ord.ord_seq.equals("stest"))
           {
              usr.avg_price = Float.parseFloat(my_or_s.price);
           }
           usr.cur_price = (float)curP;
           usr.loss = (float)(usr.ord_bs ? (curP-usr.avg_price)*50 : (curP-usr.avg_price)*-50) ;
         }
         return usr;
   }
   private void syncCOVERingWithUnSettled()throws Exception
   {
/////      System.out.println("syncCOVERingWithUnSettled()");
      if(status == COVERING || status == OPEN_COVERING)
      {
         if(my_ors[status] != null)
         {
         	  int sleep_count = 0;
   	 	      my_ors[status] = null;
         }
         UnSettledRec uns_rec=getUnSettled();
         if(uns_rec != null)
         {
/////            uns_rec.dump();
            open_price = uns_rec.avg_price ;
            my_or_c = new OrderRec();
            if(!uns_rec.ord_bs)
            {
               my_or_c.buy_or_sell = "B";
               my_or_c.price = String.valueOf(cover_price);
            } else
            {
               my_or_c.buy_or_sell = "S";
               my_or_c.price = String.valueOf(cover_price);
            }
            my_or_c.code =   uns_rec.code;
            my_or_c.amount = String.valueOf((int)uns_rec.vol);
    	 	    cover_price_setted = cover_price;
            my_or_c.seq_no = "covering";
            my_ors[COVERING] = null;
         }
      }
   }
   
   public float getLossMoney() throws Exception
   {
/////        System.out.println("getLossMoney");
        UnSettledRec uns_rec=getUnSettled();
         if(uns_rec != null)
         {
//            uns_rec.dump();
            return uns_rec.loss;
         }
         return 0;
   }
   public float getLossPoint() throws Exception
   {

         UnSettledRec uns_rec=getUnSettled();
         if(uns_rec != null)
         {
//            uns_rec.dump();
            if(uns_rec.ord_bs)
            {
               return uns_rec.cur_price - uns_rec.avg_price;
            } else
            {
               return uns_rec.avg_price - uns_rec.cur_price;
            }
         }
         return 0;
   }
   
   public int getStatus()throws Exception
   {
/////       System.out.println("getStatus "+status+"-->"+getCurrentTimeStr()+"  p:"+getCurrentPrice());
       if(status == OPENING)
       {
           throw new Exception("Worng State at Opening");
         //   OrderedRec[] ors = _my_t4_call.queryOrdered();
         //   //OrderRec mo = (OrderRec)my_ors[INITED];
    	   //   for(int i=0; i<ors.length; i++)
    	   //   {
   	     //       if(ors[i] != null && ors[i].ord_seq != null && ors[i].ord_seq.equals(my_or_b.seq_no) && ors[i].ord_no != null && ors[i].ord_no.trim().length() > 0)
   	     //       {
    	   //          my_ors[OPENING] = ors[i];
    	   //          if(ors[i].ord_qty == ors[i].cancel_qty)
    	   //          {
    	   //             status = CLOSED;
    	   //             my_ors[CLOSED] = ors[i];
    	   //          } else if(ors[i].ord_qty == ors[i].cancel_qty + ors[i].ord_match_qty &&  ors[i].ord_match_qty > 0)
    	   //          {
     	   //             status = COVERING;
    	   //             my_ors[COVERING] = my_or_s;
    	   //             open_price = Float.parseFloat(mo.price);
    	   //          }
    	   //          ors[i].dump();
    	   //       }
    	   //   }
    	      //if(my_ors[OPENING] != null)
     	      // ((OrderedRec)my_ors[OPENING]).dump();
           
       } else if(status == CANCELING)
       {
          //  OrderedRec[] ors = _my_t4_call.queryOrdered();
            OrderedRec odcancle1 = (OrderedRec)my_ors[OPENING];
            OrderedRec odcancle2 = (OrderedRec)my_ors[OPEN_COVERING];
            odcancle1 = (OrderedRec)my_ors[OPENING];
            odcancle2 = (OrderedRec)my_ors[OPEN_COVERING];
            if(odcancle1.ord_match_qty != odcancle2.ord_match_qty)
            {
                status = COVERING;
                if(odcancle1.ord_match_qty > odcancle2.ord_match_qty)
                {
                	  my_ors[OPENED] = my_ors[OPENING];
                    if(cover_price == -1)
                    {
                       cover_price = _price + _range;
                    }
                    
                } else
                {
                    my_ors[OPENED] = my_ors[OPEN_COVERING];
                    if(cover_price == -1)
                    {
                       cover_price = _price - _range;
                    }
                }
                my_ors[COVERING] = null;
                syncCOVERingWithUnSettled();
            } else if(odcancle1.cancel_qty == odcancle2.cancel_qty && 
               odcancle1.cancel_qty+odcancle1.ord_match_qty == odcancle1.ord_qty)
            {
    	             status = CLOSED;
    	             my_ors[CLOSED] = odcancle2;
            }
       }else if(status == COVERING)
       {
       	  if(my_ors[COVERING] == null && my_or_c!=null)
       	  {
//       	  	   java.util.Date d = new java.util.Date();
    	           OrderedRec or = new OrderedRec();
    	           or.ord_seq="covering";
    	           or.ord_no="c1234";
    	           or.add_date=getCurrentDateStr();
    	           or.add_time=getCurrentTimeStr();
    	           or.match_time="";
    	           or.stock_id=my_or_c.code;
    	           or.ord_bs=my_or_c.buy_or_sell;
    	           or.ord_qty=Integer.parseInt(my_or_c.amount);
    	           or.cancel_qty=0;
    	           or.ord_match_qty=0;
    	           or.octype=" ";
    	           or.preorder=" ";
    	           or.err_code="0000";
    	           or.or_source=my_or_c;
    	           my_ors[COVERING] = or;
       	  
       	   }
       	  OrderedRec[] ors = new OrderedRec[1];
       	  ors[0] =  (OrderedRec)my_ors[COVERING]; 
          processCOVERing(ors);
       }else if(status == OPEN_COVERING)
       {
    	    //if(ors[i] != null && ors[i].ord_seq != null && ors[i].ord_seq.equals(my_or_b.seq_no) && ors[i].ord_no.trim().length() > 0)
//    	     java.util.Date d = new java.util.Date();
   	       if(my_ors[OPENING]==null)
   	       {
    	           OrderedRec or = new OrderedRec();
    	           or.ord_seq="otest";
    	           or.ord_no="o1234";
    	           or.add_date=getCurrentDateStr();
    	           or.add_time=getCurrentTimeStr();
    	           or.match_time="";
    	           or.stock_id=my_or_b.code;
    	           or.ord_bs=my_or_b.buy_or_sell;
    	           or.ord_qty=Integer.parseInt(my_or_b.amount);
    	           or.cancel_qty=0;
    	           or.ord_match_qty=0;
    	           or.octype=" ";
    	           or.preorder=" ";
    	           or.err_code="0000";
    	           or.or_source=my_or_b;
    	           my_ors[OPENING] = or;
    	     }
    	     // else if(ors[i] != null && ors[i].ord_seq != null && ors[i].ord_seq.equals(my_or_s.seq_no) && ors[i].ord_no.trim().length() > 0)
   	       {
    	            
    	           OrderedRec or = new OrderedRec();
    	           or.ord_seq="stest";
    	           or.ord_no="s1234";
    	           or.add_date=getCurrentDateStr();
    	           or.add_time=getCurrentTimeStr();
    	           or.match_time="";
    	           or.stock_id=my_or_s.code;
    	           or.ord_bs=my_or_s.buy_or_sell;
    	           or.ord_qty=Integer.parseInt(my_or_s.amount);
    	           or.cancel_qty=0;
    	           or.ord_match_qty=0;
    	           or.octype=" ";
    	           or.preorder=" ";
    	           or.err_code="0000";
    	           or.or_source=my_or_s;
    	           my_ors[OPEN_COVERING] = or;
    	     }  
           OrderedRec order1 = (OrderedRec)my_ors[OPENING];
           OrderedRec order2 = (OrderedRec)my_ors[OPEN_COVERING];
           setupOrderedRec(order1);
           setupOrderedRec(order2);
    	     if((order1 != null && !order1.queryValid()) && ( order2!=null &&!order2.queryValid()))
    	     {
               if(order1.ord_match_qty != order2.ord_match_qty)
               {
                   if(order1.ord_match_qty > order2.ord_match_qty)
                   {
                   	  my_ors[OPENED] = my_ors[OPENING];
                       if(cover_price == -1)
                       {
                          cover_price = _price + _range;
                       }
                   } else
                   {
                       my_ors[OPENED] = my_ors[OPEN_COVERING];
                       if(cover_price == -1)
                       {
                          cover_price = _price - _range;
                       }
                   }
                   status = COVERING;
                   my_ors[COVERING] = null;
                   syncCOVERingWithUnSettled();
               } else 
               {
    	                status = CLOSED;
    	                my_ors[CLOSED] = order2;
               }
    	    
    	     } else if(order1 != null && !order1.queryValid())
    	     {
                   status = COVERING;
                   my_ors[OPENED] = my_ors[OPENING];
                   my_ors[COVERING] = my_ors[OPEN_COVERING];
                   cover_price_setted = cover_price = _price + _range;
    	     }else if(order2 != null && !order2.queryValid())
    	     {
                 status = COVERING;
                 my_ors[OPENED] = my_ors[OPEN_COVERING];
                 my_ors[COVERING] = my_ors[OPENING];
                 cover_price_setted = cover_price = _price - _range;
    	     }
    	     if(status == COVERING)
    	     {
       	       OrderedRec[] ors = new OrderedRec[1];
       	       ors[0] =  (OrderedRec)my_ors[COVERING]; 
    	         processCOVERing(ors);
    	     } 
       }
       return status;
   }

   public boolean isEnd()
   {
      return status == CLOSED;
   }


   public Object getStatusObject()throws Exception
   {
      getStatus();
      return my_ors[status];
   }
   
   public void dump()
   {
       System.out.println("=="+getCurrentTimeStr()+"========TSM_Transaction "+this+" price:"+_price+" range:"+_range+" dump start======"); 
       OrderRec ors = null;   
       OrderRec crs = null;   
       if(my_ors[OPENED]!=null && my_ors[OPENED] instanceof OrderedRec)
       {
         System.out.println("==========my_ors[OPENED] dump ======");
         ((OrderedRec)my_ors[OPENED]).dump();
         ors = ((OrderedRec)my_ors[OPENED]).or_source;
       }
       if(my_ors[CLOSED]!=null && my_ors[CLOSED] instanceof OrderedRec)
       {
         System.out.println("==========my_ors[CLOSED] dump ======");
         ((OrderedRec)my_ors[CLOSED]).dump();
         crs = ((OrderedRec)my_ors[CLOSED]).or_source;
       }
       if(ors != null && crs!=null )
       {
          float diff = Float.parseFloat(crs.price) -  Float.parseFloat(ors.price);
          if("S".equals(crs.buy_or_sell))
          {
              System.out.println("TSM_Transaction Diff: "+diff);
          } else
          {
              System.out.println("TSM_Transaction Diff: "+(-1*diff));
          }
       } 
       System.out.println("==========TSM_Transaction "+this+" dump end======");   
       
   }

}
