import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.security.Security;


public class TestPage {

 static public void main(String[] arg)throws Exception{

    getData("2454");

  }
 

  static boolean getData(String num) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL("http://pchome.syspower.com.tw/stock/sto0/ock3/sid"+num+".html");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
   // conn.setRequestProperty("Referer", "http://tw.stock.yahoo.com/q/q?s="+num); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "pchome.syspower.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    conn.setRequestProperty("Cookie", GetPage.cookieValue); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
    StringBuffer buy = new StringBuffer();
    StringBuffer sell = new StringBuffer();
    String s;
    boolean linein = false ;
    while((s=br.readLine())!=null){
    	System.out.println(s);
    }	 
    return true;
  }

}
