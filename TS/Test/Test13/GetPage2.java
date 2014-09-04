import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;

public class GetPage2 {
static URL u1 ; 
static Enumeration e;
static String DateStr;
static Hashtable ht;
static boolean printflag = true;
 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
//  BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
 for(int i =0; i < 1; i++)
 {
    Date d = new Date();
    System.out.println("<?xml version=\"1.0\" encoding=\"Big5\"?>");
    System.err.println("Start time: "+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds()));
    getData(DateStr);
 }
}


 

  static boolean getData(String datestr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
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
    	    System.out.println(s);
          FileOutputStream fout = new  FileOutputStream("o.txt");  
          fout.write(decodedata);
    	    return true;
    	}
    }
    return true;
  }
}