package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class SimplePattern implements Pattern {

    public SimplePattern()
    {
    }
    
    public PatternValue find(Line l, int startIdx)
    {
          Value sv = l.valueAt(startIdx);
           return new  PatternValue(this, l, startIdx, startIdx);
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