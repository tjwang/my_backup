import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test611 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      MotherPanel3 mp2 = new MotherPanel3(3);
      JFrame jf2 = new JFrame("賺大錢Test6 外資均量");
      Test6OVL   ovl = new Test6OVL("");
      Test6USDrL  rate_all = new Test6USDrL("20050101");
      Test6USDrL  rate_usd = new Test6USDrL("20050101",true);
//      KPainting baseLine2 = new KPainting(ovl.culKLine());
//      baseLine2.setPaintable(false);
//      mp2.addPainting(baseLine2);
      mp2.addPainting(new MAPainting(ovl.culMALine(1),new Color(150,0,0)),"外資累買", 0);
      mp2.addPainting(new MAPainting(ovl.culMALine(8),new Color(0,150,0)),"外資累買", 0);
      mp2.addPainting(new MAPainting(ovl.culMALine(21),new Color(0,0,150)),"外資累買", 0);
      mp2.addPainting(new MAPainting(ovl.culMALine(33),new Color(0,150,150)),"外資累買", 0);
       mp2.addPainting(new MAPainting(rate_all.culMALine(1),new Color(150,0,0)),"綜合匯率", 1);
      mp2.addPainting(new MAPainting(rate_all.culMALine(8),new Color(0,150,0)),"綜合匯率", 1);
      mp2.addPainting(new MAPainting(rate_all.culMALine(21),new Color(0,0,150)),"綜合匯率", 1);
       mp2.addPainting(new MAPainting(rate_usd.culMALine(1),new Color(150,0,0)),"美元匯率", 2);
      mp2.addPainting(new MAPainting(rate_usd.culMALine(8),new Color(0,150,0)),"美元匯率", 2);
      mp2.addPainting(new MAPainting(rate_usd.culMALine(21),new Color(0,0,150)),"美元匯率", 2);
//     mp2.AutoTuneY();
 //     mp2.tuneXGap(2);
   //   mp2.setDisplayParameters(-25000,25000,0.2,25,1000,13);
      JScrollPane jp2= new JScrollPane();
      jp2.getViewport().add(mp2);
      jf2.getContentPane().add(jp2);
      jf2.setSize(850, 700);
      jf2.show();



}

}