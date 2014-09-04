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

public class Test6Five {
 static Hashtable ht ;
static public void main(String[] arg)throws Exception
{

      java.util.Date d = new java.util.Date();      
      System.out.println(d.getTime());
      getFutureData(arg[0]);
      d = new java.util.Date();      
      System.out.println(d.getTime());
  /*    
       DBConnection dbc = new DBConnection();
       Connection dc = dbc.getConnection();
       Statement dbstmt= dc.createStatement();
       dbstmt.executeUpdate("delete from pfiveraw ");
       dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSDataPath()+"\\\\"+
                       arg[0]+"\\\\fiveRaw."+ arg[0]+".txt\"   into table pfiveraw FIELDS  TERMINATED by '|' ;");
      Test5KD     dK  = new Test5KD(arg[0],0);
      KLine dkl = dK.culKLine();
      int ll = dkl.length();
      for(int j = 0; j < ll-22;j++)
      {
          float cop_count = 0;
          float com_count = 0;
          float cop_diff = 0;
          float com_diff = 0;
          for(int i=j;i<j+20;i++)
          {   
             KValue v1 = (KValue)dkl.valueAt(i);
             KValue v2 = (KValue)dkl.valueAt(i+1);
             if(v2.getOpen() > v1.getClose())
             {
              	  cop_count++;
              	  cop_diff += (v2.getOpen() - v1.getClose());
             } else
             {   
                	com_count++;
              	  com_diff += (v2.getOpen() - v1.getClose());
             }
          }
          System.out.println(""+dkl.valueAt(j).getDateValue()+" cop_count:"+cop_count+"("+cop_diff+") com_count:"+com_count+"("+com_diff+") P:"+(cop_count/(cop_count+com_count))+"("+(cop_diff+com_diff)+")");
      }
      */
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
  //        FileOutputStream fout = new  FileOutputStream("o.txt");  
 //         fout.write(decodedata);

          s = new String(decodedata,"UTF-8");

//          int a = s.indexOf("<diffgr:diffgram");
          int a = s.indexOf("<Contract>TX023</Contract>");
//          System.err.println(a);
//          int b = s.indexOf("</diffgr:diffgram>");
          int b = s.indexOf("<Contract>TX033</Contract>");
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