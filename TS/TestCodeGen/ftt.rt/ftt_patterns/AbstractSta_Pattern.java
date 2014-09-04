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

public abstract class AbstractSta_Pattern implements Pattern {

    int _len;
    Vector<Line> line_pool;
    Vector<AbstractSta_Pattern> line_ptn_pool;
    
    PrintStream info_ps;
    PrintStream info_ps2;
    String _name;
    
    private boolean isOnlyLogInfo ;

    protected void init(int len,String name,String fn_info)
    {
    	 _len = len;
    	 line_pool = new Vector<Line>();
    	 line_ptn_pool = new Vector<AbstractSta_Pattern>();
       isOnlyLogInfo = false;
       _name = name;
       if(fn_info != null)
       {
    	    try{
    	      info_ps = new PrintStream(fn_info);
    	      info_ps2 = new PrintStream(fn_info+"_2");
          }catch(Exception e)
          {
             e.printStackTrace();
          }
      } else
      {
          info_ps = null;
      }
    }
    
    public void addRefLine(Line l, AbstractSta_Pattern aptn)
    {
       line_pool.add(l);
       line_ptn_pool.add(aptn);
    }
    
    public void cleanRefLine()
    {
    	 line_pool = new Vector<Line>();
    	 line_ptn_pool = new Vector<AbstractSta_Pattern>();
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
    
    abstract void dump_cus(PatternValue ptv);
    public void dump(PatternValue ptv)
    {
    	  if(!isOnlyLogInfo)
    	  {
           dump_cus(ptv);
        }
        Vector<NRec> priValue =  getNameValue(ptv);
        Vector<NRec> priValue2 =  getNameValue2(ptv);
        Enumeration<Line>  e = line_pool.elements();
        Line ml = ptv.getMotherLine();
        for(int i=0; i<line_pool.size(); i++)
        {
            Line l = line_pool.elementAt(i);
            AbstractSta_Pattern ptn = line_ptn_pool.elementAt(i);
            int startIndx = l.mappingIdx(l.valueAfter(ml.valueAt(ptv.getEndIdx()).getDomainValue()));
            if(startIndx >= 0 && startIndx < l.length()-2)
            {
                PatternValue nv =  ptn.find(l, startIndx);
                Vector<NRec> secStr = ptn.getNameValue(nv);
 /*
                printNamePair(String.valueOf(ptv.getStartValue().getDateValue()),String.valueOf(ptv.getStartValue().getTimeValue()+"-"+nv.getStartValue().getTimeValue())
                              ,priValue, secStr);
 */
                if(priValue != null)
                {
                    printNamePair(String.valueOf(ptv.getStartValue().getDateValue()),String.valueOf(ptv.getStartValue().getTimeValue())
                              ,priValue, secStr);
                }
                if(priValue2 != null)
                {              
                    printNamePair2(String.valueOf(ptv.getStartValue().getDateValue()),String.valueOf(ptv.getStartValue().getTimeValue())
                              ,priValue2, secStr);
                }
            }
            
        }
         
    }
    public void setOnlyLogInfo(boolean op)
    {
        isOnlyLogInfo = op;
    }
    
    abstract public Vector<NRec> getNameValue(PatternValue ptv);
    abstract public Vector<NRec> getNameValue2(PatternValue ptv);
    
    
    public void printNamePair(String ds,String ts,Vector<NRec> pri,Vector<NRec> sec)
    {
        Enumeration<NRec> e = pri.elements();
        while(e.hasMoreElements())
        {
            NRec pri_nr = e.nextElement();
            Enumeration<NRec> e2 = sec.elements();
            while(e2.hasMoreElements())
            {
               NRec sec_nr = e2.nextElement();
               if(info_ps != null)
               {
                  info_ps.println(ds+"|"+ts+"|"+pri_nr.name+"|"+sec_nr.name+"|"+
                                FConverter.getFvofName(pri_nr.name,pri_nr.value)+"|"+
                                FConverter.getFvofName(sec_nr.name,sec_nr.value)+"|");
                  if(!isOnlyLogInfo)
                  {
                    info_ps.println(ds+"|"+ts+"|"+pri_nr.name+"|"+sec_nr.name+"|"+
                                    pri_nr.value+"|"+sec_nr.value+"|");
                  }              
               } else
               {
                  System.out.println(ds+"|"+ts+"|"+pri_nr.name+"|"+sec_nr.name+"|"+
                                FConverter.getFvofName(pri_nr.name,pri_nr.value)+"|"+
                                FConverter.getFvofName(sec_nr.name,sec_nr.value)+"|");
                  if(!isOnlyLogInfo)
                  {
                 System.out.println(ds+"|"+ts+"|"+pri_nr.name+"|"+sec_nr.name+"|"+
                                   pri_nr.value+"|"+sec_nr.value+"|");
                 }              
               }
            }
        }
    }
    public void printNamePair2(String ds,String ts,Vector<NRec> pri,Vector<NRec> sec)
    {
        Enumeration<NRec> e = pri.elements();
        while(e.hasMoreElements())
        {
            NRec pri_nr = e.nextElement();
            Enumeration<NRec> e2 = sec.elements();
            while(e2.hasMoreElements())
            {
               NRec sec_nr = e2.nextElement();
               if(info_ps != null)
               {
                  info_ps2.println(ds+"|"+ts+"|"+pri_nr.name+"|"+sec_nr.name+"|"+((int)pri_nr.value)+"|"+
                                FConverter.getFvofName(sec_nr.name,sec_nr.value)+"|");
                  if(!isOnlyLogInfo)
                  {
                    info_ps2.println(ds+"|"+ts+"|"+pri_nr.name+"|"+sec_nr.name+"|"+
                                    pri_nr.value+"|"+sec_nr.value+"|");
                  }              
               } else
               {
                   System.out.println(ds+"|"+ts+"|"+pri_nr.name+"|"+sec_nr.name+"|"+((int)pri_nr.value)+"|"+
                                FConverter.getFvofName(sec_nr.name,sec_nr.value)+"|");
                 if(!isOnlyLogInfo)
                  {
                    System.out.println(ds+"|"+ts+"|"+pri_nr.name+"|"+sec_nr.name+"|"+
                                   pri_nr.value+"|"+sec_nr.value+"|");
                  }              
               }
            }
        }
    }
}
