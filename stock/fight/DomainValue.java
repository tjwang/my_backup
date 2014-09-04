package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class DomainValue {
	private double _doublevalue=0;
	private long _intvalue=0;
	private Domain dm = null;
	
  public 	DomainValue(Domain d, long v) //throws Exception
	{
//		 if(d==null) throw new Exception(" Domain null!!");
		    if(d==null) System.err.println(" Domain null!!");
//		 if(!d.isInDomain((double)v)) throw new Exception(" value "+v+ " not in his domain!!");
		    if(!d.isInDomain((double)v)) System.err.println(" value "+v+ " not in his domain!!");
		 _intvalue = v;
		 _doublevalue = (double)v;
		 dm = d;
	}

  public 	DomainValue(Domain d, int v) //throws Exception
	{
//		 if(d==null) throw new Exception(" Domain null!!");
		    if(d==null) System.err.println(" Domain null!!");
//		 if(!d.isInDomain((double)v)) throw new Exception(" value "+v+ " not in his domain!!");
		    if(!d.isInDomain((double)v)) System.err.println(" value "+v+ " not in his domain!!");
		 _intvalue = v;
		 _doublevalue = (double)v;
		 dm = d;
	}
	
  public 	DomainValue(Domain d, double dv)// throws Exception
	{
//		 if(d==null) throw new Exception(" Domain null!!");
		    if(d==null) System.err.println(" Domain null!!");
//		 if(!d.isInDomain(dv)) throw new Exception(" value "+dv+ " not in his domain!!");
		    if(!d.isInDomain(dv)) System.err.println(" value "+dv+ " not in his domain!!");
		 _intvalue = (int)dv;
		 _doublevalue = dv;
		 dm = d;
	}
	
	public DomainValue add(long diff)
	{
	  return new DomainValue(dm, getScale()+diff);
	}
	public double getDoubleValue()
	{
	    return _doublevalue;
	}
	
	public long getIntValue()
	{
	    return _intvalue;
	}
	
	public long getScale()
	{
		 if(dm.isCountable())
		 {
		    return (long)(_intvalue - dm.getLowBound());
		 } else{
 //      	   System.out.print("_doublevalue " + _doublevalue);
 //      	   System.out.print(" dm.getLowBound() " + dm.getLowBound());
 //      	   System.out.print("dm.getEplon() " + dm.getEplon());
 //      	   System.out.println(" ((_doublevalue - dm.getLowBound()) / dm.getEplon()) " + ((_doublevalue - dm.getLowBound()) / dm.getEplon()));
 	      return  (long)((_doublevalue - dm.getLowBound()) / dm.getEplon());
	   }
	}
	
	public Domain getDomain()
	{
	    return  dm;
	}
	
	public boolean nears(DomainValue dv)
	{
	    if(dv.dm != dm ) return false;
	    
	    double dis = 0;
	    if(dv._doublevalue>_doublevalue)
	       dis =dv._doublevalue-_doublevalue;
      else
         dis =_doublevalue - dv._doublevalue;	  
      return (dis <= dm.getEplon());
	}
	
	public String toString()
	{
	   if(dm == Domain.getDomainByName("T"))
	   {
	      return String.format("%1$tY %1$tm/%1$te %1$tH:%1$tM:%1$tS", new Date((long)_intvalue));
	   } else
	   {
	     if(dm.isCountable())
	     {
	        return String.valueOf(_intvalue);
	     } else
	     {
	        return String.format("%1$.2f",_doublevalue);
	     }
	   }
	}
	
}