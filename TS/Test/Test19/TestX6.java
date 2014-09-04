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

public class TestX6 {
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
      Enumeration e = ds000r.SelectBySQL("select * from ds000  order by snum");
      int total = 0;
      int got = 0;
      if(arg.length > 1)
      {
         init_check(arg[1]);
      }
      while(e.hasMoreElements())
      {
         ds000r=(Ds000_Rec)e.nextElement();
         if(check_snum != null && check_snum.get(ds000r.snum) == null)
         {
            continue;
         }
      	 total++;
      	 
         TestKD   skd = new TestKD(ds000r.snum,arg[0],1);
         WeekPattern wp = new WeekPattern();
         KLine kl = skd.culKLine();
         
         if(kl.length()<=20) 
         {
         	  continue;
         }
         Line rsvl = kl.rsv(20);
         Line kdkl = rsvl.macd(5,3);
         Line kddl = kdkl.macd(4,2);

         Line wl = kl.pattern(wp);
         Line m3 =  wl.avg(3);
         Line m8 =  wl.avg(8);
         Line m13 =  wl.avg(13);
         LineGroup lg = new LineGroup(kl);
         lg.addLine(m3);
         lg.addLine(m8);
         lg.addLine(m13);
         lg.setupLine();
         Line[] ray = lg.getSubLineArray();
         Line[][] dif_array = lg.diff();
         
         Line wpl = dif_array[0][1].pos().sign().and(dif_array[1][2].pos().sign());
         wpl = wpl.expands(kl);
//         wpl.dump();
         
         
         LineGroup lg2 = new LineGroup(kl);
         lg2.addLine(kdkl);
         lg2.addLine(kddl);
         Line[][] dif_array2 = lg2.diff();

         Line kdpl= dif_array2[0][1].pos().sign();
         Line trigerL = kdpl.diff(1).pos();
         Line kdcpl = kdpl.constant(0);
         for(int i=-4;i<=0;i++)
         {
            kdcpl = kdcpl.add(trigerL,i); 
         }

         LineGroup lg3 = new LineGroup(kl);
         if(wpl == null || kdcpl == null) continue;
         lg3.addLine(wpl);
         lg3.addLine(kdcpl);
         lg3.setupLine();
         Line[] mray = lg3.getSubLineArray();
         Line pl=mray[0].and(mray[1]);
         
         MarkPattern mp = new MarkPattern(pl);
         PatternLine mpl = (PatternLine)kl.pattern(mp);
         if(mpl.length() > 0)
         {
            PatternValue ptv = (PatternValue)mpl.valueAt(mpl.length()-1);
            if(ptv.getEndValue()==kl.valueAt(kl.length()-1) && ptv.getEndIdx()-ptv.getStartIdx() < 5 )
            {
               {
                     KValue k1 = (KValue)kl.valueAt(kl.length()-1);
                     KValue k2 = (KValue)kl.valueAt(kl.length()-2);
                    // if(k1.getClose() < k1.getOpen() && k1.getClose() < k2.getClose())
                     {
                        System.out.println(" snum "+ds000r.snum+" "+(new String(ds000r.sname.getBytes("ISO8859-1"),"MS950"))+ " "+ds000r.capital+" OK! -->" + ray[1].valueAt(0).getDateValue());
                         got ++;
                     }
               }
            }   
         }
 
      }
      System.out.println("toatl "+ total + " got "+ got);   
}


}