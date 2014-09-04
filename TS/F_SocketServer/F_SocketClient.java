import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class F_SocketClient {
 Socket cs ;
 OutputStream cos;
 
 static public void main(String[] arg)throws Exception{

     System.out.println("Hello F_Socket Client ");
     
     
     System.out.println("try connectiing");
     stock.tool.F_SocketClient fcli = new stock.tool.F_SocketClient(9999); 
     for(int i =0 ;i<5000;i++)
     {
     	  fcli.sendMsg("000"+i,(new Date()).toString());
     }
     System.out.println("connection end ");
     fcli.close();
 }


}