import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class Dofilter2 {

 static public void main(String[] arg)throws Exception{
   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0])));
   String s = "";
   while((s=br.readLine())!=null){
       StringTokenizer st = new StringTokenizer(s,"|");
       int dl = 0;
       while(st.hasMoreTokens())
       {
          String token = st.nextToken();
          System.out.print(token+"|");
          if(dl == 12)
          {
             System.out.print("100.0|100.0|");
             dl = 14;
          }else if(dl == 20)
          {
             System.out.print("100.00|100.00|100.00|100.00|100.00|100.00|100.00|100.00|");
             dl = 28;
          }else if(dl == 33)
          {
             System.out.print("100.00|");
             dl = 34;
          }
             dl++;
       }
       System.out.println();
   }
 }

}
