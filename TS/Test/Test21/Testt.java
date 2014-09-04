import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Testt {

  public static void main(String[] arg) throws Exception
  {
      System.out.println("=============");
      F2_Rec f2r = new F2_Rec();
      F2_Rec f2r2 = new F2_Rec();
      F3_Rec f2r3 = new F3_Rec();
      F2_Rec pref2r = null;
      F2_Rec pref2r2 = null;
      Enumeration<F2_Rec> e =  f2r.SelectBySQL("select date, 0 as time, (callM * 5)/pxfsum.call as f1, (putM * 5)/pxfsum.put as f2 from pxfsum where  kind= 'TXF' and type='F' order by date");
      Enumeration<F2_Rec> e2 =  f2r2.SelectBySQL("select date, 0 as time,(TcallM * 5)/pxfsum.Tcall as f1, (TputM * 5)/pxfsum.Tput as f2 from pxfsum where  kind= 'TXF' and type='F' order by date");
      Enumeration<F3_Rec> e3 =  f2r3.SelectBySQL("select date, 0 as time, pxfsum.Tcall as f1, pxfsum.Tput as f2,pxfsum.Tcpsum as f3 from pxfsum where  kind= 'TXF' and type='F' order by date");
      while(e.hasMoreElements())
      {
          f2r = e.nextElement();
          f2r2 = e2.nextElement();
          f2r3 = e3.nextElement();
          if(pref2r != null)
          {
//             int callV = Integer.parseInt(pref2r.f2) + Integer.parseInt(f2r.f1) - Integer.parseInt(f2r.f2);
//             int putV = Integer.parseInt(pref2r2.f2) + Integer.parseInt(f2r2.f1) - Integer.parseInt(f2r2.f2);
            float pMc = Float.parseFloat(f2r.f1);
            float pNc = Float.parseFloat(f2r.f2);
            float pM0 = Float.parseFloat(pref2r2.f1);
            float pN0 = Float.parseFloat(pref2r2.f2);
            float pM1 = Float.parseFloat(f2r2.f1);
            float pN1 = Float.parseFloat(f2r2.f2);
            System.out.println( "pMc: "+pMc+" pNc: "+pNc+" pM0: "+pM0+" pN0:"
                                + pN0+" pM1: "+pM1+" pN1: "+pN1);        
//             System.out.println(f2r.date+" " + f2r2.date +" callV : "+callV+" putV: "+putV+" diff:"+(callV-putV));
//             System.out.println(f2r3.date+"  Tcall : "+f2r3.f1+" Tput: "+f2r3.f2+" diff:"+
//                               (Integer.parseInt(f2r.f2)-Integer.parseInt(f2r2.f2))+ " tcpsum:"+f2r3.f3);
//             System.out.println(f2r3.date+"  Tcall : "+f2r3.f1+" Tput: "+f2r3.f2+" diff:"+
//                               (Integer.parseInt(pref2r.f2)+Integer.parseInt(f2r.f1) - )+ " tcpsum:"+f2r3.f3);
          }
//          System.out.println(f2r.dump());
          pref2r = f2r;
          pref2r2 = f2r2;
      }
  }


}
