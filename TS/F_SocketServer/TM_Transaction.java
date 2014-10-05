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

public class TM_Transaction implements Transaction{
   static T4_Call _my_t4_call = null;
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
   private    OrderRec my_or_b;
   private    OrderRec my_or_s;
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
   public TM_Transaction(int price, int range, String which_code)
   {
       if(_my_t4_call == null)
       {
           _my_t4_call = new T4_Call();
           T4_Transaction._my_t4_call = _my_t4_call;
       }
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
   }
   
   public int open()throws Exception
   {
   	  if(status == INITED)
   	  {
   	 	 //  OrderRec mo = (OrderRec)my_ors[INITED];
   	 	   System.out.println("to open tm transaction");
   	 	   String result = _my_t4_call.orderFuture(my_or_b);
   	 	   if(result == null || result.length() != 184)
   	 	   {
   	 	      System.out.println(result.length()+":"+result);
   	 	      throw new Exception("fail at a opening order");
   	 	   }
         my_or_b.seq_no = result.substring(55,61);
         System.out.println(result.length()+":"+my_or_b.seq_no);
         
         result = _my_t4_call.orderFuture(my_or_s);
   	 	   if(result == null || result.length() != 184)
   	 	   {
   	 	      System.out.println(result.length()+":"+result);
   	 	      throw new Exception("fail at a opening order");
   	 	   }
         my_or_s.seq_no = result.substring(55,61);
         System.out.println(result.length()+":"+my_or_s.seq_no);
         
         status = OPEN_COVERING;
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
         while(my_ors[OPENING] == null)
         {
         	  if(sleep_count > 10) throw new Exception("can't not get a valid order!!");
            Thread.currentThread().sleep(500);
            getStatus();
            sleep_count ++;
         }
         my_ors[CANCELING] =  my_ors[OPENING];
         String result = _my_t4_call.cancelOrdered((OrderedRec)my_ors[CANCELING]);
   	 	   System.out.println("o cancel r:"+result);
   	 	   if(result == null || result.length() != 184)
   	 	   {
   	 	      System.out.println(result.length()+":"+result);
   	 	      throw new Exception("fail at a opening cancle");
   	 	   }
         while(my_ors[OPEN_COVERING] == null)
         {
         	  if(sleep_count > 10) throw new Exception("can't not get a valid order!!");
            Thread.currentThread().sleep(500);
            getStatus();
            sleep_count ++;
         }
         my_ors[CANCELING] =  my_ors[OPEN_COVERING];
         result = _my_t4_call.cancelOrdered((OrderedRec)my_ors[CANCELING]);
   	 	   System.out.println("c cancel r:"+result);
   	 	   if(result == null || result.length() != 184)
   	 	   {
   	 	      System.out.println(result.length()+":"+result);
   	 	      throw new Exception("fail at a covering cancle");
   	 	   }
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
      if(status == COVERING)
      {   if(my_ors[COVERING] == null)
      	  {
      	     syncCOVERingWithUnSettled();
      	  }
      	  if(my_ors[COVERING] == null)
      	  {
      	     if(cover_null_warning_count++ > 5)
      	     {
                //  throw new Exception("cover_null_warning_count > 5");      	    
      	     }
      	  }
    	    for(int i=0; i<ors.length; i++)
    	    {
    	      	if(my_ors[COVERING] instanceof OrderRec)
    	      	{
    	      	   OrderRec mo = (OrderRec)my_ors[COVERING];
   	             if(ors[i] != null && ors[i].ord_seq != null && ors[i].ord_seq.equals(mo.seq_no) && ors[i].ord_no != null && ors[i].ord_no.trim().length() > 0)
   	             {
    	              ors[i].or_source = mo;
    	              my_ors[COVERING] = ors[i];
    	              if(!ors[i].queryValid())
    	              {
    	                 // my_ors[COVERING] = null;
    	                  //Thread.currentThread().sleep(2000);
    	                  //syncCOVERingWithUnSettled();
    	                 // if(my_ors[COVERING] == null)
    	                  {
    	                     status = CLOSED;
    	                     my_ors[CLOSED] = ors[i];
    	                  } 
    	               }
    	            }
    	         } else if(my_ors[COVERING] instanceof OrderedRec)
    	         {
    	      	     OrderedRec mo = (OrderedRec)my_ors[COVERING];
   	               if(ors[i] != null && ors[i].ord_seq != null && ors[i].ord_seq.equals(mo.ord_seq) && ors[i].ord_no != null && ors[i].ord_no.trim().length() > 0)
   	               {
    	                ors[i].or_source = mo.or_source;
    	                my_ors[COVERING] = ors[i];
    	                if(!ors[i].queryValid())
    	                {
    	                    //my_ors[COVERING] = null;
    	                    //Thread.currentThread().sleep(2000);  
    	                    //syncCOVERingWithUnSettled();
    	                    //if(my_ors[COVERING] == null)
    	                    {
    	                       status = CLOSED;
    	                       my_ors[CLOSED] = ors[i];
    	                    } 
    	                }
    	             }
    	         }
    	    }
      }
   }
   
   private void syncCOVERingWithUnSettled()throws Exception
   {
      System.out.println("syncCOVERingWithUnSettled()");
      if(status == COVERING || status == OPEN_COVERING)
      {
         if(my_ors[status] != null)
         {
         	  int sleep_count = 0;
         	  while(my_ors[status] instanceof OrderRec)
         	  {
         	      getStatus();
         	      if(sleep_count > 10) throw new Exception("can't not get a valid order at covering cancelining !!");
                Thread.currentThread().sleep(500);
                sleep_count ++;
         	  }
            String result = _my_t4_call.cancelOrdered((OrderedRec)my_ors[status]);
   	 	      if(result == null || result.length() != 184)
   	 	      {
   	 	         System.out.println(result.length()+":"+result);
   	 	         throw new Exception("fail at a covering cancle");
   	 	      }
   	 	      Thread.currentThread().sleep(300);
   	 	      my_ors[status] = null;
         }
         if(need_to_delay)
         {
            Thread.currentThread().sleep(1000);
            need_to_delay = false;
         }
         UnSettledRec uns_rec=_my_t4_call.queryUnSettled("1","0");
         if(uns_rec != null)
         {
            uns_rec.dump();
            open_price = uns_rec.avg_price ;
            OrderRec my_or = new OrderRec();
            if(!uns_rec.ord_bs)
            {
               my_or.buy_or_sell = "B";
               my_or.price = String.valueOf(cover_price);
            } else
            {
               my_or.buy_or_sell = "S";
               my_or.price = String.valueOf(cover_price);
            }
            my_or.code =   uns_rec.code;
            my_or.amount = String.valueOf((int)uns_rec.vol);
   	 	      String result = _my_t4_call.orderFuture(my_or);
   	 	      if(result == null || result.length() != 184)
   	 	      {
   	 	         System.out.println(result.length()+":"+result);
  	 	         throw new Exception("fail at a covering order");
    	 	    }
    	 	    cover_price_setted = cover_price;
            my_or.seq_no = result.substring(55,61);
            my_ors[status] = my_or;
         }
      }
   }
   
   public float getLossMoney() throws Exception
   {
         UnSettledRec uns_rec=_my_t4_call.queryUnSettled("1","0");
         if(uns_rec != null)
         {
//            uns_rec.dump();
            return uns_rec.loss;
         }
         return 0;
   }
   public float getLossPoint() throws Exception
   {
         UnSettledRec uns_rec=_my_t4_call.queryUnSettled("1","0");
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
   public UnSettledRec getUnSettled() throws Exception
   {
         UnSettledRec uns_rec=_my_t4_call.queryUnSettled("1","0");
         return uns_rec;
   }
   
   public int getStatus()throws Exception
   {
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
            OrderedRec[] ors = _my_t4_call.queryOrdered();
            OrderedRec odcancle1 = (OrderedRec)my_ors[OPENING];
            OrderedRec odcancle2 = (OrderedRec)my_ors[OPEN_COVERING];
    	      for(int i=0; i<ors.length; i++)
    	      {
   	            if(ors[i] != null && ors[i].ord_seq != null && ors[i].ord_seq.equals(odcancle1.ord_seq)&& ors[i].ord_no.trim().length() > 0)
   	            {
   	            	 my_ors[OPENING] = ors[i];
     	          } else if(ors[i] != null && ors[i].ord_seq != null && ors[i].ord_seq.equals(odcancle2.ord_seq)&& ors[i].ord_no.trim().length() > 0)
     	          {
     	             my_ors[OPEN_COVERING]= ors[i];
     	          }
    	      }
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
       	  OrderedRec[] ors = _my_t4_call.queryOrdered();
          processCOVERing(ors);
       }else if(status == OPEN_COVERING)
       {
          OrderedRec[] ors = _my_t4_call.queryOrdered();
    //      OrderRec mo = (OrderRec)my_ors[INITED];
    //      boolean needToSync = false;
    	    for(int i=0; i<ors.length; i++)
    	    {
    	    	  if(ors[i] != null && ors[i].ord_seq != null && ors[i].ord_seq.equals(my_or_b.seq_no) && ors[i].ord_no.trim().length() > 0)
   	          {
    	              my_ors[OPENING] = ors[i];
    	              
    	        } else if(ors[i] != null && ors[i].ord_seq != null && ors[i].ord_seq.equals(my_or_s.seq_no) && ors[i].ord_no.trim().length() > 0)
   	          {
    	              my_ors[OPEN_COVERING] = ors[i];
    	        }  
    	    }
           OrderedRec order1 = (OrderedRec)my_ors[OPENING];
           OrderedRec order2 = (OrderedRec)my_ors[OPEN_COVERING];
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
    	         processCOVERing(ors);
    	     } 
       }
       return status;
   }

   public Object getStatusObject()throws Exception
   {
      getStatus();
      return my_ors[status];
   }
public static void main(String[] args)throws Exception
{
     T4_Transaction txc = new T4_Transaction(OP_BUY, 8500, "MXFJ4");
     txc.open();
     int status;
     while(txc.getStatus() == OPENING)
     {
          System.out.println("cancel ...."+txc.cancel());
     }
     Thread.currentThread().sleep(300);
     System.out.println(txc.getStatus());
 /*    
     if(status == OPENED)
     {
        txc.cancle();
     }
     do
     {
       status = txc.getStatus();
       System.out.println(status);
     } while(status == CANCEL_SENT);
     System.out.println(status);
  */   
}

}
