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

public class TestX3 {
static public void main(String[] arg)throws Exception{
      Ds000_Rec ds000r =new Ds000_Rec();
      Enumeration e = ds000r.SelectBySQL("select * from ds000 order by snum");
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
  //       Line kml1 = skd.culMALine(3);
 //        Line kml2 = skd.culMALine(8);
 //        Line kml3 = skd.culMALine(13);
         Line  b1 = ibkd.culMALine(5);
         Line  s1 = iskd.culMALine(5);
         LineGroup lg = new LineGroup(kl);
   //      LineGroup lg2 = new LineGroup(kl);
         lg.addLine(b1);
         lg.addLine(s1);
         lg.setupLine();
         Line[] ray = lg.getSubLineArray();
         Line[][] dif_array = lg.diff();
/*
         lg2.addLine(kml1);
         lg2.addLine(kml2);
         lg2.addLine(kml3);
         lg2.setupLine();
         Line[][] dif_array2 = lg2.diff();
*/
//         Line pl = dif_array[0][1].pos().sign().and(dif_array[1][2].pos().sign()).and(dif_array[2][3].pos().sign());
         Line pl = dif_array[0][1].pos();
  /*
         Line pl2 = dif_array2[0][1].pos().sign().and(dif_array2[1][2].pos().sign());
         if(pl.length() > pl2.length())
         {
             pl = pl.sub(pl.length()-pl2.length(),pl.length()-1);
         } else if(pl2.length() > pl.length())
         {
             pl2 = pl2.sub(pl2.length()-pl.length(),pl2.length()-1);
         } 
         pl = pl.and(pl2); 
    */
         NMarkPattern np = new NMarkPattern(pl);
         PatternLine wl = (PatternLine)kl.pattern(np);
//         wl.dump(); 
        if(wl.length() > 0)
        {
           ok_total += wl.statistics() *  wl.length();
           count_total += wl.length();
           System.out.println(wl.length()+" p :"+wl.statistics());
        }
      }
      System.out.println("ok_total:"+ok_total+" count_total:"+count_total+" p:"+(ok_total/count_total));
}


}