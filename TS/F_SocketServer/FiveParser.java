import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class FiveParser {
	
static 	F2_Rec parseTwoField(String code,String ds,String data)
{
      	 F2_Rec f2r = new F2_Rec();
      	 StringTokenizer st = new StringTokenizer(data,"|");
      	 st.nextToken();
         f2r.date = ds;
         f2r.time = st.nextToken();
         f2r.f1 = st.nextToken();
         f2r.f2 = st.nextToken();
        return f2r;
}
static 	PLDayk_Rec parseWDKField(String code,String ds,String data)
{
      	 PLDayk_Rec plk = new PLDayk_Rec();
      	 StringTokenizer st = new StringTokenizer(data,"|");
      	 plk.snum = st.nextToken();
         plk.date = st.nextToken();
         plk.time = st.nextToken();
         plk.open = st.nextToken();
         plk.high = st.nextToken();
         plk.low = st.nextToken();
         plk.close = st.nextToken();
         plk.volume = st.nextToken();
         plk.money = st.nextToken();
         return plk;
}

static public void main(String[] arg)throws Exception{
  FileInputStream is = new FileInputStream(arg[1]);
//  BufferedReader br = new BufferedReader(new InputStreamReader(is));
  byte[] databuf = new byte[512];
  int rlen = 0;
  String s = null;
  int count = 0;
  
  while((rlen = is.read(databuf)) > 0)
//  while((s = br.readLine()) !=null)
  {
      if(rlen != 512) throw new Exception("Error reading in file format");
      String code = (new String(databuf,0,12)).trim();
//      String data= (new String(databuf,12,500)).trim();
//       System.out.println(s);
//       String code = s.substring(0,12);
//       String data = s.substring(12,s.length());
       
//System.out.println("code:"+code);
      if(code.equals("TX054")) 
      {
      	 
     // 	 data = data.trim().replaceAll("\\|"," |");
//      	 System.out.println(data);
         PFFiveRaw_Rec ffrr = new PFFiveRaw_Rec(code, arg[0], databuf, 12);
//         System.out.println(" "+count+"-"+ffrr.snum + " : "+ ffrr.s_rp);
         count ++;
//         ffrr.println();
      }else if(code.equals("1"))/*發行量加權股價指數*/
      {
      }else if(code.equals("2"))/*未含金融保險股指數*/
      {
      }else if(code.equals("200"))/*成交金額*/
      {
      }else if(code.equals("201"))/*成交數量,成交筆數*/
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
    //  	 System.out.println(f2.dump());
      }else if(code.equals("220"))/*成交金額<股票>*/
      {
      }else if(code.equals("221"))/*成交數量,成交筆數<股票> */
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
 //     	 System.out.println(code+"-->"+f2.dump());
      }else if(code.equals("110"))/*總委買數量,總委買筆數*/
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
     // 	 System.out.println(code+"-->"+f2.dump());
      }else if(code.equals("111"))/*總委賣數量,總委賣筆數*/
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
      //	 System.out.println(f2.dump());
      }else if(code.equals("130"))/**總委買數量,總委買筆數<股票>*/
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
  //    	 System.out.println(code+"-->"+f2.dump());
      }else if(code.equals("131"))/*總委賣數量,總委賣筆數<股票>*/
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
  //    	 System.out.println(code+"-->"+f2.dump());
      }else if(code.equals("132"))/*漲停總委賣數量,漲停總委賣筆數<股票>*/
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
      //	 System.out.println(f2.dump());
      }else if(code.equals("133"))/*漲停總委賣數量,漲停總委賣筆數<股票>*/
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
      	// System.out.println(f2.dump());
      }else if(code.equals("134"))/*跌停總委賣數量,跌停總委賣筆數<股票>*/
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
      	// System.out.println(f2.dump());
      }else if(code.equals("135"))/*跌停總委賣數量,跌停總委賣筆數<股票>*/
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 F2_Rec f2 = parseTwoField(code, arg[0], data);
      	// System.out.println(f2.dump());
      }else if(code.equals("^N225"))
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 PLDayk_Rec plk = parseWDKField(code, arg[0], data);
      
      }else if(code.equals("^HSI"))
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 PLDayk_Rec plk = parseWDKField(code, arg[0], data);
     	   System.out.println(plk.dump());
        
      }else if(code.equals("000001.SS"))
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 PLDayk_Rec plk = parseWDKField(code, arg[0], data);
      
      }else if(code.equals("^KS11"))
      {
      	 String data= (new String(databuf,12,500)).trim();
      	 PLDayk_Rec plk = parseWDKField(code, arg[0], data);
      }
   }
   System.out.println(" "+count+"-end");
 }

}