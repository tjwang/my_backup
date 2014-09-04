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

public class FTTPriceSta15M_Pattern extends FTTPriceSta_Pattern{

    public FTTPriceSta15M_Pattern(String name)
    {
    	super(15,name,name+".srpt");
    }

    
    public Vector<NRec> getNameValue(PatternValue ptv)
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
        
        Vector<NRec> result = new Vector<NRec>();
        result.add(new NRec("mhp_15",max_high));
        result.add(new NRec("adif_15",(total/len)));
        result.add(new NRec("mdif_15",(high_p-open_p)));
        result.add(new NRec("ndif_15",(low_p-open_p)));
        result.add(new NRec("dif_15",(close_p-open_p)));
        return result;
    }
    
    public Vector<NRec> getNameValue2(PatternValue ptv)
    {
        Vector<NRec> result = new Vector<NRec>();
        return result;    
    }    
}
