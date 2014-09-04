import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import stock.db.*;
import stock.sandy.*	;

public class Test8VL extends SimpleLineFactory{

public  Test8VL(String dateStr, int Amount)throws Exception
 {
   Test_Rec tr = new  Test_Rec();
   Pavgindex_Rec pavr = new Pavgindex_Rec();
 	 String queryStr = null; 
   PLast_Rec plr = new PLast_Rec();
   Enumeration e = pavr.SelectBySQL("select * from pavgindex_of where date > '"+dateStr+"' order by date");
   Vector v = new Vector();
   Vector v2 = new Vector();
   double tAmount = 0;
   double tIC = 0;
   while(e.hasMoreElements())
   {
      v.add(e.nextElement());
   }
   int vsize = v.size() - 1;
   for (int i = 0; i < vsize; i++)
   {
       for(int j=i; j < vsize;j++)
       {
          pavr = (Pavgindex_Rec)v.elementAt(j);
     	    tIC += Float.parseFloat(pavr.Credits);
     	    tAmount += Float.parseFloat(pavr.Amount);
          if (tAmount > Amount)
          {
             pavr = (Pavgindex_Rec)v.elementAt(j);
             System.out.println(pavr.date+"  tIC: "+tIC+"  tAmount:"+tAmount+" AvgIndex: "+(tAmount/tIC));
             add(Integer.parseInt(pavr.date), 0, (tAmount/tIC));
          //   sdate = pavr.date;
             tAmount = 0;
             tIC = 0;
             break;
          }
          
       }
       if (tAmount != 0)
       {
          pavr = (Pavgindex_Rec)v.elementAt(i);
          System.out.println(pavr.date+"  tIC: "+tIC+"  tAmount:"+tAmount+" AvgIndex: "+(tAmount/tIC));
          add(Integer.parseInt(pavr.date), 0, (tAmount/tIC));
          break;
      }

   } 
 }


  static public void main(String[] arg)throws Exception
 {
      JFrame jf = new JFrame("賺大錢Test8 大頭均線");
      Test8VL   skd = new Test8VL(arg[0], Integer.parseInt(arg[1]));
      Test8VL   skd2 = new Test8VL(arg[0], Integer.parseInt(arg[1])*2);
      MotherPanel mp = new MotherPanel();
//      KPainting baseLine = new KPainting(skd.culKLine());
//      baseLine.setPaintable(false);
//      mp.addPainting(baseLine);
//      mp.addPainting(new MAPainting(skd.culMALine(1),new Color(150,0,0)));
      mp.addPainting(new MAPainting(skd.culMALine(3),new Color(150,0,0)));
      mp.addPainting(new MAPainting(skd2.culMALine(3),new Color(0,0,150)));
//      mp.setDisplayParameters(5000,9000,0.2,500,100,13);
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
 }
}
