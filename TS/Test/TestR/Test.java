import stock.db.*;
import stock.sandy.*	;
import stock.app.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.fight.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("ÁÈ¤j¿úTest");
      StockF   sf = new StockF(arg[0], arg[1], null);
      StockKD   skd = new StockKD(arg[0], arg[1]);
//      MotherPanel2 mp = new MotherPanel2();
      MotherPanel mp = new MotherPanel();

      mp.setDisplayParameters(0,30, 500);
      Line l = sf.culASimpleLine();
//      l = sf.culMALine(8);
      SimpleLineFactory slf = new SimpleLineFactory(l.inverse(0.1,0.9));
      Line xl = slf.genDomainLine(50);
      Line l2 = slf.culSimpleLine();
      ClusterLine cl = (ClusterLine)l2.cluster(xl);
      System.out.println("l2 dump....");
 //     l2.dump();
      Line sl = cl.getSizeLine();
      System.out.println("sl dump....");
//      sl.dump();
      Line dsl = cl.getDomainValueLine();
      System.out.println("dsl dump....");
  //    dsl.dump();
      double cAvg  = dsl.mul(sl).summation().valueAt(sl.length()-1).getValue() / sl.summation().valueAt(sl.length()-1).getValue() ;
      Line cdif = dsl.diff(cl.constant(cAvg));
      double cVar = cdif.mul(cdif).summation().valueAt(sl.length()-1).getValue() / sl.summation().valueAt(sl.length()-1).getValue() ;
      Line pl = cl.getProbLine();

      Line pln0 = pl.normal(l2.inverse().getAvg(), l2.inverse().getVar());
//      System.out.println(cAvg+" "+ cVar);
      Line pln = pl.normal(cAvg, cVar);
//      pl.dump();
//      pln.dump();
      mp.setBaseLine(new MAPainting(xl,Color.BLUE));
      mp.addPainting(new MAPainting(pl.summation(), Color.RED));
      mp.addPainting(new MAPainting(pln.summation(), Color.BLUE));
      mp.addPainting(new MAPainting(pln0.summation(), Color.GREEN));
//      mp.addPainting(new MAPainting(sf.culMALine(8),Color.RED),"R",0);
//      mp.addPainting(new KPainting(skd.culKLine()),"KLine",1);
 
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
      
    
  }

}