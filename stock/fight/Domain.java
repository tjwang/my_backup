package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Domain {
	static private Hashtable _nameHt = new Hashtable();
	static private Hashtable _lineHt = new Hashtable();
	private String _dnName;
	private double _upBound=0;
	private double _lowBound=0;
	private double _eplon = 0;
	
	private Domain(String name,Line l)
	{
       _dnName = name;
       if( l != null)
       {
            if(l instanceof KLine)
            {
       	       Enumeration<KValue> e = l.elements();
       	       TreeSet vts = new TreeSet();
       	       
               if(!e.hasMoreElements()) return;
               KValue v = e.nextElement();
               double prevHV = v.getHigh();
               double prevLV = v.getLow();
               _upBound = prevHV;
               _lowBound = prevLV;
               vts.add(new Double(prevHV));
               vts.add(new Double(prevLV));
               
               while(e.hasMoreElements())
               {
                  v = e.nextElement();
                  double curHV =  v.getHigh();
                  double curLV =  v.getLow();
                  if(curLV < _lowBound) _lowBound = curLV;
                  if(curHV > _upBound) _upBound = curHV;
                  vts.add(new Double(curHV));
                  vts.add(new Double(curLV));
               }
               Iterator<Double> i = vts.iterator();
               if(!i.hasNext()) return;
//               Double d = ;
               prevHV =i.next().doubleValue();
               while(i.hasNext())
               {
                   double curV = i.next().doubleValue();
                   if(_eplon == 0 || _eplon > (curV-prevHV))
                   {
                       _eplon = curV-prevHV;
                   }
                   prevHV = curV;
               }
               _eplon = _eplon / 4;
               while((_upBound - _lowBound) / _eplon > 1000000000000000000.0)
               {
                   _eplon *=2;
               }
            
            } else
            {
       	       Enumeration<Value> e = l.elements();
       	       TreeSet vts = new TreeSet();
       	       
               if(!e.hasMoreElements()) return;
               Value v = e.nextElement();
               double prevV = v.getValue();
               _upBound = prevV;
               _lowBound = prevV;
               vts.add(new Double(prevV));
               while(e.hasMoreElements())
               {
                  v = e.nextElement();
                  double curV =  v.getValue();
                  if(curV < _lowBound) _lowBound = curV;
                  if(curV > _upBound) _upBound = curV;
                  vts.add(new Double(curV));
               }
               Iterator<Double> i = vts.iterator();
               if(!i.hasNext()) return;
//               Double d = ;
               prevV =i.next().doubleValue();
               while(i.hasNext())
               {
                   double curV = i.next().doubleValue();
                   if(_eplon == 0 || _eplon > (curV-prevV))
                   {
                       _eplon = curV-prevV;
                   }
                   prevV = curV;
               }
               _eplon = _eplon / 4;
               while((_upBound - _lowBound) / _eplon > 1000000000000000000.0)
               {
                   _eplon *=2;
               }
           }
       }	    
	}
	
  public double getUpBound()
  {
      return _upBound;
  }
  
  public double getLowBound()
  {
      return _lowBound;
  }
  
  public boolean isInDomain(double d)
  {
      return ((d >= _lowBound) && (d <= _upBound));
  }
  
  public boolean isCountable()
  {
      return (_eplon == 0);
  }
  
  public String getName()
  {
      return _dnName;
  }

  public double getEplon()
  {
      return _eplon;
  }
  
  static public Domain  getDomainByName(String name)
  {
        Domain d = (Domain)_nameHt.get(name);
        if ((d == null) && (name.equals("T") || name.equals("P") || name.equals("N")))
        {
            d = new Domain(name, null);
            if(name.equals("P"))
            {
               d._upBound = 100;          
               d._lowBound = 0;          
               d._eplon = 0.0001;          
            } else if (name.equals("N"))
            {
               d._upBound = Float.POSITIVE_INFINITY;          
               d._lowBound = 0;          
               d._eplon = 0;          
            } else
            {
                d._upBound = Float.POSITIVE_INFINITY;          
                d._lowBound = 0;          
                d._eplon = 0;          
            }
            _nameHt.put(name, d);
        }
        return d;
  }
  
  static public Domain  getDomainByLine(Line l)
  {
        Domain d = (Domain) _lineHt.get(l);
        if(d == null)
        {
            d = new Domain("DomainBy"+l.toString(),l);
            _lineHt.put(l,d);
        }
        return d;
  }
  
}