package stock.sandy;
import java.awt.*;
import javax.swing.*;
public interface GraphLine
{
	     public void setElementPaintData(long xIdxValue, int x, int Zy, int xGap, double yratio, double ybase);
       public void paint(Graphics2D g, Rectangle rtv);
       public PaintData getPaintData(long xIdxValue) ;
       public PaintData getPaintDataByIdx(int Idx) ;
       public PaintData getPaintDataByXPos(int x) ;
       public int getSize() ;
//	     public boolean isDataSeted();
//	     public void setDataSeted(boolean b);
	     public double getMaxValue();
	     public double getMinValue();
	     public String getName();
	     public void setName(String n);
	     public void setEnhanceS(long xIdxValue);
	     public void setEnhanceE(long xIdxValue);
	     public void setFocusStroke(Stroke sk);
	     
}