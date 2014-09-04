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

public class Test {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
  static public boolean checkValue(Line l, float cfv)
  {
     for(int i = 0; i < l.length(); i++)
     {
        Value v = l.valueAt(i);
        if(v.getValue() < cfv) return false;
     }  
     return true;
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

static public Vector query(String sd,String ed) throws Exception
{
      Ds000_Rec ds000r =new Ds000_Rec();
      Enumeration e = ds000r.SelectBySQL("select * from ds000  order by snum");
      Vector result = new Vector();
      while(e.hasMoreElements())
      {
         ds000r=(Ds000_Rec)e.nextElement();
         try
         {
           TestKD   skd = new TestKD(ds000r.snum,sd,ed);
           MALine m8 =  skd.culMALine(8);
           MALine m13 =  skd.culMALine(13);
           MALine m21 =  skd.culMALine(21);
           MALine m33 =  skd.culMALine(33);
           Line sub_m8 = m8.sub(m8.length()-21,m8.length()-1);
           Line sub_m13 = m13.sub(m13.length()-21,m13.length()-1);
           Line sub_m21 = m21.sub(m21.length()-21,m21.length()-1);
           Line sub_m33 = m33.sub(m33.length()-21,m33.length()-1);
           Line dif_1 = sub_m8.diff(sub_m13);
           Line dif_2 = sub_m13.diff(sub_m21);
           Line dif_3 = sub_m21.diff(sub_m33);
           if(checkValue(dif_1,0) && checkValue(dif_2,0) && checkValue(dif_3,0) )
           {
              result.add(ds000r);              
           }
         }catch(Exception xe)
         {
             continue;
         }   
      }   
      return result;

}


static public void main(String[] arg)throws Exception{
      Ds000_Rec ds000r =new Ds000_Rec();
      Enumeration e = ds000r.SelectBySQL("select * from ds000  order by snum");
      while(e.hasMoreElements())
      
//      for(int k = 0;k < check_snum.length; k++)
      {
         ds000r=(Ds000_Rec)e.nextElement();
         try
         {

            TestKD   skd = new TestKD(ds000r.snum,arg[0],1);
//         TestKD   skd = new TestKD(check_snum[k],arg[0],1);
           MALine m8 =  skd.culMALine(8);
           MALine m13 =  skd.culMALine(13);
           MALine m21 =  skd.culMALine(21);
           MALine m33 =  skd.culMALine(33);
           Line sub_m8 = m8.sub(m8.length()-21,m8.length()-1);
           Line sub_m13 = m13.sub(m13.length()-21,m13.length()-1);
           Line sub_m21 = m21.sub(m21.length()-21,m21.length()-1);
           Line sub_m33 = m33.sub(m33.length()-21,m33.length()-1);
           Line dif_1 = sub_m8.diff(sub_m13);
           Line dif_2 = sub_m13.diff(sub_m21);
           Line dif_3 = sub_m21.diff(sub_m33);
           if(checkValue(dif_1,0) && checkValue(dif_2,0) && checkValue(dif_3,0) )
           {
               System.out.println("snum "+ds000r.snum+" "+(new String(ds000r.sname.getBytes("ISO8859-1"),"MS950"))+ " "+ds000r.capital+" OK! -->" + sub_m8.valueAt(0).getDateValue());
//            System.out.println("snum "+check_snum[k]+" OK! -->" + sub_m8.valueAt(0).getDateValue());
           } 
         }catch(Exception xe)
         {
            continue;
         }  
      }   
}



}