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

      JFrame jf = new JFrame("賺大錢Test");
//      StockF   sf = new StockF(arg[0]);
      StockM   sf = new StockM();
      MotherPanel2 mp = new MotherPanel2();
      Line l = sf.culASimpleLine();
      LineFactory blf = sf.getBLF();
      LineFactory slf = sf.getSLF();
      l = sf.culMALine(33);
      Line bl = blf.culMALine(33);
      Line sl = slf.culMALine(33);
//     l.dump();
 //     mp.setBaseLine(new MAPainting(xl,Color.BLUE));
      mp.addPainting(new MAPainting(l, Color.RED),"價格", 0);
      mp.addPainting(new MAPainting(bl, Color.RED),"BL", 1);
      mp.addPainting(new MAPainting(sl, Color.GREEN),"SL", 1);
//      mp.addPainting(new MAPainting(sf.culMALine(8),Color.RED),"R",0);
//      mp.addPainting(new KPainting(skd.culKLine()),"KLine",1);
 
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
      
    
  }

}