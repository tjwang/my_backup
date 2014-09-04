import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.apache.commons.codec.binary.Base64;
public class FPriceRec {
    float price;
    float change;
    int ttlVol;
    float bid;
    int bidVol;
    float ask;
    int askVol;
    int _time;
   public FPriceRec(int month) throws Exception
   {
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
       String FirstMon =null;
       String SecondMon=null; 
       if(month >= 10)
       {
          FirstMon = "TX"+month+"3";
       } else
       {
          FirstMon = "TX0"+month+"3";
       }
       month++;
       if(month >= 10)
       {
          SecondMon = "TX"+month+"3";
       } else
       {
          SecondMon = "TX0"+month+"3";
       }
       Base64 base64 = new Base64();
       while((s=br.readLine())!=null){
     	    if(s.indexOf("id=\"__VIEWSTATE\" value=\"") > 0)
    	    {
    	       s = s.substring(s.indexOf("id=\"__VIEWSTATE\" value=\"")+24,s.length()-4);
             byte[] decodedata = base64.decode(s.getBytes());
             s = new String(decodedata,"UTF-8");
             int a = s.indexOf("<Contract>"+FirstMon+"</Contract>");
             int b = s.indexOf("<Contract>"+SecondMon+"</Contract>");
             s = s.substring(a,b);
             price = Float.parseFloat(s.substring(s.indexOf("<Price>"),s.indexOf("</Price>")).substring(7));        
             change = Float.parseFloat(s.substring(s.indexOf("<Change>"),s.indexOf("</Change>")).substring(8));        
             ttlVol = Integer.parseInt(s.substring(s.indexOf("<TtlVol>"),s.indexOf("</TtlVol>")).substring(8));        
             bid = Float.parseFloat(s.substring(s.indexOf("<Bid1>"),s.indexOf("</Bid1>")).substring(6));        
             bidVol =Integer.parseInt(s.substring(s.indexOf("<BidVol1>"),s.indexOf("</BidVol1>")).substring(9));        
             ask =Float.parseFloat(s.substring(s.indexOf("<Ask1>"),s.indexOf("</Ask1>")).substring(6));        
             askVol = Integer.parseInt(s.substring(s.indexOf("<AskVol1>"),s.indexOf("</AskVol1>")).substring(9));        
             _time = Integer.parseInt(s.substring(s.indexOf("<Time>"),s.indexOf("</Time>")).substring(6));        
             return ;
          }
       }
       throw new Exception("error in got future price");
   }
   public void dump()
   {
    	System.out.println("price = " +price);
    	System.out.println("change = "+change);
    	System.out.println("ttlVol = "+ttlVol);
      System.out.println("bid = "+bid);
      System.out.println("bidVol = "+bidVol);
      System.out.println("ask = "+ask);
      System.out.println("askVol = "+askVol); 
      System.out.println("_time = "+_time); 
   }
}