import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class MF_tFinder {
 
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
   //   	  System.out.println("ds000r.snum "+ ds000r.snum);   
         MF_sFactory   fac1 = new MF_sFactory(ds000r.snum,arg[0]);
         MF_oFactory   fac2 = new MF_oFactory(ds000r.snum,arg[0]);
         LineGroup basicGroup =  LG_Maker.basic(fac1,fac2);
         Line markLine =   LG_Maker.markLine(basicGroup);
         Line mainLine =   LG_Maker.mainLine(basicGroup);
         MF_Pattern   ptn1 = new MF_Pattern(markLine,basicGroup);
         try
         {
            PatternLine pl = (PatternLine)basicGroup.pattern(ptn1);
            if(pl.length() > 0)
            {
                Line[] la = basicGroup.getSubLineArray();
                PatternValue pv =(PatternValue)pl.valueAt(0);
                GroupValue gv = (GroupValue)pv.getStartValue();
               Line sl = la[1].sub(gv.getDomainValue(),la[1].valueAt(la[1].length()-1).getDomainValue());
               KValue max_kv = (KValue)sl.getMaxValue();
             	 KValue last_kv = (KValue)sl.valueAt(sl.length()-1);
             	 KValue pattern_kv = (KValue)gv.getMainValue();
             	 //la[1].dump();
             	 //sl.dump();
             	 /*
               */
               if((max_kv.getValue() - pattern_kv.getValue()) / pattern_kv.getValue() < 0.05 &&
                  (last_kv.getValue() - pattern_kv.getValue()) / pattern_kv.getValue() < 0.05)
               {
               	  System.out.println(ds000r.snum +" v1 : "+ (max_kv.getValue() - pattern_kv.getValue()) / pattern_kv.getValue() );   
               	  System.out.println(ds000r.snum +" v2 : "+ (last_kv.getValue() - pattern_kv.getValue()) / pattern_kv.getValue() );   
             	    System.out.println("max_kv--------------------------------------");
             	    max_kv.dump();
             	    System.out.println("last_kv--------------------------------------");
             	    last_kv.dump();
             	    System.out.println("pattern_kv--------------------------------------");
             	    pattern_kv.dump();
             	    System.err.println(ds000r.snum);
                  got++;
               }
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
  /*   
       System.out.println("totalsample "+ totalsample + " totalgsample "+ totalgsample);   
      System.out.println("totalrate "+ totalgsample/totalsample );   
    */
}

}
