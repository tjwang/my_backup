import stock.db.*;
import stock.sandy.*	;
import stock.tool.*	;
import stock.app.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test3A {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 static public void main(String[] arg)throws Exception{

  Ds000_Rec dsr = new Ds000_Rec();
  Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr order by snum;");
  int upCount = 0;
  int downCount = 0;
  while(e.hasMoreElements())
  {
      dsr = (Ds000_Rec)e.nextElement();
      Test3KD   skd = new Test3KD(dsr.snum,arg[0]);
  //    Test3KD   skd2 = new Test3KD(dsr.snum,arg[0],2);
  //    KLine   kl1 = skd.culKLine();
  //    KLine   kl2 = skd2.culKLine();
      skd.culF1Line();
      MALine  mal1 = skd.culMALine(8);
      skd.culF2Line();
      MALine  mal2 = skd.culMALine(8);
      MALine  mal3 = (MALine) mal1.diff(mal2);
      if(mal3.length() > 1)
      {
         MAValue mav11 = (MAValue)mal3.valueAt(mal1.length()-1);
         MAValue mav12 = (MAValue)mal3.valueAt(mal1.length()-2);
         MAValue mav21 = (MAValue)mal3.valueAt(mal2.length()-1);
         MAValue mav22 = (MAValue)mal3.valueAt(mal2.length()-2);
         MAValue mav31 = (MAValue)mal3.valueAt(mal3.length()-1);
         MAValue mav32 = (MAValue)mal3.valueAt(mal3.length()-2);
         if(mav31.getDateValue() == Integer.parseInt(arg[0]))
         {
             if(
                 (mav11.getValue() > mav12.getValue()) &&
                 (mav21.getValue() > mav22.getValue()) 
               )               
             {
             	  Date d = new Date(mav31.getDateTime());
             	  d = new Date(d.getTime()+86400000*3);
                Pamountinfo_Rec pair = new Pamountinfo_Rec();
                pair.SelectBySQL("select * from pamountinfo where date = '"+ GMethod.d2s(d)+"' and snum = '"+dsr.snum+"' ");
                PLast_Rec plr = new PLast_Rec();
//                System.out.println("select * from plast where date = '"+ d2s(d)+"' and snum = '"+dsr.snum+"' ");
                plr.SelectBySQL("select * from plast where date = '"+ GMethod.d2s(d)+"' and snum = '"+dsr.snum+"' ");
                System.out.println(dsr.snum+"-"+mav31.getDateValue()+":"+mav31.getValue()+" : "+ plr.diff);
                if(Float.parseFloat(pair.close) > Float.parseFloat(pair.open))
                {
                	upCount ++;
                }
                if(Float.parseFloat(pair.close) < Float.parseFloat(pair.open))
                {
                	downCount++;
                }
             } 
         }
      }
   }   
   System.out.println("upCount:"+upCount+" downCount:"+downCount);
}

}