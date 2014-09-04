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

public class TestI5 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("TestI5");
      TestI5Factory   i5f = new TestI5Factory(arg[0],arg[1]);
      StockInfo5  ski5 = new StockInfo5(arg[0],arg[1]);
      StockInfo5  ski5_2 = new StockInfo5(ski5);
      ski5.setupI5(5);
      ski5_2.setupI5(20);
      Line var_l = ski5.getVarLine();
      MotherPanel3 mp = new MotherPanel3(3);
      mp.addPainting(new KPainting(i5f.culKLine()),"K", 0);
      mp.addPainting(new MAPainting(i5f.culMALine(5),new Color(150,0,0)),"MA5",0);
      mp.addPainting(new MAPainting(i5f.culMALine(20),new Color(0,150,0)),"MA20",0);
 //     mp.addPainting(new MAPainting(ski5.getMaxLine().diff(ski5.getMinLine()),new Color(150,0,0)),"I5(Max-Min)",1);
      mp.addPainting(new MAPainting(var_l,new Color(0,150,0)),"I5-Var",1);
      mp.addPainting(new MAPainting(var_l.diff(1),new Color(150,0,0)),"I5-Var-D",1);
      mp.addPainting(new VPainting(i5f.culKLine().getVol(),new Color(0,150,0)),"Val",2);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

}

}