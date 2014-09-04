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
	
static KLine _mainL = null;	
static KLine _pmrL = null;	
public static LineGroup basic(MF_sFactory fac1, MF_oFactory fac2)throws Exception
{
     _mainL = fac2.culKLine();
     fac2.loadMData();
     _pmrL = fac2.culKLine();
     KLine allK = _mainL;
     if(_pmrL.length()>0)
     { 
        _mainL=(KLine)_mainL.sub(_pmrL.valueAt(0).getDomainValue(),_pmrL.valueAt(_pmrL.length()-1).getDomainValue());
     }
     LineGroup lg = new LineGroup(_mainL);
     lg.addLine(_pmrL);
     lg.addLine(allK);
     
	   return lg;
}	

public static Line markLine(LineGroup lg)
{
	  if(_pmrL.length()>0 && _mainL.length()>0)
	  {
	     Line vl = _mainL.getVol();
	     Line ml = _pmrL.getClose();
	     Line rl = ml.div(vl);
	     lg.addLine(rl);
	     double maxr = rl.getMax();
	     if(maxr < 0.3) return rl.constant(0);
	     rl = rl.diff(rl.constant(0.3)).pos().sign();
	     return rl;
	  }
	  return _mainL.constant(0);
}
	
public static Line mainLine(LineGroup lg)
{
	   return _mainL;
}	
	
}
