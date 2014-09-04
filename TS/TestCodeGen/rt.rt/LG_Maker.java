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
public static LineGroup basic(RT_sFactory fac1, RT_oFactory fac2)
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
	   return lg;
}	

public static Line markLine(LineGroup lg)
{
	   return null;
}
	
public static Line mainLine(LineGroup lg)
{
	   return _mainL;
}	
	
}
