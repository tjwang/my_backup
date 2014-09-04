import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class GetXPage {
static URL u1 ; 
static Enumeration e;
static String DateStr;
static String cookie_value = null; 
static Hashtable ht;
static boolean printflag = true;
static F_SocketClient fcli = null;
static int option = 0;
 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
DBConnection.debug = false;
//  BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream(GlobleSetting.ListPath)));
  Ds000_Rec dsr = new Ds000_Rec();
  if("1".equals(arg[1]))
  {
  	 fcli = new stock.tool.F_SocketClient(9999); 
  }
  try{
    option = Integer.parseInt(arg[1]);
  }catch(Exception iie)
  {
  
  }
  e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000fw"+arg[1]+" ;");
  String stockN = null;
  DateStr = arg[0];
  u1 = new URL("http://mis.twse.com.tw/");
//  u1 = new URL("http://mis.twse.com.tw/");
  String s;
  ht = new Hashtable();
 // BufferedReader br = new BufferedReader(new InputStreamReader(u1.openConnection().getInputStream()));
 // while((s=br.readLine())!=null);
 
  Vector v = new Vector();
 while(e.hasMoreElements())
 {
//   dsr = (Ds000_Rec)e.nextElement();   
//   System.out.println(dsr.dump());
   v.add(e.nextElement());
 }
 e = v.elements();   
// Thread  t1 =new Thread( new PageRun());
// Thread  t2 =new Thread( new PageRun());
// Thread  t3 =new Thread( new PageRun());
// Thread  t4 =new Thread( new PageRun());
for(;;)
{
  Date d = new Date();
  System.err.println("Start time: "+(d.getHours())+":"+(d.getMinutes())+":"+(d.getSeconds()));
  if(((d.getHours() == 13) && (d.getMinutes() > 30)&&(!printflag)) || (d.getHours() > 13))
  {
      return;
  }
  printflag = false;
  while(e.hasMoreElements())
  {
     dsr = (Ds000_Rec)e.nextElement();
      for(int i = 0 ;i < 20;i++){
         try{
     	      while(!getData(dsr.snum,DateStr)){
     	        i++;
     	        if(i > 20 ) break;
            }
   	        i = 501;
          }catch(Exception e3){
          	e3.printStackTrace();
           if(e3 instanceof FileNotFoundException)
           {
               break;
           }
           if (i>15)
           {
             //  System.err.println("Error"+i);
             //  (new DataInputStream(System.in)).readLine();
             //  System.err.println("Ok ..go!"+i);
             //  i = 0; 
             //  }else{
             // //e3.printStackTrace();
              System.err.println("Error Sleep"+i);
              try{
                Thread.currentThread().sleep(500);
              }catch(java.lang.InterruptedException lie)
              {
              }
           }
          }
       }
  }
  Date d2 = new Date();
  System.err.println("End time  :"+(d2.getHours())+":"+(d2.getMinutes())+":"+(d2.getSeconds())+" cost time:"+(d2.getTime()-d.getTime()));
  e = v.elements();
}



// t1.run();
// t2.run();
// t3.run();
// t4.run();

 }
 

  static boolean getData(String num,String datestr) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
//    URL u = new URL(u1,"/data/"+num+".csv?ID=111111111");
    URL u = null;
    if(option > 3)
    {
       u = new URL(u1," /stock/api/getStockInfo.jsp?ex_ch=otc_"+num+".tw_"+datestr+"&json=1&delay=0&_=1401861223656");
    } else
    {
       u = new URL(u1," /stock/api/getStockInfo.jsp?ex_ch=tse_"+num+".tw_"+datestr+"&json=1&delay=0&_=1401861223656");
    }
    URLConnection conn = u.openConnection(); 
    conn.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01"); 
    conn.setRequestProperty("Referer", "http://mis.twse.com.tw/stock/fibest.jsp"); 
    conn.setRequestProperty("Accept-Language", "zh-tw"); 
    conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)"); 
    conn.setRequestProperty("Host", "mis.twse.com.tw"); 
    conn.setRequestProperty("Connection", "Keep-Alive"); 
    if(cookie_value != null)
    {
       conn.setRequestProperty("Cookie", cookie_value);
    }
    conn.setReadTimeout(3000);
    conn.setConnectTimeout(2000);
     conn.connect(); 
     if(cookie_value == null)
     {
        Map<String, List<String>> headers = conn.getHeaderFields(); 
        if(headers != null)
        {
            List<String> values = headers.get("Set-Cookie"); 
            if(values!=null && values.size() > 0)
            {
               cookie_value = null; 
               for (Iterator<String> iter = values.iterator(); iter.hasNext(); ) {
                  String v = iter.next(); 
//                  System.out.println(v);
                  if (cookie_value == null)
                      cookie_value = v;
                  else
                      cookie_value = cookie_value + ";" + v;
               }
            }
        }
     }
     InputStream is = conn.getInputStream();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    StringBuffer sb = new StringBuffer();
    String s;
    String snum    = "";
    String diff    = "-";
    String tt    = "";
    String rp      = "";
    String ra      = "";
    String ra2     = "";
    String b1rp    = "0";
    String b1ra    = "0";
    String b2rp    = "0";
    String b2ra    = "0";
    String b3rp    = "0";
    String b3ra    = "0";
    String b4rp    = "0";
    String b4ra    = "0";
    String b5rp    = "0";
    String b5ra    = "0";
    String s1rp    = "0";
    String s1ra    = "0";
    String s2rp    = "0";
    String s2ra    = "0";
    String s3rp    = "0";
    String s3ra    = "0";
    String s4rp    = "0";
    String s4ra    = "0";
    String s5rp    = "0";
    String s5ra    = "0";
    String lclose    = "";
    while((s=br.readLine())!=null){
 //   	 System.out.println(s);
       StringTokenizer st = new StringTokenizer(s,",:");
       sb.append(datestr+"|");
       while(st.hasMoreTokens())
       {
           String tok = st.nextToken();
           String vtok = null;
           if(tok.equals("\"c\""))
           {
           	   vtok = st.nextToken();
            	 snum = (new StringTokenizer(vtok,"\"")).nextToken();
           } else if(tok.equals("\"b\""))
           {
           	   vtok = st.nextToken();
           	   String brp = (new StringTokenizer(vtok,"\"")).nextToken();
           	   StringTokenizer st2 = new StringTokenizer(brp,"_");
           	   if(st2.hasMoreTokens()) b1rp = st2.nextToken();
           	   if(st2.hasMoreTokens()) b2rp = st2.nextToken();
           	   if(st2.hasMoreTokens()) b3rp = st2.nextToken();
           	   if(st2.hasMoreTokens()) b4rp = st2.nextToken();
           	   if(st2.hasMoreTokens()) b5rp = st2.nextToken();
           } else if(tok.equals("\"a\""))
           {
           	   vtok = st.nextToken();
            	 String srp = (new StringTokenizer(vtok,"\"")).nextToken();
           	   StringTokenizer st2 = new StringTokenizer(srp,"_");
           	   if(st2.hasMoreTokens()) s1rp = st2.nextToken();
           	   if(st2.hasMoreTokens()) s2rp = st2.nextToken();
           	   if(st2.hasMoreTokens()) s3rp = st2.nextToken();
           	   if(st2.hasMoreTokens()) s4rp = st2.nextToken();
           	   if(st2.hasMoreTokens()) s5rp = st2.nextToken();
           } else if(tok.equals("\"f\""))
           {
           	   vtok = st.nextToken();
            	 String sra = (new StringTokenizer(vtok,"\"")).nextToken();
           	   StringTokenizer st2 = new StringTokenizer(sra,"_");
           	   if(st2.hasMoreTokens())   s1ra = st2.nextToken();
           	   if(st2.hasMoreTokens())   s2ra = st2.nextToken();
           	   if(st2.hasMoreTokens())   s3ra = st2.nextToken();
           	   if(st2.hasMoreTokens())   s4ra = st2.nextToken();
           	   if(st2.hasMoreTokens())   s5ra = st2.nextToken();
           } else if(tok.equals("\"g\""))
           {
           	   vtok = st.nextToken();
            	 String bra = (new StringTokenizer(vtok,"\"")).nextToken();
            	 StringTokenizer st2 = new StringTokenizer(bra,"_");
           	   if(st2.hasMoreTokens())  b1ra = st2.nextToken();
           	   if(st2.hasMoreTokens())  b2ra = st2.nextToken();
           	   if(st2.hasMoreTokens())  b3ra = st2.nextToken();
           	   if(st2.hasMoreTokens())  b4ra = st2.nextToken();
           	   if(st2.hasMoreTokens())  b5ra = st2.nextToken();
          } else if(tok.equals("\"t\""))
           {
           	   vtok = st.nextToken()+st.nextToken()+st.nextToken();
            	 tt = (new StringTokenizer(vtok,"\"")).nextToken();
           } else if(tok.equals("\"v\""))
           {
           	   vtok = st.nextToken();
            	 ra = (new StringTokenizer(vtok,"\"")).nextToken();
           } else if(tok.equals("\"tv\""))
           {
           	   vtok = st.nextToken();
            	 ra2 = (new StringTokenizer(vtok,"\"")).nextToken();
           }  else if(tok.equals("\"z\""))
           {
           	   vtok = st.nextToken();
            	 rp = (new StringTokenizer(vtok,"\"")).nextToken();
           }  else if(tok.equals("\"y\""))
           {
           	   vtok = st.nextToken();
            	 lclose = (new StringTokenizer(vtok,"\"")).nextToken();
           } 
       }
       try{
         float frp = Float.parseFloat(rp);
         float lrp = Float.parseFloat(lclose);
         diff = String.format("%.2f",frp-lrp);
       }catch(Exception xxxe)
       {
      
       }
       sb.append(snum +"|");
       sb.append(diff +"|");
       sb.append(tt   +"|");
       sb.append(rp   +"|");
       sb.append(ra   +"|");
       sb.append(ra2  +"|");
       sb.append(b1rp +"|");
       sb.append(b1ra +"|");
       sb.append(b2rp +"|");
       sb.append(b2ra +"|");
       sb.append(b3rp +"|");
       sb.append(b3ra +"|");
       sb.append(b4rp +"|");
       sb.append(b4ra +"|");
       sb.append(b5rp +"|");
       sb.append(b5ra +"|");
       sb.append(s1rp +"|");
       sb.append(s1ra +"|");
       sb.append(s2rp +"|");
       sb.append(s2ra +"|");
       sb.append(s3rp +"|");
       sb.append(s3ra +"|");
       sb.append(s4rp +"|");
       sb.append(s4ra +"|");
       sb.append(s5rp +"|");
       sb.append(s5ra +"|");
       String toPrint = sb.toString();
       String prevPrint = (String)ht.get(num);
       if(!toPrint.equals(prevPrint))
       {
          System.out.println(toPrint);
          printflag = true;
          ht.put(num,toPrint);
          if(fcli!=null)
          {
             fcli.sendMsg(num, toPrint);
          }
       }
/*    	 
           sb.append(datestr+"|");
             boolean inqflag = false;
             int j = 0;
             for(int i = 0; i < s.length(); i++)
             {
                char c = s.charAt(i);
                if( c == '"')
                {
                  inqflag = !inqflag;
                } else if(c!=':'){
                  if(inqflag)
                  {
                     if(c!=',')
                     {
                       if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }else{
                     if(c ==',')
                     {
                       if((j<3)||((j>7) && (j<31)))
                       sb.append('|');
                       j++;
                     }else{
                       if((j<3)||((j>7) && (j<31)))
                        sb.append(c);
                     }
                  }
                }
             }
*/
    }
    is.close();
    return true;
  }
  static void dataRun()
  {
      Ds000_Rec dsr = (Ds000_Rec)e.nextElement();
      for(int i = 0 ;i < 20;i++){
         try{
     	      while(!getData(dsr.snum,DateStr)){
     	        i++;
     	        if(i > 20 ) break;
            }
   	        i = 501;
          }catch(Exception e3){
           if(e3 instanceof FileNotFoundException)
           {
               break;
           }
           if (i>15)
           {
             //  System.err.println("Error"+i);
             //  (new DataInputStream(System.in)).readLine();
             //  System.err.println("Ok ..go!"+i);
             //  i = 0; 
             //  }else{
             // //e3.printStackTrace();
              System.err.println("Error Sleep"+i);
              try{
                Thread.currentThread().sleep(500);
              }catch(java.lang.InterruptedException lie)
              {
              }
           }
          }
       }

  }
  /*
 static class PageRun implements Runnable {
         PageRun() {
         }
 
         public void run() {
           for(;;){
  	          if(e.hasMoreElements())
  	          {
               }else{
                  break;
               }  
           }
            System.err.println("End time  :"+(new Date().getTime()));
         }

   }
*/
}