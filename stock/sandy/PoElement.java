package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class PoElement implements PaintData
{
	     private Value _k = null;
	     private int _X = 0;
	     private int _Y = 0;
	     private int _width = 0;
	     private int _height = 0;
	     private int _lx = 0;
	     private int _lhh = 0;
	     private int _lhl = 0;
	     private Color _dColor = Color.RED;
	     private long _Xvalue = 0;
	     private boolean isSetXvalue = false;
         private int[] _Ys=null;
		 
       PoElement(Value k)
       {
          _k = k;
		  if(_k instanceof ImageValue)
		  {
		     ImageValue iv = (ImageValue)_k; 
 			 _Ys = new int[iv.valueCount()];             
		  }
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
             double hy = _k.getValue();
             _Y = Zy - (int)((hy - ybase) * yratio);
			 if(_k instanceof ImageValue)
			 {
			    int idx = 0;
				ImageValue iv = (ImageValue)_k; 
				Enumeration e = iv.getImages();
				while(e.hasMoreElements())
				{
				    Value vx=((Value)e.nextElement());
					//vx.dump();
					double hy2 = vx.getValue();
                    _Ys[idx] = Zy - (int)((hy2 - ybase) * yratio);
                    idx++;
				}	
			 }
             
             _height = 1;
             _lx = x + (int)((xGap - 1) / 2);
             _lhh = 1 ; 
             _lhl = 1 ; 
             if(_height == 0) _height++;
             _width = (int)xGap;
             _X = x;
          }

       public void paint(Graphics2D g)
       {
             int height = _height;
             g.setColor(_dColor);
	//		 g.fillOval(_X, _Y-1, 2, 2);
	         if(_Ys!=null)
	         {
                for(int i=0;i<_Ys.length;i++)
                {
                  //  System.out.println("i:"+i+" x:"+_X+" y:"+ _Ys[i]);
					g.fillRect(_X, _Ys[i]-1, 3, 3);
				}
			 } else
			 {
                g.fillRect(_X, _Y-1, 2, 2);
			 }
//             g.drawLine(_lx, _Y - _lhh, _lx, _Y);
//             g.drawLine(_lx, _Y + height, _lx, _Y + height + _lhl);
       }
       
       public void paintDesc(Graphics2D g, int x, int y)
       {
           g.setColor(Color.BLACK);
           String descStr = "Data Value:"
                            +String.format("%1$.2f",_k.getValue()) +"   At:"
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
       
       
       public void setColor(Color c)
       {
          _dColor = c;
       }
     
}
