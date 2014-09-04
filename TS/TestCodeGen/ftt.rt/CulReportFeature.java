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
import java.sql.*;

public class CulReportFeature {

   static public void main(String[] arg)throws Exception{

      
      String deleSQLStr = "delete from feature_value where f_name = '" +arg[5]+"'";
      String doSQLStr = "select date,time,"+arg[0]+" as f1 from "+arg[1]+" where " +
                                             "time > "+arg[2]+" and time < "+arg[3]+" order by "+arg[4];
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate(deleSQLStr);
      ClassCuler cc = new ClassCuler(doSQLStr, 0.95, 300, arg[5]);
      
      System.out.println(cc);
      System.err.println(GMethod.d2s(new java.util.Date())+"|"+cc);
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