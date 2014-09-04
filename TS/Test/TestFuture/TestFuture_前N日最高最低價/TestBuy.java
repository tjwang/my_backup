import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TestBuy extends StockKD{


public  TestBuy(String dateStr,int type)throws Exception
 {
   PLDayk_Rec plr = new PLDayk_Rec();
 //  Enumeration e = plr.SelectBySQL("select * from ptxdayk where  date>'"+dateStr+"' order by date,time;");
   Enumeration e = plr.SelectBySQL("select * from ptxdayk where  date>'"+dateStr+"' order by date,time;");
   while(e.hasMoreElements())
   {
   	   add((PLDayk_Rec)e.nextElement());
   }
 }
static public void main(String[] arg)throws Exception
{

      Test5KD     dK  = new Test5KD(arg[0],0);
      KLine dkl = dK.culKLine();
      TimePattern tp = new TimePattern(50000);
      int lenpattern_len = 1;
      int cutoff = 20;
      int gotoff = 20;
      int diff = 0;
      for(diff = -50; diff < 60; diff+=10)
      for(cutoff = 20; cutoff < 60; cutoff+=10)
      for(gotoff = 20; gotoff < 60; gotoff+=10)
      for(lenpattern_len = 1; lenpattern_len < 3;lenpattern_len++)
      {
         LenPattern tp2 = new LenPattern(lenpattern_len);
         Line l= dkl.pattern(tp);
         int g_count = 0;
         int ng_count = 0;
         int g_value = 0;
         int ng_value = 0;
         for(int i=lenpattern_len;i<l.length();i++)
         {
            PatternValue pv = tp2.backwardFind(l,i-1);
            HPricePattern hp = new HPricePattern((int)pv._kv.getHigh()+diff,cutoff,gotoff,9999);
            PatternValue tv = (PatternValue)l.valueAt(i);
            Line tl = tv.toLine();
            Line gotl = tl.pattern(hp);
            for(int j=0;j<gotl.length(); j++)
            {
               PatternValue mmv = (PatternValue)gotl.valueAt(j);
               // mmv.dump();
               if(mmv.getValue() > 0)
               {
                  g_count ++;
                  g_value += (int)mmv.getValue();
               } else
               {
                  ng_count++;
                  ng_value += (int)mmv.getValue();
               }
            }
          /*
           System.out.println("v dump============================================");
          v.dump();
           System.out.println("pv dump============================================");
          pv.dump();
           System.out.println("-----------------------------------------------------------");
          */
         }  
         System.out.println("diff:"+diff+" lenpattern_len: "+lenpattern_len+" -- cutoff:"+cutoff+" -- gotoff:"+gotoff+"---------------------------");
         System.out.println("g_count:"+g_count+" g_value:"+g_value+" ng_count:"+ng_count+" ng_value:"+ng_value
                           +" total count:"+(float)g_count/(float)(g_count+ng_count) + " total value:"+(ng_value+g_value) );
      }
     /* 
      Line pl =((PatternValue)l.valueAt(l.length()-1)).toLine();
      pl.dump();
      System.out.println("============================================");
      Line l2= l.pattern(tp2);
      l2.dump();     
      System.out.println("============================================");
      pl =((PatternValue)l2.valueAt(l2.length()-1)).toLine();
      pl.dump();
      */
}

}
