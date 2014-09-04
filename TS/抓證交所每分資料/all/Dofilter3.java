import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class Dofilter3 {

 static public void main(String[] arg)throws Exception{
   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0])));
   String s = "";
   while((s=br.readLine())!=null){
       StringTokenizer st = new StringTokenizer(s,"|");
       int dl = 0;
       while(st.hasMoreTokens())
       {
          String token = st.nextToken();
          if((dl != 6) && (dl != 7))
          {
             System.out.print(token+"|");
          }
             dl++;
       }
       System.out.println();
   }
 }

}
