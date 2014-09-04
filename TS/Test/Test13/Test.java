import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;

public class Test {
 static public void main(String[] arg)throws Exception{
     Base64 base64 = new Base64();
     FileInputStream fin = new  FileInputStream("a.txt");  
     BufferedReader d    = new BufferedReader(new InputStreamReader(fin));
     String s = d.readLine();
     byte[] decodedata = base64.decode(s.getBytes());
     s = new String(decodedata,"UTF-8");
     s= s.replace("\\\u00FF\\", "\u0000").replace("\u00FF\u00FF", "\u00FF");
     System.out.println(s);
     FileOutputStream fout = new  FileOutputStream("o.txt");  
     fout.write(decodedata);
  //   System.out.println(URLDecoder.decode(s));
 }


}