package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class KPainting extends AbstractGraphLine
{
	   private KLine _l = null;
	     
       public KPainting(KLine l)
       {
          _l = l;
          _paintdata = new PaintData[_l.length()];
          for(int i = 0; i < _paintdata.length; i++)
          {
          	   KValue kv = (KValue)_l.valueAt(i);
          	   if(kv.getHigh() > maxValue)
          	   {
          	       maxValue = kv.getHigh();
          	   }
          	   if(kv.getLow() < minValue)
          	   {
          	       minValue = kv.getLow();
          	   }
               _paintdata[i] = new KElement(kv);
          }
          name = "K Line";
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
       
       public void setUpColor(Color c)
       {
          for(int i = 0; i < _paintdata.length; i++)
          {
               ((KElement)_paintdata[i]).setUpColor(c);
          }
       }
       
       public void setDownColor(Color c)
       {
          for(int i = 0; i < _paintdata.length; i++)
          {
               ((KElement)_paintdata[i]).setDownColor(c);
          }
       }

}