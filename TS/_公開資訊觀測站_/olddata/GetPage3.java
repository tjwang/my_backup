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
  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr where   tcode='2' order by snum;");
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
      for(int year=2010; year < 2013; year++)
      {
     //   for(int q = 1; q < 5; q++)
        {
 //       	 String ds = String.valueOf(year*100+q);
        	 String ds = String.valueOf(year);
        	 x =(x+1) % 10 ;
        	 if(x == 9)
        	 {
         	     System.err.println("auto waiting");
//               Thread.currentThread().sleep(15000);
        	 }
        	 for( int i = 0; i < 20; i++)
        	 {
              try{
     	           while(!getData(snum,year-1911,i)){
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
 

  static boolean getData(String snum, int year,int wt) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
//    URL u = new URL("http://mopsov.tse.com.tw/server-java/t05st30?colorchg=1&off=1&TYPEK=sii&isnew=false&year="+year+"&co_id="+snum+"&");
    URL u = new URL("http://mopsov.tse.com.tw/server-java/t05st30?colorchg=1&off=1&TYPEK=otc&isnew=false&year="+year+"&co_id="+snum+"&");
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
    int[] workIn = new int[4];
    int[] workCost =new int[4];
    
    int[] workProfit=new int[4];
    int[] workfee=new int[4];
    int[] workOpProfit=new int[4];
    
    int[] outInPro =new int[4];
    int[] outFee=new int[4];

    int[] tProfit=new int[4];
    float[] eps=new float[4];
    for(int i = 0; i < 4;i++)
    {
       workIn[i]=0;
       workCost[i]=0;
       workProfit[i]=0;
       workfee[i]=0;
       workOpProfit[i]=0;
       outInPro[i]=0;
       outFee[i]=0;
       tProfit[i]=0;
       eps[i]=0;
    }
     
    String s = null;
    int state = -1;
    int data[] = null;
    while((s=br.readLine())!=null){
//         System.out.println(s);
         if(s.indexOf("系統流量大") >0)
         {
         	   System.err.println("waiting");
         	   Thread.currentThread().sleep(5000*wt);
             return false;
         }else if(s.indexOf("營業收入") > 0)
         {
         	  state = 1;
         	  data = workIn;
         } else if(s.indexOf("營業成本") > 0)
         {
         	  state = 2;
         	  data = workCost;
         }else if(s.indexOf("營業毛利") > 0)
         {
            state = 3;
         	  data = workProfit;
         } else if(s.indexOf("營業費用") > 0)
         {
            state = 4;        
         	  data = workfee;
         } else if(s.indexOf("營業淨利") > 0)
         {
            state = 5;        
         	  data = workOpProfit;
         } else if(s.indexOf("營業利益") > 0)
         {
            state = 5;     
         	  data = workOpProfit;
        } else if(s.indexOf("營業外收入") > 0)
         {
            state = 6;
         	  data = outInPro;
         } else if(s.indexOf("營業外費用") > 0)
         {
            state = 7;
         	  data = outFee;
         } else if(s.indexOf("稅後純益") > 0)
         {
            state = 8;
         	  data = tProfit;
         } else if(s.indexOf("本期淨利") > 0)
         {
            state = 8;
         	  data = tProfit;
         } else if(s.indexOf("每股盈餘") > 0)
         {
            state = 9;
         	 // data = eps;
         } else if(state == 9) 
         { 
            s = s.substring(s.indexOf("<FONT SIZE='3' COLOR='#000000'>"));
            s = s.substring(s.indexOf(">")+1);
            String a1 = s.substring(0,s.indexOf("</FONT>"));
            a1=a1.replaceAll(",","");
            a1=a1.replaceAll("&nbsp;","");
            a1 = a1.trim();
               if(a1.equals(""))
              {
                 a1 = "0";
              } 
           try{   
              eps[0] = Float.parseFloat(a1);
           }catch(Exception xe)
           {
               eps[0] = 0;
           } 
            s = s.substring(s.indexOf("<FONT SIZE='3' COLOR='#000000'>"));
            s = s.substring(s.indexOf(">")+1);
            String a2 = s.substring(0,s.indexOf("</FONT>"));
            a2=a2.replaceAll(",","");
            a2=a2.replaceAll("&nbsp;","");
            a2 = a2.trim();
               if(a2.equals(""))
              {
                 a2 = "0";
              } 
           try{   
            eps[1] = Float.parseFloat(a2);
           }catch(Exception xe)
           {
               eps[1] = 0;
           } 
            
            s = s.substring(s.indexOf("<FONT SIZE='3' COLOR='#000000'>"));
            s = s.substring(s.indexOf(">")+1);
            String a3 = s.substring(0,s.indexOf("</FONT>"));
            a3=a3.replaceAll(",","");
            a3=a3.replaceAll("&nbsp;","");
            a3 = a3.trim();
               if(a3.equals(""))
              {
                 a3 = "0";
              } 
           try{   
            eps[2] = Float.parseFloat(a3);
           }catch(Exception xe)
           {
              eps[2] = 0;
           } 
            
            String a4 = "0" ;
            if(s.indexOf("<FONT SIZE='3' COLOR='#000000'>") > 0)
            {
               s = s.substring(s.indexOf("<FONT SIZE='3' COLOR='#000000'>"));
               s = s.substring(s.indexOf(">")+1);
               a4 = s.substring(0,s.indexOf("</FONT>"));
               a4=a4.replaceAll(",","");
               a4=a4.replaceAll("&nbsp;","");
               a4 = a4.trim();
               if(a4.equals(""))
              {
                 a4 = "0";
              } 
           try{   
              eps[3] = Float.parseFloat(a4);
           }catch(Exception xe)
           {
                eps[3] = 0;
           } 
           } 
            state = -1;
         }else if(state > 0) 
         {
         	
            s = s.substring(s.indexOf("<FONT SIZE='3' COLOR='#000000'>"));
            s = s.substring(s.indexOf(">")+1);
            String a1 = s.substring(0,s.indexOf("</FONT>"));
            a1=a1.replaceAll(",","");
            a1=a1.replaceAll("&nbsp;","");
            a1 = a1.trim();
               if(a1.equals(""))
              {
                 a1 = "0";
              } 
           try{   
            data[0] = (int)Float.parseFloat(a1);
           }catch(Exception xe)
           {
              data[0] = 0;
           } 
            
            s = s.substring(s.indexOf("<FONT SIZE='3' COLOR='#000000'>"));
            s = s.substring(s.indexOf(">")+1);
            String a2 = s.substring(0,s.indexOf("</FONT>"));
            a2=a2.replaceAll(",","");
            a2=a2.replaceAll("&nbsp;","");
            a2 = a2.trim();
               if(a2.equals(""))
              {
                 a2 = "0";
              } 
           try{   
            data[1] = (int)Float.parseFloat(a2);
           }catch(Exception xe)
           {
               data[1]  = 0;
           } 
            
            s = s.substring(s.indexOf("<FONT SIZE='3' COLOR='#000000'>"));
            s = s.substring(s.indexOf(">")+1);
            String a3 = s.substring(0,s.indexOf("</FONT>"));
            a3=a3.replaceAll(",","");
            a3=a3.replaceAll("&nbsp;","");
            a3 = a3.trim();
               if(a3.equals(""))
              {
                 a3 = "0";
              } 
           try{   
            data[2] = (int)Float.parseFloat(a3);
           }catch(Exception xe)
           {
             data[2] = 0;
           } 
            
            String a4 = "0" ;
            if(s.indexOf("<FONT SIZE='3' COLOR='#000000'>") > 0)
            {
               s = s.substring(s.indexOf("<FONT SIZE='3' COLOR='#000000'>"));
               s = s.substring(s.indexOf(">")+1);
               a4 = s.substring(0,s.indexOf("</FONT>"));
               a4=a4.replaceAll(",","");
               a4=a4.replaceAll("&nbsp;","");
               a4 = a4.trim();
               if(a4.equals(""))
              {
                 a4 = "0";
              } 
              
           try{   
              data[3] = (int)Float.parseFloat(a4);
           }catch(Exception xe)
           {
              data[3] = 0;
           } 

           } 
            state = -1;
        }
    }
    year += 1911;
    for(int i = 0; i < 4;i++)
    {
  	  if(!((workIn[i] == 0) && (workCost[i] == 0) &&(workProfit[i] == 0) &&(workfee[i] == 0) &&
   	  (workOpProfit[i] == 0) &&(outInPro[i] == 0) && (outFee[i] == 0) && (tProfit[i] == 0)))
   	 {
    	if(i==0)
    	{
         System.out.print(snum+"|"+year+"|"+(i+1)+"|"+workIn[i]);
         System.out.print("|"+workCost[i]);
         System.out.print("|"+workProfit[i]);
         System.out.print("|"+workfee[i]);
         System.out.print("|"+workOpProfit[i]);
         System.out.print("|"+outInPro[i]);
         System.out.print("|"+outFee[i]);
         System.out.print("|"+tProfit[i]);
         System.out.println("|"+eps[i]+"|");
       } else
       {
            System.out.print(snum+"|" + year + "|" + (i+1) +"|" + (workIn[i]-workIn[i-1]));
           System.out.print("|"+(workCost[i]-workCost[i-1]));
           System.out.print("|"+(workProfit[i]-workProfit[i-1]));
           System.out.print("|"+(workfee[i]-workfee[i-1]));
           System.out.print("|"+(workOpProfit[i]-workOpProfit[i-1]));
           System.out.print("|"+(outInPro[i]-outInPro[i-1]));
           System.out.print("|"+(outFee[i]-outFee[i-1]));
           System.out.print("|"+(tProfit[i]-tProfit[i-1]));
           System.out.println("|"+(eps[i]-eps[i-1])+"|");
       }
     }
    }
    //System.out.print(snum+"|"+ds+"|"+CurrentCash);
    //System.out.print("|"+CurrentAssets);
    //System.out.print("|"+FixedLand);
    //System.out.print("|"+FixedAssets);
    //System.out.print("|"+FunsAndInvest);
    //
    //System.out.print("|"+(Float.parseFloat(TotalAssets)-Float.parseFloat(CurrentAssets)-Float.parseFloat(FixedAssets)-Float.parseFloat(FunsAndInvest)));
    //System.out.print("|"+TotalAssets);
    //System.out.print("|"+CurrentLaib);
    //System.out.print("|"+LongLaib);
    //System.out.print("|"+(Float.parseFloat(TotalLaib)-Float.parseFloat(CurrentLaib)-Float.parseFloat(LongLaib)));
    //System.out.print("|"+TotalLaib);
    //System.out.print("|"+capital);
    //System.out.print("|"+capitalsurplus);
    //System.out.print("|"+UndistributedProfit);
    //System.out.println("|"+ownerEquity);
    return true;
  }
}
