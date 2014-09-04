package stock.test;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import stock.fight.*;

public class CulStatisticV {
 	
 static public void main(String[] arg)throws Exception{
     StockKD s = new StockKD(arg[0]);
     MALine mal = s.culMALine(8);
//     Enumeration e = mal.getCrossPoints();
     PrintStream ps = new PrintStream(new FileOutputStream("c:\\money\\fight\\statisticv"+arg[1]+".txt"));
     Enumeration e = mal.getVPoints();
     MAValue mav = null;
     MAValue premav = null;
     DisLevel dsl = new DisLevel(arg[0],arg[1]);
     TimeLevel tml = new TimeLevel(arg[0],arg[1]);
     SlopeLevel spl = new SlopeLevel(arg[0],arg[1]);
     TSlopeLevel tspl = new TSlopeLevel(arg[0],arg[1]);
     VolLevel vl = new VolLevel(arg[0],arg[1]);
     VolULevel vul = new VolULevel(arg[0],arg[1]);
     VolDLevel vdl = new VolDLevel(arg[0],arg[1]);
     int serial = 0;
     int prevCurve = 0;
     float prevDislevel = 0;
     float prevPeriodlevel =0;
     float prevSlopelevel  =0;
     float prevTSlopelevel  =0;
     float prevVolLevel = 0;
     float prevVolULevel = 0;
     float prevVolDLevel = 0;
      
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
          Dislevel = dsl.checkLevel(tm.getDiff()) ;
          Periodlevel = tml.checkLevel(mal.getPeriod(premav,mav));
          Slopelevel = spl.checkLevel(mal.getSlope(premav,mav));
          TSlopelevel = tspl.checkLevel(mal.getTSlope(mav));
          VolLevel = vl.checkLevel(mal.getVolume(premav,mav));
          VolULevel = vul.checkLevel(mal.getVolume(premav,tm));   
          VolDLevel = vdl.checkLevel(mal.getVolume(tm,mav));   

          ps.println(arg[0]+","+serial+","+premav.getDate()+","+premav.getTime()+","+
                     prevCurve+","+prevDislevel+","+prevPeriodlevel+","+prevSlopelevel+","+prevTSlopelevel+","+
                     prevVolLevel+","+prevVolULevel+","+prevVolDLevel+","+
                     curCurve+","+Dislevel+","+Periodlevel+","+Slopelevel+","+TSlopelevel+","+
                     VolLevel+","+VolULevel+","+VolDLevel+"," );
          prevCurve = curCurve;
          prevDislevel = Dislevel ;
          prevPeriodlevel = Periodlevel ;
          prevSlopelevel = Slopelevel ;
          prevTSlopelevel = TSlopelevel ;
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
     dbstmt.executeUpdate("delete from pStatisticsv where snum='"+arg[0]+"' ") ;
     dbstmt.executeUpdate("load data infile \"c:\\\\money\\\\fight\\\\statisticv"+
                       arg[1]+".txt\" into table pStatisticsv FIELDS  TERMINATED by ',';") ;



 }

}