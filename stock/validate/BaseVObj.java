package stock.validate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;

public  abstract class BaseVObj {
    
    public double getAvg(Line l)
    {
        Enumeration e = l.elements();
        Value v = null;
        int n = 0;
        double sum = 0;
        while(e.hasMoreElements())
        {
           v = (Value) e.nextElement();
           sum += v.getValue();
           n++;
        }
        if( n > 0)
           return sum/n;
        else
           return 0;   
    }
   
    public double getDiffSum(Line l, double d)
    {
        Enumeration e = l.elements();
        Value v = null;
        double sum = 0;
        while(e.hasMoreElements())
        {
           v = (Value) e.nextElement();
           sum += v.getValue() - d;
        }
        return sum;
        
    }

    public double getDiffSquareSum(Line l, double d)
    {
        Enumeration e = l.elements();
        Value v = null;
        double sum = 0;
        while(e.hasMoreElements())
        {
           v = (Value) e.nextElement();
           sum += (v.getValue() - d) * (v.getValue() - d) ;
        }
        return sum;
        
    }

    private static double minData()
    {
        return 0;
    }
    
    public double getCloseSquareSum(Line l1, Line l2, int shift)
    {
      double[] closediff= new double[5] ;
      for(int j = 0; j < 5 ; j++)
      {
         closediff[j] = -1;
      }
      
      double sum = 0;
      for(int i = shift; i < l1.length(); i ++)
      {
           Value v1 = l1.valueAt(i);
           Value v2 = l2.valueAt(i-shift);
           double diff = 0;
           if(v1.getValue()<v2.getValue())
           {
              diff  = v2.getValue() - v1.getValue();
           } else
           {
              diff  = v1.getValue() - v2.getValue();
           }
           closediff[i%5] = diff;
       }
       return 0;
        
    }

    public double getVariance(Line l)
    {
       return   getDiffSquareSum(l, getAvg(l)) / l.length();     
    }

    public double getDeviation(Line l)
    {
       return   Math.pow(getVariance(l), 0.5);     
    }
    
    public double getHighRate(Line l1, Line l2, int shift)
    {
      
      double sum = 0;
      double vHighCount = 0;
      double vCulCount = 0;
      for(int i = shift; i < l1.length(); i ++)
      {
           Value v1 = l1.valueAt(i);
           Value v2 = l2.valueAt(i-shift);
           if(v1.getValue()>v2.getValue())
           {
           	   vHighCount++;
           }
           vCulCount ++;
       }
       return vHighCount/vCulCount;
        
    }
    
    public double getCutSum(Line l, double d)
    {
        Enumeration e = l.elements();
        Value v = null;
        double sum = 0;
        while(e.hasMoreElements())
        {
           v = (Value) e.nextElement();
           if(v.getValue() > d)
           sum += v.getValue() - d;
        }
        return sum;
        
    }
}      

