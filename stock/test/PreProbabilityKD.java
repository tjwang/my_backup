package stock.test;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import stock.fight.*;

public class PreProbabilityKD {
 	
 static public void main(String[] arg)throws Exception{
     StockKD s = new StockKD(getStockData(arg[0]));
     MALine mal = s.culMALine(8);
     KDLine kdL = s.culKDValues(15,25,5);
     Enumeration e = mal.getVPoints();
     MAValue mav = null;
     MAValue premav = null;
     KDLevel kdl = new KDLevel(arg[0],arg[1]);
     KDDLevel kddl = new KDDLevel(arg[0],arg[1]);
     KDSLevel kdsl = new KDSLevel(arg[0],arg[1]);
     VolLevel vl = new VolLevel(arg[0],arg[1]);
     VolULevel vul = new VolULevel(arg[0],arg[1]);
     VolDLevel vdl = new VolDLevel(arg[0],arg[1]);
     int prevCurve = 0;
     float prevKDlevel = 0;
     float prevKDD =0;
     float prevKDS  =0;
     float prevVolLevel = 0;
     float prevVolULevel = 0;
     float prevVolDLevel = 0;
     int   prevKDGcross = 0;
     int   prevKDDcross = 0; 
     float err = 0;
     float correct = 0;
     int pcv  = 0;
     while(e.hasMoreElements()){
        mav = (MAValue)e.nextElement();
        if(premav !=null){
        	int curCurve = 0 ; 
        	float KDlevel = 0;
          int KDGcross = 0;
          int KDDcross = 0;
        	float KDD = 0;
        	float KDS = 0;
          float VolLevel = 0;
          float VolULevel = 0;
          float VolDLevel = 0;
          KDValue kd1 = kdL.getKDFromMA(premav);
          KDValue kd2 = kdL.getKDFromMA(mav);
          Enumeration e2 = kdL.getCrossPoints(premav, mav);
          MAValue tm = mal.getMaxDistance(premav,mav);

          if(tm.isAbove()){
             curCurve = 1;
          }else if (tm.isBelow()){
             curCurve = -1;
          }
          KDlevel = kdl.checkLevel(tm.getDiff()) ;
          KDD = kddl.checkLevel(mal.getPeriod(premav,mav));
          KDS = kdsl.checkLevel(mal.getSlope(premav,mav));
          VolLevel = vl.checkLevel(mal.getVolume(premav,mav));
          VolULevel = vul.checkLevel(mal.getVolume(premav,tm));   
          VolDLevel = vdl.checkLevel(mal.getVolume(tm,mav));   
          while(e2.hasMoreElements()){
             KDValue kdv = (KDValue)e2.nextElement();
             if(kdv.isAbove()){
                KDGcross ++;   
             }else{
                KDDcross ++;
             }
          }
          if(mav.getDate().equals(arg[2])){
            if(pcv !=0){
               if(pcv == curCurve) correct ++;
               else err ++;
            }
          }  
          
          if(mav.getDate().equals(arg[2])){
             PStatisticsKD_Rec pkdr = new PStatisticsKD_Rec();
             System.out.println(mav.getDate()+" - "+mav.getTime()+":");
             int total = pkdr.SelectCountByCondition("prevKDlevel="+KDlevel+" and prevKDD="+KDD+" and prevKDS="+KDS);             
             int up = pkdr.SelectCountByCondition("Curve=1 and   prevKDlevel="+KDlevel+" and prevKDD="+KDD+" and prevKDS="+KDS);             
             int down = pkdr.SelectCountByCondition("Curve=-1 and  prevKDlevel="+KDlevel+" and prevKDD="+KDD+" and prevKDS="+KDS);             
             System.out.println("   total = "+ total +"  up = "+ up + " down = " +down);
             if(up > down) pcv = 1;
             if(up < down) pcv = -1;
             if(up == down) pcv = 0;
          }

          prevCurve = curCurve;
          prevKDlevel = KDlevel ;
          prevKDD = KDD ;
          prevKDS = KDS ;
          prevKDGcross = KDGcross ;
          prevKDDcross = KDDcross ;
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