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
public class Test10 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("賺大錢Test10 "+arg[1]+"均買點");
      Test10CKD   skd = new Test10CKD(arg[0],arg[1]);
      Test10SKD   skd2 = new Test10SKD(arg[0],arg[1]);
      Test10VL   svd  = new Test10VL(arg[0]);
      MotherPanel mp = new MotherPanel();
      MotherPanel mp2 = new MotherPanel();
      mp.addConnectionPanel(mp2);
      mp2.addConnectionPanel(mp);
      KPainting baseLine = new KPainting(skd.culKLine());
//      baseLine.setPaintable(false);
      mp.addPainting(baseLine);
      mp.addPainting(new MAPainting(skd.culAvgBIdxLine(8),new Color(150,0,0)));
      mp.addPainting(new MAPainting(skd2.culAvgSIdxLine(8),new Color(0,150,0)));
      mp.addPainting(new MAPainting(svd.culMALine(3),new Color(0,0,150)));
//      mp.AutoTuneY();
//      mp.tuneXGap(2);
//      mp.setDisplayParameters(5000,9000,0.2,500,100,13);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();



      JFrame jf2 = new JFrame("賺大錢Test10 "+arg[1]+"均買量");
      Test10FBVL   fbvl = new Test10FBVL(arg[0],arg[1]);
      Test10FSVL   fsvl = new Test10FSVL(arg[0],arg[1]);
      KPainting baseLine2 = new KPainting(fbvl.culKLine());
     // baseLine.setPaintable(false);
      mp2.addPainting(baseLine2);
      mp2.addPainting(new MAPainting(fbvl.culMALine(8),new Color(150,0,0)));
      mp2.addPainting(new MAPainting(fsvl.culMALine(8),new Color(0,150,0)));
   //   mp2.AutoTuneY();
   //   mp2.tuneXGap(2);
    //  mp2.setDisplayParameters(50,500,0.2,25,100,13);
      JScrollPane jp2= new JScrollPane();
      jp2.getViewport().add(mp2);
      jf2.getContentPane().add(jp2);
      jf2.setSize(850, 700);
      jf2.show();

}

}