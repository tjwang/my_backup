import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test5 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("ÀðÀY¯óTest5 Buy");
      Test5KD   skd = new Test5KD(arg[0],1);
      MotherPanel mp = new MotherPanel();
      KLine kl1 =  skd.culKLine();
      LineFactory alf = new SimpleLineFactory(kl1);
//      KPainting baseLine = new KPainting(kl2);
//      KPainting baseLine2 = new KPainting();
//      baseLine2.setPaintable(false);
//      baseLine.setPaintable(false);
//      baseLine2.setUpColor(Color.GREEN);
//      baseLine2.setDownColor(Color.GREEN);
      KPainting baseLine = new KPainting(kl1);
      mp.addPainting(baseLine);
//      mp.addPainting(baseLine2);
//      mp.addPainting(new MAPainting(alf.culMALine(3),new Color(150,0,0)));
      mp.addPainting(new MAPainting(alf.culMALine(5),new Color(150,0,0)));
//      mp.addPainting(new MAPainting(alf.culMALine(8),new Color(150,0,0)));
      mp.addPainting(new MAPainting(alf.culMALine(13),new Color(0,150,0)));
 //     mp.addPainting(new MAPainting(alf.culMALine(21),new Color(0,150,0)));
//      mp.addPainting(new MAPainting(skd2.culMALine(8),new Color(0,150,0)));
  //    mp.AutoTuneY();
 //     mp.tuneXGap(20);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
}

}