import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;

public class CulMoney3 {
 static int currentCount = 0;
 static int range = 0;
 static float total = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

    Pidxrp_offical_Rec pmar = new  Pidxrp_offical_Rec();
    Pidxrp_offical_Rec pmar2 = null;
    ht = new Hashtable();
//    Enumeration e = pmar.SelectBySQL( "select * from Pidxrp_offical order by date,time;" ); 
    Enumeration e = pmar.SelectBySQL( "select * from Pidxrp_offical where date='"+arg[0]+"' order by date,time;" ); 
    Vector data = new Vector();
    float openPrice = -1;
    float closePrice = -1;
    while(e.hasMoreElements()){
           pmar = (Pidxrp_offical_Rec)e.nextElement();
           if(pmar2!=null)
           {
           	 if(Integer.parseInt(pmar.time) > 900)
           	 {
                 System.out.println(pmar.date+"|"+pmar.time+"|"+
                                 (Integer.parseInt(pmar.BuyCnt)-Integer.parseInt(pmar2.BuyCnt))+"|"+
                                 (Integer.parseInt(pmar.BuyAmt)-Integer.parseInt(pmar2.BuyAmt))+"|"+
                                 (Integer.parseInt(pmar.SellCnt)-Integer.parseInt(pmar2.SellCnt))+"|"+
                                 (Integer.parseInt(pmar.SellAmt)-Integer.parseInt(pmar2.SellAmt))+"|"+
                                 (Integer.parseInt(pmar.RpCnt)-Integer.parseInt(pmar2.RpCnt))+"|"+
                                 (Integer.parseInt(pmar.RpAmt)-Integer.parseInt(pmar2.RpAmt))+"|"+
                                 (Integer.parseInt(pmar.RpMoney)-Integer.parseInt(pmar2.RpMoney))+"|");
             }
           }
           pmar2 = pmar;
     }
     System.out.println(pmar.date+"|"+pmar.time+"|"+
                        (Integer.parseInt(pmar.BuyCnt)-Integer.parseInt(pmar2.BuyCnt))+"|"+
                        (Integer.parseInt(pmar.BuyAmt)-Integer.parseInt(pmar2.BuyAmt))+"|"+
                        (Integer.parseInt(pmar.SellCnt)-Integer.parseInt(pmar2.SellCnt))+"|"+
                        (Integer.parseInt(pmar.RpCnt)-Integer.parseInt(pmar2.RpCnt))+"|"+
                        (Integer.parseInt(pmar.RpAmt)-Integer.parseInt(pmar2.RpAmt))+"|"+
                        (Integer.parseInt(pmar.RpMoney)-Integer.parseInt(pmar2.RpMoney)));
}


}