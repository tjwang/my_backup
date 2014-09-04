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
static boolean printflag = true;
 static public void main(String[] arg)throws Exception{
 DBConnection.debug = false;
  Ds000_Rec dsr = new Ds000_Rec();
  e = dsr.SelectBySQL("select aa.snum as snum, aa.sname as sname, aa.capital * bb.rp as capital from (select * from plast where date='20101115') as bb,(select b.* from ds000attr as a,ds000 as b where a.snum= b.snum and tcode='1' and capital < 1000000000) as aa where aa.snum=bb.snum order by capital desc limit 50 ;");
  String stockN = null;
  DateStr = arg[0];
  u1 = new URL("http://mis.twse.com.tw/stock_best5.html?stockId=1101");
  String s;
  ht = new Hashtable();
  idxht = new Hashtable();
  Vector v = new Vector();
  while(e.hasMoreElements())
    v.add(e.nextElement());
  e = v.elements();   
  for(;;)
  {
     Date d = new Date();
     System.err.println("Start time: "+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds()));
     if(((d.getHours() == 13) && (d.getMinutes() > 30)&&(!printflag)) || (d.getHours() > 13))
     {
        return;
     }
     printflag = false;
     while(e.hasMoreElements())
     {
        dsr = (Ds000_Rec)e.nextElement();
       	getData(dsr.snum,DateStr);
     }
     getIndexData(DateStr);
     getFutureData(DateStr);
     d = new Date();
     System.err.println("End time  :"+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds()));
     e = v.elements();
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
                System.out.println(toPrint);
                printflag = true;
                ht.put(num,toPrint);
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
//    	 System.out.println(s);
         String num ="";
         if(s.indexOf("\"0\",\"") >= 0)
         {
            continue;
         }
//           sb.append(datestr+"|");
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
                System.out.print(num+":");
                System.out.print(datestr+"|");
                System.out.println(toPrint);
                idxht.put(num,toPrint);
            }
    }
  }catch(Exception e2)
  {
  }
    return true;
  }
  static boolean getFutureData(String datestr) {
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
          int a = s.indexOf("<Contract>TX110</Contract>");
//          System.err.println(a);
//          int b = s.indexOf("</diffgr:diffgram>");
          int b = s.indexOf("<Contract>TX120</Contract>");
//          System.err.println(b);
//          s = s.substring(a,b+19);
          s = s.substring(a,b);
//    	    System.out.println(s);
          System.out.println(s.substring(s.indexOf("<Price>"),s.indexOf("</Price>")));        
          System.out.println(s.substring(s.indexOf("<Change>"),s.indexOf("</Change>")));        
          System.out.println(s.substring(s.indexOf("<TtlVol>"),s.indexOf("</TtlVol>")));        
          System.out.println(s.substring(s.indexOf("<Bid1>"),s.indexOf("</Bid1>")));        
          System.out.println(s.substring(s.indexOf("<BidVol1>"),s.indexOf("</BidVol1>")));        
          System.out.println(s.substring(s.indexOf("<Ask1>"),s.indexOf("</Ask1>")));        
          System.out.println(s.substring(s.indexOf("<AskVol1>"),s.indexOf("</AskVol1>")));        
          System.out.println(s.substring(s.indexOf("<Time>"),s.indexOf("</Time>")));        
//          FileOutputStream fout = new  FileOutputStream("o.txt");  
//          fout.write(decodedata);
    	    return true;
    	}
    }
  }catch(Exception e2)
  {
  }
    return true;
  }
}