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

public class T4_TransactionManager extends Thread{

   static public T4_TransactionManager  one_instance;

	 Vector<T4_Transaction>  historyPool;
	 T4_Transaction currentTransaction;
   Object empty_wait_object;
   Object opened_wait_object;
   Object transaction_locker;
   boolean op_status;
   String  op_status_error_msg;
   int    transaction_status;
   float  max_loss;
   int    trans_op;
   static public boolean mesg_status;
   public T4_TransactionManager()
   {
   	    historyPool = new Vector<T4_Transaction>();
   	    currentTransaction = null;
   	    empty_wait_object = new Object();
   	    opened_wait_object = new Object();
   	    transaction_locker = new Object();
   	    op_status = true;
   	    transaction_status = -1;
   	    max_loss = -999;
   	    trans_op = -1;
   }
   
   public void setMaxLoss(float mlos)
   {
       max_loss = mlos;
   }
   
   void checkLoss() throws Exception
   {
       if(currentTransaction != null)
       {
           UnSettledRec  uns_rec = currentTransaction.getUnSettled();
           if(uns_rec != null)
           {
              if(uns_rec.ord_bs)
              {
                 float currentLoss = uns_rec.cur_price - uns_rec.avg_price;
                 if(currentLoss < max_loss)
                 {
                 	   cover((int)uns_rec.cur_price-1);
                 }
              } else
              { 
             	  float currentLoss = uns_rec.avg_price - uns_rec.cur_price;
                if(currentLoss < max_loss)
                {
                	  cover((int)uns_rec.cur_price+1);
                }
              }
           }
       }
   }
   public int getTransactionStatus()
   {
      return transaction_status;
   }
   
   public int open(int op, int price, String which_code)
   {
      int result = -1;
      if(!op_status) return -1;
      try
      {
         synchronized(transaction_locker) {
            if(currentTransaction == null)
            {
                currentTransaction = new T4_Transaction(op, price, which_code);
                trans_op = op;
                synchronized(empty_wait_object) {
                   empty_wait_object.notify();
                }
                result = 0;
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
   
   public int cover(int price)
   {
      int result = -1;
      if(!op_status) return -1;
      try
      {
         synchronized(transaction_locker) {
            if(currentTransaction != null)
            {
                if(currentTransaction.cover(price)!= T4_Transaction.OP_FAIL) 
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
                if(currentTransaction.cancel()!= T4_Transaction.OP_FAIL) result = 0;
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
   
   public void run() {
   	 try
   	 {
   	 	 while(true)
   	 	 {
   	      if(currentTransaction == null)
   	      {
   	         synchronized(empty_wait_object) {
   	         	 System.out.println("start waiting a new transaction");
   	           empty_wait_object.wait();
  	         	 System.out.println("a new transaction in");
   	         }
   	      }
   	      if(!op_status) throw new Exception(op_status_error_msg);
   	      if(currentTransaction != null)
   	      {
   	          transaction_status = currentTransaction.getStatus();
   	          switch(transaction_status)
   	          {
   	             case T4_Transaction.INITED:
   	             {
   	                 currentTransaction.open();
   	             }
   	             break;
   	             case T4_Transaction.OPENING:
   	             {
   	                 sleep(1000);
   	             }
   	             break;
   	             case T4_Transaction.OPENED:
   	             {
   	             	/*
   	                synchronized(opened_wait_object) {
    	         	      System.out.println("start waiting  transaction to covering");
  	                  opened_wait_object.wait();
    	         	      System.out.println("transaction to covering");
   	                }
   	                */
   	                checkLoss();
   	                sleep(1000);
   	             }
   	             break;
   	             case T4_Transaction.CANCELING:
   	             {
   	                 sleep(300);
   	             }
   	             break;
   	             case T4_Transaction.OPEN_COVERING:
   	             {
   	                 System.out.println("T4_Transaction.OPEN_COVERING");
   	                 checkLoss();
   	                 sleep(500);
   	             }
   	             break;
   	             case T4_Transaction.COVERING:
   	             {
    	               System.out.println("T4_Transaction.COVERING");
   	                 checkLoss();
   	                 sleep(500);
   	             }
   	             break;
   	             case T4_Transaction.CLOSED:
   	             {
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
     	  ByteArrayOutputStream bs = new ByteArrayOutputStream();
     	  PrintStream ps = new PrintStream(bs);
     	  e.printStackTrace(ps);
     	  try
     	  {
     	  	do
     	  	{
     	  	  mesg_status = false;
     	      Skype.chat("wang.tajeng").send(e.getMessage()+"\r\nStack Trace:\r\n"+bs.toString());
    	      sleep(5000);
    	    } while(mesg_status == false);
    	//     Skype.chat("peiludai").send(e.getMessage()+"\r\nStack Trace:\r\n"+bs.toString());
     	  }catch(Exception e2)
     	  {
     	     e2.printStackTrace();
     	  }
     	  e.printStackTrace();
        return ;
     }
   }
   static void testRun1(int op,int open_price,int cover_price,int maxLoss, String code)
   {
       T4_TransactionManager t4_m = new T4_TransactionManager();
       one_instance = t4_m;
       t4_m.start();
       T4_TransactionManager.one_instance.open(op, open_price, code);
       while(T4_TransactionManager.one_instance.getTransactionStatus() < T4_Transaction.OPENING)
       {
          try{
           Thread.currentThread().sleep(200);
          }catch(Exception e)
          {
          }
          System.out.println("wait state change");
       } 
       System.out.println("cover result:"+T4_TransactionManager.one_instance.cover(cover_price));
       T4_TransactionManager.one_instance.setMaxLoss(maxLoss);
       while(T4_TransactionManager.one_instance.getTransactionStatus() != T4_Transaction.OPEN_COVERING)
       {
          try{
           Thread.currentThread().sleep(200);
          }catch(Exception e)
          {
          }
          System.out.println("wait state change");
       }
       System.out.println("State Changed");
   
   }
   static void testRun2()
   {
   	 try
   	 {
       T4_TransactionManager t4_m = new T4_TransactionManager();
       one_instance = t4_m;
       t4_m.start();
       Skype.setDeamon(false); // to prevent exiting from this program
       Skype.addChatMessageListener(new ChatMessageAdapter() {
           public void chatMessageReceived(ChatMessage received) throws SkypeException {
               if (received.getType().equals(ChatMessage.Type.SAID)) {
               	 System.out.println(received.getSenderId());
               	 if("peiludai".equals(received.getSenderId()))
               	 {
                	   String content = received.getContent();
                	   System.out.println("peiludai:"+content);
                	   if(content.indexOf("open")==0)
                	   {
                 	   	   String price_v = content.substring(5);
                	   	   System.out.println("price "+price_v);
                	       System.out.println("cover result:"+T4_TransactionManager.one_instance.open(T4_Transaction.OP_BUY, Integer.parseInt(price_v), "MXFJ4"));
               	      
                	   } else if(content.equals("cancel"))
                	   {
                	       T4_TransactionManager.one_instance.cancel();
                	   } else if(content.indexOf("cover")==0)
                	   {
                	   	   String price_v = content.substring(5);
                	   	   System.out.println("price "+price_v);
                	       System.out.println("cover result:"+T4_TransactionManager.one_instance.cover(Integer.parseInt(price_v)));
                	   }
                 }
               }
           }
       });
     }catch(Exception e)
     {
     	  ByteArrayOutputStream bs = new ByteArrayOutputStream();
     	  PrintStream ps = new PrintStream(bs);
     	  e.printStackTrace(ps);
     	  try
     	  {
     	     Skype.chat("peiludai").send(e.getMessage()+"\r\nStack Trace:\r\n"+bs.toString());
     	  }catch(Exception e2)
     	  {
     	     e2.printStackTrace();
     	  }
     	  e.printStackTrace();
     }
  
   }
   static void SkypeRecving()
   {
   	 try
   	 {
       Skype.setDeamon(false); // to prevent exiting from this program
       Skype.addChatMessageListener(new ChatMessageAdapter() {
           public void chatMessageReceived(ChatMessage received) throws SkypeException {
               if (received.getType().equals(ChatMessage.Type.SAID)) {
               	 System.out.println(received.getSenderId());
               	 if("wang.tajeng".equals(received.getSenderId()))
               	 {
                	   String content = received.getContent();
                	   System.out.println("wang.tajeng:"+content);
                	   if(content.indexOf("ok")==0)
                	   {
                	   	   T4_TransactionManager.mesg_status = true;
                	   }
                 }
               }
           }
       });
     }catch(Exception e)
     {
     	  ByteArrayOutputStream bs = new ByteArrayOutputStream();
     	  PrintStream ps = new PrintStream(bs);
     	  e.printStackTrace(ps);
     	  try
     	  {
     	     Skype.chat("wang.tajeng").send(e.getMessage()+"\r\nStack Trace:\r\n"+bs.toString());
     	  }catch(Exception e2)
     	  {
     	     e2.printStackTrace();
     	  }
     	  e.printStackTrace();
     }
  
   }

   public static void main(String[] args)
   {
      SkypeRecving();
      testRun1(T4_Transaction.OP_BUY, 8998, 9003,-10, "MXFJ4");
   }

}
