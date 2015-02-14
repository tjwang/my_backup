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

public class T30_tStati {
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
      int totalsample = 0;
      double totalgsample = 0;
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
      	 
         T30_sFactory   fac1 = new T30_sFactory(ds000r.snum,arg[0]);
         T30_oFactory   fac2 = new T30_oFactory(ds000r.snum,arg[0]);
         LineGroup basicGroup =  LG_Maker.basic(fac1,fac2);
         Line markLine =   LG_Maker.markLine(basicGroup);
         Line mainLine =   LG_Maker.mainLine(basicGroup);
         T30_Pattern   ptn1 = new T30_Pattern(markLine, basicGroup);
         PatternLine pl = (PatternLine)mainLine.pattern(ptn1);
         double good = pl.statistics();
         double gsample = good * pl.length();
         System.out.println(ds000r.snum+" sample:"+ pl.length() + " rate: "+ good + " good sampe "+gsample); 
         totalsample += pl.length();
         totalgsample += gsample;
      }
      System.out.println("toatl "+ total + " got "+ got);   
      System.out.println("totalsample "+ totalsample + " totalgsample "+ totalgsample);   
      System.out.println("totalrate "+ totalgsample/totalsample );   
}

    /*     
         
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
         
         MarkPattern mp = new MarkPattern(wpl);
         PatternLine mpl = (PatternLine)i5_mid.pattern(mp);
         if(mpl.length() > 0)
         {
            PatternValue ptv = (PatternValue)mpl.valueAt(mpl.length()-1);
            if(ptv.getEndValue()==i5_mid.valueAt(i5_mid.length()-1) && i5_mid.valueAt(i5_mid.length()-1).getValue()<0)
            {
                  System.out.println(" snum "+ds000r.snum+" "+(new String(ds000r.sname.getBytes("ISO8859-1"),"MS950"))+ " "+ds000r.capital+" OK! -->" + ray[1].valueAt(0).getDateValue());
                  got ++;
            }   
         }
      */

}
