import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage2 {
static URL u1 ; 
static String cookieValue="tw_StockRSS=d=s2Wq_arbPKwlm7Lh5eQg.EOFZ9FRKoRyM1TLEP.bEwBL0xgmDWF.fI56LGGhr9KwZgLfBBIX06vO0gLySBHOm73hRlPjFWcyVwNf17tPQ5pVGLD8s5bU7lS_Xf_6UjXCCCH56Yy3.3vNKBUG2e.V.uymteJk9rm_BhHklc38MKyLqZXDl4AWoELJynx9N4MNBC.ZpTzUWduGFvPb2M7cOy2arxRRHVmu96EFfz.LfZP9yB_NtD7.x323B9YRNWxZnDFn3pbEQvrs2yTqKDVKSdC6wp8ynH782kuvlYUdevpTovw-&v=1; B=7l4ti6p5tj02k&b=4&d=vgCXGrlpYEN6Ld7gi0bomY72OpQZ1GnHCQY-&s=2n&i=PbRWsoma5JVNzF3s_JKT; Q=q1=AACAAAAAAAAAAA--&q2=S91EOg--; F=a=mddKGaoMvTMn0MGwMd0DSJPiDu79HUVRHYH_BXB0G5s8p6_w52yOXCLEwQLUGnIlMoRl7veg11yd.4YNAZ5Y5FTGGA--&b=DWuZ; C=mg=1; BA=ba=5550&ip=118.169.41.105&t=1272910646; YLS=v=1&p=0&n=0; Y=v=1&n=bl5lq4p4jne6k&p=";
private static void trustAllHttpsCertificates() throws Exception {  
    javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];  
    javax.net.ssl.TrustManager tm = new miTM();
    trustAllCerts[0] = tm;  
    javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext  
            .getInstance("SSL");  
    sc.init(null, trustAllCerts, null);  
    javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc  
            .getSocketFactory());  
}  
   static class miTM implements javax.net.ssl.TrustManager,  
            javax.net.ssl.X509TrustManager {  
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
            return null;  
        }  
  
        public boolean isServerTrusted(  
                java.security.cert.X509Certificate[] certs) {  
            return true;  
        }  
  
        public boolean isClientTrusted(  
                java.security.cert.X509Certificate[] certs) {  
            return true;  
        }  
  
        public void checkServerTrusted(  
                java.security.cert.X509Certificate[] certs, String authType)  
                throws java.security.cert.CertificateException {  
            return;  
        }  
  
        public void checkClientTrusted(  
                java.security.cert.X509Certificate[] certs, String authType)  
                throws java.security.cert.CertificateException {  
            return;  
        }  
    }  

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
  Ds000_Rec dsr = new Ds000_Rec();
  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr order by snum;");
  String stockN = null;
  
  //getCookie();
  trustAllHttpsCertificates();
  u1 = new URL("https://tw.stock.yahoo.com");
  URLConnection conn = u1.openConnection(); 
  conn.setRequestProperty("Cookie", cookieValue);
  BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
  String s;
  while((s=br.readLine())!=null);
//  System.out.println(cookieValue);

//   getData("1218",arg[0]);
  while(e.hasMoreElements()){
      dsr = (Ds000_Rec)e.nextElement();
      for(int i = 0 ;i < 20;i++){
       try{
         System.err.println(i+" "+dsr.snum);
     	   while(!getData(dsr.snum,arg[0])){
     	       i++;
     	       if(i > 20 ) break;
     	       
     	    }
   	        i = 501;
       }catch(Exception e3){
          Thread.currentThread().sleep(i*100000);
          e3.printStackTrace(System.err);
          if(i==60)
          {
          	 throw new Exception("Network not avaible ! restart ");
          }
      }
     }
   }  

  }
 

  static boolean getData(String num,String datestr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL(u1,"q/q?s="+num);
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://tw.stock.yahoo.com/q/q?s="+num); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "tw.stock.yahoo.com"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    conn.setRequestProperty("Cookie", cookieValue); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    //URL u = new URL("http://tw.stock.yahoo.com/d/s/major_"+num+".html");
    //BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
    StringBuffer buy = new StringBuffer();
    StringBuffer sell = new StringBuffer();
    String s;
    int i = 0;
    String sTime = "";
    String sBuy ="";
    String sSell="";
    String sRp = "";
    String sDiff = "";
    String sRa = "" ;
    String sTotal = "" ;
    String sPclose = "" ;
    String sOpen = "" ;
    String sHigh = "" ;
    String sLow = "" ;
    while((s=br.readLine())!=null){
      if(s.indexOf("bgcolor=\"#FFFfff\" nowrap>") > 0)
      {
         if(i==0)
         { 
         	  int idx = s.indexOf("bgcolor=\"#FFFfff\" nowrap>")+25;
            sTime =s.substring(idx,s.indexOf("</td>"));
         }
         if(i==1)
         { 
         	  int idx = s.indexOf("<b>")+3;
            sRp = s.substring(idx, s.indexOf("</b>"));
         }
         if(i==2)
         { 
         	  int idx = s.indexOf("bgcolor=\"#FFFfff\" nowrap>")+25;
            sBuy = s.substring(idx,s.indexOf("</td>"));
         }
         if(i==3)
         { 
         	  int idx = s.indexOf("bgcolor=\"#FFFfff\" nowrap>")+25;
            sSell = s.substring(idx,s.indexOf("</td>"));
         }
         if(i==4)
         { 
           if(s.indexOf("<font color=#ff0000>") > 0)
             sDiff = s.substring(s.indexOf("<font color=#ff0000>")+21);
           else if (s.indexOf("<font color=#009900>") > 0)
             sDiff = "-"+s.substring(s.indexOf("<font color=#009900>")+21);
           else 
             sDiff = s.substring(s.indexOf("<font color=#000000>")+20);
         }
         if(i==5)
         { 
         	  int idx = s.indexOf("bgcolor=\"#FFFfff\" nowrap>")+25;
            sTotal = s.substring(idx,s.indexOf("</td>"));
         }
         if(i==6)
         { 
         	  int idx = s.indexOf("bgcolor=\"#FFFfff\" nowrap>")+25;
            sPclose = s.substring(idx,s.indexOf("</td>"));
         }
         if(i==7)
         { 
         	  int idx = s.indexOf("bgcolor=\"#FFFfff\" nowrap>")+25;
            sOpen = s.substring(idx,s.indexOf("</td>"));
         }
         if(i==8)
         { 
         	  int idx = s.indexOf("bgcolor=\"#FFFfff\" nowrap>")+25;
            sHigh = s.substring(idx,s.indexOf("</td>"));
         }
         if(i==9)
         { 
         	  int idx = s.indexOf("bgcolor=\"#FFFfff\" nowrap>")+25;
            sLow = s.substring(idx,s.indexOf("</td>"));
         }
         i++;
      }
      
    }
    System.out.println(num+"|"+datestr+"|"+sTime+"|"+sBuy+"|"+sSell+"|"+sRp+"|"+sDiff+"|"+sTotal+"|"+sTotal+"|"+sPclose+"|"+
                       sOpen+"|"+sHigh+"|"+sLow+"|");
    return true;
  }
}
