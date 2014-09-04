import java.io.*;
import java.net.*;
import java.util.*;
import stock.db.*;

public class CulMoney {
 static String pDateStr = "";

 static public void main(String[] arg)throws Exception{
 DBConnection.debug = false;
  //BufferedReader stocknum = new BufferedReader(new InputStreamReader(new FileInputStream("stocknum.txt")));
  String stockN = null;
  int idate = Integer.parseInt(arg[0]);
  Date d = new Date(idate/10000,(idate%10000/100),idate%100);
  d = new Date(d.getTime()-86400000);
  pDateStr = String.valueOf((d.getYear())*10000+(d.getMonth())*100+d.getDate()); 
//  while( (stockN=stocknum.readLine())!=null){
     	   getData(stockN, arg[0]);
//   }     	   
 }

 static void getData(String snum, String datestr)throws Exception {
   PMAmount_Rec pcar = new  PMAmount_Rec();
    float first1value = -100;
    float last1value =  100;
   String first1name = null;
   String last1name = null;
    float first2value = -100;
    float last2value =  100;
   String first2name = null;
   String last2name = null;
    float first3value = -100;
    float last3value =  100;
   String first3name = null;
   String last3name = null;
    Ds000_Rec dsr = new Ds000_Rec();
   dsr.snum = null;
    Enumeration e = pcar.SelectBySQL("select *  from pMAmount  where  date = '"+datestr+"' order by snum "); 
     
      while(e.hasMoreElements()){
         pcar = (PMAmount_Rec)e.nextElement();
         if(!pcar.snum.equals(dsr.snum)){
                dsr.snum = pcar.snum ;
                dsr.SelectInto();
         }
    	   PMname_Rec pmn= new PMname_Rec();
     	   pmn.mnum = pcar.mnum;
    	   pmn.SelectInto();
         PLast_Rec pr = new PLast_Rec();
         pr.snum = dsr.snum ;
         pr.date = pDateStr ;
         if(pr.SelectBySQL("select * from PLast where snum='"+dsr.snum+"' and date='"+datestr+"'").hasMoreElements()){
           try{
              System.out.println(datestr+"|"+dsr.snum+"|"+(new String(dsr.sname.getBytes("ISO-8859-1"),"Big5")) +"|"+pmn.mnum+"|"+(new String(pmn.mname.getBytes("ISO-8859-1"),"Big5"))+"|"+pr.total+"|"+(Float.parseFloat(pcar.sum)/Float.parseFloat(pr.total))+"|");
           }catch(Exception ee){
         
           }
         }else {
            System.err.println("snum:"+dsr.snum+" pr.ran:"+pr.ra+" pcar.sum:" + pcar.sum);
         }  
         
       }
     }    



static float getPAvdata(String snum,String mnum,String datestr)throws Exception{
	float totalV = 0;
	int totalC = 0;
  PCAverage_Rec par =new PCAverage_Rec();
  PMAmount_Rec pmr = new PMAmount_Rec();
  Enumeration e = pmr.SelectBySQL("select * from tmamount where snum='"+snum+"' and mnum='"+mnum+"' and  date < '"+datestr+"'order by date");
  while(e.hasMoreElements()){
     pmr = (PMAmount_Rec)e.nextElement();
     par.snum = snum;
     par.date = pmr.date;
     if(par.SelectInto()){
        float fv = Float.parseFloat(par.rp);
        int cv = Integer.parseInt(pmr.inAmount);
//        System.out.println(pmr.date +":"+fv+":"+cv+":"+fv*cv);
        totalV = totalV +  (fv * cv);
        totalC = totalC + cv;
     }
  }
//  System.out.println("totalV:"+totalV+" totalC:" +totalC);
  return (totalV/totalC);
}

static float getNAvdata(String snum,String mnum,String datestr)throws Exception{
	float totalV = 0;
	int totalC = 0;
  PCAverage_Rec par =new PCAverage_Rec();
  PMAmount_Rec pmr = new PMAmount_Rec();
  Enumeration e = pmr.SelectBySQL("select * from tmamount where snum='"+snum+"' and mnum='"+mnum+"' and  date < '"+datestr+"'order by date");
  while(e.hasMoreElements()){
     pmr = (PMAmount_Rec)e.nextElement();
     par.snum = snum;
     par.date = pmr.date;
     if(par.SelectInto()){
        float fv = Float.parseFloat(par.rp);
        int cv = Integer.parseInt(pmr.outAmount);
//        System.out.println(pmr.date +":"+fv+":"+cv+":"+fv*cv);
        totalV = totalV +  (fv * cv);
        totalC = totalC + cv;
     }
  }
//  System.out.println("totalV:"+totalV+" totalC:" +totalC);
  return (totalV/totalC);
}




}
