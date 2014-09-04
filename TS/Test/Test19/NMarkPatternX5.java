import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import stock.pattern.*;

public class NMarkPatternX5 extends MarkPattern {

    Line _kl;
    Line _ml3;
    Line _ml8;
    NMarkPatternX5(Line ml,Line kl)
    {
        super(ml);
        _kl = kl;
        _ml3 = kl.avg(3);
        _ml8 = kl.avg(8);
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	 int i = startIdx;
       PatternValue ptv = find(l, startIdx, 0, true);
       int rSI = -1;
       int rEI = -1;
       if(ptv == null) return null;
       for(i=ptv.getStartIdx();i<l.length();i++)
       {
           DomainValue dv = ptv.getMotherLine().valueAt(i).getDomainValue();
           Value mv3 = _ml3.valueAt(dv);   
           Value mv8 = _ml8.valueAt(dv);   
           if(rSI == -1)
           {
              if(mv3.getValue() > mv8.getValue()) 
                 rSI = i;
           }else
           {
              if(mv3.getValue() < mv8.getValue())
              {
                 rEI = i;
                 return  new  PatternValue(this, l, rSI, rEI);
              } 
           }
       }
       if(rSI == -1)
       {
       	  System.out.println("xxx2");
          return ptv;
       }else
       {
       	  System.out.println("xxx1");
          return  new  PatternValue(this, l, rSI, l.length()-1);
       }
    }
    
    public double culValue(PatternValue ptv)
    {
         if(ptv.getEndIdx() <= ptv.getStartIdx()) return 0;
          Value sv = ptv.getMotherLine().valueAt(ptv.getStartIdx()+1);
          Value ev = ptv.getMotherLine().valueAt(ptv.getEndIdx());
          return  ev.getValue() - sv.getValue();
    }
    
}