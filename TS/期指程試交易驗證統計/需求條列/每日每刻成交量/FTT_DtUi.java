import stock.db.*;
import stock.fight.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.app.*	;
import stock.sandy.*	;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class FTT_DtUi {
 
   static private KLine subFrom900(KLine l)
   {
      for(int i=0;i<l.length();i++)
      {
         if(l.valueAt(i).getTimeValue() >= 90000) return (KLine)l.sub(i,l.length()-1);
      }
      return null;
   }
   static public void main(String[] arg)throws Exception{
      Date sDate = GMethod.s2d(arg[0]);
      try
      {
      JFrame jf2 = new JFrame("T30_tUI");
      JScrollPane jp2= new JScrollPane();
      Container jc = jf2.getContentPane();
      jc.setLayout(new BoxLayout(jc, BoxLayout.Y_AXIS ) );
      jc.add(jp2);
      MotherPanel3 mp = new MotherPanel3(2);
          FTT_sFactory   fac1 = new FTT_sFactory("test",arg[0]);
          KLine txl = subFrom900((KLine)fac1.getLineByCode("TX", 0));
          KLine svl = subFrom900((KLine)fac1.getLineByCode("200", 0));
          KLine ssl1 = subFrom900((KLine)fac1.getLineByCode("221", 0));
          KLine ssl2 = subFrom900((KLine)fac1.getLineByCode("221:2", 0));
          KLine ssl130 = subFrom900((KLine)fac1.getLineByCode("130", 0));
          KLine ssl130_2 = subFrom900((KLine)fac1.getLineByCode("130:2", 0));
          KLine ssl131 = subFrom900((KLine)fac1.getLineByCode("131", 0));
          KLine ssl131_2 = subFrom900((KLine)fac1.getLineByCode("131:2", 0));
          Line dl = ssl130.getClose().diff(ssl131.getClose());
          Line dl2 = ssl130_2.getClose().diff(ssl131_2.getClose());
      mp.addPainting(new MAPainting(txl,new Color(150,0,0)),"TX",0);
      mp.addPainting(new MAPainting(dl.percent(dl.getMax(),dl.getMin()),new Color(200,200,0)),"ssl130-ssl131",1);
      mp.addPainting(new MAPainting(dl2.percent(dl2.getMax(),dl2.getMin()),new Color(200,0,200)),"1302-1312",1);
      jp2.getViewport().add(mp);
      jf2.setSize(850, 700);
      jf2.show();
       }catch(Exception xe)
       {
            xe.printStackTrace();
            System.out.println("||||");
       }
   }

}
