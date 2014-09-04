package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class GroupValue extends AbstractValue {
	Value mainValue;
	GroupCalculator gvc;
	
	Vector valuePool;
 	GroupValue (Value mv, GroupCalculator gvc1)
	{
		 mainValue = mv;
		 valuePool = new Vector();
		 gvc = gvc1;
	}
	
  public int getDateValue()
  {
      return mainValue.getDateValue();
  }
  
  public int getTimeValue()
  {
     return mainValue.getTimeValue();
  }
  
  public double getValue()
  {
      if(gvc == null)
      {
         return mainValue.getValue();
      }
      return gvc.cul(this);
  }
  
  public void dump()
  {
     System.out.println("mainValue Dump:");
     mainValue.dump();
     Enumeration e = valuePool.elements();
     System.out.println("subValue Dump:");
     while(e.hasMoreElements())
     {
         Value v = (Value)e.nextElement();
         v.dump();        
     }
  }
  
  void addValue(Value v)
  {
     valuePool.add(v);
  }
  
  Enumeration elements()
  {
     return valuePool.elements();
  }
  
  public DomainValue getDomainValue()//  throws Exception
  {
  	 return mainValue.getDomainValue();
  }
  
  public Value getMainValue()
  {
    return mainValue;
  }
}