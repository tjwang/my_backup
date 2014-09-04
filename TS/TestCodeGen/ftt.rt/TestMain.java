import stock.db.*;
import stock.fight.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TestMain {
 

   static public void main(String[] arg)throws Exception{

      System.out.println("TestMain");
//-      FTTFD_Pattern fdp = new FTTFD_Pattern(5,"vol.info");
      FTTSta_Pattern fstap = new FTTSta_Pattern(5,"vol","vol_sta.info");
      FTTSta_Pattern fstap2 = new FTTSta_Pattern(5,"vol",null);
      FTTSta_Pattern fstap3 = new FTTSta_Pattern(5,"vol",null);
//-      fdp.setOnlyLogInfo(true);
 //     fstap.setOnlyLogInfo(true);  //-
      
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
         Line l_tx = fac1.getLineByCode("TX",1);
         Line l_tx0x4 = null;
         if(l_tx == null) continue;
         
         if(Integer.parseInt(dateStr) > 20140716)
         {
             l_tx0x4 = fac1.getLineByCode("TX084",1);
         } else
         {
             l_tx0x4 = fac1.getLineByCode("TX074",1);
         }
         Line l_200 = fac1.getLineByCode("220",1);

//        System.out.println("Dumpping TX....");
//        l_tx.dump();
//        DomainValue dv = 
          System.out.println("Dumpping Volunm....");
          l_200 = l_200.sub(l_200.mappingIdx(l_200.valueAfter(new DomainValue(l_200.valueAt(0).getDomain(),GMethod.s2d(dateStr,"90000").getTime()))),
                        l_200.length()-1);
          //    PatternLine pl = l_200.pattern(fdp);
          //    pl.dump();

 //-         PatternLine pl2 = l_200.patternByStep(fdp);
           PatternLine pl2 = l_200.patternByStep(fstap); //-
          fstap.addRefLine(l_200,fstap2); //-
 //         fstap.addRefLine(l_tx0x4,fstap3); //-
          pl2.dump();
          fstap.cleanRefLine();
          //     System.out.println("Dumpping TX074....");
          //     l_tx074.dump();
          
       }
  }
}  