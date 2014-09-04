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

public class MotherPanel extends MPanel {
   
   Diagram dg ;
   public static void main(String[] arg) throws Exception
   {
      JFrame jf = new JFrame("ÁÈ¤j¿ú");
      //JLabel jl = new JLabel();
//      StockKD   skd = new StockKD("0001",arg[0]);
      StockKD   skd = new StockKD("0001",arg[0]);
      MotherPanel mp = new MotherPanel();
      mp.addPainting(new KPainting(skd.culKLine()));
  //    mp.addDataLine(new KPainting(skd2.culKLine()));
      mp.addPainting(new MAPainting(skd.culMALine(21),Color.BLUE));
      mp.addPainting(new MAPainting(skd.culMALine(8),Color.RED));
      
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();


   }
  public MotherPanel()
  {
     dg = new Diagram(60,60);
     dg.setYNum(0,12);
     this.add(dg);
  }
  
  public void setDisplayParameters(double YLowBound1, double YUpBound1,int yValueHight)
  {
      dg.setDisplayParameters(YLowBound1, YUpBound1, yValueHight);
  }
  
  public void addPainting(GraphLine dl)
  {
  	  addPainting(dl,0, null);
  }
 
  public void addPainting(GraphLine dl, int Shift)
  {
 	    addPainting(dl, Shift, null);
  } 
  
  public void addPainting(GraphLine dl, int Shift, String LineName)
  {
  	 if(LineName != null)
        dl.setName(LineName);
     
     if(dg.getXaxisFunc() == null)
     {
         dg.setXaxisFunc(new XaxisFuncByGLIdx(dl));
     }    
     dg.add(dl);
     updateUI() ;
     invalidate();
  }
  
  public void setBaseLine(GraphLine dl)
  {
//      dg.add(dl);
      dg.setXaxisFunc(new XaxisFuncByGLIdx(dl));
      updateUI() ;
      invalidate();
  
  }
  
  
}