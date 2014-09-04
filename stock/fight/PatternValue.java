package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.pattern.*;

public class PatternValue extends AbstractValue {
   int startIdx;
   int endIdx;
   Line motherLine;
   Pattern  ptn;
   Vector patternData;
   public KValue  _kv;
   public PatternValue(Pattern patn, Line mLine, int sIdx, int eIdx)
   {
       ptn = patn;
       motherLine = mLine;
       startIdx = sIdx;
       endIdx = eIdx;
       _kv = new KValue(this);
   }
   
   public PatternValue(Pattern patn, Line mLine, int sIdx, int eIdx, Vector ptndata)
   {
       ptn = patn;
       motherLine = mLine;
       startIdx = sIdx;
       endIdx = eIdx;
       patternData = ptndata;
       _kv = new KValue(this);
   }
   
   public int getStartIdx()
   {
      return startIdx;
   }
   
   public Value getStartValue()
   {
      return motherLine.valueAt(startIdx);
   }
   
   public int getEndIdx()
   {
   	  return endIdx;
   }   
   
   public Value getEndValue()
   {
      return motherLine.valueAt(endIdx);
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
      return ptn.culValue(this);
   }

   public void dump()
   {
       ptn.dump(this);
   }
 
   public DomainValue getDomainValue()//  throws Exception
   {
        return getEndValue().getDomainValue();
   }
   
   public Enumeration getPatternData()
   {
        return patternData.elements();
   }
   
   public double getOpen(){
      return _kv.getOpen();
   }
   
   public double getClose(){
      return _kv.getClose();
   }
   
   public double getHigh(){
      return _kv.getHigh();
   }
   
   public double getLow(){
      return _kv.getLow();
   }

}