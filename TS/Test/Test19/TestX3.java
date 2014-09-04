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
	/*
static public void main(String[] arg)throws Exception{
      TestKD   skd = new TestKD("2454",arg[0],1);
      KLine kl = skd.culKLine();
      MALine m5 =  skd.culMALine(5);
      MALine m13 =  skd.culMALine(13);
      Line sub_m5 = m5.sub(m5.length()-120,m5.length()-1);
      Line sub_m13 = m13.sub(m13.length()-120,m13.length()-1);
      Line dif_1 = sub_m5.diff(sub_m13);
      MarkPattern wp = new MarkPattern(dif_1);
      PatternLine wl = (PatternLine)kl.pattern(wp);
      Line sl = wl.shadow();
     // wl.dump();
      for(int i=0;i<kl.length();i++)
      {
          System.out.println(i+"==========================");
          Value v1 = kl.valueAt(i);
          Value v2 = sl.valueAt(v1.getDomainValue());
          if(v2!=null)
          {
              Value v3 = dif_1.valueAt(v1.getDomainValue());
             v1.dump();
             v2.dump();
             v3.dump();

          }
      }
}
*/
static	Hashtable check_snum = null;

static void init_check(String filename) throws Exception
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
         KLine kl = skd.culKLine();
         LineGroup lg = new LineGroup(kl);
         lg.addLine(skd.culMALine(5));
         lg.addLine(skd.culMALine(8));
         lg.addLine(skd.culMALine(13));
         lg.addLine(skd.culMALine(21));
         lg.setupLine();
         Line[] ray = lg.getSubLineArray();
         Line[][] dif_array = lg.diff();
         Line pl = dif_array[0][1].pos().sign().and(dif_array[1][2].pos().sign()).and(dif_array[2][3].pos().sign());

//.and(dif_array[1][2]);
 //        .and());
         MarkPattern wp = new MarkPattern(pl);
         PatternLine wl = (PatternLine)kl.pattern(wp);
         if(wl.length() > 0)
         {
            PatternValue ptv = (PatternValue)wl.valueAt(wl.length()-1);
            if(ptv.getEndValue()==kl.valueAt(kl.length()-1) && ptv.getEndIdx()-ptv.getStartIdx() < 5 )
            {
               if(wl.length() > 1)
               {
                  // PatternValue ptv2 = (PatternValue)wl.valueAt(wl.length()-2);
                  // if(ptv.getStartIdx() - ptv2.getEndIdx() > 10)
                   {
                     KValue k1 = (KValue)kl.valueAt(kl.length()-1);
                     KValue k2 = (KValue)kl.valueAt(kl.length()-2);
                     if(k1.getClose() < k1.getOpen() && k1.getClose() < k2.getClose())
                     {
                        System.out.println(" snum "+ds000r.snum+" "+(new String(ds000r.sname.getBytes("ISO8859-1"),"MS950"))+ " "+ds000r.capital+" OK! -->" + ray[1].valueAt(0).getDateValue());
                         got ++;
                     }
                   }
               } else
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