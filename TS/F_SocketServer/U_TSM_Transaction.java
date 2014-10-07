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

public class U_TSM_Transaction extends TSM_Transaction{
	 
	 BufferedReader br;
	 
	 public U_TSM_Transaction(int price, int range, String which_code)
	 {
        br  = new BufferedReader(new InputStreamReader(System.in));
        init(price, range, which_code);
	 }
   public double getCurrentPrice()
   {
   	 double cur_price = -1;
   	 while(cur_price < 0)
   	 {
        try{
           System.out.print("Pleas input current price:");
           cur_price = Double.parseDouble(br.readLine());
         }catch(Exception e)
         {
         	 e.printStackTrace();
           cur_price = -1;
         }
     }     
     return cur_price;
   
   }
   

}
