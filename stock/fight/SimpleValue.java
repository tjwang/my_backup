package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class SimpleValue extends AbstractValue implements Value{
  
  int date_value;
  int time_value;
  double value;
  
  public SimpleValue(DomainValue dv, double v)
  {
      _dv = dv;
      value = v;
      if(dv.getDomain() == Domain.getDomainByName("T"))
      {
         _datetime = dv.getIntValue();
         Date d = new Date(_datetime);
         date_value =  (d.getYear()+1900) * 10000 + (d.getMonth()+1) * 100 + d.getDate();
         time_value = d.getHours()*10000+d.getMinutes()*100+d.getSeconds();
      } 
  }
  
  public SimpleValue(long timevalue, double v)
  {
     _datetime = timevalue;
     Date d = new Date(_datetime);
     date_value =  (d.getYear()+1900) * 10000 + (d.getMonth()+1) * 100 + d.getDate();
     time_value = d.getHours()*10000+d.getMinutes()*100+d.getSeconds();
     value = v;
  }

  public   SimpleValue(int date_v, int time_v, double v)
  {
     date_value = date_v;
     time_value = time_v;
     value = v;
  }
  
  public int getDateValue()
  {
       return date_value;
  }
  
  public int getTimeValue()
  {
      return time_value;
  }
  
  public double getValue()
  {
      return value;
  }
  
  
}