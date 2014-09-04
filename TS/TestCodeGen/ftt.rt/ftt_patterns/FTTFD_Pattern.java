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

public class FTTFD_Pattern extends AbstractFtt_Pattern {

    public FTTFD_Pattern(String fn_info)
    {
    	  this(5,fn_info);
    }
    public FTTFD_Pattern(int len,String fn_info)
    {
         init(len, fn_info);
    }
    
    public PatternValue find(Line l, int startIdx)
    {
        if(l.length() < startIdx + _len)
        {
           return new  PatternValue(this, l, startIdx, l.length()-1);
        } else
        {
           return new  PatternValue(this, l, startIdx, startIdx+_len-1);
        }
    }
    
    public double culValue(PatternValue ptv)
    {
          Value sv = ptv.getMotherLine().valueAt(ptv.getStartIdx());
          Value ev = ptv.getMotherLine().valueAt(ptv.getEndIdx());
          return (ev.getValue() - sv.getValue());
    
    }
    
    
    public void info_log(PatternValue ptv)
    {
        Line l = ptv.getMotherLine();
        double  max_high = 0;
        double  max_vol = 0;
        double  min_vol = -1;
        double  total = 0;
        int len = 0;
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             KValue v = (KValue)l.valueAt(i);
             double vol = (v.getClose() - v.getOpen());
             if(v.getHigh() > max_high) max_high = v.getHigh();
             total += vol;
             if(vol > max_vol) max_vol = vol;
             if(min_vol < 0 || min_vol > vol) min_vol = vol;
             len++;
        }
        
        info_ps.println("Vol|"+ptv.getStartValue().getDateValue()+"|"+ptv.getStartValue().getTimeValue()+"|"+max_high+"|"+(total/len)+
                        "|"+max_vol+"|"+min_vol+"|" );
    }
    
}
