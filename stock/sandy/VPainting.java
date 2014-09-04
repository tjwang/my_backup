package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class VPainting extends AbstractGraphLine
{
	     private Line _l = null;
	     private PaintData[] _pdarray = null;
	     
       public VPainting(Line l,Color c)
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
               _paintdata[i] = new VElement(v,c);
          }
          name = "Vol Line";
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

}