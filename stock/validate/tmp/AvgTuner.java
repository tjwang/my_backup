package stock.validate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;
import stock.app.*;

public  class AvgTuner extends Tuner {

   static public void main(String[] arg)throws Exception{  
      Ds000_Rec  ds000r = new Ds000_Rec();
      Enumeration e = ds000r.SelectBySQL("select * from ds000 ");
      AvgTuner  atr = new AvgTuner();
      atr.setTuneBase(3);
      while(e.hasMoreElements())
      {
      	 ds000r = (Ds000_Rec)e.nextElement();
         StockInfoData   skd = new StockInfoData(ds000r.snum,arg[0], arg[1]);
//         KLine kl = skd.culKLine();
//         MALine l = (MALine)atr.staticTune(kl);
//         if((l!=null) && (l.getPeriod() < 5 ))
//         {
//             System.out.println("snum:"+ds000r.snum+" period:"+l.getPeriod());  
//         }
         
         Line kl1 = skd.culOpen_upAmtLine();
         Line kl2 = skd.culOpen_downAmtLine();
         Line l = kl1.diff(kl2,0);
         Line l2 = kl2.diff(kl1,0);
//         System.out.println("Rate:"+String.format("%1$.5f",atr.getHighRate(kl1,kl2,0))+" snum:"+ds000r.snum+" "+ new String(ds000r.sname.getBytes("ISO-8859-1"),"MS950")); 
         if(atr.getCutSum(kl1,0) /kl1.length() > 1000)
          System.out.println("Rate:"+String.format("%1$.5f",atr.getCutSum(l,0)/atr.getCutSum(l2,0))+" snum:"+ds000r.snum+" "+ new String(ds000r.sname.getBytes("ISO-8859-1"),"MS950") ); 
      }
     // l.dump();
   }
   
   public Line staticTune(Line L)throws Exception
   {
      int vlength = L.length();
      LineFactory alf = new SimpleLineFactory(L);
      MALine basel = alf.culMALine(_range);
      double minSquare = -1;
      int minSn = 0;
      for(int i = 1; i < vlength; i++)
      {
          MALine mal = alf.culMALine(i);
          Line dl = basel.diff(mal,i);
          if(dl.length() > 10)
          {
            double d =  Math.pow(getDiffSquareSum(dl,0)/dl.length(),0.5);
//            System.out.println("minSquare:"+d);
            if(minSquare < 0) 
            { 
          	   minSquare = d;
          	   minSn = i;
            } else if( d < minSquare)
            {
          	   minSquare = d;
          	   minSn = i;
            }
         }
      }
     // System.out.println("minSn:"+minSn);
     if(minSn < 1) return null;
      return  alf.culMALine(minSn);
   }
   
   public Line dynamicTune(Line L)
   {
     return L;
   }
   
}      

