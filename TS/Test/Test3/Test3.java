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
public class Test3 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("賺大錢Test3 Buy");
      Test3KD   skd = new Test3KD(arg[0],arg[1]);
      MotherPanel mp = new MotherPanel();
      MotherPanel mp2 = new MotherPanel();

      mp.addConnectionPanel(mp2);
      mp2.addConnectionPanel(mp);

      skd.culF1Line();
      mp.addPainting(new MAPainting(skd.culMALine(8),new Color(150,0,0)));
      skd.culF2Line();
      mp.addPainting(new MAPainting(skd.culMALine(8),new Color(0,150,0)));
 
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
      
      JFrame jf2 = new JFrame("賺大錢Test3 MBuy");
      Test3MKD   smkd = new Test3MKD(arg[0],arg[1]);
      
      smkd.culF1Line();
      mp2.addPainting(new MAPainting(smkd.culMALine(8),new Color(150,0,0)));
      smkd.culF2Line();
      mp2.addPainting(new MAPainting(smkd.culMALine(8),new Color(0,150,0)));
 
      JScrollPane jp2= new JScrollPane();
      jp2.getViewport().add(mp2);
      jf2.getContentPane().add(jp2);
      jf2.setSize(850, 700);
      jf2.show();
    
  }

}