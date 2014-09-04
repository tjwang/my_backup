import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class GetPage2 {
static URL u1 ; 
static String cookieValue;

static private void getCookie()throws IOException{
  URL url = new URL( "http://www.yahoo.com.tw/" ); 
  URLConnection conn = url.openConnection(); 
 conn.setRequestProperty("Accept", "*/*"); 
 conn.setRequestProperty("Referer", "http://tw.yahoo.com/"); 
 conn.setRequestProperty("Accept-Language", "zh-tw"); 
 //conn.setRequestProperty("Accept-Encoding", "gzip, deflate"); 
 conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
 conn.setRequestProperty("Host", "tw.stock.yahoo.com"); 
 conn.setRequestProperty("Connection", "Keep-Alive"); 
  conn.connect(); 
  Map<String, List<String>> headers = conn.getHeaderFields(); 
  List<String> values = headers.get("Set-Cookie"); 
  
  //System.out.println(headers);
  cookieValue = null; 
  for (Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
     String v = iter.next(); 
     if (cookieValue == null)
         cookieValue = v;
     else
         cookieValue = cookieValue + ";" + v;
 } 
 //System.out.println(cookieValue);

}

 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
  BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("opt_TXO_20100427.txt")));
  String s;
   int state = 0;
   StringBuffer sb;
   String monthStr = null;
   String dateStr = arg[0];
   String type = "P";
  while((s=br.readLine())!=null)
  { 
	   if(s.indexOf("月份:") > 0){
       state = 1 ;
       monthStr = s.substring(s.indexOf("月份:")+4, s.indexOf("月份:")+11);
    }
    if((state == 1) && (s.indexOf("總量") > 0)){
       state = 2 ;
       if(type.equals("P")) type = "C";
       else type = "P";
    }
    if(state == 2)
    {
       if(s.indexOf("</a></td>") > 0){
         String a =monthStr+"|"+type+"|"+dateStr+"|"+s.substring(s.indexOf(";\">")+3,s.indexOf("</a></td>"));
       
         a = a+"|";
         s=br.readLine();
         s=br.readLine();
         a = a + s.substring(s.indexOf("><b>")+4,s.indexOf("</b></td>"));
         a = a+"|";
         s=br.readLine();
         a = a + s.substring(s.indexOf(">")+1,s.indexOf("</td>"));
         a = a+"|";
          s=br.readLine();
         a = a + s.substring(s.indexOf(">")+1,s.indexOf("</td>"));
         a = a+"|";
         s=br.readLine();
         if(s.indexOf("</font></td>") > 0){
            s=s.substring(s.indexOf(">")+1);
            a = a + s.substring(s.indexOf(">")+2,s.indexOf("</font></td>"));
            a = a+"|";
         }else
         {
            a = a + s.substring(s.indexOf(">")+1,s.indexOf("</td>"));
            a = a+"|";
        
         }
          s=br.readLine();
         a = a + s.substring(s.indexOf(">")+1,s.indexOf("</td>"));
         a = a+"|";
         a = a.replace('－','0');
         System.out.println(a);     
       }
    
    }
    if((s.indexOf("</table>") >= 0)&&(state > 1))
    {
    	state = 1;
    }
//    System.out.println(s);
   }
  }
 

  static boolean getData(String num,String datestr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL(u1,"/d/s/major_"+num+".html");
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Cookie", cookieValue); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    //URL u = new URL("http://tw.stock.yahoo.com/d/s/major_"+num+".html");
    //BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
    StringBuffer buy = new StringBuffer();
    StringBuffer sell = new StringBuffer();
    String s;
    while((s=br.readLine())!=null){
    	 int di =0;
    	 if ((di=s.indexOf("資料日期")) > 0){
    	 	try{
    	 	  System.err.println("num:"+num+"di:"+di +" "+s);
    	    String dy = s.substring(di+5,di+7);
    	    String dm = s.substring(di+9,di+11);
    	    String dd = s.substring(di+13,di+15);
    	    dy=String.valueOf(Integer.parseInt(dy)+1911)+dm+dd;
    	    if(!dy.equals(datestr)){
    	       System.err.println("Error Date:"+dy);
    	       return false;
    	    }
    	  }catch(Exception e){
    	       return true;
    	  }  
    	 }
    	 if (startflag){
//    	 	System.out.println(s);
           if(s.indexOf("</tr") >= 0) trflag = false;
           if(trflag){
              if(count < 4) {
              	if(count == 0){
                  PMname_Rec pmr = new PMname_Rec();
                  pmr.mname = s.substring(s.indexOf("\">")+2,s.indexOf("</td>")).trim();
                  if(pmr.mname.length() > 0){
                    if( pmr.SelectByCondition("mname='"+new String(pmr.mname.getBytes("MS950"),"ISO-8859-1")+"'").hasMoreElements()){
                       //System.out.println(head +"|" +arg[1]+"|"+ pmr.mnum+lineData.substring(lineData.indexOf("|")));
              	       buy.append(num+"|"+datestr+"|");
              	       buy.append(pmr.mnum+"|");
                    }else{
                       System.err.println("no main name!!"+pmr.mname+"xx" );
                    }
                  }
              	}else{
              	   buy.append(s.substring(s.indexOf("\">")+2,s.indexOf("</td>"))+"|");
                }
              } else {
              	if(count == 4){
                  PMname_Rec pmr = new PMname_Rec();
                  pmr.mname = s.substring(s.indexOf("\">")+2,s.indexOf("</td>")).trim();
                  if(pmr.mname.length() > 0){
                    if( pmr.SelectByCondition("mname='"+new String(pmr.mname.getBytes("MS950"),"ISO-8859-1")+"'").hasMoreElements()){
                       //System.out.println(head +"|" +arg[1]+"|"+ pmr.mnum+lineData.substring(lineData.indexOf("|")));
                        sell.append(num+"|"+datestr+"|");
              	       sell.append(pmr.mnum+"|");
                    }else{
                       System.err.println("no main name!!"+pmr.mname );
                    }
                  }
              	}else{
                  sell.append(s.substring(s.indexOf("\">")+2,s.indexOf("</td>"))+"|");
                }
              }
              count = (count + 1) % 8;
              if(count == 4) buy.append("\n"); 
              if(count == 0) sell.append("\n"); 
           }
           if(s.indexOf("<tr") >= 0) trflag = true;
       }
       if(s.indexOf("賣超</td>") >= 0)
       startflag = true;
       if(s.indexOf("</table>") >= 0)
       startflag = false;
    }
//    System.out.println(num+";");
    System.out.print(buy.toString());
    System.out.print(sell.toString());
    return true;
  }

}
