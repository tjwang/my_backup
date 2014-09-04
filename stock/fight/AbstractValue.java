package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public abstract class AbstractValue implements Value{
  
  static private Domain TimeDomain = Domain.getDomainByName("T");
  long _datetime = -1;
  DomainValue _dv = null;

  public long  getDateTime()
  {
  	  if(_datetime > 0)
  	  {
  	     return _datetime;
  	  }
  	  int d_value = getDateValue();
  	  int t_value = getTimeValue();
  	  if(t_value > 0)
  	  {
         _datetime = (long)(new Date( d_value/10000 - 1900, (d_value%10000)/100 -1, d_value%100,
                                  t_value/10000,
                                  (t_value%10000)/100,
                                  t_value%100) ).getTime();
  	  }else{
         _datetime = (long)(new Date(d_value/10000 - 1900, (d_value%10000)/100 -1, d_value%100)).getTime();
      }
      return _datetime;
  }
  
  abstract public int getDateValue();
  abstract public int getTimeValue();
  abstract public double getValue();
  public void   dump()
  { 
  	if(_dv==null || _dv.getDomain()==TimeDomain)
      System.out.println("default dump: date:"+getDateValue()+" time:"+getTimeValue()+" value:"+getValue());
    else
      System.out.println("default dump: "+ getDomainValue()+" scale: "+(getDomainValue().getScale())+" value:"+getValue());
  }
  
  public String getName()
  {
      return "----";
  }
  
  public Domain getDomain()
  {
      if(_dv != null)
      {
      	 return _dv.getDomain();
      } else
      {
         return TimeDomain;
      }
  }
  
  public DomainValue getDomainValue()//  throws Exception
  {
      if(_dv != null)
      {
      	 return _dv;
      } else
      {
        return new DomainValue(TimeDomain,getDateTime());
      }
  }

}