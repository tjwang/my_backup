package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class GeneralBuyPattern implements Pattern{

    Line slow_l;
    Line fast_l;
    Line slow_sl;
    Line fast_sl;
    PatternValue last_value ;
    int c_loss;
    int c_interval;
    public GeneralBuyPattern( Line fb, Line sb, Line fs, Line ss)
    { 
       slow_l = sb;
       fast_l = fb;
       fast_sl = fs;
       slow_sl = ss;
       last_value = null;
       c_loss = 0;
       c_interval = 0;
    }
    
    public void setManualCut(int interval, int loss)
    {
         c_loss = loss;
         c_interval = interval;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	   int i = 0;
    	   int state = 0;
    	   if(last_value == null)
    	   {
    	      for(i=startIdx; i<l.length(); i++)
    	      {
    	          Value v = l.valueAt(i);
    	          DomainValue dv = v.getDomainValue();
    	          Value sv = slow_l.valueAt(dv);
    	          Value fv = fast_l.valueAt(dv);
    	          if(sv != null && fv != null)
    	          {
    	              if(sv.getValue() < fv.getValue())
    	              {
    	                 if(state == -1) {
    	              	//   if(i < l.length()-1) i++;
    	              	   PatternValue pv = new PatternValue(this,l,startIdx,i);
    	              	   last_value = pv;
    	              	   return  pv;
    	                 }
    	                 state = 1;
    	              }
    	           
    	              if(sv.getValue() > fv.getValue())
    	              {
    	                 state = -1;
    	              }
    	          }
    	       }
    	   } else
    	   {
    	       float buyp = (float)last_value.getEndValue().getValue();
    	       Value prev_v = null;
    	       for(i=startIdx; i<l.length(); i++)
    	       {
    	          Value v = l.valueAt(i);
    	          if(prev_v != null && prev_v.getDateValue()!= v.getDateValue())
    	          {
    	              PatternValue pv = new PatternValue(this,l,startIdx,i-1);
    	              last_value = null;
    	              return  pv;
    	          }
    	          
    	          if(c_loss != 0)
    	          {
    	              if(v.getValue() - buyp < c_loss) 
    	              {
    	                  if(i-startIdx > c_interval)
    	                  {
    	              	     PatternValue pv = new PatternValue(this,l,startIdx,i);
    	              	     last_value = null;
    	              	     return  pv;
    	                  }
    	              }
    	          }
    	          DomainValue dv = v.getDomainValue();
    	          Value sv = slow_sl.valueAt(dv);
    	          Value fv = fast_sl.valueAt(dv);
    	          if(sv != null && fv != null)
    	          {
    	              if(sv.getValue() > fv.getValue())
    	              {
    	                 if(state == -1) {
    	              	   PatternValue pv = new PatternValue(this,l,startIdx,i);
    	              	   last_value = null;
    	              	   return  pv;
    	                 }
    	                 state = 1;
    	              }
    	           
    	              if(sv.getValue() < fv.getValue())
    	              {
    	                 state = -1;
    	              }
    	          }
    	          prev_v = v;
    	       }
    	       
    	   }
         return null;
    }
    
    public double culValue(PatternValue ptv)
    {
        return ptv.getEndValue().getValue();
    }
    
    public void dump(PatternValue ptv)
    {
       ptv.getEndValue().dump();
    }
}