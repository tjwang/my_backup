package stock.test;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import stock.fight.*;

public class CulStatisticKD {
 	
 static public void main(String[] arg)throws Exception{
     StockKD s = new StockKD(arg[0]);
     MALine mal = s.culMALine(8);
     KDLine kdL = s.culKDValues(15,25,5);
//     Enumeration e = mal.getCrossPoints();
     PrintStream ps = new PrintStream(new FileOutputStream("c:\\money\\fight\\statistickd"+arg[1]+".txt"));
     Enumeration e = mal.getVPoints();
     MAValue mav = null;
     MAValue premav = null;
     KDLevel kdl = new KDLevel(arg[0],arg[1]);
     KDDLevel kddl = new KDDLevel(arg[0],arg[1]);
     KDSLevel kdsl = new KDSLevel(arg[0],arg[1]);
     VolLevel vl = new VolLevel(arg[0],arg[1]);
     VolULevel vul = new VolULevel(arg[0],arg[1]);
     VolDLevel vdl = new VolDLevel(arg[0],arg[1]);
     int serial = 0;
     int prevCurve = 0;
     float prevKDlevel = 0;
     float prevKDD =0;
     float prevKDS  =0;
     float prevVolLevel = 0;
     float prevVolULevel = 0;
     float prevVolDLevel = 0;
     int   prevKDGcross = 0;
     int   prevKDDcross = 0; 
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

          ps.println(arg[0]+","+serial+","+premav.getDate()+","+premav.getTime()+","+
                     prevCurve+","+prevKDlevel+","+prevKDD+","+prevKDS+","+prevKDGcross+","+prevKDDcross+","+
                     prevVolLevel+","+prevVolULevel+","+prevVolDLevel+","+
                     curCurve+","+KDlevel+","+KDD+","+KDS+","+KDGcross+","+KDDcross+","+
                     VolLevel+","+VolULevel+","+VolDLevel+"," );
          prevCurve = curCurve;
          prevKDlevel = KDlevel ;
          prevKDD = KDD ;
          prevKDS = KDS ;
          prevKDGcross = KDGcross ;
          prevKDDcross = KDDcross ;
          prevVolLevel = VolLevel ;
          prevVolULevel =VolULevel;
          prevVolDLevel =VolDLevel;
          serial ++;
        }
        premav = mav;
     }
//================================================================================================
//   DB  Work 
//================================================================================================
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate("delete from pStatisticsKD where snum='"+arg[0]+"' ") ;
     dbstmt.executeUpdate("load data infile \"c:\\\\money\\\\fight\\\\statistickd"+
                       arg[1]+".txt\" into table pStatisticsKD FIELDS  TERMINATED by ',';") ;



 }

}