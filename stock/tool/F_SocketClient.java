package stock.tool;
import java.io.*;
import java.net.*;
import java.util.*;

public class F_SocketClient {
 Socket cs ;
 OutputStream cos;
 

 public F_SocketClient(int pport)
 {
	  try{
      cs = new Socket("127.0.0.1",pport);
      cos = cs.getOutputStream();
    } catch(Exception ex)
    {
       ex.printStackTrace();
    }
 }
 
 public void sendMsg(String skey,String data)
 {
	  try{
       StringBuffer sbkey = new StringBuffer(skey);
       StringBuffer sbdata = new StringBuffer(data);
       sbkey.setLength(12);
       sbdata.setLength(500);
       cos.write(sbkey.append(sbdata).toString().getBytes());
    } catch(Exception ex)
    {
       ex.printStackTrace();
    }
 }
 
 public void close()
 {
 	  try{
       cos.close();
    }catch(Exception ex)
    {
       ex.printStackTrace();
    }
 }

}