import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test6 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      MotherPanel mp2 = new MotherPanel();
      JFrame jf2 = new JFrame("賺大錢Test6 外資均量");
      Test6FBVL   fbvl = new Test6FBVL("");
//      KPainting baseLine2 = new KPainting(fbvl.culKLine());
//      baseLine2.setPaintable(false);
//      mp2.addPainting(baseLine2);
      mp2.addPainting(new MAPainting(fbvl.culMALine(1),new Color(150,0,0)));
      mp2.addPainting(new MAPainting(fbvl.culMALine(8),new Color(0,150,0)));
      mp2.addPainting(new MAPainting(fbvl.culMALine(21),new Color(0,0,150)));
      mp2.addPainting(new MAPainting(fbvl.culMALine(33),new Color(0,150,150)));
  //    mp2.AutoTuneY();
 //     mp2.tuneXGap(2);
      mp2.setDisplayParameters(50,5000,580);
      JScrollPane jp2= new JScrollPane();
      jp2.getViewport().add(mp2);
      jf2.getContentPane().add(jp2);
      jf2.setSize(850, 700);
      jf2.show();



}

}