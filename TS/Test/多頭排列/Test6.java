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

public class Test6 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 
  static public boolean checkValue1(Line l, float cfv)
  {
     for(int i = 0; i < l.length(); i++)
     {
        Value v = l.valueAt(i);
        if(v.getValue() > cfv) return false;
     }  
     return true;
  }

  static public boolean checkValue(Line l, float cfv)
  {
        Value v2 = l.valueAt(l.length()-1);
        Value v1 = l.valueAt(0);
        
        if(v2.getValue() > 2 && v1.getValue() < -2) return true;
        
        return false;
  }
 
 
 static final String[] check_snum =
 {
 	"8068",
 "2915",
 "6116",
 "3265",
 "5904",
 "2461",
 "2428",
 "9914",
 "2913",
 "2449",
 "8042",
 "8096",
 "6234",
 "3698",
 "1337",
 "6176",
 "2375",
 "2059",
 "8422",
 "8078",
 "2891",
 "2393",
 "2454",
 "3105",
 "6192",
 "2325",
 "3563",
 "1580",
 "3189",
 "6145",
 "3257",
 "2548",
 "6271",
 "1312",
 "1476",
 "8213",
 "1319",
 "2474",
 "6239",
 "3491",
 "1477",
 "2356",
 "1537",
 "1536",
 "2373",
 "6282",
 "1507",
 "8299",
 "2534",
 "4401",
 "6279",
 "3376",
 "4207",
 "4126",
 "2520",
 "8039",
 "3561",
 "2313",
 "4527",
 "4958",
 "2455",
 "3044",
 "2049",
"6274"
};  

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
         TestKD   skd = new TestKD(ds000r.snum,arg[0],1);
//         TestKD   skd = new TestKD(check_snum[k],arg[0],1);
//         KDLine kd1 =  skd.culKDLine(25,16,5);
//         Line kl1  = kd1.getKLine();
//         Line dl1  = kd1.getDLine();
         KLine kl = skd.culKLine();
         if(kl.length() < 100) continue;
         Line rsvl = kl.rsv(25);
         Line kl1 = rsvl.macd(16+1,2);
         Line dl1 = kl1.macd(5+1,2);
         Line sub_k = kl1.sub(kl1.length()-13,kl1.length()-11);
         Line sub_d = dl1.sub(dl1.length()-13,dl1.length()-11);
         

         Line dif_0 = sub_k.diff(sub_d);

        if(checkValue(dif_0,(float)0.1))
         {
         	   Value v1 = kl.valueAt(kl.length()-11);
         	   Value v2 = kl.valueAt(kl.length()-6);
             System.out.println("snum "+ds000r.snum+" "+(new String(ds000r.sname.getBytes("ISO8859-1"),"MS950"))+ " "+
                        ds000r.capital+" OK! -->" + sub_k.valueAt(0).getDateValue());
             v1.dump();           
             v2.dump();   
             System.out.println("diff: "+(v2.getValue()-v1.getValue()));   
                     
//            System.out.println("snum "+check_snum[k]+" OK! -->" + sub_m8.valueAt(0).getDateValue());
            got ++;
         }   
      }
      System.out.println("toatl "+ total + " got "+ got);   
}



}