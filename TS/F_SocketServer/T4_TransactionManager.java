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

public class T4_TransactionManager extends Thread{
	 Vector<T4_Transaction>  historyPool;
	 T4_Transaction currentTransaction;
   Object empty_wait_object;
   Object opened_wait_object;
   Object transaction_locker;
   boolean op_status;
   public T4_TransactionManager()
   {
   	    historyPool = new Vector<T4_Transaction>();
   	    currentTransaction = null;
   	    empty_wait_object = new Object();
   	    opened_wait_object = new Object();
   	    transaction_locker = new Object();
   	    op_status = true;
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
                empty_wait_object.notify();
                result = 0;
            }
         }
         
      }catch(Exception e)
      {
          e.printStackTrace();
          result = -1;
          op_status = false;
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
                if(currentTransaction.cover(price)!= T4_Transaction.OP_FAIL) result = 0;
            }
         }
      }catch(Exception e)
      {
          e.printStackTrace();
          result = -1;
          op_status = false;
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
          e.printStackTrace();
          result = -1;
          op_status = false;
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
   	      if(currentTransaction != null)
   	      {
   	          switch(currentTransaction.getStatus())
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
   	                synchronized(opened_wait_object) {
    	         	      System.out.println("start waiting  transaction to covering");
  	                  opened_wait_object.wait();
    	         	      System.out.println("transaction to covering");
   	                }
   	             }
   	             break;
   	             case T4_Transaction.CANCELING:
   	             {
   	                 sleep(300);
   	             }
   	             break;
   	             case T4_Transaction.OPEN_COVERING:
   	             {
   	                 sleep(1000);
   	             }
   	             break;
   	             case T4_Transaction.COVERING:
   	             {
   	                 sleep(1000);
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
     	  e.printStackTrace();
        return ;
     }
   }
   
   public static void main(String[] args)throws Exception
   {

   }

}
