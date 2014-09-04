package stock.sandy;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import stock.db.*;

public class Diagram {
   
     int atPanelX = 0;
     int atPanelY = 0;
     int Uy = 60; //Y禸よ痙フ,程蔼翴竚(钩计) *
     int Ly = 60; //Y禸よ痙フ,程翴竚(钩计) *
     int Lx = 50; //X禸オよ痙フ(钩计)
     int height = 600 ; // よ痙フ + 计跋 + よ痙フ (钩计) %* height = Uy + Ly + (int)(YUpBound - YLowBound) * yRatio;
     int width = 800;  // オよ夹ボ痙フ + 计跋 (钩计) % width = Zx+getXNums() * xGap;
     int Zx = 50; //X翴(钩计)  Zx = Lx; %
     int Zy = 540; //Y翴(钩计) Zy = height - Ly - yGap * yDGapNum; %
     
     double YLowBound = 999999; //y禸计 程 *
     double YUpBound = 0;  //y禸计 程 *
     int yUGapNum = 8;  // Y禸よΤ碭 *
     int yDGapNum = 0;  // Y禸よΤ碭 *
     
     double yRatio = 0.05; //y禸计 ㎝ 钩计ゑㄒ %* yRatio = (YUpBound - YLowBound) / (Zy - Uy);
     double OyRatio = 0.05; //y禸计 ㎝ 钩计ゑㄒ (﹍) 
     double yGapValue = -1;// Y禸絬–虫计 % yGapValue = (YUpBound - YLowBound) / (yUGapNum+yDGapNum)
     int yGapPixel = 120;// Y禸絬–虫钩计 % yGapValue = (YUpBound - YLowBound) / (yUGapNum+yDGapNum)
     boolean isZyValueSeted = false;
     double ZyValue = 0; // Y禸计
     
     int yZoom = 0; //Y禸罽Ω计 %
     
     int ZxNum = 0;  // Zx材碭 *
     int XGap = 200; //X禸絬Τ钩计 *
     int xGap = 13; //X禸–虫禯瞒 (钩计) *
     
     boolean isXaxisVisible = true;
     boolean isYaxisVisible = true;
     boolean isXmarkVisible = true;
     boolean isShowFocus = true;
     XaxisFunc  _xf = null;
     Vector graphLinePool = new Vector();
     int focusLineIdx = 0;
     BufferedImage bufimg = null;
     Rectangle lastDrawRect = new Rectangle(0,0,0,0);
   
     public Diagram(int uy, int ly)
     {
        Uy =  uy ;
        Ly  = ly;
     }
  
     void drawLabels(Graphics g, Rectangle rtv)
     {
        Graphics2D   g2d   =   (Graphics2D)g;   
        
        int YLValue = atPanelY + height - Ly ;
        int vZy = Zy + atPanelY;
        int vUy = Uy + atPanelY;
        int XGapNum = (XGap/xGap);
        int XGapValue = XGapNum * xGap;
        
        int XValue = Lx + atPanelX;
        int i = 0;
        int x = (int)rtv.getX() ;
        int y = (int)rtv.getY() ;
        int x2 = x + (int)rtv.getWidth();
        int y2 = y + (int)rtv.getHeight();
        
        if(vZy + 15 > y2)
        {
           vZy = y2 - 20;
        }
        while(XValue < width)
        {
           if(i > 0)
           {
           	   if(XValue >= x && XValue <= x2)
           	   {
                  g2d.setColor(new Color(200,200,200));
                  g2d.drawLine(XValue, vUy-10, XValue, YLValue+10); 
                  if(isXmarkVisible)
                  {
                     g2d.setColor(Color.BLACK);
                     g2d.drawString( _xf.getXMark(i*XGapNum), XValue-25, vZy+15); 
                  }
               }
           }
           if(i == ZxNum)
           {
               Zx = XValue;
           }
           XValue += XGapValue;
           i++;
        }
     
        int YValue = YLValue;
        int XLabel = Zx - 60;
        double YLabel = YLowBound;
        vZy = Zy + atPanelY;
        if(XLabel < x)
        {
           XLabel = x + 10;
        }
//        while(YValue > vUy - 10)
//        {
//           if (YValue >= y && YValue <= y2)
//           {
//              g2d.setColor(Color.BLACK);
//              g2d.drawString(String.format("%1$.2f",YLabel),XLabel,YValue);
//              g2d.setColor(new Color(200,200,200));
//              drawVirtualLine(g2d, Lx, YValue, width, YValue); 
//           }
//           YLabel += yGapValue;
//           YValue = YLValue - ((int)((YLabel-YLowBound) * yRatio));
//        }
        for(i = 0 - yDGapNum; i < yUGapNum + 1 ; i++)
        {
           YLabel = ZyValue + yGapValue * i;
           YValue = vZy - ((int)((YLabel-ZyValue) * yRatio));
           if (YValue >= y && YValue <= y2)
           {
              g2d.setColor(Color.BLACK);
              g2d.drawString(String.format("%1$.2f",YLabel),XLabel,YValue);
              g2d.setColor(new Color(200,200,200));
              drawVirtualLine(g2d, Lx, YValue, width, YValue); 
           }
        }
        g2d.setColor(Color.BLACK);
      
        if(isXaxisVisible)
           g2d.drawLine(Lx, vZy, width, vZy);   
        if(isYaxisVisible)
           g2d.drawLine(Zx, Uy - 10, Zx, YLValue + 10); 
     }
  
     void drawContent(Graphics g, Rectangle rtv)
     {
     	 if((int)lastDrawRect.getX() != (int)rtv.getX() || 
          (int)lastDrawRect.getY() != (int)rtv.getY() || 
          (int)lastDrawRect.getWidth() != (int)rtv.getWidth() || 
          (int)lastDrawRect.getHeight() != (int)rtv.getHeight() || (bufimg == null)) 
        {
           bufimg = new BufferedImage((int)(rtv.getX()+rtv.getWidth()), (int)(rtv.getY()+rtv.getHeight()), BufferedImage.TYPE_INT_RGB);
//           Graphics2D   g2d   =   (Graphics2D)g;  
           Graphics2D   g2d   =   (Graphics2D)bufimg.getGraphics();  
    	     g2d.setClip(0, atPanelY, width, height);
           g2d.setColor(Color.WHITE);
           g2d.fillRect(0, atPanelY, width, height);
           g2d.setColor(Color.BLACK);
    	     Enumeration e = graphLinePool.elements();
    	     int i = 0;
    	     while(e.hasMoreElements())
    	     {
    	         GraphLine gl = (GraphLine) e.nextElement();
          	   Stroke s = g2d.getStroke();
    	         if((i == focusLineIdx) && (isShowFocus))
    	         {
          	       // g2d.setStroke(new BasicStroke(2));
          	       gl.setFocusStroke(new BasicStroke(2));
    	         }
    	         gl.paint(g2d,rtv);
               gl.setFocusStroke(null);
               g2d.setStroke(s);
               i++;
    	     }
    	     g.setClip(0, atPanelY, width, height);
    	     ((Graphics2D)g).drawImage(bufimg, new AffineTransform(), null);
        } else
        {
    	     g.setClip(0, atPanelY, width, height);
           ((Graphics2D)g).drawImage(bufimg, new AffineTransform(), null);
        }
        lastDrawRect = new Rectangle((int)rtv.getX(),(int)rtv.getY(),(int)rtv.getWidth(),(int)rtv.getHeight());
     }

     void drawCursor(Graphics g, int Mx, int My, Rectangle rtv)
     {
            int vZy = height - Ly + atPanelY;
            int vUy = Uy + atPanelY;
            int x = (int)rtv.getX() + Lx + atPanelX;
            Graphics2D   g2d   =   (Graphics2D)g;   
            g2d.setColor(Color.RED);
            g2d.drawLine(Mx, atPanelY, Mx, atPanelY+height);
            g2d.drawLine(0, My, width, My);
            if(graphLinePool.size() > 0)
            {
               GraphLine gl = getFocusGraphLine();
               PaintData pd = gl.getPaintDataByXPos(Mx);
               g2d.drawString(gl.getName(), x+10, atPanelY + 15);
               if(pd != null)
               {
                  pd.paintDesc(g2d, x+90, atPanelY);
               }
            }
            if( My > atPanelY && My < vZy)
            {
               g.setColor(Color.BLUE);
               g.drawString(String.format("%1$.2f",YLowBound+(vZy-My)/ yRatio), x, My);
            }
     }
  
	   public void setEnhanceS(int idx)
	   {
    	     Enumeration e = graphLinePool.elements();
    	     long xv = -1;
    	     if(idx > 0)
    	        xv = _xf.getXValue(idx);
    	     while(e.hasMoreElements())
    	     {
    	         GraphLine gl = (GraphLine) e.nextElement();
	             gl.setEnhanceS(xv);
	         }
	   }
	   public void setEnhanceE(int idx)
	   {
    	     Enumeration e = graphLinePool.elements();
    	     long xv = -1;
    	     if(idx > 0)
    	        xv = _xf.getXValue(idx);
    	     while(e.hasMoreElements())
    	     {
    	         GraphLine gl = (GraphLine) e.nextElement();
	             gl.setEnhanceE(xv);
	         }
	   }
     public void setXaxisFunc(XaxisFunc xf)
     {
         _xf = xf;
         SyncParameterWithGraphLine();
     }
  
     public XaxisFunc getXaxisFunc()
     {
         return _xf;
     }
     public void setPanelPos(int x, int y)
     {
         atPanelX = x;
         atPanelY = y;
         SyncParameterWithGraphLine();
     }
     
     public Rectangle getPanelPos()
     {
        return new Rectangle(atPanelX, atPanelY, width, height);
     }
     
     public GraphLine getFocusGraphLine()
     {
         if (graphLinePool.size() > 0)
         {
            return (GraphLine)graphLinePool.elementAt(focusLineIdx);
         }
        return null;
     }
     
     public void changeFocusGraphLine()
     {
         if (graphLinePool.size() > 0)
         {
            focusLineIdx = (++focusLineIdx) % graphLinePool.size();
        	  bufimg = null;
         }
        // System.out.println("changeFocusGraphLine" + focusLineIdx+ " " + graphLinePool.size());
     }
     
     public void add(GraphLine gl)
     {
     	  if(_xf == null)
     	  {
     	    _xf = new XaxisFuncByGLIdx(gl);  
     	  }
         
        if(gl.getMaxValue() > YUpBound)
        {
            YUpBound = gl.getMaxValue();
        }
        if(gl.getMinValue() < YLowBound)
        {
            YLowBound = gl.getMinValue();
        }
        yGapValue = (YUpBound - YLowBound) / (double)(yUGapNum+yDGapNum) ;
        graphLinePool.add(gl);
      	SyncParametersByPixel();
      }
     
     public void SyncParameterWithGraphLine()
     {
    	  Enumeration e = graphLinePool.elements();
    	  int YLValue = atPanelY + height - Ly ;
    	  while(e.hasMoreElements())
    	  {
    	      GraphLine gl = (GraphLine) e.nextElement();
     	      int xPos = Lx+atPanelX;
     	      for(int i=0; i<_xf.getSize(); i++)
     	      {
     	         gl.setElementPaintData(_xf.getXValue(i), xPos, YLValue, xGap, yRatio, YLowBound);
     	         xPos += xGap;
     	      }
    	  }
    	  bufimg = null;
     }
     
     void SyncParametersByPixel()
     {
//  	    System.out.println("SyncParametersByPixel");
  	    width = Zx+getXNums() * xGap;
        yRatio = ((double)(height- Ly - Uy))/(YUpBound - YLowBound) ;
        OyRatio = yRatio;
        yZoom = 0;
        if(yGapValue < 0)
        {
           yGapValue = (YUpBound - YLowBound) / (double)(yUGapNum+yDGapNum) ;
   	    }
   	    Zy = height - Ly - ((int)(yGapValue * yDGapNum * yRatio));
  	    if(!isZyValueSeted)
  	    {
   	       ZyValue = YLowBound + yGapValue * yDGapNum;
  	    }
   	    SyncParameterWithGraphLine();
     }

    void SyncParametersByValue()
    {
//  	    System.out.println("SyncParametersByValue");
  	    height =  Uy + Ly + (int)((YUpBound - YLowBound) * yRatio);
   	    width = Zx+getXNums() * xGap;
        if(yGapValue < 0)
        {
          yGapValue = (YUpBound - YLowBound) / (double)(yUGapNum+yDGapNum) ;
  	    }
  	    Zy = height - Ly - ((int)(yGapValue * yDGapNum * yRatio));
  	    if(!isZyValueSeted)
  	    {
   	       ZyValue = YLowBound + yGapValue * yDGapNum;
  	    }
  	    SyncParameterWithGraphLine();
    }
    
    public void setZyValue(double zyv)
    {
        ZyValue = zyv;
        Zy = height - Ly - ((int)((ZyValue - YLowBound) * yRatio));
        isZyValueSeted = true;
    }
    
    public void setyGapValue(double gv)
    {
        yGapValue = gv;
    }
    
    public int getHeight() 
    {
        return height;
    }
    
    public int getWidth()  
    {
//        System.out.println("d w "+width);
        return width;
    }
  
    public int getXNums()
    {
       if(_xf == null)
       {
          if(graphLinePool.size() > 0)
          {
              _xf = new XaxisFuncByGLIdx((GraphLine)graphLinePool.elementAt(0));
          }
          else
          {
             return 0;
          }
       }
       return _xf.getSize();
    }
  
    public void setYNum(int dn, int un)
    {
    	  yDGapNum = dn;
    	  yUGapNum = un;
        SyncParametersByPixel();
    }
    
    public void setZxNum(int n)
    {
        ZxNum = n;
        SyncParametersByPixel();
    }

    public void setDisplayParameters(int yValueHight)
    {
    	  Enumeration e = graphLinePool.elements();
    	  if (e.hasMoreElements())
    	  {
    	      YLowBound = 999999999;
    	      YUpBound = -999999999;
    	  }
    	  while(e.hasMoreElements())
    	  {
    	      GraphLine gl = (GraphLine) e.nextElement();
          	if(gl.getMaxValue() > YUpBound)
          	{
          	    YUpBound = gl.getMaxValue();
          	}
          	if(gl.getMinValue() < YLowBound)
          	{
          	    YLowBound = gl.getMinValue();
          	}
    	  }
        height = yValueHight + Uy + Ly;
        SyncParametersByPixel();
    }

    public void setDisplayParameters(double YLowBound1, double YUpBound1,int yValueHight)
    {
    	  YLowBound = YLowBound1;
    	  YUpBound = YUpBound1;
        height = yValueHight + Uy + Ly;
    	  yGapValue = (YUpBound - YLowBound) / (double)(yUGapNum+yDGapNum) ;
        SyncParametersByPixel();
    }

    public void setYLowBound(double YLowBound1)
    {
    	  YLowBound = YLowBound1;
    	  yGapValue = (YUpBound - YLowBound) / (double)(yUGapNum+yDGapNum) ;
        SyncParametersByPixel();
    }
  
    public void setYUpBound(double YUpBound1)
    {
    	  YUpBound = YUpBound1;
    	  yGapValue = (YUpBound - YLowBound) / (double)(yUGapNum+yDGapNum) ;
        SyncParametersByPixel();
    }
    
    public void setYRatio(boolean b)
    {
    	  if(b)
    	  {
    	     yZoom++;
    	  } else
    	  {
    	     yZoom--;
    	  }
        yRatio = OyRatio * (1+0.05 * yZoom);
        SyncParametersByValue();
    }

    public void setYRatio(int yz)
    {
    	  yZoom=yz;
        yRatio = OyRatio * (1+0.05 * yZoom);
        SyncParametersByValue();
    }
    public int getYRatio()
    {
    	  return yZoom;
    }

    public void setxGap(int xg)
    {
    	  xGap = xg;
    	  width = getXNums()*xGap + Lx;
//    	  System.out.println("getXNums() "+getXNums()+ " xGap "+xGap + " Lx"+Lx +" width "+width);
  	   SyncParameterWithGraphLine();
    }
    
     public void setXGap(int Xg)
    {
    	  XGap = Xg;
    }
  
    public void setXaxisVisible(boolean b)
    {
        isXaxisVisible = b;
    }
    
    public void setXmarkVisible(boolean b)
    {
        isXmarkVisible = b;
    }

    public void setShowFocus(boolean b)
    {
        isShowFocus = b;
    	  bufimg = null;
    }

    public boolean  getShowFocus()
    {
        return isShowFocus ;
    }
    
    public void setYaxisVisible(boolean b)
    {
        isYaxisVisible = b;
    }
  
    void drawVirtualLine(Graphics2D g2d,int x1, int y1,int x2, int y2)
    {
       double rx = (x2 - x1) ;
       double ry = (y2 - y1);
       double rxy = Math.pow(rx * rx + ry * ry,0.5);
       rx = (rx / rxy )* 5;
       ry = (ry / rxy )* 5;
       int count = 0;
       double vx1 = x1 ;
       double vy1 = y1 ;
       double vx2 = vx1 +  rx;
       double vy2 = vy1 +  ry;
       while((vx2 < x2) || (vy2 < y2))
       {
         if(count == 0)
         {
            g2d.drawLine((int)vx1, (int)vy1, (int)vx2, (int)vy2);
           // System.out.println("vx1:"+((int)vx1)+", vy1:"+((int)vy1)+", vx2:"+((int)vx2)+", vy2:"+((int)vy2));
            count = 1;
         } else {
            count = 0;
         }
         vx1 = vx2 ;
         vy1 = vy2 ;
         vx2 = vx1 +  rx;
         vy2 = vy1 +  ry;
       }
    
    }
}