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
public class Test23 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("賺大錢Test23 留外資期指");
      Test6VL   svd  = new Test6VL(arg[0]);
      Test6OVL  ovl  = new Test6OVL(arg[0]);
      Test6FBVL   fbvl = new Test6FBVL(arg[0]);
      Test6FSVL   fsvl = new Test6FSVL(arg[0]);
      Test6FBVL   fbvl1 = new Test6FBVL(arg[0],1);
      Test6FSVL   fsvl1 = new Test6FSVL(arg[0],1);
      Test6FBVL   fbvl2 = new Test6FBVL(arg[0],2);
      Test6FSVL   fsvl2 = new Test6FSVL(arg[0],2);
      Test6FBVL   fbvl3 = new Test6FBVL(arg[0],3);
      Test6FSVL   fsvl3 = new Test6FSVL(arg[0],3);
      MotherPanel3 mp = new MotherPanel3(3);
 //     mp.addPainting(new MAPainting(svd.culMALine(1),new Color(0,0,150)),"大盤<1>", 0);
      mp.addPainting(new MAPainting(fsvl2.culMALine(1),new Color(150,0,0)),"大盤<8>", 0);
      mp.addPainting(new MAPainting(fsvl3.culMALine(1),new Color(0,150,0)),"大盤<13>", 0);
//      mp.addPainting(new MAPainting(ovl.culMALine(1),new Color(0,0,150)),"外資累買<1>", 1);
//      mp.addPainting(new MAPainting(ovl.culMALine(8),new Color(150,0,0)),"外資累買<8>", 1);
//      mp.addPainting(new MAPainting(ovl.culMALine(21),new Color(0,150,0)),"外資累買<21>", 1);
//      mp.addPainting(new MAPainting(ovl.culMALine(33),new Color(0,150,150)),"外資累買<33>", 1);
//      mp.addPainting(new MAPainting(ovl.culMALine(55),new Color(150,150,0)),"外資累買<55>", 1);
      mp.addPainting(new MAPainting(fbvl3.culMALine(1),new Color(0,150,0)),"期指均賣<8>", 1);
      mp.addPainting(new MAPainting(fbvl2.culMALine(1),new Color(150,0,0)),"期指累買<8>", 1);
      mp.addPainting(new MAPainting(fsvl.culMALine(8),new Color(0,150,0)),"外資均賣<8>", 2);
      mp.addPainting(new MAPainting(fbvl.culMALine(8),new Color(150,0,0)),"外資累買<8>", 2);
     JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

}

}