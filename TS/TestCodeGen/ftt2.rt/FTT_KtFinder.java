import stock.db.*;
import stock.fight.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class FTT_KtFinder {
 
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

static byte[] getISbyDate(String ds)
{   
	    try
	    {
         File dataFile = new File("X:\\Working\\Data\\"+ds+"\\fss_8888.log."+ds);
         byte[] mydataCache = new byte[(int)dataFile.length()];
         FileInputStream data_source = new FileInputStream(dataFile);
         data_source.read(mydataCache);
         data_source.close();
         ByteArrayInputStream bais = new ByteArrayInputStream(mydataCache);
         return mydataCache;
      }catch(Exception xxe)
      {
       //  xxe.printStackTrace();
      }
      return null;
}

static public void main(String[] arg)throws Exception{
      int total = 0;
      int got = 0;
      int totalsample = 0;
      double totalgsample = 0;
      if(arg.length > 1)
      {
        FTT_sFactory.current_TX = arg[1];
      }
      if(arg.length > 2)
      {
        FTT_sFactory.current_Method = arg[2];
      }
      Hashtable<String,byte[]> iht = new Hashtable<String,byte[]> ();
      for(int txRange = 6; txRange < 18 ; txRange++)
       for(int txGet = 6; txGet < 12 ; txGet++)
        for(int txLoss = -10; txLoss > -12 ; txLoss--)
      {  
       
         SimpleTSRunPlan.tRange = txRange;
         SimpleTSRunPlan.tMaxGet = txGet;
         SimpleTSRunPlan.tMaxLoss = txLoss;
         S4_TransactionManager.one_instance = null;
//         Runtime.exec(
         Date sDate = GMethod.s2d(arg[0]);
         Date eDate = new Date();
         Date xxStartDate = new Date();
         while(sDate.getTime() <= eDate.getTime())
         {
            try
            {
                byte[] data_ary = iht.get(GMethod.d2s(sDate));
                if(data_ary == null)
                {
                    data_ary = getISbyDate(GMethod.d2s(sDate));
                    iht.put(GMethod.d2s(sDate),data_ary);
                }
                ByteArrayInputStream mis = new ByteArrayInputStream(data_ary);
                if(S4_TransactionManager.one_instance != null)
                {
                   S4_TransactionManager.one_instance.reset();
                }
                mis.mark(10);
            	  for(int i=930;i<=1325;i++)
            	  {
                 if(i%100 < 60)
                 {
                   mis.reset();
                   FTT_sFactory   fac1 = new FTT_sFactory("test",GMethod.d2s(sDate),i*100,mis);
                 }
               }
            }catch(Exception xe)
            {
          //      xe.printStackTrace();
            }
            sDate = new Date(sDate.getTime()+86400000);
         }
         Date xxEndDate = new Date();
         System.err.println("Time cost:"+(xxEndDate.getTime()-xxStartDate.getTime()));
         System.err.println("txTest case : ("+txRange+","+txGet+","+txLoss
            +" fail_count: "+S4_TransactionManager.one_instance.fail_count+ " match_count: "+S4_TransactionManager.one_instance.do_count
            +" ssesP:"+(1-(float)S4_TransactionManager.one_instance.fail_count/(float)S4_TransactionManager.one_instance.do_count)
            +" Gain:"+(txGet * (S4_TransactionManager.one_instance.do_count - S4_TransactionManager.one_instance.fail_count) + 
                       txLoss * S4_TransactionManager.one_instance.fail_count)
            );
        PrintStream ps = S4_TransactionManager.one_instance.getDumpPs();
        if(ps != null)
        {
            ps.close();
            File f1 = new File(arg[0]+".dump.log");
            File f2 = new File(arg[0]+"-"+txRange+"-"+txGet+"x"+txLoss+".dump.log");
            f1.renameTo(f2);
        }
      }
}

}
