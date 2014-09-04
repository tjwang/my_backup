import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class FiveLF extends SimpleLineFactory{
 Hashtable<String,Line> SnumHt = new  Hashtable();
 
 static public void main(String[] arg)throws Exception
 {
    FiveLF fl = new   FiveLF(arg[0]);
    fl.dump("2330");
    fl.dump("2454");
 }
 
 public  FiveLF(String dateStr)throws Exception
 {
      FiveSeries  fs = new FiveSeries(dateStr);
//      System.out.println("Start Line....");
      SimpleLine fsl= fs.culSimpleLine();
      Enumeration<FiveElement> e = fsl.elements();
      int index = 0;
      int startIdx = 0;
      FiveElement prevFe = null;
 
//       System.out.println("Start dis....");
     while(e.hasMoreElements())
      {
          FiveElement fe = e.nextElement();
          if ((prevFe!=null) && (!fe.getSnum().equals(prevFe.getSnum())) )
          {
          	 Line l = fsl.sub(startIdx, index-1);
          	 SnumHt.put(prevFe.getSnum(), l);
          	 startIdx = index;  
          }
          prevFe = fe;
          index ++;
      }
//        System.out.println("End dis....");
     Line l = fsl.sub(startIdx, index-1);
      SnumHt.put(prevFe.getSnum(), l);
 }
 
 public void dump(String snum)
 {
      Line l = SnumHt.get(snum);
      if(l!=null)
      {
          l.dump();
      }
 }


}
