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
public class Test20 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("�Ȥj��Test6 �~�ꧡ�R�I");
      Test6VL   svd  = new Test6VL(arg[0]);
      Test6OVL  ovl  = new Test6OVL(arg[0]);
      Test11SKD skd11 = new Test11SKD(arg[0]);
/*
      MotherPanel3 mp = new MotherPanel3(3);
      mp.addPainting(new MAPainting(svd.culMALine(1),new Color(0,0,150)),"�j�L<1>", 0);
      mp.addPainting(new MAPainting(svd.culMALine(8),new Color(150,0,0)),"�j�L<8>", 0);
      mp.addPainting(new MAPainting(svd.culMALine(13),new Color(0,150,0)),"�j�L<13>", 0);
      mp.addPainting(new MAPainting(ovl.culMALine(1),new Color(0,0,150)),"�~��ֶR<1>", 1);
      mp.addPainting(new MAPainting(ovl.culMALine(8),new Color(150,0,0)),"�~��ֶR<8>", 1);
      mp.addPainting(new MAPainting(ovl.culMALine(21),new Color(0,150,0)),"�~��ֶR<21>", 1);
      mp.addPainting(new MAPainting(ovl.culMALine(33),new Color(0,150,150)),"�~��ֶR<33>", 1);
      mp.addPainting(new MAPainting(ovl.culMALine(55),new Color(150,150,0)),"�~��ֶR<55>", 1);
      mp.addPainting(new MAPainting(skd11.culMALine(1),new Color(0,0,150)),"�ĸ�ֶR<1>", 2);
      mp.addPainting(new MAPainting(skd11.culMALine(8),new Color(150,0,0)),"�ĸ�ֶR<8>", 2);
      mp.addPainting(new MAPainting(skd11.culMALine(21),new Color(0,150,0)),"�ĸ�ֶR<21>", 2);
      mp.addPainting(new MAPainting(skd11.culMALine(33),new Color(0,150,150)),"�ĸ�ֶR<33>", 2);
      mp.addPainting(new MAPainting(skd11.culMALine(55),new Color(150,150,0)),"�ĸ�ֶR<55>", 2);
*/
      Test6FBVL   fbvl = new Test6FBVL(arg[0],1);
      Test6FSVL   fsvl = new Test6FSVL(arg[0],1);
      MotherPanel3 mp = new MotherPanel3(2);
      mp.addPainting(new MAPainting(svd.culMALine(1),new Color(0,0,150)),"�j�L<1>", 0);
      mp.addPainting(new MAPainting(svd.culMALine(8),new Color(150,0,0)),"�j�L<8>", 0);
      mp.addPainting(new MAPainting(svd.culMALine(13),new Color(0,150,0)),"�j�L<13>", 0);
      mp.addPainting(new MAPainting(fbvl.culMALine(5),new Color(150,0,0)),"�~��h��<5>", 1);
      mp.addPainting(new MAPainting(fsvl.culMALine(5),new Color(0,150,0)),"�~��ų�<8>", 1);

     JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

}

}