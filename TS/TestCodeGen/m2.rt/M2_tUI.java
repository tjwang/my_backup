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

public class M2_tUI {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 static public void main(String[] arg)throws Exception{

/******************* Source Data Select******************/
      M2_sFactory  fac1 = new M2_sFactory(arg[0],arg[1]);
      M2_oFactory  fac2 = new M2_oFactory(arg[0],arg[1]);

      LineGroup basicGroup =  LG_Maker.basic(fac1,fac2);
      Line markLine =   LG_Maker.markLine(basicGroup);
      Line mainLine =   LG_Maker.mainLine(basicGroup);

/****************User prepare do *****************/
//  To do something
//      ski5.setupI5(5);
//      ski5_2.setupI5(20);
//      Line var_l = ski5.getVarLine();


// Line data building
      Line[] fac1_lines = new Line[3];
      fac1_lines[0] = mainLine;
      fac1_lines[1] = mainLine.avg(5);
      fac1_lines[2] = mainLine.avg(20);

      Line[] fac2_lines = new Line[3];
      Line[] ray = basicGroup.getSubLineArray();
      for(int i=0; i<ray.length&&i<3; i++)
      {
         fac2_lines[i] = ray[i];
      }



/*****************Display*************************/
    JFrame jf = new JFrame("M2_tUI");
    MotherPanel3 mp = new MotherPanel3(2);

//  Region  1 <Factory1>   
     if( fac1_lines[0] instanceof KLine)
     {
         mp.addPainting(new KPainting((KLine)fac1_lines[0]),"Main", 0);
     } else
     {
         mp.addPainting(new MAPainting(fac1_lines[0],new Color(0,0,150)),"Main",0);
     }
      mp.addPainting(new MAPainting(fac1_lines[1],new Color(150,0,0)),"MA5",0);
      mp.addPainting(new MAPainting(fac1_lines[2],new Color(0,150,0)),"MA20",0);
//    mp.addPainting(new VPainting(i5f.culKLine().getVol(),new Color(0,150,0)),"Val",0);

//  Region  2 <Factory1>    
      if( fac2_lines[0] instanceof KLine)
     {
        mp.addPainting(new KPainting((KLine)fac2_lines[0]),"fac2-0", 1);
     } else
     {
         mp.addPainting(new MAPainting(fac2_lines[0],new Color(0,0,150)),"fac2-0",1);
     }
      mp.addPainting(new MAPainting(fac2_lines[1],new Color(150,0,0)),"fac2-1",1);
      mp.addPainting(new MAPainting(fac2_lines[2],new Color(0,150,0)),"fac2-2",1);
//    mp.addPainting(new VPainting(i5f.culKLine().getVol(),new Color(0,150,0)),"Val",1);

/*
//  Region  3 <Factory1>    
      mp.addPainting(new KPainting((KLine)fac3_lines[0]),"K", 2);
      mp.addPainting(new MAPainting(fac3_lines[1],new Color(150,0,0)),"MA5",2);
      mp.addPainting(new MAPainting(fac3_lines[2],new Color(0,150,0)),"MA20",2);
//    mp.addPainting(new VPainting(i5f.culKLine().getVol(),new Color(0,150,0)),"Val",2);
*/
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();

}

}
