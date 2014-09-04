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

    PamountNew_Rec pmar = new  PamountNew_Rec();
    PamountNew_Rec pmar2 = null;
    ht = new Hashtable();
    Enumeration e = pmar.SelectBySQL("select snum,date,time,open as pbuy, close as psell, close as rp, 0 as diff,"+
                                     " money as ra, money as total  from  pldayk where  snum='T000'  and time !='0' and date='"+arg[0]+"' order by date,time "  ); 
    Vector data = new Vector();
    float totalPM = 0;
    float totalMoney = 0;
    float totalCredits = 0;
    float cmoney = 0;
    while(e.hasMoreElements()){
           pmar = (PamountNew_Rec)e.nextElement();
//           if((pmar2!=null) && (!pmar2.date.equals(pmar.date)))
//           {
//              System.out.println(pmar2.date+"|"+(totalPM/totalMoney)+"|"+totalMoney+"|");
//              totalPM = 0;   
//              totalMoney = 0;
//              cmoney = 0;    
//           }
           cmoney = Float.parseFloat(pmar.ra);
           totalMoney += cmoney;
           totalPM += Float.parseFloat(pmar.rp) * cmoney;
           totalCredits += cmoney / Float.parseFloat(pmar.rp)  ;
           pmar2 = pmar;
    }
//    System.out.println(arg[0]+"|"+(totalPM/totalMoney)+"|"+totalMoney+"|");
    System.out.println(arg[0]+"|"+(totalPM/totalMoney)+"|"+(totalMoney/totalCredits)+"|"+totalMoney+"|"+totalPM+"|"+totalCredits+"|");
}


}