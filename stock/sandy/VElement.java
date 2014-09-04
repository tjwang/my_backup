package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class VElement implements PaintData
{
	     private Value _v = null;
	     private int _X = 0;
	     private int _Y = 0;
	     private int _width = 0;
	     private int _height = 0;
	     private Color _color;
	     private long _Xvalue = 0;
	     private boolean isSetXvalue = false;
	     
       public VElement(Value v, Color c)
       {
          _v = v;
          _color = c;
       }
       
       public int  getX()
       {
           return _X;
       }	
       
       public int  getY()
       {
           return _Y;
       }
       
       public void setPaintData(int x, int Zy, int xGap, double yratio, double ybase)
       {
           _X = x ;
           _Y = Zy - (int)((_v.getValue() - ybase) * yratio);
           _width = xGap;
           _height = Zy - _Y;
       }
       
       public void paint(Graphics2D g)
       {
             g.setColor(_color);
             if(_width > 1)
               g.fillRect(_X, _Y, (int)_width - 1, _height);
             else
               g.fillRect(_X, _Y, (int)_width , _height);
       }
       
       public void paintDesc(Graphics2D g, int x, int y)
       {
           g.setColor(Color.BLACK);
           String descStr = "VData Value:"
                            +String.format("%1$.2f",_v.getValue())   +  "   Date:"
//                           + String.format("%1$tY %1$tm/%1$te %tT", new Date(_v.getDateTime()));
                            +_v.getDomainValue().toString();
           g.drawString(descStr, x + 15, y + 15); 
       
       }
       
       public String getXMark()
       {
//       	  return String.format("%1$tY %1$tm/%1$te", new Date(_v.getDateTime()));
        	  return _v.getDomainValue().toString();
       }
       
       public long getxIdxValue()
       {
           //return _v.getDateTime();
        	 if(!isSetXvalue)
       	   {
   	   	      _Xvalue = _v.getDomainValue().getScale();
       	   }
           return _Xvalue;
       }  
          
       public Dimension getDescSize()
       {
           return  new Dimension(65,60);
       }
       
     
}