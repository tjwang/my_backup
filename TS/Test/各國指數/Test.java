import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.fight.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import stock.db.*;
import stock.sandy.*	;
public class Test {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame(arg[0]+" ���u");
      WSKD   skd = new WSKD(arg[0],arg[1]);
      MotherPanel mp = new MotherPanel();
  //    mp.addPainting(baseLine);
      mp.addPainting(new MAPainting(skd.culMALine(1),new Color(150,0,0)));
      mp.addPainting(new MAPainting(skd.culMALine(8),new Color(0,150,0)));
      mp.addPainting(new MAPainting(skd.culMALine(21),new Color(0,0,150)));
      mp.addPainting(new MAPainting(skd.culMALine(33),new Color(0,150,150)));
      mp.addPainting(new MAPainting(skd.culMALine(56),new Color(150,0,150)));
      mp.addPainting(new MAPainting(skd.culMALine(89),new Color(110,60,150)));
   //   mp.setDisplayParameters(500,5000,0.2,500,100,13);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();


}

}