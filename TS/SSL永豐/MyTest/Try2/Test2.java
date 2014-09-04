import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.pattern.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

//分析量的關係
public class Test2 {
  static     Hashtable ht = new Hashtable();
  static     Hashtable ht15 = new Hashtable();
  static   PatternLine dataLine = null;
   public static void main(String[] arg) throws Exception
   {
       RpLF  irf = new RpLF(RpLF.RpMoney, arg[0], arg[1]);
       RpLF  irfcnt = new RpLF(RpLF.RpCnt, arg[0], arg[1]);
       RpLF  irfamt = new RpLF(RpLF.RpAmt, arg[0], arg[1]);
       RpLF  irfbuy = new RpLF(RpLF.BuyAmt, arg[0], arg[1]);
       RpLF  irfsell = new RpLF(RpLF.SellAmt, arg[0], arg[1]);
       SimpleLine sl = irf.culSimpleLine();
       SimpleLine slcnt = irfcnt.culSimpleLine();
       SimpleLine slamt = irfamt.culSimpleLine();
       SimpleLine slbuy = irfbuy.culSimpleLine();
       SimpleLine slsell = irfsell.culSimpleLine();
       
       RangeByDatePattern rdp0 = new RangeByDatePattern(90000, 102500);
       RangeByDatePattern rdp1 = new RangeByDatePattern(90000, 103000);
//       RangeByDatePattern rdp2 = new RangeByDatePattern(132500, 133000);
       RangeByDatePattern rdp2 = new RangeByDatePattern(93000, 104500);
       dataLine = sl.pattern(rdp1);
       PatternLine dataLine0 = sl.pattern(rdp0);
       Line adl = dataLine.diff(dataLine0);
       

       PatternLine dataLineAmt0 = slamt.pattern(rdp0);
       PatternLine dataLineAmt = slamt.pattern(rdp1);
       PatternLine dataLineAmt2 = slamt.pattern(rdp2);
       PatternLine dataLineCnt = slcnt.pattern(rdp1);
       PatternLine dataLineCnt2 = slcnt.pattern(rdp2);

       Line xl = dataLine.diff(dataLine0).div(dataLineAmt.diff(dataLineAmt0));
       Line dataLineBuy = slbuy.pattern(rdp1).diff(slbuy.pattern(rdp0)).mul(xl);
       Line dataLineSell = slsell.pattern(rdp1).diff(slbuy.pattern(rdp0)).mul(xl);
       
       PatternLine dataLine2 = sl.pattern(rdp2);
       Line adl2 = dataLine2.diff(dataLine);

       int n = dataLine2.length();
       SimpleLineFactory mlf = new SimpleLineFactory();
       SimpleLineFactory x1f = new SimpleLineFactory();
       SimpleLineFactory xk1f = new SimpleLineFactory();
       SimpleLineFactory xk2f = new SimpleLineFactory();
       SimpleLineFactory xd1f = new SimpleLineFactory();
       SimpleLineFactory xd2f = new SimpleLineFactory();
       SimpleLineFactory xk01f = new SimpleLineFactory();
       SimpleLineFactory xk02f = new SimpleLineFactory();
       SimpleLineFactory xd01f = new SimpleLineFactory();
       SimpleLineFactory xd02f = new SimpleLineFactory();
       SimpleLineFactory xmalf = new SimpleLineFactory();
       SimpleLineFactory plf0 = new SimpleLineFactory(dataLine);
       SimpleLineFactory plf = new SimpleLineFactory(dataLine2);
 //      for(int a = 10; a < 20 ; a++)
       {
  //     	  for(int b=5; b < 10; b++)
       	  {
    //   	  	 for(int c=3; c< 10; c++)
       	  	 {
       KDLine kdl0 = plf0.culKDLine(25,9,9);
       KDLine kdl = plf.culKDLine(25,9,9);
       Line mal0 = plf0.culMALine(8);
       Line mal = plf.culMALine(8);
       for(int i = 0; i < n; i++)
       {
           PatternValue pv1 = (PatternValue)dataLine.valueAt(i);
           PatternValue pv2 = (PatternValue)dataLine2.valueAt(i);
           DomainValue dv = pv1.getDomainValue();
           KDValue kdv1 = (KDValue)kdl0.valueAt(dv);
           int idx = kdl0.mappingIdx(kdv1);
           Value mav = mal.valueAt(pv2.getDomainValue());
           int maidx = mal.mappingIdx(mav)-1;
         //  System.out.println("maidx:"+maidx);
           if(kdv1 != null &&  idx > 1 && maidx > 0)
           {
              KDValue kdv21 = (KDValue)kdl.valueAt(idx-1);
              KDValue kdv22 = (KDValue)kdl.valueAt(idx-2);
           	  KDValue kdv2 = (KDValue)kdl0.valueAt(idx-1);
           	  mav = mal0.valueAt(maidx);
//              mlf.add(pv2.getDateTime(), pv2.getEndValue().getValue());
              mlf.add(pv2.getDateTime(), adl2.valueAt(i).getValue());
              x1f.add(pv2.getDateTime(), pv1.getEndValue().getValue());
//              xk1f.add(pv2.getDateTime(),((PatternValue)dataLineAmt.valueAt(i)).getEndValue().getValue());
//              xk2f.add(pv2.getDateTime(),((PatternValue)dataLineCnt.valueAt(i)).getEndValue().getValue());
              xk1f.add(pv2.getDateTime(),adl.valueAt(i).getValue());
              xk2f.add(pv2.getDateTime(),dataLineBuy.valueAt(i).getValue());
              xd1f.add(pv2.getDateTime(),dataLineSell.valueAt(i).getValue());
              xd2f.add(pv2.getDateTime(),((PatternValue)dataLine2.valueAt(i-4)).getEndValue().getValue());
              xk01f.add(pv2.getDateTime(),((PatternValue)dataLine2.valueAt(i-5)).getEndValue().getValue());
              xk02f.add(pv2.getDateTime(), ((PatternValue)dataLine2.valueAt(i-6)).getEndValue().getValue());
              xd01f.add(pv2.getDateTime(),((PatternValue)dataLine2.valueAt(i-7)).getEndValue().getValue());
              xd02f.add(pv2.getDateTime(),((PatternValue)dataLine2.valueAt(i-8)).getEndValue().getValue());
              xmalf.add(pv2.getDateTime(),((PatternValue)dataLine2.valueAt(i-9)).getEndValue().getValue());
//              xk1f.add(pv2.getDateTime(),kdv21.getKValue());
//              xk2f.add(pv2.getDateTime(), kdv22.getKValue());
//              xd1f.add(pv2.getDateTime(), kdv21.getDValue());
//              xd2f.add(pv2.getDateTime(),kdv22.getDValue());
//              xk01f.add(pv2.getDateTime(),kdv1.getKValue());
//              xk02f.add(pv2.getDateTime(), kdv2.getKValue());
//              xd01f.add(pv2.getDateTime(), kdv1.getDValue());
//              xd02f.add(pv2.getDateTime(),kdv2.getDValue());
//              xmalf.add(pv2.getDateTime(),mav.getValue());
           }
       }
       LineGroup lg = new LineGroup(mlf.culSimpleLine());
//       lg.addLine(x1f.culSimpleLine());
       lg.addLine(xk1f.culSimpleLine());
       lg.addLine(xk2f.culSimpleLine());
       lg.addLine(xd1f.culSimpleLine());
       lg.addLine(xd2f.culSimpleLine());
       lg.addLine(xk01f.culSimpleLine());
       lg.addLine(xk02f.culSimpleLine());
       lg.addLine(xd01f.culSimpleLine());
       lg.addLine(xd02f.culSimpleLine());
 //      lg.addLine(xmalf.culSimpleLine());
  //  try{
    	 
  //     lg.culMSLParameter();
   //    lg.initCalculator(0,n);
   //    Line sml = lg.getMainLine();
   //    Line scl = lg.genConvLine(); 
   //    printConv(sml, scl);
    int lgn = lg.length();
    System.out.println(lgn);
    SimpleLineFactory tmlf = new SimpleLineFactory();
    SimpleLineFactory tclf = new SimpleLineFactory();
    SimpleLineFactory malf = new SimpleLineFactory();
     for(int i=0; i < lgn-60; i++)
     {
     	  for(int j=0;j<20; j++)
     	  {
     	    try{
             lg.initCalculator(i,60-j);
             GroupValue gv = (GroupValue)lg.valueAt(i+60);
             Value mv = gv.getMainValue();
//             System.out.println(v.getValue());
             Value mav = mal.valueAt(gv.getDomainValue());
             tmlf.add(mv);
             tclf.add(gv);
             malf.add(mal.valueAt(mal.mappingIdx(mav)-1));
//             int idx = mal.mappingIdx(mav);
//             double r = mal.valueAt(idx-1).getValue()/mal0.valueAt(idx-1).getValue();
//             System.out.println(r);
//             malf.add(gv.getDateTime(),mal0.valueAt(idx).getValue()*r);
             break;
          }catch(Exception ex){
          	ex.printStackTrace();
         //    System.err.println("i:"+i);
          }
        }
     }
     Line sml = tmlf.culSimpleLine();
     Line scl = tclf.culSimpleLine(); 
     Line mall = malf.culSimpleLine(); 
     System.out.println(scl.length());
      printConv(sml, scl);
      printConv(sml, mall);
       //System.out.println("abc:"+a+" "+ b+" "+c);
 //    } 
 //    catch(Exception ee)
 //    {
 //   }
}}}
 //      dataLine.dump();
 /*      MinutePattern m5 = new MinutePattern(5);
       MinutePattern m15 = new MinutePattern(15);
       MinutePattern m30 = new MinutePattern(30);
       dataLine = sl.patternByStep(m30);
       Enumeration<PatternValue> e = dataLine.elements();
       while(e.hasMoreElements())
       {
           PatternValue pv = e.nextElement();
           PatternValue pv5 = null;
           PatternValue pv15 = null;
           if(pv.getEndIdx()+5<sl.length())
           {
              pv5 = m5.find(sl,pv.getEndIdx()+1);
           }
           if(pv.getEndIdx()+15<sl.length())
           {
               pv15 = m15.find(sl,pv.getEndIdx()+1);
           }
           if(pv5!=null)
           {
              ht.put(pv,pv5);
           }
           if(pv15!=null)
           {
              ht15.put(pv,pv15);
           }
       }
  */
  }
  static void printConv(Line sml, Line scl)
  {
  	   int n = sml.length();
       Line yvarl = sml.diff(sml.constant(sml.summation().valueAt(n-1).getValue()/n));
       Line ycll = sml.diff(scl);
       double yavgVar = yvarl.mul(yvarl).summation().valueAt(n-1).getValue()/n;
       double ycovVar = ycll.mul(ycll).summation().valueAt(n-1).getValue()/n;
       System.out.println("Line Caculator yavgVar:"+yavgVar+"  ycovVar:"+ycovVar+"   cov:"+(1-ycovVar/yavgVar));
  
  }
  static void culBkCount5()
  {
       MinutePattern m5 = new MinutePattern(5);
       Enumeration<PatternValue> ek = dataLine.elements();
       while(ek.hasMoreElements())
       {
           PatternValue pvx = ek.nextElement();
           PatternValue pv5 = (PatternValue)ht.get(pvx);
           if(pv5 == null) continue;
           Line l2 = pvx.toLine();
           PatternLine pl = l2.pattern(m5);
           Enumeration<PatternValue> e = pl.elements();
           int bkc = 0;
           while(e.hasMoreElements())
           {
               PatternValue pv =  e.nextElement();
               if(pv.getOpen() < pv.getClose())
               {
                  bkc ++;
               }
           }
           System.out.println((pv5.getClose() - pv5.getOpen())+ " Bkc : "+ bkc + " "+String.format("%tT",new Date(pv5.getDateTime())));
       }
  }
  static void culBkCount5S()
  {
       MinutePattern m5 = new MinutePattern(5);
       Enumeration<PatternValue> ek = dataLine.elements();
       while(ek.hasMoreElements())
       {
           PatternValue pvx = ek.nextElement();
           PatternValue pv5 = (PatternValue)ht.get(pvx);
           if(pv5 == null) continue;
           Line l2 = pvx.toLine();
           PatternLine pl = l2.patternByStep(m5);
           Enumeration<PatternValue> e = pl.elements();
           int bkc = 0;
           while(e.hasMoreElements())
           {
               PatternValue pv =  e.nextElement();
               if(pv.getOpen() < pv.getClose())
               {
                  bkc ++;
               }
           }
           System.out.println((pv5.getClose() - pv5.getOpen())+ " Bkc : "+ bkc + " "+String.format("%tT",new Date(pv5.getDateTime())));
       }
  }

  static void culBkCount5S(int kr, int k, int sk)
  {
       MinutePattern m5 = new MinutePattern(5);
       Enumeration<PatternValue> ek = dataLine.elements();
       SimpleLineFactory mlf = new SimpleLineFactory();
       SimpleLineFactory x1f = new SimpleLineFactory();
       SimpleLineFactory x2f = new SimpleLineFactory();
       SimpleLineFactory x31f = new SimpleLineFactory();
       SimpleLineFactory x32f = new SimpleLineFactory();
       SimpleLineFactory x33f = new SimpleLineFactory();
       SimpleLineFactory x34f = new SimpleLineFactory();
       SimpleLineFactory x41f = new SimpleLineFactory();
       SimpleLineFactory x42f = new SimpleLineFactory();
       SimpleLineFactory x43f = new SimpleLineFactory();
       SimpleLineFactory x44f = new SimpleLineFactory();
        while(ek.hasMoreElements())
       {
           PatternValue pvx = ek.nextElement();
           PatternValue pv15 = (PatternValue)ht.get(pvx);
           if(pv15 == null) continue;
           mlf.add(pv15.getDateTime(), pv15.getClose() - pv15.getOpen());
              
           Line l2 = pvx.toLine();
           PatternLine pl = l2.patternByStep(m5);
           Enumeration<PatternValue> e = pl.elements();
           int bkc = 0;
           while(e.hasMoreElements())
           {
               PatternValue pv =  e.nextElement();
               if(pv.getOpen() < pv.getClose())
               {
                  bkc ++;
               }
           }
           x1f.add(pv15.getDateTime(), bkc);

           pl = l2.pattern(m5);
           e = pl.elements();
           bkc = 0;
           while(e.hasMoreElements())
           {
               PatternValue pv =  e.nextElement();
               if(pv.getOpen() < pv.getClose())
               {
                  bkc ++;
               }
           }
           x2f.add(pv15.getDateTime(), bkc);
           
           SimpleLineFactory lf = new SimpleLineFactory(l2);
           KDLine kdl = lf.culKDLine(kr,k,sk);
           KDValue kdv1 = (KDValue)kdl.valueAt(0);
           KDValue kdv2 = (KDValue)kdl.valueAt(kdl.length()/3);
           KDValue kdv3 = (KDValue)kdl.valueAt((kdl.length()+kdl.length())/3);
           KDValue kdv4 = (KDValue)kdl.valueAt(kdl.length()-1);
           x31f.add(pv15.getDateTime(), kdv1.getKValue());
           x32f.add(pv15.getDateTime(), kdv2.getKValue());
           x33f.add(pv15.getDateTime(), kdv3.getKValue());
           x34f.add(pv15.getDateTime(), kdv4.getKValue());
           x41f.add(pv15.getDateTime(), kdv1.getDValue());
           x42f.add(pv15.getDateTime(), kdv2.getDValue());
           x43f.add(pv15.getDateTime(), kdv3.getDValue());
           x44f.add(pv15.getDateTime(), kdv4.getDValue());
          // x4f.add(pv15.getDateTime(), kdv.getDValue());


 //          System.out.println((pv15.getClose() - pv15.getOpen())+ " Bkc : "+ bkc + " "+String.format("%tT",new Date(pv15.getDateTime())));
       }
       LineGroup lg = new LineGroup(mlf.culSimpleLine());
       lg.addLine(x1f.culSimpleLine());
       lg.addLine(x2f.culSimpleLine());
       lg.addLine(x31f.culSimpleLine());
       lg.addLine(x32f.culSimpleLine());
       lg.addLine(x33f.culSimpleLine());
       lg.addLine(x34f.culSimpleLine());
       lg.addLine(x41f.culSimpleLine());
       lg.addLine(x42f.culSimpleLine());
       lg.addLine(x43f.culSimpleLine());
       lg.addLine(x44f.culSimpleLine());
       lg.culMSLParameter();
  }

  static void culBkCount15S(int kr, int k, int sk)
  {
       MinutePattern m5 = new MinutePattern(5);
       Enumeration<PatternValue> ek = dataLine.elements();
       SimpleLineFactory mlf = new SimpleLineFactory();
       SimpleLineFactory x1f = new SimpleLineFactory();
       SimpleLineFactory x2f = new SimpleLineFactory();
       SimpleLineFactory x31f = new SimpleLineFactory();
       SimpleLineFactory x32f = new SimpleLineFactory();
       SimpleLineFactory x33f = new SimpleLineFactory();
       SimpleLineFactory x34f = new SimpleLineFactory();
       SimpleLineFactory x41f = new SimpleLineFactory();
       SimpleLineFactory x42f = new SimpleLineFactory();
       SimpleLineFactory x43f = new SimpleLineFactory();
       SimpleLineFactory x44f = new SimpleLineFactory();
        while(ek.hasMoreElements())
       {
           PatternValue pvx = ek.nextElement();
           PatternValue pv15 = (PatternValue)ht15.get(pvx);
           if(pv15 == null) continue;
           mlf.add(pv15.getDateTime(), pv15.getClose() - pv15.getOpen());

           Line l2 = pvx.toLine();
           PatternLine pl = l2.patternByStep(m5);
           Enumeration<PatternValue> e = pl.elements();
           int bkc = 0;
           while(e.hasMoreElements())
           {
               PatternValue pv =  e.nextElement();
               if(pv.getOpen() < pv.getClose())
               {
                  bkc ++;
               }
           }
           x1f.add(pv15.getDateTime(), bkc);

           pl = l2.pattern(m5);
           e = pl.elements();
           bkc = 0;
           while(e.hasMoreElements())
           {
               PatternValue pv =  e.nextElement();
               if(pv.getOpen() < pv.getClose())
               {
                  bkc ++;
               }
           }
           x2f.add(pv15.getDateTime(), bkc);
           
           SimpleLineFactory lf = new SimpleLineFactory(l2);
           KDLine kdl = lf.culKDLine(kr,k,sk);
           KDValue kdv1 = (KDValue)kdl.valueAt(0);
           KDValue kdv2 = (KDValue)kdl.valueAt(kdl.length()/3);
           KDValue kdv3 = (KDValue)kdl.valueAt((kdl.length()+kdl.length())/3);
           KDValue kdv4 = (KDValue)kdl.valueAt(kdl.length()-1);
           x31f.add(pv15.getDateTime(), kdv1.getKValue());
           x32f.add(pv15.getDateTime(), kdv2.getKValue());
           x33f.add(pv15.getDateTime(), kdv3.getKValue());
           x34f.add(pv15.getDateTime(), kdv4.getKValue());
           x41f.add(pv15.getDateTime(), kdv1.getDValue());
           x42f.add(pv15.getDateTime(), kdv2.getDValue());
           x43f.add(pv15.getDateTime(), kdv3.getDValue());
           x44f.add(pv15.getDateTime(), kdv4.getDValue());
          // x4f.add(pv15.getDateTime(), kdv.getDValue());


 //          System.out.println((pv15.getClose() - pv15.getOpen())+ " Bkc : "+ bkc + " "+String.format("%tT",new Date(pv15.getDateTime())));
       }
       LineGroup lg = new LineGroup(mlf.culSimpleLine());
       lg.addLine(x1f.culSimpleLine());
       lg.addLine(x2f.culSimpleLine());
       lg.addLine(x31f.culSimpleLine());
       lg.addLine(x32f.culSimpleLine());
       lg.addLine(x33f.culSimpleLine());
       lg.addLine(x34f.culSimpleLine());
       lg.addLine(x41f.culSimpleLine());
       lg.addLine(x42f.culSimpleLine());
       lg.addLine(x43f.culSimpleLine());
       lg.addLine(x44f.culSimpleLine());
       lg.culMSLParameter();
  }

  static void culKDCount15(int kr, int k, int sk)
  {
       MinutePattern m5 = new MinutePattern(5);
       Enumeration<PatternValue> ek = dataLine.elements();
       SimpleLineFactory mlf = new SimpleLineFactory();
       SimpleLineFactory x1f = new SimpleLineFactory();
       SimpleLineFactory x2f = new SimpleLineFactory();
       SimpleLineFactory x3f = new SimpleLineFactory();
       
       while(ek.hasMoreElements())
       {
           PatternValue pvx = ek.nextElement();
           PatternValue pv15 = (PatternValue)ht15.get(pvx);
           if(pv15 == null) continue;
           SimpleLineFactory lf = new SimpleLineFactory(pvx.toLine());
           KDLine kdl = lf.culKDLine(kr,k,sk);
           KDValue kdv = (KDValue)kdl.valueAt(kdl.length()-1);
           
//           System.out.print(String.format("%tT",new Date(pv15.getDateTime()))+" "+String.format("%1$.2f",(pv15.getClose() - pv15.getOpen()))+" Diff: ("+String.format("%1$.2f",(kdv.getKValue()-kdv.getDValue()))+")");
//           System.out.println("  KValue:"+String.format("%1$.2f",kdv.getKValue())+" DValue:"+ String.format("%1$.2f",kdv.getDValue()));
           mlf.add(pv15.getDateTime(), pv15.getClose() - pv15.getOpen());
           x1f.add(pv15.getDateTime(), kdv.getKValue()-kdv.getDValue());
           x2f.add(pv15.getDateTime(), kdv.getKValue());
           x3f.add(pv15.getDateTime(), kdv.getDValue());
       }
       LineGroup lg = new LineGroup(mlf.culSimpleLine());
//       lg.addLine(x1f.culSimpleLine());
       lg.addLine(x2f.culSimpleLine());
       lg.addLine(x3f.culSimpleLine());
//       System.out.println("LG dump.....................");
//       lg.dump();
 //       lg.culMSLParameter(0);
 //       lg.culMSLParameter(1);
 //       lg.culMSLParameter(2);
 //       lg.culMSLParameter(0,1);
 //       lg.culMSLParameter(1,2);
 //       lg.culMSLParameter(2,0);
        lg.culMSLParameter();
 /*       
       LineGroup lg1 = new LineGroup(mlf.culSimpleLine());
       lg1.addLine(x1f.culSimpleLine());
       lg1.addLine(x2f.culSimpleLine());
       LineGroup lg2 = new LineGroup(mlf.culSimpleLine());
       lg2.addLine(x2f.culSimpleLine());
       lg2.addLine(x3f.culSimpleLine());
       LineGroup lg3 = new LineGroup(mlf.culSimpleLine());
       lg3.addLine(x3f.culSimpleLine());
       lg3.addLine(x1f.culSimpleLine());
       lg1.culMSLParameter();
       lg2.culMSLParameter();
       lg3.culMSLParameter();
 */
  }

}