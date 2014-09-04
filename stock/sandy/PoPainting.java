package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class PoPainting extends AbstractGraphLine
{
	   private Line _l = null;
	     
       public PoPainting(Line l)
       {
          _l = l;
          _paintdata = new PaintData[_l.length()];
		  Color c = new Color(150,0,0);
          for(int i = 0; i < _paintdata.length; i++)
          {
          	   Value kv = _l.valueAt(i);
          	   if(kv.getValue() > maxValue)
          	   {
          	       maxValue = kv.getValue();
          	   }
          	   if(kv.getValue() < minValue)
          	   {
          	       minValue = kv.getValue();
          	   }
               _paintdata[i] = new PoElement(kv);
			   ((PoElement)_paintdata[i]).setColor(c);
          }
          name = "Po Line";
       }
       
       public PoPainting(Line l,Color c)
       {
          _l = l;
          _paintdata = new PaintData[_l.length()];
          for(int i = 0; i < _paintdata.length; i++)
          {
          	   Value kv = _l.valueAt(i);
          	   if(kv.getValue() > maxValue)
          	   {
          	       maxValue = kv.getValue();
          	   }
          	   if(kv.getValue() < minValue)
          	   {
          	       minValue = kv.getValue();
          	   }
               _paintdata[i] = new PoElement(kv);
			   ((PoElement)_paintdata[i]).setColor(c);
          }
          name = "Po Line";
       }
	   
       public void paint(Graphics2D g, Rectangle rtv)
       {
       	   int x1 = (int)rtv.getX();
       	   int x2 = x1+(int)rtv.getWidth();
       	   for(int i = 0; i < _paintdata.length; i++)
       	   {
       	   	   if(_paintdata[i].getX() > x1 && _paintdata[i].getX() < x2)
       	   	   {
       	           _paintdata[i].paint(g);
       	       }
       	   }
       }
       
       public void setColor(Color c)
       {
          for(int i = 0; i < _paintdata.length; i++)
          {
               ((PoElement)_paintdata[i]).setColor(c);
          }
       }
       
 }



