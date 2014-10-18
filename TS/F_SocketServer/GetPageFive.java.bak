import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;

public class GetPageFive {
static URL u1 ; 
static Hashtable idxht = null;
static String postData="ctl00_ScriptManager1_HiddenField=%3B%3BAjaxControlToolkit%2C+Version%3D1.0.10618.0%2C+Culture%3Dneutral%2C+PublicKeyToken%3D28f01b0e84b6d53e%3Azh-TW%3A43a0de04-5dca-41f9-a426-313ba06240d5%3A411fea1c%3A865923e8%3Ae7c87f07%3A91bd373d%3Abbfda34c%3A30a78ec5%3A3510d9fc%3B&ctl00%24ContentPlaceHolder1%24Uc_future_menu1%24hifCategory=&ctl00%24ContentPlaceHolder1%24Uc_future_menu1%24txtSearch=&ctl00%24ContentPlaceHolder1%24cbxRefresh=on&ctl00%24ContentPlaceHolder1%24ddlFusa_Commodity=&ctl00%24ContentPlaceHolder1%24ddlFusa_SelMon=&ctl00%24ContentPlaceHolder1%24btnDgToExcel=%E5%8C%AF%E5%87%BAExcel%E5%A0%B1%E8%A1%A8&hiddenInputToUpdateATBuffer_CommonToolkitScripts=1&__EVENTTARGET=&__EVENTARGUMENT=&__LASTFOCUS=&__VIEWSTATE=";
static String cookieValue = null;
static String cookie_value = null; 
static String viewState = null;
static String[] future_list = {"TX", "TX104","TX114","TX124", "TX035"};
static String[] wcode_list = {"^N225", "^HSI", "000001.SS", "^KS11"};
static String[] index_list={"1"/*發行量加權股價指數*/,"2"/*未含金融保險股指數*/,"200"/*成交金額*/,"201"/*成交數量,成交筆數*/,
	                          "220"/*成交金額<股票>*/, "221"/*成交數量,成交筆數 */,
	                          "110"/*總委買數量,總委買筆數*/,"111"/*總委賣數量,總委賣筆數*/,
	                          "130"/*總委買數量,總委買筆數*/,"131"/*總委賣數量,總委賣筆數*/,
	                          "132"/*漲停委買數量,漲停委買筆數*/,"133"/*漲停委賣筆數,漲停委賣數量*/,
	                          "134"/*跌停委買數量,跌停委買筆數*/,"135"/*跌停委賣數量,跌停委賣筆數*/};
static  stock.tool.F_SocketClient fcli = null;
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
static public void main(String[] arg)throws Exception{
  DBConnection.debug = false;
  u1 = new URL("http://mis.twse.com.tw/stock_best5.html?stockId=1101");
  String s;
  idxht = new Hashtable();
  setupViewState();
  fcli = new stock.tool.F_SocketClient(8888);
  Date lastWgetTime = null;
  for(;;)
  {
     Date d = new Date();
     System.err.println("Start time: "+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds()));
     if(((d.getHours() == 13) && (d.getMinutes() > 45)) || (d.getHours() > 13))
     {
        break;
     }
     String ds = GMethod.d2s(d);
     System.out.println("getIndexData in");
     getIndexData(ds);
     System.out.println("getIndexData out");
     System.out.println("getFutureData in");
     for(int i=0;i<future_list.length;i++)
     {
        getFutureData(ds,future_list[i]);
     }
      System.out.println("getFutureData out");
    if(lastWgetTime == null || d.getTime() - lastWgetTime.getTime() > 60000)
     {
     System.out.println("getWData in");
        for(int i=0;i<wcode_list.length;i++)
        {
           try{
             getWData(ds,wcode_list[i]);
           } catch(Exception xe)
           {
              xe.printStackTrace();
           }
        }
        lastWgetTime = d;
     System.out.println("getWData out");
     }
     d = new Date();
     Thread.currentThread().sleep(500);

     System.err.println("End time  :"+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds()));
   }

 }
 
  static boolean isIndexList(String idx)
  {
     for(int i=0; i<index_list.length; i++)
     {
         if(index_list[i].equals(idx)) return true;
     }
     return false;
  }
  
  static boolean getIndexData(String datestr) 
  {
 try{   

     URL ur = new URL("http://mis.twse.com.tw/stock/api/getStatis.jsp?ex=tse&delay=0&_=1401861223656");
     URLConnection conn = ur.openConnection(); 
     conn.setRequestProperty("Referer", "http://mis.twse.com.tw/stock"); 
     conn.setRequestProperty("Host", "mis.twse.com.tw"); 
     conn.setRequestProperty("Cache-Control", "no-cache"); 
     if(cookie_value != null)
     {
        conn.setRequestProperty("Cookie", cookie_value);
     }
     
    conn.setReadTimeout(13000);
    conn.setConnectTimeout(13000);
     conn.connect(); 
     if(cookie_value == null)
     {
        Map<String, List<String>> headers = conn.getHeaderFields(); 
        if(headers != null)
        {
            List<String> values = headers.get("Set-Cookie"); 
            if(values!=null && values.size() > 0)
            {
               cookie_value = null; 
               for (Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
                  String v = iter.next(); 
//                  System.out.println(v);
                  if (cookie_value == null)
                      cookie_value = v;
                  else
                      cookie_value = cookie_value + ";" + v;
               }
            }
        }
     }
     String[] snum_idx = new String[12];
     String[] data_idx_1 = new String[12];
     String[] data_idx_2 = new String[12];
     String timestr = "00:00:00";
     for(int i=0;i<12;i++)
     {
         snum_idx[i] = index_list[2+i];
         data_idx_1[i] = null;//index_list[2+i]+"|";
         data_idx_2[i] = null;
     }
     //String  snum_money_al=  "200"/*成交金額*/;
     //String  snum_ra_al = "201";/*成交數量,成交筆數*/
	   //String  snum_money_sk = "220";/*成交金額<股票>*/
	   //String  snum_ra_st= "221";/*成交數量,成交筆數 */
	   //String  snum_buy_ra_al=  "110";/*總委買數量,總委買筆數*/
	   //String  snum_sell_ra_al=  "111";/*總委賣數量,總委賣筆數*/
	   //String  snum_buy_ra_st=   "130";/*總委買數量,總委買筆數*/
	   //String  snum_sell_ra_st= "131";/*總委賣數量,總委賣筆數*/
	   //String  snum_buyU_ra_st= "132";/*漲停委買數量,漲停委買筆數*/
	   //String  snum_sellU_ra_st="133";/*漲停委賣筆數,漲停委賣數量*/
	   //String  snum_buyL_ra_st=  "134";/*跌停委買數量,跌停委買筆數*/;
	   //String  snum_sellL_ra_st="135"/*跌停委賣數量,跌停委賣筆數*/;
	   //
     //String  data_money_al=  "200"/*成交金額0*/;
     //String  data_ra_al = "201";/*成交數量,成交筆數1*/
	   //String  data_money_sk = "220";/*成交金額<股票>2*/
	   //String  data_ra_st= "221";/*成交數量,成交筆數 3*/
	   //String  data_buy_ra_al=  "110";/*總委買數量,總委買筆數4*/
	   //String  data_sell_ra_al=  "111";/*總委賣數量,總委賣筆數5*/
	   //String  data_buy_ra_st=   "130";/*總委買數量,總委買筆數6*/
	   //String  data_sell_ra_st= "131";/*總委賣數量,總委賣筆數7*/
	   //String  data_buyU_ra_st= "132";/*漲停委買數量,漲停委買筆數8*/
	   //String  data_sellU_ra_st="133";/*漲停委賣筆數,漲停委賣數量9*/
	   //String  data_buyL_ra_st=  "134";/*跌停委買數量,跌停委買筆數10*/;
	   //String  data_sellL_ra_st="135"/*跌停委賣數量,跌停委賣筆數11*/;

     BufferedReader ir = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     String s = null;
     while((s=ir.readLine())!=null)
     {
//          System.out.println(s);
          StringTokenizer st = new StringTokenizer(s,",");
          while(st.hasMoreTokens())
          {
             String data = st.nextToken();
             if(data.indexOf("\"tz\":") == 0)
             {
                 data_idx_1[0] = data.substring(6,data.length()-1);
//                 System.out.println("成交金額<all>:"+data_idx_1[0]);
             } else if(data.indexOf("\"fz\":") == 0)
             {
                  data_idx_1[2] = data.substring(6,data.length()-1);
//                  System.out.println("成交金額<stock>:"+data_idx_1[2]);
             } else if(data.indexOf("\"tv\":") == 0)
             {
                   data_idx_1[1] =  data.substring(6,data.length()-1);
//                   System.out.println("成交數量<all>:"+ data_idx_1[1]);
             } else if(data.indexOf("\"fv\":") == 0)
             {
                  data_idx_1[3] =  data.substring(6,data.length()-1);
//                  System.out.println("成交數量<stock>:"+data_idx_1[3]);
             } else if(data.indexOf("\"tr\":") == 0)
             {
                   data_idx_2[1] =  data.substring(6,data.length()-1);
//                   System.out.println("成交筆數<all>:"+data_idx_2[1]);
             } else if(data.indexOf("\"fr\":") == 0)
             {
                   data_idx_2[3] =  data.substring(6,data.length()-1);
//                     System.out.println("成交筆數<stock>:"+data_idx_2[3]);
             } else if(data.indexOf("\"t4\":") == 0)
             {
                  data_idx_1[4] = data.substring(6,data.length()-1);
//                   System.out.println("總委買數量<all>:"+data_idx_1[4]);
             } else if(data.indexOf("\"s4\":") == 0)
             {
                   data_idx_1[6] = data.substring(6,data.length()-1);
//                   System.out.println("總委買數量<stock>:"+ data_idx_1[6]);
             } else if(data.indexOf("\"t2\":") == 0)
             {
                  data_idx_2[4] = data.substring(6,data.length()-1);
//                   System.out.println("總委買筆數<all>:"+data_idx_2[4]);
             } else if(data.indexOf("\"s2\":") == 0)
             {
                   data_idx_2[6] = data.substring(6,data.length()-1);
//                   System.out.println("總委買筆數<stock>:"+data_idx_2[6]);
             } else if(data.indexOf("\"t3\":") == 0)
             {
                   data_idx_1[5] = data.substring(6,data.length()-1);
//                   System.out.println("總委賣數量<all>:"+data_idx_1[5]);
             } else if(data.indexOf("\"s3\":") == 0)
             {
                   data_idx_1[7] = data.substring(6,data.length()-1);
//                   System.out.println("總委賣數量<stock>:"+data_idx_1[7]);
             } else if(data.indexOf("\"t1\":") == 0)
             {
                  data_idx_2[5] = data.substring(6,data.length()-1);
//                  System.out.println("總委賣筆數<all>:"+data_idx_2[5]);
             } else if(data.indexOf("\"s1\":") == 0)
             {
                   data_idx_2[7] = data.substring(6,data.length()-1);
//                   System.out.println("總委賣筆數<stock>:"+ data_idx_2[7]);
             } else if(data.indexOf("\"tu4\":") == 0)
             {
//                   System.out.println("漲停委買數量<all>:"+data.substring(7,data.length()-1));
             } else if(data.indexOf("\"su4\":") == 0)
             {
                   data_idx_1[8] = data.substring(7,data.length()-1);
//                   System.out.println("漲停委買數量<stock>:"+data_idx_1[8]);
             } else if(data.indexOf("\"tu2\":") == 0)
             {
//                   System.out.println("漲停委買筆數<all>:"+data.substring(7,data.length()-1));
             } else if(data.indexOf("\"su2\":") == 0)
             {
                   data_idx_2[8] = data.substring(7,data.length()-1);
//                   System.out.println("漲停委買筆數<stock>:"+data_idx_2[8]);
             } else if(data.indexOf("\"tu3\":") == 0)
             {
//                   System.out.println("漲停委賣數量<all>:"+data.substring(7,data.length()-1));
             } else if(data.indexOf("\"su3\":") == 0)
             {
                   data_idx_1[9] = data.substring(7,data.length()-1);
//                   System.out.println("漲停委賣數量<stock>:"+data_idx_1[9]);
             } else if(data.indexOf("\"tu1\":") == 0)
             {
//                   System.out.println("漲停委賣筆數<all>:"+data.substring(7,data.length()-1));
             } else if(data.indexOf("\"su1\":") == 0)
             {
                   data_idx_2[9] = data.substring(7,data.length()-1);
//                   System.out.println("漲停委賣筆數<stock>:"+data_idx_2[9]);
             } else if(data.indexOf("\"tw4\":") == 0)
             {
//                   System.out.println("跌停委買數量<all>:"+data.substring(7,data.length()-1));
             } else if(data.indexOf("\"sw4\":") == 0)
             {
                   data_idx_1[10] = data.substring(7,data.length()-1);
//                   System.out.println("跌停委買數量<stock>:"+data_idx_1[10]);
             } else if(data.indexOf("\"tw2\":") == 0)
             {
//                   System.out.println("跌停委買筆數<all>:"+data.substring(7,data.length()-1));
             } else if(data.indexOf("\"sw2\":") == 0)
             {
                   data_idx_2[10] = data.substring(7,data.length()-1);
//                   System.out.println("跌停委買筆數<stock>:"+ data_idx_2[10]);
             } else if(data.indexOf("\"tw3\":") == 0)
             {
//                   System.out.println("跌停委賣數量<all>:"+data.substring(7,data.length()-1));
             } else if(data.indexOf("\"sw3\":") == 0)
             {
                   data_idx_1[11] = data.substring(7,data.length()-1);
//                   System.out.println("跌停委賣數量<stock>:"+data_idx_1[11]);
             } else if(data.indexOf("\"tw1\":") == 0)
             {
//                   System.out.println("跌停委賣筆數<all>:"+data.substring(7,data.length()-1));
             } else if(data.indexOf("\"sw1\":") == 0)
             {
                    data_idx_2[11] = data.substring(7,data.length()-1);
//                  System.out.println("跌停委賣筆數<stock>:"+data_idx_2[11]);
             }  else if(data.indexOf("\"%\":") == 0)
             {
                  timestr = data.substring(5,data.length()-1);
                  timestr = timestr.replaceAll(":","");
                  if(timestr.length() < 6) timestr = "0"+timestr;
//                  System.out.println("time:"+timestr);
             } 
          }
     }
     for(int i=0;i<12;i++)
     { 
        StringBuffer sb = new StringBuffer();
        sb.append(snum_idx[i]);
        sb.append("|");
        sb.append(timestr);
        sb.append("|");
        sb.append(data_idx_1[i]);
        sb.append("|");
       if(data_idx_2[i]!=null)
        {
           sb.append(data_idx_2[i]);
           sb.append("|");
        }
        
        String toPrint = sb.toString();
        String prevPrint = (String)idxht.get(snum_idx[i]);
        if(!toPrint.equals(prevPrint))
        {
//           System.out.println(snum_idx[i]+"--->"+toPrint);
           fcli.sendMsg(snum_idx[i], toPrint);
           idxht.put(snum_idx[i],toPrint);
        }
    }
    return true;
   }catch(Exception xxxe)
   {
       xxxe.printStackTrace();
   }
   return false;
 } 
 /*
  static boolean getIndexData(String datestr) {
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
 try{   
    URL u = new URL(u1,"/data/TSEIndex.csv");
    URLConnection conn = u.openConnection(); 
*/
//    conn.setRequestProperty("Accept", "*/*"); 
/*
    conn.setRequestProperty("Referer", "http://mis.twse.com.tw/market_index.html?market=1"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "mis.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String s;
    while((s=br.readLine())!=null){
         StringBuffer sb = new StringBuffer();
 //   	 System.err.println(s);
         String num ="";
         if(s.indexOf("\"0\",\"") >= 0)
         {
            continue;
         }
             boolean inqflag = false;
             int j = 0;
             for(int i = 0; i < s.length(); i++)
             {
                char c = s.charAt(i);
                if( c == '"')
                {
                  inqflag = !inqflag;
                } else if(c!=':'){
                  if(inqflag)
                  {
                     if(c!=',')
                     {
                   //    if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }else{
                     if(c ==',')
                     {
                  //     if((j<3)||((j>7) && (j<31)))
                       if (j == 0)
                       {
                          num = sb.toString();  
                       }
                       sb.append('|');
                       j++;
                     }else{
                 //      if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }
                }
             }
             if(isIndexList(num))
             {
                String toPrint = sb.toString();
                String prevPrint = (String)idxht.get(num);
                if(!toPrint.equals(prevPrint))
                {
   //                System.out.print(num+":");
   //                System.out.print(datestr+"|");
   //                System.out.println(toPrint);
                   StringBuffer sb2 = new StringBuffer();
                   sb2.append(datestr);
                   sb2.append("|");
                   sb2.append(toPrint);
                   fcli.sendMsg(num, toPrint);
//               Index_Rec ir = new Index_Rec(sb2.toString());
                   idxht.put(num,toPrint);
 //               idxht_data.put(num,ir);
                }
              }
    }
  }catch(Exception e2)
  {
      e2.printStackTrace();
  }
    return true;
  }
 */ 
  
  static void setupViewState() {
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
 try{                                            
    URL u = new URL("http://info512.taifex.com.tw/Future/FusaQuote_Norl.aspx");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/x-ms-application, application/x-ms-xbap, application/vnd.ms-xpsdocument, application/xaml+xml, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*"); 
    conn.setRequestProperty("Referer", "http://info512.taifex.com.tw/Future/FusaQuote_Norl.aspx"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; GTB7.5; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; InfoPath.1; .NET4.0C; .NET4.0E)"); 
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
    conn.setRequestProperty("Host", "info512.taifex.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setRequestProperty("Cache-Control", "no-cache"); 
    if(cookieValue != null)
    {
 //      System.out.println("cookieValue : "+cookieValue);
       conn.setRequestProperty("Cookie", cookieValue);
    }
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    conn.connect(); 
    Map<String, List<String>> headers = conn.getHeaderFields(); 
    List<String> values = headers.get("Set-Cookie"); 
    
    if(values.size() > 0)
    {
       cookieValue = null; 
       for (Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
          String v = iter.next(); 
          System.out.println(v);
          if (cookieValue == null)
              cookieValue = v;
          else
              cookieValue = cookieValue + ";" + v;
       }
    } 
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuffer sb = new StringBuffer();
    String s;
    while((s=br.readLine())!=null){
 //   	System.out.println(s);
    	if(s.indexOf("id=\"__VIEWSTATE\" value=\"") > 0)
    	{
    	    s = s.substring(s.indexOf("id=\"__VIEWSTATE\" value=\"")+24,s.length()-4);
          System.out.println(s);
          viewState = s;
    	}
    }
  }catch(Exception e2)
  {
  }
}
  
  static String getFutureData(String datestr,String txcode) {
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
 try{                       
//    setupViewState();
    URL u = new URL("http://info512.taifex.com.tw/Future/ImgChartDetail.ashx?type=1&contract="+txcode);
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/x-ms-application, application/x-ms-xbap, application/vnd.ms-xpsdocument, application/xaml+xml, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*"); 
    conn.setRequestProperty("Referer", "http://info512.taifex.com.tw/Future/FusaQuote_Norl.aspx"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; GTB7.5; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; InfoPath.1; .NET4.0C; .NET4.0E)"); 
    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
    conn.setRequestProperty("Host", "info512.taifex.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setRequestProperty("Cache-Control", "no-cache"); 
    if(cookieValue != null)
    {
   //    System.out.println("cookieValue : "+cookieValue);
       conn.setRequestProperty("Cookie", cookieValue);
    }
    conn.setReadTimeout(13000);
    conn.setConnectTimeout(13000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    String s;
    String s_open = null;
    String s_buy  = null;
    String s_sell = null;
    String s_high = null;
    String s_low  = null;
    String s_amp = null;
    String s_rp = null;
    String s_diff = null;
    String s_ra = null;
    String s_try_rp = null;
    String s_try_ra = null;
    String s_tra = null;
    String s_uncommit = null;
    String s_lclose = null;
    String s_buy_tcount = null;
    String s_buy_tra = null;
    String s_sell_tcount = null;
    String s_sell_tra = null;
    String s_time = null;
    String s_buy1_rp = null;
    String s_buy1_ra = null;
    String s_buy2_rp = null;
    String s_buy2_ra = null;
    String s_buy3_rp = null;
    String s_buy3_ra = null;
    String s_buy4_rp = null;
    String s_buy4_ra = null;
    String s_buy5_rp = null;
    String s_buy5_ra = null;
    String s_sell1_rp = "";
    String s_sell1_ra = "";
    String s_sell2_rp = "";
    String s_sell2_ra = "";
    String s_sell3_rp = "";
    String s_sell3_ra = "";
    String s_sell4_rp = "";
    String s_sell4_ra = "";
    String s_sell5_rp = "";
    String s_sell5_ra = "";
    String s_buyN_rp = "";
    String s_buyN_ra = "";
    String s_sellN_rp = "";
    String s_sellN_ra = "";
    
    boolean five_in = false;
    int five_state = 0;
    int five_count = 0;
    while((s=br.readLine())!=null){
  //  	System.out.println(s);
    	StringTokenizer st = new StringTokenizer(s.replaceAll(",",", "),",");
    	while(st.hasMoreTokens())
    	{
    	   if(five_in)
    	   {
            s=st.nextToken();
            s=s.trim();
            if(s.equals("最佳衍生一檔價量")) continue;
            switch(five_count)
            {
               case 0:
                 s_buy1_rp = s ;
                 break;
               case 1:
                 s_buy1_ra = s ;
                 break;
               case 2:
                 s_sell1_rp = s ;
                 break;
               case 3:
                 s_sell1_ra = s ;
                 break;
               case 4:
                 s_buy2_rp = s ;
                 break;
               case 5:
                 s_buy2_ra = s ;
                 break;
               case 6:
                 s_sell2_rp = s ;
                 break;
               case 7:
                 s_sell2_ra = s ;
                 break;
               case 8:
                 s_buy3_rp = s ;
                 break;
               case 9:
                 s_buy3_ra = s ;
                 break;
               case 10:
                 s_sell3_rp = s ;
                 break;
               case 11:
                 s_sell3_ra = s ;
                 break;
               case 12:
                 s_buy4_rp = s ;
                 break;
               case 13:
                 s_buy4_ra = s ;
                 break;
               case 14:
                 s_sell4_rp = s ;
                 break;
               case 15:
                 s_sell4_ra = s ;
                 break;
               case 16:
                 s_buy5_rp = s ;
                 break;
               case 17:
                 s_buy5_ra = s ;
                 break;
               case 18:
                 s_sell5_rp = s ;
                 break;
               case 19:
                 s_sell5_ra = s ;
                 break;
               case 20:
                 s_buyN_rp = s ;
                 break;
               case 21:
                 s_buyN_ra = s ;
                 break;
               case 22:
                 s_sellN_rp = s ;
                 break;
               case 23:
                 s_sellN_ra = s ;
                 break;
            }
            five_count ++;
    	   } else
    	   {
    	      s=st.nextToken();
    	      s=s.trim();
    	      if(s.equals("開盤"))
    	      {
    	         s_open = st.nextToken();
    	      }else if(s.equals("振幅"))
    	      {
    	         s_amp = st.nextToken();
    	      }else if(s.equals("買進"))
    	      {
    	         s_buy = st.nextToken();
    	      }else if(s.equals("最高"))
    	      {
    	         s_high = st.nextToken();
    	      }else if(s.equals("賣出"))
    	      {
    	         s_sell = st.nextToken();
    	      }else if(s.equals("最低"))
    	      {
    	         s_low = st.nextToken();
    	      }else if(s.equals("成交"))
    	      {
    	         s_rp = st.nextToken();
    	      }else if(s.equals("漲跌"))
    	      {
    	         s_diff = st.nextToken();
    	      }else if(s.equals("單量"))
    	      {
    	         s_ra = st.nextToken();
    	      }else if(s.equals("試撮價"))
    	      {
    	         s_try_rp = st.nextToken();
    	      }else if(s.equals("總量"))
    	      {
    	         s_tra = st.nextToken();
    	      }else if(s.equals("試撮量"))
    	      {
    	         s_try_ra = st.nextToken();
    	      }else if(s.equals("未平倉"))
    	      {
    	         s_uncommit = st.nextToken();
    	      }else if(s.equals("參考價"))
    	      {
    	         s_lclose = st.nextToken();
    	      }else if(s.equals("委買筆"))
    	      {
    	         s_buy_tcount = st.nextToken();
    	      }else if(s.equals("委賣筆"))
    	      {
    	         s_sell_tcount = st.nextToken();
    	      }else if(s.equals("委買口"))
    	      {
    	         s_buy_tra = st.nextToken();
    	      }else if(s.equals("委賣口"))
    	      {
    	         s_sell_tra = st.nextToken();
    	      }else if(s.equals("時間"))
    	      {
    	         s_time = st.nextToken();
    	      }else if(s.equals("委買價"))
    	      {
    	         five_state ++;
    	      }else if(s.equals("委買量"))
    	      {
    	         five_state ++;
    	      }else if(s.equals("委賣價"))
    	      {
    	         five_state ++;
    	      }else if(s.equals("委賣量"))
    	      {
    	         five_state ++;
    	      }
    	      if(five_state == 4)
    	      {
    	         five_in = true;
    	      }
    	   }
    	}
    }
    StringBuffer sb = new StringBuffer();
//    System.out.println("s_open = "+s_open);
    sb.append(s_open+"|");
//    System.out.println("s_buy  =  "+s_buy);
    sb.append(s_buy+"|");
//    System.out.println("s_sell =  "+s_sell);
    sb.append(s_sell+"|");
//    System.out.println("s_high =  "+s_high);
    sb.append(s_high+"|");
//    System.out.println("s_low  =  "+s_low);
    sb.append(s_low+"|");
//    System.out.println("s_amp =  "+s_amp);
    sb.append(s_amp+"|");
//    System.out.println("s_rp =  "+s_rp);
    sb.append(s_rp+"|");
//    System.out.println("s_diff =  "+s_diff);
    sb.append(s_diff+"|");
//    System.out.println("s_ra =  "+s_ra);
    sb.append(s_ra+"|");
//    System.out.println("s_try_rp =  "+s_try_rp);
    sb.append(s_try_rp+"|");
//    System.out.println("s_try_ra =  "+s_try_ra);
    sb.append(s_try_ra+"|");
//    System.out.println("s_tra =  "+s_tra);
    sb.append(s_tra+"|");
//    System.out.println("s_uncommit  ="+s_uncommit);
    sb.append(s_uncommit+"|");
//    System.out.println("s_lclose =  "+s_lclose);
    sb.append(s_lclose+"|");
//    System.out.println("s_buy_tcount =  "+s_buy_tcount);
    sb.append(s_buy_tcount+"|");
//    System.out.println("s_buy_tra =  "+s_buy_tra);
    sb.append(s_buy_tra+"|");
//    System.out.println("s_sell_tcount =  "+s_sell_tcount);
    sb.append(s_sell_tcount+"|");
//    System.out.println("s_sell_tra =  "+s_sell_tra);
    sb.append(s_sell_tra+"|");
//    System.out.println("s_time =  "+s_time);
    sb.append(s_time+"|");
//    System.out.println("s_buy1_rp =  "+s_buy1_rp);
    sb.append(s_buy1_rp+"|");
//    System.out.println("s_buy1_ra =  "+s_buy1_ra);
    sb.append(s_buy1_ra+"|");
//    System.out.println("s_buy2_rp =  "+s_buy2_rp);
    sb.append(s_buy2_rp+"|");
//    System.out.println("s_buy2_ra =  "+s_buy2_ra);
    sb.append(s_buy2_ra+"|");
//    System.out.println("s_buy3_rp =  "+s_buy3_rp);
    sb.append(s_buy3_rp+"|");
//    System.out.println("s_buy3_ra =  "+s_buy3_ra);
    sb.append(s_buy3_ra+"|");
//    System.out.println("s_buy4_rp =  "+s_buy4_rp);
    sb.append(s_buy4_rp+"|");
//    System.out.println("s_buy4_ra =  "+s_buy4_ra);
    sb.append(s_buy4_ra+"|");
//    System.out.println("s_buy5_rp =  "+s_buy5_rp);
    sb.append(s_buy5_rp+"|");
//    System.out.println("s_buy5_ra =  "+s_buy5_ra);
    sb.append(s_buy5_ra+"|");
//    System.out.println("s_sell1_rp =  "+s_sell1_rp);
    sb.append(s_sell1_rp+"|");
//    System.out.println("s_sell1_ra =  "+s_sell1_ra);
    sb.append(s_sell1_ra+"|");
//    System.out.println("s_sell2_rp =  "+s_sell2_rp);
    sb.append(s_sell2_rp+"|");
//    System.out.println("s_sell2_ra =  "+s_sell2_ra);
    sb.append(s_sell2_ra+"|");
//    System.out.println("s_sell3_rp =  "+s_sell3_rp);
    sb.append(s_sell3_rp+"|");
//    System.out.println("s_sell3_ra =  "+s_sell3_ra);
    sb.append(s_sell3_ra+"|");
//    System.out.println("s_sell4_rp =  "+s_sell4_rp);
    sb.append(s_sell4_rp+"|");
//    System.out.println("s_sell4_ra =  "+s_sell4_ra);
    sb.append(s_sell4_ra+"|");
//    System.out.println("s_sell5_rp =  "+s_sell5_rp);
    sb.append(s_sell5_rp+"|");
//    System.out.println("s_sell5_ra =  "+s_sell5_ra);
    sb.append(s_sell5_ra+"|");
//    System.out.println("s_buyN_rp =  "+s_buyN_rp);
    sb.append(s_buyN_rp+"|");
//    System.out.println("s_buyN_ra =  "+s_buyN_ra);
    sb.append(s_buyN_ra+"|");
//    System.out.println("s_sellN_rp =  "+s_sellN_rp);
    sb.append(s_sellN_rp+"|");
//    System.out.println("s_sellN_ra =  "+s_sellN_ra);
    sb.append(s_sellN_ra+"|");
    String data = sb.toString().replaceAll(" ","");
    System.out.println("data length:"+data.length());
//    System.out.println(txcode+"-->"+data);
    fcli.sendMsg(txcode, data);
  }catch(Exception e2)
  {
  	 e2.printStackTrace();
  }
    return null;
  }
  

  static boolean getWData(String datestr,String num) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL(u1,"https://tw.stock.yahoo.com/us/q?s="+num);
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://tw.stock.yahoo.com/us/q?s="+num); 
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
    boolean sf = false;
    String workdate;
    while((s=br.readLine())!=null){
 //       	  System.out.println(i+" "+s);
 /*
       if(s.indexOf("資料日期") >=0)
       {
           workdate = s.substring(s.indexOf("資料日期")+5,s.indexOf("</td>"));
           StringTokenizer st = new  StringTokenizer(workdate,"/");
           Date ddd = new Date();
           int m = Integer.parseInt("1"+st.nextToken())%100;
           int d = Integer.parseInt("1"+st.nextToken())%100;
           int y = Integer.parseInt(st.nextToken());
           if(d != ddd.getDate())
           {
              return true;
           }
           //System.out.println(workdate);
       }*/
       if((s.indexOf("開盤</td>") >=0) || (s.indexOf("總市值</td>") >=0))
       {
          sf = true;
       } 
       if(sf)
       {
         if(s.indexOf("</td>") > 0)
         {
           if(i==2)
            { 
            	  int idx = s.indexOf("<font")+6;
            	  s=s.substring(idx);
                sRp =s.substring(s.indexOf(">")+1,s.indexOf("<"));
            }
            if(i==5)
            { 
            	  int idx = s.indexOf("<font")+6;
            	  s=s.substring(idx);
                sDiff =s.substring(s.indexOf(">")+1,s.indexOf("<"));
            }
            if(i==6)
            { 
                s = s.substring(2);
                sRa =s.substring(s.indexOf(">")+1,s.indexOf("<"));
            }
            if(i==7)
            { 
                s = s.substring(2);
                sPclose =s.substring(s.indexOf(">")+1,s.indexOf("<"));
            }
            if(i==8)
            { 
                s = s.substring(2);
                sOpen =s.substring(s.indexOf(">")+1,s.indexOf("<"));
            }
            if(i==10)
            { 
                s = s.substring(2);
                sHigh =s.substring(s.indexOf(">")+1,s.indexOf("<"));
            }
            if(i==11)
            { 
                s = s.substring(2);
                sLow =s.substring(s.indexOf(">")+1,s.indexOf("<"));
            }
            i++;
         }
         if((s.indexOf("<tr align=center bgcolor=#fff0c1>") >=0) || (s.indexOf("</table>") >=0))
         {
              sf = false;
         }
       }  
    }
    s = num+"|"+datestr+"|0|"+sOpen+"|"+sHigh+"|"+sLow+"|"+sRp+"|"+sRa+"|"+sRa+"|";
    s = s.replaceAll(",","");
    System.out.println(s);
    fcli.sendMsg(num, s);
    return true;
  }

//  static Future_Rec getFutureData(String datestr) {
//    boolean startflag = false;
//    boolean trflag = false;
//    int count = 0;
// try{                       
////    setupViewState();
//    URL u = new URL("http://info512.taifex.com.tw/Future/FusaQuote_Norl.aspx");
//    URLConnection conn = u.openConnection(); 
//    conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/x-ms-application, application/x-ms-xbap, application/vnd.ms-xpsdocument, application/xaml+xml, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*"); 
//    conn.setRequestProperty("Referer", "http://info512.taifex.com.tw/Future/FusaQuote_Norl.aspx"); 
//    conn.setRequestProperty("Accept-Language", "zh-tw"); 
//    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; GTB7.5; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; InfoPath.1; .NET4.0C; .NET4.0E)"); 
//    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
//    conn.setRequestProperty("Host", "info512.taifex.com.tw"); 
//    conn.setRequestProperty("Connection", "Keep-Alive"); 
//    conn.setRequestProperty("Cache-Control", "no-cache"); 
//    if(cookieValue != null)
//    {
//       System.out.println("cookieValue : "+cookieValue);
//      conn.setRequestProperty("Cookie", cookieValue);
//    }
//    conn.setReadTimeout(19000);
//    conn.setConnectTimeout(19000);
//    conn.setDoOutput(true);
//    OutputStream os = conn.getOutputStream();
//    os.write((postData+viewState).getBytes());
//    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//    StringBuffer sb = new StringBuffer();
//    String s;
//    Base64 base64 = new Base64();
//    System.out.println("data in");
//    while((s=br.readLine())!=null){
//    	if(s.indexOf("8,889") >= 0)
//    	  System.out.println(s.replaceAll("<>",""));
//    	if(s.indexOf("id=\"__VIEWSTATE\" value=\"") > 0)
//    	{
//    	    s = s.substring(s.indexOf("id=\"__VIEWSTATE\" value=\"")+24,s.length()-4);
//          viewState = s;
//           System.out.println("viewState" + viewState);
//    	}
//    }
//  }catch(Exception e2)
//  {
//  }
//    return null;
//  }
/*
  static void setupReport(Enumeration e)
  {
   	  while(e.hasMoreElements())
   	  {
   	  	  Try_Rec tr = (Try_Rec)e.nextElement();
          if(tr instanceof Stock_Rec)
          {
             Stock_Rec sr = (Stock_Rec)tr;
             Stock_Report srpt = (Stock_Report)StockReportPool.get(sr.snum);
             if(srpt == null) {
                srpt = new Stock_Report(sr.snum);
                StockReportPool.put(sr.snum, srpt);
             }
             srpt.add(sr);
          } else if(tr instanceof Index_Rec)
          {
             Index_Rec ir = (Index_Rec)tr;
             Index_Report irpt = (Index_Report)IndexReportPool.get(ir.num);
             if(irpt == null) {
                irpt = new Index_Report(ir.num);
                IndexReportPool.put(ir.num, irpt);
             }
             irpt.add(ir);
          
          } else if(tr instanceof Future_Rec)
          {
             Future_Rec fr = (Future_Rec)tr;
             if (futureReport == null)
             {
                futureReport = new Future_Report();
             }
             futureReport.add(fr);
          } 
   	  }      
  }
  */
/*
  static void culReport1(Future_Rec fr)
  {
  	  Enumeration e = ht_data.elements();
  	  float preVal = 0;
  	  float curVal = 0;
  	  while(e.hasMoreElements())
  	  {
  	     Stock_Rec sr = (Stock_Rec) e.nextElement();
  	     Stock_Ds0Rec  sr0  =  (Stock_Ds0Rec)snumht.get(sr.snum);
  	     preVal += sr0.value;
  	     curVal += sr.rp * sr0.capital;
  	  }
  	  Index_Rec ir= (Index_Rec)idxht_data.get("1");
  	  float preIdx = ir.value-ir.change;
  	  float preFuIdx = fr.price - fr.change;
  	  float stockChane = ((curVal-preVal)/preVal);
  	  System.err.println("Stock Diff:"+(stockChane*preIdx)+" ..<"+(stockChane*100)+"% ");
  	  System.err.println("Idx Diff:"+ ir.change +" ..<"+((ir.change)/preIdx*100)+"% ");
  	  System.err.println("preFuIdx Diff:"+ fr.change +" ..<"+((fr.change)/preFuIdx*100)+"% ");
  }
*/
 }