import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.pattern.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

//統計 30分內資料,和5分後的關係
public class Test1 {
  static     Hashtable ht = new Hashtable();
  static     Hashtable ht15 = new Hashtable();
  static   PatternLine dataLine = null;
   public static void main(String[] arg) throws Exception
   {
       IndexLF  ilf = new IndexLF(arg[0]);
       SimpleLine sl = ilf.culSimpleLine();
       MinutePattern m5 = new MinutePattern(5);
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
                        

           /* else
           {
              pv5 = m5.find(sl,pv.getEndIdx());
              pv15 = m15.find(sl,pv.getEndIdx());
           }*/
           if(pv5!=null)
           {
              ht.put(pv,pv5);
           }
           if(pv15!=null)
           {
              ht15.put(pv,pv15);
           }
       }
//       culBkCount15();
       culBkCount5S(17,12,8);
       culBkCount15S(17,12,8);
     // for(int i =10; i < 50 ; i++)
     //  for(int j = 5; j < 30; j++)
      //  for(int k=3; k < 15; k ++)
        {
        // System.out.println(i+" "+ j+ " "+ k+ " :");
       //   culKDCount15(17,12,8);
        }  
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