import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class CulMoneyOut {
 static String pDateStr = "";

 static public void main(String[] arg)throws Exception{
 DBConnection.debug = false;
  String stockN = null;
 // int idate = Integer.parseInt(arg[0]);
 // Date d = new Date(idate/10000,(idate%10000/100),idate%100);
 // d = new Date(d.getTime()-86400000);
 // pDateStr = String.valueOf((d.getYear())*10000+(d.getMonth())*100+d.getDate()); 
     	   getData(stockN, arg[0]);
 }

 static void getData(String snum, String datestr)throws Exception {
   Psnuminoutsum_Rec ppsumr = null;
   Psnuminoutsum_Rec psumr = new  Psnuminoutsum_Rec();
   Enumeration e = psumr.SelectBySQL("select *  from psnuminoutsum  where  date = '"+datestr+"' order by snum,type "); 
   float Aamt= 0;  
   float Famt= 0;  
   float Bamt= 0;  
   float Oamt= 0;  
     while(e.hasMoreElements()){
         psumr = (Psnuminoutsum_Rec)e.nextElement();
         if((ppsumr!=null)&&(!psumr.snum.equals(ppsumr.snum))){
             PLast_Rec pr = new PLast_Rec();
             pr.snum = ppsumr.snum ;
             pr.date = datestr ;
             if(pr.SelectBySQL("select * from PLast where snum='"+ppsumr.snum+"' and date='"+datestr+"'").hasMoreElements()){
               int total = Integer.parseInt(pr.total);
               Oamt = total - Aamt - Famt - Bamt;
               System.out.println(ppsumr.snum+"|"+datestr+"|"+(Famt/total)+"|"+(Bamt/total)+"|"+(Aamt/total)+"|"+(Oamt/total)+"|"+total+"|");
             }else {
              int total = 0;
               Oamt = total - Aamt - Famt - Bamt;
                System.err.println("snum:"+ppsumr.snum+" pr.ran:"+pr.ra+" pcar.sum:" );
             }  
             Aamt = 0;
              Famt = 0;
             Bamt = 0;
             Oamt = 0;

          }  
        if(psumr.type.equals("F"))
        {    
        	Famt = Float.parseFloat(psumr.out)/1000;
        }
        if(psumr.type.equals("A"))
        {
        	Aamt = Float.parseFloat(psumr.out)/1000;
        }
        if(psumr.type.equals("B"))
        {
        	Bamt = Float.parseFloat(psumr.out)/1000;
        }
        ppsumr = psumr;
       }
     }    





}
