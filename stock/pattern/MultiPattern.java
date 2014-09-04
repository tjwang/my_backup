package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public class MultiPattern implements Pattern{
	  public static final int OR = 0;
	  public static final int COVER = 1;
	  public static final int XCOVER = 2;
	  public static final int CROSS = 3;
	  public static final int ANY = 4;
	  public static final int XOR = 5;
	  
	  Pattern pattern1 ;
	  Pattern pattern2 ;
	  int option;
    MpCalculator mpc=null;

    public MultiPattern(Pattern ptn1, Pattern ptn2, int op)
    { 
       pattern1 = ptn1;
       pattern2 = ptn2;
       option = op;
    }
    
    public void setCalculator(MpCalculator c)
    {
       mpc = c;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
        switch(option)
        {
        	 case OR:
        	   return find_OR(l, startIdx);
        	 case COVER:
        	   return find_COVER(l, startIdx);
        	 case XCOVER:
        	   return find_XCOVER(l, startIdx);
        	 case CROSS:
        	   return find_CROSS(l, startIdx);
        	 case ANY:
        	   return find_ANY(l, startIdx);
        	 case XOR:
        	   return find_XOR(l, startIdx);
        }
         return null;
      
    }
    
    PatternValue find_OR(Line l, int startIdx)
    {
     	  PatternValue ptv1 = pattern1.find(l,startIdx);
     	  PatternValue ptv2 = pattern2.find(l,startIdx);
     	  int StartIdx;
     	  int EndIdx;
     	  if(ptv1.getStartIdx() > ptv2.getStartIdx())
     	  {
     	     StartIdx = ptv2.getStartIdx();
     	  }else
     	  {
     	     StartIdx = ptv1.getStartIdx();
     	  }
     	  if(ptv1.getEndIdx() > ptv2.getEndIdx())
     	  {
     	     EndIdx = ptv1.getEndIdx();
     	  }else
     	  {
     	     EndIdx = ptv2.getEndIdx();
     	  }
    	  return new  PatternValue(this, l, startIdx, startIdx);
    }
    
    PatternValue find_COVER(Line l, int startIdx)
    {
     	  int startIdx1 = startIdx;
     	  int startIdx2 = startIdx;
     	  while ((startIdx1 >=0 ) && (startIdx2 >= 0))
     	  {
     	     PatternValue ptv1 = pattern1.find(l,startIdx1);
     	     PatternValue ptv2 = pattern2.find(l,startIdx2);
     	     if ((ptv1.getStartIdx() < ptv2.getStartIdx()) && (ptv1.getEndIdx() > ptv2.getEndIdx()))
     	     {
     	       return ptv1;
     	     }
     	     if ((ptv2.getStartIdx() < ptv1.getStartIdx()) && (ptv2.getEndIdx() > ptv1.getEndIdx()))
     	     {
     	       return ptv2;
     	     }
     	     if (ptv1.getStartIdx() < ptv2.getStartIdx())
     	     {

     	     } else {
     	    
     	     }
     	  
     	  }
    	  return null;
    }
        
    PatternValue find_XCOVER(Line l, int startIdx)
    {
     	  PatternValue ptv1 = pattern1.find(l,startIdx);
     	  PatternValue ptv2 = pattern2.find(l,startIdx);
    	  return null;
    }
        
    PatternValue find_CROSS(Line l, int startIdx)
    {
        PatternValue ptv1 = pattern1.find(l,startIdx);
     	  PatternValue ptv2 = pattern2.find(l,startIdx);
    	  return null;
    }
        
    PatternValue find_ANY(Line l, int startIdx)
    {
     	  PatternValue ptv1 = pattern1.find(l,startIdx);
     	  PatternValue ptv2 = pattern2.find(l,startIdx);
     	  if(ptv1.getStartIdx() > ptv2.getStartIdx())
     	  {
     	      return ptv2;
     	  }else
     	  {
     	      return ptv1;
     	  }
    }
        
    public PatternValue find_XOR(Line l, int startIdx)
    {
     	  PatternValue ptv1 = pattern1.find(l,startIdx);
     	  PatternValue ptv2 = pattern2.find(l,startIdx);
    	  return null;

    } 
       
    public double culValue(PatternValue ptv)
    {
        if(mpc != null)
        {
        	 Enumeration e = split(ptv);
           PatternValue ptv1 = null;
           PatternValue ptv2 = null;;
        	 if(e.hasMoreElements()) 
        	     ptv1 = (PatternValue)e.nextElement();
        	 if(e.hasMoreElements()) 
        	     ptv2 = (PatternValue)e.nextElement();
           return mpc.cul(ptv1,ptv2);
        }
        return 0;
    }
    
    public Enumeration split(PatternValue ptv)
    {
        switch(option)
        {
        	 case OR:
        	   return split_OR(ptv);
        	 case COVER:
        	   return split_COVER(ptv);
        	 case XCOVER:
        	   return split_XCOVER(ptv);
        	 case CROSS:
        	   return split_CROSS(ptv);
        	 case ANY:
        	   return split_ANY(ptv);
        	 case XOR:
        	   return split_XOR(ptv);
        }
         return null;
    
    }
    Enumeration split_OR(PatternValue ptv)
    {
       PatternValue ptv1;
       PatternValue ptv2;
         return null;
    }
    Enumeration split_COVER(PatternValue ptv)
    {
       PatternValue ptv1;
       PatternValue ptv2;
          return null;
   }
    Enumeration split_XCOVER(PatternValue ptv)
    {
       PatternValue ptv1;
       PatternValue ptv2;
         return null;
    }
    Enumeration split_CROSS(PatternValue ptv)
    {
       PatternValue ptv1;
       PatternValue ptv2;
         return null;
    }
    Enumeration split_ANY(PatternValue ptv)
    {
       PatternValue ptv1;
       PatternValue ptv2;
         return null;
    }
    Enumeration split_XOR(PatternValue ptv)
    {
       PatternValue ptv1;
       PatternValue ptv2;
         return null;
    }
    public void dump(PatternValue ptv)
    {
    
    }
}