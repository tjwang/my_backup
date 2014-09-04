import stock.db.*;
import stock.sandy.*	;
import stock.app.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.fight.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test2 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("ÁÈ¤j¿úTest");
      StockF   sf = new StockF("0001", arg[0], null);
      MotherPanel mp = new MotherPanel();

      KLine rl = sf.culRawKLine();
      StockF3 sf3 = new StockF3(rl);
//      ClusterLine cl = sf3.culCLine();
      ClusterLine cl = rl.clusterByValue(50);
      Enumeration<ClusterValue> e = cl.elements();
      TreeSet vts = new TreeSet(new CurveValueComparator());
      Vector cvv = new Vector();
      while(e.hasMoreElements())
      {
         Enumeration<CurveValue> ce = clusterList(e.nextElement(),rl);
         while(ce.hasMoreElements())
         {
           // vts.add(e.nextElement());
            cvv.add(ce.nextElement());
         }
      }
      CurveValue[] ary = new  CurveValue[cvv.size()];
      int[] arynum = new int[cvv.size()];
      cvv.toArray(ary);
      for(int i = 0; i < ary.length; i++) 
         arynum[i] = 0;
      for(int i = 0; i < ary.length; i++)
      {
         for(int j = 0; j < ary.length; j++)
         {
             if(i != j && ary[j].getType() == ary[i].getType() && ary[j].getApexValue() != ary[i].getApexValue())
             {
                 if(ary[i].isContain(ary[j]))
                 {
                    arynum[i]++;
                   // System.out.println("i"+i+": "+arynum[i]);
                 }     
             }
          }
      }
      for(int i = 0; i < ary.length; i++) 
      {
         if(arynum[i] ==0)
         {
            vts.add(ary[i]);
           // System.out.println("vts add "+ary[i]);
          }
      }
      
      Iterator<CurveValue> i = vts.iterator();
      Vector cpool = new Vector();
      CurveValue cv = null;
      while(i.hasNext())
      {
          CurveValue cv1 = i.next();
          if(cv == null)
          {
             cv = cv1;
          } else if(cv.getApexValue() != cv1.getApexValue())
          {
             cpool.add(cv);
             cv = cv1;  
          } else
          {
             if(cv1.getValue() -  cv.getValue() > 0.01)
             {
                 cv = cv1;  
             }
          }
      }
      Enumeration<CurveValue> ce = cpool.elements();
      while(ce.hasMoreElements())
      {
          cv = ce.nextElement();
          if(cv.getType()!=1)
              cv.dump();
      }
/*
      Line l3 = sf3.culVLine();
//      mp.addPainting(new MAPainting(sf.culMALine(33), Color.RED),"raw data", 0);
//      mp.addPainting(new VPainting(l3, Color.RED));
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
 */
          
    
  }


  static public Enumeration clusterList(ClusterValue cv, Line line)
   {
       Enumeration<ImageValue> e = cv.elements(); 
//       System.out.println("clustList ==============================");
       Value[] va = new Value[cv.getSize()];
       int l = 0;
       Vector pool = new Vector();
       while(e.hasMoreElements())
       {
//          ImageValue iv = e.nextElement();
//          va[l] = iv.getImage();
          va[l] = e.nextElement();
    //      va[l].dump();
          l++;
       }
       for(int i = l-1; i >= 0 ; i--)
       {
          for(int j = 0 ; j < i; j++)
          {
             int iIdx = line.mappingIdx(va[i]);
             int jIdx = line.mappingIdx(va[j]);
             if(iIdx - jIdx >= 10)
             {
                CurveValue cuv = new CurveValue(line, jIdx, iIdx);
                if(cuv.getType()!= 0)
                {
                   pool.add(cuv);
                }
             } else if(iIdx - jIdx <= -10)
             {
                CurveValue cuv = new CurveValue(line, iIdx, jIdx);
                if(cuv.getType()!= 0)
                {
                   pool.add(cuv);
                }
              }
          }
       }
      // Enumeration<CurveValue> e2 = pool.elements();
      // CurveValue cuv = e2.nextElement();
       CurveValue[] ary = new  CurveValue[pool.size()];
       pool.toArray(ary);
       Vector pool2 = new Vector();
       for(int i = 0; i < ary.length; i++)
       {
          boolean valid = true;
          for(int j = 0; j < ary.length; j++)
          {
             if(i != j && ary[j].getType() == ary[i].getType() && ary[j].getApexValue() == ary[i].getApexValue())
             {
                 if(ary[i].isContain(ary[j]))
                  {
                     valid = false;
                     break;
                  }     
             }
          }
          if(valid)
              pool2.add(ary[i]);
       }
     /*  Enumeration<CurveValue> e2 = pool2.elements();
       while(e2.hasMoreElements())
       {
           CurveValue cuv = e2.nextElement();
           //cuv.dump();
           System.out.println(cuv);
       }*/
       return pool2.elements();
   }
    private static class CurveValueComparator implements Comparator<CurveValue>
    {
       public int compare(CurveValue v1, CurveValue v2) 
       {
           if(v1.getApexValue().getDateTime() < v2.getApexValue().getDateTime()) return -1;
           return 1;
       }
       
       public boolean equals(Object obj) 
       {
          return super.equals(obj);
       }
    }

}
//                if(ary[j].isContain(ary[i]) || ary[i].isContain(ary[j]))
//                {
//                 //  System.out.println("jv" + ary[j].getValue() +" iv"+ary[i].getValue());
//                   if(ary[j].getValue() -  ary[i].getValue() > 0.001)
//                   {
//                      valid = false;
//                      break;
//                   }
//                   if(ary[j].getValue() -  ary[i].getValue() < 0.00001 &&
//                      ary[j].getValue() -  ary[i].getValue() > -0.00001 )
//                   {
//               //    System.out.println("iev" +ary[i].getEndValue().getValue() +" jev"+ary[j].getEndValue().getValue());
//               /*
//                       if(ary[j].getStartValue() == ary[i].getStartValue() && 
//                          (ary[i].getEndValue().getValue() - ary[j].getEndValue().getValue()) * ary[i].getType() < 0)
//                       {
//                           
//                          valid = false;
//                          break;
//                       } 
//                        if(ary[j].getEndValue() == ary[i].getEndValue() && 
//                          (ary[i].getStartValue().getValue() - ary[j].getStartValue().getValue()) * ary[i].getType() < 0)
//                       {
//                          valid = false;
//                          break;
//                       } 
//                  */
  //                 }
