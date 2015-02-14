import stock.db.*;
import stock.fight.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class FTT_DtFinder {
 
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
         File dataFile = new File("D:\\Java\\TS\\Data\\"+ds+"\\fss_8888.log."+ds);
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
      if(arg.length > 2 && arg[2].equals("STA_2"))
      {
        SimpleTSRunPlan.tMode = SimpleTSRunPlan.XX_STA_2;
      } else
      {
        SimpleTSRunPlan.tMode = SimpleTSRunPlan.XX_STA;
      }
      {  
         S4_TransactionManager.one_instance = null;
         Date sDate = GMethod.s2d(arg[0]);
         Date eDate = new Date();
         while(sDate.getTime() <= eDate.getTime())
         {
            try
            {
 //               byte[] data_ary = null;//iht.get(GMethod.d2s(sDate));
 //               if(data_ary == null)
 //               {
 //                   data_ary = getISbyDate(GMethod.d2s(sDate));
 //                   iht.put(GMethod.d2s(sDate),data_ary);
 //               }
                System.out.print(GMethod.d2s(sDate)+"|");
//                ByteArrayInputStream mis = new ByteArrayInputStream(data_ary);
                if(S4_TransactionManager.one_instance != null)
                {
                   S4_TransactionManager.one_instance.reset();
                }
//                mis.mark(10);
            	  for(int i=930;i<=930;i++)
            	  {
  //                 mis.reset();
                   FTT_sFactory   fac1 = new FTT_sFactory("test",GMethod.d2s(sDate),i*100);
                }
            }catch(Exception xe)
            {
              //  xe.printStackTrace();
              System.out.println("||||");
            }
            sDate = new Date(sDate.getTime()+86400000);
         }
      }
}

}
