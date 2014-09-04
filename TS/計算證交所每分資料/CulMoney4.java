import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;

public class CulMoney4 {
 static int currentCount = 0;
 static int range = 0;
 static float total = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

    Pldayk_ofcl_Rec pmar = new  Pldayk_ofcl_Rec();
    Pldayk_ofcl_Rec pmar2 = null;
    ht = new Hashtable();
    Enumeration e = pmar.SelectBySQL("select * from Pldayk_ofcl where date='"+arg[0]+"' order by time ;"); 
//    Enumeration e = pmar.SelectBySQL("select * from Pldayk_ofcl  order by date,time ;"); 
    Vector data = new Vector();
    float AmountM = 0;
    float AmountV = 0;
    float AmountC = 0;
    float CreditsM= 0;
    float CreditsV = 0;
    float CreditsC = 0;
    while(e.hasMoreElements()){
           pmar = (Pldayk_ofcl_Rec)e.nextElement();
           if((pmar2!=null) && (!pmar2.date.equals(pmar.date)))
           {
              System.out.println(pmar2.date+"|"+(AmountM/CreditsM)+"|"+(AmountV/CreditsV)+"|"+(AmountC/CreditsC)+"|"
                                           +(AmountM)+"|"+(AmountV)+"|"+(AmountC)+"|"
                                           +(CreditsM)+"|"+(CreditsV)+"|"+(CreditsC)+"|");
              AmountM = 0;
              AmountV = 0;
              AmountC = 0;
              CreditsM= 0;
              CreditsV = 0;
              CreditsC = 0;
           }
           float vAmountM = 0;
           float vAmountV = 0;
           float vAmountC = 0;
           vAmountM = Float.parseFloat(pmar.RpMoney);
           vAmountV = Float.parseFloat(pmar.RpAmt);
           vAmountC = Float.parseFloat(pmar.RpCnt);
           AmountM += vAmountM ;
           AmountV += vAmountV ;
           AmountC += vAmountC ;
           CreditsM += vAmountM / Float.parseFloat(pmar.aIdx)  ;
           CreditsV += vAmountV / Float.parseFloat(pmar.aIdx)  ;
           CreditsC += vAmountC / Float.parseFloat(pmar.aIdx)  ;
           pmar2 = pmar;
    }
//    System.out.println(arg[0]+"|"+(totalPM/totalMoney)+"|"+totalMoney+"|");
    System.out.println(pmar2.date+"|"+(AmountM/CreditsM)+"|"+(AmountV/CreditsV)+"|"+(AmountC/CreditsC)+"|"
                                 +(AmountM)+"|"+(AmountV)+"|"+(AmountC)+"|"
                                 +(CreditsM)+"|"+(CreditsV)+"|"+(CreditsC)+"|");
}


}