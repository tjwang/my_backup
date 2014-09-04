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

public class FTTSta_Pattern extends AbstractSta_Pattern{

    public FTTSta_Pattern(String name)
    {
    	if(name == null || name.length()==0)
    	{
        init(5,name,null);
      } else
      {
         init(5,name,name+".srpt");
      }
    }
    
    public FTTSta_Pattern(int len,String name,String fn_info)
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
        double  max_high = 0;
        double  max_vol = 0;
        double  min_vol = -1;
        double  total = 0;
        int len = 0;
        Vector<NRec> result = new Vector<NRec>();
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
        result.add(new NRec("max_hv",max_high));
        result.add(new NRec("max_vol",max_vol));
        result.add(new NRec("avg_vol",(total/len)));
        result.add(new NRec("min_vol",min_vol));
        return result;
    }
    
    public Vector<NRec> getNameValue2(PatternValue ptv)
    {
        Vector<NRec> result = new Vector<NRec>();
        Line l = ptv.getMotherLine();
        if( ptv.getStartIdx() < _len * 2) return result;
        int max_hv_i = 0;
        int max_vol_i = 0;
        int avg_vol_i = 0;
        int min_vol_i = 0;
        
//        for(int j = ptv.getStartIdx() - _len * 2; j <= ptv.getStartIdx(); j+= _len)
        for(int j = ptv.getStartIdx()/* - _len * 2*/,k = 0; k < 3/*j <= ptv.getStartIdx()*/; j-= _len,k++)
        {
            double  max_high = 0;
            double  max_vol = 0;
            double  min_vol = -1;
            double  total = 0;
            int len = 0;
            for(int i = 0; i < _len && i+j <= ptv.getEndIdx(); i++)
            {
                 KValue v = (KValue)l.valueAt(j+i);
                 double vol = (v.getClose() - v.getOpen());
                 if(v.getHigh() > max_high) max_high = v.getHigh();
                 total += vol;
                 if(vol > max_vol) max_vol = vol;
                 if(min_vol < 0 || min_vol > vol) min_vol = vol;
                 len++;
            }
           max_hv_i = (max_hv_i*3)+(FConverter.getFvofName("max_hv",max_high)+1);
           max_vol_i = (max_vol_i*3)+(FConverter.getFvofName("max_vol",max_vol)+1);
           avg_vol_i = (avg_vol_i*3)+(FConverter.getFvofName("avg_vol",(total/len))+1);
           min_vol_i = (min_vol_i*3)+(FConverter.getFvofName("min_vol",min_vol)+1);
        }
        result.add(new NRec("max_hv",(double)max_hv_i));
        result.add(new NRec("max_vol",(double)max_vol_i));
        result.add(new NRec("avg_vol",(double)avg_vol_i));
        result.add(new NRec("min_vol",(double)min_vol_i));
        return result;    
    }    
    
}
