package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class CurvePattern implements Pattern {

    int p;
    double lb;
    public CurvePattern(int period, double lowBound)
    {
    	   p = period;
    	   lb = lowBound;
    }
    
//    public PatternValue find(Line l, int sIdx)
//    {
//          
//        for(int startIdx = sIdx; startIdx < l.length() - p; startIdx++)
//        {
//            int endIdx = startIdx + p;
//            Value maxV = null;
//            Value minV = null;      
//            Value startV = null;
//            Value endV = null;      
//            for(int i = startIdx; i < endIdx; i++)
//            {
//                 Value v = l.valueAt(i);
//       	         if((maxV == null)|| (v.getValue() > maxV.getValue()))
//       	         {
//                    maxV = v;
//       	         }
//       	         if((minV == null)|| (v.getValue() < minV.getValue()))
//       	         {
//       	         	  minV = v;
//       	         }
//            }
//            startV = l.valueAt(startIdx);
//            endV =  l.valueAt(endIdx);
//            double maxDis = maxV.getValue() - startV.getValue();
//            if(maxDis > maxV.getValue() - endV.getValue())
//            {
//               maxDis = maxV.getValue() - endV.getValue();
//               maxDis = maxDis / endV.getValue();
//            } else {
//               maxDis = maxDis / startV.getValue();
//            }
//            double minDis = startV.getValue() - minV.getValue();
//            if(minDis > endV.getValue() -minV.getValue())
//            {
//               minDis = endV.getValue() -minV.getValue();
//               minDis = minDis / endV.getValue();
//            } else {
//               minDis = minDis / startV.getValue();
//            }
//            Vector v = new Vector();
//            v.add(minV);
//            v.add(maxV);
//            if((maxDis > minDis) && (maxDis > lb))
//            {
//            	 v.add(maxV);
//            	 v.add(new Double(maxDis));
//               return new  PatternValue(this, l, startIdx, endIdx, v);
//            } else  if((minDis > maxDis) && (minDis > lb))
//            {
//            	 v.add(minV);
//            	 v.add(new Double(minDis));
//               return new  PatternValue(this, l, startIdx, endIdx, v);
//            }
//        }  
//        return null;
//    }
    public PatternValue find(Line l, int sIdx)
    {
          
        for(int startIdx = sIdx; startIdx < l.length() - p; startIdx++)
        {
            int endIdx = startIdx + p;
            Value ApexV  = l.valueAt((startIdx+endIdx)/2);
            Value startV = l.valueAt(startIdx);
            Value endV =  l.valueAt(endIdx);
            Value maxV = null;
            Value minV = null;      
            for(int i = startIdx; i < endIdx; i++)
            {
                 Value v = l.valueAt(i);
       	         if((maxV == null)|| (v.getValue() > maxV.getValue()))
       	         {
                    maxV = v;
       	         }
       	         if((minV == null)|| (v.getValue() < minV.getValue()))
       	         {
       	         	  minV = v;
       	         }
            }
            double sediff = (startV.getValue() - endV.getValue())/startV.getValue();
            //if(sediff > -0.03 && sediff < 0.03)
            {
                double vBase = (startV.getValue() + endV.getValue()) / 2;
                double vAmp = (ApexV.getValue() -  vBase) / vBase;
                if(lb < 0 && vAmp < lb)
                {
                   Vector v = new Vector();
                   v.add(minV);
                   v.add(maxV);
                   v.add(ApexV);
                	 v.add(new Double(vAmp));
                   return new  PatternValue(this, l, startIdx, endIdx, v);
                }else if(lb > 0 && vAmp > lb)
                {
                   Vector v = new Vector();
                   v.add(minV);
                   v.add(maxV);
                   v.add(ApexV);
            	     v.add(new Double(vAmp));
                   return new  PatternValue(this, l, startIdx, endIdx, v);
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
        System.out.println("CurvePattern Value dump:");
        Enumeration e = ptv.getPatternData();
        Value minV = (Value)e.nextElement();
        Value maxV = (Value)e.nextElement();
        Value ApexV = (Value)e.nextElement();
  	    System.out.print("minV:");
  	    minV.dump();
  	    System.out.print("maxV:");
   	    maxV.dump();
 	      System.out.print("Apex:");
  	    ApexV.dump();
  	    System.out.println("Dis1:"+ ((ApexV.getValue() - ptv.getStartValue().getValue()) / ptv.getStartValue().getValue()));
  	    System.out.println("Dis2:"+ ((ApexV.getValue() - ptv.getEndValue().getValue()) / ptv.getEndValue().getValue()));
 	      System.out.println("vAmp:"+e.nextElement());
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Value :"+(culValue(ptv)));
    }
}