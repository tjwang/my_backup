import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class KexValue extends KValue{

    public KexValue(String name, int dateint, int timeint, double open, double high, double low, double close, float v, float m){
       super(name,dateint,timeint,open,high,low,close,v,m);
    }
    
    public void setOpen(double kexopen){
        kopen = kexopen;
    }
 
    public void setClose(double kexclose){
       kclose = kexclose;
    }

    public void setHigh(double kexhigh){
       khigh = kexhigh;
    }

    public void setLow(double kexlow){
       klow = kexlow;
    }

    public void setVolume(float kexvolume)
    {
        volume = kexvolume;
    }

    public void setMoney(float kexmoney)
    {
        money = kexmoney;
    }
}