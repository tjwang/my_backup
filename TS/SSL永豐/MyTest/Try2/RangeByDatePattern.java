import stock.pattern.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class RangeByDatePattern implements Pattern {

    int _stm =1;
    int _etm =1;
    
    public RangeByDatePattern(int stm, int etm)
    {
     	 _stm = stm;
    	 _etm = etm;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	  
        Value sv = l.valueAt(startIdx);
        if(sv.getDateValue() < 0) return null;
        
        int sIdx = -1;
        for (int i=startIdx; i < l.length(); i++)
        {
           Value v = l.valueAt(i);
           int dt = v.getTimeValue();
           if((dt >= _stm) && (dt <= _etm))
           {
             	if(sIdx < 0)
             	  sIdx = i;
           } else if(sIdx >= 0)
           {
               return new  PatternValue(this, l, sIdx, i-1);
           }
        }
        if(sIdx >= 0)
        {
               return new  PatternValue(this, l, sIdx, l.length()-1);
        }  
        return  null;
    }
    
    public double culValue(PatternValue ptv)
    {
          Value ev = ptv.getMotherLine().valueAt(ptv.getEndIdx());
          return ev.getValue();
    }
    
    public void dump(PatternValue ptv)
    {
        System.out.println("WeekPattern PatternValue dump:");
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Diff :"+(culValue(ptv)));
    }
}