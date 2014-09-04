import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GetPage {
static URL u1 ; 
static Enumeration e;
static String DateStr;
static Hashtable ht;
static boolean printflag = true;
 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
//  BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
  Ds000_Rec dsr = new Ds000_Rec();
  e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr where tcode = '"+arg[1]+"' and ccode != 29 order by snum;");
  String stockN = null;
  DateStr = arg[0];
  u1 = new URL("http://mis.twse.com.tw/stock_best5.html?stockId=1101");
  String s;
  ht = new Hashtable();
 // BufferedReader br = new BufferedReader(new InputStreamReader(u1.openConnection().getInputStream()));
 // while((s=br.readLine())!=null);
 
  Vector v = new Vector();
 while(e.hasMoreElements())
 {
//   dsr = (Ds000_Rec)e.nextElement();   
//   System.out.println(dsr.dump());
   v.add(e.nextElement());
 }
 e = v.elements();   
// Thread  t1 =new Thread( new PageRun());
// Thread  t2 =new Thread( new PageRun());
// Thread  t3 =new Thread( new PageRun());
// Thread  t4 =new Thread( new PageRun());
for(;;)
{
  Date d = new Date();
  System.err.println("Start time: "+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds()));
  if(((d.getHours() == 13) && (d.getMinutes() > 30)&&(!printflag)) || (d.getHours() > 13))
  {
      return;
  }
  printflag = false;
  while(e.hasMoreElements())
  {
     dsr = (Ds000_Rec)e.nextElement();
      for(int i = 0 ;i < 20;i++){
         try{
     	      while(!getData(dsr.snum,DateStr)){
     	        i++;
     	        if(i > 20 ) break;
            }
   	        i = 501;
          }catch(Exception e3){
           if(e3 instanceof FileNotFoundException)
           {
               break;
           }
           if (i>15)
           {
             //  System.err.println("Error"+i);
             //  (new DataInputStream(System.in)).readLine();
             //  System.err.println("Ok ..go!"+i);
             //  i = 0; 
             //  }else{
             // //e3.printStackTrace();
              System.err.println("Error Sleep"+i);
              try{
                Thread.currentThread().sleep(500);
              }catch(java.lang.InterruptedException lie)
              {
              }
           }
          }
       }
  }
  Date d2 = new Date();
  System.err.println("End time  :"+(d2.getHours())+":"+(d2.getMinutes())+":"+(d2.getSeconds())+" cost time:"+(d2.getTime()-d.getTime()));
  e = v.elements();
}



// t1.run();
// t2.run();
// t3.run();
// t4.run();

 }
 

  static boolean getData(String num,String datestr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL(u1,"/data/"+num+".csv?ID=111111111");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://mis.twse.com.tw/stock_best5.html?stockId="+num+"&Refresh=1"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "mis.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuffer sb = new StringBuffer();
    String s;
    while((s=br.readLine())!=null){
//    	 System.out.println(s);
           sb.append(datestr+"|");
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
                       if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }else{
                     if(c ==',')
                     {
                       if((j<3)||((j>7) && (j<31)))
                       sb.append('|');
                       j++;
                     }else{
                       if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }
                }
             }
             String toPrint = sb.toString();
             String prevPrint = (String)ht.get(num);
             if(!toPrint.equals(prevPrint))
             {
                System.out.println(toPrint);
                printflag = true;
                ht.put(num,toPrint);
             }
    }
    return true;
  }
  static void dataRun()
  {
      Ds000_Rec dsr = (Ds000_Rec)e.nextElement();
      for(int i = 0 ;i < 20;i++){
         try{
     	      while(!getData(dsr.snum,DateStr)){
     	        i++;
     	        if(i > 20 ) break;
            }
   	        i = 501;
          }catch(Exception e3){
           if(e3 instanceof FileNotFoundException)
           {
               break;
           }
           if (i>15)
           {
             //  System.err.println("Error"+i);
             //  (new DataInputStream(System.in)).readLine();
             //  System.err.println("Ok ..go!"+i);
             //  i = 0; 
             //  }else{
             // //e3.printStackTrace();
              System.err.println("Error Sleep"+i);
              try{
                Thread.currentThread().sleep(500);
              }catch(java.lang.InterruptedException lie)
              {
              }
           }
          }
       }

  }
  /*
 static class PageRun implements Runnable {
         PageRun() {
         }
 
         public void run() {
           for(;;){
  	          if(e.hasMoreElements())
  	          {
               }else{
                  break;
               }  
           }
            System.err.println("End time  :"+(new Date().getTime()));
         }

   }
*/
}