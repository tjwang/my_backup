import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

public class Sync2 {

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
   PMAmount_Rec pmr = new PMAmount_Rec();
   e = pmr.SelectBySQL("select * from pmamount2 where date='"+arg[0]+"'");
   while(e.hasMoreElements()){
      pmr = (PMAmount_Rec)e.nextElement();
      s = (String)mht.get(pmr.mnum);
      if(s!=null)
      {
         System.out.println(pmr.snum+"|"+pmr.date+"|"+s+"|"+pmr.inAmount+"|"+pmr.outAmount+"|"+pmr.sum+"|");
      }
   }  
 }

}
