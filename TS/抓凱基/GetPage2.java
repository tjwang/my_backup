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
  Ds000_Rec dsr = new Ds000_Rec();
  PMname_Rec mnr = new PMname_Rec();
//  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr  where snum < '5515' order by snum;");
  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr   order by snum;");
  Enumeration e2 = mnr.SelectBySQL("select * from pmname2  order by mnum;");
//  Enumeration e2 = mnr.SelectBySQL("select * from pmname2 where mnum='9200' order by mnum;");
  String stockN = null;
  
//  getCookie();
//  u1 = new URL("http://just1.kgieworld.com.tw/z/zc/zco/zco0/zco0_2338_5820.djhtm");
//  URLConnection conn = u1.openConnection(); 
//  conn.setRequestProperty("Cookie", cookieValue);
//  BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//  String s;
//  while((s=br.readLine())!=null);
//  System.out.println(cookieValue);

//    getData("2338","5820",arg[0]);
 Vector v = new Vector();
 while(e2.hasMoreElements()){
    v.add(e2.nextElement());
 }
  while(e.hasMoreElements()){
      dsr = (Ds000_Rec)e.nextElement();
     System.err.println(" "+dsr.snum);
     e2 = v.elements();
      while(e2.hasMoreElements()){
             mnr = (PMname_Rec)e2.nextElement();
             for(int i = 0 ;i < 20;i++){
               try{
            	    while(!getData(dsr.snum,mnr.mnum,arg[0])){
            	       i++;
            	       if(i > 20 ) break;
            	     }
       	          i = 501;
                }catch(Exception e3){
                	e3.printStackTrace();
                  if (i>15)
                  {
                     System.err.println("Error"+i);
                     (new DataInputStream(System.in)).readLine();
                     System.err.println("Ok ..go!"+i);
                     i = 0; 
                  }else{
                     System.err.println("Error Sleep"+i);
                     Thread.currentThread().sleep(8000);
                  }
                }
             }
       }
  }  

  }
 

  static boolean getData(String snum,String mnum,String yearStr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL("http://web.tcsc.com.tw/z/zc/zco/zco0/zco0_"+snum+"_"+mnum+".djhtm");

    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
 //   conn.setRequestProperty("Referer", "http://tw.stock.yahoo.com/q/q?s="+num); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "just1.kgieworld.com.tw"); 
    conn.setRequestProperty("Connection", "close"); 
    //    conn.setRequestProperty("Cookie", cookieValue); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    conn.connect();
    InputStream is = conn.getInputStream();

//   InputStream is = u.openStream();

    BufferedReader br = new BufferedReader(new InputStreamReader(is));

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
         if(s.indexOf("class=\"t4n0\">") > 0)
         {
         	  int idx = s.indexOf("class=\"t4n0\">")+13;
            sTime =s.substring(idx,s.indexOf("</TD>"));
            i = 1;
         }
         if(s.indexOf("class=\"t3") > 0)
         {
           StringBuffer sb = new StringBuffer();
           if(i==1)
           { 
           	  int idx = s.indexOf("class=\"t3")+13;
           	  sBuy = s.substring(idx, s.indexOf("</TD>"));
           	  for(int j = 0 ;j<sBuy.length();j++)
           	  {
           	     if(sBuy.charAt(j) != ',')   sb.append(sBuy.charAt(j));
           	  }
              sBuy = sb.toString();
          }
           if(i==2)
           { 
           	  int idx = s.indexOf("class=\"t3")+13;
              sSell = s.substring(idx,s.indexOf("</TD>"));
           	  for(int j = 0 ;j<sSell.length();j++)
           	  {
           	     if(sSell.charAt(j) != ',')  sb.append(sSell.charAt(j));
           	  }
              sSell = sb.toString();
           }
           if(i==3)
           { 
           	  int idx = s.indexOf("class=\"t3")+13;
              sTotal = s.substring(idx,s.indexOf("</TD>"));
           	  for(int j = 0 ;j<sTotal.length();j++)
           	  {
           	     if(sTotal.charAt(j) != ',')     sb.append(sTotal.charAt(j));
           	  }
              sTotal = sb.toString();
 //             if(Integer.parseInt("1"+sTime.substring(0,2)) > 106)
 //             {
 //                System.out.println(snum+"|"+"2013"+sTime.substring(0,2)+sTime.substring(3)+"|"+mnum+"|"+sBuy+"|"+sSell+"|"+sTotal+"|");
  //             }else{
                 System.out.println(snum+"|"+yearStr+sTime.substring(0,2)+sTime.substring(3)+"|"+mnum+"|"+sBuy+"|"+sSell+"|"+sTotal+"|");
  //            }
           }
           i++;
         //  System.out.println(i);
         } 
    }
    is.close();
    return true;
  }
}
