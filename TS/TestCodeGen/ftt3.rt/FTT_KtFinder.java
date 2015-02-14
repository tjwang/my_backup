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
    for(int xxi = SimpleTSRunPlan.UP; xxi < SimpleTSRunPlan.tMode_NUM; xxi++)
     for(int pmode = SimpleTSRunPlan.PATTEN_MA; pmode < SimpleTSRunPlan.tpMode_NUM; pmode++)
      for(int txRange = 0; txRange < 1 ; txRange++)
       for(int txGet = 10; txGet < 11 ; txGet++)
        for(int txLoss = -10; txLoss > -11 ; txLoss--)
      {  
       
         SimpleTSRunPlan.tRange = txRange;
         SimpleTSRunPlan.tMaxGet = txGet;
         SimpleTSRunPlan.tMaxLoss = txLoss;
         SimpleTSRunPlan.tMode = xxi;//SimpleTSRunPlan.UP;
         SimpleTSRunPlan.tpMode = pmode;
         S4_TransactionManager.one_instance = null;
//         Runtime.exec(
         Date sDate = GMethod.s2d(arg[0]);
         Date eDate = new Date();
         if(arg.length > 3)
         {
            eDate = GMethod.s2d(arg[3]);
            System.out.println(arg[3]+" "+eDate.getTime()+" "+sDate.getTime());
         }
         
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
            	  	System.out.println("i-->"+i);
                 if(i%100 < 60)
                 {
                   mis.reset();
                   FTT_sFactory   fac1 = new FTT_sFactory("test",GMethod.d2s(sDate),i*100,mis);
                 }
                }
                FeatureIdxHash fis = stock.tool.TimeIdxHash.gSumAll();
                fis.dump(); 
                String tMode_str = null;
                String tpMode_str = null;
                switch(xxi)
                {
                   case SimpleTSRunPlan.UP:
                        tMode_str ="UP";
                        break;
                   case SimpleTSRunPlan.DOWN:
                        tMode_str ="DOWN";
                        break;
                }
                switch(pmode)
                {
                   case SimpleTSRunPlan.PATTEN_MA:
                        tpMode_str = "MA";
                        break;
                   case SimpleTSRunPlan.PATTEN_K:
                        tpMode_str = "K";
                        break;
                   case SimpleTSRunPlan.PATTEN_KD:
                        tpMode_str = "KD";
                        break;
                   case SimpleTSRunPlan.PATTEN_V:
                        tpMode_str = "V";
                        break;
                }
                FileOutputStream fos = new FileOutputStream("Fis-"+tMode_str+"-"+tpMode_str+"."+GMethod.d2s(sDate)+".idxh");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(fis);
                stock.tool.TimeIdxHash.gClean();
            }catch(Exception xe)
            {
                xe.printStackTrace();
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
/*
        PrintStream ps = S4_TransactionManager.one_instance.getDumpPs();
        if(ps != null)
        {
            ps.close();
            File f1 = new File(arg[0]+".dump.log");
            File f2 = new File(arg[0]+"-"+txRange+"-"+txGet+"x"+txLoss+".dump.log");
            f1.renameTo(f2);
        }
*/        
      }
}

}
