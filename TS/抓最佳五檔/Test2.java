import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;
import stock.tool.*;

public class Test2 {
static byte[] data;



 static public void main(String[] arg)throws Exception{
    System.out.println(GlobleSetting.getTSDataPath());
    System.out.println(GlobleSetting.getTSCodePath());

//   System.out.println(URLDecoder.decode("%3C%53%43%52%49%50%54%20%4C%41%4E%47%55%41%47%45%3D%22%4A%61%76%61%53%63%72%69%70%74%22%3E%3C%21%2D%2D%0D%0A%68%70%5F%6F%6B%3D%74%72%75%65%3B%66%75%6E%63%74%69%6F%6E%20%68%70%5F%64%30%30%28%73%29%7B%69%66%28%21%68%70%5F%6F%6B%29%72%65%74%75%72%6E%3B%64%6F%63%75%6D%65%6E%74%2E%77%72%69%74%65%28%73%29%7D%2F%2F%2D%2D%3E%3C%2F%53%43%52%49%50%54%3E"));
//   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("xx.txt")));
//   String s;
//     boolean pf = false;
//   while((s=br.readLine())!=null){
//      
//   ByteArrayOutputStream data = new ByteArrayOutputStream();
//     boolean sf = false;
//     int datai = 0;
//     for(int i=0; i<s.length(); i++)
//     {
//        if(s.charAt(i)=='"')
//        {
//           sf = !sf;
//        }else{
//            if(sf && (s.charAt(i)== '\\') && (s.charAt(i+1)== 'x')) 
//            {
//            	char hc = s.charAt(i+2);
//            	char lc = s.charAt(i+3);
//            	data.write(((hc > 0x39?(hc - 0x41+10):(hc - 0x30)) * 16 + (lc > 0x39?(lc - 0x41+10):(lc - 0x30))));
//              if(!pf) 
//              {
//              	System.err.println((hc > 0x39?(hc - 0x41+10):(hc - 0x30)));
//              	System.err.println((lc > 0x39?(lc - 0x41+10):(lc - 0x30)));
//              	//System.err.println(data[datai]);
//              	pf = true;
//              }
//              i += 3;
//            }else{
//               System.out.print(data.toString());
//               data = new ByteArrayOutputStream();
//               System.out.print(s.charAt(i));
//            }
//        }
//     }
//   
//   }
}
 


}
