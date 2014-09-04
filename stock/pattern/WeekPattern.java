package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class WeekPattern implements Pattern {

    public WeekPattern()
    {
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	  
        Value sv = l.valueAt(startIdx);
        if(sv.getDateValue() < 0) return null;
        
        int weekDay = (new Date(sv.getDateTime())).getDay();
        for (int i=startIdx+1; i < l.length(); i++)
        {
           Value v = l.valueAt(i);
           if((new Date(v.getDateTime())).getDay()  <= weekDay)
           {
               return new  PatternValue(this, l, startIdx, i-1);
           }
        }
        return  new  PatternValue(this, l, startIdx, l.length()-1);
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