package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class FixedDistance2Pattern implements Pattern {

    double _dis;
    int _len;
    
    public FixedDistance2Pattern(double dis, int maxlen)
    {
       _dis = dis;
       _len = maxlen;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
        for(int i=startIdx; i < l.length()-_len + 1; i++)
        {
             Value sv = l.valueAt(i);
             for(int j=i+1; j < i+_len; j++)
             {
                Value ev = l.valueAt(j);
                if(_dis > 0)
                {
                    if((ev.getValue() - sv.getValue()) / sv.getValue()> _dis)
                    {
                        return new  PatternValue(this, l, i, j);
                    }
                } else {
                   if((ev.getValue() - sv.getValue())/sv.getValue() < _dis)
                   {
                       return new PatternValue(this, l, i, j);
                   }
                }
             }
         }
        return null;
    }

    public double culValue(PatternValue ptv)
    {
          Value sv = ptv.getMotherLine().valueAt(ptv.getStartIdx());
          Value ev = ptv.getMotherLine().valueAt(ptv.getEndIdx());
          return (ev.getValue() - sv.getValue());
    
    }
    
    public void dump(PatternValue ptv)
    {
        System.out.println("FixedDistance2 PatternValue dump:");
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Diff :"+(culValue(ptv)));
        System.out.println("Rate :"+(culValue(ptv)/ptv.getMotherLine().valueAt(ptv.getStartIdx()).getValue()));
    }
}