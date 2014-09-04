import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage2 {
static URL u1 ; 
static String cookieValue;

static boolean data_ok;
 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
//    Date d = new Date(105,0,1);
//    Date d2 = new Date();
//    long d2long = d2.getTime();
//    while(d.getTime() < d2long){
//      String ds = String.valueOf((1900+d.getYear())*10000+(d.getMonth()+1)*100+d.getDate());
//      System.err.println(ds);
      data_ok = false;
      for(int i = 0 ;i < 20;i++){
        try{
//     	     while(!getData(ds)){
     	     while(!getData(arg[0])){
     	       i++;
     	       if(i > 20 ) break;
     	     }
   	       i = 501;
        }catch(Exception e3){
          Thread.currentThread().sleep(i*1000);
          if(i>15)
          {
         	   throw new Exception("Network not avaible ! restart ");
           }
        }
      } 
      if(data_ok == false)
      {
         throw new Exception("Data fetch Fail ");
      }
//      d = new Date(d.getTime()+86400000);
//    } 

  }
 

  static boolean getData(String Dstr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL("http://www.twse.com.tw/ch/trading/exchange/MI_MARGN/MI_MARGN3_2.php?select2=&input_date="+Dstr.substring(0,4)+"/"+Dstr.substring(4,6)+"/"+Dstr.substring(6,8)+"&type=csv");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://www.twse.com.tw/"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "www.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuffer buy = new StringBuffer();
    StringBuffer sell = new StringBuffer();
    String s;
    String sBuy ="0";
    String sSell="0";
    String sTotal = "0" ;
    boolean start = false;
    StringBuffer sb = new StringBuffer();
    while((s=br.readLine())!=null){
         if(s.indexOf("1101,") == 0)
         {
         	  start = true;
         }
         if(s.indexOf(',') < 0)
         {
         	  start = false;
         }
         if(start)
         { 
             data_ok = true;
            System.out.print(Dstr+"|");
             boolean inqflag = false;
             int j = 0;
             for(int i = 0; i < s.length(); i++)
             {
                char c = s.charAt(i);
                if( c == '"')
                {
                  inqflag = !inqflag;
                } else {
                  if(inqflag)
                  {
                     if(c!=',')
                     {
                       if((j!=1)&&(j!=15))
                        System.out.print(c);
                     }
                  }else{
                     if(c ==',')
                     {
                        if((j!=1)&&(j!=15))
                       System.out.print('|');
                       j++;
                     }else{
                       if((j!=1)&&(j!=15))
                        System.out.print(c);
                     }
                  }
                }
             }
              System.out.println();
         }
      
    }
    return true;
  }
}
