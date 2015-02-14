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
        // xxe.printStackTrace();
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
       
         S4_TransactionManager.one_instance = null;
         Date sDate = GMethod.s2d(arg[0]);
            try
            {
                byte[] data_ary = iht.get(GMethod.d2s(sDate));
                if(data_ary == null)
                {
                    data_ary = getISbyDate(GMethod.d2s(sDate));
                    iht.put(GMethod.d2s(sDate),data_ary);
                }
                ByteArrayInputStream mis = new ByteArrayInputStream(data_ary);
                mis.mark(10);
            	  for(int i=930;i<=1325;i++)
            	  {
                 if(i%100 < 60)
                 {
                   mis.reset();
                   S4_TransactionManager.one_instance = null;
                   System.out.println("test at "+i+"-->");
                   FTT_sFactory   fac1 = new FTT_sFactory("test",GMethod.d2s(sDate),i*100,mis);
                   System.out.println("txTest case :  fail_count: "+S4_TransactionManager.one_instance.fail_count+ " match_count: "+S4_TransactionManager.one_instance.do_count);
                   System.out.println("====================================");
                 }
                }
            }catch(Exception xe)
            {
                xe.printStackTrace();
            }


   }

}
