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
public static LineGroup basic(M2_sFactory fac1, M2_oFactory fac2)throws Exception
{
     _mainL = fac1.culKLine();
     LineGroup lg = new LineGroup(_mainL);
     Line m3 =  _mainL.avg(3);
     Line m8 =  _mainL.avg(8);
     Line m13 =  _mainL.avg(13);
     lg.addLine(m3);
     lg.addLine(m8);
     lg.addLine(m13);
     lg.setupLine();
   /*  
     _mainL = fac2.culKLine();
     fac2.loadMData();
     _pmrL = fac2.culKLine();
     if(_pmrL.length()>0)
     {
        _mainL=(KLine)_mainL.sub(_pmrL.valueAt(0).getDomainValue(),_pmrL.valueAt(_pmrL.length()-1).getDomainValue());
     }
     LineGroup lg = new LineGroup(_mainL);
     lg.addLine(_pmrL);
	   */
	   return lg;
}	

public static Line markLine(LineGroup lg)
{
/*
	  if(_pmrL.length()>0)
	  {
	     Line vl = _mainL.getVol();
	     Line ml = _pmrL.getClose();
	     Line rl = ml.div(vl);
	     lg.addLine(rl);
	     double maxr = rl.getMax();
	     if(maxr < 0.3) return rl.constant(0);
	     rl = rl.diff(rl.constant(maxr-0.000001)).pos().sign();
	     return rl;
	  }
	  return _mainL.constant(0);
*/
   return null;
}
	
public static Line mainLine(LineGroup lg)
{
	   return _mainL;
}	
	
}
