import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage4 {
static URL u1 ; 
static String cookieValue;


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
//  BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
  Ds000_Rec dsr = new Ds000_Rec();
  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr where snum > '2533' and tcode='1' order by snum;");
//  String stockN = null;
//  getData("2454",arg[0].substring(0,2),arg[0].substring(2,4));
//    Date d = new Date(105,0,1);
//    Date d2 = new Date();
//    long d2long = d2.getTime();
//    while(d.getTime() < d2long){
//      String ds = String.valueOf((1900+d.getYear())*10000+(d.getMonth()+1)*100+d.getDate());
  while(e.hasMoreElements()){
      dsr = (Ds000_Rec)e.nextElement();

        String snum = dsr.snum; 
      System.err.println(snum);
      int x = 0;
      for(int year=2010; year < 2011; year++)
      {
        for(int q = 1; q < 13; q++)
        {
//        	 String ds = String.valueOf(year*100+q);
//        	 String ds = String.valueOf(year);
        	 x =(x+1) % 10 ;
        	 if(x == 9)
        	 {
         	     System.err.println("auto waiting");
//               Thread.currentThread().sleep(15000);
        	 }
        	 for( int i = 0; i < 20; i++)
        	 {
              try{
     	           while(!getData(snum,year-1911, q,i)){
     	              i++;
     	              if(i > 20 ) break;
     	           }
   	             i = 501;
             //    Thread.currentThread().sleep(1000);
              }catch(Exception e3){
                // e3.printStackTrace();
                if(e3 instanceof java.io.FileNotFoundException)
                 {
                   break;
                 }else
                 {
                  	e3.printStackTrace(System.err);
                 }
                 Thread.currentThread().sleep(i*1000);
                 //i = 50;
                 if(i>15)
                 {
         	          throw new Exception("Network not avaible ! restart ");
                 }
              }
           }
        }
      } 
   } 

  }
 

  static boolean getData(String snum, int y, int m,int wt) throws Exception{
//  http://mopsov.tse.com.tw/server-java/stapap1?TYPEK=otc&colorchg=1&isnew=false&id=1742&yearmonth=9901&step=1&                  
    URL u = new URL("http://mopsov.tse.com.tw/server-java/stapap1?TYPEK=sii&colorchg=1&isnew=false&id="+snum+"&yearmonth="+(y*100+m)+"&step=1&");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://www.twse.com.tw/"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "www.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
     
    String s = null;
    int state = -1;
    boolean  startflag = false;
    Hashtable ht = new Hashtable();
    while((s=br.readLine())!=null)
    {
//         System.out.println(s);
         if(s.indexOf("系統流量大") >0)
         {
         	   System.err.println("waiting");
         	   Thread.currentThread().sleep(5000*wt);
             return false;
         }else if(s.indexOf("COLGROUP") >= 0)
         {
            state = 2;
         } else if(s.indexOf("設質比例") >= 0 && state == -1)
         {
         	  state = 1;
         } else if((state == 1) && (s.indexOf("<TD>") >= 0))
         {
         	  s = s.substring(s.indexOf("TD>")+3);
            String data = s.substring(0,s.indexOf("<"));
            int type = 4;
            if(data.indexOf("董事")>=0)
            {
               type = 1;
            } else if(data.indexOf("監察")>=0)
            {
               type = 2;
            } 
            
            s = s.substring(s.indexOf(">")+1);
            String name = s.substring(0,s.indexOf("<"));
            name=name.replaceAll(",","");
            name=name.replaceAll("&nbsp;","");
            name = name.trim();
            
            String preT = (String)ht.get(name);
            if(preT!=null)
            {
               type += (int)preT.charAt(10)-0x30;
            }
            s = s.substring(s.indexOf(">")+1);
            String stock1 = s.substring(0,s.indexOf("<"));
            stock1=stock1.replaceAll(",","");
            stock1=stock1.replaceAll("&nbsp;","");
            stock1 = stock1.trim();
            
            s = s.substring(s.indexOf(">")+1);
            String stock2 = s.substring(0,s.indexOf("<"));
            stock2=stock2.replaceAll(",","");
            stock2=stock2.replaceAll("&nbsp;","");
            stock2 = stock2.trim();

            s = s.substring(s.indexOf(">")+1);
            String stock3 = s.substring(0,s.indexOf("<"));
            stock3=stock3.replaceAll(",","");
            stock3=stock3.replaceAll("&nbsp;","");
            stock3 = stock3.trim();
            
             s = s.substring(s.indexOf(">")+1);
            String stock4 = s.substring(0,s.indexOf("<"));
            stock4=stock4.replaceAll(",","");
            stock4=stock4.replaceAll("&nbsp;","");
            stock4 = stock4.trim();
            
            s = s.substring(s.indexOf(">")+1);
            String stock22 = s.substring(0,s.indexOf("<"));
            stock22=stock22.replaceAll(",","");
            stock22=stock22.replaceAll("&nbsp;","");
            stock22 = stock22.trim();

            s = s.substring(s.indexOf(">")+1);
            String stock23 = s.substring(0,s.indexOf("<"));
            stock23=stock23.replaceAll(",","");
            stock23=stock23.replaceAll("&nbsp;","");
            stock23 = stock23.trim();
            
             s = s.substring(s.indexOf(">")+1);
            String stock24 = s.substring(0,s.indexOf("<"));
            stock24=stock24.replaceAll(",","");
            stock24=stock24.replaceAll("&nbsp;","");
            stock24 = stock24.trim();
            String fdata = snum+"|"+y+"|"+m+"|"+type+"|"+name+"|"+stock1+"|"+(Long.parseLong(stock2)+Long.parseLong(stock22))+
                            "|"+(Long.parseLong(stock3)+Long.parseLong(stock23))+"|";
            ht.put(name,fdata);
           // System.out.println(fdata);
         }
    }
    Enumeration e = ht.elements();
    while(e.hasMoreElements())
    {
       System.out.println(e.nextElement());	
    }
    return true;
  }
}
