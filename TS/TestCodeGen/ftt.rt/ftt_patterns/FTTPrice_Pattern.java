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

public class FTTPrice_Pattern extends AbstractFtt_Pattern {

    public FTTPrice_Pattern(String fn_info)
    {
        this(5,fn_info);
    }
    
    public FTTPrice_Pattern(int len,String fn_info)
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
        double  max_high = -9999;
        double  avg_dif = 0;
        double  max_dif = -1;
        double  min_dif = 0;
        double  dif = 0;
        double  open_p = 0;
        double  close_p = 0;
        double  high_p = -9999;
        double  low_p = 9999999;
        double  total = 0;
        int len = 0;
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             KValue v = (KValue)l.valueAt(i);
             if(i == ptv.getStartIdx())
             {
                open_p = v.getOpen(); 
             }
             if(i == ptv.getEndIdx())
             {
                close_p = v.getClose(); 
             }
            
             double m_dif = (v.getHigh() - v.getLow());
             if(m_dif > max_high) max_high = m_dif;
             
             if(v.getHigh() > high_p) high_p = v.getHigh();
             if(v.getLow() < low_p) low_p = v.getLow();
             total += m_dif;
             len++;
        }
        
        info_ps.println(ptn_name+"|"+ptv.getStartValue().getDateValue()+"|"+ptv.getStartValue().getTimeValue()+"|"+max_high+"|"+(total/len)+
                        "|"+(high_p-open_p)+"|"+(low_p-open_p)+"|"+(close_p-open_p)+"|");
    }
    
}
