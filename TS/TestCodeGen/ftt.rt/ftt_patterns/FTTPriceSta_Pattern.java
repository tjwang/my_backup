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

public class FTTPriceSta_Pattern extends AbstractSta_Pattern{

    public FTTPriceSta_Pattern(String name)
    {
    	if(name == null || name.length()==0)
    	{
        init(5,name,null);
      } else
      {
         init(5,name,name+".srpt");
      }
    }
    
    public FTTPriceSta_Pattern(int len,String name,String fn_info)
    {
        init(len,name,fn_info);
    }
    
    private void dump2(PatternValue ptv)
    {
        System.out.println("FixedDistance PatternValue dump:");
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Diff :"+(culValue(ptv)));
        System.out.println("Rate :"+(culValue(ptv)/ptv.getMotherLine().valueAt(ptv.getStartIdx()).getValue()));
    }

    public void dump_cus(PatternValue ptv)
    {
           System.out.println("dumping pattern value:");
           dump2(ptv);
           Enumeration<Line>  e = line_pool.elements();
           Line ml = ptv.getMotherLine();
           while(e.hasMoreElements())
           {
              Line l = e.nextElement();
              int startIndx = l.mappingIdx(l.valueAfter(ml.valueAt(ptv.getEndIdx()).getDomainValue()));
              if(startIndx < l.length()-2)
              {
                 PatternValue nv =  find(l, startIndx);
                 System.out.println("NextValue at "+l+" PatternValue dump:");
                 dump2(nv);
              }
           }
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
        result.add(new NRec("max_hp",max_high));
        result.add(new NRec("avg_dif",(total/len)));
        result.add(new NRec("max_dif",(high_p-open_p)));
        result.add(new NRec("min_dif",(low_p-open_p)));
        result.add(new NRec("dif",(close_p-open_p)));
        return result;
    }
    
    public Vector<NRec> getNameValue2(PatternValue ptv)
    {
        Line l = ptv.getMotherLine();
        Vector<NRec> result = new Vector<NRec>();
        if( ptv.getStartIdx() < _len * 2) return result;
        int max_hp_i = 0;
        int avg_dif_i = 0;
        int max_dif_i = 0;;
        int min_dif_i = 0;
        int dif_i= 0;
        for(int j = ptv.getStartIdx()/* - _len * 2*/,k = 0; k < 3/*j <= ptv.getStartIdx()*/; j-= _len,k++)
        {
           int len = 0;
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
           for(int i = 0; i < _len && i+j <= ptv.getEndIdx(); i++)
           {   
               KValue v = (KValue)l.valueAt(j+i);
               if(i == 0)
               {
                  open_p = v.getOpen(); 
               }
               if(i == _len-1)
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
           max_hp_i = (max_hp_i*3)+(FConverter.getFvofName("max_hp",max_high)+1);
           avg_dif_i = (avg_dif_i*3)+(FConverter.getFvofName("avg_dif",(total/len))+1);
           max_dif_i = (max_dif_i*3)+(FConverter.getFvofName("max_dif",(high_p-open_p))+1);
           min_dif_i = (min_dif_i*3)+(FConverter.getFvofName("min_dif",(low_p-open_p))+1);
           dif_i= (dif_i*3)+(FConverter.getFvofName("dif",(close_p-open_p))+1);
           
        }
        result.add(new NRec("max_hp",(double)max_hp_i));
        result.add(new NRec("avg_dif",(double)avg_dif_i));
        result.add(new NRec("max_dif",(double)max_dif_i));
        result.add(new NRec("min_dif",(double)min_dif_i));
        result.add(new NRec("dif",(double)dif_i));
        return result;
    }    
    
}
