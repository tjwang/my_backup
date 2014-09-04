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

public class TestX2 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
  static public boolean checkValue(Line l1, Line l2,Line l3)
  {
     boolean Bflag = true;
     for(int i = 0; i < 13; i++)
     {
        Value v1 = l1.valueAt(i);
        Value v2 = l2.valueAt(i);
        Value v3 = l3.valueAt(i);
        if(v1.getValue() < 0 || v2.getValue()< 0 || v3.getValue()< 0)
        {
            Bflag = false;
        }
     } 
     if( Bflag ) return false;
    for(int i = 20; i < 25; i++)
    {
       Value v1 = l1.valueAt(i);
       Value v2 = l2.valueAt(i);
       Value v3 = l3.valueAt(i);
        if(v1.getValue() < 0 || v2.getValue()< 0 || v3.getValue()< 0)
       {
           return false;
       }
    }  
     return true;
  }
 
static public void main(String[] arg)throws Exception{
      Ds000_Rec ds000r =new Ds000_Rec();
      Enumeration e = ds000r.SelectBySQL("select * from ds000  order by snum");
      int total = 0;
      int got = 0;
      while(e.hasMoreElements())
      
//      for(int k = 0;k < check_snum.length; k++)
      {
      	 total++;
         ds000r=(Ds000_Rec)e.nextElement();
 //        TestKD   skd = new TestKD(ds000r.snum,arg[0],arg[1],100);
 ///        TestKD   skd = new TestKD(check_snum[k],arg[0],1);
         TestKD   skd = new TestKD(ds000r.snum,arg[0],1);
         MALine m5 =  skd.culMALine(5);
         MALine m8 =  skd.culMALine(8);
         MALine m13 =  skd.culMALine(13);
         MALine m21 =  skd.culMALine(21);
         if(m21.length() < 25) continue;
         Line sub_m5 = m5.sub(m5.length()-25,m5.length()-1);
         Line sub_m8 = m8.sub(m8.length()-25,m8.length()-1);
         Line sub_m13 = m13.sub(m13.length()-25,m13.length()-1);
         Line sub_m21 = m21.sub(m21.length()-25,m21.length()-1);
         Line dif_1 = sub_m5.diff(sub_m8);
         Line dif_2 = sub_m8.diff(sub_m13);
         Line dif_3 = sub_m13.diff(sub_m21);
        if(checkValue(dif_1,dif_2,dif_3))
         {
             
             System.out.println(" snum "+ds000r.snum+" "+(new String(ds000r.sname.getBytes("ISO8859-1"),"MS950"))+ " "+ds000r.capital+" OK! -->" + sub_m8.valueAt(0).getDateValue());
//            System.out.println("snum "+check_snum[k]+" OK! -->" + sub_m8.valueAt(0).getDateValue());
            got ++;
         }   
      }
      System.out.println("toatl "+ total + " got "+ got);   
}



}