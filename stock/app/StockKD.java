package stock.app;
import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class StockKD extends KDataLineFactory{

 public  StockKD()
 {
 }
 
 public  StockKD(String snum)throws Exception
 {
 	   this(snum, null);
 }
 
 public  StockKD(String snum,String dateStr)throws Exception
 {
   PLDayk_Rec pkr= new PLDayk_Rec();
   Enumeration e = null;
   if(dateStr == null){
      e = pkr.SelectBySQL("select * from  pLDayk where   snum='"+snum+"' order by date,time "  ); 
   }else {
      e = pkr.SelectBySQL("select * from  pLDayk where   snum='"+snum+"' and date > '"+dateStr+"' order by date,time "  );    
   }
   if (!e.hasMoreElements())
   {
      if(dateStr == null){
         e = pkr.SelectBySQL("select * from  pLDayksnum where   snum='"+snum+"' order by date,time "  ); 
      }else {
         e = pkr.SelectBySQL("select * from  pLDayksnum where   snum='"+snum+"' and date > '"+dateStr+"' order by date,time "  );    
      }
  }
   while(e.hasMoreElements()){
      add((PLDayk_Rec)e.nextElement());
   }
 }
 
 public void add(PLDayk_Rec kv){
     int date_value;
     int time_value;
     try{
        date_value = Integer.parseInt(kv.date);
     }catch(Exception ne)
     {
        date_value = 0;
     }
     try{
        if(kv.time.indexOf(":") > 0)
        {
           time_value = Integer.parseInt(kv.time.substring(0,2)) * 10000 + 
                        Integer.parseInt(kv.time.substring(3,5)) * 100 +
                        Integer.parseInt(kv.time.substring(6,8)) ;
        } else
        {
        	 time_value = Integer.parseInt(kv.time);
        }
     }catch(Exception ne)
     {
        time_value = 0;
     }
	 try{
        add(kv.snum, date_value, time_value, Float.parseFloat(kv.open), Float.parseFloat(kv.high), Float.parseFloat(kv.low), Float.parseFloat(kv.close), Float.parseFloat(kv.volume), Float.parseFloat(kv.money));
	 }catch(Exception e2)
	 {
//          System.out.println(kv.dump());
//		  add(kv.snum, date_value, time_value, Float.parseFloat(kv.open), Float.parseFloat(kv.high), Float.parseFloat(kv.low), Float.parseFloat(kv.close), Float.parseFloat(kv.volume), Float.parseFloat(kv.money));
		  add(kv.snum, date_value, time_value, 0,0, 0,0,0, 0);
	 }
}
  
  public MALine culVolMALine(int s)throws Exception {
     TreeSet ts = (TreeSet)dayKdata.clone();
     dayKdata.clear();
     Iterator<KValue> e = ts.iterator();
     while(e.hasNext()){
        KValue kv = e.next();
        add(kv.getName(), kv.getDateValue(), kv.getTimeValue(), (double)kv.getVolume());
     }
     MALine mal = culMALine(s);
     dayKdata = ts;
     return mal;
  }    
        

}
