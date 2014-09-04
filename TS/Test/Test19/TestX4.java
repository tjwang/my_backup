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
      	 total++;
         ds000r=(Ds000_Rec)e.nextElement();
         if(check_snum != null && check_snum.get(ds000r.snum) == null)
         {
            continue;
         }
         TestKD   skd = new TestKD(ds000r.snum,arg[0],1);
         TestI2KD   ibkd = new TestI2KD(ds000r.snum,arg[0],5);
         TestI2KD   iskd = new TestI2KD(ds000r.snum,arg[0],6);
         KLine kl = skd.culKLine();
         LineGroup lg = new LineGroup(kl);
         lg.addLine(ibkd.culMALine(5));
         lg.addLine(iskd.culMALine(5));
         lg.setupLine();
         Line[] ray = lg.getSubLineArray();
         Line[][] dif_array = lg.diff();
         Line pl = dif_array[0][1].pos();

         MarkPattern wp = new MarkPattern(pl);
         PatternLine wl = (PatternLine)kl.pattern(wp);
         if(wl.length() > 0)
         {
            PatternValue ptv = (PatternValue)wl.valueAt(wl.length()-1);
            if(ptv.getEndValue()==kl.valueAt(kl.length()-1) && ptv.getEndIdx()-ptv.getStartIdx() < 3 )
            {
              /* if(wl.length() > 1)
               {
                   PatternValue ptv2 = (PatternValue)wl.valueAt(wl.length()-2);
                   if(ptv.getStartIdx() - ptv2.getEndIdx() > 20)
                   {
                     KValue k1 = (KValue)kl.valueAt(kl.length()-1);
                     KValue k2 = (KValue)kl.valueAt(kl.length()-2);
                    // if(k1.getClose() < k1.getOpen() && k2.getClose() < k2.getOpen())
                     {
                        System.out.println(" snum "+ds000r.snum+" "+(new String(ds000r.sname.getBytes("ISO8859-1"),"MS950"))+ " "+ds000r.capital+" OK! -->" + ray[1].valueAt(0).getDateValue());
                         got ++;
                     }
                   }
               } else*/
               {
                     KValue k1 = (KValue)kl.valueAt(kl.length()-1);
                     KValue k2 = (KValue)kl.valueAt(kl.length()-2);
                     if(k1.getClose() < k1.getOpen() && k1.getClose() < k2.getClose())
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