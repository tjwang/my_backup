import stock.db.*;
import stock.sandy.*	;
import stock.app.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.fight.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("ÁÈ¤j¿úTest");
      StockF   sf = new StockF("0001", arg[0], null);
      MotherPanel2 mp = new MotherPanel2();

//      mp.setDisplayParameters(0,30, 500);
      Line fl = sf.culFSimpleLine(1);
      Line rl = sf.culRSimpleLine();
//      Line l = sf.culXSimpleLine();
//      Line l2 = sf.culXSimpleLine2();
      StockF2 sf2 = new StockF2(rl);
      Line l3 = sf2.culVLine(20);
//      System.out.println(l2.getAvg());
//      l = l.add(l2.constant(9036));
//      l = sf.culMALine(8);
//      mp.addPainting(new MAPainting(sf.culVLine(), Color.RED),"raw data", 0);
      mp.addPainting(new MAPainting(sf.culMALine(33), Color.RED),"raw data", 0);
      mp.addPainting(new MAPainting(l3, Color.RED),"f data", 1);
 //     mp.addPainting(new MAPainting(l2, Color.GREEN),"f base data", 1);
//      mp.addPainting(new MAPainting(sf.getBLF().culMALine(33), Color.RED),"BL", 1);
//      mp.addPainting(new MAPainting(sf.getSLF().culMALine(33), Color.GREEN),"SL", 1);
//      mp.setDisplayParameters(0,1300, 500,1);

      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
      
    
  }

}