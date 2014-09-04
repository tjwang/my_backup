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

public class MotherPanel2 extends MPanel {
   
  Diagram dg ;
  Diagram dg2 ; 
  public MotherPanel2()
  {
     dg = new Diagram(60,20);
     dg.setYNum(0,12);
     this.add(dg);
     dg2 = new Diagram(30,20);
     this.add(dg2);
  }
  
  public void setDisplayParameters(double YLowBound1, double YUpBound1,int yValueHight, int index)
  {
      if(index == 0)
         dg.setDisplayParameters(YLowBound1, YUpBound1, yValueHight);
    
      if(index == 1)
         dg2.setDisplayParameters(YLowBound1, YUpBound1, yValueHight);
  }
  
  public void addPainting(GraphLine dl, int index)
  {
  	  addPainting(dl, null, index);
  }
 
  
  public void addPainting(GraphLine dl, String LineName, int index)
  {
  	 if(LineName != null)
        dl.setName(LineName);
     
     Diagram vdg = dg;
     if(index == 1)
     {
        vdg = dg2;
     }
     if(vdg.getFocusGraphLine() == null)
     {
         if(vdg == dg2 && dg.getXaxisFunc()!=null )
         {
            vdg.setXaxisFunc( dg.getXaxisFunc());
         }else
         {
            vdg.setXaxisFunc(new XaxisFuncByGLIdx(dl));
         }
     }    
     vdg.add(dl);
     updateUI() ;
     invalidate();
  }
  
  public void setBaseLine(GraphLine dl, int index)
  {
      Diagram vdg = dg;
      if(index == 1)
      {
        vdg = dg2;
      }
      vdg.add(dl);
      vdg.setXaxisFunc(new XaxisFuncByGLIdx(dl));
      updateUI() ;
      invalidate();
  
  }
  
  
}