import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import stock.pattern.*;

public class NMarkPattern extends MarkPattern {

    NMarkPattern(Line ml)
    {
        super(ml);
    }
    
    public PatternValue find(Line l, int startIdx)
    {
        return find(l, startIdx, 0, true);
    }
    
    public double culValue(PatternValue ptv)
    {
         if(ptv.getEndIdx() <= ptv.getStartIdx()) return 0;
          Value sv = ptv.getMotherLine().valueAt(ptv.getStartIdx()+1);
          Value ev = ptv.getMotherLine().valueAt(ptv.getEndIdx());
          return  ev.getValue() - sv.getValue();
    }
    
}