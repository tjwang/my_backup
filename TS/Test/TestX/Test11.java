import stock.db.*;
import stock.sandy.*	;
import stock.fight.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test11 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("Test1");
      JFrame jf2 = new JFrame("Test2");
      Test11SKD   skd = new Test11SKD(arg[0]);
      MotherPanel mp = new MotherPanel();
      MotherPanel mp2 = new MotherPanel();
      //KPainting baseLine = new KPainting(skd.culKLine());

//      baseLine.setPaintable(false);
      MALine mal8 = skd.culMALine(8);
      MALine mal33 = skd.culMALine(56);
    //  mp.addPainting(baseLine);
      
      CLine l = (CLine)mal8.cross(mal33,48);
      LineFactory alf =  new SimpleLineFactory(l.LinearExtends());
      LineFactory alf2 = new SimpleLineFactory(l);

      mp.addPainting(new MAPainting(mal8,new Color(0,0,150)));
      mp.addPainting(new MAPainting(mal33,new Color(0,150,150)));
      mp.addPainting(new MAPainting(alf.culMALine(1),new Color(150,0,0)));
//      mp.addPainting(baseLine2);
//      mp.setDisplayParameters(2000,12000,0.2,500,100,13);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

      mp2.addPainting( new MAPainting(mal8,new Color(0,0,150)));
      mp2.addPainting(new MAPainting(alf2.culMALine(1),new Color(150,0,0)));
      JScrollPane jp2= new JScrollPane();
      jp2.getViewport().add(mp2);
      jf2.getContentPane().add(jp2);
      jf2.setSize(850, 700);
      jf2.show();

}

}