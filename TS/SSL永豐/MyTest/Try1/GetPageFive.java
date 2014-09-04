import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;

public class GetPageFive {
static URL u1 ; 
static Enumeration e;
static String DateStr;
static Hashtable ht;
static Hashtable idxht;
static Hashtable ht_data = new Hashtable();
static Hashtable idxht_data = new Hashtable();;
static Hashtable snumht;
static boolean printflag = true;
static  Vector StockPool = new Vector();
static  Vector IndexPool = new Vector();
static  Vector tempStockPool = null;
static  Vector tempIndexPool = null;
static  Vector FuturePool = new Vector();
static  Hashtable  StockReportPool = new Hashtable();
static  Hashtable  IndexReportPool = new Hashtable();
static  Future_Report  futureReport = new Future_Report();
static  boolean  inRun = true;

 static public void main(String[] arg)throws Exception{
  DBConnection.debug = false;
  Ds000_Rec dsr = new Ds000_Rec();
  e = dsr.SelectBySQL("select aa.snum as snum, aa.capital as sname, aa.capital * bb.rp as capital from (select * from plast where date='20101116') as bb,(select b.* from ds000attr as a,ds000 as b where a.snum= b.snum and tcode='1' and capital < 1000000000) as aa where aa.snum=bb.snum order by capital desc limit 50 ;");
  String stockN = null;
  DateStr = arg[0];
  u1 = new URL("http://mis.twse.com.tw/stock_best5.html?stockId=1101");
  String s;
  ht = new Hashtable();
  idxht = new Hashtable();
  snumht = new Hashtable();
  Vector v = new Vector();
  while(e.hasMoreElements())
  {
     dsr = (Ds000_Rec)e.nextElement();
     v.add(dsr);   
     Stock_Ds0Rec sr0 = new  Stock_Ds0Rec(); 
     sr0.snum = dsr.snum;
     sr0.capital = Integer.parseInt(dsr.sname);
     sr0.value = Float.parseFloat(dsr.capital);  
     snumht.put(dsr.snum,sr0);

  }
  e = v.elements();   
  Thread  t1 =new Thread( new MyInput());
  t1.start();
  for(;;)
  {
     Date d = new Date();
     System.err.println("Start time: "+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds()));
     if(((d.getHours() == 13) && (d.getMinutes() > 35)&&(!printflag)) || (d.getHours() > 13))
     {
        break;
     }
     if(!inRun)
     {
        break;
     }
     printflag = false;
     tempStockPool = new Vector();
     while(e.hasMoreElements())
     {
        dsr = (Ds000_Rec)e.nextElement();
       	getData(dsr.snum,DateStr);
     }
     tempIndexPool = new Vector();
     getIndexData(DateStr);
     Future_Rec fr = getFutureData(DateStr);
     d = new Date();
     // -- add to Data Pool-----------------------------------------------------
     int ctime = d.getHours() * 10000+d.getMinutes()*100 + d.getSeconds();
     Enumeration e2 = tempStockPool.elements();
     while(e2.hasMoreElements())
     {
         Stock_Rec sr = (Stock_Rec)e2.nextElement();
         sr.setCTime(ctime);
         StockPool.add(sr);
     }
     setupReport(tempStockPool.elements());
     e2 = tempIndexPool.elements();
     while(e2.hasMoreElements())
     {
         Index_Rec ir = (Index_Rec)e2.nextElement();
         ir.setCTime(ctime);
         IndexPool.add(ir);
     }   
     setupReport(tempIndexPool.elements());
     if(fr!=null)
     {             
       fr.creat_time = ctime;
       FuturePool.add(fr);
       futureReport.add(fr);
       culReport1(fr);
     }
     //------------------------------------------------------------------------
     

     System.err.println("End time  :"+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds()));
     e = v.elements();
   }

   //Dump Data
   dumpData();
 }
 
  static void dumpData()
  {
      Enumeration e2 = StockPool.elements();
      while(e2.hasMoreElements())
      {
          System.out.println(e2.nextElement());
      }
      e2 = IndexPool.elements();
      while(e2.hasMoreElements())
      {
          System.out.println(e2.nextElement());
      }
      e2 = FuturePool.elements();
      while(e2.hasMoreElements())
      {
          System.out.println(e2.nextElement());
      }
  }
  
  static void dumpData(String Filename) throws IOException
  {
      PrintStream ps = new PrintStream(Filename);
      Enumeration e2 = StockPool.elements();
      while(e2.hasMoreElements())
      {
          ps.println(e2.nextElement());
      }
      e2 = IndexPool.elements();
      while(e2.hasMoreElements())
      {
          ps.println(e2.nextElement());
      }
      e2 = FuturePool.elements();
      while(e2.hasMoreElements())
      {
          ps.println(e2.nextElement());
      }
  }

  static boolean getData(String num,String datestr) {
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
 try{   
    URL u = new URL(u1,"/data/"+num+".csv?ID=111111111");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://mis.twse.com.tw/stock_best5.html?stockId="+num+"&Refresh=1"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "mis.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuffer sb = new StringBuffer();
    String s;
    while((s=br.readLine())!=null){
//    	 System.out.println(s);
           sb.append(datestr+"|");
             boolean inqflag = false;
             int j = 0;
             for(int i = 0; i < s.length(); i++)
             {
                char c = s.charAt(i);
                if( c == '"')
                {
                  inqflag = !inqflag;
                } else if(c!=':'){
                  if(inqflag)
                  {
                     if(c!=',')
                     {
                       if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }else{
                     if(c ==',')
                     {
                       if((j<3)||((j>7) && (j<31)))
                       sb.append('|');
                       j++;
                     }else{
                       if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }
                }
             }
             String toPrint = sb.toString();
             String prevPrint = (String)ht.get(num);
             if(!toPrint.equals(prevPrint))
             {
                Stock_Rec sr = new Stock_Rec(toPrint);   
                tempStockPool.add(sr);
                printflag = true;
                ht.put(num,toPrint);
                ht_data.put(num,sr);
             }
    }
  }catch(Exception e2)
  {
  }
    return true;
  }

  static boolean getIndexData(String datestr) {
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
 try{   
    URL u = new URL(u1,"/data/TSEIndex.csv");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://mis.twse.com.tw/market_index.html?market=1"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "mis.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String s;
    while((s=br.readLine())!=null){
         StringBuffer sb = new StringBuffer();
 //   	 System.err.println(s);
         String num ="";
         if(s.indexOf("\"0\",\"") >= 0)
         {
            continue;
         }
             boolean inqflag = false;
             int j = 0;
             for(int i = 0; i < s.length(); i++)
             {
                char c = s.charAt(i);
                if( c == '"')
                {
                  inqflag = !inqflag;
                } else if(c!=':'){
                  if(inqflag)
                  {
                     if(c!=',')
                     {
                   //    if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }else{
                     if(c ==',')
                     {
                  //     if((j<3)||((j>7) && (j<31)))
                       if (j == 0)
                       {
                          num = sb.toString();  
                       }
                       sb.append('|');
                       j++;
                     }else{
                 //      if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }
                }
             }
             String toPrint = sb.toString();
             String prevPrint = (String)idxht.get(num);
             if(!toPrint.equals(prevPrint))
             {
 //               System.out.print(num+":");
 //               System.out.print(datestr+"|");
//                System.out.println(toPrint);
                StringBuffer sb2 = new StringBuffer();
                sb2.append(datestr);
                sb2.append("|");
                sb2.append(toPrint);
                Index_Rec ir = new Index_Rec(sb2.toString());
                tempIndexPool.add(ir);
                idxht.put(num,toPrint);
                idxht_data.put(num,ir);
            }
    }
  }catch(Exception e2)
  {
      e2.printStackTrace();
  }
    return true;
  }
  static Future_Rec getFutureData(String datestr) {
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
 try{   
    URL u = new URL("http://info512.taifex.com.tw/Future/FusaQuote_Norl.aspx?d=0634634");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://info512.taifex.com.tw/Future/FusaQuote_Norl.aspx?d=0634634"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "info512.taifex.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuffer sb = new StringBuffer();
    String s;
    Base64 base64 = new Base64();
    while((s=br.readLine())!=null){
    	if(s.indexOf("id=\"__VIEWSTATE\" value=\"") > 0)
    	{
    	    s = s.substring(s.indexOf("id=\"__VIEWSTATE\" value=\"")+24,s.length()-4);
          byte[] decodedata = base64.decode(s.getBytes());
          s = new String(decodedata,"UTF-8");
//          int a = s.indexOf("<diffgr:diffgram");
          int a = s.indexOf("<Contract>TX120</Contract>");
//          System.err.println(a);
          int b = s.indexOf("<Contract>TX011</Contract>");
//          System.err.println(b);
          Future_Rec fr = new Future_Rec();
          s = s.substring(a,b);
          fr.price  =  Float.parseFloat(s.substring(s.indexOf("<Price>")+7,s.indexOf("</Price>")));        
          fr.change = Float.parseFloat(s.substring(s.indexOf("<Change>")+8,s.indexOf("</Change>")));        
          fr.ttlVol = Integer.parseInt(s.substring(s.indexOf("<TtlVol>")+8,s.indexOf("</TtlVol>")));        
          fr.bid1 = Float.parseFloat(s.substring(s.indexOf("<Bid1>")+6,s.indexOf("</Bid1>")));        
          fr.bidVol1 = Integer.parseInt(s.substring(s.indexOf("<BidVol1>")+9,s.indexOf("</BidVol1>")));        
          fr.ask1 = Float.parseFloat(s.substring(s.indexOf("<Ask1>")+6,s.indexOf("</Ask1>")));        
          fr.askVol1 = Integer.parseInt(s.substring(s.indexOf("<AskVol1>")+9,s.indexOf("</AskVol1>")));        
          fr.time = Integer.parseInt(s.substring(s.indexOf("<Time>")+6,s.indexOf("</Time>")));        
    	    return fr;
    	}
    }
  }catch(Exception e2)
  {
  }
    return null;
  }

  static void setupReport(Enumeration e)
  {
   	  while(e.hasMoreElements())
   	  {
   	  	  Try_Rec tr = (Try_Rec)e.nextElement();
          if(tr instanceof Stock_Rec)
          {
             Stock_Rec sr = (Stock_Rec)tr;
             Stock_Report srpt = (Stock_Report)StockReportPool.get(sr.snum);
             if(srpt == null) {
                srpt = new Stock_Report(sr.snum);
                StockReportPool.put(sr.snum, srpt);
             }
             srpt.add(sr);
          } else if(tr instanceof Index_Rec)
          {
             Index_Rec ir = (Index_Rec)tr;
             Index_Report irpt = (Index_Report)IndexReportPool.get(ir.num);
             if(irpt == null) {
                irpt = new Index_Report(ir.num);
                IndexReportPool.put(ir.num, irpt);
             }
             irpt.add(ir);
          
          } else if(tr instanceof Future_Rec)
          {
             Future_Rec fr = (Future_Rec)tr;
             if (futureReport == null)
             {
                futureReport = new Future_Report();
             }
             futureReport.add(fr);
          } 
   	  }      
  }

  static void culReport1(Future_Rec fr)
  {
  	  Enumeration e = ht_data.elements();
  	  float preVal = 0;
  	  float curVal = 0;
  	  while(e.hasMoreElements())
  	  {
  	     Stock_Rec sr = (Stock_Rec) e.nextElement();
  	     Stock_Ds0Rec  sr0  =  (Stock_Ds0Rec)snumht.get(sr.snum);
  	     preVal += sr0.value;
  	     curVal += sr.rp * sr0.capital;
  	  }
  	  Index_Rec ir= (Index_Rec)idxht_data.get("1");
  	  float preIdx = ir.value-ir.change;
  	  float preFuIdx = fr.price - fr.change;
  	  float stockChane = ((curVal-preVal)/preVal);
  	  System.err.println("Stock Diff:"+(stockChane*preIdx)+" ..<"+(stockChane*100)+"% ");
  	  System.err.println("Idx Diff:"+ ir.change +" ..<"+((ir.change)/preIdx*100)+"% ");
  	  System.err.println("preFuIdx Diff:"+ fr.change +" ..<"+((fr.change)/preFuIdx*100)+"% ");
  }

 static class MyInput implements Runnable {
         MyInput() {
         }
 
         public void run() {
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           while(inRun){
              try{
                String s=br.readLine();
                if(s.equals("End"))
                {
                   inRun = false;
                }
                if(s.equals("dump"))
                {
                   dumpData("dump.txt");
                }
              }catch(IOException ie)
              {
              }
           }
            System.err.println("End by User  :"+(new Date().getTime()));
         }

   }
   }