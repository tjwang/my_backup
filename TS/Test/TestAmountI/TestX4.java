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

public class TestX4 {
static public void main(String[] arg)throws Exception{
      Ds000_Rec ds000r =new Ds000_Rec();
      Enumeration e = ds000r.SelectBySQL("select * from ds000 where snum='2454' order by snum");
      int total = 0;
      int got = 0;
      double ok_total = 0;
      double count_total = 0;
      while(e.hasMoreElements())
      {
      	 total++;
         ds000r=(Ds000_Rec)e.nextElement();
         TestKD   skd = new TestKD(ds000r.snum,arg[1]);
         TestI2KD   ibkd = new TestI2KD(ds000r.snum,arg[1],5);
         TestI2KD   iskd = new TestI2KD(ds000r.snum,arg[1],6);
         KLine kl = skd.culKLine();
         Line  b1 = ibkd.culMALine(5);
         Line  s1 = iskd.culMALine(5);
         LineGroup lg = new LineGroup(kl);
         lg.addLine(b1);
         lg.addLine(s1);
         lg.setupLine();
         Line[] ray = lg.getSubLineArray();
         Line[][] dif_array = lg.diff();
         Line pl = dif_array[0][1].pos();
         NMarkPattern np = new NMarkPattern(pl);
         FilterPattern fp = new FilterPattern(0,-1);
         PatternLine wl = (PatternLine)kl.pattern(np);
         Line sl = wl.pattern(fp);
    //     wl.dump();
         sl.dump();
         System.out.println("=============================================");
         sl=sl.shadow();
         sl=sl.shadow();
         sl.dump();
      }
}


}