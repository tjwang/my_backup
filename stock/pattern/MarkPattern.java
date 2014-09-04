package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class MarkPattern implements Pattern {

    Line  markLine;
    public MarkPattern(Line ml)
    {
    	  markLine = ml;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	/*  
        int i = 0;
        int m_startIdx = startIdx;
        int m_endIdx = startIdx+1;
        for (i=startIdx; i < l.length(); i++)
        {
           Value sv = l.valueAt(i);
           Value mv = markLine.valueAt(sv.getDomainValue());
           if(mv == null) continue;
           if(mv.getValue() <= 0) break;
        }
        for (; i < l.length(); i++)
        {
           Value sv = l.valueAt(i);
           Value mv = markLine.valueAt(sv.getDomainValue());
           if(mv.getValue() > 0) break;
        }
        m_startIdx = i;
        if(m_startIdx == l.length())
        {
           return  null;
        }
        for (; i < l.length(); i++)
        {
           Value sv = l.valueAt(i);
           Value mv = markLine.valueAt(sv.getDomainValue());
           if(mv.getValue() <= 0) break;
        }
        m_endIdx = i - 1;
        return  new  PatternValue(this, l, m_startIdx, m_endIdx);
         */
       return find(l, startIdx, 0, true);
    }
    
    public PatternValue find(Line l, int startIdx, int shift, boolean is_from_end)
    {
    	  
        int i = 0;
        int m_startIdx = startIdx;
        int m_endIdx = startIdx+1;
		
 
		if(startIdx == 0)
		{
			for (i=startIdx; i < l.length(); i++)
			{
			   Value sv = l.valueAt(i);
			   Value mv = markLine.valueAt(sv.getDomainValue());
			   if(mv != null) break;
			}
		}else
		{
           for (i=startIdx; i < l.length(); i++)
           {
              Value sv = l.valueAt(i);
              Value mv = markLine.valueAt(sv.getDomainValue());
              if(mv == null) continue;
              if(mv.getValue() <= 0) break;
           }
		} 
		
        for (; i < l.length(); i++)
        {
           Value sv = l.valueAt(i);
           Value mv = markLine.valueAt(sv.getDomainValue());
           if(mv == null) 
		   { 
		   //    sv.dump();
               continue;
		   }
		   if(mv.getValue() > 0) break;
        }
        m_startIdx = i;
        if(m_startIdx == l.length())
        {
           return  null;
        }
        for (; i < l.length(); i++)
        {
           Value sv = l.valueAt(i);
           Value mv = markLine.valueAt(sv.getDomainValue());
           if(mv == null) 
		       { 
		//          sv.dump();
              continue;
		       }
           if(mv.getValue() <= 0) break;
        }
        m_endIdx = i - 1;
		    if(is_from_end)
	      {
            m_endIdx += shift;
		    } else
		    {
            m_endIdx = m_startIdx+shift;
		    }
		    if(m_endIdx > l.length()-1) m_endIdx= l.length()-1;
        return  new  PatternValue(this, l, m_startIdx, m_endIdx);
    }

    public double culValue(PatternValue ptv)
    {
          Value ev = ptv.getMotherLine().valueAt(ptv.getEndIdx());
          return ev.getValue();
    }
    
    public void dump(PatternValue ptv)
    {
        System.out.println("MarkPattern PatternValue dump:");
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Diff :"+(culValue(ptv)));
    }
}
