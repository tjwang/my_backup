import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class LoadAllData {
static URL u1 ; 


 static public void main(String[] arg)throws Exception{
      DBConnection.debug = false;
     	int i = 0;
     	Date d = new Date(109,0,1);
     	Date today = new Date();
     	while(d.getTime() < today.getTime())
     	{
     	   String dates = GMethod.d2s(d);
     	   System.err.println(dates);
     	   while(!getData(dates.substring(0,6),dates)){
     	      i++;
     	      if(i > 20 ) break;
     	   }
     	   i = 0;
     	   while(!getDataOTC(dates)){
     	       i++;
     	       if(i > 20 ) break;
     	   }
     	   d = new Date(d.getTime()+86400000);
     	}
     	
   }  


  static boolean getData(String YMstr,String Dstr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL("http://www.twse.com.tw/ch/trading/exchange/MI_INDEX/MI_INDEX3_print.php?genpage=genpage/Report"+
                         YMstr+"/A112"+Dstr+ "ALLBUT0999_1.php&type=csv");
//    URL u = new URL("http://www.twse.com.tw/ch/trading/exchange/STOCK_DAY/genpage/Report201001/201001_F3_1_8_2454.php?STK_NO="+
//                     num+"&myear=2011&mmon="+Mstr);
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://www.twse.com.tw/"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "www.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    boolean start_flag = false;
    String s = "";
    while((s=br.readLine())!=null){
      
      if(s.indexOf("1101,") == 0)
      {
      	 start_flag = true;
      }
      if(s.indexOf(",") < 0)
      {
      	 start_flag = false;
      }
      if(start_flag)
      {
          csvPrint(Dstr,s);
      }
    }
    return true;
  }
  static void csvPrint(String ds,String s)
  {
      StringTokenizer st = new StringTokenizer(s,",");
      boolean bflag= false;
      int field_idx = 0;
      StringBuffer volsb = null;
      while(st.hasMoreTokens())
      {
         String token = st.nextToken();
         if(token.charAt(0) == '"')
         {
            bflag = true;
            token = token.substring(1);
         }
         if(token.charAt(token.length()-1) == '"')
         {
            bflag = false;
            token = token.substring(0,token.length()-1);
         }
         
         if(field_idx == 0 && token.length()!=4)
         {
            return ;
         }else  if(field_idx == 1) 
         {
            field_idx++;
            continue;
         }else  if(field_idx == 2 && bflag == false) 
         {
            if(volsb == null) volsb = new StringBuffer();
            volsb.append(token);
            System.out.print(Integer.parseInt(volsb.toString())/1000);
            System.out.print("|");
            field_idx++;
            volsb = null;
            continue;
         }else  if(field_idx == 2 && bflag == true) 
         {
            if(volsb == null) volsb = new StringBuffer();
            volsb.append(token);
            continue;
         } else  if(field_idx == 4 && bflag == true) 
         {
            if(volsb == null) volsb = new StringBuffer();
            volsb.append(token);
            continue;
         } else if (field_idx == 4 && bflag == false)
         {
            if(volsb == null) 
            {
               System.out.print("0|");
            }else
            {
               System.out.print(Integer.parseInt(volsb.toString()));
               System.out.print("|");
            }
            field_idx++;
            volsb = null;
            continue;
         }else if(field_idx == 9)
         {
            if(token.charAt(0) == '¡Ð')
            {
               System.out.print("-");
            } else if(token.charAt(0) == '0')
            {
               System.out.print(token+"|");
               field_idx++;
            }
            field_idx++;
            continue;
         }  
           
         System.out.print(token);
         if(field_idx == 0)
         {
            System.out.print("|"+ds+"|0");
         }
         if(!bflag)
         {
             System.out.print("|");
             field_idx++;
         }
      }
      System.out.println();
  }

  static boolean getDataOTC(String Dstr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    URL u = new URL("http://www.otc.org.tw/ch/stock/aftertrading/otc_quotes_no1430/stk_wn1430_download.php?d="+
               (Integer.parseInt(Dstr.substring(0,4))-1911)+
               "/"+Dstr.substring(4,6)+"/"+Dstr.substring(6,8)+"&se=AL&s=0,asc,0");
//    URL u = new URL("http://www.twse.com.tw/ch/trading/exchange/STOCK_DAY/genpage/Report201001/201001_F3_1_8_2454.php?STK_NO="+
//                     num+"&myear=2011&mmon="+Mstr);
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://www.twse.com.tw/"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "www.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    //conn.setRequestProperty("Cookie", "B=fftihip43671r&b=3&s=0s;"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    boolean start_flag = false;
    String s = "";
    while((s=br.readLine())!=null){
      if(s.indexOf(",") < 0)
      {
      	 start_flag = false;
      }
      if(start_flag)
      {
        //  System.out.println(s);
          csvPrint2(Dstr,s);
      }
      if(s.indexOf("\"") == 0)
      {
      	 start_flag = true;
      }
    }
    return true;
  }
  static void csvPrint2(String ds,String s)
  {
      StringTokenizer st = new StringTokenizer(s,",");
      boolean bflag= false;
      int field_idx = 0;
      StringBuffer volsb = null;
      
      String snum = null;
      String popen = null;
      String pclose = null;
      String diff = null;
      String high = null;
      String low  = null;
      String ra = null;
      String money = null;
      String rac = null;
      String pbuy = null;
      String psell = null;
      while(st.hasMoreTokens())
      {
         String token = st.nextToken();
         if(token.charAt(0) == '"')
         {
            bflag = true;
            token = token.substring(1);
         }
         if(token.charAt(token.length()-1) == '"')
         {
            bflag = false;
            token = token.substring(0,token.length()-1);
         }
         
         if(field_idx == 0 && token.length()!=4)
         {
            return ;
         }else  if(field_idx == 1) 
         {
            field_idx++;
            continue;
         }else  if(field_idx == 7 && bflag == false) 
         {
            if(volsb == null) volsb = new StringBuffer();
            volsb.append(token);
            ra = String.valueOf(Integer.parseInt(volsb.toString())/1000);
            field_idx++;
            volsb = null;
            continue;
         }else  if(field_idx == 7 && bflag == true) 
         {
            if(volsb == null) volsb = new StringBuffer();
            volsb.append(token);
            continue;
         } else  if(field_idx == 8 && bflag == true) 
         {
            if(volsb == null) volsb = new StringBuffer();
            volsb.append(token);
            continue;
         } else if (field_idx == 8 && bflag == false)
         {
            if(volsb == null) 
            {
               money = "0";
            }else
            {
               money = String.valueOf(Integer.parseInt(volsb.toString()));
            }
            field_idx++;
            volsb = null;
            continue;
         } else  if(field_idx == 9 && bflag == false) 
         {
            if(volsb == null) volsb = new StringBuffer();
            volsb.append(token);
            rac = String.valueOf(Integer.parseInt(volsb.toString()));
            field_idx++;
            volsb = null;
            continue;
         }else  if(field_idx == 9 && bflag == true) 
         {
            if(volsb == null) volsb = new StringBuffer();
            volsb.append(token);
            continue;
         } 
         
         if(!bflag)
         {
             if(field_idx == 0)
             {
                snum = token;
             } else if(field_idx == 2)
             {
                pclose = token;
             } else if(field_idx == 3)
             {
                diff = token;
             }else if(field_idx == 4)
             {
                popen = token;
             }else if(field_idx == 5)
             {
                high = token;
             }else if(field_idx == 6)
             {
                low = token;
             }else if(field_idx == 10)
             {
                pbuy = token;
             }else if(field_idx == 11)
             {
                psell = token;
             }
             field_idx++;
         }
      }
      System.out.println(snum+"|"+ds+"|0|"+ra+"|"+rac+"|"+money+"|"+popen+"|"+high+"|"+low+"|"+pclose+"|"+diff.replaceAll(" ","")+"|"+pbuy+"|0|"+psell+"|0|0|");
  }

  
}
