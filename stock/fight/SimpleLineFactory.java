package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class SimpleLineFactory extends AbstractLineFactory{

  protected Vector<Value> dataPool;

  public SimpleLineFactory()
  {
      dataPool = new Vector<Value>();
  }

  public SimpleLineFactory(Line l)
  {
      dataPool = new Vector<Value>();
      for(int i=0; i<l.length(); i++)
      {
         dataPool.add(l.valueAt(i));
      }
  }
  
  public void add(Value v)
  {
      dataPool.add(v);
  }
  
  public void add(int date_v, int time_v, double v)
  {
      dataPool.add(new SimpleValue(date_v, time_v, v));
  }
  
  public void add(long timevalue, double v)
  {
      dataPool.add(new SimpleValue(timevalue, v));
  }

  public KDLine culKDLine(int k, int sk, int sd){
     KDLine result = new KDLine();
     Enumeration<Value> e = dataPool.elements();
     double[] priceHs = new double[k];
     double[] priceLs = new double[k];
     double   price = 0;
     int count = 0;
     double preK = 50;
     double preD = 50;
     while(e.hasMoreElements()){
        Value v= e.nextElement();
        priceHs[count%k] = v.getValue();
        priceLs[count%k] = v.getValue();
        price =  v.getValue();
        count ++ ;
        if(count >= k){
           double rsv = getRSV(priceHs, priceLs, price);
           preK = (preK / (sk+1)) * (sk - 1) +  (rsv / (sk+1)) * 2  ;
           preD = (preD / (sd+1)) * (sd - 1) +  (preK / (sd+1)) * 2 ;
           result.add(new KDValue(preK, preD,  v.getDomainValue()));
        }
     }
     return result;
  }    

  public MALine culMALine(int s){
     MALine result = new MALine(s);
     Enumeration<Value> e = dataPool.elements();
     double[] prices = new double[s];
     int count = 0;
     while(e.hasMoreElements()){
        Value v= e.nextElement();
        prices[count%s] = v.getValue();
        count ++ ;
        if(count >= s){
           result.add(new MAValue( v.getDomainValue(), getAverage(prices), s));
        }
     }
     return result;
  }    

  public KLine culKLine(){
     KLine result = new KLine();
     Enumeration<Value> e = dataPool.elements();
     while(e.hasMoreElements()){
         Value v= e.nextElement();
         result.add(new KValue( v.getDomainValue(),v.getValue(),v.getValue(),v.getValue(),v.getValue()));
     }
     return result;
  }    

  public SimpleLine culSimpleLine(){
     SimpleLine result = new SimpleLine();
     Enumeration<Value> e = dataPool.elements();
     while(e.hasMoreElements()){
         result.add(e.nextElement());
     }
     return result;
  }    

}