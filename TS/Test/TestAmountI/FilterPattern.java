import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import stock.pattern.*;

public class FilterPattern implements Pattern  {
    
    int _op = -1;
    double _threshold = 0;
    public FilterPattern(double th, int op)
    {
       _op = op;
       _threshold = th;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	  
        for (int i=startIdx; i < l.length(); i++)
        {
           Value v = l.valueAt(i);
           
           if( v instanceof PatternValue)
           {
              PatternValue ptv = (PatternValue)v;
              if(_op > 0)
              {
                if(ptv.getValue() > _threshold) 
                {
                    return new  PatternValue(this, l, i, i);
                }
              } else
              {
                if(ptv.getValue() < _threshold) 
                {
                    return new  PatternValue(this, l, i, i);
                }
              }
           }
        }
        return  null;
    }
       public double culValue(PatternValue ptv)
    {
          return 1;
    }
    public void dump(PatternValue ptv)
    {
        System.out.println("FilterPattern dump:");
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
    }    
}