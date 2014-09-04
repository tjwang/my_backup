import stock.db.*;
import stock.fight.*;
import stock.pattern.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class StockF2 extends SimpleLineFactory{
 
    Vector<PLDayk_Rec> infodata ;
    String  _snum;
    SimpleLineFactory blf ;
    SimpleLineFactory slf ;
    Line MLine = null;
    public   StockF2(Line l) throws Exception
    {
       MLine = l;
    }
 
    public Line culVLine(int len){
    	Pattern p = new SimpleLenPattern(len);
    	Line l2 = MLine.patternByStep(p);
    	int ni = 0;
    	dataPool = new Vector<Value>();
    	Domain f = Domain.getDomainByName("N");
    	Enumeration e = l2.elements();
    	while(e.hasMoreElements())
    	{
    	   PatternValue pv = (PatternValue)e.nextElement();
    	   ni++;
    	   Value v = pv.getEndValue();
    	   Line l = pv.toLine();
    	   Line[] fl = l.dft(1);
    	   float fv = 0;
    	   for(int i = 0 ; i < 5 ; i++)
    	   {
             Value v1 = fl[0].valueAt(i);
             Value v2 = fl[1].valueAt(i);
             fv += Math.pow(v1.getValue() * v1.getValue() +  v2.getValue() * v2.getValue(),0.5);   	  
    	   }
//    	   System.out.println("ni:"+ni);
     	   add(new SimpleValue(v.getDomainValue(),fv));
    	}
      return culSimpleLine();
    }

}