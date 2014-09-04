import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import stock.tool.*;

public class CulMoney3 {

 static public void main(String[] arg)throws Exception{
    DBConnection dbc = new DBConnection();
    Connection dc = dbc.getConnection();
    Statement dbstmt= dc.createStatement();
    PCPPrice_Rec pcppr = new  PCPPrice_Rec();
    PrintStream  ps = new PrintStream("data3.txt");
    //Calculate Call
    Enumeration e = pcppr.SelectBySQL("select cp_month,type,date,0 as price ,sum((price+rp)*ra) / sum(ra) as rp , 0 as buy,0 as sell,0 as diff,sum(ra) as ra from pcpprice where date > '"+
                        arg[0]+"' and cp_month='"+arg[1]+"' and type='C' group by date,type order by type,date;"); 
       while(e.hasMoreElements()){
          pcppr = (PCPPrice_Rec)e.nextElement();
      	  PLDayk_Rec plkr = new  PLDayk_Rec();
      	  PLDayk_Rec plkr2 = new  PLDayk_Rec();
          plkr.SelectBySQL("select * from pldayk  where date = '"+pcppr.date+"' and snum = '0001' "); 
          plkr2.SelectBySQL("select * from pldayk  where date = '"+pcppr.date+"' and snum = '9999' "); 

          ps.println( pcppr.date+"|"+plkr.close+"|"+plkr2.close+"|"+pcppr.cp_month+"|"+ pcppr.type+"|"+ 
                      pcppr.rp+"|"+pcppr.ra+"|"  
                    );
       }
       //Calculate Put
       e = pcppr.SelectBySQL("select cp_month,type,date,0 as price ,sum((price-rp)*ra) / sum(ra) as rp , 0 as buy,0 as sell,0 as diff,sum(ra) as ra from pcpprice where date > '"+
                        arg[0]+"' and cp_month='"+arg[1]+"' and type='P' group by date,type order by type,date;"); 
                           
       while(e.hasMoreElements()){
          pcppr = (PCPPrice_Rec)e.nextElement();
      	  PLDayk_Rec plkr = new  PLDayk_Rec();
      	  PLDayk_Rec plkr2 = new  PLDayk_Rec();
           plkr.SelectBySQL("select * from pldayk  where date = '"+pcppr.date+"' and snum = '0001' "); 
           plkr2.SelectBySQL("select * from pldayk  where date = '"+pcppr.date+"' and snum = '9999' "); 

            ps.println( pcppr.date+"|"+plkr.close+"|"+plkr2.close+"|"+pcppr.cp_month+"|"+ pcppr.type+"|"+ 
               pcppr.rp+"|"+pcppr.ra+"|"   
             );
       }
     dbstmt.executeUpdate("delete from pcp3") ;

     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\ºâ¿ï¾ÜÅv\\\\data3.txt\" into table pcp3 FIELDS  TERMINATED by '|' ;");
}
 private static int countSum(PMAmount_Rec[]  Top3) throws Exception
 {
    int vSum = 0;
    for(int i=0;i < Top3.length; i++)
    {
       vSum += Integer.parseInt(Top3[i].sum);
    }
    return vSum;
 }





}
