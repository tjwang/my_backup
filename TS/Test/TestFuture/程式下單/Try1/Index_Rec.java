import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
public class Index_Rec implements Try_Rec{
   String dates = null;
   String num = null;
   int creat_time = 0;
   int time = 0;
   float value = 0;
   float change = 0;
   float change2 = 0;
   Index_Rec(String n, String d, int t , float v , float c, float c2, int ct)
   {
   	  num = n;
   	  dates = d;
   	  time = t;
   	  value = v;
   	  change = c;
   	  change2 = c2;
   	  creat_time = ct;
   }
   Index_Rec(String data)
   {
      if(data.indexOf(":") > 0)
      {
         data = data.substring(data.indexOf(":")+1);
      }
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
             num = token;
             break;
           case 2:
             time = Integer.parseInt(token);
             break;
           case 3:
             value = Float.parseFloat(token);;
             break;
           case 4	:
             change = Float.parseFloat(token);
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
       sb.append(creat_time+":B:");
       sb.append(dates+"|");
       sb.append(num+"|");
       sb.append(time+"|");
       sb.append(value+"|");
       sb.append(change+"|");
       return sb.toString();
   }
   public int getCreateTime()
   {
       return creat_time;
   }

 }
      
