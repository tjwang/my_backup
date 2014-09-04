import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class M2_tFinder {
 
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
         M2_sFactory   fac1 = new M2_sFactory(ds000r.snum,arg[0]);
         M2_oFactory   fac2 = new M2_oFactory(ds000r.snum,arg[0]);
         LineGroup basicGroup =  LG_Maker.basic(fac1,fac2);
         Line markLine =   LG_Maker.markLine(basicGroup);
         Line mainLine =   LG_Maker.mainLine(basicGroup);
         M2_Pattern   ptn1 = new M2_Pattern(markLine,basicGroup);
         try
         {
            PatternLine pl = (PatternLine)basicGroup.pattern(ptn1);
            if(pl.length() > 0)
            {
            	 got++;
            	 System.err.println(ds000r.snum);   
               pl.dump();
            }
         }catch(Exception xe)
         {
            xe.printStackTrace();
         }
         /*
         double good = pl.statistics();
         double gsample = good * pl.length();
         System.out.println(ds000r.snum+" sample:"+ pl.length() + " rate: "+ good + " good sampe "+gsample); 
         totalsample += pl.length();
         totalgsample += gsample;
         */
      }
      System.out.println("toatl "+ total + " got "+ got);   
}

}
