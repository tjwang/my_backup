import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage2 {
static URL u1 ; 
static String cookieValue;

static private void setCookie(URLConnection conn)throws IOException{
 conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/x-ms-application, application/x-ms-xbap, application/vnd.ms-xpsdocument, application/xaml+xml, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*"); 
 conn.setRequestProperty("Referer", "http://www.taifex.com.tw/chinese/3/7_12_4.asp"); 
 conn.setRequestProperty("Accept-Language", "zh-tw"); 
 //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
 conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; GTB6.5; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; InfoPath.1)"); 
 conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
 conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
 conn.setRequestProperty("Host", "www.taifex.com.tw"); 
 conn.setRequestProperty("Connection", "Keep-Alive"); 
 conn.setRequestProperty("Cache-Control", "no-cache"); 
 conn.setRequestProperty("Cookie", "ASPSESSIONIDAACCRRDD=HFAIODIDICFEFPIOCLHGKMDP; BIGipServerPOOL_PORTAL=33689772.20480.0000"); 
 //System.out.println(cookieValue);

}

static String getTypeCode(int state)
{
   switch(state/10)
   {
     case 1:
         return "TXO";
     case 2:
         return "TEO";
     case 3:
         return "TFO";
     case 4:
         return "STO";
     case 5:
         return "MSO";
     case 6:
         return "GTO";
     case 7:
         return "XIO";
   }
   return null;
}
static String getCategoryCode(int state)
{
   switch(state%10)
   {
     case 1:
         return "A";
     case 2:
         return "B";
     case 3:
         return "F";
   }
   return null;
}
 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
   String dateStr = arg[0];
   int dateInt = Integer.parseInt(dateStr);
DBConnection.debug = false;
  u1 = new URL("http://www.taifex.com.tw/chinese/3/7_12_4.asp");
  URLConnection conn = u1.openConnection(); 
  conn.setDoOutput(true);
  setCookie(conn);
  OutputStream os = conn.getOutputStream();
  os.write(("goday=&syear="+(dateInt/10000)+"&smonth="+((dateInt%10000)/100)+"&sday="+(dateInt%100)+"&COMMODITY_ID=").getBytes("ISO-8859-1"));
  os.close();
  BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"ISO-8859-1"));
  String s;
   int state = 0;
   String monthStr = null;
   String type = "P";
   StringBuffer sb = null;   
  while((s=br.readLine())!=null)
  { 
  	s = new String(s.getBytes("ISO-8859-1"),"UTF-8");
	  if(s.indexOf("選擇權<br>小計") > 0){
	     break;
	  }
 	  if((s.indexOf(">臺指<br>選擇<br>權") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 10 ;
       continue;
    }
//       System.out.println(s);
	  if((s.indexOf("電子<br>選擇<br>權") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 20 ;
       continue;
    }
	  if((s.indexOf("金融<br>選擇<br>權") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 30 ;
       continue;
    }
	  if((s.indexOf("股票<br>選擇<br>權") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 40 ;
       continue;
    }
	  if((s.indexOf(">MSCI臺指選擇權<") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 50 ;
       continue;
    }
	  if((s.indexOf("櫃買<br>指數<br>選擇<br>權") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 60 ;
       continue;
    }
	  if((s.indexOf("非金<br>電選<br>擇權") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 70 ;
       continue;
    }
    if((state > 0) && (s.indexOf("自營商") > 0))
    {
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       state = state/10 * 10 + 1 ;
       sb = new StringBuffer();
       continue;
    }
    if((state > 0) && (s.indexOf("投信") > 0))
    {
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       state = state/10 * 10 + 2 ;
       sb = new StringBuffer();
       continue;
    }
    if((state > 0) && (s.indexOf("外資") > 0))
    {
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       state = state/10 * 10 + 3 ;
       sb = new StringBuffer();
       continue;
    }
    if(s.indexOf("<div align=\"right\"><font color=\"blue\">") > 0)
    {
       String datas = s.substring(s.indexOf("<div align=\"right\"><font color=\"blue\">")+
                                            "<div align=\"right\"><font color=\"blue\">".length(),s.indexOf("</font>"));
       if(!"".equals(datas))
       {
          datas = datas.replaceAll(",","");
          Integer.parseInt(datas); 
          sb.append("|"+datas);
       }
    }else if(s.indexOf("<div align=\"right\">") > 0)
    {
       String datas = s.substring(s.indexOf("<div align=\"right\">")+
                                            "<div align=\"right\">".length(),s.indexOf("</div>"));
       if(!"".equals(datas))
       {
          datas = datas.replaceAll(",","");
          Integer.parseInt(datas); 
          sb.append("|"+datas);
       }
    }
   // System.out.println(s);
  }
   if(sb!=null)
   {
      System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
   }
  }
 


}
