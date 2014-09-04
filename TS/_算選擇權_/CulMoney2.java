import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import stock.tool.*;

public class CulMoney2 {

 static public void main(String[] arg)throws Exception{
    DBConnection dbc = new DBConnection();
    Connection dc = dbc.getConnection();
    Statement dbstmt= dc.createStatement();
    PCPPrice_Rec pcppr = new  PCPPrice_Rec();
    PrintStream  ps = new PrintStream("data2.txt");
    Enumeration e = pcppr.SelectBySQL("select * from pcpprice  where"+
                                     " date > '"+arg[0]+"'  and cp_month='"+arg[1]+"' order by type,date,ra*rp;"); 
    PCPPrice_Rec[] databuffer = new PCPPrice_Rec[2];
    String cp_date = null; 
       while(e.hasMoreElements()){
          pcppr = (PCPPrice_Rec)e.nextElement();
      	  if ((cp_date != null) && (!cp_date.equals(pcppr.date)) ){
      	  	      PLDayk_Rec plkr = new  PLDayk_Rec();
      	  	      PLDayk_Rec plkr2 = new  PLDayk_Rec();
                  plkr.SelectBySQL("select * from pldayk  where date = '"+cp_date+"' and snum = '0001' "); 
                  plkr2.SelectBySQL("select * from pldayk  where date = '"+cp_date+"' and snum = '9999' "); 

              ps.println( cp_date+"|"+plkr.close+"|"+plkr2.close+"|"+pcppr.cp_month+"|"+ databuffer[0].type+"|"+ 
                                  databuffer[0].price+"|"+databuffer[0].rp+"|"+databuffer[0].ra+"|"+   
                                  databuffer[1].price+"|"+databuffer[1].rp+"|"+databuffer[1].ra+"|"   
                                  );
      	  }
      	  cp_date = pcppr.date;
      	  databuffer[1] = databuffer[0]; 
      	  databuffer[0] = pcppr;
       }
       PLDayk_Rec plkr = new  PLDayk_Rec();
       PLDayk_Rec plkr2 = new  PLDayk_Rec();
       plkr.SelectBySQL("select * from pldayk  where date = '"+cp_date+"' and snum = '0001' "); 
       plkr2.SelectBySQL("select * from pldayk  where date = '"+cp_date+"' and snum = '9999' "); 

       ps.println( cp_date+"|"+plkr.close+"|"+plkr2.close+"|"+pcppr.cp_month+"|"+ databuffer[0].type+"|"+ 
                           databuffer[0].price+"|"+databuffer[0].rp+"|"+databuffer[0].ra+"|"+   
                           databuffer[1].price+"|"+databuffer[1].rp+"|"+databuffer[1].ra+"|"   
                           );
                           
     dbstmt.executeUpdate("delete from pcp2") ;

     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\ºâ¿ï¾ÜÅv\\\\data2.txt\" into table pcp2 FIELDS  TERMINATED by '|' ;");
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
