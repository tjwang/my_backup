import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage3 {
static URL u1 ; 
static PrintStream   aps = null;
static PrintStream   nps = null;


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");


DBConnection.debug = false;
    aps = new PrintStream(new FileOutputStream("aps.txt"));
    nps = new PrintStream(new FileOutputStream("nps.txt"));
    Mmap_Rec mmr = new Mmap_Rec();
/*    Enumeration<Mmap_Rec> e = mmr.SelectBySQL("select cnum as mnum1, cname as mnum2 from cname ;");
    while(e.hasMoreElements())
    {
       mmr = e.nextElement();
       mmr.mnum2 = new String(mmr.mnum2.getBytes("ISO8859-1"),"MS950");
       System.out.println(mmr.mnum2);
    }*/
    Date d = new Date(2011-1900,9,14);
    Date d2 = new Date();
    while(d.getTime() < d2.getTime())
    {
        String ds = String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());
        getData(ds.substring(0,4),ds.substring(4,6),ds.substring(6,8));
        d = new Date(d.getTime()+86400000);
    }
//    getData("","","","");
 }
 

  static boolean getData(String y,String m, String d) throws Exception{
    int count = 0;
//    String qData="dollar="+URLEncoder.encode(noname)+"&begin_day="+URLEncoder.encode(sdate)+"&end_day="+URLEncoder.encode(edate);
 //   System.out.println(qData);
    String qData ="__VIEWSTATE=%2FwEPDwUJNTMzOTQ0MzAxZBgDBR5fX0NvbnRyb2xzUmVxdWlyZVBvc3RCYWNrS2V5X18WBwUIdmlldzFfOTkFB3ZpZXcxXzYFB3ZpZXcxXzcFC2FmdGVyT3JOb3QwBQthZnRlck9yTm90MQUHZW50aXR5MAUHZW50aXR5MQUKbXVsdGlUYWJzMg8PZAIBZAUJbXVsdGlUYWJzDw9kAgFkJRi0GkRMGvkRkmu59%2Bk%2BO7V1EVY%3D&view=1&lang=zh-TW&term=99&year="+y+"&month="+m+"&day="+d+"&afterOrNot=0&whom1=USD&whom2=&entity=1&lang=zh-TW&term2=0&year2="+y+"&month2="+m+"&day2="+d+"&Button3=%E6%9F%A5%E8%A9%A2&__EVENTVALIDATION=%2FwEWEgLQ7qmFDgK7k8uzBgK%2Fk%2FPpBwKMhc76CQLWssLjAgKf5L6bDQLbgpvECwLL3LrjCgLW3JbjCgLV3JbjCgKLts3CAQKUts3CAQLbmoicBALEmoicBAKM54rGBgKgwpPxDQLWlM%2BbAgLPhrqxD%2FTTA646EPrG6XOjcvdXIPPxEY4M";
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
    conn.setRequestProperty("Cookie", "BankOfTaiwanCookie=369404096.20480.0000"); 
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
//                 mNs = null;
//                 mAs = null;
//    	       }
////        	   System.out.println(s);
    	   }
    }
    return true;
  }
}
