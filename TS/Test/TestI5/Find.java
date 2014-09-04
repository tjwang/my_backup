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

public class Find {
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
      	 
         TestI5Factory   i5f = new TestI5Factory(ds000r.snum,arg[0]);
         WeekPattern wp = new WeekPattern();
         KLine kl = i5f.culKLine();

         if(kl.length()<=20) 
         {
         	  continue;
         }
         StockInfo5  ski5 = new StockInfo5(ds000r.snum,arg[0]);
         ski5.setupI5(5);
         Line i5_mid = ski5.getMidLine();
         Line ma5_l =  i5f.culMALine(5);

         if(i5_mid.length() > kl.length())
         {
            i5_mid = i5_mid.sub(i5_mid.length()-kl.length(),i5_mid.length()-1);
         } else
         {
            kl = (KLine)kl.sub(kl.length()-i5_mid.length(),kl.length()-1);
         }
         
         if(i5_mid.length() > ma5_l.length())
         {
            i5_mid = i5_mid.sub(i5_mid.length()-ma5_l.length(),i5_mid.length()-1);
         } else
         {
            ma5_l = ma5_l.sub(ma5_l.length()-i5_mid.length(),ma5_l.length()-1);
         }

         i5_mid = i5_mid.diff(ma5_l);
         
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
 
      }
      System.out.println("toatl "+ total + " got "+ got);   
}


}