package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class MAPainting extends AbstractGraphLine
{
	     private Line _l = null;
	     private Color _color;
	     
       public MAPainting(Line l,Color c)
       {
          _l = l;
          _paintdata = new PaintData[_l.length()];
          for(int i = 0; i < _paintdata.length; i++)
          {
          	   Value v = _l.valueAt(i);
          	   if(v.getValue() > maxValue)
          	   {
          	       maxValue = v.getValue();
          	   }
          	   if(v.getValue() < minValue)
          	   {
          	       minValue = v.getValue();
          	   }
               _paintdata[i] = new MAElement(v);
          }
          _color = c;
          name = "MLine";
          if(_l instanceof MALine)
          {
             name = "MA Line("+((MALine)_l).getPeriod()+")";
          }
       }
       
       public void paint(Graphics2D g, Rectangle rtv)
       {
       	   g.setColor(_color);
       	   int x1 = (int)rtv.getX();
       	   int x2 = x1+(int)rtv.getWidth();
       	   PaintData prevP = null;
       	   Stroke s = g.getStroke();
       	   for(int i = 0; i < _paintdata.length; i++)
       	   {
       	   	 if(prevP != null && _paintdata[i].getX() != 0 ) 
       	   	 {  
       	   	    if(_paintdata[i].getX() > x1 && _paintdata[i].getX() < x2)
       	   	    {
       	   	       if(((enhance_SIdx < _paintdata[i].getxIdxValue() &&  _paintdata[i].getxIdxValue() <= enhance_EIdx ) || (enhance_EIdx == -1)) && fstroke != null)
       	   	       {
       	   	          g.setStroke(fstroke);
       	   	       } else
       	   	       {
       	   	          g.setStroke(s);
       	   	       }
       	   	        g.drawLine( _paintdata[i-1].getX(),_paintdata[i-1].getY(),
       	   	                    _paintdata[i].getX(),_paintdata[i].getY());
       	   	        _paintdata[i].paint(g);             
       	   	    }
       	   	    prevP = _paintdata[i];
       	   	 }  else if(_paintdata[i].getX() != 0)
       	   	 {
       	   	     prevP = _paintdata[i];
       	   	 } 
       	   }
        }
       
 
}