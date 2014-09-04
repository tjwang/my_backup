import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.fight.*;

public class Future_Report {
   Vector dataPool;
   Future_Report()
   {
      dataPool = new Vector();
   }
   void add(Future_Rec fr)
   {
      dataPool.add(fr);
   }
   Enumeration elements()
   {
       return dataPool.elements();
   }
   
   LineFactory getLineFactory()
   {
       Enumeration e = dataPool.elements();
       KDataLineFactory kf = new KDataLineFactory(); 
       while(e.hasMoreElements())
       {
      	   Future_Rec fr = (Future_Rec)e.nextElement();
      	   kf.add("fu", 20101119, fr.time, (double)fr.price, fr.change);
       } 
       return  kf;
   }

}