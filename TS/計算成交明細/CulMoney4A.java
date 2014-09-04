import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class CulMoney4A {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

    PAmountinfo4_Rec pmar = new  PAmountinfo4_Rec();
    PAmountinfo4_Rec pmar2 = null;
    ht = new Hashtable();
    Enumeration e = pmar.SelectBySQL("select *,0 as tamount from  Pamountinfo4 where date='"+arg[0]+"' order by snum,rangeS,date,timeS "  ); 
    while(e.hasMoreElements()){
           pmar = (PAmountinfo4_Rec)e.nextElement();
           if(pmar2==null)
           {
             System.out.println(pmar.snum+"|"+pmar.date+"|"+pmar.rangeS+"|"+pmar.timeS+"|"+pmar.rangeE+"|"+pmar.timeE+"|"+pmar.amount+"|"+Cultime(pmar)+"|" );
             pmar2 = pmar;
           }else{
             if(
               (!pmar.timeE.equals(pmar2.timeE)) ||
               (!pmar.date.equals(pmar2.date)) ||
               (!pmar.snum.equals(pmar2.snum)) 
               )
             {
                System.out.println(pmar.snum+"|"+pmar.date+"|"+pmar.rangeS+"|"+pmar.timeS+"|"+pmar.rangeE+"|"+pmar.timeE+"|"+pmar.amount+"|"+Cultime(pmar)+"|" );
                pmar2 = pmar;
             }
             pmar2 = pmar;
          }
    }
}

static int Cultime(PAmountinfo4_Rec pmar)
{
 	  int dint = Integer.parseInt(pmar.date); 
    Date ds = new Date(dint/10000- 1900,(dint %10000)/100 -1,dint % 100
                      ,Integer.parseInt(pmar.timeS.substring(0,2))
                      ,Integer.parseInt(pmar.timeS.substring(3,5))
                      ,Integer.parseInt(pmar.timeS.substring(6,8))
                       );
    Date de = new Date(dint/10000- 1900,(dint %10000)/100 -1,dint % 100
                      ,Integer.parseInt(pmar.timeE.substring(0,2))
                      ,Integer.parseInt(pmar.timeE.substring(3,5))
                      ,Integer.parseInt(pmar.timeE.substring(6,8))
                       );
                       
    return  (int)((de.getTime() -  ds.getTime())/1000);                  
}


}