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
import com.skype.Skype;
import com.skype.ChatMessage;
import com.skype.ChatMessageAdapter;
import com.skype.Skype;
import com.skype.SkypeException;

public class S4_TransactionManager {

   static public S4_TransactionManager  one_instance;

	 Vector<Transaction>  historyPool;
	 Transaction currentTransaction;
   Object empty_wait_object;
   Object opened_wait_object;
   Object transaction_locker;
   boolean op_status;
   String  op_status_error_msg;
   int    transaction_status;
   float  max_loss;
   float  max_get;
   int    trans_op;
   int    do_count;
   int    fail_count;
   int    timeOut;
   int    open_time;
   boolean is_ever_covering;
   boolean is_last_fail;
   boolean is_MaxGetSet;
   static public boolean mesg_status;
   PrintStream dump_ps;
   public S4_TransactionManager(String ds)
   {
   	    historyPool = new Vector<Transaction>();
   	    currentTransaction = null;
   	    empty_wait_object = new Object();
   	    opened_wait_object = new Object();
   	    transaction_locker = new Object();
   	    op_status = true;
   	    transaction_status = -1;
   	    max_loss = -999;
   	    max_get = 0;
   	    trans_op = -1;
   	    is_last_fail = false;
   	    do_count = 0;
   	    fail_count = 0;
   	    timeOut = 0;
   	    open_time = 0;
   	    try{
   	      dump_ps = new PrintStream(ds+".dump.log");
        }catch(FileNotFoundException fe)
        {
        }
   }
   
   int getCurrentTime()
   {
      int ti = Integer.parseInt(((TSM_Transaction)currentTransaction).getCurrentTimeStr());
      int h = ti/10000;
      int m = (ti/100)%100;
      int s = ti%100;
      return 60*60*h+60*m+s;
   }
   
   public void setMaxLoss(float mlos)
   {
       max_loss = mlos;
   }
   
   public void setMaxGet(float mget)
   {
       max_get = mget;
       is_MaxGetSet = false;
   }

   public void setTimeOut(int TO)
   {
       timeOut = TO;
   }
   

   public float getMaxLoss()
   {
       return max_loss;
   }
   
   public float getMaxGet()
   {
      return  max_get;
   }

   public int getTimeOut()
   {
       return timeOut;
   }
   
   void checkTimeOut() 
   {
      if(timeOut > 0)
      {
         if(getCurrentTime() - open_time > timeOut)
         {
            cancel();
         }
      }
   }
   void checkLoss() throws Exception
   {
       if(currentTransaction != null)
       {
           UnSettledRec  uns_rec = currentTransaction.getUnSettled();
           if(uns_rec != null)
           {
           	  uns_rec.dump();
              if(uns_rec.ord_bs)
              {
                 float currentLoss = uns_rec.cur_price - uns_rec.avg_price;
                 if(currentLoss < max_loss)
                 {
                 	   cover((int)uns_rec.cur_price-1);
                 	   is_last_fail = true;
                 } else if(!is_MaxGetSet && max_get>=1)
                 {
                 	   cover((int)(uns_rec.avg_price+max_get));
                     is_MaxGetSet = true;
                 }
              } else
              { 
             	  float currentLoss = uns_rec.avg_price - uns_rec.cur_price;
                if(currentLoss < max_loss)
                {
                	  cover((int)uns_rec.cur_price+1);
                	  is_last_fail = true;
                } else if(!is_MaxGetSet&& max_get>=1)
                {
                 	   cover((int)(uns_rec.avg_price-max_get));
                     is_MaxGetSet = true;
                }
              }
           }
       }
   }
   public int getTransactionStatus()
   {
      return transaction_status;
   }
   
   public boolean isFail()
   {
      return is_last_fail;
   }

   public int open(Transaction t)
   {
      int result = -1;
      if(!op_status) return -1;
      try
      {
         synchronized(transaction_locker) {
            // synchronized(empty_wait_object) {
                if(currentTransaction == null)
                {
                	 is_last_fail = false;
                   currentTransaction = t;
                   trans_op = t.getOp();
                 	 System.out.println("xxxxx");
                 	 open_time = getCurrentTime();
                 	 is_ever_covering = false;
                 //  empty_wait_object.notify();
                }
                result = 0;
            //}
         }
         
      }catch(Exception e)
      {
          result = -1;
     	    ByteArrayOutputStream bs = new ByteArrayOutputStream();
     	    PrintStream ps = new PrintStream(bs);
     	    e.printStackTrace(ps);
          e.printStackTrace();
          op_status = false;
          op_status_error_msg = e.getMessage()+"\r\nStack Trace:\r\n"+bs.toString();
      }
      return result;
   }

   public int cover(int price)
   {
      int result = -1;
      if(!op_status) return -1;
      try
      {
         synchronized(transaction_locker) {
            if(currentTransaction != null)
            {
                if(currentTransaction.cover(price)!= Transaction.OP_FAIL) 
                { 
                 	 result = 0;
                   //synchronized(opened_wait_object) {
                   //    opened_wait_object.notify();
                   //}
                }
            }
         }
      }catch(Exception e)
      {
          result = -1;
     	    ByteArrayOutputStream bs = new ByteArrayOutputStream();
     	    PrintStream ps = new PrintStream(bs);
     	    e.printStackTrace(ps);
          e.printStackTrace();
          op_status = false;
          op_status_error_msg = e.getMessage()+"\r\nStack Trace:\r\n"+bs.toString();
      }
      return result;
  
   }
   
   public int cancel()
   {
      int result = -1;
      if(!op_status) return -1;
      try
      {
         synchronized(transaction_locker) {
            if(currentTransaction != null)
            {
                if(currentTransaction.cancel()!= Transaction.OP_FAIL) result = 0;
            }
         }
      }catch(Exception e)
      {
          result = -1;
     	    ByteArrayOutputStream bs = new ByteArrayOutputStream();
     	    PrintStream ps = new PrintStream(bs);
     	    e.printStackTrace(ps);
          e.printStackTrace();
          op_status = false;
          op_status_error_msg = e.getMessage()+"\r\nStack Trace:\r\n"+bs.toString();
      }
      return result;
     
   }
   public void start()
   {
  
   }
   public void sRun() {
   	 try
   	 {
   	 	 {
   	    // synchronized(empty_wait_object) {
   	         if(currentTransaction == null)
   	         {
   	         	 System.out.println("start waiting a new transaction");
   	         }
   	     // }
   	      if(!op_status) throw new Exception(op_status_error_msg);
   	      if(currentTransaction != null)
   	      {
   	          transaction_status = currentTransaction.getStatus();
       //       System.out.println("transaction_status->"+transaction_status);   	          
   	          switch(transaction_status)
   	          {
   	             case Transaction.INITED:
   	             {
   	                 currentTransaction.open();
   	 /*
   	                PrintStream tps = System.out;
   	                System.setOut(dump_ps);
    	              System.out.println("Transaction.INITED");
  	                currentTransaction.dump();
   	                System.setOut(tps);
   	  */
   	             }
   	             break;
   	             case Transaction.OPENING:
   	             {
   	     //            System.out.println("Transaction.OPENING");
   	                   checkTimeOut();
   	             }
   	             break;
   	             case Transaction.OPENED:
   	             {
   	             	/*
   	                synchronized(opened_wait_object) {
    	         	      System.out.println("start waiting  transaction to covering");
  	                  opened_wait_object.wait();
    	         	      System.out.println("transaction to covering");
   	                }
   	                */
   	  //               System.out.println("Transaction.OPENED");
   	                checkLoss();
   	             }
   	             break;
   	             case Transaction.CANCELING:
   	             {
   	//                 System.out.println("Transaction.CANCELING");
   	             }
   	             break;
   	             case Transaction.OPEN_COVERING:
   	             {
   	//                 System.out.println("Transaction.OPEN_COVERING");
    	             checkTimeOut();
 	                 checkLoss();
   	             }
   	             break;
   	             case Transaction.COVERING:
   	             {
    	      //         System.out.println("Transaction.COVERING");
   	                 checkLoss();
   	                 is_ever_covering = true;
   	             }
   	             break;
   	             case Transaction.CLOSED:
   	             {
   	                PrintStream tps = System.out;
   	                System.setOut(dump_ps);
   	                System.out.println("Transaction.CLOSED");
   	                currentTransaction.dump();
   	                System.setOut(tps);
   	                if(is_ever_covering)
   	                {
   	                	  do_count ++;
   	                    if(is_last_fail)
   	                    {
   	                      fail_count++;
   	                    }
   	                 }
                     synchronized(transaction_locker) {
                       currentTransaction = null;
                    }
   	             }
   	             break;
   	          }
   	      }
   	   }
     }catch(Exception e)
     {
     	  e.printStackTrace();
        return ;
     }
   }
   public PrintStream getDumpPs()
   {
      return  dump_ps;
   }
   
   public static void main(String[] args)
   {
   }

}
