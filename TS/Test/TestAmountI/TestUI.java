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

public class TestUI {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("�Ȥj��Test6 �~�ꧡ�R�I");
      TestKD   skd = new TestKD(arg[0],arg[1]);
      TestI2KD   ibkd = new TestI2KD(arg[0],arg[1],5);
      TestI2KD   iskd = new TestI2KD(arg[0],arg[1],6);

      MotherPanel2 mp = new MotherPanel2();
      mp.addPainting(new MAPainting(skd.culMALine(1),new Color(0,0,150)),"�j�L", 0);
      mp.addPainting(new MAPainting(skd.culMALine(8),new Color(150,0,0)),"���R�I", 0);
      mp.addPainting(new MAPainting(skd.culMALine(13),new Color(0,150,0)),"�����I", 0);
      mp.addPainting(new MAPainting(ibkd.culMALine(5),new Color(150,0,0)),"���R�q",1);
      mp.addPainting(new MAPainting(iskd.culMALine(5),new Color(0,150,0)),"����q",1);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

}

}