import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import stock.fight.*;
import org.apache.commons.codec.binary.Base64;

public class Try1 {
static  Vector StockPool = new Vector();
static  Vector IndexPool = new Vector();
static  Vector FuturePool = new Vector();

//static  Hashtable  sht = new Hashtable();;
//static  Hashtable  idxht = new Hashtable();;
static  Hashtable  ht_data = new Hashtable();
static  Hashtable  idxht_data = new Hashtable();;
static  Hashtable  snumht = new Hashtable();;
static  Hashtable  StockReportPool = new Hashtable();
static  Hashtable  IndexReportPool = new Hashtable();
static  Future_Report  futureReport = new Future_Report();

  static public void main(String[] arg)throws Exception
  {
      DBConnection.debug = false;
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0])));
      String s = null;
      Future_Rec fr = null;
      Index_Rec ir = null;
      Stock_Rec sr = null;
      Ds000_Rec dsr = new Ds000_Rec();
      Enumeration e = dsr.SelectBySQL("select aa.snum as snum, aa.capital as sname, aa.capital * bb.rp as capital from (select * from plast where date='20101116') as bb,(select b.* from ds000attr as a,ds000 as b where a.snum= b.snum and tcode='1' and capital < 1000000000) as aa where aa.snum=bb.snum order by capital desc limit 50 ;");
      while(e.hasMoreElements())
      {
         dsr = (Ds000_Rec)e.nextElement();
         Stock_Ds0Rec sr0 = new  Stock_Ds0Rec(); 
         sr0.snum = dsr.snum;
         sr0.capital = Integer.parseInt(dsr.sname);
         sr0.value = Float.parseFloat(dsr.capital);  
         snumht.put(dsr.snum,sr0);
      }
      
      while((s=br.readLine())!=null){
         if(s.indexOf(":B:")>0)
         {
             ir = new Index_Rec(s.substring(s.indexOf(":B:")+3));
             ir.creat_time = Integer.parseInt(s.substring(0,s.indexOf(":B:")));
             IndexPool.add(ir);
         } else if(s.indexOf(":C:")>0)
         {
             fr = new Future_Rec(s.substring(s.indexOf(":C:")+3));
             fr.creat_time = Integer.parseInt(s.substring(0,s.indexOf(":C:")));;
             FuturePool.add(fr);
         } else if(s.indexOf(":A:")>0){
             sr = new Stock_Rec(s.substring(s.indexOf(":A:")+3));
             sr.creat_time = Integer.parseInt(s.substring(0,s.indexOf(":A:")));;
             StockPool.add(sr);
         }
      }  
  	  TreeSet recordPool = new TreeSet(new TRComparator());
      e = StockPool.elements();
      while(e.hasMoreElements())
      {
         recordPool.add(e.nextElement());
      }
      e = IndexPool.elements();
      while(e.hasMoreElements())
      {
          recordPool.add(e.nextElement());
      }
      e = FuturePool.elements();
      while(e.hasMoreElements())
      {
         recordPool.add(e.nextElement());
      }
  	  Iterator<Try_Rec> i = recordPool.iterator();
  	  int ctime = 0;
  	  Vector v = new Vector();
   	  while(i.hasNext())
   	  {
   	      Try_Rec tr = i.next();
   	      if(ctime != tr.getCreateTime() && ctime!=0)
   	      {
              setupReport(v.elements());
              culReport1(fr);
              culReport2(fr);
              v = new Vector();  	      
   	      }
   	      ctime = tr.getCreateTime();
//   	      System.out.println(tr);
          if(tr instanceof Future_Rec)
             fr =(Future_Rec) tr ;
   	      v.add(tr);
   	  }
   	  Index_Report irpt = (Index_Report)IndexReportPool.get("X1");
   	  LineFactory ilf = irpt.getLineFactory();
   	  KLine ikl = ilf.culKLine();
   	  ikl.dump();
/*   	  Stock_Report srpt = (Stock_Report)StockReportPool.get("2454");
   	  LineFactory slf = srpt.getLineRpFactory();
   	  KLine skl = slf.culKLine();
   	  skl.dump();
 */  	  
  /* 	  
      e = StockReportPool.elements();
      while(e.hasMoreElements())
      {
         Stock_Report srpt = (Stock_Report)e.nextElement();
         Enumeration e2 = srpt.elements();
         while(e2.hasMoreElements())
         {
             System.out.println(e2.nextElement());
         }
      }
      e = IndexReportPool.elements();
      while(e.hasMoreElements())
      {
         Index_Report irpt = (Index_Report)e.nextElement();
         Enumeration e2 = irpt.elements();
         while(e2.hasMoreElements())
         {
             System.out.println(e2.nextElement());
         }
      }
      e = futureReport.elements();
      while(e.hasMoreElements())
      {
         System.out.println(e.nextElement());
      }
    */
  }
  
  static void setupReport(Enumeration e)
  {
   	  while(e.hasMoreElements())
   	  {
   	  	  Try_Rec tr = (Try_Rec)e.nextElement();
          if(tr instanceof Stock_Rec)
          {
             Stock_Rec sr = (Stock_Rec)tr;
             Stock_Report srpt = (Stock_Report)StockReportPool.get(sr.snum);
             if(srpt == null) {
                srpt = new Stock_Report(sr.snum);
                StockReportPool.put(sr.snum, srpt);
             }
             srpt.add(sr);
             ht_data.put(sr.snum, sr);
          } else if(tr instanceof Index_Rec)
          {
             Index_Rec ir = (Index_Rec)tr;
             Index_Report irpt = (Index_Report)IndexReportPool.get(ir.num);
             if(irpt == null) {
                irpt = new Index_Report(ir.num);
                IndexReportPool.put(ir.num, irpt);
             }
             irpt.add(ir);
             idxht_data.put(ir.num, ir);
          
          } else if(tr instanceof Future_Rec)
          {
             Future_Rec fr = (Future_Rec)tr;
             if (futureReport == null)
             {
                futureReport = new Future_Report();
             }
             futureReport.add(fr);
          } 
   	  }      
  }
  static void culReport1(Future_Rec fr)
  {
  	  Enumeration e = ht_data.elements();
  	  float preVal = 0;
  	  float curVal = 0;
  	  while(e.hasMoreElements())
  	  {
  	     Stock_Rec sr = (Stock_Rec) e.nextElement();
  	     Stock_Ds0Rec  sr0  =  (Stock_Ds0Rec)snumht.get(sr.snum);
  	     preVal += sr0.value;
  	     curVal += sr.rp * sr0.capital;
  	  }
  	  Index_Rec ir= (Index_Rec)idxht_data.get("1");
  	  float preIdx = ir.value-ir.change;
  	  float preFuIdx = fr.price - fr.change;
  	  float stockChane = ((curVal-preVal)/preVal);
      Index2_Rec ir2 = new Index2_Rec("X1", ir.dates, fr.creat_time, fr.creat_time, ir.time, fr.time,
                               preIdx - (stockChane*preIdx), preIdx,  fr.price,
                               (stockChane*preIdx),ir.change, fr.change, fr.creat_time);
      Index_Report irpt = (Index_Report)IndexReportPool.get("X1");
      if(irpt == null) {
          irpt = new Index_Report(ir2.num);
          IndexReportPool.put(ir2.num, irpt);
      }
      irpt.add(ir2);

 /*
  	  System.err.println("Time:"+fr.creat_time);
  	  System.err.println("Stock Diff:"+(stockChane*preIdx)+" ..<"+(stockChane*100)+"% ");
  	  System.err.println("Idx Diff:"+ ir.change +" ..<"+((ir.change)/preIdx*100)+"% ");
  	  System.err.println("preFuIdx Diff:"+ fr.change +" ..<"+((fr.change)/preFuIdx*100)+"% ");
*/
//  	  System.err.println("=================================================================");
  }
  static void culReport2(Future_Rec fr)
  {
  	  Enumeration e = ht_data.elements();
  	  float buy1M = 0;
  	  int buy1C = 0;
  	  float sell1M = 0;
  	  int sell1C = 0;
  	  float buy5M = 0;
  	  int buy5C = 0;
  	  float sell5M = 0;
  	  int sell5C = 0;
  	  while(e.hasMoreElements())
  	  {
  	     Stock_Rec sr = (Stock_Rec) e.nextElement();
  	     buy1M += sr.b1rp * sr.b1ra;
  	     buy1C += sr.b1ra;
  	     sell1M += sr.s1rp * sr.s1ra;
  	     sell1C += sr.s1ra;
  	     buy5M += sr.b1rp * sr.b1ra + sr.b2rp * sr.b2ra + sr.b3rp * sr.b3ra + sr.b4rp * sr.b4ra + sr.b5rp * sr.b5ra ;
  	     buy5C += sr.b1ra + sr.b2ra + sr.b3ra + sr.b4ra + sr.b5ra ;
  	     sell5M += sr.s1rp * sr.s1ra + sr.s2rp * sr.s2ra + sr.s3rp * sr.s3ra + sr.s4rp * sr.s4ra + sr.s5rp * sr.s5ra ;
  	     sell5C += sr.s1ra + sr.s2ra + sr.s3ra + sr.s4ra + sr.s5ra;
  	  }
/*  	  Index_Rec ir110= (Index_Rec)idxht_data.get("110"); //總委買
  	  Index_Rec ir111= (Index_Rec)idxht_data.get("111"); //總委賣
  	  Index_Rec ir120= (Index_Rec)idxht_data.get("120"); //基金委買
  	  Index_Rec ir121= (Index_Rec)idxht_data.get("121"); //基金委賣
  	  Index_Rec ir130= (Index_Rec)idxht_data.get("130"); //漲停委買
  	  Index_Rec ir131= (Index_Rec)idxht_data.get("131"); //漲停委賣
  	  Index_Rec ir140= (Index_Rec)idxht_data.get("140"); //跌停委買
  	  Index_Rec ir141= (Index_Rec)idxht_data.get("141"); //跌停委賣
  	  System.err.println("Time:"+fr.creat_time);
      System.err.println(" buy1M:"+ buy1M );
      System.err.println(" buy1C:"+ buy1C ); 
      System.err.println(" sell1M:"+sell1M);
      System.err.println(" sell1C:"+sell1C);
      System.err.println(" buy5M:"+ buy5M );
      System.err.println(" buy5C:"+ buy5C );
      System.err.println(" sell5M:"+sell5M);
      System.err.println(" sell5C:"+sell5C);
      System.err.println("總委買   :"+((int)ir110.value)+" count:"+((int)ir110.change));
      System.err.println("總委賣   :"+((int)ir111.value)+" count:"+((int)ir111.change));
      System.err.println("基金委買 :"+((int)ir120.value)+" count:"+((int)ir120.change));
      System.err.println("基金委賣 :"+((int)ir121.value)+" count:"+((int)ir121.change));
      System.err.println("漲停委買 :"+((int)ir130.value)+" count:"+((int)ir130.change));
      System.err.println("漲停委賣 :"+((int)ir131.value)+" count:"+((int)ir131.change));
      System.err.println("跌停委買 :"+((int)ir140.value)+" count:"+((int)ir140.change));
      System.err.println("跌停委賣 :"+((int)ir141.value)+" count:"+((int)ir141.change));
      System.err.println("一般委買 :"+((int)ir110.value - (int)ir130.value - (int)ir140.value)+
                            " count:"+((int)ir110.change - (int)ir130.change - (int)ir140.change));
      System.err.println("一般委賣 :"+((int)ir111.value - (int)ir131.value - (int)ir141.value)+
                            " count:"+((int)ir111.change - (int)ir131.change - (int)ir141.change));
  	  System.err.println("=================================================================");
 */
  }
  
}