import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
//尋找營收Yoy 成長
public class TT_sFactory extends KDataLineFactory{
 
    Vector<F2_Rec> infodata ;
    String  _sqlStr;
    String  _snum;
    String  _startdate;

    public    TT_sFactory()
    {
    }
    
    public    TT_sFactory(String snum,String stardate) throws Exception
    {
    	  _snum = snum;
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
    public void loadIData() throws Exception
    {
        dayKdata = new TreeSet(new KdataComparator());
        Incomes_Rec imr = new Incomes_Rec();
        Incomes_Rec pimr = null;
        String mOpen  = null;
        String mHigh  = null;
        String buyer  = null;
        String mClose = null;
        String mLow   = null;
        String seller = null;
        Enumeration e = null;
        System.out.println("loadIData "+_snum);
        if(_startdate == null){
            e = imr.SelectBySQL("select * from  incomes where   snum='"+_snum+"' order by year,m "  ); 
        }else {
            e = imr.SelectBySQL("select * from  incomes where   snum='"+_snum+"' order by year,m  "  );    
        }
        while(e.hasMoreElements()){
        	imr = (Incomes_Rec)e.nextElement();
        	if(mOpen == null) 
        	{
        		 double d_curMon = Double.parseDouble(imr.CurMon);
        		 double d_lastMon = Double.parseDouble(imr.LastMon);
        		 double d_lastYear = Double.parseDouble(imr.LastYear);
        		 mOpen = String.valueOf(((d_curMon/d_lastMon)-1)*100);
        		 mLow = mOpen;
        		 buyer = mOpen;
       		   mClose = String.valueOf(((d_curMon/d_lastYear)-1)*100);
        		 mHigh = mClose;
        		 seller = mClose;
           }
          //if(ppmr!=null && !pmr.date.equals(ppmr.date))
          {
             PLDayk_Rec pkr= new PLDayk_Rec();
        		 pkr.snum = imr.snum;
        		 pkr.date = String.valueOf(Integer.parseInt(imr.year)*10000+Integer.parseInt(imr.m)*100+1);
        		 pkr.time = "0";
        		 pkr.open = mOpen;
        		 pkr.high = mHigh;
        		 pkr.close = mClose;
        		 pkr.low = mLow;
        		 pkr.volume = buyer;
        		 pkr.money = seller;
             add(pkr);
             mOpen  = null;
             mHigh  = null;
             buyer  = null;
             mClose = null;
             mLow   = null;
             seller = null;
          }
          pimr = imr;
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
