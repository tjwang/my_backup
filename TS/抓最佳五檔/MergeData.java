import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class MergeData {

 static public void main(String[] arg)throws Exception{
   String s = null;
   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0]+".1.txt")));
   while((s=br.readLine())!=null){
   	  System.out.println(s);
   }
   br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0]+".2.txt")));
   while((s=br.readLine())!=null){
   	  System.out.println(s);
   }   
   br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0]+".3.txt")));
   while((s=br.readLine())!=null){
   	  System.out.println(s);
   }   
   br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0]+".4.txt")));
   while((s=br.readLine())!=null){
   	  System.out.println(s);
   }   
   br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0]+".5.txt")));
   while((s=br.readLine())!=null){
   	  System.out.println(s);
   }   
 }

}
