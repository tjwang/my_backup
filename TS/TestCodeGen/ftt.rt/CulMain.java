import stock.db.*;
import stock.app.*;
import stock.fight.*;
import stock.tool.*;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CulMain {

   static public void main(String[] arg)throws Exception{

//      String doSQLStr = "select date,time,min_vol/100000 as f1 from sta_vol where " +
//                                             "time > 90500 and time < 132500 order by min_vol";
      String doSQLStr = "select date,time,"+arg[0]+" as f1 from "+arg[1]+" where " +
                                             "time > "+arg[2]+" and time < "+arg[3]+" order by "+arg[4];
      ClassCuler cc = new ClassCuler(doSQLStr, 0.95, 300, arg[4]);
      
      System.out.println(cc);
/*
      OneFieldBySQL  of1 = new OneFieldBySQL("select date,time,max_vol/100000 as f1 from sta_vol where " +
                                             "time > 90500 and time < 132500 order by max_vol");
      Line l = of1.culF1Line();
      CulClass_Pattern cp = new CulClass_Pattern(300,(float)l.getMax(),(float)l.getMin());
//      l.dump();
      PatternLine pl = l.pattern(cp);
//      pl.dump();
      
      System.out.println(cp.getFeatureValue(pl,0.95));
      JFrame jf2 = new JFrame("class_view");
      JScrollPane jp2= new JScrollPane();
      MotherPanel3 mp = new MotherPanel3(1);
      Container jc = jf2.getContentPane();
      
      mp.addPainting(new MAPainting(pl,new Color(150,0,0)),"class v",0);
      jc.setLayout(new BoxLayout(jc, BoxLayout.Y_AXIS ) );
      jc.add(jp2);
      jp2.getViewport().add(mp);
      jf2.setSize(850, 700);
      jf2.show();
*/      
   }
}  