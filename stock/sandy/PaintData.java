package stock.sandy;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public interface PaintData
{
     public int getX();
     public int getY();
     public void setPaintData(int x, int Zy, int xGap, double yratio, double ybase);
     public void paint(Graphics2D g);
     public void paintDesc(Graphics2D g, int x, int y);
     public String getXMark();
     public long getxIdxValue();
}