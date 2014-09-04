import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import stock.pattern.*;

public class LPricePattern implements Pattern {

    int lprice;
    int maxLen;
    int cutoff;
    int gotoff;
    public LPricePattern(int lp,int coff, int goff, int mLen)
    {
         lprice = lp;
         cutoff = coff;
         gotoff = goff;
         maxLen = mLen;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
        int lIdx = -1 ; 
        int oIdx = l.length()-1;
        for(int i=startIdx; i < l.length()-1; i++)
        {
           KValue v = (KValue)l.valueAt(i);
           KValue v2 = (KValue)l.valueAt(i+1);
           if(v.getHigh() > lprice && v2.getLow() < lprice)
           {
           	   lIdx = i+1;
           	   break;
           }
        }
        if(lIdx < 0 ) return null;
        for(int i=lIdx; i<l.length() && i < lIdx+maxLen; i++)
        {
            KValue v = (KValue)l.valueAt(i);
            /*
            if(lprice - v.getLow() > cutoff)
            {
            	  oIdx =  i;
            	  break;
            }
            if(v.getHigh() - lprice > gotoff)
            {
            	  oIdx =  i;
            	  break;
            }
            */
            if(lprice - v.getLow() > gotoff)
            {
            	  oIdx =  i;
            	  break;
            }
            if(v.getHigh() - lprice > cutoff)
            {
            	  oIdx =  i;
            	  break;
            }
        }
        return  new  PatternValue(this, l, lIdx, oIdx);
    }
    

    public double culValue(PatternValue ptv)
    {
          /*
          KValue v = (KValue)ptv.getMotherLine().valueAt(ptv.getEndIdx());
          if(lprice - v.getLow() > cutoff) return v.getLow() - lprice;
          if(v.getHigh() - lprice > gotoff) return v.getHigh() - lprice;
          */
          KValue v = (KValue)ptv.getMotherLine().valueAt(ptv.getEndIdx());
          if(v.getHigh() - lprice > cutoff) return lprice - v.getHigh();
          if(lprice - v.getLow() > gotoff) return lprice - v.getLow();
          return   lprice - v.getValue() ;
    }
    
    public void dump(PatternValue ptv)
    {
        //System.out.println("WeekPattern PatternValue dump:");
        Line l = ptv.getMotherLine();
        System.out.println("----"+lprice+":"+culValue(ptv)+"--------------------------------------");
        l.valueAt(ptv.getStartIdx()).dump();
        l.valueAt(ptv.getEndIdx()).dump();
        ptv._kv.dump();
        System.out.println("=================================================================");
    }
}