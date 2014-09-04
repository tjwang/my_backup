import stock.fight.*;
import stock.pattern.*;
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

public class Test51 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 
static public void report(Line fbl, Line sbl, Line fsl, Line ssl, Line kl)
{
      GeneralBuyPattern gbp = new GeneralBuyPattern(fbl,sbl, fsl,ssl);
      PatternLine pl = kl.pattern(gbp);
      int i = 0;
      int tn = 0;
      int good = 0;
      int ng = 0;
      float total_diff = 0;
      for(i=0; i<pl.length();i+=2)
      {
      	 if(i+1 < pl.length())
      	 {
            Value pv_in = pl.valueAt(i);
            Value pv_out = pl.valueAt(i+1);
            if(pv_in.getDateValue()!= pv_out.getDateValue()) continue;
            float diff = (float)(pv_out.getValue() - pv_in.getValue());
            total_diff += diff;
    //        pv_in.dump();
    //        pv_out.dump();
            if(diff > 3)
            {
    //           System.out.println("Good Tansaction "+tn+" buy "+pv_in.getValue()+" sell:"+pv_out.getValue()
    //                           +" got:"+(diff));
               good++;
            } else
            {
     //          System.out.println("NG Tansaction "+tn+" buy "+pv_in.getValue()+" sell:"+pv_out.getValue()
     //                          +" got:"+(diff));
               ng++;
            }
      //      System.out.println("==========================================================================");                   
            tn++;
         }
      }
      
      System.out.println("Total:"+tn+" Good:"+good+" NG:"+ng+" probality:"+((float)good/(float)tn) +" total diff:"+total_diff);
}

static public void main(String[] arg)throws Exception{

      Test5KD   skd = new Test5KD(arg[0],1);
//      int i = 0;
      KLine kl1 =  skd.culKLine();
      LineFactory alf = new SimpleLineFactory(kl1);

      Line[] fl = new Line[3];
      Line[] sl = new Line[3];
      fl[0] = alf.culMALine(3);
      fl[1] = alf.culMALine(5);
      fl[2] = alf.culMALine(8);
      sl[0] = alf.culMALine(13);
      sl[1] = alf.culMALine(21);
      sl[2] = alf.culMALine(33);
      for(int i=0; i<3; i++)
        for(int j=0; j<3; j++)
          for(int k=0;k<3; k++)
            for(int l=0;l<3;l++)
            {
               System.out.println("i:"+i+" j:"+j+" k:"+k+" l:"+l);
               report(fl[i],sl[j],fl[k],sl[l],kl1);
               System.out.println("==================================");
            }

//
//      int state = 0;
//      Enumeration e = kl1.elements();
//      while(e.hasMoreElements())
//      {
//          KValue kv = (KValue)e.nextElement();
//          DomainValue dv = kv.getDomainValue();
//          float hv = -1;
//          float lv = 999999999;          
//          
//          for(i=0; i < 5; i++)
//          {
//              Value v = ls[i].valueAt(dv);
//              if(v==null) break;
//              if(v.getValue()>hv) hv = (float)v.getValue();
//              if(v.getValue()<lv) lv = (float)v.getValue();
//          }
//          if(i!=5) continue;
//     //     System.out.println(dv + " k: "+v.getValue()+ " m8: "+v8.getValue()+" m13: "+ v13.getValue());
//          /*
//          if(v8 != null && v13 != null)
//          {
//             if(v.getHigh() < v8.getValue())
//             {
//                 state = -1;
//             } else if (v.getClose() > v8.getValue())
//             {
//                 if(state == -1)
//                 {
//                    System.out.println(dv + " k: "+v.getValue()+ " m8: "+v8.getValue()+" m13: "+ v13.getValue());
//                 }
//                 state = 1;
//             }
//          }
//          */
//      }
      
}

}