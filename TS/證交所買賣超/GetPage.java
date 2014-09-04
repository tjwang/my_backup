import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.security.Security;


public class GetPage {
static String  ub = "http://www.twse.com.tw/ch/trading/fund/TWT43U/TWT43U_print.php?edition=ch&filename=genpage/A";
static String  ub2 = ".dat&type=csv&title=98年9月7日自營商買賣超彙總表(含避險)"; 
static String  ua = "http://www.twse.com.tw/ch/trading/fund/TWT44U/TWT44U_print.php?edition=ch&filename=genpage/A";
static String  ua2= ".dat&type=csv&title=98年9月7日投信買賣超彙總表(含普通股、鉅額、零股)(＊表該證券今日有鉅額交易)"; 
static String  uf = "http://www.twse.com.tw/ch/trading/fund/TWT38U/TWT38U_print.php?edition=ch&filename=genpage/A";
static String  uf2 = ".dat&type=csv&title=98年9月7日外資及陸資買賣超彙總表（含普通股、鉅額、零股）（＊表該股票今日有鉅額交易）"; 

 static public void main(String[] arg)throws Exception{
  DBConnection.debug = false;
   getData(arg[0],ub+arg[0]+ub2,"B");
   getData2(arg[0],ua+arg[0]+ua2,"A");
   getData2(arg[0],uf+arg[0]+uf2,"F");
 }
 

  static boolean getData(String datestr,String adrurl,String type) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL(adrurl);
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
   // conn.setRequestProperty("Referer", "http://tw.stock.yahoo.com/q/q?s="+num); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
//    conn.setRequestProperty("Host", "pchome.syspower.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    //conn.setRequestProperty("Cookie", cookieValue); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"MS950"));
    StringBuffer buy = new StringBuffer();
    StringBuffer sell = new StringBuffer();
    String s;
    boolean linein = false ;
    while((s=br.readLine())!=null){
    	 int di =0;
 //   	 System.out.println(s);
      if((linein) && (s.charAt(0)!='=') && (s.charAt(0)!='0')&& (s.indexOf('.') < 0)) 
      {
        System.out.print(datestr+","+type+",");
        boolean flag = true;
        for(int i=0;i<s.length();i++)
        {
          if(s.charAt(i)=='"') 
          {
          	flag = !flag; 
            continue;
          }
          if((flag)||(s.charAt(i)!=','))   System.out.print(s.charAt(i));
        }
        System.out.print("\n");
      }
      if(s.indexOf("證券代號") >= 0)
      {
      	linein = true;
      }
    }	 
    return true;
  }
  static boolean getData2(String datestr,String adrurl,String type) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL(adrurl);
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
   // conn.setRequestProperty("Referer", "http://tw.stock.yahoo.com/q/q?s="+num); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
//    conn.setRequestProperty("Host", "pchome.syspower.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    //conn.setRequestProperty("Cookie", cookieValue); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"MS950"));
    StringBuffer buy = new StringBuffer();
    StringBuffer sell = new StringBuffer();
    String s;
    boolean linein = false ;
    while((s=br.readLine())!=null){
    	 int di =0;
//    	 System.out.println(s);
      if(s.indexOf(',') >= 0)
        s=s.substring(s.indexOf(',')+1);
      if((linein) && (s.charAt(0)!='=') && (s.charAt(0)!='0') && (s.indexOf('.') < 0)) 
      {
        System.out.print(datestr+","+type+",");
        boolean flag = true;
        for(int i=0;i<s.length();i++)
        {
          if(s.charAt(i)=='"') 
          {
          	flag = !flag; 
            continue;
          }
          if((flag)||(s.charAt(i)!=','))   System.out.print(s.charAt(i));
        }
        System.out.print("\n");
      }
      if(s.indexOf("證券代號") >= 0)
      {
      	linein = true;
      }
    }	 
    return true;
  }

}
