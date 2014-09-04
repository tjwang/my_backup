package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class FixedDistancePattern implements Pattern {

    double _dis;
    int _len;
    
    public FixedDistancePattern(double dis, int len)
    {
       _dis = dis;
       _len = len;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
//        System.out.println("PatternValue find(Line l, int startIdx)1");
        for(int i=startIdx; i < l.length()-_len + 1; i++)
        {
             Value sv = l.valueAt(i);
             Value ev = l.valueAt(i+_len-1);
             if(_dis > 0)
             {
                if((ev.getValue() - sv.getValue()) / sv.getValue()> _dis)
                {
                     return new  PatternValue(this, l, i, i+_len-1);
                }
             } else {
                if((ev.getValue() - sv.getValue())/sv.getValue() < _dis)
                {
                    return new PatternValue(this, l, i, i+_len-1);
                }
             }
        }
//        System.out.println("PatternValue find(Line l, int startIdx)2");
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
        System.out.println("FixedDistance PatternValue dump:");
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Diff :"+(culValue(ptv)));
        System.out.println("Rate :"+(culValue(ptv)/ptv.getMotherLine().valueAt(ptv.getStartIdx()).getValue()));
    }
}