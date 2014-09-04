import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
public class Stock_Rec implements Try_Rec{
   String dates = null;
   String snum = null;
   int creat_time = 0;
   int time = 0;
   int ra = 0;
   int tra = 0;
   float rp = 0;
   float change = 0;
   int b1ra = 0;
   float b1rp = 0;
   int b2ra = 0;
   float b2rp = 0;
   int b3ra = 0;
   float b3rp = 0;
   int b4ra = 0;
   float b4rp = 0;
   int b5ra = 0;
   float b5rp = 0;
   int s1ra = 0;
   float s1rp = 0;
   int s2ra = 0;
   float s2rp = 0;
   int s3ra = 0;
   float s3rp = 0;
   int s4ra = 0;
   float s4rp = 0;
   int s5ra = 0;
   float s5rp = 0;
   
   Stock_Rec(String data)
   {
      StringTokenizer st = new StringTokenizer(data,"|");
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
           case 2:
             change = Float.parseFloat(token);
             break;
           case 3:
             time = Integer.parseInt(token);
             break;
           case 4	:
             rp = Float.parseFloat(token);;
             break;
           case 5	:
             tra = Integer.parseInt(token);
             break;
           case 6:
             ra = Integer.parseInt(token);
             break;
           case 7:
             b1rp = Float.parseFloat(token);;
             break;
           case 8:
             b1ra = Integer.parseInt(token);
             break;
           case 9:
             b2rp = Float.parseFloat(token);;
             break;
           case 10:
             b2ra = Integer.parseInt(token);
             break;
           case 11:
             b3rp = Float.parseFloat(token);;
             break;
           case 12:
             b3ra = Integer.parseInt(token);
             break;
           case 13:
             b4rp = Float.parseFloat(token);;
             break;
           case 14:
             b4ra = Integer.parseInt(token);
             break;
           case 15:
             b5rp = Float.parseFloat(token);;
             break;
           case 16:
             b5ra = Integer.parseInt(token);
             break;
           case 17:
             s1rp = Float.parseFloat(token);;
             break;
           case 18:
             s1ra = Integer.parseInt(token);
             break;
           case 19:
             s2rp = Float.parseFloat(token);;
             break;
           case 20:
             s2ra = Integer.parseInt(token);
             break;
           case 21:
             s3rp = Float.parseFloat(token);;
             break;
           case 22:
             s3ra = Integer.parseInt(token);
             break;
           case 23:
             s4rp = Float.parseFloat(token);;
             break;
           case 24:
             s4ra = Integer.parseInt(token);
             break;
           case 25:
             s5rp = Float.parseFloat(token);;
             break;
           case 26:
             s5ra = Integer.parseInt(token);
             break;
         }
         idx ++;
      }

   }
   void setCTime(int ctime)
   {
      creat_time = ctime;
   }
   public String toString()
   {
       StringBuffer sb = new StringBuffer();
       sb.append(creat_time+":A:");
       sb.append(dates+"|");
       sb.append(snum+"|");
       sb.append(change+"|");
       sb.append(time+"|");
       sb.append(rp+"|");
       sb.append(tra+"|");
       sb.append(ra+"|");
       sb.append(b1rp+"|");
       sb.append(b1ra+"|");
       sb.append(b2rp+"|");
       sb.append(b2ra+"|");
       sb.append(b3rp+"|");
       sb.append(b3ra+"|");
       sb.append(b4rp+"|");
       sb.append(b4ra+"|");
       sb.append(b5rp+"|");
       sb.append(b5ra+"|");
       sb.append(s1rp+"|");
       sb.append(s1ra+"|");
       sb.append(s2rp+"|");
       sb.append(s2ra+"|");
       sb.append(s3rp+"|");
       sb.append(s3ra+"|");
       sb.append(s4rp+"|");
       sb.append(s4ra+"|");
       sb.append(s5rp+"|");
       sb.append(s5ra+"|");
       return sb.toString();
   
   }
   public int getCreateTime()
   {
       return creat_time;
   }

}