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

public   Test5KD(String snum)throws Exception
 {
 	   this(snum, "20090101",1);
 }

public  Test5KD(String snum,String dateStr,int type)throws Exception
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
    	    queryStr = "select snum as f1,sum(tamount)/100 as f2,'f3' as f3,'timeS' as f4,'rangeE' as f5 ,"+
    	    " 'timeE' as f6,'amount' as f7,'f8' as f8,'f9' as f9 ,'f10' as f10, "+
    	    " 'f11' as f11,'f12' as f12,'f13' as f13 "+
          "from  pamountinfo4A where snum='"+snum+"' and date >= '"+GMethod.getBWorkDay(plr.date, snum, 4)+"' and date <= '"+plr.date+"' and rangeS >"+
           rangeLowBound+" and rangeS <"+rangeHighBound+" and rangeE > rangeS group by snum; ";
        }
       if(type == 2)
       {
    	    queryStr = "select snum as f1,sum(tamount)/100 as f2,'f3' as f3,'timeS' as f4,'rangeE' as f5 ,"+
    	    " 'timeE' as f6,'amount' as f7,'f8' as f8,'f9' as f9 ,'f10' as f10, "+
    	    " 'f11' as f11,'f12' as f12,'f13' as f13 "+
          "from  pamountinfo4A where snum='"+snum+"' and date >= '"+GMethod.getBWorkDay(plr.date, snum, 4)+"' and date <= '"+plr.date+"' and rangeS >"+
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
