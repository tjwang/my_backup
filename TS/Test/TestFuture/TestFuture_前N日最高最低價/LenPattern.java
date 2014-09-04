import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import stock.pattern.*;

public class LenPattern implements Pattern {

    int _len;
    public LenPattern(int len)
    {
    	  _len = len;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	  
        Value sv = l.valueAt(startIdx);
        if(startIdx+_len > l.length()-1) return null;
        
        return  new  PatternValue(this, l, startIdx, startIdx+_len-1);
    }
    
    public PatternValue backwardFind(Line l, int startIdx)
    {
    	  
        Value sv = l.valueAt(startIdx);
        if(startIdx-_len+1 < 0) return null;
        
        return  new  PatternValue(this, l, startIdx-_len+1, startIdx );
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