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
static private KLine subFrom900(KLine l)
{
   for(int i=0;i<l.length();i++)
   {
      if(l.valueAt(i).getTimeValue() >= 90000) return (KLine)l.sub(i,l.length()-1);
   }
   return null;
}
static void printLineForInsertDb(Line l, String code)
{
   for(int i=0;i<l.length();i++)
   {
       System.err.println(code+"|"+l.valueAt(i).getDateValue()+"|"+l.valueAt(i).getTimeValue()+"|"+l.valueAt(i).getValue());
   } 
}

static void printLineConverted(FTT_sFactory fac1,Line l, String code)
{
   for(int i=0;i<l.length();i++)
   {
      // System.err.println(code+"|"+l.valueAt(i).getDateValue()+"|"+l.valueAt(i).getTimeValue()+"|"+l.valueAt(i).getValue());
      Value v = l.valueAt(i);
      Line baseline = fac1.getStaVLineByCode(code,v.getTimeValue());
      System.out.println(code+"-->"+l.valueAt(i).getTimeValue()+", "+baseline.getRsv(v.getValue()));
   } 
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
//      if(arg.length > 2 && arg[2].equals("STA_2"))
//      {
//        SimpleTSRunPlan.tMode = SimpleTSRunPlan.XX_STA_2;
//      } else
//      {
//        SimpleTSRunPlan.tMode = SimpleTSRunPlan.XX_STA;
//      }
      {  
//         S4_TransactionManager.one_instance = null;
         Date sDate = GMethod.s2d(arg[0]);
         Date eDate = new Date();
         while(sDate.getTime() <= eDate.getTime())
         {
            try
            {
                FTT_sFactory   fac1 = new FTT_sFactory("test",GMethod.d2s(sDate));
             /*   
                Line svl_900 = fac1.getStaVLineByCode("200",90000);
                Line svl_1225 = fac1.getStaVLineByCode("200",122500);
                Line svl130d131_900 = fac1.getStaVLineByCode("130d131",90000);
                Line svl130d131_1225 = fac1.getStaVLineByCode("130d131",122500);
                svl_900.dump();
                svl_1225.dump();
                svl130d131_900.dump();
                svl130d131_1225.dump();
               */
                KLine svl = subFrom900((KLine)fac1.getLineByCode("200", 0));
                KLine ssl1 = subFrom900((KLine)fac1.getLineByCode("221", 0));
                KLine ssl2 = subFrom900((KLine)fac1.getLineByCode("221:2", 0));
                Line svl_h = subFrom900((KLine)fac1.getLineByCode("200", 0)).getHigh();
                Line ssl1_h = subFrom900((KLine)fac1.getLineByCode("221", 0)).getHigh();
                Line ssl2_h = subFrom900((KLine)fac1.getLineByCode("221:2", 0)).getHigh();
                KLine ssl130 = subFrom900((KLine)fac1.getLineByCode("130", 0));
                KLine ssl130_2 = subFrom900((KLine)fac1.getLineByCode("130:2", 0));
                KLine ssl131 = subFrom900((KLine)fac1.getLineByCode("131", 0));
                KLine ssl131_2 = subFrom900((KLine)fac1.getLineByCode("131:2", 0));
               
                System.out.println(GMethod.d2s(sDate)+"svl-->");
//                svl.dump();
//                printLineForInsertDb(svl,"200");
//                 printLineConverted(fac1,svl,"200");
                
                System.out.println(GMethod.d2s(sDate)+"ssl1-->");
//                ssl1.dump();
//                printLineForInsertDb(ssl1,"221");
//                printLineConverted(fac1,ssl1,"221");
                
                System.out.println(GMethod.d2s(sDate)+"ssl2-->");
//                ssl2.dump();
//                printLineForInsertDb(ssl2,"221_2");
//                printLineConverted(fac1,ssl2,"221_2");

                System.out.println(GMethod.d2s(sDate)+"ssl1/ssl2-->");
//                ssl1.div(ssl2).dump();
//                printLineForInsertDb(ssl1.div(ssl2),"221q2");
//                 printLineConverted(fac1,ssl2,"221q2");
              
                System.out.println(GMethod.d2s(sDate)+"ssl130-->");
//                ssl130.dump();
//                printLineForInsertDb(ssl130,"130");
//                 printLineConverted(fac1,ssl130,"130");


                System.out.println(GMethod.d2s(sDate)+"ssl1302-->");
//                ssl130_2.dump();
//                printLineForInsertDb(ssl130_2,"130_2");
//                 printLineConverted(fac1,ssl130_2,"130_2");

                System.out.println(GMethod.d2s(sDate)+"ssl130/ssl1302-->");
 //               ssl130.div(ssl130_2).dump();
 //               printLineForInsertDb(ssl130.div(ssl130_2),"130q2");
//                 printLineConverted(fac1,ssl130.div(ssl130_2),"130q2");
                
                System.out.println(GMethod.d2s(sDate)+"ssl131-->");
//                ssl131.dump();
//                printLineForInsertDb(ssl131,"131");
//                 printLineConverted(fac1,ssl131,"131");

                System.out.println(GMethod.d2s(sDate)+"ssl1312-->");
 //               ssl131_2.dump();
 //               printLineForInsertDb(ssl131_2,"131_2");
//                 printLineConverted(fac1,ssl131_2,"131_2");

                System.out.println(GMethod.d2s(sDate)+"ssl131/ssl1312-->");
//                ssl131.div(ssl131_2).dump();
//                printLineForInsertDb(ssl131.div(ssl131_2),"131q2");
                 printLineConverted(fac1,ssl131.div(ssl131_2),"131q2");

                System.out.println(GMethod.d2s(sDate)+"ssl130-ssl131-->");
//                ssl130.diff(ssl131).dump();
//                printLineForInsertDb(ssl130.diff(ssl131),"130d131");
                 printLineConverted(fac1,ssl130.diff(ssl131),"130d131");
                
                System.out.println(GMethod.d2s(sDate)+"ssl1302-ssl1312-->");
//                ssl130_2.diff(ssl131_2).dump();
//                printLineForInsertDb( ssl130_2.diff(ssl131_2),"139d131_2");
                 printLineConverted(fac1, ssl130_2.diff(ssl131_2),"139d131_2");
                
                System.out.println(GMethod.d2s(sDate)+"ssl130/2-ssl131/2-->");
//                ssl130.div(ssl130_2).diff(ssl131.div(ssl131_2)).dump();
//                printLineForInsertDb(ssl130.div(ssl130_2),"130q2");
//                 printLineConverted(fac1,ssl130.div(ssl130_2),"130q2");
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
