package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class KDataLineFactory extends AbstractLineFactory{
  protected TreeSet dayKdata;

   public KDataLineFactory()
   {
      dayKdata = new TreeSet(new KdataComparator());
   }
  
  public KDataLineFactory(KLine kl)
  {
      dayKdata = new TreeSet(new KdataComparator());
      for(int i=0; i<kl.length(); i++)
      {
         dayKdata.add(kl.valueAt(i));
      }
  }
  
  public KDataLineFactory(PatternLine pl)
  {
      dayKdata = new TreeSet(new KdataComparator());
      for(int i=0; i<pl.length(); i++)
      {
         dayKdata.add(new KValue((PatternValue)pl.valueAt(i)));
      }
  }
  
  protected void add(String name, int dateint, int timeint, double open, double high, double low, double close, float v, float m)
  {
      dayKdata.add(new KValue(name, dateint, timeint, open, high, low, close, v, m));
  }
  
  protected void add(String name, int dateint, int timeint, double value)
  {
      dayKdata.add(new KValue(name, dateint, timeint, value));
  }
  
  protected void add(String name, int dateint, int timeint, double value,  float mv )
  {
      dayKdata.add(new KValue(name, dateint, timeint, value, mv));
  }
  
  protected void add(String name, int dateint, int timeint, double value, float v, float m)
  {
      dayKdata.add(new KValue(name, dateint, timeint, value, v, m));
  }
  
  protected void add(String name, int dateint, double value)
  {
      dayKdata.add(new KValue(name, dateint, value));
  }
  
  protected void add(KValue kv)
  {
      dayKdata.add(kv);
  }

  public KDLine culKDLine(int k, int sk, int sd){
     KDLine result = new KDLine();
     Iterator<KValue> e = dayKdata.iterator();
     double[] priceHs = new double[k];
     double[] priceLs = new double[k];
     double   price = 0;
     int count = 0;
     double preK = 50;
     double preD = 50;
     while(e.hasNext()){
        KValue kv = e.next();
        priceHs[count%k] = kv.getHigh();
        priceLs[count%k] = kv.getLow();
        price =  kv.getValue();
        count ++ ;
        if(count >= k){
           double rsv = getRSV(priceHs, priceLs, price);
           preK = (preK / (sk+1)) * (sk - 1) +  (rsv / (sk+1)) * 2  ;
           preD = (preD / (sd+1)) * (sd - 1) +  (preK / (sd+1)) * 2 ;
           result.add(new KDValue(preK, preD, kv));
        }
     }
     return result;
  }    

  public MALine culMALine(int s){
     MALine result = new MALine(s);
     Iterator<KValue> e = dayKdata.iterator();
     double[] prices = new double[s];
     int count = 0;
     while(e.hasNext()){
        KValue kv= e.next();
        prices[count%s] = kv.getValue();
        count ++ ;
        if(count >= s){
           result.add(new MAValue(kv,getAverage(prices),s));
        }
     }
     return result;
  }    

  public KLine culKLine(){
     KLine result = new KLine();
     Iterator<KValue> e = dayKdata.iterator();
     while(e.hasNext()){
        result.add(e.next());
     }
     return result;
  }    
  
  public SimpleLine culSimpleLine(){
     SimpleLine result = new SimpleLine();
     Iterator<KValue> e = dayKdata.iterator();
     while(e.hasNext()){
        result.add(e.next());
     }
     return result;
  }    
     

static protected class KdataComparator implements Comparator<KValue>
{
	  public KdataComparator()
	  {
	  }
    
    public int compare(KValue o1, KValue o2) 
    {
       if ((o1._dv == null || o1._dv.getDomain() == Domain.getDomainByName("T")) &&
           (o2._dv == null || o2._dv.getDomain() == Domain.getDomainByName("T")))
       {
          if(o1.date_value < o2.date_value) return -1;
          if(o1.date_value > o2.date_value) return 1;
          if(o1.time_value < o2.time_value) return -1;
          if(o1.time_value > o2.time_value) return 1;
          return 0;
       } 
       if( o1.getDomainValue().getDoubleValue() < o2.getDomainValue().getDoubleValue() )
         return -1;
       if( o1.getDomainValue().getDoubleValue() > o2.getDomainValue().getDoubleValue() )
         return 1;
         return 0;
    }
    
    public boolean equals(Object obj) 
    {
       return super.equals(obj);
    }
 
	
} 
}