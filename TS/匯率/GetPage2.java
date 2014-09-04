import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage2 {
static URL u1 ; 
static PrintStream   aps = null;
static PrintStream   nps = null;


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");


DBConnection.debug = false;
    aps = new PrintStream(new FileOutputStream("aps.txt"));
    nps = new PrintStream(new FileOutputStream("nps.txt"));
    String ds = arg[0].substring(0,4) + "/" + arg[0].substring(4,6) + "/"+ arg[0].substring(6,8);
  //  Mmap_Rec mmr = new Mmap_Rec();
  //  Enumeration<Mmap_Rec> e = mmr.SelectBySQL("select cnum as mnum1, cname as mnum2 from cname ;");
 //   while(e.hasMoreElements())
    {
  //     mmr = e.nextElement();
  //     mmr.mnum2 = new String(mmr.mnum2.getBytes("ISO8859-1"),"MS950");
 //      System.out.println(mmr.mnum2 + "  "+ ds);
       getData(arg[0].substring(0,4), arg[0].substring(4,6),arg[0].substring(6,8));
    }
 }
 
  static boolean getData(String y,String m, String d) throws Exception{
    int count = 0;
//    String qData="dollar="+URLEncoder.encode(noname)+"&begin_day="+URLEncoder.encode(sdate)+"&end_day="+URLEncoder.encode(edate);
 //   System.out.println(qData);
    String qData ="__VIEWSTATE=%2FwEPDwUKMTYzMjkyNTMyN2QYAwUeX19Db250cm9sc1JlcXVpcmVQb3N0QmFja0tleV9fFgkFCHZpZXcxXzk5BQd2aWV3MV82BQd2aWV3MV83BQthZnRlck9yTm90MAULYWZ0ZXJPck5vdDEFB2VudGl0eTAFB2VudGl0eTEFBlJhZGlvMQUGUmFkaW8yBQptdWx0aVRhYnMyDw9kAgFkBQltdWx0aVRhYnMPD2QCAWTxwwBRUhg8RDpbUvDvSfu%2Ffmh6Mw%3D%3D&view=1&lang=zh-TW&term=99&year=2013&month=10&day=01&afterOrNot=0&whom1=USD&whom2=&entity=1&lang=zh-TW&term2=1&year2=2013&month2=10&day2=22&Button3=%E6%9F%A5%E8%A9%A2&__EVENTVALIDATION=%2FwEWFAKcnb0HAruTy7MGAr%2BT8%2BkHAoyFzvoJAtaywuMCAp%2FkvpsNAtuCm8QLAsvcuuMKAtbcluMKAtXcluMKAou2zcIBApS2zcIBAtuaiJwEAsSaiJwEAoznisYGAqDCk%2FENAqvHvb4NArTHvb4NAtaUz5sCAs%2BGurEPDkM3TuUtBcj6lusmfXDSVh%2BhJ1Y%3D";
    URL u = new URL("http://rate.bot.com.tw/Pages/UIP004/UIP004INQ1.aspx?lang=zh-TW");
    URLConnection conn = u.openConnection(); 
    conn.setDoOutput(true);
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://www.cosmosbank.com.tw/rate_jsp/rate-7.jsp"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "www.cosmosbank.com.tw"); 
    conn.setRequestProperty("Content-Length", String.valueOf(qData.length())); 
    conn.setRequestProperty("Cache-Control", "no-cache"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setRequestProperty("Cookie", "BankOfTaiwanCookie=386181312.20480.0000"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    OutputStream os = conn.getOutputStream();
    os.write(qData.getBytes());
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    
    String s = null;
    String dateS = y+m+d;
    String mNs = null;
    String mAs = null;      
    float usdrate = 0;
    while((s=br.readLine())!=null){
 //   	System.out.println(s);
    	if(s.indexOf("decimal")>=0)
    	{
    		 String cmn = s.substring(s.indexOf("(")+1,s.indexOf(")"));    
    		 //int endidx = s.indexOf("</td><td class=\"link\"");
    		 s =s.substring(s.indexOf("\"decimal\">")+10);
    		 String value1 = s.substring(0,s.indexOf("</td>"));
    		 s =s.substring(s.indexOf("\"decimal\">")+10);
    		 String value2 = s.substring(0,s.indexOf("</td>"));
    		 s =s.substring(s.indexOf("\"decimal\">")+10);
    		 String value3 = s.substring(0,s.indexOf("</td>"));
    		 s =s.substring(s.indexOf("\"decimal\">")+10);
    		 String value4 = s.substring(0,s.indexOf("</td>"));
//    		 String value = s.substring(s.lastIndexOf("\"decimal\">")+10,s.indexOf("</td><td class=\"link\""));
//    		 System.out.println(s);
         if(value4.equals("-"))
         {
    		   float cv = Float.parseFloat(value2);
    		   if(cmn.equals("USD")) 
    		   { 
    		     usdrate = cv;
             aps.println(dateS+"|"+cmn+"|1.0|");
           }else
           {
             aps.println(dateS+"|"+cmn+"|"+(cv/usdrate)+"|");
           }
           nps.println(dateS+"|"+cmn+"|"+value2+"|");
    	   } else
    	   {
    		   float cv = Float.parseFloat(value4);
    		   if(cmn.equals("USD")) 
    		   { 
    		     usdrate = cv;
             aps.println(dateS+"|"+cmn+"|1.0|");
           }else
           {
             aps.println(dateS+"|"+cmn+"|"+(cv/usdrate)+"|");
           }
           nps.println(dateS+"|"+cmn+"|"+value4+"|");
   	   }
    	   }
    }
    return true;
  }
//  static boolean getData(String monum,String noname,String sdate, String edate) throws Exception{
//    int count = 0;
//    String qData="dollar="+URLEncoder.encode(noname)+"&begin_day="+URLEncoder.encode(sdate)+"&end_day="+URLEncoder.encode(edate);
// //   System.out.println(qData);
//    URL u = new URL("http://www.cosmosbank.com.tw/wwwuser/owa/PK_FDrate.AnalyRate");
//    URLConnection conn = u.openConnection(); 
//    conn.setDoOutput(true);
//    conn.setRequestProperty("Accept", "*/*"); 
//    conn.setRequestProperty("Referer", "http://www.cosmosbank.com.tw/rate_jsp/rate-7.jsp"); 
//    conn.setRequestProperty("Accept-Language", "zh-tw"); 
//    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
//    conn.setRequestProperty("Host", "www.cosmosbank.com.tw"); 
//    conn.setRequestProperty("Content-Length", String.valueOf(qData.length())); 
//    conn.setRequestProperty("Cache-Control", "no-cache"); 
//    conn.setRequestProperty("Connection", "Keep-Alive"); 
//    conn.setRequestProperty("Cookie", "JSESSIONID=f886d77285234acfa42fecc88323ec17.mQXNpkXNrQjwcxaKa38UePWLbN8IahmIaxiO-xqIax1yn64Iah4Kb3yMmk5yc64I-huKa30xpRjOnl8ImQXNpkXNrQjw-AbJqQPJqQ9vpAGImQXH-BfR8NuSbh4NaMbCpQPz8QvJpkixn6jAmljGr5XDqQLvpAe_"); 
//    conn.setReadTimeout(19000);
//    conn.setConnectTimeout(19000);
//    OutputStream os = conn.getOutputStream();
//    os.write(qData.getBytes());
//    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//    
//    String s = null;
//    String dateS = null;
//    String mNs = null;
//    String mAs = null;
//    while((s=br.readLine())!=null){
//    	   if(s.indexOf("<TD COLSPAN=\"NOWRAP\">")>=0)
//    	   {
//    	       s = s.substring(s.indexOf('>')+1);
//    	       s = s.substring(0,s.indexOf('<'));
//        	   s=s.replaceAll("/","");
//        	   dateS = s;
////        	   System.out.println(s);
//    	   } else if(s.indexOf("<TD ALIGN=\"Right\" COLSPAN=\"NOWRAP\">")>=0)
//    	   {
//    	       s = s.substring(s.indexOf('>')+1);
//    	       s = s.substring(0,s.indexOf('<'));
//    	       s = s.trim();
//    	       if (mNs == null)
//    	       {
//    	           mNs = s;
//    	       } else if(mAs == null)
//    	       {
//    	           mAs = s;
//                 aps.println(dateS+"|"+monum+"|"+mAs+"|");
//                 nps.println(dateS+"|"+monum+"|"+mNs+"|");
//                 mNs = null;
//                 mAs = null;
//    	       }
////        	   System.out.println(s);
//    	   }
//    }
//    return true;
//  }
}
