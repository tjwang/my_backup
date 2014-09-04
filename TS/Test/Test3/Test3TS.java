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
public class Test3TS {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("±j¿YØÛTest3TS");
      Test3TKD   skd = new Test3TKD(arg[0],arg[1]);
//      Test3TKD   skd2 = new Test3TKD(arg[0],arg[1],2);
      
      Line kl1 =  skd.culF1Line();
      Line kl2 =  skd.culF2Line();
      Line kl3 = (Line)kl1.diff(kl2);
      LineFactory alf = new SimpleLineFactory(kl3);
      MotherPanel mp = new MotherPanel();
//      KPainting baseLine = new KPainting(kl3);
//      KPainting baseLine2 = new KPainting(skd2.culKLine());
//      baseLine2.setPaintable(false);
//      baseLine.setPaintable(false);
//      baseLine2.setUpColor(Color.GREEN);
//      baseLine2.setDownColor(Color.GREEN);
//      mp.addPainting(baseLine);
  //    mp.addPainting(baseLine2);
//      mp.addDataLine(new MAPLine(skd,8,new Color(210,0,0)));
      mp.addPainting(new MAPainting(alf.culMALine(8),new Color(150,0,0)));
      mp.addPainting(new MAPainting(alf.culMALine(21),new Color(0,150,0)));
      mp.addPainting(new MAPainting(alf.culMALine(55),new Color(0,0,150)));
//      mp.addDataLine(new MAPLine(skd,8,Color.BLACK));
//      mp.addDataLine(new MAPLine(skd2,8,new Color(0,200,0)));
 //     mp.addPainting(new MAPainting(skd2.culMALine(8),new Color(0,150,0)));
//      mp.addDataLine(new MAPLine(skd2,8,Color.RED));
//      mp.AutoTuneY();
//      mp.tuneXGap(20);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
      
}

}