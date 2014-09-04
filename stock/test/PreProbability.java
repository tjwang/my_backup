package stock.test;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import stock.fight.*;

public class PreProbability {
 	
 static public void main(String[] arg)throws Exception{
 	   //DBConnection.debug = true;
     StockKD s = new StockKD(getStockData(arg[0])); 
     MALine mal = s.culMALine(8);
     Enumeration e = mal.getVPoints();
     MAValue mav = null;
     MAValue premav = null;
     DisLevel dsl = new DisLevel(arg[0],arg[1]);
     TimeLevel tml = new TimeLevel(arg[0],arg[1]);
     SlopeLevel spl = new SlopeLevel(arg[0],arg[1]);
     VolLevel vl = new VolLevel(arg[0],arg[1]);
     VolULevel vul = new VolULevel(arg[0],arg[1]);
     VolDLevel vdl = new VolDLevel(arg[0],arg[1]);
     int prevCurve1 = 0;
     float prevDislevel1 = 0;
     float prevPeriodlevel1 =0;
     float prevSlopelevel1  =0;
     float prevTSlopelevel1  =0;
     float prevVolLevel = 0;
     float prevVolULevel = 0;
     float prevVolDLevel = 0;
     int prevCurve2  =0;
     float prevDislevel2    =0;
     float prevPeriodlevel2 =0;
     float prevSlopelevel2  =0;
     float err = 0;
     float correct = 0;
     int pcv  = 0;
     while(e.hasMoreElements()){
        mav = (MAValue)e.nextElement();
        if(premav !=null){
        	int curCurve = 0 ; 
        	float Dislevel = 0;
        	float Periodlevel = 0;
        	float Slopelevel = 0;
        	float TSlopelevel = 0;
        	float VolLevel = 0;
        	float VolULevel = 0;
        	float VolDLevel = 0;
        	
          MAValue tm = mal.getMaxDistance(premav,mav);
          if(tm.isAbove()){
             curCurve = 1;
          }else if (tm.isBelow()){
             curCurve = -1;
          }
          if(mav.getDate().equals(arg[2])){
            if(pcv !=0){
               if(pcv == curCurve) correct ++;
               else err ++;
            }
          }  
          Dislevel = dsl.checkLevel(tm.getDiff()) ;
          Periodlevel = tml.checkLevel(mal.getPeriod(premav,mav));
          Slopelevel = spl.checkLevel(mal.getSlope(premav,mav));
          TSlopelevel = spl.checkLevel(mal.getTSlope(mav));
          VolLevel = vl.checkLevel(mal.getVolume(premav,mav));
          VolULevel = vul.checkLevel(mal.getVolume(premav,tm));   
          VolDLevel = vdl.checkLevel(mal.getVolume(tm,mav));   
          
          if(mav.getDate().equals(arg[2])){
             PStatistics_Rec psr = new PStatistics_Rec();
             PStatisticsV_Rec psvr = new PStatisticsV_Rec();
             System.out.println(mav.getDate()+" - "+mav.getTime()+":");
//             int total = psr.SelectCountByCondition("prevCurve1="+curCurve+"  and prevCurve2="+prevCurve1);             
//             int up = psr.SelectCountByCondition("Curve=1 and  prevCurve1="+curCurve+" and prevCurve2="+prevCurve1);             
//             int down = psr.SelectCountByCondition("Curve=-1 and  prevCurve1="+curCurve+" and prevCurve2="+prevCurve1);             
//             int total = psr.SelectCountByCondition("prevCurve1="+curCurve+" and prevSlopelevel1="+Slopelevel+" and prevCurve2="+prevCurve1+" and prevSlopelevel2="+prevSlopelevel1);             
//             int up = psr.SelectCountByCondition("Curve=1 and  prevCurve1="+curCurve+" and prevSlopelevel1="+Slopelevel+" and prevCurve2="+prevCurve1+" and prevSlopelevel2="+prevSlopelevel1);             
//             int down = psr.SelectCountByCondition("Curve=-1 and  prevCurve1="+curCurve+" and prevSlopelevel1="+Slopelevel+" and prevCurve2="+prevCurve1+" and prevSlopelevel2="+prevSlopelevel1);             
//             int total = psr.SelectCountByCondition("prevSlopelevel1="+Slopelevel);             
//             int up = psr.SelectCountByCondition("Curve=1 and  prevSlopelevel1="+Slopelevel);             
//             int down = psr.SelectCountByCondition("Curve=-1 and  prevSlopelevel1="+Slopelevel);             
//             int total = psr.SelectCountByCondition("prevDislevel1="+Dislevel+" and prevSlopelevel1="+Slopelevel);             
//             int up = psr.SelectCountByCondition("Curve=1 and  prevDislevel1="+Dislevel+" and prevSlopelevel1="+Slopelevel);             
//             int down = psr.SelectCountByCondition("Curve=-1 and  prevDislevel1="+Dislevel+" and prevSlopelevel1="+Slopelevel);             
             int total = psr.SelectCountByCondition("prevDislevel1="+Dislevel+" and prevSlopelevel1="+Slopelevel+" and prevTSlopelevel1="+TSlopelevel);             
             int up = psr.SelectCountByCondition("Curve=1 and  prevDislevel1="+Dislevel+" and prevSlopelevel1="+Slopelevel+" and prevTSlopelevel1="+TSlopelevel);             
             int down = psr.SelectCountByCondition("Curve=-1 and  prevDislevel1="+Dislevel+" and prevSlopelevel1="+Slopelevel+" and prevTSlopelevel1="+TSlopelevel);             
             System.out.println("   total = "+ total +"  up = "+ up + " down = " +down);
             total = psvr.SelectCountByCondition(" prevVlevel="+VolLevel+" and prevTSlopelevel="+TSlopelevel);             
             up = psvr.SelectCountByCondition("Curve=1 and  prevVlevel="+VolLevel+" and prevTSlopelevel="+TSlopelevel);             
             down = psvr.SelectCountByCondition("Curve=-1 and  prevVlevel="+VolLevel+" and prevTSlopelevel="+TSlopelevel);             
             System.out.println("   total = "+ total +"  up = "+ up + " down = " +down);
             if(up > down) pcv = 1;
             if(up < down) pcv = -1;
             if(up == down) pcv = 0;
          }

          prevCurve2 = prevCurve1;
          prevDislevel2 = prevDislevel1 ;
          prevPeriodlevel2 = prevPeriodlevel1 ;
          prevSlopelevel2 = prevSlopelevel1 ;
          prevCurve1 = curCurve;
          prevDislevel1 = Dislevel ;
          prevPeriodlevel1 = Periodlevel ;
          prevSlopelevel1 = Slopelevel ;
          prevTSlopelevel1 = TSlopelevel ;
          prevVolLevel = VolLevel ;
          prevVolULevel =VolULevel;
          prevVolDLevel =VolDLevel;
        }
        premav = mav;
     }
     System.out.println("Total predict "+(correct+err)+" error:"+err+"("+(err/(correct+err))+") correct:"+correct+"("+(correct/(correct+err))+")");
//================================================================================================
//   DB  Work 
//================================================================================================

 }

static Vector getStockData(String snum)throws Exception {
   PLDayk_Rec pkr= new PLDayk_Rec();
   Vector dayKdata = new Vector();
   Enumeration e = null;
   e = pkr.SelectBySQL("select * from  pLDayk2 where   snum='"+snum+"' order by date,time "  ); 
   while(e.hasMoreElements()){
   	  pkr = (PLDayk_Rec)e.nextElement();
      dayKdata.add(pkr);
   }
   return dayKdata;
}

}