import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class DeleteDup {

 static public void main(String[] arg)throws Exception{
   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0])));
   String s = "";
   while((s=br.readLine())!=null){
   	  StringTokenizer st = new StringTokenizer(s,"|");
   	  System.out.println( "delete from pmamount where snum='"+st.nextToken()+"' and date='"+st.nextToken()+"' and mnum='"+
   	                       st.nextToken()+"' ;");
   }
 }

}
