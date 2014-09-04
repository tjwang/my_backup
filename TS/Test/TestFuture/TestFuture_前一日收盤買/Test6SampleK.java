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

public class Test6SampleK {
 static Hashtable ht ;
static public void main(String[] arg)throws Exception
{

      Test5KD     dK  = new Test5KD(arg[0],0);
      KLine dkl = dK.culKLine();
      int ll = dkl.length();
      float ccp_count = 0;
      float ccm_count = 0;
      float ccp_diff = 0;
      float ccm_diff = 0;
      float cop_count = 0;
      float com_count = 0;
      float cop_diff = 0;
      float com_diff = 0;
      float ocp_count = 0;
      float ocm_count = 0;
      float ocp_diff = 0;
      float ocm_diff = 0;
      //v.getDomainValue()
      for(int i = 0; i < ll-1;i++)
      {
             KValue v1 = (KValue)dkl.valueAt(i);
             KValue v2 = (KValue)dkl.valueAt(i+1);
             if(v2.getClose() > v1.getClose())
             {
              	  ccp_count++;
              	  ccp_diff += (v2.getClose() - v1.getClose());
             } else
             {   
                	ccm_count++;
              	  ccm_diff += (v2.getClose() - v1.getClose());
             }
             if(v2.getOpen() > v1.getClose())
             {
              	  cop_count++;
              	  cop_diff += (v2.getOpen() - v1.getClose());
             } else
             {   
                	com_count++;
              	  com_diff += (v2.getOpen() - v1.getClose());
             }
             if(v2.getClose() > v2.getOpen())
             {
              	  ocp_count++;
              	  ocp_diff += (v2.getClose() - v2.getOpen());
             } else
             {   
                	ocm_count++;
              	  ocm_diff += (v2.getClose() - v2.getOpen());
             }
      }
      System.out.println("1收盤 < 2收盤: ccp_count:"+ccp_count+"("+ccp_diff+") ccm_count:"+ccm_count+"("+ccm_diff+") P:"+(ccp_count/(ccp_count+ccm_count))+"("+(ccp_diff+ccm_diff)+")");
      System.out.println("1收盤 < 2開盤: cop_count:"+cop_count+"("+cop_diff+") com_count:"+com_count+"("+com_diff+") P:"+(cop_count/(cop_count+com_count))+"("+(cop_diff+com_diff)+")");
      System.out.println("2開盤 < 2收盤: ocp_count:"+ocp_count+"("+ocp_diff+") ocm_count:"+ocm_count+"("+ocm_diff+")  P:"+(ocp_count/(ocp_count+ocm_count)+"("+(ocp_diff+ocm_diff)+")"));
}

}