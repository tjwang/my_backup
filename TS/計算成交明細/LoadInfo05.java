import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class LoadInfo05 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     java.util.Date sDate = new java.util.Date(111,0,1);
     java.util.Date eDate = new java.util.Date(114,0,12);
     while(sDate.getTime() < eDate.getTime())
     {
        String do_date = GMethod.d2s(sDate);
        System.out.println(GMethod.d2s(sDate));
        dbstmt.executeUpdate("delete from pAmountTemp") ;
        try{
           dbstmt.executeUpdate("load data infile\""+ GlobleSetting.getTSDataPath()+"\\\\"+
                          do_date+"\\\\amount."+ do_date+".txt\" into table pAmountTemp FIELDS  TERMINATED by '|';") ;
           dbstmt.executeUpdate("insert into  pamountinfo5 select snum,date,0 as time, rp, diff,sum(ra) as ra ,count(rp) as cnt from pamounttemp  group by snum,rp;") ;
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        sDate = new java.util.Date(sDate.getTime()+86400000);
     }
     
 /*    
*/

 }
 



}
