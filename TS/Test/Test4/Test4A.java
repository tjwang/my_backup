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
public class Test4A {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("ÁÈ¤j¿úTest4A Buy");
      Test4AKD   skd = new Test4AKD(arg[0],arg[1],1);
      Test4AKD   skd2 = new Test4AKD(arg[0],arg[1],2);
      MotherPanel mp = new MotherPanel();
      KPainting baseLine = new KPainting(skd.culKLine());
      KPainting baseLine2 = new KPainting(skd2.culKLine());
//      baseLine2.setPaintable(true);
//      baseLine.setPaintable(true);
      baseLine2.setUpColor(Color.GREEN);
      baseLine2.setDownColor(Color.GREEN);
      mp.addPainting(baseLine);
      mp.addPainting(baseLine2);
      mp.addPainting(new MAPainting(skd.culMALine(8),new Color(150,0,0)));
      mp.addPainting(new MAPainting(skd2.culMALine(8),new Color(0,150,0)));
  //    mp.AutoTuneY();
  //    mp.tuneXGap(20);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
}

}