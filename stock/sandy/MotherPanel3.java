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

public class MotherPanel3 extends MPanel {
   
  Diagram[] dgs ;
  public MotherPanel3(int n)
  {
  	 dgs = new Diagram[n];
     dgs[0] = new Diagram(60,20);
     dgs[0].setYNum(0,12);
     this.add(dgs[0]);
  	 for(int i=1; i < n; i++)
  	 {
        dgs[i] = new Diagram(30,20);
        this.add(dgs[i]);
     }
  }
  
  public void setDisplayParameters(double YLowBound1, double YUpBound1,int yValueHight, int index)
  {
         dgs[index].setDisplayParameters(YLowBound1, YUpBound1, yValueHight);
  }
  
  public void addPainting(GraphLine dl, int index)
  {
  	  addPainting(dl, null, index);
  }
 
  
  public void addPainting(GraphLine dl, String LineName, int index)
  {
  	 if(LineName != null)
        dl.setName(LineName);
     
     Diagram vdg = dgs[index];
     if(vdg.getFocusGraphLine() == null)
     {
         if(vdg != dgs[0] && dgs[0].getXaxisFunc()!=null )
         {
            vdg.setXaxisFunc( dgs[0].getXaxisFunc());
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
      Diagram vdg = dgs[index];
      vdg.add(dl);
      vdg.setXaxisFunc(new XaxisFuncByGLIdx(dl));
      updateUI() ;
      invalidate();
  
  }
  
  
}