import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class RT_sFactory extends KDataLineFactory{
 
    Vector<F2_Rec> infodata ;
    String  _sqlStr;

    public    RT_sFactory()
    {
    }
    
    public    RT_sFactory(String snum,String stardate) throws Exception
    {
    	  loadKData(snum, stardate);
    }
    
    public void loadKData(String snum,String stardate) throws Exception
    {
        PLDayk_Rec pkr= new PLDayk_Rec();
        Enumeration e = null;
        if(stardate == null){
            e = pkr.SelectBySQL("select snum,date,time,open,high,low,close,ra as volume,money from  pamountinfo7 where   snum='"+snum+"' order by date,time "  ); 
        }else {
            e = pkr.SelectBySQL("select snum,date,time,open,high,low,close,ra as volume,money from  pamountinfo7 where   snum='"+snum+"' and date > '"+stardate+"' order by date,time "  );    
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
		  add(kv.snum, date_value, time_value, 0,0, 0,0,0, 0);
	 }
}  

}
