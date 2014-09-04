import stock.fight.*;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ViewDe
{
   static Hashtable ds000ht;
   
   static public void main(String[] arg)throws Exception
   {
    	 PAmountTemp_Rec  prevamr = null;
  	   PAmountTemp_Rec  amr = new PAmountTemp_Rec();
  	   Vector v = null;
  	   Ds000_Rec ds000= new Ds000_Rec();
  	   ds000ht = new Hashtable();
  	   Enumeration e = ds000.SelectBySQL("select ds000.snum as snum, ds000.sname as sname, ds000.capital as capital from ds000,ds000attr "+
  	                     "where ds000.snum = ds000attr.snum and ds000attr.tcode='1' and capital!= 10000000000 order by capital desc limit 100");
  	   while(e.hasMoreElements())
  	   {
  	      ds000 = (Ds000_Rec)e.nextElement();
  	      ds000ht.put(ds000.snum, ds000);
  	   }
  	   
  	   TreeSet recordht = new TreeSet(new VRComparator(0));
   	   e = amr.SelectBySQL("select * from pAmountTemp where time >= '"+arg[0]+"' and time <= '"+arg[1]+"' order by snum,time;");
   	   while(e.hasMoreElements())
   	   {
   	      amr = (PAmountTemp_Rec)e.nextElement();
   	      if((prevamr == null) || (!prevamr.snum.equals(amr.snum)))   
   	      {
   	      	 if(v!=null)
   	      	 {
   	      	     recorData(recordht, v, "default");
   	      	 }
   	         v = new Vector();
   	      }
   	      v.add(amr);
   	      prevamr = amr;
   	   }
   	   Iterator<VRecord> i = recordht.descendingIterator();
   	   double a = 0;
   	   double b = 0;
   	   while(i.hasNext())
   	   {
   	      VRecord vr = i.next();
   	      vr.dump();
   	      a += vr.capital * Float.parseFloat(vr.starter.rp);
   	      b += vr.capital * Float.parseFloat(vr.ender.rp);
   	   }
   	   System.out.println("a :" + a);
   	   System.out.println("b :" + b);
   	   System.out.println("b/a :" + b/a);
   	
   }
   
   static void recorData(TreeSet ht, Vector v, String type)
   {
       Enumeration e = v.elements();
  	   PAmountTemp_Rec  amr = null;
  	   VRecord vr = new VRecord();
  	   vr.rpCount = 0;
  	   vr.rpMoney = 0;
       while(e.hasMoreElements())
       {
          if(amr == null)
          {
             amr = (PAmountTemp_Rec)e.nextElement();
             vr.starter = amr;
          } else{
             amr = (PAmountTemp_Rec)e.nextElement();
          }
          vr.rpCount += Integer.parseInt(amr.ra);
          vr.rpMoney += Float.parseFloat(amr.rp) * Integer.parseInt(amr.ra);
       }
       vr.ender = amr;
       if(vr.ender == null) return;
       vr.diff_ab = Float.parseFloat(vr.ender.rp) - Float.parseFloat(vr.starter.rp);
       vr.diff = vr.diff_ab / (Float.parseFloat(vr.starter.rp) - Float.parseFloat(vr.starter.diff));
       Ds000_Rec ds000r = (Ds000_Rec)ds000ht.get(vr.starter.snum);
       if(ds000r == null) return;
       try{
        	vr.capital = Integer.parseInt(ds000r.capital);
          vr.diff_TAB = Integer.parseInt(ds000r.capital) * vr.diff_ab;
       }catch(NumberFormatException ne)
       {
        	vr.capital = 10000;
          vr.diff_TAB = vr.diff_ab * 10000;
       }
       ht.add(vr);
   }
}