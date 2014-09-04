package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class FixedAvgPattern implements Pattern {

    double _dis;
    int _len;
    int _avglen;
    public FixedAvgPattern(double dis, int avgLen, int len)
    {
       _dis = dis;
       _len = len;
       _avglen = avgLen;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
//        System.out.println("PatternValue find(Line l, int startIdx)1");
        for(int i=startIdx; i < l.length()-(_avglen+_len) + 1; i++)
        {
             Value sv = l.valueAt(i);
             double avgvalue = 0;
             for(int j=0; j < _avglen; j++)
             {
                  avgvalue += l.valueAt(i+j).getValue();
             }
             avgvalue = avgvalue / _avglen;
             
             if(_dis > 0)
             {
                for(int j=i+_avglen; j < i+_avglen+_len; j++)
                {
                   Value ev = l.valueAt(j);
                   if((ev.getValue() - avgvalue) / avgvalue > _dis)
                   {
                       return new  PatternValue(this, l, i, j);
                   }
                }
             } else {
                for(int j=i+_avglen; j < i+_avglen+_len; j++)
                {
                   Value ev = l.valueAt(j);
                   if((ev.getValue() - avgvalue)/ avgvalue < _dis)
                   {
                       return new  PatternValue(this, l, i, j);
                   }
                }
             }
        }
//        System.out.println("PatternValue find(Line l, int startIdx)2");
        return null;
    }
    public double culValue(PatternValue ptv)
    {
          double avgvalue = 0;
          avgvalue = avgValue(ptv);
          Value ev = ptv.getMotherLine().valueAt(ptv.getEndIdx());
          return (ev.getValue() - avgvalue);
    
    }
    
    public double avgValue(PatternValue ptv)
    {
          double avgvalue = 0;
          int startIdx = ptv.getStartIdx();
          Line l = ptv.getMotherLine();
          for(int j=startIdx; j < startIdx+_avglen; j++)
          {
               avgvalue += l.valueAt(j).getValue();
          }
          avgvalue = avgvalue / _avglen;
          return avgvalue;
    
    }
    
    public void dump(PatternValue ptv)
    {
        System.out.println("FixedAvgPattern PatternValue dump:");
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Diff :"+(culValue(ptv)));
        System.out.println("Rate :"+(culValue(ptv)/avgValue(ptv)));
    }
}