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
import org.apache.commons.codec.binary.Base64;

public class CutOff_Call {

    public static void main(String[] args) throws Exception 
    {
    	 T4_Call t4_c = new T4_Call();
    	 boolean noorder = true;
    	 do
    	 {
    	    OrderedRec[] ors = t4_c.queryOrdered();
    	    noorder = true;
    	    for(int i=0; i<ors.length; i++)
    	    {
 //   	       if(ors[i] != null)
 //   	               ors[i].dump();
             if(ors[i] != null && ors[i].queryValid())
             {
                t4_c.cancelOrdered(ors[i]);
                noorder = false;
             }
    	    }
    	    System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
    	    Thread.currentThread().sleep(100);
    	    
    	 } while(!noorder);
       UnSettledRec uns_rec=t4_c.queryUnSettled("1","0");
       if(uns_rec != null)
       {
          uns_rec.dump();
          OrderRec my_or = new OrderRec();
          FPriceRec fp_rec = new FPriceRec(Integer.parseInt(args[0]));
          if(!uns_rec.ord_bs)
          {
             my_or.buy_or_sell = "B";
             my_or.price = String.valueOf((int)(fp_rec.price+1));;
          } else
          {
             my_or.buy_or_sell = "S";
             my_or.price = String.valueOf((int)(fp_rec.price-1));;
          }
          my_or.code =   uns_rec.code;
          my_or.amount = String.valueOf((int)uns_rec.vol);
          System.out.println(t4_c.orderFuture(my_or));
       }
   }
}

