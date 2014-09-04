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
import stock.app.*	;

public class Test3T {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("ÁÈ¤j¿úTest3 Buy");
      //JLabel jl = new JLabel();
      Test3TKD   skd = new Test3TKD(arg[0],arg[1]);
//      Test3TKD   skd2 = new Test3TKD(arg[0],arg[1],2);
      MotherPanel mp = new MotherPanel();
      skd.culF1Line();
      KPainting baseLine = new KPainting(skd.culKLine());
      skd.culF2Line();
      KPainting baseLine2 = new KPainting(skd.culKLine());
//      baseLine2.setPaintable(false);
//      baseLine.setPaintable(false);
      baseLine2.setUpColor(Color.GREEN);
      baseLine2.setDownColor(Color.GREEN);
      mp.addPainting(baseLine);
      mp.addPainting(baseLine2);
//      mp.addDataLine(new MAPLine(skd,8,new Color(210,0,0)));
      skd.culF1Line();
      mp.addPainting(new MAPainting(skd.culMALine(8),new Color(150,0,0)));
//      mp.addDataLine(new MAPLine(skd,8,Color.BLACK));
//      mp.addDataLine(new MAPLine(skd2,8,new Color(0,200,0)));
      skd.culF2Line();
      mp.addPainting(new MAPainting(skd.culMALine(8),new Color(0,150,0)));
//      mp.addDataLine(new MAPLine(skd2,8,Color.RED));
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
      
}

}