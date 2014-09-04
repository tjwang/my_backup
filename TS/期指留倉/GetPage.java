import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage {
static URL u1 ; 
static String cookieValue;

static private void setCookie(URLConnection conn)throws IOException{
 conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/x-ms-application, application/x-ms-xbap, application/vnd.ms-xpsdocument, application/xaml+xml, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*"); 
 conn.setRequestProperty("Referer", "http://www.taifex.com.tw/chinese/3/7_12_3.asp"); 
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
         return "TXF";
     case 2:
         return "EXF";
     case 3:
         return "FXF";
     case 4:
         return "MXF";
     case 5:
         return "T5F";
     case 6:
         return "STF";
     case 7:
         return "MSF";
     case 8:
         return "GTF";
     case 9:
         return "XIF";
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
  u1 = new URL("http://www.taifex.com.tw/chinese/3/7_12_3.asp");
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
  	
	  if(s.indexOf("期貨<br>小計") > 0){
	     break;
	  }
 	  if(s.indexOf("臺股<br>期貨") > 0){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 10 ;
       continue;
    }
//      
	  if(s.indexOf("電子<br>期貨") > 0){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 20 ;
       continue;
    }
	  if((s.indexOf("金融<br>期貨") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 30 ;
       continue;
    }
	  if((s.indexOf("小型<br>臺指<br>期貨") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 40 ;
       continue;
    }
	  if((s.indexOf("臺灣50<br>期貨") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 50 ;
       continue;
    }
	  if((s.indexOf("股票<br>期貨") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 60 ;
       continue;
    }
	  if((s.indexOf(">MSCI臺指期貨<") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 70 ;
       continue;
    }
	  if((s.indexOf(">櫃買<br>指數<br>期貨") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 80 ;
       continue;
    }
	  if((s.indexOf("非金<br>電期<br>貨") > 0) && (s.indexOf("OPTION") < 0)){
       if(sb!=null)
       {
          System.out.println(dateStr+"|"+getTypeCode(state)+"|"+getCategoryCode(state)+sb.toString());
       }
       sb = null;
       state = 90 ;
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
 

  static boolean getData(String num,String datestr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL(u1,"/d/s/major_"+num+".html");
    URLConnection conn = u.openConnection(); 
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
    	 if ((di=s.indexOf("資料日期")) > 0){
    	 	try{
    	 	  System.err.println("num:"+num+"di:"+di +" "+s);
    	    String dy = s.substring(di+5,di+7);
    	    String dm = s.substring(di+9,di+11);
    	    String dd = s.substring(di+13,di+15);
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
    System.out.print(sell.toString());
    return true;
  }

}
