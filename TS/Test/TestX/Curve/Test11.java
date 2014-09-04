import stock.db.*;
import stock.sandy.*	;
import stock.fight.*;
import stock.app.*;
import stock.pattern.*;
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
      Frame jf2 = new JFrame("Test2");
      StockKD   skd = new StockKD("0001",arg[0]);
      MotherPanel mp = new MotherPanel();
      MotherPanel mp2 = new MotherPanel();
      KPainting baseLine = new KPainting(skd.culKLine());

//     baseLine.setPaintable(false);
      CurvePattern  cptn = new CurvePattern(40, 0.05);
      MALine mal8 = skd.culMALine(5);
      PatternLine crl = mal8.pattern(cptn);
      PatternLine crl2 = skd.culKLine().pattern(cptn);
      crl.dump();
      System.out.println("=========================================================");
      crl2.dump();
//      MALine mal33 = skd.culMALine(56);
//      mp.addPainting(baseLine);
//      
//      Line l = mal8.cross(mal33,48);
//      AbstractLineFactory alf2 = l.genLineFactory(AbstractLineFactory.SIMPLE);
//      AbstractLineFactory alf = l.genLineFactory(AbstractLineFactory.CXTYPE);
//      KPainting baseLine2 = new KPainting(alf.culKLine());
//      
//      mp.addPainting(new MAPainting(mal8,new Color(0,0,150)));
//      mp.addPainting(new MAPainting(mal33,new Color(0,150,150)));
//      mp.addPainting(new MAPainting(alf.culMALine(1),new Color(150,0,0)));
////      mp.addPainting(baseLine2);
//      mp.setDisplayParameters(2000,12000,0.2,500,100,13);
//      JScrollPane jp= new JScrollPane();
//      jp.getViewport().add(mp);
//      jf.getContentPane().add(jp);
//      jf.setSize(850, 700);
//      jf.show();
//      
//      baseLine2 = new KPainting(alf.culKLine());
//      mp2.addPainting(baseLine2);
//   //   mp2.addPainting(new MAPainting(alf2.culMALine(1),new Color(150,0,0)));
//      JScrollPane jp2= new JScrollPane();
//      jp2.getViewport().add(mp2);
//      jf2.getContentPane().add(jp2);
//      jf2.setSize(850, 700);
//      jf2.show();

}

}