import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GetYLData {
static URL u1 ; 
static String cookieValue="tw_StockRSS=d=s2Wq_arbPKwlm7Lh5eQg.EOFZ9FRKoRyM1TLEP.bEwBL0xgmDWF.fI56LGGhr9KwZgLfBBIX06vO0gLySBHOm73hRlPjFWcyVwNf17tPQ5pVGLD8s5bU7lS_Xf_6UjXCCCH56Yy3.3vNKBUG2e.V.uymteJk9rm_BhHklc38MKyLqZXDl4AWoELJynx9N4MNBC.ZpTzUWduGFvPb2M7cOy2arxRRHVmu96EFfz.LfZP9yB_NtD7.x323B9YRNWxZnDFn3pbEQvrs2yTqKDVKSdC6wp8ynH782kuvlYUdevpTovw-&v=1; B=7l4ti6p5tj02k&b=4&d=vgCXGrlpYEN6Ld7gi0bomY72OpQZ1GnHCQY-&s=2n&i=PbRWsoma5JVNzF3s_JKT; Q=q1=AACAAAAAAAAAAA--&q2=S91EOg--; F=a=mddKGaoMvTMn0MGwMd0DSJPiDu79HUVRHYH_BXB0G5s8p6_w52yOXCLEwQLUGnIlMoRl7veg11yd.4YNAZ5Y5FTGGA--&b=DWuZ; C=mg=1; BA=ba=5550&ip=118.169.41.105&t=1272910646; YLS=v=1&p=0&n=0; Y=v=1&n=bl5lq4p4jne6k&p=";


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
  Wldcode_Rec dsr = new Wldcode_Rec();
  Enumeration e = dsr.SelectBySQL("select * from Wldcode ;");
  String stockN = null;
  
  while(e.hasMoreElements()){
      dsr = (Wldcode_Rec)e.nextElement();
      System.err.println(dsr.wcode);
  	   getData(dsr.wcode);
   }  

  }
 

  static boolean getData(String num) throws Exception{
    try{
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Program Files\\YLoader\\data\\"+num+".csv")));
      String s = null;
      while((s=br.readLine())!=null)
      {
      	 StringTokenizer st = new StringTokenizer(s,",");
      	 String ds = st.nextToken();
      	 String open = st.nextToken();
      	 String high = st.nextToken();
      	 String low = st.nextToken();
      	 String close = st.nextToken();
      	 String vol = st.nextToken();
      	 st = new StringTokenizer(ds,"/");
      	 int m = Integer.parseInt(st.nextToken());
      	 int d = Integer.parseInt(st.nextToken());
      	 int y = Integer.parseInt(st.nextToken());
         System.out.println(num+"|"+(y*10000+m*100+d)+"|0|"+open+"|"+high+"|"+low+"|"+close+"|"+vol+"|"+vol+"|");
      }
//    s = num+"|"+datestr+"|"+sOpen+"|"+sRp+"|"+sHigh+"|"+sLow+"|"+sRa+"|"+sDiff+"|"+sPclose+"|";
//    s = s.replaceAll(",","");
//    System.out.println(s);
    } catch(FileNotFoundException e)
    {
        System.err.println(num+" not Found");
        return false;
    }
    return true;
  }
}
