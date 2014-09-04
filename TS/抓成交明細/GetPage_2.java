import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.security.Security;


public class GetPage_2 {
static URL u1 ; 
static String cookieValue="ASPSESSIONIDSCBASQSB=PBCNNPDCKICHBJHNDNICJNCB; ASPSESSIONIDQCDDTQSA=GFFDNIDCOPGAKMBLKHPPOGLD; __utma=1.675619342.1340900172.1340900172.1340900172.1; __utmb=1.2.10.1340900172; __utmc=1; __utmz=1.1340900172.1.1.utmcsr=coco-in.net|utmccn=(referral)|utmcmd=referral|utmcct=/thread-8087-1-1.html; __utma=106653599.1757177219.1340900172.1340900172.1340900172.1; __utmb=106653599.2.10.1340900172; __utmc=106653599; __utmz=106653599.1340900172.1.1.utmcsr=coco-in.net|utmccn=(referral)|utmcmd=referral|utmcct=/thread-8087-1-1.html; yamAnalytics=5FFA64_424420691_224EA4AC";

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

static void httpsConnect()throws Exception
{
		Class factoryClass = null;
		URLStreamHandlerFactory factory = null;
 		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		Properties properties = System.getProperties();

		String handlers = System.getProperty("java.protocol.handler.pkgs");
		if (handlers == null) {
		    // nothing specified yet (expected case)
		    properties.put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol");
		}
		else {
		    // something already there, put ourselves out front
		    properties.put("java.protocol.handler.pkgs", "com.sun.net.ssl.internal.www.protocol|".concat(handlers));		    
		}
    System.setProperties(properties); // put the updated properties back in System
    
    
    URL page = new URL("https://member.pchome.com.tw/login_check.html"); // Process the URL far enough to find the right handler
//    URL page = new URL("https://member.pchome.com.tw/login.html"); // Process the URL far enough to find the right handler
    URLConnection conn = page.openConnection();
    conn.setDoOutput(true);
    conn.setUseCaches(false); // Don't look at possibly cached data
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://pchome.syspower.com.tw/prot/sto1/com1?id=money7888&mredirect=&id=money7888"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "member.pchome.com.tw"); 
//    conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.connect(); 
    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
    wr.write("ref=&mredirect=&id=money7888&pwd=mm12345678&Submit=%B5n%A4J");
    wr.flush();  //money_kt88,qwert456
    Map<String, List<String>> headers = conn.getHeaderFields(); 
    List<String> values = headers.get("Set-Cookie"); 
    
    System.out.println(headers);
//    cookieValue = null; 
//    for (Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
//      String v = iter.next(); 
//      if (cookieValue == null)
//         cookieValue = v;
//      else
//         cookieValue = cookieValue + ";" + v;
//    } 
//    System.out.println(cookieValue);

    
    
    
    System.out.println("Content-type = " + conn.getContentType()); // See what's here
    System.out.println("Content-length = " + conn.getContentLength()); // See how much of it there is
//    // Read it all and print it out
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String buffer = "";
  while (buffer != null) {
		        try {
		            System.out.println(buffer);
		            buffer = br.readLine();
		        }
		        catch (IOException ioe) {
		            ioe.printStackTrace();
		            break;
		        }
		 }


}


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
  DBConnection.debug = false;
  //BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
  Ds000_Rec dsr = new Ds000_Rec();
  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr order by snum;");
  String stockN = null;
  
  while(e.hasMoreElements()){
       dsr = (Ds000_Rec)e.nextElement();
   for(int i = 0 ;i < 20;i++){
       try{
         System.err.println(dsr.snum);
     	   while(!getData(dsr.snum,arg[0])){
     	       i++;
     	       if(i > 20 ) break;
     	       
     	    }
   	      i = 501;
       }catch(Exception e3){
          Thread.currentThread().sleep(i*1000);
          e3.printStackTrace(System.err);
          if(i==19)
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
    URL u = new URL("http://yamstock.megatime.com.tw/asp/stockinfo/ps_pv_time.asp?stockid="+num+"&name1=D1&index1=2");
    URLConnection conn = u.openConnection(); 
    conn.setDoOutput(true);
    conn.setRequestProperty("Accept", "*/*"); 
   // conn.setRequestProperty("Referer", "http://tw.stock.yahoo.com/q/q?s="+num); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "pchome.syspower.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    conn.setRequestProperty("Cookie", cookieValue); 
//    OutputStream os = conn.getOutputStream();
//    os.write("is_check=1".getBytes());
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
    StringBuffer buy = new StringBuffer();
    StringBuffer sell = new StringBuffer();
    String s;
    boolean linein = false ;
    while((s=br.readLine())!=null){
    	 if(s.indexOf("<script>T('") >= 0 && s.indexOf("</script>") >= 0)
    	 {
         StringTokenizer st = new StringTokenizer(s,"'"); 
          int i = 0;
          System.out.print(num+"|"+datestr+"|");
    	 	  while(st.hasMoreTokens())
    	 	  {
    	 	     String ss = st.nextToken();
    	 	     i++;
    	 	     if(i%2 == 0)
    	 	     {
    	 	        if(i/2 == 4)
    	 	        {
   	 	            System.out.print(ss+"|0|");
    	 	        } else
    	 	        {
    	 	           System.out.print(ss+"|");
     	 	        }
    	 	     }
    	 	  }
         System.out.println();
       }
    }	 
    return true;
  }

}
