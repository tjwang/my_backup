import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.security.Security;


public class GetPage {
static URL u1 ; 
static String cookieValue="stock_config=YTozOntzOjc6IlN0b2NrSWQiO047czozOiJ0YWciO2k6MztzOjQ6InR5cGUiO2k6Mjt9; stock_id=money7888; cookie=YToyOntzOjI6Iml2IjtzOjg6IgVEDQ2O5tW9IjtzOjU6ImNyeXB0IjtzOjM4OiKCpJgPU9yO4Y0W5ZSmErG3CebFUk030H8ak6Sx3RZEUY9moMr7CSI7fQ%3D%3D";

static private void getCookie()throws IOException{
  URL url = new URL( "http://pchome.megatime.com.tw/stock/sto0/ock3/sid1101.html" ); 
  URLConnection conn = url.openConnection(); 
   conn.setDoOutput(true);
   conn.setRequestProperty("Accept", "*/*"); 
   conn.setRequestProperty("Accept-Language", "zh-tw"); 
   conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
   conn.setRequestProperty("Host", "pchome.megatime.com.tw"); 
   conn.setRequestProperty("Connection", "Keep-Alive"); 
   conn.connect(); 
   OutputStream os = conn.getOutputStream();
   os.write("is_check=1".getBytes());
   Map<String, List<String>> headers = conn.getHeaderFields(); 
   List<String> values = headers.get("Location"); 
  
  //System.out.println(headers);
  cookieValue = null; 
  for (Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
     String v = iter.next(); 
    System.out.println(v);
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
    wr.flush();
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
    //     getCookie();
    //     if(i >= 0)
    //     return;
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
    URL u = new URL("http://pchome.megatime.com.tw/stock/sto0/ock3/sid"+num+".html");
    URLConnection conn = u.openConnection(); 
    conn.setDoOutput(true);
    conn.setRequestProperty("Accept", "*/*"); 
   // conn.setRequestProperty("Referer", "http://tw.stock.yahoo.com/q/q?s="+num); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "pchome.megatime.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    conn.setRequestProperty("Cookie", cookieValue); 
    OutputStream os = conn.getOutputStream();
    os.write("is_check=1".getBytes());
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
    StringBuffer buy = new StringBuffer();
    StringBuffer sell = new StringBuffer();
    String s;
    boolean linein = false ;
    while((s=br.readLine())!=null){
    	 int di =0;
    //      System.err.println(s);
   	 if ((di=s.indexOf("²Ö­p¶q(±i)")) > 0){
    	 	try{
    	 		StringTokenizer st = new StringTokenizer(s,"<>"); 
    	 	  while(st.hasMoreTokens())
    	 	  {
    	 	     String stoken = st.nextToken();
    	 	     if((stoken.indexOf(':')>0)&&(stoken.length()<15))
    	 	     {
    	 	       System.out.print(num+"|"+datestr+"|"+stoken+"|");
    	 	       linein = true;  
    	 	     }else if(stoken.equals("¡Ð")||stoken.equals("--"))
    	 	     {
               System.out.print(stoken+"|");
    	 	     }else if(stoken.equals("/TR") || stoken.equals("/tr"))
    	 	     {
    	 	       if(linein)
    	 	         System.out.println("");
    	 	       linein = false;  
    	 	     }else {
    	 	        try{
    	 	          Float.parseFloat(stoken);
    	 	          System.out.print(stoken+"|");
    	 	        }catch( NumberFormatException nfe)
    	 	        {
    	 	        
    	 	        }
    	 	     }
    	 	   
    	 	  }
    	  }catch(Exception e){
    	       return true;
    	  }  
    	 }
    }	 
    return true;
  }

}
