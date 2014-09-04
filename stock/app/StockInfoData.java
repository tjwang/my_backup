package stock.app;
import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class StockInfoData extends SimpleLineFactory{
 
    Vector<Pamountinfo_Rec> infodata ;
    String  _snum;

    public   StockInfoData(String snum) throws Exception
    {
    	   this(snum, null, null);
    }
 
    public  StockInfoData(String snum,String dateStart, String dateEnd)throws Exception
    {
      _snum = snum;
      Pamountinfo_Rec par= new Pamountinfo_Rec();
      Enumeration e = null;
      if((dateStart != null) && (dateEnd != null))
        e = par.SelectBySQL("select * from  pamountinfo where   snum='"+snum+"' and date >='"+dateStart+"' and date <= '"+dateEnd+"'  order by date "  );  
      else if(dateStart != null)
        e = par.SelectBySQL("select * from  pamountinfo where   snum='"+snum+"' and date >='"+dateStart+"' order by date "  );  
      else
        e = par.SelectBySQL("select * from  pamountinfo where   snum='"+snum+"'  order by date "  );  
      infodata = new Vector();  
      while(e.hasMoreElements()){
      	  par = (Pamountinfo_Rec)e.nextElement();
      	  infodata.add(par);
      }
    }

    public SimpleLine culAmtLine()throws Exception {
      Vector v =  new Vector();
      Enumeration<Pamountinfo_Rec> e = infodata.elements();
      while(e.hasMoreElements()){
     	  Pamountinfo_Rec par = e.nextElement();
        add(Integer.parseInt(par.date), 0, Integer.parseInt(par.Open_downAmt)+ Integer.parseInt(par.Close_upAmt));
      }
      dataPool = v;
      return culSimpleLine();
    }    
  
    public SimpleLine culRateLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.rate));
       }
       dataPool = v;
       return culSimpleLine();
    }   
     
    public SimpleLine culAvgLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.Avg));
       }
       dataPool = v;
       return culSimpleLine();
    }   
    
    public SimpleLine culOpen_upAvgLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.Open_upAvg));
       }
       dataPool = v;
       return culSimpleLine();
    } 
      
    public SimpleLine culOpen_downAvgLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.Open_downAvg));
       }
       dataPool = v;
       return culSimpleLine();
    }
       
    public SimpleLine culClose_upAvgLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.Close_upAvg));
       }
       dataPool = v;
       return culSimpleLine();
    }
       
    public SimpleLine culClose_downAvgLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.Close_downAvg));
       }
       dataPool = v;
       return culSimpleLine();
    }
       
    public SimpleLine culOpen_upAmtLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.Open_upAmt));
       }
       dataPool = v;
       return culSimpleLine();
    }
       
    public SimpleLine culOpen_downAmtLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.Open_downAmt));
       }
       dataPool = v;
       return culSimpleLine();
    }
       
    public SimpleLine culClose_upAmtLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.Close_upAmt));
       }
       dataPool = v;
       return culSimpleLine();
    }
       
    public SimpleLine culClose_downAmtLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.Close_downAmt));
       }
       dataPool = v;
       return culSimpleLine();
    }
       
    public SimpleLine culhand_rateLine()throws Exception {
       Vector v =  new Vector();
       Enumeration<Pamountinfo_Rec> e = infodata.elements();
       while(e.hasMoreElements()){
       	  Pamountinfo_Rec par = e.nextElement();
          add(Integer.parseInt(par.date), 0, Float.parseFloat(par.hand_rate));
       }
       dataPool = v;
       return culSimpleLine();
    }   
 

}
