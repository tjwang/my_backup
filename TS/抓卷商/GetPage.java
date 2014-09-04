import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.security.cert.Certificate;
 
import javax.net.ssl.*;
//import javax.net.ssl.SSLPeerUnverifiedException;

public class GetPage {
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
  
//  getCookie();
  u1 = new URL("http://tw.stock.yahoo.com");
  URLConnection conn = u1.openConnection(); 
  conn.setRequestProperty("Cookie", cookieValue);
  BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
  String s;
  while((s=br.readLine())!=null);
//  System.out.println(cookieValue);


  //while( (stockN=stocknum.readLine())!=null){
  trustAllHttpsCertificates();
  while(e.hasMoreElements()){
       dsr = (Ds000_Rec)e.nextElement();
   for(int i = 0 ;i < 20;i++){
       try{
         System.err.println(i+" "+dsr.snum);
        if( dsr.snum.length() > 4)  break;
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
//    HostnameVerifier hv = new HostnameVerifier() {  
//        public boolean verify(String urlHostName, SSLSession session) {  
//            return true;  
//        }  
//    };  
    URL u = new URL("https://tw.stock.yahoo.com/d/s/major_"+num+".html");
    HttpsURLConnection  conn = (HttpsURLConnection)u.openConnection(); 
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
    while((s=br.readLine())!=null){
    	 int di =0;
 //      System.out.println(s);
    	 if ((di=s.indexOf("資料日期")) > 0){
    	 	try{
    	 	  System.err.println("num:"+num+"di:"+di +" "+s);
    	    String dy = s.substring(di+5,di+8);
    	    String dm = s.substring(di+10,di+12);
    	    String dd = s.substring(di+14,di+16);
    	    dy=String.valueOf(Integer.parseInt(dy)+1911)+dm+dd;
    	    if(!dy.equals(datestr)){
    	       System.err.println("Error Date:"+dy);
    	       return false;
    	    }
    	  }catch(Exception e){
    	       return true;
    	  }  
    	 }
    	 if (startflag){
//    	 	System.out.println(s);
           if(s.indexOf("</tr") >= 0) trflag = false;
           if(trflag){
              if(count < 4) {
              	if(count == 0){
                  PMname_Rec pmr = new PMname_Rec();
                  pmr.mname = s.substring(s.indexOf("\">")+2,s.indexOf("</td>")).trim();
                  if(pmr.mname.length() > 0){
                    if( pmr.SelectByCondition("mname='"+new String(pmr.mname.getBytes("MS950"),"ISO-8859-1")+"'").hasMoreElements()){
                       //System.out.println(head +"|" +arg[1]+"|"+ pmr.mnum+lineData.substring(lineData.indexOf("|")));
              	       buy.append(num+"|"+datestr+"|");
              	       buy.append(pmr.mnum+"|");
                    }else{
                       System.err.println("no main name!!"+pmr.mname+"xx" );
                    }
                  }
              	}else{
              	   buy.append(s.substring(s.indexOf("\">")+2,s.indexOf("</td>"))+"|");
                }
              } else {
              	if(count == 4){
                  PMname_Rec pmr = new PMname_Rec();
                  pmr.mname = s.substring(s.indexOf("\">")+2,s.indexOf("</td>")).trim();
                  if(pmr.mname.length() > 0){
                    if( pmr.SelectByCondition("mname='"+new String(pmr.mname.getBytes("MS950"),"ISO-8859-1")+"'").hasMoreElements()){
                       //System.out.println(head +"|" +arg[1]+"|"+ pmr.mnum+lineData.substring(lineData.indexOf("|")));
                        sell.append(num+"|"+datestr+"|");
              	       sell.append(pmr.mnum+"|");
                    }else{
                       System.err.println("no main name!!"+pmr.mname );
                    }
                  }
              	}else{
                  sell.append(s.substring(s.indexOf("\">")+2,s.indexOf("</td>"))+"|");
                }
              }
              count = (count + 1) % 8;
              if(count == 4) buy.append("\n"); 
              if(count == 0) sell.append("\n"); 
           }
           if(s.indexOf("<tr") >= 0) trflag = true;
       }
       if(s.indexOf("賣超</td>") >= 0)
       startflag = true;
       if(s.indexOf("</table>") >= 0)
       startflag = false;
    }
//    System.out.println(num+";");
    System.out.print(buy.toString());
    if(buy.toString().indexOf(',') > 0) System.err.println("error buy amount!!"+buy.toString() );
    System.out.print(sell.toString());
    if(sell.toString().indexOf(',') > 0) System.err.println("error sell amount!!"+sell.toString() );
    return true;
  }

}
