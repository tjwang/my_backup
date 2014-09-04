import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.fight.*;

public class Stock_Report {
   String _snum = null;
   Vector dataPool;
   Stock_Report(String snum)
   {
   	  _snum = snum;
      dataPool = new Vector();
   }
   void add(Stock_Rec sr)
   {
      dataPool.add(sr);
   }
   Enumeration elements()
   {
       return dataPool.elements();
   }
   LineFactory getLineRpFactory()
   {
       Enumeration e = dataPool.elements();
       KDataLineFactory kf = new KDataLineFactory(); 
       while(e.hasMoreElements())
       {
      	   Stock_Rec sr = (Stock_Rec)e.nextElement();
      	   kf.add(_snum, 20101119, sr.time, (double)sr.rp);
       } 
       return  kf;
   }

}