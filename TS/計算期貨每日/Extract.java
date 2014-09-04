import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class Extract {
  static class Rec
  {
  	 int ttss ;
  	 int seq ;
  	 int price;
  	 int ra;
  	 Rec(String data,int no)
  	 {
  	    StringTokenizer st = new StringTokenizer(data,",");
  	    st.nextToken();
  	    st.nextToken();
  	    st.nextToken();
        ttss = Integer.parseInt(st.nextToken().trim());
        seq = no;
        price = Integer.parseInt(st.nextToken().trim());
        ra = Integer.parseInt(st.nextToken().trim());
  	 }
  	 void dump()
  	 {
  	    System.out.println("rec :"+seq+"  "+ +ttss+"  "+ +price+"  "+ +ra+"  ");
  	 }
  }
  static public void main(String[] arg)throws Exception{
  	 String filename_zip = "Daily_"+arg[0].substring(0,4)+"_"+arg[0].substring(4,6)+"_"+arg[0].substring(6,8)+".zip";
  	 String filename = "Daily_"+arg[0].substring(0,4)+"_"+arg[0].substring(4,6)+"_"+arg[0].substring(6,8)+".rpt";
  	 Runtime rt = Runtime.getRuntime();
  	 Process p = rt.exec("unzip -o \"X:\\Working\\TS\\_期交所_\\期貨每日\\"+filename_zip+"\"");
     p.waitFor();
     BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
     String s = null;
     int datei = Integer.parseInt(arg[0]) ;
     int d = datei % 100;
     int y = datei/10000;
     int m = (datei%10000)/100;
     int wc = 0;
     Vector txv = new Vector();
     Vector mtxv = new Vector();
     for(int i=1; i < d; i++) 
     {
        Date dD = new Date(y-1900, m - 1, i);
   //  System.err.println("y:"+y+" m:"+m+" i:"+i+"Day:"+dD.getDay()+ "wc : "+ wc );
        if(dD.getDay() == 3)
        {
           wc++;
        }
     }    
     
     if(wc >=3) m++;
     System.err.println("wc : "+ wc );
     int tx_seq = 0;
     int mtx_seq = 0;
     int tx_RA = 0;
     Rec tx_prec = null;
     Rec mtx_prec = null;
     while((s=br.readLine())!=null)
     { 
         if(s.indexOf(",TX")>0 && s.indexOf(","+(y*100+m)+" ") > 0)
         {
             Rec r = new Rec(s,tx_seq++);
             if(r.ra % 2 != 0) 
             {
                System.err.println("got ra"+r.ttss+" "+r.price);
             }
             if(tx_prec != null && tx_prec.price != r.price)
             {
                  computeRecs(txv, arg[0],(y*100+m), "TX");
                  txv = new Vector();
             }
             tx_RA += r.ra;
             txv.add(r);
             tx_prec = r;
           //  System.out.println(s);
         } else if(s.indexOf(",MTX")>0 && s.indexOf(","+(y*100+m)+" ") > 0)
         {
             Rec r = new Rec(s,mtx_seq++);
             if(mtx_prec != null && mtx_prec.price != r.price)
             {
              //    computeRecs(mtxv);
                  mtxv = new Vector();
             }
             mtxv.add(r);
             mtx_prec = r ;
     //        System.out.println(s);
         }

     }
     System.err.println(tx_RA);
  }
  
  static void computeRecs(Vector v,String date_s, int contact, String name)
  {
     Enumeration e = v.elements();
     Rec prec = null;
     int count = 0;
     int  tt = 0;
     int seq = 0;
     int start_t = 0;
     while(e.hasMoreElements())
     {
        Rec r = (Rec) e.nextElement();
        if(prec != null)
        {
        	 tt+= r.ttss - prec.ttss;
        } else
        {
      //     r.dump();
           start_t = r.ttss;
        }
        count += r.ra;
        prec = r;
        seq ++;
     }
     //prec.dump();
     //System.out.println("time : "+ tt +"  ra:"+count+ " count:"+seq);
     System.out.println(name+"|"+date_s+"|"+prec.ttss+"|"+contact+"|"+prec.price+"|"+seq+"|"+(count/2)+"|");
  }
}
