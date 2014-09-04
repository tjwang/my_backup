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

public class Test6x {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("賺大錢Test6 外資均買點");
      Test6CKD   skd = new Test6CKD(arg[0],arg[1]);
      Test6SKD   skd2 = new Test6SKD(arg[0],arg[1]);
      Test6VL   svd  = new Test6VL(arg[0]);

      Test6FBVL   fbvl = new Test6FBVL(arg[0],arg[1]);
      Test6FSVL   fsvl = new Test6FSVL(arg[0],arg[1]);
      MotherPanel2 mp = new MotherPanel2();
      mp.addPainting(new MAPainting(svd.culMALine(4),new Color(0,0,150)),"大盤", 0);
      mp.addPainting(new MAPainting(skd.culAvgBIdxLine(10),new Color(150,0,0)),"均買點", 0);
      mp.addPainting(new MAPainting(skd2.culAvgSIdxLine(10),new Color(0,150,0)),"均賣點", 0);
      mp.addPainting(new MAPainting(fbvl.culMALine(10),new Color(150,0,0)),"均買量",1);
      mp.addPainting(new MAPainting(fsvl.culMALine(10),new Color(0,150,0)),"均賣量",1);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

}

}