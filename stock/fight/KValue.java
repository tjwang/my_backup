package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class KValue extends AbstractValue{
 
//    PLDayk_Rec k = null;
    String snum;
    int    date_value;
    int    time_value;
    protected double kopen;
    protected double khigh;
    protected double klow;
    protected double kclose;
    protected float  volume;
    protected float  money;
    
    KValue(PatternValue pv)
    {
        snum = "pattern";
        Enumeration<Value> e = pv.elements();
        Value v = null;
        volume = 0;
        money = 0;
        if(e.hasMoreElements())
        {
           v = e.nextElement();
           if (v instanceof PatternValue)
           {
           	  KValue kv = ((PatternValue) v)._kv ;
              kopen = kv.getOpen();
              khigh = kv.getHigh();
              klow = kv.getLow();
           } else if (v instanceof KValue)
           {
           	  KValue kv = (KValue) v ;
              kopen = kv.getOpen();
              khigh = kv.getHigh();
              klow = kv.getLow();
           } else {
              kopen = v.getValue();
              khigh = v.getValue();
              klow = v.getValue();
           }
        }
        while(e.hasMoreElements())
        {
           v = e.nextElement();
           if (v instanceof PatternValue)
           {
           	  KValue kv = ((PatternValue) v)._kv ;
           	  if(kv.getHigh() > khigh) khigh = kv.getHigh();
              if(kv.getLow() < klow ) klow = kv.getLow();
           } else if (v instanceof KValue)
           {
           	  KValue kv = (KValue) v ;
           	  if(kv.getHigh() > khigh) khigh = kv.getHigh();
              if(kv.getLow() < klow ) klow = kv.getLow();
           }else 
           {
           	  double d = v.getValue();
              if(d > khigh) khigh = d;
              if(d < klow ) klow = d;
           }
        }
        if (v instanceof PatternValue)
        {
        	  KValue kv = ((PatternValue) v)._kv ;
            kclose = kv.getClose();
        } else if (v instanceof KValue)
        {
           kclose = ((KValue)v).getClose();
        } else
        {
           kclose = v.getValue();
        }
        _dv = v.getDomainValue();
        date_value = pv.getDateValue();
        time_value = pv.getTimeValue();
    }
    
    protected KValue(DomainValue dv, double open, double high, double low, double close)
    {
        snum = "noname";
        kopen = open;
        khigh = high;
        klow = low;
        kclose = close;
        _dv = dv;
        if(dv.getDomain() == Domain.getDomainByName("T"))
        {
           _datetime = dv.getIntValue();
           Date d = new Date(_datetime);
           date_value =  (d.getYear()+1900) * 10000 + (d.getMonth()+1) * 100 + d.getDate();
           time_value = d.getHours()*10000+d.getMinutes()*100+d.getSeconds();
        } 
    }
    
    
    protected KValue(String name, int dateint, int timeint, double open, double high, double low, double close, float v, float m){
           snum = name;
           date_value = dateint;
           time_value = timeint;
           kopen = open;
           khigh = high;
           klow = low;
           kclose = close;
           volume = v;
           money = m;
    }
    protected KValue(String name, int dateint, int timeint, double value){
           snum = name;
           date_value = dateint;
           time_value = timeint;
           kopen = value;
           khigh = value;
           klow = value;
           kclose = value;
           volume = (float)value;
           money = (float)value;
    }
    protected KValue(String name, int dateint, int timeint, double value,  float mv ){
           snum = name;
           date_value = dateint;
           time_value = timeint;
           kopen = value;
           khigh = value;
           klow = value;
           kclose = value;
           volume = mv;
           money = mv;
    }
    protected KValue(String name, int dateint, int timeint, double value, float v, float m){
           snum = name;
           date_value = dateint;
           time_value = timeint;
           kopen = value;
           khigh = value;
           klow = value;
           kclose = value;
           volume = v;
           money = m;
    }
    protected KValue(String name, int dateint, double value){
           snum = name;
           date_value = dateint;
           time_value = 0;
           kopen = value;
           khigh = value;
           klow = value;
           kclose = value;
           volume = (float)value;
           money = (float)value;
    }
    
 public String getName()
 {
    return snum;
 }
 public double getOpen(){
    return kopen;
 }
 
 public double getClose(){
    return kclose;
 }

 public double getHigh(){
    return khigh;
 }

 public double getLow(){
    return klow;
 }


 public double getUp(){
    if(kopen > kclose){
      return khigh - kopen;
    }
    return khigh - kclose;
 }

 public double getDown(){
    if(kopen > kclose){
      return kclose - klow;
    }
    return kopen - klow;
 }

  public double getReal(){
    return kclose - kopen;
  }

 
  public float getVolume()
  {
      return volume;
  }

  public float getMoney()
  {
      return money;
  }
  
  public int getDateValue(){
       return date_value;
  }
  
  public int getTimeValue(){
       return time_value;
  }
  
  public  void dump(){
       System.out.println(" date:"+date_value+" - "+time_value+ " open:"+kopen+ " close:"+kclose+ " high:"+khigh+ " low:"+klow+ " volume:"+volume+" money:"+money);
  }


  public double getValue(){
       return kclose;    	  
  }


}