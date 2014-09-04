package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class  ImageValue extends SimpleValue implements Value{
  
  Value _i;
  Vector valuePool;
  public ImageValue(DomainValue dv, double v, Value iv)
  {
      super(dv,v);
	  valuePool = new Vector();
	  valuePool.add(iv);
      _i = iv;
  }
  
  public ImageValue(long timevalue, double v, Value iv)
  {
      super(timevalue,v);
      _i = iv;
  }

  public  ImageValue(int date_v, int time_v, double v,Value iv)
  {
      super(date_v, time_v, v);
      _i = iv;
  }
  
  public Value getImage()
  {
      return _i;
  }

  public Enumeration getImages()
  {
      return valuePool.elements();
  }
  public void addValue(Value iv2)
  {
      valuePool.add(iv2);
  }
  public int valueCount()
  {
     return valuePool.size();
  }
  public void dump()
  {
       System.out.println(this.getDomainValue());	   
	   System.out.println("Value List:");
	   Enumeration e = valuePool.elements();
	   while(e.hasMoreElements())
	   {
          ((Value)e.nextElement()).dump();
	   }
	   	
  }
  
}
