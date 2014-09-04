import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.pattern.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestX5 {
static public void main(String[] arg)throws Exception{
      Ds000_Rec ds000r =new Ds000_Rec();
//      Enumeration e = ds000r.SelectBySQL("select * from ds000 where snum='2454' order by snum");
      Enumeration e = ds000r.SelectBySQL("select * from ds000  order by snum");
      int total = 0;
      int got = 0;
      double ok_total = 0;
      double count_total = 0;
      while(e.hasMoreElements())
      {
      	 total++;
         ds000r=(Ds000_Rec)e.nextElement();
         TestKD   skd = new TestKD(ds000r.snum,arg[0]);
         KLine kl = skd.culKLine();
         Line kml1 = skd.culMALine(8);
         Line kml2 = skd.culMALine(13);
         Line kml3 = skd.culMALine(21);
         LineGroup lg = new LineGroup(kl);
         lg.addLine(kml1);
         lg.addLine(kml2);
         lg.addLine(kml3);
         lg.setupLine();
         Line[] ray = lg.getSubLineArray();
         Line[][] dif_array = lg.diff();
         Line pl = dif_array[0][1].pos().sign().and(dif_array[1][2].pos().sign());
         NMarkPatternX5 np = new NMarkPatternX5(pl);
//         FilterPattern fp = new FilterPattern(0,-1);
         PatternLine wl = (PatternLine)kl.pattern(np);
//         Line sl = wl.pattern(fp);
//         wl.dump();
//         sl.dump();
      //   System.out.println("============================================="+wl.statistics()+" "+wl.length());
         ok_total += wl.statistics() *  wl.length();
         count_total += wl.length();
         System.out.println(wl.length()+" p :"+wl.statistics());
           
  //       sl=sl.shadow();
  //       sl=sl.shadow();
  //       sl.dump();
      }
      System.out.println("ok_total:"+ok_total+" count_total:"+count_total+" p:"+(ok_total/count_total));
}


}