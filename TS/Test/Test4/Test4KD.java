import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test4KD extends StockKD{

public   Test4KD(String snum)throws Exception
 {
 	   this(snum, null,"0",1);
 }
 
public  Test4KD(String snum,String dateStr,String p,int type)throws Exception
 {
  // dayKdata = ;
   Test_Rec tr = new  Test_Rec();
 	 String queryStr = null; 

   Enumeration e = null;
   if(dateStr == null){
    	 queryStr = "select snum as f1,date as f2,rangeS as f3,timeS as f4,rangeE as f5 ,"+
    	 " timeE as f6,amount as f7,'f8' as f8,'f9' as f9 ,'f10' as f10, "+
    	 " 'f11' as f11,'f12' as f12,'f13' as f13 "+
       "from  pamountinfo4A where snum='"+snum+"'  and rangeS-"+p+" > -0.001 and rangeS-"+
        p+" < 0.001  order by date,timeS;";
   }else {
    	 queryStr = "select snum as f1,date as f2,rangeS as f3,timeS as f4,rangeE as f5 ,"+
    	 " timeE as f6,amount as f7,'f8' as f8,'f9' as f9 ,'f10' as f10, "+
    	 " 'f11' as f11,'f12' as f12,'f13' as f13 "+
       "from  pamountinfo4A where snum='"+snum+"' and date > '"+dateStr+"' and rangeS-"+p+" > -0.001 and rangeS-"+
        p+" < 0.001 order by date,timeS;";
       
   }
   System.out.println(queryStr);
      e = tr.SelectBySQL(queryStr );    
   while(e.hasMoreElements()){
   	  tr = (Test_Rec)e.nextElement();
   	  System.out.println(tr.f1 +" "+tr.f2+" "+tr.f3+" "+tr.f4+" "+tr.f5+" "+tr.f6+" "+tr.f7);
      if( ((type == 1)&&(Float.parseFloat(tr.f5) > Float.parseFloat(tr.f3))) ||
          ((type == 2)&&(Float.parseFloat(tr.f5) < Float.parseFloat(tr.f3)))
          )
      {
         PLDayk_Rec pkr = new  PLDayk_Rec();
         pkr.snum=tr.f1;
         pkr.date=tr.f2;
         pkr.time=tr.f4; 
         pkr.open=tr.f7;
         pkr.close=tr.f7;
         pkr.high=tr.f7;
         pkr.low=tr.f7;
         pkr.volume=tr.f7;
         pkr.money=tr.f7;
        add(pkr);
      }   
   }
 }


}
