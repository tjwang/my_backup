package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ClusterLine extends AbstractLine implements Line {
	
//	 Mapper mapper;
   Vector<ClusterValue> dataPool = null;

   ClusterLine()
   {
      dataPool = new Vector<ClusterValue>();
   }
 
   public Enumeration elements()
   {
       return dataPool.elements();
   }
   
   public int length()
   {
       return dataPool.size();
   }
   
   public Value valueAt(int index)
   {
        return dataPool.elementAt(index);
   }
   
   public Line sub(int startIdx,int endIndex)
   {
      ClusterLine subClusterLine = new ClusterLine();
      for(int i = startIdx; i<=endIndex ; i++)
      {
         subClusterLine.add((ClusterValue)valueAt(i));
      }
      return subClusterLine;
   }
   
   public void add(ClusterValue sv)
   {
       dataPool.add(sv);
   }      
   
   public Line getFirstValueLine()
   {
      Enumeration<ClusterValue> e = dataPool.elements();
      SimpleLine sl = new SimpleLine();
      while(e.hasMoreElements())
      {
           ClusterValue tcv = e.nextElement();
           sl.add(new SimpleValue(tcv.getDomainValue(), tcv.getFirstValue()));
      }
      return sl;
   }
   
   public Line getLastValueLine()
   {
      Enumeration<ClusterValue> e = dataPool.elements();
      SimpleLine sl = new SimpleLine();
      while(e.hasMoreElements())
      {
           ClusterValue tcv = e.nextElement();
           sl.add(new SimpleValue(tcv.getDomainValue(), tcv.getLastValue()));
      }
      return sl;
   }
   
   public Line getDomainValueLine()
   {
      Enumeration<ClusterValue> e = dataPool.elements();
      SimpleLine sl = new SimpleLine();
      while(e.hasMoreElements())
      {
           ClusterValue tcv = e.nextElement();
           DomainValue dv = tcv.getDomainValue();
           sl.add(new SimpleValue(dv, dv.getDoubleValue()));
      }
      return sl;
   }
   
   public Line getAvgValueLine()
   {
      Enumeration<ClusterValue> e = dataPool.elements();
      SimpleLine sl = new SimpleLine();
      while(e.hasMoreElements())
      {
           ClusterValue tcv = e.nextElement();
           sl.add(new SimpleValue(tcv.getDomainValue(), tcv.getAvgValue()));
      }
      return sl;
   }
   
   public Line getSumValueLine()
   {
      Enumeration<ClusterValue> e = dataPool.elements();
      SimpleLine sl = new SimpleLine();
      System.out.println("getSumValueLine");
      while(e.hasMoreElements())
      {
           ClusterValue tcv = e.nextElement();
           sl.add(new SimpleValue(tcv.getDomainValue(), tcv.getSumValue()));
      }
      return sl;
   }
   
   public Line getSizeLine()
   {
      Enumeration<ClusterValue> e = dataPool.elements();
      SimpleLine sl = new SimpleLine();
      System.out.println("getSizeLine");
      while(e.hasMoreElements())
      {
           ClusterValue tcv = e.nextElement();
           sl.add(new SimpleValue(tcv.getDomainValue(), tcv.getSize()));
      }
      return sl;
   }
   
   public Line getProbLine()
   {
      Enumeration<ClusterValue> e = dataPool.elements();
      SimpleLine sl = new SimpleLine();
      System.out.println("getProbLine");
      double space = 0;
      while(e.hasMoreElements())
      {
           ClusterValue tcv = e.nextElement();
           space +=  tcv.getSize();
      }
      e = dataPool.elements();
      double psum = 0;
      while(e.hasMoreElements())
      {
           ClusterValue tcv = e.nextElement();
           psum += (tcv.getSize()/space)*100;
         //  if(psum > 5 && psum < 95)
           {
              sl.add(new SimpleValue(tcv.getDomainValue(), (tcv.getSize()/space)*100));
           }
      }
      return sl;
   }
}