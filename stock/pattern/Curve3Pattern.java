package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class Curve3Pattern implements Pattern {

    int p;
    double lb;
    public Curve3Pattern(int period, double lowBound)
    {
    	   p = period;
    	   lb = lowBound;
    }
    
    public PatternValue find(Line l, int sIdx)
    {
          
        for(int startIdx = sIdx; startIdx < l.length() - p; startIdx++)
        {
            int endIdx = startIdx + p;
            Value ApexV = null;
            int ApexIdx = 0;
            double fV = 0;
            int FrontIdx = -1;
            double bV = 0;
            int BackIdx = -1;
            
            for(int i = startIdx; i < endIdx; i++)
            {
                 Value v = l.valueAt(i);
                 if(lb > 0)
                 {
       	           if((ApexV == null)|| (v.getValue() > ApexV.getValue()))
       	           {
                      ApexV = v;
                      ApexIdx = i;
       	           }
       	         } else
       	         {
       	         	
       	           if((ApexV == null)|| (v.getValue() < ApexV.getValue()))
       	           {
       	         	     ApexV = v;
       	         	     ApexIdx = i;
       	           }
       	        }
            }
            for(int i = ApexIdx; i >= startIdx; i--)
            {
            	  Value v = l.valueAt(i);
            	  fV = (ApexV.getValue() - v.getValue()) /  v.getValue();
                if( fV < lb && lb < 0)
                {
                    FrontIdx = i;
                    break;
                }
                if(fV > lb && lb > 0)
                {
                    FrontIdx = i;
                    break;
                }
            }
            for(int i = ApexIdx; i < endIdx; i++)
            {
            	  Value v = l.valueAt(i);
            	  bV = (ApexV.getValue() - v.getValue()) /  v.getValue();
                if( bV < lb && lb < 0)
                {
                    BackIdx = i;
                    break;
                }
                if(bV > lb && lb > 0)
                {
                    BackIdx = i;
                    break;
                }
            }
            if(BackIdx >= 0 && FrontIdx >= 0)
            {
            	 Vector v = new Vector();
            	 v.add(ApexV);
            	 v.add(new Double((fV+bV)/2));
               return new  PatternValue(this, l, FrontIdx, BackIdx, v);
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
        System.out.println("CurvePattern Value dump:");
        Enumeration e = ptv.getPatternData();
        Value ApexV = (Value)e.nextElement();
 	      System.out.print("Apex:");
  	    ApexV.dump();
 	      System.out.println("vAmp:"+e.nextElement());
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Value :"+(culValue(ptv)));
    }
}