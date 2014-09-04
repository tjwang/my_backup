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
static	Hashtable check_snum = null;

static void init_check(String filename)  throws Exception
{
      BufferedReader d
          = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
      String snum = null;
      check_snum = new Hashtable();
      while((snum=d.readLine())!=null)
      {
         check_snum.put(snum.trim(),snum.trim());
      }
}

static public void main(String[] arg)throws Exception{
      Ds000_Rec ds000r =new Ds000_Rec();
//      Enumeration e = ds000r.SelectBySQL("select * from ds000  where snum=2454 order by snum");
      Enumeration e = ds000r.SelectBySQL("select * from ds000  order by snum");
//      Enumeration e = ds000r.SelectBySQL("select ds000.snum as snum,ds000.sname as sname,ds000.capital*rp as capital from ds000,plast2 where capital < 30000000 and plast2.snum=ds000.snum and date='20131227' order by capital desc limit 200  ;");
      int total = 0;
      int got = 0;
      double ok_total = 0;
      double count_total = 0;
      int ok_count = 0;
      if(arg.length > 1)
      {
         init_check(arg[1]);
      }
      while(e.hasMoreElements())
      {
      	 total++;
         ds000r=(Ds000_Rec)e.nextElement();
         if(check_snum != null && check_snum.get(ds000r.snum) == null)
         {
            continue;
         }
         TestKD   skd = new TestKD(ds000r.snum,arg[0],1);
         WeekPattern wp = new WeekPattern();
         KLine kl = skd.culKLine();

         Line rsvl = kl.rsv(20);
         Line kdkl = rsvl.macd(5,2);
         Line kddl = kdkl.macd(4,2);
         
         Line wl = kl.pattern(wp);
         Line m5 =  wl.avg(5);
         Line m8 =  wl.avg(8);
         Line m13 =  wl.avg(13);
         
         LineGroup lg = new LineGroup(kl);
         lg.addLine(m5);
         lg.addLine(m8);
         lg.addLine(m13);
         lg.setupLine();
         Line[] ray = lg.getSubLineArray();
         Line[][] dif_array = lg.diff();

         LineGroup lg2 = new LineGroup(kl);
         lg2.addLine(kdkl);
         lg2.addLine(kddl);
         Line[][] dif_array2 = lg2.diff();

         Line kdpl= dif_array2[0][1].pos().sign();
         Line wpl = dif_array[0][1].pos().sign().and(dif_array[1][2].pos().sign());
         Line kml = kdkl.diff(kdkl.constant(80.1)).neg().sign().mul(kdkl.constant(-1)).pos();
         Line trigerL = kdpl.diff(1).pos();
         Line kdcpl = kdpl.constant(0);
         for(int i=-3;i<3;i++)
         {
            kdcpl = kdcpl.add(trigerL,i); 
         }
         Line wmpl = wpl.expands(kl);
         LineGroup lg3 = new LineGroup(kl);
         if(wmpl == null || kml == null || kdcpl == null) continue;
         lg3.addLine(wmpl);
         lg3.addLine(kml);
         lg3.addLine(kdcpl);
         lg3.setupLine();
         Line[] mray = lg3.getSubLineArray();
         Line fpl=mray[0].and(mray[1]).and(mray[2]);
         
//         fpl.dump();
//          mray[1].dump();
//         NMarkPatternX5 np = new NMarkPatternX5(fpl,kl);
         NMarkPattern np = new NMarkPattern(fpl);
         PatternLine rslt = (PatternLine)kl.pattern(np);
      //   rslt.dump();
         double p = rslt.statistics();
         ok_total += p *  rslt.length();
         count_total += rslt.length();
         if(p>0.5) ok_count++;
         System.out.println(ds000r.snum+" "+rslt.length()+" p :"+rslt.statistics());
      }
      System.out.println("toatl "+ total + " got "+ got);   
      System.out.println("ok_total:"+ok_total+" ok_count:"+ok_count+" count_total:"+count_total+" p:"+(ok_total/count_total));
}


}