package stock.app;
import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.tool.*;

public class  StockInfo5 extends SimpleLineFactory{

 String  _snum;
 Vector<ADayInfo5_Field> dailyData;
 Vector<I5Value>  i5vData;
 Vector<I5Value>[] i5vData_pools;
 int[] i5vData_pools_setup_configs;
 public  StockInfo5(String snum,String dateStr)throws Exception
 {
     _snum = snum;
     PAmountinfo5_Rec par= new PAmountinfo5_Rec();
     dailyData = new Vector<ADayInfo5_Field>();
     i5vData = null;
     Enumeration e = par.SelectBySQL("select * from  PAmountinfo5 where   snum='"+snum+"' and date > '"+dateStr+"'order by date,rp "  );    
     ADayInfo5_Field  mydata = new ADayInfo5_Field();
     while(e.hasMoreElements())
     {
   	    par = (PAmountinfo5_Rec)e.nextElement();
   	    if(!mydata.add(par))
   	    {
   	    	 mydata.toFieldData();
   	    	 dailyData.add(mydata);
   	    	 mydata = new ADayInfo5_Field();
   	    	 mydata.add(par);
   	    }
     }
     mydata.toFieldData();
     dailyData.add(mydata);
  /*   
     i5vData_pools_setup_configs = new int[10];
     i5vData_pools = new Vector<I5Value>[10];
     for(int i=0; i<10; i++)
     {
        i5vData_pools_setup_configs[i] = -1;
        i5vData_pools[i] = null;
     }
     */
 }
 public  StockInfo5(StockInfo5 src)throws Exception
 {
     dailyData = src.dailyData;
 }

 public void dump()
 {
    Enumeration<ADayInfo5_Field> e = dailyData.elements();
    while(e.hasMoreElements())
    {
       ADayInfo5_Field a5f = e.nextElement();
       a5f.dump();
    }
 }
 public static void main(String[] args)throws Exception
 {
    StockInfo5  ski5 = new StockInfo5("2454","20140101");
    ski5.setupI5(3);
    Line l = ski5.getI5Line();
    l.dump();
 }
 
 public void setupI5(int s){
   /* 
     int setup_idx = -1;
     for(int i=0; i<10; i++)
     {
        if(i5vData_pools_setup_configs[i] == s && i5vData_pools[i]!=null)
        {
           i5vData = i5vData_pools[i];
           System.out.println("setupI5 got");
           return;
        }
        if( i5vData_pools[i]==null && setup_idx < 0) setup_idx = i;
     }
   */  
     i5vData = new Vector<I5Value>();
     for(int i=s;i<=dailyData.size();i++)
     {
        i5vData.add(new I5Value(dailyData,i-s,s));
     }
     /*
     if(setup_idx>=0) 
     {
     	 i5vData_pools[setup_idx] = i5vData;
       i5vData_pools_setup_configs[setup_idx]=s;
     }
     */
  }   

 public Line getI5Line()
 {
	   SimpleLine result = new SimpleLine(); 
	   Enumeration<I5Value> e = i5vData.elements();
	   while(e.hasMoreElements())
	   {
        result.add(e.nextElement());
	   }
	   return result;
 } 

 public Line getAvgLine()
 {
	   SimpleLine result = new SimpleLine(); 
	   Enumeration<I5Value> e = i5vData.elements();
	   while(e.hasMoreElements())
	   {
   			I5Value i5v = e.nextElement();
        result.add(new SimpleValue(i5v.getDomainValue(),i5v.get_avg_value()));
	   }
	   return result;
 } 

 public Line getMidLine()
 {
	   SimpleLine result = new SimpleLine(); 
	   Enumeration<I5Value> e = i5vData.elements();
	   while(e.hasMoreElements())
	   {
   			I5Value i5v = e.nextElement();
        result.add(new SimpleValue(i5v.getDomainValue(),i5v.get_mid_value()));
	   }
	   return result;
 } 

 public Line getMCntLine()
 {
	   SimpleLine result = new SimpleLine(); 
	   Enumeration<I5Value> e = i5vData.elements();
	   while(e.hasMoreElements())
	   {
   			I5Value i5v = e.nextElement();
        result.add(new SimpleValue(i5v.getDomainValue(),i5v.get_m_cnt_value()));
	   }
	   return result;
 } 

 public Line getMRaLine()
 {
	   SimpleLine result = new SimpleLine(); 
	   Enumeration<I5Value> e = i5vData.elements();
	   while(e.hasMoreElements())
	   {
   			I5Value i5v = e.nextElement();
        result.add(new SimpleValue(i5v.getDomainValue(),i5v.get_m_ra_value()));
	   }
	   return result;
 } 

 public Line getMaxLine()
 {
	   SimpleLine result = new SimpleLine(); 
	   Enumeration<I5Value> e = i5vData.elements();
	   while(e.hasMoreElements())
	   {
   			I5Value i5v = e.nextElement();
        result.add(new SimpleValue(i5v.getDomainValue(),i5v.get_max_value()));
	   }
	   return result;
 } 

 public Line getMinLine()
 {
	   SimpleLine result = new SimpleLine(); 
	   Enumeration<I5Value> e = i5vData.elements();
	   while(e.hasMoreElements())
	   {
   			I5Value i5v = e.nextElement();
        result.add(new SimpleValue(i5v.getDomainValue(),i5v.get_min_value()));
	   }
	   return result;
 } 

 public Line getVarLine()
 {
	   SimpleLine result = new SimpleLine(); 
	   Enumeration<I5Value> e = i5vData.elements();
	   while(e.hasMoreElements())
	   {
   			I5Value i5v = e.nextElement();
        result.add(new SimpleValue(i5v.getDomainValue(),i5v.get_var_value()));
	   }
	   return result;
 } 


 public Line getVarRaLine()
 {
	   SimpleLine result = new SimpleLine(); 
	   Enumeration<I5Value> e = i5vData.elements();
	   while(e.hasMoreElements())
	   {
   			I5Value i5v = e.nextElement();
        result.add(new SimpleValue(i5v.getDomainValue(),i5v.get_var_value_ra()));
	   }
	   return result;
 } 



}