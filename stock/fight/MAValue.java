package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class MAValue extends KValue{
 
    double value = 0 ;
    int period;
    
    MAValue ( DomainValue dv, double v, int p)
    {
       super(dv, v, v, v, v);
       value = v;
       period = p;
    }
    
    MAValue ( String sname, int datei, int timei, double v, int p)
    {
       super(sname, datei, timei, v);
       value = v;
       period = p;
      
    }

    MAValue( KValue kv, double v,int p){
       super(kv.snum, kv.date_value, kv.time_value, kv.kopen, 
              kv.khigh, kv.klow, kv.kclose, kv.volume, kv.money);
       _dv = kv._dv;
       value = v;
       period = p;
    }
    
    public    boolean isAbove()
    {
       return (value < kclose);
    }
    
    public    boolean isSAbove()
    {
       return ((value < kclose) && (value < klow));
    }
    
    public    boolean isBelow()
    {
       return (value > kclose);
    }
    
    public    boolean isSBelow()
    {
       return ((value > kclose) && (value > khigh));
    }   
    
    public    boolean isCross()
    {
    	if((value < khigh) && (value > klow))
        return true;
      else
        return false;
    }

    public   boolean isSCross()
    {
    	if(((value < kopen) && (value > kclose)) || ((value > kopen) && (value < kclose)))
        return true;
      else
        return false;
    }
    
    public  double getDiff()
    {
      if(kclose > value) return kclose - value;
      return value - kclose ;
    }
   
    public  double getMinDiff(){
      double diff1,diff2 ;
      if(isCross()) return 0;
      diff1 = (khigh > value)?(khigh - value):(value - khigh);   	
      diff2 = (klow > value)?(klow - value):(value - klow);   	
      return (diff1 > diff2)? diff2:diff1;
    }
   
    public    double getMaxDiff(){
       double diff1,diff2 ;
       diff1 = (khigh > value)?(khigh - value):(value - khigh);   	
       diff2 = (klow > value)?(klow - value):(value - klow);   	
       return (diff1 > diff2)? diff1:diff2;
    }
  
    public  void dump()
    {
       System.out.print("  Ma Dump: \n  k:");
	     super.dump();
       System.out.println("  ma:"+value+"("+period+")");
    }
   

    public double getValue()
    {
       return value;
    }   
 
}