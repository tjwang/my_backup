package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class SimpleLenPattern implements Pattern {

    int _len = 0;
    public SimpleLenPattern(int len)
    {
        _len = len;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
        if(startIdx+_len  <= l.length())
        {    
           return new  PatternValue(this, l, startIdx, startIdx+_len-1);
        }
        return null;
    }
    
    public double culValue(PatternValue ptv)
    {
          Value sv = ptv.getMotherLine().valueAt(ptv.getStartIdx());
          return sv.getValue();
    
    }
    
    public void dump(PatternValue ptv)
    {
        System.out.println("Simple PatternValue dump:");
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Value :"+(culValue(ptv)));
    }
}