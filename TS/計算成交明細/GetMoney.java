import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GetMoney {
 static int icount = 0;
 static int isum = 0;
 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
  BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
  String stockN = "3013";
 // while(!getData(stockN,arg[0]));
  while( (stockN=stocknum.readLine())!=null){
   for(int i = 0 ;i < 25;i++){
     try{
   	   while(!getData(stockN,arg[0]));
   	   i = 100;
     }catch(Exception e){
       Thread.currentThread().sleep(5000);
         e.printStackTrace(System.err);
      }
   }  
  }
 }

  static boolean getData(String num, String datestr) throws IOException{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
    URL u = new URL("http://pchome.syspower.com.tw/stock/?stock_no="+num+"&item=24");
    URLConnection conn = u.openConnection(); 
    conn.setReadTimeout(19000);
    conn.setConnectTimeout(19000);

//    BufferedReader br = new BufferedReader(new InputStreamReader(u.openStream()));
    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    StringBuffer buy = new StringBuffer();
//    StringBuffer sell = new StringBuffer();
    String s;
    while((s=br.readLine())!=null){
    	 if (startflag){
           if(trflag){
 //             System.out.println(s);
              s=s.substring(s.indexOf("<TD")+1);
              boolean catchflag = false;
              boolean isadd = false;
              for (int j=0;j<s.length();j++){
              	 char c = s.charAt(j);
                 if (c == '<') {
                 	   catchflag = false;
                 	   if(isadd)
                     	   buy.append('|');
                 	   isadd = false;
                 	}
              	 if (catchflag) {
              	 	   if (c < 128){
                 	 	    if (c!='\t'&&c!=' '){
            	 	    	 	    buy.append(c);
                 	 	        isadd = true;
                 	 	    }   
                     }else{
                          buy.append('-');
               	 	        isadd = true;
                     }
              	 }
              	 if (c == '>') catchflag = true;
              }
           }
           if(s.indexOf("</TR>") >= 0){
           	 trflag = false;
             buy.append('\n');
           }
           if(s.indexOf("<TR") >= 0) {
           	buy.append(num+"|"+datestr+"|");
           	trflag = true;
           }
       }
       if(s.indexOf("²Ö­p¶q") >= 0)
       startflag = true;
       if(s.indexOf("</TABLE>") >= 0)
       startflag = false;
//       System.out.println(s);
    }
  //  System.out.println("====================="+num+"=============================");
   // if (validData(buy.toString())) {
       System.out.print(buy.toString());
       return true;
   //  } else {
   //    return false;
   //  }
    //System.out.println("======================================================");
  
  }

static boolean validData(String s) throws IOException{
    int total = -1;
    int ctotal = 0;
    int ct = 0;
    String linedata = null;
    BufferedReader br = new BufferedReader(new InputStreamReader(new StringBufferInputStream(s)));
    while((linedata=br.readLine())!=null){
    	 if (linedata.trim().equals("")) continue ;
       StringTokenizer st = new StringTokenizer(linedata, "|");
       String token="";
       for(int i = 0; i < 11 ; i++){
          token = st.nextToken();
          if ( i == 9){
             try {
             	  ct =  Integer.parseInt(token);
                ctotal += ct;
             } catch(Exception ee){
              
             }
          }       	  
          if (( i == 10) && (total == -1)){
          	  try{
                total = Integer.parseInt(token);
                total += ctotal - ct;
              } catch(Exception ee){
              }
          }
       }
    }
    if((ctotal==0) && (total==-1)) {
       icount = 0 ;
       isum = 0;
    	return true;
    }
    System.err.println("ctotal:"+ctotal+" total:"+total);
    if (ctotal == total) {
       icount = 0 ;
       isum = 0;
       return true;
    
    } else {
       if ( ctotal > isum ){
          isum = ctotal; 
       }
       if ((icount > 1) && (isum == ctotal) ){
          isum = 0;
          icount = 0;
          return true;
       } else {
          icount ++;
       }
       return false;
    }
}

}
