import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import stock.pattern.*;

public class HPricePattern implements Pattern {

    int hprice;
    int maxLen;
    int cutoff;
    int gotoff;
    public HPricePattern(int hp,int coff, int goff, int mLen)
    {
         hprice = hp;
         cutoff = coff;
         gotoff = goff;
         maxLen = mLen;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
        int hIdx = -1 ; 
        int oIdx = l.length()-1;
        for(int i=startIdx; i < l.length()-1; i++)
        {
           KValue v = (KValue)l.valueAt(i);
           KValue v2 = (KValue)l.valueAt(i+1);
           if(v.getLow() < hprice && v2.getHigh() > hprice)
           {
           	   hIdx = i+1;
           	   break;
           }
        }
        if(hIdx < 0 ) return null;
        for(int i=hIdx; i<l.length() && i < hIdx+maxLen; i++)
        {
            KValue v = (KValue)l.valueAt(i);
            /*
            if(v.getHigh() - hprice > cutoff)
            {
            	  oIdx =  i;
            	  break;
            }
            if(hprice - v.getLow() > gotoff)
            {
            	  oIdx =  i;
            	  break;
            }
            */
            if(v.getHigh() - hprice > gotoff)
            {
            	  oIdx =  i;
            	  break;
            }
            if(hprice - v.getLow() > cutoff)
            {
            	  oIdx =  i;
            	  break;
            }
        }
        return  new  PatternValue(this, l, hIdx, oIdx);
    }
    

    public double culValue(PatternValue ptv)
    {
          KValue v = (KValue)ptv.getMotherLine().valueAt(ptv.getEndIdx());
     /*
          if(v.getHigh() - hprice > cutoff) return hprice - v.getHigh();
          if(hprice - v.getLow() > gotoff) return hprice - v.getLow();
          return hprice - v.getValue();
      */
          if(hprice - v.getLow() > cutoff) return v.getLow() - hprice;
          if(v.getHigh() - hprice > gotoff) return  v.getHigh() - hprice; 
          return v.getValue() - hprice;
    }
    
    public void dump(PatternValue ptv)
    {
        //System.out.println("WeekPattern PatternValue dump:");
        Line l = ptv.getMotherLine();
        System.out.println("----"+hprice+":"+culValue(ptv)+"--------------------------------------");
        l.valueAt(ptv.getStartIdx()).dump();
        l.valueAt(ptv.getEndIdx()).dump();
        ptv._kv.dump();
        System.out.println("=================================================================");
    }
}