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

public class Test7 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 

static public void main(String[] arg)throws Exception{

      TestKD   skd = new TestKD("2454",arg[0],1);
      KLine bl = skd.culKLine();
      int neg = 0;
      int pos = 0;
      double posp = 0;
      double negp = 0;
      for(int xi = 100; xi < bl.length() - 21; xi+=10)
      {
         Value vs = bl.valueAt(xi-100);
         Value ve = bl.valueAt(xi);
         Value vc = bl.valueAt(xi+21);
//       Enumeration e = Test.query(arg[0],String.valueOf(v0.getDateValue())).elements();
         Enumeration e = Test2.query2(String.valueOf(vs.getDateValue()),String.valueOf(ve.getDateValue())).elements();
         while(e.hasMoreElements())
         {
             Ds000_Rec ds000r =(Ds000_Rec)e.nextElement();
             TestKD   skd2 = new TestKD(ds000r.snum,arg[0],String.valueOf(vc.getDateValue()));
             KLine kl = skd2.culKLine();  
         	   Value v1 = kl.valueAt(kl.length()-21);
         	   Value v2 = kl.valueAt(kl.length()-1);
             System.out.print("snum "+ds000r.snum+" "+(new String(ds000r.sname.getBytes("ISO8859-1"),"MS950"))+ " "+
                        ds000r.capital+" OK! -->" + v1.getDateValue());
            // v1.dump();           
            // v2.dump();   
             System.out.println("diff: "+(v2.getValue()-v1.getValue())+"percent : " +(v2.getValue()- v1.getValue())/v1.getValue());
             if(v1.getValue() <= 0 ) continue;   
             if(v2.getValue()> v1.getValue())
             {
             	  pos++;
             	  posp += (v2.getValue()- v1.getValue())/v1.getValue();
             }else 
             {
                neg -- ;
                negp += (v2.getValue()- v1.getValue())/v1.getValue();
             }       
          }
      }
      System.out.println("pos "+ pos+ " neg "+ neg);
      System.out.println("posp "+ posp+ " negp "+ negp);
}



}