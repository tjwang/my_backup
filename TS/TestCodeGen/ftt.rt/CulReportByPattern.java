import stock.db.*;
import stock.fight.*;
import stock.tool.*;
import stock.pattern.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class CulReportByPattern {
 

   static public void main(String[] arg)throws Exception{

      System.out.println("CulReport");
//      FTTFD_Pattern fdp = new FTTFD_Pattern(5,arg[1]);
      AbstractFtt_Pattern  fdp = (AbstractFtt_Pattern)Class.forName(arg[3]).getConstructor(String.class).newInstance(arg[1]);
      fdp.setOnlyLogInfo(true);
      
      long todate = (new Date()).getTime();
      Date workdate = GMethod.s2d(arg[0]);
      while(workdate.getTime() < todate)
      {      
         String dateStr = GMethod.d2s(workdate);
         System.out.println(dateStr);
         workdate = new Date(workdate.getTime()+86400000);
         FTT_sFactory   fac1 = null;
         
         try{
            fac1 = new FTT_sFactory(dateStr,dateStr);
         }catch(Exception e)
         {
            e.printStackTrace();
            continue;
         }
         Line l_tx = null;
         if(arg[2].indexOf("TX0") == 0)
         {
            l_tx = fac1.getLineByCode("TX074",1);
            if(l_tx == null) l_tx = fac1.getLineByCode("TX084",1);
            if(l_tx == null) l_tx = fac1.getLineByCode("TX094",1);
         } else
         {
            l_tx = fac1.getLineByCode(arg[2],1);
         }
//         l_tx.dump();
//         Line l_tx0x4 = null;
//         if(l_tx == null) continue;
         
//         if(Integer.parseInt(dateStr) > 20140716)
//         {
//             l_tx0x4 = fac1.getLineByCode("TX084",1);
//         } else
//         {
//             l_tx0x4 = fac1.getLineByCode("TX074",1);
//         }
//         Line l_200 = fac1.getLineByCode("220",1);

 //        l_200 = l_200.sub(l_200.mappingIdx(l_200.valueAfter(new DomainValue(l_200.valueAt(0).getDomain(),GMethod.s2d(dateStr,"90000").getTime()))),
 //                       l_200.length()-1);
         l_tx = l_tx.sub(l_tx.mappingIdx(l_tx.valueAfter(new DomainValue(l_tx.valueAt(0).getDomain(),GMethod.s2d(dateStr,"90000").getTime()))),
                        l_tx.length()-1);
         PatternLine pl2 = l_tx.patternByStep(fdp);
         pl2.dump();
          
       }
  }
}  