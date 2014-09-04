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
public class Test2 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      JFrame jf = new JFrame("ÁÈ¤j¿ú");
      Test2KD   skd = new Test2KD(arg[0],arg[1]);
      Test2KD   skd2 = skd.genClosePriceData();
      MotherPanel mp = new MotherPanel();
      mp.addPainting(new MAPainting(skd.culMALine(8),Color.RED));
      mp.addPainting(new MAPainting(skd2.culMALine(8),Color.BLUE));
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
}

}