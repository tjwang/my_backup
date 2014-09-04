import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test4AKD extends StockKD{

public   Test4AKD(String snum)throws Exception
 {
 	   this(snum, "20090101",1);
 }
 static String d2s(Date d)
 {
  return  String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());
 }
 
 static Date s2d(String ds)
 {
  return  new Date(Integer.parseInt(ds.substring(0,4))-1900,
                   Integer.parseInt(ds.substring(4,6))-1,
                   Integer.parseInt(ds.substring(6,8)));
 }
 
 static String getBWorkDay(String ds,String snum)throws Exception
 {
     Date d = s2d(ds);
     int i = 0;
     PLast_Rec plr = new PLast_Rec();
     while(i<2)
     {
     	  d = new Date(d.getTime()-86400000);	
     	  System.out.println("select * from plast where snum = '"+snum+"' and date ='"+d2s(d)+"' order by date;");
        if( plr.SelectBySQL("select * from plast where snum = '"+snum+"' and date ='"+d2s(d)+"' order by date;").hasMoreElements())
        {
           i++;
        }
        if(Integer.parseInt(d2s(d)) < 20090811)
        {
           return d2s(d);
        }
     }
     return d2s(d);
 }
public  Test4AKD(String snum,String dateStr,int type)throws Exception
 {
   Test_Rec tr = new  Test_Rec();
 	 String queryStr = null; 
   PLast_Rec plr = new PLast_Rec();
   Enumeration e = plr.SelectBySQL("select * from plast where snum = '"+snum+"' and date>'"+dateStr+"' order by date;");
   while(e.hasMoreElements())
   {
       plr = (PLast_Rec)e.nextElement();
       float rp = Float.parseFloat(plr.rp);
       float rangeLowBound =(float) (rp * 0.97);
       float rangeHighBound =(float)( rp * 1.03);
       if(type == 1)
       {
    	    queryStr = "select snum as f1,(sum(amount)/sum(tamount))*100 as f2,'f3' as f3,'timeS' as f4,'rangeE' as f5 ,"+
    	    " 'timeE' as f6,'amount' as f7,'f8' as f8,'f9' as f9 ,'f10' as f10, "+
    	    " 'f11' as f11,'f12' as f12,'f13' as f13 "+
          "from  pamountinfo4A where snum='"+snum+"' and date >= '"+getBWorkDay(plr.date,snum)+"' and date <= '"+plr.date+"' and rangeS >"+
           rangeLowBound+" and rangeS <"+rangeHighBound+" and rangeE > rangeS group by snum; ";
        }
       if(type == 2)
       {
    	    queryStr = "select snum as f1,(sum(amount)/sum(tamount))*100 as f2,'f3' as f3,'timeS' as f4,'rangeE' as f5 ,"+
    	    " 'timeE' as f6,'amount' as f7,'f8' as f8,'f9' as f9 ,'f10' as f10, "+
    	    " 'f11' as f11,'f12' as f12,'f13' as f13 "+
          "from  pamountinfo4A where snum='"+snum+"' and date >= '"+getBWorkDay(plr.date,snum)+"' and date <= '"+plr.date+"' and rangeS >"+
           rangeLowBound+" and rangeS <"+rangeHighBound+" and rangeE < rangeS group by snum;";
        }
//         System.out.println(queryStr);
         tr.SelectBySQL(queryStr );
         PLDayk_Rec pkr = new  PLDayk_Rec();
         pkr.snum=tr.f1;
         pkr.date=plr.date;
         pkr.time="000000"; 
         pkr.open=tr.f2;
         pkr.close=tr.f2;
         pkr.high=tr.f2;
         pkr.low=tr.f2;
         pkr.volume=tr.f2;
         pkr.money=tr.f2;
         System.out.println(pkr.date+":"+tr.f2);
        add(pkr);
   }
 }


}
