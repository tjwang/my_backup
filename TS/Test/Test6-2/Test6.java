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
public class Test6 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("�Ȥj��Test6 �~�ꧡ�R�I");
      Test6CKD   skd = new Test6CKD(arg[0]);
      Test6SKD   skd2 = new Test6SKD(arg[0]);
      Test6VL   svd  = new Test6VL(arg[0]);
      Test6FBVL   fbvl = new Test6FBVL(arg[0]);
      Test6FSVL   fsvl = new Test6FSVL(arg[0]);
      MotherPanel2 mp = new MotherPanel2();
      mp.addPainting(new MAPainting(svd.culMALine(4),new Color(0,0,150)),"�j�L", 0);
      mp.addPainting(new MAPainting(skd.culAvgBIdxLine(10),new Color(150,0,0)),"�~�ꧡ�R�I", 0);
      mp.addPainting(new MAPainting(skd2.culAvgSIdxLine(10),new Color(0,150,0)),"�~�ꧡ���I", 0);
      mp.addPainting(new MAPainting(fbvl.culMALine(10),new Color(150,0,0)),"�R�q���u", 1);
      mp.addPainting(new MAPainting(fsvl.culMALine(10),new Color(0,150,0)),"��q���u", 1);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

}

}