import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage3 {
static URL u1 ; 
static String cookieValue;


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
//  BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
  Ds000_Rec dsr = new Ds000_Rec();
  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr where tcode='1' order by snum;");
  String stockN = null;
//  getData("2454",arg[0].substring(0,2),arg[0].substring(2,4));
  
  int StartY = Integer.parseInt(arg[0])/100;
  int StartM =Integer.parseInt(arg[0])%100;
  int EndY = Integer.parseInt(arg[1])/100;
  int EndM =Integer.parseInt(arg[1])%100;
  while(e.hasMoreElements())
  {
     dsr = (Ds000_Rec)e.nextElement();
      System.err.println(dsr.snum);
     for(int y=StartY;y<=EndY; y++)
     {
   	    for(int m=1;m<=12;m++)
   	    {
            if((y == StartY) && (m < StartM))
            {
            	 m = StartM ;
           	}
            if((y == EndY) && (m > EndM))
            {
           	   break;
           	}
            for(int i = 0 ;i < 20;i++)
            {
                try
                {
     	            while(!getData(dsr.snum,y,m)){
     	              i++;
     	              if(i > 20 ) break;
       	           }
     	             i = 501;
                }catch(Exception e3){
                   Thread.currentThread().sleep(i*1000);
                   e3.printStackTrace(System.err);
                }
            }
        }
     }
  }  
}  
 

  static boolean getData(String num,int Ystr,int Mstr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    System.err.println("http://www.twse.com.tw/ch/trading/exchange/STOCK_DAY/STOCK_DAY_print.php?genpage=genpage/Report"+(Ystr*100+Mstr)+
                     "/"+(Ystr*100+Mstr)+"_F3_1_8_"+num+".php&type=csv");
    URL u = new URL("http://www.twse.com.tw/ch/trading/exchange/STOCK_DAY/STOCK_DAY_print.php?genpage=genpage/Report"+(Ystr*100+Mstr)+
                     "/"+(Ystr*100+Mstr)+"_F3_1_8_"+num+".php&type=csv");
//    URL u = new URL("http://www.twse.com.tw/ch/trading/exchange/STOCK_DAY/genpage/Report201001/201001_F3_1_8_2454.php?STK_NO="+
//                     num+"&myear=2010&mmon="+Mstr);
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
    String s = null;
    while((s=br.readLine())!=null){
      if(s.indexOf("/") > 0)
      {
       //    System.out.println(s);
           char[] data = s.toCharArray();
           boolean Qstarted = false;
           int j = 0;
           String sDate = null;
           String sTime = "0000";
           String sRa ="0";
           String sRaM="0";
           String sRaC = "0";
           String sDiff = "0";
           String sOpen = "0" ;
           String sHigh = "0" ;
           String sLow = "0" ;
           String sClose = "0" ;
           StringBuffer sb = new StringBuffer();
           for(int i=0;i<data.length;i++)
           {
               if(data[i] == ',')
               {
                  if(!Qstarted)
                  {
        //             System.out.println(sb.toString());
                     if(j == 0)
                     {
                        sDate = sb.toString();
                        sDate = String.valueOf(Ystr*100+Mstr)+sDate.trim().substring(6,8);
                     }
                     if(j == 1)
                     {
                        sRa = String.valueOf(Integer.parseInt(sb.toString())/1000);
                     }
                     if(j == 2)
                     {
                        sRaM =sb.toString();
                     }
                     if(j == 3)
                     {
                        sOpen =sb.toString();
                     }
                     if(j == 4)
                     {
                        sHigh =sb.toString();
                     }
                     if(j == 5)
                     {
                        sLow =sb.toString();
                     }
                     if(j == 6)
                     {
                        sClose =sb.toString();
                     }
                     if(j == 7)
                     {
                        sDiff =sb.toString();
                     }
                     if(j == 8)
                     {
                        sRaC =sb.toString();
                     }
                     sb = new StringBuffer();
                     j++;
                  }
                  continue;
               }  
               if(data[i] == '"')
               {
                  Qstarted  = !Qstarted;
                  continue;
               }  
               sb.append(data[i]);
           }
           sRaC =sb.toString();
          System.out.println(num+"|"+sDate+"|"+sTime+"|"+sOpen+"|"+sHigh+"|"+sLow+"|"+sClose+"|"+sRa+"|"+sRaM+"|"+sRaC+"|"+sDiff+"|");
      }
      
    }
    return true;
  }
}
