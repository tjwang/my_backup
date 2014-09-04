import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TestKD extends StockKD{

public   TestKD(String snum)throws Exception
 {
 	   this(snum, "20090101",1);
 }

public  TestKD(String snum,String dateStr,int type)throws Exception
 {
 	 String queryStr = null; 
   PLDayk_Rec plr = new PLDayk_Rec();
   Enumeration e = plr.SelectBySQL("select snum,date,time,open,high,low,rp as close,total as volume,total as  money from plast2 where snum = '"+snum+"' and date>'"+dateStr+"' and ra > 0 order by date;");
   while(e.hasMoreElements())
   {
         //PLDayk_Rec pkr = new  PLDayk_Rec();
         //System.out.println(pkr.date+":"+tr.f2);
         add((PLDayk_Rec)e.nextElement());
   }
 }

public  TestKD(String snum,String dateStr,String dateE)throws Exception
 {
 	 String queryStr = null; 
   PLDayk_Rec plr = new PLDayk_Rec();
   Enumeration e = plr.SelectBySQL("select snum,date,time,open,high,low,rp as close,total as volume,total as  money from plast2 where snum = '"+snum+"' and date>'"+dateStr+"' and date <='"+dateE+"'order by date;");
   while(e.hasMoreElements())
   {
         //PLDayk_Rec pkr = new  PLDayk_Rec();
         //System.out.println(pkr.date+":"+tr.f2);
         add((PLDayk_Rec)e.nextElement());
   }
 }

public  TestKD(String snum,String dateStr,String dateE,int ccn)throws Exception
 {
 	 String queryStr = null; 
   PLDayk_Rec plr = new PLDayk_Rec();
   Enumeration e = plr.SelectBySQL("select snum,date,time,open,high,low,rp as close,total as volume,total as  money from plast2 where snum = '"+snum+"' and date>'"+dateStr+"' and date <='"+dateE+"'order by date;");
   Vector v = new Vector();
   int totalv = 0;
   while(e.hasMoreElements())
   {
         //PLDayk_Rec pkr = new  PLDayk_Rec();
         //System.out.println(pkr.date+":"+tr.f2);
         plr = (PLDayk_Rec)e.nextElement();
         totalv += Integer.parseInt(plr.volume);
         v.add(plr);
   }
   int vunit = totalv / ccn;
   e = v.elements();
   int cur_v = 0;
   float cur_m = 0;
   if(vunit <= 0) return ;
   while(e.hasMoreElements())
   {
       plr = (PLDayk_Rec)e.nextElement();
       int vol = Integer.parseInt(plr.volume);
       int tcount = 90000;
       while(vol + cur_v > vunit)
       {
       	  cur_m += (vunit - cur_v) * Float.parseFloat(plr.close);
          PLDayk_Rec pxl = (PLDayk_Rec)plr.Clone();
          pxl.time = String.valueOf(tcount);
          pxl.close = String.valueOf(cur_m/vunit);
          vol -= (vunit - cur_v);
          cur_v = 0;
          cur_m = 0;
  //        System.out.println(pxl.dump());
          add(pxl);
          tcount++;
       }
       cur_v += vol;
       cur_m += vol * Float.parseFloat(plr.close);
   }
 }

}
