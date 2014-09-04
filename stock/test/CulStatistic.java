package stock.test;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import stock.fight.*;

public class CulStatistic {
 	
 static public void main(String[] arg)throws Exception{
     StockKD s = new StockKD(arg[0]);
     MALine mal = s.culMALine(8);
//     Enumeration e = mal.getCrossPoints();
     PrintStream ps = new PrintStream(new FileOutputStream("c:\\money\\fight\\statistic"+arg[1]+".txt"));
     Enumeration e = mal.getVPoints();
     MAValue mav = null;
     MAValue premav = null;
     DisLevel dsl = new DisLevel(arg[0],arg[1]);
     TimeLevel tml = new TimeLevel(arg[0],arg[1]);
     SlopeLevel spl = new SlopeLevel(arg[0],arg[1]);
     TSlopeLevel tspl = new TSlopeLevel(arg[0],arg[1]);
     int serial = 0;
     int prevCurve1 = 0;
     float prevDislevel1 = 0;
     float prevPeriodlevel1 =0;
     float prevSlopelevel1  =0;
     float prevTSlopelevel1  =0;
     int  prevCurve2  =0;
     float prevDislevel2    =0;
     float prevPeriodlevel2 =0;
     float prevSlopelevel2  =0;
     while(e.hasMoreElements()){
        mav = (MAValue)e.nextElement();
        if(premav !=null){
        	int curCurve = 0 ; 
        	float Dislevel = 0;
        	float Periodlevel = 0;
        	float Slopelevel = 0;
        	float TSlopelevel = 0;
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
          ps.println(arg[0]+","+serial+","+premav.getDate()+","+premav.getTime()+","+
                     prevCurve1+","+prevDislevel1+","+prevPeriodlevel1+","+prevSlopelevel1+","+prevTSlopelevel1+","+
                     prevCurve2+","+prevDislevel2+","+prevPeriodlevel2+","+prevSlopelevel2+","+
                     curCurve+","+Dislevel+","+Periodlevel+","+Slopelevel+","+TSlopelevel+",");
          prevCurve2 = prevCurve1;
          prevDislevel2 = prevDislevel1 ;
          prevPeriodlevel2 = prevPeriodlevel1 ;
          prevSlopelevel2 = prevSlopelevel1 ;
          prevCurve1 = curCurve;
          prevDislevel1 = Dislevel ;
          prevPeriodlevel1 = Periodlevel ;
          prevSlopelevel1 = Slopelevel ;
          prevTSlopelevel1 = TSlopelevel ;
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
     dbstmt.executeUpdate("delete from pStatistics where snum='"+arg[0]+"' ") ;
     dbstmt.executeUpdate("load data infile \"c:\\\\money\\\\fight\\\\statistic"+
                       arg[1]+".txt\" into table pStatistics FIELDS  TERMINATED by ',';") ;



 }

}