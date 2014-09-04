package stock.pricer;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;
import java.util.*;

public class PPDMap {
     
    Hashtable<Double,Double> ht;  
   
    PPDMap()
    {
        ht = new Hashtable();    
    }
   
    double getPriceProb(double p)
    {
       return ht.get(new Double(p)).doubleValue();
    }
    
    void putPriceProb(double pric, double prob)
    {
    	  Double pricO = new Double(pric);
        Double prevp = ht.get(pric);
        if(prevp == null)
        {
           ht.put(pricO , new Double(prob));
        } else
        {
           ht.put(pricO, new Double(prevp.doubleValue()+prob));
        }
    }
    
    double[] dumpPrice()
    {
        double[]  data = new double[ht.size()];    	
    	  Enumeration<Double> e =  ht.keys();
    	  for(int i = 0; i<data.length; i++)
    	  {
    	      data[i] = e.nextElement().doubleValue();
    	  }
    	  qsort(data, 0 , data.length);
        return data;
    }
    
    void qsort(double[] data, int pos, int len)
    {
  //      double[] ddata = new double[len];
        double v = data[pos];
        int li = pos+1;
        int ri = pos+len-1;
        boolean changeOn = true;
        while(ri >= li)
        {
           if(changeOn)
           {
              if(data[li] > v)
              {
                 changeOn = false;
              } else
              {
                 li++;
              }
           } else
           {
              if(data[ri] > v)
              {
                 ri--;
              }else
              {
                 changeOn = true;
                 double dv = data[li];
                 data[li] = data[ri];
                 data[ri] = dv ;
                 li++;
                 ri--;
              }
          
           }
        }
        data[pos] = data[ri];
        data[ri] = v;
        if(ri-pos > 1)
          qsort(data, pos, ri-pos);
        if(pos+len-1-ri > 1)
          qsort(data, ri+1, pos+len-1-ri);
        	  
    }
}      

