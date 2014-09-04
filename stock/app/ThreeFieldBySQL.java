package stock.app;
import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ThreeFieldBySQL extends SimpleLineFactory{
 
    Vector<F3_Rec> infodata ;
    protected String  _sqlStr;

    public   ThreeFieldBySQL() 
    {
    
    }
    
    public   ThreeFieldBySQL(String sqlStr) throws Exception
    {
    	  loadData(sqlStr);
    }
    
    public void loadData(String sqlStr) throws Exception
    {
        _sqlStr = sqlStr;
        F3_Rec pr= new F3_Rec();
        Enumeration<F3_Rec> e = pr.SelectBySQL(_sqlStr);  
        infodata = new Vector();  
        while(e.hasMoreElements()){
        	 infodata.add(e.nextElement());
        }
        culF1Line();
    }
    
    public SimpleLine culF1Line() {
        Enumeration<F3_Rec> e = infodata.elements();
        dataPool = new Vector<Value>();
        while(e.hasMoreElements()){
     	    F3_Rec pr = e.nextElement();
          add(Integer.parseInt(pr.date), Integer.parseInt(pr.time), Float.parseFloat(pr.f1));
        }
        return culSimpleLine();
    }    
  
    public SimpleLine culF2Line() {
        Enumeration<F3_Rec> e = infodata.elements();
        dataPool = new Vector<Value>();
        while(e.hasMoreElements()){
     	    F3_Rec pr = e.nextElement();
          add(Integer.parseInt(pr.date), Integer.parseInt(pr.time), Float.parseFloat(pr.f2));
        }
        return culSimpleLine();
    }    
    
    public SimpleLine culF3Line(){
        Enumeration<F3_Rec> e = infodata.elements();
        dataPool = new Vector<Value>();
        while(e.hasMoreElements()){
     	    F3_Rec pr = e.nextElement();
          add(Integer.parseInt(pr.date), Integer.parseInt(pr.time), Float.parseFloat(pr.f3));
        }
        return culSimpleLine();
    }    

}
