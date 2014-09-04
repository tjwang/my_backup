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
//import org.apache.commons.codec.binary.Base64;

public class Cancel_Call {

    public static void main(String[] args) throws Exception 
    {
    	 T4_Call t4_c = new T4_Call();
//    	 FPriceRec fp_rec = new FPriceRec(Integer.parseInt(args[1]));
//    	 fp_rec.dump();
    	 OrderRec my_or = new OrderRec();
    	 my_or.buy_or_sell = "B";
    	 my_or.code = "MXFI4";
    	 my_or.price = String.valueOf(9409);
    	 my_or.amount = String.valueOf(1);
       System.out.println(t4_c.orderFuture(my_or));
   }
}

