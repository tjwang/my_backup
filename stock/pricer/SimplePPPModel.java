package stock.pricer;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;
import stock.app.*;

public class SimplePPPModel implements PPPModel{
     
   DataLine baseL = null;
   public SimplePPPModel() throws Exception
   {
      baseL =new DataLine((new OneFieldBySQL("select date,0 as time, close as f1 from pamountinfo where snum='2905' and date> '20100601' and date < '20110401' ;")).culF1Line());
   }
   
   public double getAtomGrap()
   {
       return 0.05;
   }
   
//   public double getUpProb()
//   {
//       return 0.5 ;
//   }
   
   public double getUpProb(double[] data)
   {
        DataLine dl = new DataLine(baseL, data);
  //      dl.dump();
        return 0.1;
   }


 }      

