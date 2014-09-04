package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class KElement implements PaintData
{
	     private KValue _k = null;
	     private int _X = 0;
	     private int _Y = 0;
	     private int _width = 0;
	     private int _height = 0;
	     private int _lx = 0;
	     private int _lhh = 0;
	     private int _lhl = 0;
	     private Color downColor = Color.GREEN;
	     private Color upColor = Color.RED;
	     private long _Xvalue = 0;
	     private boolean isSetXvalue = false;
	     
       KElement(KValue k)
       {
          _k = k;
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
             double hy = (_k.getClose() > _k.getOpen())?_k.getClose():_k.getOpen();
             _Y = Zy - (int)((hy - ybase) * yratio);
             _height = (int)(_k.getReal() * yratio);
             _lx = x + (int)((xGap - 1) / 2);
             _lhh = (int)(_k.getUp() * yratio) ; 
             _lhl = (int)(_k.getDown() * yratio) ; 
             if(_height == 0) _height++;
             _width = (int)xGap;
             _X = x;
            // System.out.println(_Y+" "+ Zy+" "+ ybase +" "+ hy+" "+yratio);
         }

       public void paint(Graphics2D g)
       {
             int height = _height;
             if(height < 0)
             {
                height = height * -1; 
                g.setColor(downColor);
             }else{
                g.setColor(upColor);
             }
             g.fillRect(_X, _Y, (int)_width - 1, height);
             g.drawLine(_lx, _Y - _lhh, _lx, _Y);
             g.drawLine(_lx, _Y + height, _lx, _Y + height + _lhl);
       }
       
       public void paintDesc(Graphics2D g, int x, int y)
       {
           g.setColor(Color.BLACK);
           String descStr = "KData Open:"
                            +String.format("%1$.2f",_k.getOpen())   +"   High:"
                            + String.format("%1$.2f",_k.getHigh())  +"   Low:"
                            + String.format("%1$.2f",_k.getLow())   +"   Close:"
                            + String.format("%1$.2f",_k.getClose()) +"   Vol:"
                            + String.format("%1$.2f",_k.getVolume())+"   At:"
                           +  _k.getDomainValue().toString();
           g.drawString(descStr, x + 15, y + 15); 
       }
       
       public String getXMark()
       {
       	//  return String.format("%1$tY %1$tm/%1$te", new Date(_k.getDateTime()));
      	  return _k.getDomainValue().toString();
       }
       
       public long getxIdxValue()
       {
       	   if(!isSetXvalue)
       	   {
   	   	      _Xvalue = _k.getDomainValue().getScale();
       	   }
           return _Xvalue;
       }  
          
       public Dimension getDescSize()
       {
            return  new Dimension(65,100);
       }
       
       
       public void setUpColor(Color c)
       {
          upColor = c;
       }
       public void setDownColor(Color c)
       {
         downColor = c;
       }
     
}