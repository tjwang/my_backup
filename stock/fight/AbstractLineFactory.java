package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public abstract class AbstractLineFactory implements LineFactory
{
    abstract public KLine  culKLine();
    abstract public MALine culMALine(int s) ;
    abstract public KDLine culKDLine(int k, int sk, int sd);
    abstract public SimpleLine culSimpleLine();
    
    public Line genTimeLine(int seconds)
    {
    	 int msec = seconds * 1000;
       SimpleLine tl = new SimpleLine();
       KLine  kl = culKLine();
       
       if( kl.getDomain() !=  Domain.getDomainByName("T")) 
           return null;
       Value v1 = kl.valueAt(0);
       Value v2 = kl.valueAt( kl.length()-1);
       long time1 = (v1.getDateTime() / msec) * msec;   
       long time2 = (v2.getDateTime() / msec +1) * msec;
       for(long i = time1 ; i <= time2 ; i+=msec)
       {
       	  tl.add(new SimpleValue(i,0.0));
       }  
       return tl;
    }
    
    public Line genDomainLine(int samples)
    {
       SimpleLine dl = new SimpleLine();
       SimpleLine  l = culSimpleLine();
       Value v1 = l.valueAt(0);
       Value v2 = l.valueAt( l.length()-1);
       Domain d = v1.getDomain();
       if(d.isCountable())
       {
          long sdv = v1.getDomainValue().getIntValue();   
          long edv = v2.getDomainValue().getIntValue();
          long interval = (edv - sdv) / samples;
          for(long i = sdv ; i <= edv ; i+=interval)
          {
          	  dl.add(new SimpleValue(new DomainValue(d,i),i));
          } 
       } else
       {
          double sdv = v1.getDomainValue().getDoubleValue();   
          double edv = v2.getDomainValue().getDoubleValue();
          double interval = (edv - sdv) / samples;
          System.out.println("sdv:"+sdv+" edv:"+ edv+" inter:"+interval);
          for(double i = sdv ; i <= edv ; i+=interval)
          {
          	  dl.add(new SimpleValue(new DomainValue(d,i),i));
//              System.out.println("last:"+i);
          } 
       }    
       return dl;
    }

    public Line genDomainLineByCount(int count)
    {
       SimpleLine dl = new SimpleLine();
       SimpleLine  l = culSimpleLine();
       Value v1 = l.valueAt(0);
       Value v2 = l.valueAt( l.length()-1);
       Domain d = v1.getDomain();
       if(d.isCountable())
       {
          long sdv = v1.getDomainValue().getIntValue();   
          long edv = v2.getDomainValue().getIntValue();
          for(long i = sdv ; i <= edv ; i+=count)
          {
          	  dl.add(new SimpleValue(new DomainValue(d,i),i));
          } 
       } else
       {
          double sdv = v1.getDomainValue().getDoubleValue();   
          double edv = v2.getDomainValue().getDoubleValue();
          for(double i = sdv ; i <= edv ; i+=count)
          {
          	  dl.add(new SimpleValue(new DomainValue(d,i),i));
//              System.out.println("last:"+i);
          } 
       }    
       return dl;
    }

    static public double getRSV(double[] priceHs, double[] priceLs, double price){
       double[] result = new double[2]; 
       result[0] = priceHs[0];
       for(int i = 0; i < priceHs.length ; i++){
          if (priceHs[i] > result[0]){
             result[0] = priceHs[i];
          }
       }
       result[1] = priceLs[0];
       for(int i = 0; i < priceLs.length ; i++){
          if (priceLs[i] < result[1]){
             result[1] = priceLs[i];
          }
       }
       return ((price - result[1]) / (result[0] - result[1])) * 100; 
    } 
    static public double getAverage(double[] prices){
        double sum = 0;
        for(int i = 0; i < prices.length; i++){
            sum += prices[i];
        }
        return sum /  prices.length ;
    }
}      