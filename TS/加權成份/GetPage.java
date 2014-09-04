import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage {
static URL u1 ; 
static String cookieValue;

static private void setCookie(URLConnection conn)throws IOException{
 conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/x-ms-application, application/x-ms-xbap, application/vnd.ms-xpsdocument, application/xaml+xml, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*"); 
 conn.setRequestProperty("Referer", "http://www.taifex.com.tw/chinese/3/7_12_3.asp"); 
 conn.setRequestProperty("Accept-Language", "zh-tw"); 
 //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
 conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; GTB6.5; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; InfoPath.1)"); 
 conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
 conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
 conn.setRequestProperty("Host", "www.taifex.com.tw"); 
 conn.setRequestProperty("Connection", "Keep-Alive"); 
 conn.setRequestProperty("Cache-Control", "no-cache"); 
 conn.setRequestProperty("Cookie", "ASPSESSIONIDAACCRRDD=HFAIODIDICFEFPIOCLHGKMDP; BIGipServerPOOL_PORTAL=33689772.20480.0000"); 
 //System.out.println(cookieValue);

}

 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
   String dateStr = arg[0];
   int dateInt = Integer.parseInt(dateStr);
   DBConnection.debug = false;
   u1 = new URL("http://www.taifex.com.tw/chinese/2/9_7_1.asp");
   URLConnection conn = u1.openConnection(); 
   setCookie(conn);
   BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
   String s;
   int state = 0;
   StringBuffer sb = null;   
   while((s=br.readLine())!=null)
   { 
	     while(s.indexOf("<TD align=right bgcolor=\"#FFFFFF\">") >= 0) 
       {
          // System.out.println(s);
          s=s.substring(s.indexOf("<TD align=right bgcolor=\"#FFFFFF\">") + 34);
          int eIdx = s.indexOf("</td>");
          String a = s.substring(0,eIdx);
          s=s.substring(eIdx+5);
          s=s.substring(s.indexOf("<TD align=right bgcolor=\"#FFFFFF\">") + 34);
          eIdx = s.indexOf("</td>");
          String b = s.substring(0,eIdx);
          s=s.substring(eIdx+5);
          System.out.println(arg[0]+"|"+b.trim()+"|");	
       }
    }
 }


}
