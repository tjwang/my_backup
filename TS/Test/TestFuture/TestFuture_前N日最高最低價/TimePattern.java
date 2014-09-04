import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import stock.pattern.*;

public class TimePattern implements Pattern {

    long min_interval;
    public TimePattern(long sec)
    {
    	  min_interval = sec * 1000;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	  
        Value sv = l.valueAt(startIdx);
        if(sv.getDateValue() < 0) return null;
        
        long base_time = sv.getDateTime();
        for (int i=startIdx+1; i < l.length(); i++)
        {
           Value v = l.valueAt(i);
           if(v.getDateTime() -  base_time > min_interval)
           {
               return new  PatternValue(this, l, startIdx, i-1);
           }
        }
        return  new  PatternValue(this, l, startIdx, l.length()-1);
    }
    
    public PatternValue backwardFind(Line l, int startIdx)
    {
    	  
        Value sv = l.valueAt(startIdx);
        if(sv.getDateValue() < 0) return null;
        
        long base_time = sv.getDateTime();
        for (int i=startIdx; i >=0; i--)
        {
           Value v = l.valueAt(i);
           if(base_time - v.getDateTime() > min_interval)
           {
               return new  PatternValue(this, l, i+1, startIdx);
           }
        }
        return  new  PatternValue(this, l, 0, startIdx );
    }

    public double culValue(PatternValue ptv)
    {
          Value ev = ptv.getMotherLine().valueAt(ptv.getEndIdx());
          return ev.getValue();
    }
    
    public void dump(PatternValue ptv)
    {
        //System.out.println("WeekPattern PatternValue dump:");
        //Line l = ptv.getMotherLine();
        //for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
       // {
       //      l.valueAt(i).dump();
       // }
       // System.out.println("Diff :"+(culValue(ptv)));
        ptv._kv.dump();
    }
}