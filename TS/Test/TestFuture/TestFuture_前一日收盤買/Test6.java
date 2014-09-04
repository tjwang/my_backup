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

public class Test6 {
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

      Test6FBVL   fbvl = new Test6FBVL(arg[0]);
      Test6FSVL   fsvl = new Test6FSVL(arg[0]);
      Test5KD     dK  = new Test5KD(arg[0],0);
      KLine dkl = dK.culKLine();
      int krange = 5;
      for(krange = 5; krange <= 21 ; krange++)
      {
      Line bl = fbvl.culMALine(krange);
      Line sl = fsvl.culMALine(krange);
      Line dl = bl.diff(sl);
      int ll = dl.length();
      float p_count = 0;
      float m_count = 0;
      //v.getDomainValue()
      for(int i = 1; i < ll-2;i++)
      {
         Value _v = dl.valueAt(i-1);
         Value v = dl.valueAt(i);
         Value vv = dl.valueAt(i+1);
         //v.dump();
         
         //if(v.getValue() < _v.getValue() && v.getValue() < 0)
         {
             KValue v1 = (KValue)dkl.valueAt(v.getDomainValue());
             KValue v2 = (KValue)dkl.valueAt(vv.getDomainValue());
             if(v2.getClose() > v1.getClose())
             {
              	  p_count++;
             } else
             {   
                	m_count++;
             }
         }
         //v2.dump();
      }
      System.out.println("Kr:"+ krange+" p_count:"+p_count+" m_count:"+m_count+" P:"+(p_count/(p_count+m_count)));
      //dl.dump();
    }
}

}