package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class Curve2Pattern implements Pattern {

    int s;
    int p;
    int e;
    double lb;
    public Curve2Pattern(int period, int startrange, int endrange, double lowBound)
    {
    	   p = period;
    	   s = startrange;
    	   e = endrange;
    	   lb = lowBound;
    }
    
    public PatternValue find(Line l, int sIdx)
    {
          
        for(int startIdx = sIdx; startIdx < l.length() - p - e; startIdx++)
        {
            int endIdx = startIdx + p;
            if(startIdx < s) continue; 
            Value ApexV  = null;
            Value startV = l.valueAt(startIdx);
            Value endV =  l.valueAt(endIdx);
            Value sV = null;
            Value eV = null;
            Value pV = null;
            double savgV = 0;
            double pavgV = 0;
            double eavgV = 0;
            for(int i = 0; i < s; i++)
            {
                 Value v = l.valueAt(startIdx-i);
       	         if (lb < 0)
       	         {
        	          if((sV == null)|| (v.getValue() < sV.getValue()))
       	            {
                       sV = v;
       	            }
       	         } else
       	         {
         	          if((sV == null)|| (v.getValue() > sV.getValue()))
       	            {
                       sV = v;
       	            }
      	         }
      	         savgV += v.getValue();
            }   
           savgV = savgV / s;
             for(int i = startIdx; i < endIdx; i++)
            {
                 Value v = l.valueAt(i);
       	         if(lb < 0)
       	         {
       	            if((pV == null)|| (v.getValue() > pV.getValue()))
       	            {
       	         	     pV = v;
       	            }
       	            if((ApexV == null)|| (v.getValue() < ApexV.getValue()))
       	            {
       	               ApexV = v;
       	            }
       	         } else
       	         {
       	            if((pV == null)|| (v.getValue() < pV.getValue()))
       	            {
       	         	     pV = v;
       	            }
       	            if((ApexV == null)|| (v.getValue() > ApexV.getValue()))
       	            {
       	               ApexV = v;
       	            }
       	         }
      	         pavgV += v.getValue();
            }
            pavgV = ApexV.getValue();// / p;
            for(int i = 0; i < e; i++)
            {
                 Value v = l.valueAt(startIdx+i+p);
       	         if (lb < 0)
       	         {
        	          if((eV == null)|| (v.getValue() < eV.getValue()))
       	            {
                       eV = v;
       	            }
       	         } else
       	         {
         	          if((eV == null)|| (v.getValue() > eV.getValue()))
       	            {
                       eV = v;
       	            }
      	         }
      	         eavgV += v.getValue();
            }   
            eavgV = eavgV / e;
           double sdiff = (pavgV - savgV)/savgV;
           double ediff = (pavgV - eavgV)/eavgV;
            //if(sediff > -0.03 && sediff < 0.03)
            {
  //              double vBase = (startV.getValue() + endV.getValue()) / 2;
 //               double vAmp = (ApexV.getValue() -  vBase) / vBase;
                if(lb < 0 && sdiff < lb && ediff < lb)
                {
                   Vector v = new Vector();
          //         v.add(minV);
          //         v.add(maxV);
                   v.add(ApexV);
                	 v.add(new Double((sdiff+ediff)/2));
                   return new  PatternValue(this, l, startIdx, endIdx-1, v);
                }else if(lb > 0 && sdiff > lb && ediff > lb)
                {
                   Vector v = new Vector();
           //        v.add(minV);
           //        v.add(maxV);
                   v.add(ApexV);
            	     v.add(new Double((sdiff+ediff)/2));
                   return new  PatternValue(this, l, startIdx, endIdx-1, v);
                }
            }
        }  
        return null;
    }
    
    public double culValue(PatternValue ptv)
    {
       //   Value sv = ptv.getMotherLine().valueAt(ptv.getStartIdx());
          return 0;
          //sv.getValue();
    
    }
    
    public void dump(PatternValue ptv)
    {
        System.out.println("Curve2Pattern Value dump:");
        Enumeration e = ptv.getPatternData();
   //     Value minV = (Value)e.nextElement();
   //     Value maxV = (Value)e.nextElement();
        Value ApexV = (Value)e.nextElement();
  //	    System.out.print("minV:");
  //	    minV.dump();
  //	    System.out.print("maxV:");
  // 	    maxV.dump();
 	      System.out.print("Apex:");
  	    ApexV.dump();
 // 	    System.out.println("Dis1:"+ ((ApexV.getValue() - ptv.getStartValue().getValue()) / ptv.getStartValue().getValue()));
//  	    System.out.println("Dis2:"+ ((ApexV.getValue() - ptv.getEndValue().getValue()) / ptv.getEndValue().getValue()));
 	      System.out.println("vAmp:"+e.nextElement());
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Value :"+(culValue(ptv)));
    }
}