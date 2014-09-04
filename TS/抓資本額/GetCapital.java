import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GetCapital {
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
  URL url = new URL( "https://tw.yahoo.com" ); 
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
  cookieValue = null; 
  if(values == null) return;
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
  
  trustAllHttpsCertificates();
  getCookie();
// getData("2454");
//  BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
 Ds000attr_Rec dsr = new Ds000attr_Rec();
// Enumeration e = dsr.SelectBySQL("select * from ds000attr where snum > '3673' order by snum;");
 Enumeration e = dsr.SelectBySQL("select * from ds000attr  order by snum;");
//  while( (stockN=stocknum.readLine())!=null){
  while(e.hasMoreElements())
  {
    dsr = (Ds000attr_Rec)e.nextElement();
    
    if(dsr.ccode.equals("29"))
    {
       System.out.println(dsr.snum+"|10000000000|0|0|0|0|0|0|0|0|0|0|0|0|0|0|0|");
    } else {
      for(int i = 0 ;i < 10;i++){
        try{
        	System.err.println(dsr.snum);
   	      getData(dsr.snum);
   	      i = 80;
        }catch(IOException re){
        	  re.printStackTrace();
        	  Thread.currentThread().sleep(5000);
        }
      }  
    }
  }

 }

  static void getData(String num) throws IOException{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    int casestage = 0;
    URL u = new URL("https://tw.stock.yahoo.com/d/s/company_"+num+".html");
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
    InputStream is = conn.getInputStream();

    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    StringBuffer buy = new StringBuffer();
    String s;
    String capital=null;
    String rawProfit=null;
    String pureProfit=null;
    String unTaxtedProfit=null;
    String xxxProfit=null;
    String stockProfit=null;
    String eps1=null;
    String eps2=null;
    String eps3=null;
    String eps4=null;
    String dsdate=null;
    String dcdate=null;
    float dsamount=0;
    float dcamount=0;
    String worth=null;
    
    while((s=br.readLine())!=null){
//    	System.out.println(s);
     	 if (startflag){
    	 	 switch(casestage){
            case 1:	
              try{
            	dcamount=Float.parseFloat(s.substring(s.indexOf("\">")+2,s.indexOf("元")));
            	startflag = false;
             }catch(Exception e){
              // e.printStackTrace();
            	dcamount=0;
            	startflag = false;
             } 
              break;
            case 2:	
              try{
            	dsamount=Float.parseFloat(s.substring(s.indexOf("\">")+2,s.indexOf("元")));
            	startflag = false;
             }catch(Exception e){
       //        e.printStackTrace();
            	dsamount=0;
            	startflag = false;
             } 
              break;
            case 3:	
            try{
            	capital=s.substring(s.indexOf("\">")+2,s.indexOf("億"));
            	if(capital.charAt(0) > '9' || capital.charAt(0) < '0')
            	{
            	    capital=s.substring(s.indexOf("\" >")+3,s.indexOf("億"));
            	} 
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	capital="1";
            	startflag = false;
             } 
              break;
            case 4:	
             try{
             	rawProfit=s.substring(s.indexOf("\">")+2,s.indexOf("%"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            		rawProfit="0";
            	startflag = false;
             } 
              break;
            case 5:	
             try{
            	eps1=s.substring(s.indexOf("\">")+2,s.indexOf("元"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	eps1="0";
            	startflag = false;
             } 
              break;
            case 6:	
              try{
             	pureProfit=s.substring(s.indexOf("\">")+2,s.indexOf("%"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	pureProfit="0";
            	startflag = false;
             } 
              break;
            case 7:	
              try{
            	eps2=s.substring(s.indexOf("\">")+2,s.indexOf("元"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	eps2="0";
            	startflag = false;
             } 
              break;
            case 8:	
              try{
            	unTaxtedProfit=s.substring(s.indexOf("\">")+2,s.indexOf("%"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	unTaxtedProfit="0";
            	startflag = false;
             } 
              break;
            case 9:	
                try{
           	eps3=s.substring(s.indexOf("\">")+2,s.indexOf("元"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
           	eps3="0";
            	startflag = false;
             } 
             break;
            case 10:	
            try{
            	xxxProfit=s.substring(s.indexOf("\">")+2,s.indexOf("%"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	xxxProfit="0";
            	startflag = false;
             } 
              break;
            case 11:	
            try{
            	eps4=s.substring(s.indexOf("\">")+2,s.indexOf("元"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	eps4="0";
            	startflag = false;
             } 
              break;
            case 12:	
            try{
             	stockProfit=s.substring(s.indexOf("\">")+2,s.indexOf("%"));
             	s=br.readLine();
             	worth = s.substring(s.indexOf("每股淨值: 　　")+8,s.indexOf("元")).trim();
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	worth="0";
            	startflag = false;
             } 
              break;
            case 13:	
            try{
             	dsdate=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	stockProfit="0";
            	startflag = false;
             } 
              break;
            case 14:	
            try{
             	dcdate=s.substring(s.indexOf("\">")+2,s.indexOf("</td>"));
            	startflag = false;
             }catch(Exception e){
               e.printStackTrace();
            	stockProfit="0";
            	startflag = false;
             } 
              break;
         }
       }
       if(s.indexOf("現金股利") >= 0){
          startflag = true;
          casestage = 1 ;
       }
       if(s.indexOf("股票股利") >= 0){
          startflag = true;
          casestage = 2 ;
       }
       if(s.indexOf("股本") >= 0){
          startflag = true;
          casestage = 3 ;
       }
       if(s.indexOf("營業毛利率") >= 0){
          startflag = true;
          casestage = 4 ;
       }
       if(s.indexOf("季</") >= 0){
          startflag = true;
          casestage ++ ;
       }
       if(s.indexOf("營業利益率") >= 0){
          startflag = true;
          casestage = 6 ;
       }
//       if(s.indexOf(">95第4季") >= 0){
//          startflag = true;
//          casestage = 5 ;
//       }
       if(s.indexOf("稅前淨利率") >= 0){
          startflag = true;
          casestage = 8 ;
       }
//       if(s.indexOf(">95第3季") >= 0){
//          startflag = true;
//          casestage = 7 ;
//       }
       if(s.indexOf("資產報酬率") >= 0){
          startflag = true;
          casestage = 10 ;
       }
 //      if(s.indexOf(">95第2季") >= 0){
 //         startflag = true;
 //         casestage = 9 ;
 //      }
       if(s.indexOf("股東權益報酬率") >= 0){
          startflag = true;
          casestage = 12 ;
       }
       if(s.indexOf("除權日期") >= 0){
          startflag = true;
          casestage = 13 ;
       }
       if(s.indexOf("除息日期") >= 0){
          startflag = true;
          casestage = 14 ;
       }
    }
    try{    
       float totalEps = Float.parseFloat(eps1)+Float.parseFloat(eps2)+Float.parseFloat(eps3)+Float.parseFloat(eps4);
       System.out.println(num+"|"+((long)(Float.parseFloat(capital)*10000))+"|"+rawProfit+"|"+pureProfit+"|"+unTaxtedProfit+"|"+
                xxxProfit+"|"+stockProfit+"|"+eps1+"|"+eps2+"|"+eps3+"|"+eps4+"|"+totalEps+"|"+
                 convetDate(dsdate)+"|"+convetDate(dcdate)+"|"+dcamount+"|"+dsamount+"|"+worth+"|");
     }catch(Exception e){
     	  System.err.println(capital);
        e.printStackTrace();
        System.err.println(num+"|"+((long)(Float.parseFloat(capital)*10000))+"|"+rawProfit+"|"+pureProfit+"|"+unTaxtedProfit+"|"+
                xxxProfit+"|"+stockProfit+"|"+eps1+"|"+eps2+"|"+eps3+"|"+eps4+"|"+
                 convetDate(dsdate)+"|"+convetDate(dcdate)+"|"+dcamount+"|"+dsamount+"|"+worth+"|");
   
    }
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
    }else if((ca[i] == '/') && (i==2 || i==3))
    {
       dint = (dint +1911);
    }else if((ca[i] == '/') && (i==5 || i==6))
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
