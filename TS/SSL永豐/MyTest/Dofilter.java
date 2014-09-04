import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class Dofilter {

 static public void main(String[] arg)throws Exception{
   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0])));
   String s = "";
   Hashtable ht =new Hashtable();
   String dates = null;
   String snum = null;
   String times = null;
   String ra = null;
   String rp = null;
   String b1ra = null;
   String b1rp = null;
   String b2ra = null;
   String b2rp = null;
   String b3ra = null;
   String b3rp = null;
   String b4ra = null;
   String b4rp = null;
   String b5ra = null;
   String b5rp = null;
   String s1ra = null;
   String s1rp = null;
   String s2ra = null;
   String s2rp = null;
   String s3ra = null;
   String s3rp = null;
   String s4ra = null;
   String s4rp = null;
   String s5ra = null;
   String s5rp = null;
   String prevRp = null;
   while((s=br.readLine())!=null){
      StringTokenizer st = new StringTokenizer(s,"|");
      int idx = 0;
      
      while(st.hasMoreTokens())
      {
         String token = st.nextToken();
         switch(idx)
         {
           case 0:
             dates = token;
             break;
           case 1:
             snum = token;
             break;
           case 4	:
             rp = token;
             break;
           case 3:
             times = token;
             break;
           case 6:
             ra = token;
             break;
           case 7:
             b1rp = token;
             break;
           case 8:
             b1ra = token;
             break;
           case 9:
             b2rp = token;
             break;
           case 10:
             b2ra = token;
             break;
           case 11:
             b3rp = token;
             break;
           case 12:
             b3ra = token;
             break;
           case 13:
             b4rp = token;
             break;
           case 14:
             b4ra = token;
             break;
           case 15:
             b5rp = token;
             break;
           case 16:
             b5ra = token;
             break;
           case 17:
             s1rp = token;
             break;
           case 18:
             s1ra = token;
             break;
           case 19:
             s2rp = token;
             break;
           case 20:
             s2ra = token;
             break;
           case 21:
             s3rp = token;
             break;
           case 22:
             s3ra = token;
             break;
           case 23:
             s4rp = token;
             break;
           case 24:
             s4ra = token;
             break;
           case 25:
             s5rp = token;
             break;
           case 26:
             s5ra = token;
             break;
         }
         idx ++;
      }
      //prevRp=(String)ht.get(snum);
      //if(prevRp == null)
      //{
      //     PLast_Rec pr = new PLast_Rec();
      //     pr.date=dates;
      //     pr.snum = snum;
      //     if(pr.SelectInto())
      //     {
      //        prevRp = String.valueOf(Float.parseFloat(pr.rp)-Float.parseFloat(pr.diff));
      //        ht.put(snum,prevRp);
      //     } else {
      //        System.err.println("Error: get price "+snum );
      //     }
      //}
      //if(prevRp!=null)
      //{
      //   float frp = Float.parseFloat(prevRp) +  Float.parseFloat(rp);
      //   System.out.println(snum+"|"+dates+"|"+times+"|0|"+frp+"|"+ra+"|");
      //}
      if(!ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|1|"+rp+"|"+ra+"|");
      if(!b1ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|0|"+b1rp+"|"+b1ra+"|");
      if(!b2ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|0|"+b2rp+"|"+b2ra+"|");
      if(!b3ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|0|"+b3rp+"|"+b3ra+"|");
      if(!b4ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|0|"+b4rp+"|"+b4ra+"|");
      if(!b5ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|0|"+b5rp+"|"+b5ra+"|");
      if(!s1ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|2|"+s1rp+"|"+s1ra+"|");
      if(!s2ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|2|"+s2rp+"|"+s2ra+"|");
      if(!s3ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|2|"+s3rp+"|"+s3ra+"|");
      if(!s4ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|2|"+s4rp+"|"+s4ra+"|");
      if(!s5ra.equals("0"))
         System.out.println(snum+"|"+dates+"|"+times+"|2|"+s5rp+"|"+s5ra+"|");
   }
 }

}
