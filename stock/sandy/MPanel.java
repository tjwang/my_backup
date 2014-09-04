package stock.sandy;

import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import stock.app.*;

public class MPanel extends JLabel implements Icon {
   

   int Mx; //滑鼠的X坐標(像數)
   int My; //滑鼠的Y坐標(像數)
   int Lx = 90; //X軸左方留白(像數)
   int ZxNum = 0;  // Zx在第幾個大格 *
   int XGap = 200; //X軸大格線有像數 *
   int xGap = 13; //X軸每個單位的距離 (像數) *
   int markIdxS = -1;
   int markIdxE = -1;
   boolean yRatioCanBeTuned = true; //yRatio是否隨滾輪變化
   boolean ySingleDiagramBeTuned = true; //是否只調整一個Diagram
   Vector diagramPool = new Vector();
   Vector ConnectionedPanel = new Vector();
   int focusDiagramIdx = 0;

   public static void main(String[] arg) throws Exception
   {
      JFrame jf = new JFrame("賺大錢");
      StockKD   skd = new StockKD("0001");
      KPainting kpaint = new KPainting(skd.culKLine());
      XaxisFuncByGLIdx _xfg = new XaxisFuncByGLIdx(kpaint);
      MPanel mp = new MPanel();
  	  Diagram dg = new Diagram(60,0);
      dg.setYNum(0,10);
  	  dg.setXaxisFunc(_xfg);
  	  dg.add(kpaint);
  	  dg.add(new MAPainting(skd.culMALine(8),Color.BLUE));
      dg.setDisplayParameters(300);
      //dg.setXmarkVisible(false);
      //dg.setXaxisVisible(false);
      mp.add(dg);
  	  
  	  Diagram dg2 = new Diagram(30,60);
  	  dg2.setXaxisFunc(_xfg);
  	  dg2.add(new VPainting(skd.culVolMALine(1),Color.RED));
      dg2.setDisplayParameters(300);
      dg2.setYLowBound(0);
      dg2.setYNum(0,4);
      mp.add(dg2);
      
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
   }  
   
  public MPanel()
  {
  //   setZyNum(4);
  //   setYNum(4,4);
     MyMouseListener mml = new MyMouseListener(this);
     addMouseListener(mml);
     addMouseMotionListener(mml);
     addMouseWheelListener(mml);
     setIcon(this);
     
  }
  
  
  public void paintIcon(Component c, Graphics g, int x, int y) 
  {   
     Enumeration  e = diagramPool.elements();
     Rectangle rtv = getVisibleRect();
     while(e.hasMoreElements())
     {
         Diagram dg = (Diagram) e.nextElement();
         dg.drawContent(g, rtv);
         dg.drawLabels(g, rtv);
         dg.drawCursor(g, Mx, My, rtv);
         
         g.setColor(Color.RED);
         if(yRatioCanBeTuned)
         {
             g.drawString("YScare",5,15);
         } else{
             g.drawString("XScare",5,15);
         }
         if(ySingleDiagramBeTuned)
         {
             g.drawString("Diagram One",5,30);
         } else{
             g.drawString("Diagram All",5,30);
         }
     }
  }   
  
  public int getDiagramHeight() 
  {
     Enumeration  e = diagramPool.elements();
     int hieght = 0;
     while(e.hasMoreElements())
     {
         Diagram dg = (Diagram) e.nextElement();
         hieght += dg.getHeight();
     }
     return hieght;
  }
    
  public int getIconHeight() 
  {
     return getDiagramHeight()+30;
  }
  
  public int getIconWidth()  
  {
     Enumeration  e = diagramPool.elements();
     int width = 0;
     while(e.hasMoreElements())
     {
         Diagram dg = (Diagram) e.nextElement();
         int dgwidth = dg.getWidth();
         if (dgwidth > width)
         {
            width = dgwidth;
         }
     }
     return width+30;
  }
  
  public void add(Diagram dg)
  {
     dg.Lx = Lx;
     dg.ZxNum = ZxNum;
     dg.XGap = XGap;
     dg.xGap = xGap;
     dg.setPanelPos(0, getDiagramHeight());
     dg.SyncParameterWithGraphLine();
     diagramPool.add(dg);
     updateUI() ;
     invalidate();
  }
  
  public Diagram getFocusDiagram()
  {
      if (diagramPool.size() > 0)
      {
         return (Diagram)diagramPool.elementAt(focusDiagramIdx);
      }
     return null;
  }
  
  public Diagram getDiagram(int x, int y)
  {
     Enumeration  e = diagramPool.elements();
     int hieght = 0;
     while(e.hasMoreElements())
     {
         Diagram dg = (Diagram) e.nextElement();
         Rectangle rt = dg.getPanelPos();
         if( rt.getX() <= x && x <= rt.getX()+rt.getWidth() &&
             rt.getY() <= y && y <= rt.getY()+rt.getHeight() 
           )
          return dg;
     }
     return null;
  }
  
  public void setFocusDiagram(Diagram dg)
  {
      if (diagramPool.size() > 0 && dg != null)
      {
         int i = 0;
         Enumeration  e = diagramPool.elements();
         while(e.hasMoreElements())
         {
            Diagram dg2 = (Diagram) e.nextElement();
            if(dg2 == dg)
            {
               focusDiagramIdx = i;
               return ;
            }
            i++;
         }
      }
  }

  public void addConnectionPanel(MPanel mp)
  {
      ConnectionedPanel.add(mp);
  }
  
  public MPanelSetting getSetting()
  {
      MPanelSetting mss = new MPanelSetting();
      mss.Mx = Mx;
      mss.My = My; //滑鼠的Y坐標(像數)
      mss.Lx = Lx; //X軸左方留白(像數)
      mss.ZxNum = ZxNum;  // Zx在第幾個大格 *
      mss.XGap = XGap; //X軸大格線有像數 *
      mss.xGap = xGap; //X軸每個單位的距離 (像數) *
      mss.yzs = new int[diagramPool.size()];
      Enumeration  e = diagramPool.elements();
      int i = 0;
      while(e.hasMoreElements())
      {
      	 Diagram dg = (Diagram) e.nextElement();
       	 mss.yzs[i]= dg.getYRatio();
       	 i++;
      }
      
      return mss;
  }
  
  public void restoreSetting(MPanelSetting mss)
  {
     if(mss != null)
     {
 //      Mx = mss.Mx;
 //      My = mss.My; //滑鼠的Y坐標(像數)
//       Lx = mss.Lx; //X軸左方留白(像數)
//       ZxNum = mss.ZxNum;  // Zx在第幾個大格 *
       XGap =  mss.XGap; //X軸大格線有像數 *
       xGap = mss.xGap; //X軸每個單位的距離 (像數) *
       Enumeration  e = diagramPool.elements();
       int i = 0;
       while(e.hasMoreElements())
       {
          Diagram dg = (Diagram) e.nextElement();
          dg.setYRatio(mss.yzs[i]-1);
          i++;
       }
       tuneYRatio(true);
       e = diagramPool.elements();
       while(e.hasMoreElements())
       {
          Diagram dg = (Diagram) e.nextElement();
          dg.setXGap(XGap);
          dg.setxGap(xGap);
       }     
      updateUI() ;
      invalidate();
    }
  }
  
  public void setConnectedPanelVLine(int mxpos)
  {  
       if (mxpos > getIconWidth() - 30) return;
       int xIdx = (mxpos - Lx) / xGap ;
       Enumeration e = ConnectionedPanel.elements();
       while(e.hasMoreElements())
       {
           MPanel mp  = (MPanel)e.nextElement();
           mp.setVlineByElementIdx(xIdx);
       }
  }
  
  public void setVlineByElementIdx(int idx)
  {  
//      int vMx = xGap * idx + Lx;
//      if(vMx > getIconWidth() - 30) return ;
//      Mx = vMx ; 
      Diagram dg = getFocusDiagram();
      if(dg != null)
      {
         GraphLine gl = dg.getFocusGraphLine();
         if(gl != null)
         {
             PaintData pd = gl.getPaintDataByIdx(idx);
             if(pd != null)
             {
                Mx = pd.getX();
                My = pd.getY();
             }
         }
      }
      updateUI() ;
      invalidate();
  } 

  public void tuneYRatio(boolean b)
  {
     Enumeration  e = diagramPool.elements();
     int hieght = 0;
     while(e.hasMoreElements())
     {
         Diagram dg = (Diagram) e.nextElement();
         dg.setPanelPos(0, hieght);
         dg.setYRatio(b);
         hieght += dg.getHeight();
     }
     updateUI() ;
     invalidate();
  }
  
  public void tuneYRatio(Diagram dg, boolean b)
  {
     int hieght = 0;
     dg.setYRatio(b);
     Enumeration  e = diagramPool.elements();
     while(e.hasMoreElements())
     {
         Diagram dg2 = (Diagram) e.nextElement();
         dg2.setPanelPos(0, hieght);
         hieght += dg2.getHeight();
     }
     updateUI() ;
     invalidate();
  }

  public void tuneXGap(int diff)
  {
    if(xGap + diff >= 1)
    {
    	 xGap += diff;
    }
    if(xGap > 1) xGap=(int)xGap;
 //   System.out.println("xGap tuned:"+xGap);
    Enumeration  e = diagramPool.elements();
    while(e.hasMoreElements())
    {
       Diagram dg = (Diagram) e.nextElement();
       dg.setxGap(xGap);
    }
    updateUI() ;
    invalidate();
  }
  

static class MyMouseListener extends MouseAdapter
{
	 MPanel _mp;
   MyMouseListener(MPanel mp)
   {
      _mp = mp;
   }
   public void mouseMoved(MouseEvent e)
   {
        int mx = e.getX() - _mp.Lx;
        int my = e.getY();
        int m = ((int)_mp.xGap) / 2 ;
        boolean redraw = false;
        
        if(mx < 0)
             return ;
             
        if(my != _mp.My)
        {     
           _mp.My = e.getY();
           redraw = true;
        }
        mx = (int)(((mx - m) / _mp.xGap) * _mp.xGap + m );
        if (_mp.Mx != (mx + _mp.Lx))
        {
           _mp.Mx = mx + _mp.Lx;
           _mp.setConnectedPanelVLine(_mp.Mx);
           redraw = true;
        }  
        if(redraw)
        {
           _mp.updateUI() ;
           _mp.invalidate();
        }
 //     System.out.println("mouseMoved X:"+e.getX()+" Y:"+e.getY() );
   }
   public void mousePressed(MouseEvent e) 
   {
 //     System.out.println("mousePressed X:"+e.getX()+" Y:"+e.getY() );
   }
   public void mouseClicked(MouseEvent e) 
   {
 //     System.out.println("mouseClicked X:"+e.getX()+" Y:"+e.getY() + "button: "+e.getButton());
       if(e.getClickCount() >= 2)
       {
          if(e.getButton() == MouseEvent.BUTTON1)
          {
             _mp.yRatioCanBeTuned = !_mp.yRatioCanBeTuned;
          }
          if(e.getButton() == MouseEvent.BUTTON3)
          {
             _mp.ySingleDiagramBeTuned = !_mp.ySingleDiagramBeTuned;
          }
      } else {
          if(e.getButton() == MouseEvent.BUTTON1)
          {
          	  Diagram vdg = _mp.getDiagram(e.getX(), e.getY());
          	 if (vdg != _mp.getFocusDiagram())
          	 {
          	    _mp.setFocusDiagram(vdg);
          	 } else
          	 {
          	     vdg.setShowFocus(!vdg.getShowFocus());
          	 }
          }else if(e.getButton() == MouseEvent.BUTTON2)
          {
          	 Diagram vdg = _mp.getFocusDiagram();
             XaxisFunc xf = vdg.getXaxisFunc();
             int xIdx = ((e.getX() - _mp.Lx)/_mp.xGap);
             if(xIdx >= 0) 
             {
                if(_mp.markIdxS == -1 && _mp.markIdxE == -1)
                {
                   _mp.markIdxS = xIdx;
                   vdg.setEnhanceS(_mp.markIdxS);
                } else if(_mp.markIdxS >= 0 && xIdx > _mp.markIdxS)
                {
                   _mp.markIdxE = xIdx;
                   vdg.setEnhanceE(_mp.markIdxE);
                } else if(_mp.markIdxS > xIdx)
                {
                   _mp.markIdxS = -1;
                   _mp.markIdxE = -1;
                   vdg.setEnhanceS(_mp.markIdxS);
                   vdg.setEnhanceE(_mp.markIdxE);
                }
             }
             System.out.println("X:"+e.getX()+ "xx: "+xIdx);
             System.out.println(xf.getXMark(xIdx));
          }else if(e.getButton() == MouseEvent.BUTTON3)
          {
          	 Diagram dg = _mp.getFocusDiagram();
          	 if(dg != null)
            	  dg.changeFocusGraphLine();
          }
      }
      _mp.updateUI() ;
      _mp.invalidate();
     
   }
   public void mouseDragged(MouseEvent e) 
   {
 //     System.out.println("mouseDragged X:"+e.getX()+" Y:"+e.getY() );
   }
   public void mouseEntered(MouseEvent e) 
   {
 //     System.out.println("mouseEntered X:"+e.getX()+" Y:"+e.getY() );
   }
   public void mouseExited(MouseEvent e) 
   {
 //     System.out.println("mouseExited( X:"+e.getX()+" Y:"+e.getY() );
   }
   public void mouseWheelMoved(MouseWheelEvent e) 
   {
 //      System.out.println("mouseWheelMoved( getUnitsToScroll:"+e.getUnitsToScroll()+" getScrollAmount():"+e.getScrollAmount() );
      if(e.getUnitsToScroll() < 0)
      {
         if(_mp.yRatioCanBeTuned)
         {
         	 if(_mp.ySingleDiagramBeTuned)
         	 {
              _mp.tuneYRatio(_mp.getFocusDiagram(), true);
         	 }else{
              _mp.tuneYRatio(true);
           } 
         }
         else
         {    
            _mp.tuneXGap(1);
         }
      } else {
         if(_mp.yRatioCanBeTuned)
         {
         	 if(_mp.ySingleDiagramBeTuned)
         	 {
              _mp.tuneYRatio(_mp.getFocusDiagram(), false);
         	 }else{
              _mp.tuneYRatio(false);
           } 
         }
         else
         {
            _mp.tuneXGap(-1);
         }
      }
   }
 
}  
  
}