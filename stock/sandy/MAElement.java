package stock.sandy;
import stock.fight.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class MAElement implements PaintData
{
	     private Value _mav = null;
	     private int _X = 0;
	     private int _Y = 0;
	     private long _Xvalue = 0;
	     private boolean isSetXvalue = false;

       public MAElement(Value mav)
       {
          _mav = mav;
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
           _X = x + (int)((xGap - 1) / 2);
           _Y = Zy - (int)((_mav.getValue() - ybase) * yratio);
           if(_X == 0)
           {
           	    System.out.print("X == Zero ::");
           	    _mav.dump();
           }
       }
              
       public void paint(Graphics2D g)
       {
       }
       
       public void paintDesc(Graphics2D g, int x, int y)
       {
           g.setColor(Color.BLACK);
           String descStr = "MaData Value:"
                            +String.format("%1$.2f",_mav.getValue())   +  "   At:"
//                           + String.format("%1$tY %1$tm/%1$te %tT", new Date(_mav.getDateTime()));
                            + _mav.getDomainValue().toString();
           g.drawString(descStr, x + 15, y + 15); 
       
       }
       
      public String getXMark()
       {
   //    	  return String.format("%1$tY %1$tm/%1$te", new Date(_mav.getDateTime()));
      	  return _mav.getDomainValue().toString();
       }
       
       public long getxIdxValue()
       {
        //   return _mav.getDateTime();
        	 if(!isSetXvalue)
       	   {
   	   	      _Xvalue = _mav.getDomainValue().getScale();
       	   }
          return _Xvalue;
       }  
          
       public Dimension getDescSize()
       {
            return  new Dimension(60,60);
       }
       

}