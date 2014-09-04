package stock.app;
import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class OneFieldBySQL extends SimpleLineFactory{
 
    Vector<F1_Rec> infodata ;
    String  _sqlStr;

    public   OneFieldBySQL()
    {
    }
    
    public   OneFieldBySQL(String sqlStr) throws Exception
    {
    	  loadData(sqlStr);
    }
    
    public void loadData(String sqlStr) throws Exception
    {
        _sqlStr = sqlStr;
        F1_Rec pr= new F1_Rec();
        Enumeration<F1_Rec> e = pr.SelectBySQL(_sqlStr);  
        infodata = new Vector();  
        while(e.hasMoreElements()){
        	 infodata.add(e.nextElement());
        }
        culF1Line();
    }
    
    public SimpleLine culF1Line(){
        Enumeration<F1_Rec> e = infodata.elements();
        dataPool = new Vector<Value>();
        while(e.hasMoreElements()){
     	    F1_Rec pr = e.nextElement();
          add(Integer.parseInt(pr.date), Integer.parseInt(pr.time), Float.parseFloat(pr.f1));
        }
        return culSimpleLine();
    }    
  

}
