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

public class Test6P {
 static Hashtable ht ;
static public void main(String[] arg)throws Exception
{

      Test5KD     dK  = new Test5KD(arg[0],0);
      KLine dkl = dK.culKLine();
      int ll = dkl.length();
      for(int j = 0; j < ll-22;j++)
      {
          float cop_count = 0;
          float com_count = 0;
          float cop_diff = 0;
          float com_diff = 0;
          for(int i=j;i<j+20;i++)
          {   
             KValue v1 = (KValue)dkl.valueAt(i);
             KValue v2 = (KValue)dkl.valueAt(i+1);
             if(v2.getOpen() > v1.getClose())
             {
              	  cop_count++;
              	  cop_diff += (v2.getOpen() - v1.getClose());
             } else
             {   
                	com_count++;
              	  com_diff += (v2.getOpen() - v1.getClose());
             }
          }
          System.out.println(""+dkl.valueAt(j).getDateValue()+" cop_count:"+cop_count+"("+cop_diff+") com_count:"+com_count+"("+com_diff+") P:"+(cop_count/(cop_count+com_count))+"("+(cop_diff+com_diff)+")");
      }
}

}