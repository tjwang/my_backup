import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.fight.*;

public class Index_Report {
   String _num = null;
   Vector dataPool;
   Index_Report(String num)
   {
   	  _num = num;
      dataPool = new Vector();
   }
   void add(Index_Rec ir)
   {
      dataPool.add(ir);
   }
   void add(Index2_Rec ir)
   {
      dataPool.add(ir);
   }
   Enumeration elements()
   {
       return dataPool.elements();
   }
   LineFactory getLineFactory()
   {
       Enumeration e = dataPool.elements();
       KDataLineFactory kf = new KDataLineFactory();
       int date_v = -1; 
       while(e.hasMoreElements())
       {
      	   Object o = e.nextElement();
      	   if(o instanceof Index_Rec)
      	   {
      	      Index_Rec ir = (Index_Rec)o;
      	      if(date_v < 0) date_v = Integer.parseInt(ir.dates);
      	      kf.add(_num, date_v, ir.time, (double)ir.value, ir.change);
      	   }
      	   if(o instanceof Index2_Rec)
      	   {
      	      Index2_Rec ir2 = (Index2_Rec)o;
      	      kf.add(ir2);
      	   }
       } 
       
       return  kf;
   }

}