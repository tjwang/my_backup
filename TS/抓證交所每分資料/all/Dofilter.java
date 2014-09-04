import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class Dofilter {

 static public void main(String[] arg)throws Exception{
   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0])));
   String s = "";
   int basedate = Integer.parseInt(arg[1]);
   int dl = 0;
   while((s=br.readLine())!=null){
      if(Integer.parseInt(s.substring(0,8)) > basedate)
      {
          int dc = 0;
          for(int i=0;i<s.length();i++)
          {
              if(s.charAt(i) == '|')
                dc++;
          }
          if(dl == 0)
          {
             dl = dc;
          }
          if(dl == dc)
          {
             System.out.println(s);
          } else {
             System.err.println(s);
             return;
          } 
       }
   }
 }

}
