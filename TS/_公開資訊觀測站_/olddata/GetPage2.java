import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage2 {
static URL u1 ; 
static String cookieValue;


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
//  BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
  Ds000_Rec dsr = new Ds000_Rec();
  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr order by snum;");
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
      for(int year=2000; year < 2011; year++)
      {
        for(int q = 1; q < 5; q++)
        {
        	 String ds = String.valueOf(year*100+q);
        	 for( int i = 0; i < 20; i++)
        	 {
              try{
     	           while(!getData(snum,ds)){
     	              i++;
     	              if(i > 20 ) break;
     	           }
   	             i = 501;
              }catch(Exception e3){
                 
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
 

  static boolean getData(String snum, String ds) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL("http://mopsov.tse.com.tw/nas/t06sa18/"+ds+"/A01_"+snum+"_"+ds+".htm");
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
    String CurrentCash ="0";
    String CurrentAssets ="0";
    
    String FixedLand="0";
    String FixedAssets="0";
    String FunsAndInvest="0";
    
    String otherAssets ="0";
    String TotalAssets="0";

    String CurrentLaib="0";
    String LongLaib="0";
    String otherLaib="0";
    String TotalLaib="0";
     
    String capital="0";
    String capitalsurplus="0";
    String UndistributedProfit="0";
    String  ownerEquity="0";
     
     
    boolean isCurrentAsset = false;
    String s = null;
    while((s=br.readLine())!=null){
//         System.out.println(s);
         if(s.indexOf(";現金及約當現金<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            CurrentCash = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(CurrentCash.equals("&nbsp;") || CurrentCash.equals(" ")|| CurrentCash.equals(""))
            {
               CurrentCash = "0";
            }
         } else if(s.indexOf(";現金<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            CurrentCash = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(CurrentCash.equals("&nbsp;") || CurrentCash.equals(" ")|| CurrentCash.equals(""))
            {
               CurrentCash = "0";
            }
         } else if(s.indexOf("土 地") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            FixedLand = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         }else if(s.indexOf("土    地") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            FixedLand = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";普通股股本<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            capital = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         }  else if(s.indexOf(";普通股<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            capital = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         }  else if(s.indexOf(";股本<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            capital = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         }  else if(s.indexOf(";股    本<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            capital = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";資本公積合計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            capitalsurplus = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";資本公積<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            capitalsurplus = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";資本公積總計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            capitalsurplus = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";保留盈餘合計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            UndistributedProfit = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";保留盈餘總計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            UndistributedProfit = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";保留盈餘<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            UndistributedProfit = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";股東權益總計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            ownerEquity = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";股東權益<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            ownerEquity = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         }  else if(s.indexOf(";股東權益合計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            ownerEquity = s.substring(s.indexOf(">")+1,s.indexOf("<"));
         } else if(s.indexOf(";固定資產淨額<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            FixedAssets = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(FixedAssets.equals("&nbsp;") || FixedAssets.equals(" ")|| FixedAssets.equals(""))
            {
               FixedAssets = "0";
            }
         } else if(s.indexOf(";固定資產<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            FixedAssets = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(FixedAssets.equals("&nbsp;") || FixedAssets.equals(" ")|| FixedAssets.equals(""))
            {
               FixedAssets = "0";
            }
         }  else if(s.indexOf(";固定資產-淨額<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            FixedAssets = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(FixedAssets.equals("&nbsp;") || FixedAssets.equals(" ")|| FixedAssets.equals(""))
            {
               FixedAssets = "0";
            }
         } else if(s.indexOf(";基金及投資<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            FunsAndInvest = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(FunsAndInvest.equals("&nbsp;") || FunsAndInvest.equals(" ")|| FunsAndInvest.equals(""))
            {
               FunsAndInvest = "0";
            }
        } else if(s.indexOf(";長期投資合計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            FunsAndInvest = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(FunsAndInvest.equals("&nbsp;") || FunsAndInvest.equals(" ")|| FunsAndInvest.equals(""))
            {
               FunsAndInvest = "0";
            }
         } else if(s.indexOf(";基金及長期投資<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            FunsAndInvest = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(FunsAndInvest.equals("&nbsp;") || FunsAndInvest.equals(" ")|| FunsAndInvest.equals(""))
            {
               FunsAndInvest = "0";
            }
         } else if(s.indexOf("採權益法之股權投資-淨額<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            FunsAndInvest = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(FunsAndInvest.equals("&nbsp;") || FunsAndInvest.equals(" ")|| FunsAndInvest.equals(""))
            {
               FunsAndInvest = "0";
            }
         } else if(s.indexOf(";資產總計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            TotalAssets = s.substring(s.indexOf(">")+1,s.indexOf("<"));
            if(TotalAssets.equals("&nbsp;") || TotalAssets.equals(" ")|| TotalAssets.equals(""))
            {
               TotalAssets = "0";
            }
         }  else if(s.indexOf(";資產合計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            TotalAssets = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(TotalAssets.equals("&nbsp;") || TotalAssets.equals(" ")|| TotalAssets.equals(""))
            {
               TotalAssets = "0";
            }
        } else if(s.indexOf(";資產<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            TotalAssets = s.substring(s.indexOf(">")+1,s.indexOf("<"));
            if(TotalAssets.equals("&nbsp;") || TotalAssets.equals(" ")|| TotalAssets.equals(""))
            {
               TotalAssets = "0";
            }
         } else if(s.indexOf(";流動負債<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            CurrentLaib = s.substring(s.indexOf(">")+1,s.indexOf("<"));
            if(CurrentLaib.equals("&nbsp;") || CurrentLaib.equals(" ")|| CurrentLaib.equals(""))
            {
               CurrentLaib = "0";
            }
         }  else if(s.indexOf(";流動負債合計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            CurrentLaib = s.substring(s.indexOf(">")+1,s.indexOf("<"));
            if(CurrentLaib.equals("&nbsp;") || CurrentLaib.equals(" ")|| CurrentLaib.equals(""))
            {
               CurrentLaib = "0";
            }
         } else if(s.indexOf(";長期負債<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            LongLaib = s.substring(s.indexOf(">")+1,s.indexOf("<"));
            if(LongLaib.equals("&nbsp;") || LongLaib.equals(" ")|| LongLaib.equals(""))
            {
               LongLaib = "0";
            }
         }  else if(s.indexOf(";長期負債合計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            LongLaib = s.substring(s.indexOf(">")+1,s.indexOf("<"));
            if(LongLaib.equals("&nbsp;") || LongLaib.equals(" ")|| LongLaib.equals(""))
            {
               LongLaib = "0";
            }
         } else if(s.indexOf(";長期附息負債<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            LongLaib = s.substring(s.indexOf(">")+1,s.indexOf("<"));
            if(LongLaib.equals("&nbsp;") || LongLaib.equals(" ")|| LongLaib.equals(""))
            {
               LongLaib = "0";
            }
         } else if(s.indexOf(";負債總計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            TotalLaib = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(TotalLaib.equals("&nbsp;") || TotalLaib.equals(" ")|| TotalLaib.equals(""))
             {
               TotalLaib = "0";
             }
         } else if(s.indexOf(";負債<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            TotalLaib = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(TotalLaib.equals("&nbsp;") || TotalLaib.equals(" ")|| TotalLaib.equals(""))
             {
               TotalLaib = "0";
             }
         } else if(s.indexOf(";負債合計<") > 0)
         {
            s = s.substring(s.indexOf("align=right>"));
            TotalLaib = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(TotalLaib.equals("&nbsp;") || TotalLaib.equals(" ")|| TotalLaib.equals(""))
             {
               TotalLaib = "0";
             }
         } else if(s.indexOf(";流動資產<") > 0)
         {
             s = s.substring(s.indexOf("align=right>"));
             CurrentAssets = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(CurrentAssets.equals("&nbsp;") || CurrentAssets.equals(" ")|| CurrentAssets.equals(""))
             {
               CurrentAssets = "0";
             }
          } else if(s.indexOf(";流動資產合計") > 0)
         {
             s = s.substring(s.indexOf("align=right>"));
             CurrentAssets = s.substring(s.indexOf(">")+1,s.indexOf("<"));
             if(CurrentAssets.equals("&nbsp;") || CurrentAssets.equals(" ")|| CurrentAssets.equals(""))
             {
               CurrentAssets = "0";
             }
          }
    }
    System.out.print(snum+"|"+ds.substring(0,4)+"|"+ds.substring(4,6)+"|"+CurrentCash);
    System.out.print("|"+CurrentAssets);
    System.out.print("|"+FixedLand);
    System.out.print("|"+FixedAssets);
    System.out.print("|"+FunsAndInvest);
    
    System.out.print("|"+(Float.parseFloat(TotalAssets)-Float.parseFloat(CurrentAssets)-Float.parseFloat(FixedAssets)-Float.parseFloat(FunsAndInvest)));
    System.out.print("|"+TotalAssets);
    System.out.print("|"+CurrentLaib);
    System.out.print("|"+LongLaib);
    System.out.print("|"+(Float.parseFloat(TotalLaib)-Float.parseFloat(CurrentLaib)-Float.parseFloat(LongLaib)));
    System.out.print("|"+TotalLaib);
    System.out.print("|"+capital);
    System.out.print("|"+capitalsurplus);
    System.out.print("|"+UndistributedProfit);
    System.out.println("|"+ownerEquity+"|");
     
    return true;
  }
}
