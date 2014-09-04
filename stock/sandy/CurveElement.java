package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class CurveElement implements PaintData
{
	     private PatternValue _v = null;
	     private int _X = 0;
	     private int _Y = 0;
	     private int[] _Xs = null;
	     private int[] _Ys = null; 
	     private int _width = 0;
	     private int _height = 0;
	     private Color _color;
	     private long _Xvalue = 0;
	     private boolean isSetXvalue = false;
	     
       public CurveElement(PatternValue v, Color c)
       {
          _v = v;
          _Xs = new int[v.length()];
          _Ys = new int[v.length()];
          _color = c;
       }
       
       public int  getX()
       {
           return _Xs[0];
       }	
       
       public int  getY()
       {
           return _Ys[0];
       }
       
       public void setPaintData(int x, int Zy, int xGap, double yratio, double ybase)
       {
           int vX = x ;
           Enumeration e = _v.elements();
           int i = 0;
           while(e.hasMoreElements())
           {
           	  Value v = (Value) e.nextElement();
              _Xs[i] = vX + (int)((xGap - 1) / 2);
              _Ys[i] = Zy - (int)((v.getValue() - ybase) * yratio);
              vX += xGap;
              i++;
           }
//           _width = xGap;
//           _height = Zy - _Y;
       }
       
       public void paint(Graphics2D g)
       {
           g.setColor(_color);
           for(int i = 0; i < _Xs.length-1; i++)
           {
               g.drawLine( _Xs[i], _Ys[i], _Xs[i+1], _Ys[i+1]);
           }
       }
       
       public void paintDesc(Graphics2D g, int x, int y)
       {
           g.setColor(Color.BLACK);
           String descStr = "CurveData Value:"
                            +String.format("%1$.2f",_v.getValue())   +  "   Date:"
//                           + String.format("%1$tY %1$tm/%1$te %tT", new Date(_v.getDateTime()));
                            +_v.getDomainValue().toString();
           g.drawString(descStr, x + 15, y + 15); 
       
       }
       
       public String getXMark()
       {
//       	  return String.format("%1$tY %1$tm/%1$te", new Date(_v.getDateTime()));
        	  return _v.getStartValue().getDomainValue().toString();
       }
       
       public long getxIdxValue()
       {
           //return _v.getDateTime();
        	 if(!isSetXvalue)
       	   {
   	   	      _Xvalue = _v.getStartValue().getDomainValue().getScale();
       	   }
           return _Xvalue;
       }  
          
       public Dimension getDescSize()
       {
           return  new Dimension(65,60);
       }
       
     
}