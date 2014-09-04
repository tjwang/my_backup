package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class KDValue extends KValue{
 
    public double kValue = 0 ;
    public double dValue = 0 ;
    
    KDValue(double k, double d, DomainValue dv)
    {
       super(dv, k, k, k, k);
       kValue = k;
       dValue = d;
    }

    KDValue(double k, double d, String sname, int datei, int timei){
       super(sname, datei, timei, k);
       kValue = k;
       dValue = d;
    }
    
    KDValue(double k, double d, KValue kv){
       super(kv.snum, kv.date_value, kv.time_value, kv.kopen, 
              kv.khigh, kv.klow, kv.kclose, kv.volume, kv.money);
       _dv = kv._dv;
       kValue = k;
       dValue = d;
    }
    
    public boolean isAbove(){
       return (kValue > dValue);
    }
    
    public double getDiff(){
       return kValue - dValue;
    }
    
    public double getKValue(){
       return kValue;
    }

    public double getDValue(){
       return dValue;
    }
    
    public double getValue(){
       return kValue;    	  
    }
    
    public void dump()
    {
       System.out.print("  KD Dump: \n  private:");
	     super.dump();
       System.out.println("  KValue:"+kValue+" DValue:"+ dValue+")");
    
    }
}