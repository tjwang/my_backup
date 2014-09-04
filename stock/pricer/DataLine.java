package stock.pricer;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;

public class DataLine extends AbstractLine implements Line
{
   Vector<SimpleValue[]> DataPool = null;
   public DataLine(DataLine dl,double[] data)
   {
      DataPool = new Vector<SimpleValue[]>();
      int basei = 0;
      Enumeration<SimpleValue[]> sae = dl.DataPool.elements();
      while(sae.hasMoreElements())
      {
  	      SimpleValue[] sva = sae.nextElement();
  	      basei +=sva.length;
          DataPool.add(sva);
      }
   	  SimpleValue[] nData = new SimpleValue[data.length];
   	  for(int i = 0; i < data.length ; i++)
   	  {
         nData[i] = new SimpleValue(new DomainValue(Domain.getDomainByName("N"),basei++) ,data[i]);	  
   	  }
      DataPool.add(nData);
  	  
   }

   public DataLine(Line bl)
   {
   	  DataPool = new Vector<SimpleValue[]>();
   	  int basei = 0;
   	  SimpleValue[] baseData = new SimpleValue[bl.length()];
   	  Enumeration<Value> e = bl.elements();
   	  while(e.hasMoreElements())
   	  {
   	     baseData[basei] = new SimpleValue(new DomainValue(Domain.getDomainByName("N"),basei) ,e.nextElement().getValue());
   	     basei++;
   	  }
   	  DataPool.add(baseData);
   }

   public Enumeration elements()
   {
   	   Vector v = new Vector();
   	   Enumeration<SimpleValue[]> sae = DataPool.elements();
   	   while(sae.hasMoreElements())
   	   {
   	      SimpleValue[] sva = sae.nextElement();
   	      for(int i = 0; i < sva.length; i++)
   	      {
   	         v.add(sva[i]);
   	      } 
   	   }
       return v.elements();
   }
   
   public int length()
   {
   	   int dl = 0;
   	   Enumeration<SimpleValue[]> sae = DataPool.elements();
   	   while(sae.hasMoreElements())
   	   {
   	      SimpleValue[] sva = sae.nextElement();
          dl += sva.length;
   	   }
       return dl;
   }
   
   public Value valueAt(int index)
   {
   	   Enumeration<SimpleValue[]> sae = DataPool.elements();
   	   while(sae.hasMoreElements())
   	   {
   	      SimpleValue[] sva = sae.nextElement();
          if(index >= sva.length)
          {
             index -= sva.length;
          } else
          {
             return sva[index];
          }
          
   	   }
       return null;
   }
   
   public Line sub(int startIdx,int endIndex)
   {
//      SimpleLine subSimpleLine = new SimpleLine();
//      for(int i = startIdx; i<=endIndex ; i++)
//      {
//         subSimpleLine.add(valueAt(i));
//      }
//      return subSimpleLine;
       return null;
   }

//   public DataLine(SimpleLine bl, double[] data)
//   {
//   	  int basei = 0;
//   	  Enumeration<Value> e = bl.elements();
//   	  while(e.hasMoreElements())
//   	  {
//   	     add(new SimpleValue(new DomainValue(Domain.getDomainByName("N"),basei++) ,e.nextElement().getValue()));
//   	  }
//   	  for(int i = 0; i < data.length ; i++)
//   	  {
//         add(new SimpleValue(new DomainValue(Domain.getDomainByName("N"),basei++) ,data[i]));	  
//   	  }
//   }
   
}