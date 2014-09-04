package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class CValue extends AbstractValue{
 
  double _sv1;
  double _fv1;
  double _sv2;
  double _fv2;
  int    _mIdx;
  int    date_value;
  int    time_value;
    
  CValue(DomainValue dv, double sv1,double fv1,double sv2,double fv2,int mIndex)
  {
     _dv = dv;
     if(dv.getDomain() == Domain.getDomainByName("T"))
     {
        _datetime = dv.getIntValue();
        Date d = new Date(_datetime);
        date_value =  (d.getYear()+1900) * 10000 + (d.getMonth()+1) * 100 + d.getDate();
        time_value = d.getHours()*10000+d.getMinutes()*100+d.getSeconds();
     } 
     _sv1 = sv1;
     _fv1 = fv1;
     _sv2 = sv2;
     _fv2 = fv2;
     _mIdx = mIndex;
  }
  
  public int getMIdx()
  {
       return _mIdx;
  }
  
//  public KValue toKValue()
//  {
//      return  new KValue("cvalue", date_value, time_value, getValue());
//  }

 
  public int getDateValue(){
       return date_value;
  }
  
  public int getTimeValue(){
       return time_value;
  }
  
  public  void dump(){
       System.out.println(" date:"+date_value+" - "+time_value+ " sv1:"+_sv1+ " sv2:"+_sv2+ " fv1:"+_fv1+ " fv2:"+_fv2 + " mIdx:"+_mIdx);
  }

  public double getValue(){
       return (_sv2*_fv1 - _sv1*_fv2) / (_sv2 + _fv1 - _fv2 - _sv1);  	  
  }


}