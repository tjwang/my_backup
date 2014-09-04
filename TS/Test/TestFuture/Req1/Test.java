import stock.fight.*;
import stock.pattern.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
// static Vector<PReport_Rec> rp1;
// static Vector<PReport_Rec> rp2;
// static Vector<PReport_Rec> rr1;
 static SimpleLineFactory rp1;
 static SimpleLineFactory rp2;
 static StockKD rr ;
 static  Hashtable<String,SimpleLineFactory> lfht;
 static  Hashtable<String,Ds000_Rec> ckht;
 static public void printReport()
 {
 	/*
     Enumeration<PReport_Rec> e = rp1.elements();
      PReport_Rec rp = null;
      System.out.println("Report 1:");
     while(e.hasMoreElements())
     {
        System.out.println((e.nextElement()).dump());
     }
     e = rp2.elements();
      System.out.println("Report 2:");
     while(e.hasMoreElements())
     {
        System.out.println((e.nextElement()).dump());
     }
 */
 }
 static public void addReport(Hashtable ht,long dift)
 {
    Enumeration<PFiveRaw_Rec> e =ht.elements();
    long maxTime = 0;
    int sell_count_lv1 = 0; 
    int sell_count_lvA = 0; 
    int buy_count_lv1 = 0; 
    int buy_count_lvA = 0; 
    int sell_mcount_lv1 = 0; 
    int sell_mcount_lvA = 0; 
    int buy_mcount_lv1 = 0; 
    int buy_mcount_lvA = 0; 

    PFiveRaw_Rec max_ffr=null; 
    PReport_Rec rpr = new PReport_Rec();
    PReport_Rec rprm = new PReport_Rec();
//    System.out.println("--------------------addReport-------------------------");
    while(e.hasMoreElements())
    {
       PFiveRaw_Rec ffr = e.nextElement();
       sell_count_lv1 += ffr.count1Sell();
       sell_count_lvA += ffr.countAllSell();
       buy_count_lv1 += ffr.count1Buy();
       buy_count_lvA += ffr.countAllBuy();
       sell_mcount_lv1 += ffr.count1SellM();
       sell_mcount_lvA += ffr.countAllSellM();
       buy_mcount_lv1 += ffr.count1BuyM();
       buy_mcount_lvA += ffr.countAllBuyM();
       
       if(ffr.getDateTime().getTime()/1000 > maxTime)
       {
          maxTime = ffr.getDateTime().getTime()/1000;
          max_ffr=ffr;
       }
    }
    rpr.name="count";
    rpr.date = max_ffr.date;
    rpr.time = max_ffr.time;
    rpr.bf1 = String.valueOf(buy_count_lv1);
    rpr.bf2 = String.valueOf(buy_count_lvA);
    rpr.sf1 = String.valueOf(sell_count_lv1);
    rpr.sf2 = String.valueOf(sell_count_lvA);
    
    rprm.name="mcount";
    rprm.date = max_ffr.date;
    rprm.time = max_ffr.time;
    rprm.bf1 = String.valueOf(buy_mcount_lv1);
    rprm.bf2 = String.valueOf(buy_mcount_lvA);
    rprm.sf1 = String.valueOf(sell_mcount_lv1);
    rprm.sf2 = String.valueOf(sell_mcount_lvA);
    rp1.add(new ReportV(rpr));
    rp2.add(new ReportV(rprm));
 //  System.out.println(max_ffr.snum+" "+max_ffr.date+" "+max_ffr.time+"+++++++++++++++++++printReport+++++++++++++++++++"+maxTime);
 //   System.out.println(" \n");
     
}
static void getFuture(String ds)throws Exception
{
   	 Runtime rt = Runtime.getRuntime();
  	 Process p = rt.exec("X:\\Working\\TS\\計算期貨每日\\getDataAL.bat "+ ds);
     p.waitFor();
     String filename="X:\\Working\\TS\\計算期貨每日\\dataF\\ptxdayk."+ds+".txt";
     BufferedReader d
          = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
     String s = null ;
     
     while((s=d.readLine())!=null)
     {
     	  PLDayk_Rec ldk = new PLDayk_Rec(s);
        //System.out.println(ldk.dump());
        rr.add(ldk);
     }
}

 static public void countVol(FileInputStream fi)throws Exception
 {
       BufferedReader d
          = new BufferedReader(new InputStreamReader(fi));
      String s = d.readLine();
      PFiveRaw_Rec frr = null;
      if(s!=null) frr = new PFiveRaw_Rec(s); 
//      Vector<PFiveRaw_Rec> v = new Vector<PFiveRaw_Rec>();
      Hashtable ht = new Hashtable();
      lfht = new Hashtable<String,SimpleLineFactory>();
      long start_time = frr.getDateTime().getTime()/1000;
      String last_snum = null;
      do
      {
//         System.out.println(frr.getDateTime().getTime()/1000 - start_time);
//          System.out.println(frr.date + " "+ frr.time);
       	 if(last_snum != null && Integer.parseInt(last_snum) > Integer.parseInt(frr.snum))
       	 {
//         	  System.out.println(frr.date + " "+ frr.time);
         	  addReport(ht, frr.getDateTime().getTime()/1000 - start_time);
       	 }
         ht.put(frr.snum, frr);
         Ds000_Rec ds000r = ckht.get(frr.snum);
         if(ds000r != null)
         { 
            SimpleLineFactory slf = lfht.get(frr.snum);
            if(slf == null)
            {
               slf = new SimpleLineFactory();
               lfht.put(frr.snum,slf);
            }
            if(frr.time.length() == 6 && !frr.time.equals("000000"))
            {
               slf.add(Integer.parseInt(frr.date), Integer.parseInt("1"+frr.time)-1000000, Double.parseDouble(frr.rp));
            } else if(frr.time.length() == 5)
            {
               slf.add(Integer.parseInt(frr.date), Integer.parseInt(frr.time), Double.parseDouble(frr.rp));
            }
         }
         last_snum = frr.snum;
         /*
         if(frr.getDateTime().getTime()/1000 - start_time < 600)
         {
         	  v.add(frr);
         } else
         {
            printReport(v);
            v.remove(0);
            PFiveRaw_Rec ffr2 = v.elementAt(0);
            start_time = ffr2.getDateTime().getTime()/1000;
         }
         */
         s = d.readLine();
         if(s!=null) frr = new PFiveRaw_Rec(s); 
         else frr = null;
      }while(frr != null);
      
//      System.out.println(frr.countAllBuy()+" : "+frr.countAllBuyM());   
//      System.out.println(frr.countAllSell()+" : "+frr.countAllSellM());   
 }
 
 static public void main(String[] arg)throws Exception{
   System.out.println("range:"+arg[0]+" ~ "+arg[1]);
   Ds000_Rec ds000r = new Ds000_Rec();
   Enumeration de = ds000r.SelectBySQL("select * from ds000 where capital < 1000000000 order by capital desc limit 50 ;"); 
   ckht = new Hashtable<String,Ds000_Rec>();
   
   while(de.hasMoreElements())
   {
      ds000r = (Ds000_Rec)de.nextElement();
      ckht.put(ds000r.snum,ds000r);
   }
   Date start_date = GMethod.s2d(arg[0]);
   Date end_date = GMethod.s2d(arg[1]);
   long start_time = start_date.getTime();
   long end_time =   end_date.getTime();
   while(start_time<=end_time)
   {
   	  Date d = new Date(start_time);
      try{
         rp1 = new SimpleLineFactory();
         rp2 = new SimpleLineFactory();
         rr = new StockKD();
         FileInputStream fi = new FileInputStream("X:\\Working\\Data\\"+GMethod.d2s(d)+"\\fiveRaw."+GMethod.d2s(d)+".txt");
         countVol(fi);
         System.out.println("got "+fi+" "+GMethod.d2s(d));
        // printReport();
         getFuture(GMethod.d2s(d));
         Line l1 = rp1.culSimpleLine();
         Line l2 = rr.culSimpleLine();
         SimpleLineFactory slf1 = new SimpleLineFactory();
         SimpleLineFactory slf2= new SimpleLineFactory();
        
         for(int i=0;i<l1.length();i++)
         {
             Value v = l1.valueAt(i);
             Value v0 =  l2.valueAfter(v.getDomainValue());
             Value v1 =  l2.valueAfter(v.getDomainValue().add(900*1000));
             PReport_Rec prr=((ReportV)v)._priv_data;
             float a = Integer.parseInt(prr.bf1); 
             float b = Integer.parseInt(prr.sf1); 
             if(v1!=null && v0!=null && b > 1 )
             {
               System.out.println((a/b)+"|"+(v1.getValue()-v0.getValue())+"|");
               slf1.add(new SimpleValue(v.getDomainValue(),(a/b)));
               slf2.add(new SimpleValue(v.getDomainValue(),(v1.getValue()-v0.getValue())));
             }
         }
         Line l11 =  slf1.culSimpleLine();
         Line l12 =  slf2.culSimpleLine();
         LineGroup lg = new LineGroup(l12);
         lg.addLine(l11);
         lg.culMSLParameter();
         

        System.out.println("8039 " + lfht.get("8039"));
        System.out.println("2330 " + lfht.get("2330"));
/*
         SimpleLineFactory tlf = lfht.get("8039");
         Line tl1 =  tlf.culSimpleLine();
         tl1.dump();
 */
       /*  
         Value v = l1.valueAt(3);
         Value v0 = l2.valueAt(0); 
         Value v2 = l2.valueAfter(v.getDomainValue().add(900*1000));
         v.dump();
         v0.dump();
         v2.dump();
         System.out.println("v1 d:"+v.getDomainValue().getScale());
         System.out.println("v0 d:"+v0.getDomainValue().getScale());
         System.out.println("v2 d:"+v2.getDomainValue().getScale());
         l1.dump();
         */
         
      }catch(Exception e)
      {
         e.printStackTrace();
      }
      start_time +=86400000;
   }
   
   
 }

}