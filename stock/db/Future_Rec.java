package stock.db;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
public class Future_Rec implements Try_Rec{
   public float price = 0;
   public float change = 0;
   public int ttlVol = 0;
   public float bid1 = 0;
   public int bidVol1 = 0;
   public float ask1 = 0;
   public int askVol1 = 0;
   public int time = 0;
   public int creat_time = 0;
   public Future_Rec()
   {
   }
   public Future_Rec(String data)
   {
      StringTokenizer st = new StringTokenizer(data,"|");
      int idx = 0;
      while(st.hasMoreTokens())
      {
         String token = st.nextToken();
         switch(idx)
         {
           case 0:
             price = Float.parseFloat(token);
             break;
           case 1:
             change = Float.parseFloat(token);
             break;
           case 2:
             ttlVol = Integer.parseInt(token);
             break;
           case 3:
             bid1 = Float.parseFloat(token);
             break;
           case 4	:
             bidVol1 =Integer.parseInt(token);
             break;
           case 5:
             ask1 = Float.parseFloat(token);
             break;
           case 6	:
             askVol1 =Integer.parseInt(token);
             break;
           case 7:
             time = Integer.parseInt(token);
             break;
         }
         idx ++;
      }
   }
   public String toString()
   {
       StringBuffer sb = new StringBuffer();
       sb.append(creat_time+":C:");
       sb.append(price+"|");
       sb.append(change+"|");
       sb.append(ttlVol+"|");
       sb.append(bid1+"|");
       sb.append(bidVol1+"|");
       sb.append(ask1+"|");
       sb.append(askVol1+"|");
       sb.append(time+"|");
       return sb.toString();
   }
   public void setCTime(int ctime)
   {
      creat_time = ctime;
   }
   public int getCreateTime()
   {
       return creat_time;
   }

}
