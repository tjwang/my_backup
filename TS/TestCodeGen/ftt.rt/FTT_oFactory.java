import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class FTT_oFactory extends KDataLineFactory{
 
    Vector<F2_Rec> infodata ;
    String  _sqlStr;
    String  _snum;
    String  _startdate;
    Hashtable datapool_m;
static 	F2_Rec parseTwoField(String code,String ds,String data)
{
      	 F2_Rec f2r = new F2_Rec();
      	 StringTokenizer st = new StringTokenizer(data,"|");
      	 st.nextToken();
         f2r.date = ds;
         f2r.time = st.nextToken();
         f2r.f1 = st.nextToken();
         f2r.f2 = st.nextToken();
        return f2r;
}
static 	PLDayk_Rec parseWDKField(String code,String ds,String data)
{
      	 PLDayk_Rec plk = new PLDayk_Rec();
      	 StringTokenizer st = new StringTokenizer(data,"|");
      	 plk.snum = st.nextToken();
         plk.date = st.nextToken();
         plk.time = st.nextToken();
         plk.open = st.nextToken();
         plk.high = st.nextToken();
         plk.low = st.nextToken();
         plk.close = st.nextToken();
         plk.volume = st.nextToken();
         plk.money = st.nextToken();
         return plk;
}
    public    FTT_oFactory()
    {
    	   
    }
    
    public    FTT_oFactory(String snum,String stardate) throws Exception
    {
        _snum = snum;
        datapool_m = new Hashtable();
        _startdate = stardate;
    	  loadKData(snum, stardate);
    }
    
    public void loadKData(String snum,String stardate) throws Exception
    {
         FileInputStream is = new FileInputStream("X:\\Working\\Data\\"+stardate+"\\fss_8888.log."+stardate);
         byte[] databuf = new byte[512];
         int rlen = 0;
         String s = null;
         int count = 0;
         while((rlen = is.read(databuf)) > 0)
         {
             if(rlen != 512) throw new Exception("Error reading in file format");
             String code = (new String(databuf,0,12)).trim();
             if(code.equals("TX054")||code.equals("TX064")||code.equals("TX094")||code.equals("TX124")||code.equals("TX")) 
             {
                PFFiveRaw_Rec ffrr = new PFFiveRaw_Rec(code, stardate, databuf, 12);
      	        Vector v = (Vector)datapool_m.get(code);
      	        if(v == null)
      	        {
      	           v = new Vector();
      	           datapool_m.put(code,v);
      	        }
      	        v.add(ffrr);
//                count ++;
             }else if(code.equals("1"))/*發行量加權股價指數*/
             {
             
             }else if(code.equals("2"))/*未含金融保險股指數*/
             {
             }else if(code.equals("200"))/*成交金額*/
             {
            
             }else if(code.equals("201")/*成交數量,成交筆數*/
                      ||code.equals("221")/*成交數量,成交筆數<股票> */
                      ||code.equals("110")/*總委買數量,總委買筆數*/
                      ||code.equals("111")/*總委賣數量,總委賣筆數*/
                      ||code.equals("130")/**總委買數量,總委買筆數<股票>*/
                      ||code.equals("131")/*總委賣數量,總委賣筆數<股票>*/
                      ||code.equals("132")/*漲停總委賣數量,漲停總委賣筆數<股票>*/
                      ||code.equals("133")/*漲停總委賣數量,漲停總委賣筆數<股票>*/
                      ||code.equals("134")/*跌停總委賣數量,跌停總委賣筆數<股票>*/
                      ||code.equals("135"))/*跌停總委賣數量,跌停總委賣筆數<股票>*/
             {
      	        String data= (new String(databuf,12,500)).trim();
      	        F2_Rec f2 = parseTwoField(code, stardate, data);
      	        Vector v = (Vector)datapool_m.get(code);
      	        if(v == null)
      	        {
      	           v = new Vector();
      	           datapool_m.put(code,v);
      	        }
      	        v.add(f2);
            }else if(code.equals("220"))/*成交金額<股票>*/
            {
            }else if(code.equals("^HSI")||code.equals("^N225")||code.equals("000001.SS")||code.equals("^KS11"))
            {
      	       String data= (new String(databuf,12,500)).trim();
      	       PLDayk_Rec plk = parseWDKField(code, stardate, data);
//     	         System.out.println(plk.dump());
      	       Vector v = (Vector)datapool_m.get(code);
      	       if(v == null)
      	       {
      	          v = new Vector();
      	          datapool_m.put(code,v);
      	       }
      	       v.add(plk);
            }
         }
          

    }
    
    public Line getLineByCode(String code,int idx)
    {
       if(code.equals("201")/*成交數量,成交筆數*/
                      ||code.equals("221")/*成交數量,成交筆數<股票> */
                      ||code.equals("110")/*總委買數量,總委買筆數*/
                      ||code.equals("111")/*總委賣數量,總委賣筆數*/
                      ||code.equals("130")/**總委買數量,總委買筆數<股票>*/
                      ||code.equals("131")/*總委賣數量,總委賣筆數<股票>*/
                      ||code.equals("132")/*漲停總委賣數量,漲停總委賣筆數<股票>*/
                      ||code.equals("133")/*漲停總委賣數量,漲停總委賣筆數<股票>*/
                      ||code.equals("134")/*跌停總委賣數量,跌停總委賣筆數<股票>*/
                      ||code.equals("135"))/*跌停總委賣數量,跌停總委賣筆數<股票>*/
       {
          Vector v = (Vector)datapool_m.get(code);
          dayKdata = new TreeSet(new KdataComparator());
          Enumeration<F2_Rec> e = v.elements();
          int time_i = 84500;
          double x_open = -1;
          double x_high = -1;
          double x_low = 999999;
          double x_close = 0;
          float  x_ra = 0;
          while(e.hasMoreElements()){
     	      F2_Rec pr = e.nextElement();
     	      if(pr.time == null || pr.time.equals("null")) continue;
     	      if("".equals(pr.f1) && idx == 1)
     	      {
     	         continue;
     	      }
     	      if("".equals(pr.f2) && idx == 2)
     	      {
     	         continue;
     	      }
            int xtime_i = Integer.parseInt("1"+pr.time)-1000000;
            while(xtime_i - time_i >= 100)
            {    	      
        	     if(x_open > 0)
      	          add(code,Integer.parseInt(pr.date), time_i, x_open, x_high, x_low, x_close, x_ra, x_ra);
               if(time_i % 10000 == 5900)
               {
                 time_i += 4000;
               }
               time_i += 100;
               if(xtime_i - time_i < 100 )
               {
                 x_open = -1;
                 x_high = -1;
                 x_low = 999999;
                 x_close = 0;
                 x_ra = 0;
               }
             }
             double val = -1;
             if(idx == 1)
               val = Double.parseDouble(pr.f1);
             else
               val = Double.parseDouble(pr.f2);
             if(x_open < 0)
             {
                x_open = val;
             }
             if(val < x_low) x_low = val;
             if(val > x_high) x_high = val;
             x_close = val;
          }
          if(v==null) return null;
          return culMALine(1);
        } else if(code.equals("TX054")||code.equals("TX064")||code.equals("TX094")||code.equals("TX124")||code.equals("TX")) 
        {
          Vector v = (Vector)datapool_m.get(code);
          dayKdata = new TreeSet(new KdataComparator());
          Enumeration<PFFiveRaw_Rec> e = v.elements();
          int time_i = 84500;
          double x_open = -1;
          double x_high = -1;
          double x_low = 999999;
          double x_close = 0;
          float  x_ra = 0;
          
          while(e.hasMoreElements()){
     	      PFFiveRaw_Rec pr = e.nextElement();
     	      if(pr.time == null || pr.time.equals("null")) continue;
     	      if("".equals(pr.s_rp))
     	      {
     	  //       System.out.println(pr.dump());
     	         continue;
     	      }
            int xtime_i = Integer.parseInt("1"+pr.time)-1000000;
            while(xtime_i - time_i >= 100)
            {    	      
            //if(code.equals("TX054")) 
            //   System.out.println(code +":"+ time_i+"-->"+x_open+"|"+x_high+"|"+x_low+"|"+x_close+"|"+x_ra);;
     	        if(x_open > 0)
      	         add(code,Integer.parseInt(pr.date), time_i, x_open, x_high, x_low, x_close, x_ra, x_ra);
               if(time_i % 10000 == 5900)
               {
                 time_i += 4000;
               }
               time_i += 100;
               if(xtime_i - time_i < 100 )
               {
                  
                  x_open = -1;
                  x_high = -1;
                  x_low = 999999;
                  x_close = 0;
                  x_ra = 0;
               }
            }
            double val = Double.parseDouble(pr.s_rp);
            if(x_open < 0)
            {
               x_open = val;
            }
            if(val < x_low) x_low = val;
            if(val > x_high) x_high = val;
            x_close = val;
            if(pr.s_ra != null && !"".equals(pr.s_ra) && !"null".equals(pr.s_ra))
            {
               x_ra =  Float.parseFloat(pr.s_ra);
            }
         }
         if(v==null) return null;
         return culKLine();
        
        }
        return null;
    }
   /* 
    public void loadMData() throws Exception
    {
        dayKdata = new TreeSet(new KdataComparator());
        PMAmount_Rec pmr = new PMAmount_Rec();
        PMAmount_Rec ppmr = null;
        String mOpen  = null;
        String mHigh  = null;
        String buyer  = null;
        String mClose = null;
        String mLow   = null;
        String seller = null;
        Enumeration e = null;
        if(_startdate == null){
            e = pmr.SelectBySQL("select * from  pmamount2 where   snum='"+_snum+"' order by date,sum "  ); 
        }else {
            e = pmr.SelectBySQL("select * from  pmamount2 where   snum='"+_snum+"' and date > '"+_startdate+"' order by date,sum "  );    
        }
        while(e.hasMoreElements()){
        	pmr = (PMAmount_Rec)e.nextElement();
        	if(mOpen == null) 
        	{
        		 mOpen = pmr.sum;
        		 mLow = "-"+pmr.outAmount;
        		 buyer = pmr.mnum;
          }
          if(ppmr!=null && !pmr.date.equals(ppmr.date))
          {
             PLDayk_Rec pkr= new PLDayk_Rec();
        		 mClose = ppmr.sum;
        		 mHigh = ppmr.inAmount;
        		 seller = ppmr.mnum;
        		 pkr.snum = ppmr.snum;
        		 pkr.date = ppmr.date;
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
          ppmr = pmr;
       }
        
    }
*/
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
