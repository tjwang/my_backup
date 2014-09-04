import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test3 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("賺大錢Test3 Buy");
      MotherPanel mp = new MotherPanel();
      MotherPanel mp2 = new MotherPanel();
      SinKD  smkd = new SinKD(500);

      mp.addConnectionPanel(mp2);
      mp2.addConnectionPanel(mp);
      mp.addPainting(new MAPainting(smkd.culMALine(8),new Color(150,0,0)));
      mp.addPainting(new MAPainting(smkd.culMALine(13),new Color(0,150,0)));
      mp.addPainting(new MAPainting(smkd.culMALine(21),new Color(0,0,150)));

      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
       
      JFrame jf2 = new JFrame("賺大錢Test3 MBuy");
      KDLine kdl = smkd.culKDLine(50,15,5);
      mp2.addPainting(new MAPainting(kdl.getKLine(),new Color(150,0,0)));
      mp2.addPainting(new MAPainting(kdl.getDLine(),new Color(0,150,0)));
      mp2.setDisplayParameters(0, 100, 580);

      JScrollPane jp2= new JScrollPane();
      jp2.getViewport().add(mp2);
      jf2.getContentPane().add(jp2);
      jf2.setSize(850, 700);
      jf2.show();
    
  }

}