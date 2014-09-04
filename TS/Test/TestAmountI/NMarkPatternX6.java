import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import stock.pattern.*;

public class NMarkPatternX6 extends MarkPattern {

    NMarkPatternX6(Line ml)
    {
        super(ml);
    }
    
    public PatternValue find(Line l, int startIdx)
    {
        return find(l, startIdx, 0, true);
    }
    
    public double culValue(PatternValue ptv)
    {
        // if(ptv.getEndIdx() - ptv.getStartIdx() < 10) return 0;
         int sIdx = ptv.getStartIdx();
         int eIdx = ptv.getEndIdx();
         Line ml = ptv.getMotherLine();
         double sv = ml.valueAt(sIdx).getValue() ;//+ ml.valueAt(sIdx+1).getValue() + ml.valueAt(sIdx+2).getValue();
         double ev =  ml.valueAt(eIdx).getValue() ;//+  ml.valueAt(eIdx-1).getValue()+ ml.valueAt(eIdx-2).getValue();
         if(ev > sv) return 1;
         return  0;
    }
    
}