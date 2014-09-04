import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage2 {
static URL u1 ; 
static String cookieValue;

static private void getCookie()throws IOException{
  URL url = new URL( "http://www.yahoo.com.tw/" ); 
  URLConnection conn = url.openConnection(); 
 conn.setRequestProperty("Accept", "*/*"); 
 conn.setRequestProperty("Referer", "http://tw.yahoo.com/"); 
 conn.setRequestProperty("Accept-Language", "zh-tw"); 
 //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
 conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
 conn.setRequestProperty("Host", "tw.stock.yahoo.com"); 
 conn.setRequestProperty("Connection", "Keep-Alive"); 
  conn.connect(); 
  Map<String, List<String>> headers = conn.getHeaderFields(); 
  List<String> values = headers.get("Set-Cookie"); 
  
  //System.out.println(headers);
  cookieValue = null; 
  for (Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
     String v = iter.next(); 
     if (cookieValue == null)
         cookieValue = v;
     else
         cookieValue = cookieValue + ";" + v;
 } 
 //System.out.println(cookieValue);

}

 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
//  BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
//  Ds000_Rec dsr = new Ds000_Rec();
//  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr order by snum;");
//  String stockN = null;
//  getData("2454",arg[0].substring(0,2),arg[0].substring(2,4));
    Date d = new Date(105,0,1);
    Date d2 = new Date();
    long d2long = d2.getTime();
    while(d.getTime() < d2long){
      String ds = String.valueOf((1900+d.getYear())*10000+(d.getMonth()+1)*100+d.getDate());
      System.err.println(ds);
      for(int i = 0 ;i < 20;i++){
        try{
     	     while(!getData(ds)){
     	       i++;
     	       if(i > 20 ) break;
     	     }
   	       i = 501;
        }catch(Exception e3){
          Thread.currentThread().sleep(i*1000);
          if(i>15)
          {
             e3.printStackTrace(System.err);
             (new DataInputStream(System.in)).readLine();
          }
        }
      } 
      d = new Date(d.getTime()+86400000);
    } 

  }
 

  static boolean getData(String Dstr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL("http://www.twse.com.tw/ch/trading/fund/BFI82U/BFI82U_print.php?begin_date="+Dstr+"&end_date="+Dstr+"&report_type=day&language=ch&save=csv");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://www.twse.com.tw/"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "www.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuffer buy = new StringBuffer();
    StringBuffer sell = new StringBuffer();
    String s;
    String sBuy ="0";
    String sSell="0";
    String sTotal = "0" ;
    StringBuffer sb = new StringBuffer();
    while((s=br.readLine())!=null){
//         System.out.println(s);
         if(s.indexOf("自營商") >= 0)
         {
            System.out.print(Dstr+"|A|");
            StringTokenizer st = new StringTokenizer(s,"\"");
            while(st.hasMoreTokens())
            {
               String token = st.nextToken();
               if(((token.charAt(0) >= 0x30) && (token.charAt(0) <= 0x39)) || (token.charAt(0)=='-'))
               {
                  System.out.print(token.replaceAll(",","")+"|");
               }
            }
               System.out.println();
         }
         if(s.indexOf("投信") >= 0)
         {
           System.out.print(Dstr+"|B|");
            StringTokenizer st = new StringTokenizer(s,"\"");
            while(st.hasMoreTokens())
            {
               String token = st.nextToken();
               if(((token.charAt(0) >= 0x30) && (token.charAt(0) <= 0x39)) || (token.charAt(0)=='-'))
               {
                  System.out.print(token.replaceAll(",","")+"|");
               }
            }
               System.out.println();
         }
         if(s.indexOf("外資") >= 0)
         {
           System.out.print(Dstr+"|F|");
            StringTokenizer st = new StringTokenizer(s,"\"");
            while(st.hasMoreTokens())
            {
               String token = st.nextToken();
               if(((token.charAt(0) >= 0x30) && (token.charAt(0) <= 0x39)) || (token.charAt(0)=='-'))
               {
                  System.out.print(token.replaceAll(",","")+"|");
               }
            }
               System.out.println();
         }
      
    }
 //   sTotal =sb.toString();
//    System.out.println(num+"|2010"+Mstr+Dstr+"|"+sTime+"|"+sBuy+"|"+sSell+"|"+sRp+"|"+sDiff+"|"+sRa+"|"+sTotal+"|"+sPclose+"|"+
  //                     sOpen+"|"+sHigh+"|"+sLow+"|");
    return true;
  }
}
