import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.pattern.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FT_Pattern extends MarkPattern {

    Line _kl;
    FT_Pattern(Line ml,Line kl)
    {
        super(ml);
        _kl = kl;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	 int i = startIdx;
       PatternValue ptv = find(l, startIdx, 0, true);
 
 // write customize code bellow
 /*      
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
    */   
       return ptv;
    }
    
 // write customize code bellow
    public double culValue(PatternValue ptv)
    {
         if(ptv.getEndIdx() <= ptv.getStartIdx()) return 0;
          Value sv = ptv.getMotherLine().valueAt(ptv.getStartIdx()+1);
          Value ev = ptv.getMotherLine().valueAt(ptv.getEndIdx());
          return  ev.getValue() - sv.getValue();
    }
    
}
