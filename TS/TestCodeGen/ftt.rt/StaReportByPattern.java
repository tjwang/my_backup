import stock.db.*;
import stock.fight.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class StaReportByPattern {
 

   static public void main(String[] arg)throws Exception{

      System.out.println("StaRepor");

      AbstractSta_Pattern pri_ptn = (AbstractSta_Pattern)Class.forName(arg[2]).getConstructor(String.class).newInstance(arg[1]);//new FTTSta_Pattern(5,"vol",arg[1]);
      AbstractSta_Pattern rst_ptn = (AbstractSta_Pattern)Class.forName(arg[4]).getConstructor(String.class).newInstance("");//new FTTSta_Pattern(5,"vol",null);
      
      pri_ptn.setOnlyLogInfo(true);
      rst_ptn.setOnlyLogInfo(true);
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
         Line l1 = null;
         if(arg[3].indexOf("TX0") == 0)
         {
            l1 = fac1.getLineByCode("TX074",1);
            if(l1 == null) l1 = fac1.getLineByCode("TX084",1);
            if(l1 == null) l1 = fac1.getLineByCode("TX094",1);
         } else
         {
            l1 = fac1.getLineByCode(arg[3],1);
         }
         Line l2 = null;
         if(arg[5].indexOf("TX0") == 0)
         {
            l2 = fac1.getLineByCode("TX074",1);
            if(l2 == null) l2 = fac1.getLineByCode("TX084",1);
            if(l2 == null) l2 = fac1.getLineByCode("TX094",1);
         } else
         {
            l2 = fac1.getLineByCode(arg[5],1);
         }
         if(l1 == null || l2 == null) continue;
         
         l1 = l1.sub(l1.mappingIdx(l1.valueAfter(new DomainValue(l1.valueAt(0).getDomain(),GMethod.s2d(dateStr,"90000").getTime()))),
                        l1.length()-1);
          PatternLine pl2 = l1.patternByStep(pri_ptn); //-
          pri_ptn.addRefLine(l2,rst_ptn); //-
          pl2.dump();
          pri_ptn.cleanRefLine();
       }
  }
}  