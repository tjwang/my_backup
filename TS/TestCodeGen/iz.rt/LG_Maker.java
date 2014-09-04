import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.pattern.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LG_Maker {
	
static Line _mainL = null;	

public static float getUnit(float price)
{
   if(price >= 1000) return (float)5;
   else if(price >= 500) return (float)1;
   else if(price >= 100) return (float)0.5;
   else if(price >= 50) return (float)0.1;
   else if(price >= 10) return (float)0.05;
   return (float)0.01;
}
public static LineGroup basic(IZ_sFactory fac1, IZ_oFactory fac2)throws Exception
{
     _mainL = fac1.culKLine();
     LineGroup lg = new LineGroup(_mainL);
     lg.addLine(((KLine)_mainL).getVol());
   /*  
     _mainL = fac2.culKLine();
     fac2.loadMData();
     _pmrL = fac2.culKLine();
     if(_pmrL.length()>0)
     {
        _mainL=(KLine)_mainL.sub(_pmrL.valueAt(0).getDomainValue(),_pmrL.valueAt(_pmrL.length()-1).getDomainValue());
     }
     
     lg.addLine(_pmrL);
	   */
	   return lg;
}	

public static Line markLine(LineGroup lg)
{
	  if(_mainL.length()>1)
	  {
	  	 KLine kmainL = (KLine)_mainL;
	     Line vl = kmainL.getVol();
	     Line cl = kmainL.getClose();
	     Line ml = kmainL.getMoney();
	 //    Line ccl = kmainL.constant(0);
	     Line prevl = cl.diff(ml);
	     Line cxl = cl.convert(new ValueConverter(){
	                           public double getConvertValue(Value v)
	                           {
	                              return 	(double)getUnit((float)v.getValue());
	                           }
	                           });

//	     if(cl.valueAt(cl.length()-2).getValue() == 0)      return _mainL.constant(0);                  

//    System.out.println("cl dump:");
//	     cl.dump();

//    System.out.println("ml dump:");
//	     ml.dump();

//   System.out.println("cxl dump:");
//	     cxl.dump();

	     ml = ml.diff(cxl);                      

//	     ml = ml.add(cxl);                      

//   System.out.println("ml2 dump:");
//	     ml.dump();

	     ml = ml.div(prevl);
//   System.out.println("ml3 dump:");
//	     ml.dump();

	     ml= ml.mul(ml.constant(-1)).diff(ml.constant(0.07)).pos();
       return ml.and(vl);
//	     return ml.diff(ml.constant(0.07)).pos();
	  }
	  return _mainL.constant(0);
}
	
public static Line mainLine(LineGroup lg)
{
	   return _mainL;
}	
	
}
