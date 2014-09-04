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
  String filename ="OptionsDaily_"+ dateStr.substring(0,4)+"_"+dateStr.substring(4,6)+"_"+dateStr.substring(6,8)+".zip";
  String filelink = "http://www.taifex.com.tw/OptionsDailyDownload/"+filename;
  System.out.println(filelink);
  u1 = new URL(filelink);
  URLConnection conn = u1.openConnection(); 
  setCookie(conn);
  FileOutputStream os = new FileOutputStream(filename);
  
  InputStream is = conn.getInputStream();
  byte[] buf= new byte[4096];
  int len = 0;
  while((len=is.read(buf)) > 0)
  { 
  	os.write(buf,0,len);
  }
  os.close();

  }

}
