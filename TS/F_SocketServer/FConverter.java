import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.pattern.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FConverter {
	
   String name;
   double maxv;
   double minv;
   private static Hashtable<String,FConverter> ht;
	 private FConverter(F3_Rec f3)
	 {
	     name = f3.f1;
	     maxv = Double.parseDouble(f3.f2);
	     minv = Double.parseDouble(f3.f3);
	 }
	 
	 public int getFv(double v)
	 {
	    if(v >= maxv) return 1;
	    if(v <= minv) return -1;
	    return 0;
	 }
   static private void loadFtTable()
   {
       if(ht == null)
       {
          try
          {
             F3_Rec f3r = new F3_Rec();
             Enumeration e = f3r.SelectBySQL("select 0 as date, 0 as time, f_name as f1, max as f2, min as f3 from feature_value ");
             ht = new Hashtable<String,FConverter>();
             while(e.hasMoreElements())
             {
               f3r = (F3_Rec)e.nextElement();
               System.out.println(f3r.dump());
               ht.put(f3r.f1,new FConverter(f3r));
             } 
          } catch(Exception ie)
          {
            ie.printStackTrace();  
          }        
       }
   }
  
   static public int getFvofName(String name, double v)
   {
       loadFtTable();
//       System.out.println(name);
       return  ht.get(name).getFv(v);
   }
   static public int getFvofName(NRec nr)
   {
       loadFtTable();
//       System.out.println(name);
       return  ht.get(nr.name).getFv(nr.value);
   }
  
   static public PRec[] getPvByNameAndValue(String name, int vv, String pvTab, String dst)
   {
        F3_Rec f3r = new F3_Rec();
        PRec[] ppvs = null;
        try{
           Enumeration e = f3r.SelectBySQL("select 0 as date, 0 as time, sec_n as f1, sec_v f2, vcc as f3 from "+
                                        pvTab +" where pri_n ='"+name+"' and pri_v="+vv+" and sec_n='"+dst+"' ");
           Vector<F3_Rec> f3_rec_pool = new  Vector<F3_Rec>();
           int total_count = 0;
           int pvs_count = 0;
           while(e.hasMoreElements())
           {
             f3r = (F3_Rec)e.nextElement();
        //     System.out.println(f3r.dump());
             f3_rec_pool.add(f3r);
             if(Integer.parseInt(f3r.f2)!= 0)
             {
                pvs_count ++;
             }
             total_count += Integer.parseInt(f3r.f3);
           }
           ppvs = new PRec[pvs_count];
           e = f3_rec_pool.elements();
           pvs_count = 0;
           while(e.hasMoreElements())
           {
             f3r = (F3_Rec)e.nextElement();
             int ncase = Integer.parseInt(f3r.f2);
             if(ncase != 0)
             {
                int vcc =  Integer.parseInt(f3r.f3);
                ppvs[pvs_count] = new PRec(ncase,(double)vcc / (double) total_count);
  //              ppvs.add(new PRec(ncase,(double)vcc / (double) total_count));
                pvs_count++;
             }
           }
           
        }catch(Exception e)
        {
           e.printStackTrace();
        } 
//        return (PRec[])ppvs.toArray();      
        return ppvs;
   }
   static public PRec[] getPvofNR(NRec nr, String pvTab, String dst)
   {
        return getPvByNameAndValue(nr.name, getFvofName(nr), pvTab, dst);
   }
   static public PRec[] getPvofNR_NonConvert(NRec nr, String pvTab, String dst)
   {
        return getPvByNameAndValue(nr.name, (int)nr.value, pvTab, dst);
   }
   static public void dumpPRecs(PRec[] pvs)
   {
      if(pvs == null) return ;
      for(int i=0; i<pvs.length; i++)
      {
         System.out.println(pvs[i].ncase+"-->:"+pvs[i].pvalue);
      }
   }
}