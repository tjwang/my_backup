import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.pattern.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestX7 {
static public void main(String[] arg)throws Exception{
      TestKD   skd = new TestKD("2454",arg[0],1);
      KLine kl = skd.culKLine();
      MALine m5 =  skd.culMALine(5);
  //    MALine m13 =  skd.culMALine(13);
 //     m5.dump();
      kl.avg(5).dump();
      kl.getVol().dump();
}
static	Hashtable check_snum = null;

static void init_check(String filename) throws Exception
{
      BufferedReader d
          = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
      String snum = null;
      check_snum = new Hashtable();
      while((snum=d.readLine())!=null)
      {
         check_snum.put(snum.trim(),snum.trim());
      }
}


}