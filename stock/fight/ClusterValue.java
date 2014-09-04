package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ClusterValue extends AbstractValue
{
 
    Vector<Value> valuePool ;
    int date_value = -1;
    int time_value = -1;
    double _sum = 0;
    double _max = 0;
    double _min = 0;
    
    ClusterValue(Enumeration e, DomainValue dv) 
    {
       _dv = dv;
       if(dv.getDomain() == Domain.getDomainByName("T"))
       {
          _datetime = dv.getIntValue();
          Date d = new Date(_datetime);
          date_value =  (d.getYear()+1900) * 10000 + (d.getMonth()+1) * 100 + d.getDate();
          time_value = d.getHours()*10000+d.getMinutes()*100+d.getSeconds();
       } 
       valuePool = new Vector<Value>();
       while(e.hasMoreElements())
       {
       	  Value v = (Value) e.nextElement();
       	  _sum += v.getValue();
       	  valuePool.add(v); 
       }
        
    }
   
    public int getSize(){
        return valuePool.size();
    }
   
    public int getDateValue()
    {
        return date_value;
    }
    
    public int getTimeValue()
    {
       return time_value;
    }
  
    public  void dump(){
   	 System.out.println("ClusterValue:---");
   	 Enumeration e = valuePool.elements();
   	 while(e.hasMoreElements())
   	 {
   	    Value vv= (Value) e.nextElement();
   	    vv.dump();
   	 }
   }
    
   public void add(Value v)
   {
       valuePool.add(v);
   }
   public Enumeration  elements()
   {
        return valuePool.elements();
   }
 
   public double getValue(){
        return  getLastValue();  	  
   }
 
   public double getFirstValue(){
        return valuePool.elementAt(0).getValue();  	  
   }
 
   public double getLastValue(){
        return valuePool.elementAt(valuePool.size()-1).getValue();    
   }
   
   public double getAvgValue(){
        return _sum / valuePool.size();  	  
   }
   
   public double getSumValue(){
        return _sum;  	  
   }
   
}