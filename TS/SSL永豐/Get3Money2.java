import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.ssl.*;

public class Get3Money2 {
static URL u1 ; 
static String cookieValue;

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
  
 // getCookie();
  getData(arg[0]);
 }

  static void getData(String dateStr) throws IOException{
        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket("eztrade.sinopac.com.tw", 443);
String postdata = "SANSID=Q122291821Sb2a4dbb3f4ab3f8f8d6866f39de5577e&BHNO=0020&ACTNO=0664316&"+
"CSEQ=0664316&STKID=TXF&STOCKID=TXF&SMONTH=201209&SMONTH2=&SPRICE=&CODE=&SANSTOCK=&"+
"QFUNC=&QMODE=AWS&TMODE=F&LMODE=M&SKIND=&TCODE=&QSUM_MODE=";
    String testData ="POST /scripts_ft/cgirpc32.dll/RPCSVR551S_cgiqpric HTTP/1.1\r\n"+
"Accept: */*\r\n"+
"Accept-Language: zh-tw\r\n"+
"User-Agent: Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; GTB7.4; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; InfoPath.1)\r\n"+
"Accept-Encoding: gzip, deflate\r\n"+
"Content-Length: "+postdata.length()+"\r\n"+
"Connection: Keep-Alive \r\n\r\n"+postdata;

            OutputStream os = sslsocket.getOutputStream();
            //OutputStreamWriter outputstreamwriter = new OutputStreamWriter(outputstream);
            //BufferedWriter bufferedwriter = new BufferedWriter(outputstreamwriter);
            InputStream inputstream = sslsocket.getInputStream();
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
            BufferedReader br = new BufferedReader(inputstreamreader);
            os.write(testData.getBytes());
       
       String s = null;  
        while((s=br.readLine())!= null)
        {
            System.out.println(s);
            if(s.trim().equals("0"))
            {
               os.write(testData.getBytes());
             }
        }

    
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
