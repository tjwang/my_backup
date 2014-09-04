import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSqlD {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

//     dbstmt.executeUpdate("delete from pAmountTemp") ;
                       
     dbstmt.executeUpdate("INSERT INTO pfive_infoD (snum,date,type,rp,ra,count)   "+
                          " select snum, date, 'B', rp, sum(ra_w), count(snum) from  pfive_info where date = '"+
                          arg[0]+"' and typeBS='B' and typeAD='D' group by snum,date,rp;");

     dbstmt.executeUpdate("INSERT INTO pfive_infoD (snum,date,type,rp,ra,count)   "+
                          " select snum, date, 'S', rp, sum(ra_w), count(snum) from  pfive_info where date = '"+
                          arg[0]+"' and typeBS='S' and typeAD='D' group by snum,date,rp;");


 }
 



}
