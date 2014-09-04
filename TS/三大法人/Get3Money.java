import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Get3Money {
static URL u1 ; 
static String cookieValue;
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
  URL url = new URL( "http://tw.yahoo.com" ); 
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
  
  System.err.println(headers+"!!");
 if(values == null) return ;
   cookieValue = null; 
  for (Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
     String v = iter.next(); 
     if (cookieValue == null)
         cookieValue = v;
     else
         cookieValue = cookieValue + ";" + v;
 } 
 
 System.err.println(cookieValue);
String s;
BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
while((s=br.readLine())!=null);
}
 static public void main(String[] arg)throws Exception{
  //System.out.println("Hello!! Java !! Show Me The Momey!!!");
  DBConnection.debug = false;
  String stockN = null;
  
  getCookie();
  trustAllHttpsCertificates();
  getData(arg[0]);
 }

  static void getData(String dateStr) throws IOException{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    int casestage = 0;
    URL u = new URL("https://tw.stock.yahoo.com/d/i/major.html");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://tw.stock.yahoo.com/d/i/major.html"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "tw.stock.yahoo.com"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    conn.setRequestProperty("Cookie", cookieValue); 
    InputStream is = conn.getInputStream();

    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    StringBuffer buy = new StringBuffer();
    String s;
    String FMBuy=null;
    String FMSell=null;
    String FMSum=null;
    String FM1Buy=null;
    String FM1Sell=null;
    String FM1Sum=null;
    String FM2Buy=null;
    String FM2Sell=null;
    String FM2Sum=null;
    String FM3Buy=null;
    String FM3Sell=null;
    String FM3Sum=null;
    String FM4Buy=null;
    String FM4Sell=null;
    String FM4Sum=null;
    String MMBuy=null;
    String MMSell=null;
    String MMSum=null;
    String SMBuy=null;
    String SMSell=null;
    String SMSum=null;
    
    String FMBuy2=null;
    String FMSell2=null;
    String FMSum2=null;
    String MMBuy2=null;
    String MMSell2=null;
    String MMSum2=null;
    String SMBuy2=null;
    String SMSell2=null;
    String SMSum2=null;
    
    while((s=br.readLine())!=null){
    	 if (startflag){
    	 	 switch(casestage){
            case 1:	
//              System.out.println(s);
             	MMBuy=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM1Buy=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM2Buy=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM3Buy=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM4Buy=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FMBuy=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	SMBuy=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
              break;
            case 2:	
             	MMSell=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM1Sell=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM2Sell=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM3Sell=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM4Sell=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FMSell=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	SMSell=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
              break;
            case 3:	
             	MMSum=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM1Sum=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM2Sum=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM3Sum=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FM4Sum=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FMSum=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	SMSum=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
              break;
            case 4:	
             	MMBuy2=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FMBuy2=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	SMBuy2=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
              break;
            case 5:	
             	MMSell2=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FMSell2=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	SMSell2=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
              break;
            case 6:	
             	MMSum2=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	FMSum2=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
             	s=br.readLine();
             	SMSum2=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
              break;
         }
       }
       startflag =false;
       if(s.indexOf("買進(億)") >= 0){
          startflag = true;
          if(casestage >=3)
          casestage = 4 ;
          else
          casestage = 1 ;
       }
       if(s.indexOf("賣出(億)") >= 0){
          startflag = true;
          if(casestage >=3)
          casestage = 5 ;
          else
          casestage = 2 ;
       }
       if(s.indexOf("買賣超(億)") >= 0){
          startflag = true;
          if(casestage >=3)
          casestage = 6 ;
          else
          casestage = 3 ;
       }
    }
    System.out.println(dateStr+"|F|"+FMBuy+"|"+FMSell+"|"+FMSum+"|");
    System.out.println(dateStr+"|B|"+MMBuy+"|"+MMSell+"|"+MMSum+"|");
    System.out.println(dateStr+"|A|"+SMBuy+"|"+SMSell+"|"+SMSum+"|");
    System.out.println(dateStr+"|F1|"+FM1Buy+"|"+FM1Sell+"|"+FM1Sum+"|");
    System.out.println(dateStr+"|F2|"+FM2Buy+"|"+FM2Sell+"|"+FM2Sum+"|");
    System.out.println(dateStr+"|F3|"+FM3Buy+"|"+FM3Sell+"|"+FM3Sum+"|");
    System.out.println(dateStr+"|F4|"+FM4Buy+"|"+FM4Sell+"|"+FM4Sum+"|");
    System.out.println(dateStr+"|ff|"+FMBuy2+"|"+FMSell2+"|"+FMSum2+"|");
    System.out.println(dateStr+"|bb|"+MMBuy2+"|"+MMSell2+"|"+MMSum2+"|");
    System.out.println(dateStr+"|aa|"+SMBuy2+"|"+SMSell2+"|"+SMSum2+"|");
    is.close();  
  }
static String convetDate(String ds)
{
  char[] ca = ds.toCharArray();
  int dint = 0;
  for(int i=0;i<ca.length;i++)
  {
    if(ca[i] == '-')
    {
       return "10000101";
    }else if((ca[i] >= 0x30) && (ca[i] <= 0x39))
    {
       dint = dint*10 + (ca[i]-0x30);
    }else if((ca[i] == '/') && (i==2))
    {
       dint = (dint +1911);
    }else if((ca[i] == '/') && (i==5))
    {
       dint = dint;
    }else{
     System.err.println("errr "+ca[i]+" "+i);
       return "10000101";
    }
    
  }
  return String.valueOf(dint);
}

}
