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

public class TestI5_2 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("TestI5_2");
      TestI5Factory   i5f = new TestI5Factory(arg[0],arg[1]);
      StockInfo5  ski5 = new StockInfo5(arg[0],arg[1]);
      StockInfo5  ski5_2 = new StockInfo5(ski5);
      ski5.setupI5(5);
      ski5_2.setupI5(20);
      Line i5_av =ski5.getMCntLine();
      Line i5_midL =i5f.culMALine(5);
      Line i5_av_d_midL = i5_av.diff(i5_midL);
      Line ma5_l =  ski5.getMRaLine();// i5f.culMALine(5);
      if(ma5_l.length() > i5_av.length())
      {
         ma5_l = ma5_l.sub(ma5_l.length()-i5_av.length(),ma5_l.length()-1);
      } else
      {
         i5_av = i5_av.sub(i5_av.length()-ma5_l.length(),i5_av.length()-1);
      }
      MotherPanel3 mp = new MotherPanel3(2);
      mp.addPainting(new KPainting(i5f.culKLine()),"K", 0);
      mp.addPainting(new MAPainting(ma5_l,new Color(150,0,0)),"MA5",0);
      mp.addPainting(new MAPainting(i5f.culMALine(20),new Color(0,150,0)),"MA20",0);
 //     mp.addPainting(new MAPainting(ski5.getMaxLine().diff(ski5.getMinLine()),new Color(150,0,0)),"I5(Max-Min)",1);
//      mp.addPainting(new MAPainting(i5_av,new Color(0,150,0)),"MaxC5",1);
//      mp.addPainting(new MAPainting(ski5.getMidLine(),new Color(150,0,0)),"Mid5",1);
//      mp.addPainting(new MAPainting(i5_av.diff(ma5_l).mul(i5_av.constant(10)),new Color(150,0,0)),"I5Avg",1);
      mp.addPainting(new MAPainting(i5_av_d_midL.mul(i5_av_d_midL.constant(10)).diff(1).macd(3,2),new Color(150,0,0)),"I5Avg",1);
      mp.addPainting(new MAPainting(i5_av.constant(0),new Color(0,150,0)),"I5Avg",1);
 //     mp.addPainting(new VPainting(i5f.culKLine().getVol(),new Color(0,150,0)),"Val",2);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

}

}