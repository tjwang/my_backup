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

public static LineGroup basic(FTT_sFactory fac1, FTT_oFactory fac2)throws Exception
{
     //_mainL = fac1.culKLine();
     Line l_1 = fac1.getLineByCode("2330",1);
     Line l_2 = fac1.getLineByCode("2317",1);
     Line l_3 = fac1.getLineByCode("2454",1);
     Line l_4 = fac1.getLineByCode("2412",1);
     Line l_tx = fac1.getLineByCode("TX",1);
     Line l_tx084 = fac1.getLineByCode("TX094",1);
     Line l_self = fac1.getLineByCode("TX_SELF",1);
     Line l_maall = fac1.getLineByCode("MA_ALL",1);
     Line l_maall_d = fac1.getLineByCode("MA_ALL_D",1);
     Line l_sta_up = fac1.getLineByCode("TX_200_Up_Sta",1);
     Line l_sta_down = fac1.getLineByCode("TX_200_Down_Sta",1);
     _mainL = l_tx084;
     
     LineGroup lg = new LineGroup(_mainL);
     lg.addLine(l_tx);
     lg.addLine(l_tx084);
     lg.addLine(l_self);
     lg.addLine(l_1);
     lg.addLine(l_2);
     lg.addLine(l_3);
     lg.addLine(l_4);
     lg.addLine(l_sta_up);
     lg.addLine(l_sta_down);
//     lg.setupLine();
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
   return  _mainL.constant(0);
}
	
public static Line mainLine(LineGroup lg)
{
	   return _mainL;
}	
	
}
