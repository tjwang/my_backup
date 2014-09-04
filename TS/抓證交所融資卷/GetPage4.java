import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage4 {
	static boolean data_ok;

static URL u1 ; 
static String cookieValue;
static String filedValue;

static String fieldType1  ="代號,  名稱,  前資餘額(張),  資買,  資賣,  現償,  資餘額,  資屬證金, 資使用率(%), 資限額, 前券餘額(張),   券賣,  券買,  券償,  券餘額,  券屬證金,  券使用率(%), 券限額,  資券相抵(張),  備註";
static String fieldType3  ="代號,  名稱,  前資餘額(張),  資買,  資賣,  現償,  資餘額,  資屬證金, 資使用率(%), 資限額, 前券餘額(張),   券賣,  券買,  券償,  券餘額,  券屬證金,  券使用率(%), 券限額,  資券相抵(張),  備註";
static String fieldType4  ="代號,  名稱,  前資餘額(張),  資買,  資賣,  現償,  資餘額,  資屬證金, 資使用率(%), 資券限額, 前券餘額(張),   券賣,  券買,  券償,  券餘額,  券屬證金,  券使用率(%), 券限額,  資券相抵(張),  備註";


static String fieldType5  ="代號,  名稱,  前資餘額(張),  資買,  資賣,  現償,  資餘額,  資屬證金, 資使用率(%), 資券限額, 前券餘額(張),   券賣,  券買,  券償,  券餘額,  券屬證金,  券使用率(%),  資券相抵(張),  備註";
static String fieldType7  ="代號,  名稱,  前資餘額(張),  資買,  資賣,  現償,  資餘額,  資屬證金, 資使用率(%), 資券限額, 前券餘額(張),   券賣,  券買,  券償,  券餘額,  券屬證金,  券使用率(%),  資券相抵(張),  備註";

static String fieldType2  ="代號,  名稱,  前資餘額(張),  資買,  資賣,  現償,  資餘額,  資屬證金,  資限額,  資使用率(%), 前券餘額(張),   券賣,  券買,  券償,  券餘額,  券屬證金,  券使用率(%),  資券相抵(張),  備註";
static String fieldType6  ="代號,  名稱,  前資餘額(張),  資買,  資賣,  現償,  資餘額,  資屬證金,  資限額,  資使用率(%), 前券餘額(張),   券賣,  券買,  券償,  券餘額,  券屬證金,  券使用率(%),  資券相抵(張),  備註";
static String fieldType8  ="代號,  名稱,  前資餘額(張),  資買,  資賣,  現償,  資餘額,  資屬證金,  資限額,  資使用率(%), 前券餘額(張),   券賣,  券買,  券償,  券餘額,  券屬證金,  券使用率(%),  資券相抵(張),  備註";


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
//    Date d = new Date(107,3,23);
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
          if(e3 instanceof FileNotFoundException)
          {
             break;
          }
          Thread.currentThread().sleep(i*1000);
          if(i>15)
          {
         	   throw new Exception("Network not avaible ! restart ");
          }
           e3.printStackTrace();
         
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
    String Dstr2 = String.valueOf(Integer.parseInt(Dstr.substring(0,4))-1911)+"/"+Dstr.substring(4,6)+"/"+Dstr.substring(6,8);
 //   System.err.println("http://www.otc.org.tw/ch/stock/margin_trading/margin_balance/RSTA3106_"+Dstr2+".CSV");
//    URL u = new URL("http://www.otc.org.tw/ch/stock/margin_trading/margin_balance/RSTA3106_"+Dstr2+".CSV");
//    System.out.println("http://www.otc.org.tw/ch/stock/margin_trading/margin_balance/margin_bal_download.php?d="+Dstr2+"&s=0,asc,1");                                      
    //URL u = new URL("http://www.otc.org.tw/ch/stock/margin_trading/margin_balance/margin_bal_download.php?d="+Dstr2+"&s=0,asc,1");
    URL u=new URL("http://www.otc.org.tw/web/stock/margin_trading/margin_balance/margin_bal_download.php?l=zh-tw&d="+Dstr2+"&s=0,asc,1");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "*/*"); 
    conn.setRequestProperty("Referer", "http://www.otc.org.tw/"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "www.otc.org.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuffer sell = new StringBuffer();
    String s;
    String sBuy ="0";
    String sSell="0";
    String sTotal = "0" ;
    boolean start = false;
    StringBuffer sb = new StringBuffer();
    int fileType = 0;
    while((s=br.readLine())!=null){
      //   System.out.println(s);
         if(s.indexOf("代號") >= 0)
         {
            if((s.indexOf(fieldType1) >= 0) || (s.indexOf(fieldType3) >= 0) || (s.indexOf(fieldType4) >= 0))
            {
                 fileType  = 0;           
            }
            if((s.indexOf(fieldType2) >= 0) ||(s.indexOf(fieldType6) >= 0) || (s.indexOf(fieldType8) >= 0) ) 
            {
                 fileType  = 1;           
            }
            if((s.indexOf(fieldType5) >= 0) || (s.indexOf(fieldType7) >= 0) )
            {
                 fileType  = 2;           
            }
         }
         
         if(s.indexOf("合計(張),")> 0)
         {
         	  break;
         }
         if(s.indexOf("\",\"") == 7)
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
             StringBuffer musage = new StringBuffer();
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
                       if((j!=1))
                       {
                         if((j==8) && (fileType == 1))
                         {
                            musage.append(c);
                         } else
                         {
                            System.out.print(c);
                          }
                       }
                     }
                  }else{
                     if(c ==',')
                     {
                        if((j!=1))
                        {
                        	if((j==8) && (fileType == 1))
                          {
                             musage.append('|');
                          } else
                          {
                          	 System.out.print('|');
                        	}
                        	if((j==9) && (fileType == 1))
                        	{
                        	   System.out.print(musage.toString());
                        	}
                        	if((j==16) && (fileType != 0))
                        	{
                        	   System.out.print("99999|");
                        	   j++;
                        	}
                          
                        }
                        j++;
                     }else{
                       if((j!=1))
                       {
                       	 if((j==8) && (fileType == 1))
                         {
                            musage.append(c);
                         } else
                         {
                            System.out.print(c);
                          }
                       }
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
