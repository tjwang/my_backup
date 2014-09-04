package stock.tool;
import java.io.*;
import java.net.*;

public class FilterFile2 {

 static public void main(String[] arg)throws Exception{
  BufferedReader filesource = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0])));
  String linedata;
  while( (linedata=filesource.readLine())!=null){
      if(linedata.trim().length() > 0){
        System.out.println(linedata.replaceAll(",",""));
      }
  }  
 }

}
