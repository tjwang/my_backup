import stock.db.*;
import stock.fight.*;
import stock.pattern.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class StockF3 extends SimpleLineFactory{
 
    Vector<PLDayk_Rec> infodata ;
    KLine MLine = null;
    public   StockF3(KLine l) throws Exception
    {
       MLine = l;
    }
 
    public Line culVLine(){
        Line il =  MLine.inverse(0,1);
        Enumeration e = il.elements();
        SimpleLineFactory slf = new SimpleLineFactory(il);
        Line dl = slf.genDomainLineByCount(50);
        Line cl = il.cluster(dl);
        e = cl.elements();
      	dataPool = new Vector<Value>();
        while(e.hasMoreElements())
        {
           ClusterValue cv =(ClusterValue)e.nextElement();
           add(new SimpleValue(cv.getDomainValue(),cv.getSize()));
        }
        return culSimpleLine();
    }
    public ClusterLine culCLine(){
        Line il =  MLine.inverse(0,1);
        Enumeration e = il.elements();
        SimpleLineFactory slf = new SimpleLineFactory(il);
        Line dl = slf.genDomainLineByCount(50);
        ClusterLine cl = il.cluster(dl);
        return cl;
    }

}