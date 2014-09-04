package stock.app;
import stock.sandy.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import stock.db.*;

public class PriceVPanel extends MPanel {
   
   FiveSeries _fs = null;
   SimpleLine _fl = null;
   ClusterLine _cfl = null;
   StockSeries _ss = null;
   ClusterLine _csl = null;
   Diagram dg  = null;
   Diagram dg2 = null;
   float basep = 0;
   
   public static void main(String[] arg) throws Exception
   {
      JFrame jf = new JFrame("寥窥");
      PriceVPanel pvp = new PriceVPanel(arg[0], arg[1]);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(pvp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
   }
   
   public PriceVPanel(String snum, String dateStr) throws Exception
   {

      dg = new Diagram(30,20);
      _ss = new StockSeries(snum, dateStr);
      _fs = new FiveSeries(snum, dateStr);
      _fl = _fs.culSimpleLine();
      KLine kl = _ss.culKLine();
      MALine mal = _ss.culMALine(1);
      MALine vmal = _ss.culVolMALine(1);
      Line tl = _ss.genTimeLine(60);
      _csl  = (ClusterLine)kl.cluster(tl);
      ClusterLine cl1 = (ClusterLine)mal.cluster(tl);
      ClusterLine cl2 = (ClusterLine)vmal.cluster(tl);
      _cfl = _fl.cluster(tl);
      
      Value v = mal.valueAt(0);
      basep = _ss.getBasePrice();
      XaxisFunc  xf = new XaxisFuncByGLIdx(new PVPainting(_csl,Color.BLUE));
 
      dg.setXaxisFunc(xf);
      dg.add(new PVPainting(cl1,Color.BLUE));
      System.out.println("basep"+basep);
      dg.setYNum(4,4);
      dg.setDisplayParameters(RoundPrice(basep * 0.93), RoundPrice(basep * 1.07), 350);
      dg.setZyValue(basep);
      dg.setyGapValue(RoundGrap((basep * 0.07) / 4, basep));
      this.add(dg);
 
      dg2 = new Diagram(30,20);
      dg2.setXaxisFunc(xf);
      dg2.setYNum(0,4);
      dg2.add(new VPainting(cl2.getSumValueLine(),Color.RED));
      dg2.setDisplayParameters(250);
      dg2.setYLowBound(0);
      this.add(dg2);
      
   }
  
  static double RoundPrice(double p)
  {
  	 double roundP = 0;
     if(p > 100)
     {
       roundP = 0.5 * (int)(p / 0.5);//y笷计程(笷)
     } else  if(p > 50)
     {
       roundP = 0.1 * (int)(p / 0.1);
     } else if ( p > 10)
     {
       roundP = 0.05 * (int)(p / 0.05);
     } else {
       roundP = 0.01 * (int)(p / 0.01);
     }
     return roundP;
  }
  
  static double  RoundGrap(double p, double pbase)
  {
  	 double roundP = 0;
     if(pbase > 100)
     {
       roundP =  ((int)(p / 0.5)) * 0.5;//y笷计程(笷)
       
     } else  if(pbase > 50)
     {
       roundP = ((int)(p / 0.1)) * 0.1;
     } else if ( pbase > 10)
     {
       roundP = ((int)(p / 0.05)) * 0.05;
     } else {
       roundP = ((int)(p / 0.01)) * 0.01;
     }
     return roundP;
  }
  
  
  private class  PVPainting extends MAPainting
  {
  	     private ClusterLine _l = null;
  	     
         public PVPainting(ClusterLine l,Color c)
         {
            super(l,c);
            _l = l;
            _paintdata = new PaintData[_l.length()];
            for(int i = 0; i < _paintdata.length; i++)
            {
            	   ClusterValue v = (ClusterValue)_l.valueAt(i);
            	   if(v.getLastValue() > maxValue)
            	   {
            	       maxValue = v.getLastValue();
            	   }
            	   if(v.getLastValue() < minValue)
            	   {
            	       minValue = v.getLastValue();
            	   }
                 _paintdata[i] = new PVElement(v);
            }
            setName("Price");
         }
   
  }
  
  private class PVElement extends MAElement
  {
  	     private ClusterValue _tcv = null;
  
         public PVElement(ClusterValue tcv)
         {
            super(tcv);
            _tcv = tcv;
         }
                
         public void paintDesc(Graphics2D g, int x, int y)
         {
             super.paintDesc(g, x, y);
             int vy = y + 55;
             Enumeration e = _tcv.elements();
             while(e.hasMoreElements())
             {
                 KValue kv = (KValue)e.nextElement();
                 g.setColor(Color.BLACK);
                 g.drawString(String.format("%tT", new Date(kv.getDateTime()))+ " " + 
                              String.format("%1$.2f",kv.getClose()) + " " + (int)kv.getVolume()
                              , getX() + 10, vy);
                 float diff =   (float)(kv.getClose() - basep);
                 if(diff > 0)
                 {
                     g.setColor(Color.RED);
                     g.drawString( "+"+String.format("%1$.2f", diff) , getX() + 118, vy);
                 } else if (diff < 0)
                 {
                     g.setColor(Color.GREEN);
                     g.drawString( String.format("%1$.2f", diff) , getX() + 118, vy);
                 } else {
                     g.drawString( String.format("%1$.2f", diff) , getX() + 118, vy);
                 }           
                 vy +=15; 
             }
             g.setColor(new Color(122,150,223));
             y += 30;
             if (vy - y < 110)
             {
                 vy = y+110;
             }
             g.drawRect(getX() + 5, y, 150 , vy - y);
             
             y = vy;
    //         int cTcfIdx = _csl.mappingIdx(_tcv);
      //       System.out.println("cTcfIdx :"+cTcfIdx);
             ClusterValue vtcf =  (ClusterValue)_cfl.valueAt(_tcv.getDomainValue());
             if (vtcf != null)
             {        
                
                e = vtcf.elements();
                int vx = getX() + 5;
                while(e.hasMoreElements())
                {
                    FiveElement fe = (FiveElement) e.nextElement();
                    PFive_Rec pfive = fe.getRpData();
                
                    g.setColor(new Color(122,150,223));
                    g.drawRect(vx, y, 150, 180);
                    PFive_Rec[] pfiveSells = fe.getSellData();
                    int i = 0;
                    for(int j = pfiveSells.length - 1; j >= 0; j--) 
                    {
                      if(pfiveSells[j] != null)
                      {
                         g.drawString(pfiveSells[j].rp,vx+5,y+15+i*15);
                         g.drawString("/  "+pfiveSells[j].ra,vx+45,y+15+i*15);
                      }
                      i++;
                    }
                    PFive_Rec[] pfiveBuys = fe.getBuyData();
                    for(int j = 0 ; j < pfiveBuys.length; j++) 
                    {
                       pfive = pfiveBuys[j];
                       if(pfive!=null)
                       {
                         g.drawString( pfive.rp,vx+70,y+90+j*15);
                         g.drawString("/  "+pfive.ra,vx+110,y+90+j*15);
                       }  
                    }
                    g.setColor(Color.RED);
                    g.drawString(String.format("%tT", new Date(fe.getDateTime())),vx+40,y+165);
                    vx +=150;
                }
             }
         }
         
         public String getXMark()
         {
         	  return String.format("%tT", new Date(_tcv.getDateTime()));
         }
         
  }  

}