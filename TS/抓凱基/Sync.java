import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class Sync {

 static public void main(String[] arg)throws Exception{
   String s = "";
   int basedate = Integer.parseInt(arg[0]);
//   int basedate2 = Integer.parseInt(arg[2]);
   Hashtable mht = new  Hashtable();
   Mmap_Rec mmap = new Mmap_Rec();
   Enumeration e = mmap.SelectBySQL("select * from mmap ");
   while(e.hasMoreElements())
   {
       mmap = (Mmap_Rec)e.nextElement();
       mht.put(mmap.mnum1,mmap.mnum2);
   } 
   e = mmap.SelectBySQL("select distinct date as mnum1, '0' as mnum2 from pmamount2 where date > 20091021 and date < '20100101' ;");    
   Vector dv = new Vector();
   while(e.hasMoreElements()){
      mmap = (Mmap_Rec)e.nextElement();
      dv.add(mmap.mnum1);
  }
 
  Ds000_Rec dsr = new Ds000_Rec();
  PMname_Rec mnr = new PMname_Rec();
   
  e = mnr.SelectBySQL("select * from pmname2 order by mnum;");
  Vector v = new Vector();
  while(e.hasMoreElements()){
    v.add(e.nextElement());
  }
  
  e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr order by snum;");
  while(e.hasMoreElements()){
      dsr = (Ds000_Rec)e.nextElement();
 //     System.err.println(" "+dsr.snum);
   //  if(Integer.parseInt(dsr.snum) < 2396)
   //  {
   //      continue;
   //  }

      Enumeration e2 = v.elements();
      while(e2.hasMoreElements()){
             mnr = (PMname_Rec)e2.nextElement();
             if(mht.get(mnr.mnum) == null)
             {
                 continue;
             }
             Enumeration e3 = dv.elements();
             String pqdate = null;
             while(e3.hasMoreElements()){
             	
             	  String qdate = (String)e3.nextElement();
             	  PMAmount_Rec pmr = new PMAmount_Rec();
             	  mmap.SelectBySQL("select count(*) as mnum1 ,'x' as mnum2  from pmamount2 where snum ='"+dsr.snum+"' and mnum='"+mnr.mnum+"' and  date = '"+qdate+"'  ;");
//                System.out.println(dsr.snum+" :"+mnr.mnum+" - "+ mht.get(mnr.mnum)+"date:"+qdate+" count:"+mmap.mnum1);
                if(mmap.mnum1.equals("0"))
                {
             	       PMAmount_Rec pmr2 = new PMAmount_Rec();
                     pmr2.SelectBySQL("select * from pmamount2009 where snum ='"+dsr.snum+"' and mnum='"+mht.get(mnr.mnum)+"' and  date = '"+pqdate+"';" );
                     pmr.SelectBySQL ("select * from pmamount2009 where snum ='"+dsr.snum+"' and mnum='"+mht.get(mnr.mnum)+"' and  date = '"+qdate+"';" );
                     if(!pmr.mnum.equals(""))
                     {
                         String data = pmr.dump();
                         String data2 = pmr2.dump();
                         if(data.indexOf("|0|0|0|") < 0)
                         {
                         //    System.out.println(data);
                        if( 
                         (!pmr.inAmount.equals(pmr2.inAmount)) ||
                         (!pmr.outAmount.equals(pmr2.outAmount)) ||
                         (!pmr.sum.equals(pmr2.sum))
                         )
                         {
                            System.out.println(data);
                          }
                        }
                     }
                }else{
//             	       PMAmount_Rec pmr2 = new PMAmount_Rec();
//                     pmr2.SelectBySQL("select * from pmamount2 where snum ='"+dsr.snum+"' and mnum='"+mnr.mnum+"' and  date = '"+qdate+"';" );
//                     pmr.SelectBySQL("select * from pmamount where snum ='"+dsr.snum+"' and mnum='"+mht.get(mnr.mnum)+"' and  date = '"+qdate+"';" );
//
//                     if(pmr.inAmount.equals("")) continue;                     
//                     if( 
//                         (!pmr.inAmount.equals(pmr2.inAmount)) ||
//                         (!pmr.outAmount.equals(pmr2.outAmount)) ||
//                         (!pmr.sum.equals(pmr2.sum))
//                      )
//                     {
//                         String data1= pmr.dump();
//                         String data2= pmr2.dump();
//                         System.out.println("Not Equals 1:"+data1);
//                         System.out.println("            :"+data2);
//                        
//                     }
//                    
                }
                pqdate = qdate;
            }
      }
  }  
 }

}
