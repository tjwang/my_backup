package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.pattern.*;

public class CurveValue extends AbstractValue {
   int startIdx;
   int endIdx;
   Line motherLine;
   Vector curveData;
   Value maxv;
   Value minv;
   Value sv;
   Value ev;
   int type;
   public CurveValue(Line mLine, int sIdx, int eIdx)
   {
       motherLine = mLine;
       startIdx = sIdx;
       endIdx = eIdx;
       Enumeration<Value> e = elements();
       sv = null;
       ev = null;
       maxv = null;
       minv = null;
       int maxi = 0;
       int mini = 0;
       int l = 0;
       Vector pool = new Vector();
       while(e.hasMoreElements())
       {
          Value v = e.nextElement();
          if(sv == null) sv = v;
          if(maxv == null || maxv.getValue() < v.getValue())
          {
             maxv = v;
             maxi = l;
          }
          if(minv == null || minv.getValue() > v.getValue())
          {
             minv = v;
             mini = l;
          }
          ev = v;
          pool.add(v);
          l++;
       }
       if((maxv != sv) && (maxv != ev) && (minv != sv) && (minv != ev))
       {
           type = 0;
       } else if(maxv == sv || maxv == ev)
       {
           type = 1;
       } else 
       {
           type = -1;      
       }
   }
   
   public Value getStartValue()
   {
      return sv;
   }
   
   public Value getEndValue()
   {
      return ev;
   }

   public Value getApexValue()
   {
      if(type == 1) return minv;
      if(type == -1) return maxv;
      return null;
   }

   public int length()
   {
       return endIdx - startIdx + 1;
   }
   
   public Line getMotherLine()
   {
   	  return motherLine;
   } 
     
   public Line toLine()
   {
      return motherLine.sub(startIdx, endIdx);
   }
   
   public Enumeration elements()
   {
       Vector v = new Vector();
       for(int i=startIdx;i<=endIdx;i++)
       {
          v.add(motherLine.valueAt(i));
       }
       return v.elements();
   }
   
   public int getDateValue()
   {
      return motherLine.valueAt(endIdx).getDateValue();
   }
   
   public int getTimeValue()
   {
      return motherLine.valueAt(endIdx).getTimeValue();
   }
   
   public double getValue()
   {
   	   if(type == 0)
   	   {
   	        return 0;
   	   }
   	   return maxv.getValue() - minv.getValue();
   }
   
   public boolean isCover(CurveValue cv)
   {
      Value csv = cv.getStartValue();
      Value cev = cv.getEndValue();
      if(sv.getDomainValue().getDoubleValue() > cev.getDomainValue().getDoubleValue() ||
         ev.getDomainValue().getDoubleValue() < csv.getDomainValue().getDoubleValue())
         return false;
      return true;
   }
   
   public boolean isContain(CurveValue cv)
   {
      Value csv = cv.getStartValue();
      Value cev = cv.getEndValue();
      if(sv.getDomainValue().getDoubleValue() <= csv.getDomainValue().getDoubleValue() &&
         ev.getDomainValue().getDoubleValue() >= cev.getDomainValue().getDoubleValue())
        return true;
      
      return false;   
   }

   public void dump()
   {
       Enumeration<Value> e = elements();
       System.out.println("Cruve dump:=========");
       while(e.hasMoreElements())
       {
          Value v = e.nextElement();
          v.dump();
       }
   }
 
   public int getType()
   {
       return type;
   }
   
   public DomainValue getDomainValue()//  throws Exception
   {
        return getStartValue().getDomainValue();
   }
   

}