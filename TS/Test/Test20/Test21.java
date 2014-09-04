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
public class Test21 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("賺大錢Test6 外資均買點");
      Test6VL   svd  = new Test6VL(arg[0]);
      Test6OVL  ovl  = new Test6OVL(arg[0]);
      Test11SKD skd11 = new Test11SKD(arg[0]);
      MotherPanel3 mp = new MotherPanel3(2);
      Line ovlL = ovl.culMALine(1);
      Line skdL = skd11.culMALine(1);
      ovlL = ovlL.percent(ovlL.getMax(),ovlL.getMin());
      skdL = skdL.percent(skdL.getMax(),skdL.getMin());
      mp.addPainting(new MAPainting(svd.culMALine(1),new Color(0,0,150)),"大盤<1>", 0);
      mp.addPainting(new MAPainting(ovlL,new Color(0,0,150)),"外資累買<1>", 1);
      mp.addPainting(new MAPainting(skdL,new Color(0,150,0)),"融資累買<1>", 1);
      mp.addPainting(new MAPainting(ovlL.add(skdL),new Color(150,150,0)),"融資+外資", 1);
     JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

}

}