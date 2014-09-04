import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class IZ_tFinder {
 
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
         IZ_sFactory   fac1 = new IZ_sFactory(ds000r.snum,arg[0]);
         IZ_oFactory   fac2 = new IZ_oFactory(ds000r.snum,arg[0]);
         LineGroup basicGroup =  LG_Maker.basic(fac1,fac2);
         Line markLine =   LG_Maker.markLine(basicGroup);
         Line mainLine =   LG_Maker.mainLine(basicGroup);
//         markLine.dump();
         IZ_Pattern   ptn1 = new IZ_Pattern(markLine,basicGroup);
         try
         {
            PatternLine pl = (PatternLine)basicGroup.pattern(ptn1);
            if(pl.length() > 0)
            {
               if(((PatternValue)pl.valueAt(pl.length()-1)).getEndIdx() == (markLine.length()-1))
               {
            	   got++;
            	   ds000r.sname = new String(ds000r.sname.getBytes("ISO-8859-1"),"MS950");
            	   System.out.println(ds000r.dump());  
            	   KLine kl = (KLine) mainLine;
            	   System.out.println("Max volume since 2014/3/1 : "+kl.getVol().getMax());
            	   System.out.println("Average volume since 2014/3/1 : "+kl.getVol().getAvg());
            	   KValue kv = (KValue)kl.valueAt(kl.length()-1);
            	   kv.dump();
             	   System.out.println(" vol("+kv.getVolume()+") / capital ("+ds000r.capital+"):"+
             	                    kv.getVolume()/Float.parseFloat(ds000r.capital));
           	     System.out.println("========================================");
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
}

}
