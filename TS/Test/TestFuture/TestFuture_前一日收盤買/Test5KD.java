import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test5KD extends StockKD{


public  Test5KD(String dateStr,int type)throws Exception
 {
   PLDayk_Rec plr = new PLDayk_Rec();
 //  Enumeration e = plr.SelectBySQL("select * from ptxdayk where  date>'"+dateStr+"' order by date,time;");
   Enumeration e = plr.SelectBySQL("select * from pldayk where  date>'"+dateStr+"' and time=0 order by date,time;");
   while(e.hasMoreElements())
   {
   	   add((PLDayk_Rec)e.nextElement());
   }
/* 	
 	 String queryStr = null; 
   Pfamount_Rec plr = new Pfamount_Rec();
   Pfamount_Rec prev_plr = null;
   int s_p = 0;
   int l_p = 0;
   int h_p = -10;
   int m_p =9999999;
   int v = 0;
   int m = 0;
   Enumeration e = plr.SelectBySQL("select * from pfamount where  date>'"+dateStr+"' order by date,time;");
   while(e.hasMoreElements())
   {
       plr = (Pfamount_Rec)e.nextElement();
       int pprice = Integer.parseInt(plr.price);
       if(s_p == 0) 
       {
          s_p = pprice;
       }
       l_p = pprice;
       if(h_p < pprice)
       {
          h_p = pprice;
       }
       if(m_p > pprice)
       {
          m_p = pprice;
       }
       v += Integer.parseInt(plr.ra);
       m += Integer.parseInt(plr.count);
       if(prev_plr != null && Integer.parseInt(plr.time)/100 != Integer.parseInt(prev_plr.time)/100)
       {
         PLDayk_Rec pkr = new  PLDayk_Rec();
         pkr.snum=prev_plr.fcode;
         pkr.date=prev_plr.date;
         pkr.time=String.valueOf(Integer.parseInt(prev_plr.time)); 
         pkr.open=String.valueOf(s_p);
         pkr.close=String.valueOf(l_p);
         pkr.high=String.valueOf(h_p);
         pkr.low=String.valueOf(m_p);
         pkr.volume=String.valueOf(m);
         pkr.money=String.valueOf(v);
  //       System.out.println(pkr.date+":"+pkr.time);
         add(pkr);
         s_p = 0;
         l_p = 0;
         h_p = -10;
         m_p =9999999;
         v = 0;
         m = 0;
       }
       prev_plr = plr;
   }*/
 }


}
