import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage5 {
static URL u1 ; 
static String cookieValue;


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
   DBConnection.debug = false;
    int di = Integer.parseInt(arg[0]);
    getData(di/100-1911, di%100,1);
    getData(di/100-1911, di%100,2);

  }
 

  static boolean getData(int y, int m,int wt) throws Exception{
                    
    URL u = null;
    if(wt == 1)
    {
//       u = new URL("http://mopsov.tse.com.tw/t21/sii/t21sc03_"+y+"_"+m+".html");
       u = new URL("http://mops.twse.com.tw/t21/sii/t21sc03_"+y+"_"+m+"_0.html");
    } else
    {
//       u = new URL("http://mopsov.tse.com.tw/t21/otc/t21sc03_"+y+"_"+m+".html");
       u = new URL("http://mops.twse.com.tw/t21/otc/t21sc03_"+y+"_"+m+"_0.html");
    }
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://www.twse.com.tw/"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "www.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     
    String s = null;
    int state = -1;
    boolean  startflag = false;
    Hashtable ht =new Hashtable();
    while((s=br.readLine())!=null)
    {
//         System.out.println(s);
        while(s.indexOf("<tr align=right>") >= 0)
        {
            s = s.substring(s.indexOf("<tr align=right>")+16);
            String data = s.substring(0,s.indexOf("</tr>"));
            if(data.indexOf("¦X­p") < 0)
            {
            	StringBuffer sb = new StringBuffer();
              sb.append((y+1911)+"|"+m);
              String snum=null;
              for(int i = 0; i<5 ; i++)
              {
                 data = data.substring(data.indexOf(">")+1);
                 String value = data.substring(0,data.indexOf("</td>"));
                 value=value.replaceAll(",","");
                 value=value.replaceAll("&nbsp;","");
                 value = value.trim();
                 if(i==0)
                 {
                     snum=value;
                 }
                 if(i!=1)
                 {
                    sb.append("|"+value);
                 }
                 data = data.substring(data.indexOf("</td>")+5);
              }
              sb.append("|");
              ht.put(snum,sb.toString());
           }
        } 
    }
    Enumeration e = ht.elements();
    while(e.hasMoreElements())
    {
       System.out.println(e.nextElement());
    }
    return true;
  }
}
