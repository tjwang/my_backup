package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class CurvePainting extends AbstractGraphLine
{
	     private PatternLine _l = null;
	     private PaintData[] _pdarray = null;
	     
       public CurvePainting(PatternLine l,Color c)
       {
          _l = l;
          _paintdata = new PaintData[_l.length()];
          for(int i = 0; i < _paintdata.length; i++)
          {
          	   PatternValue v = (PatternValue)_l.valueAt(i);
          	   if(v.getHigh() > maxValue)
          	   {
          	       maxValue = v.getHigh();
          	   }
          	   if(v.getLow() < minValue)
          	   {
          	       minValue = v.getLow();
          	   }
               _paintdata[i] = new CurveElement(v,c);
          }
          name = "Pattern Line";
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