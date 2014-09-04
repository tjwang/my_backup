import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DoSql05 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("insert into  pamountinfo5 select snum,date,0 as time, rp, diff,sum(ra) as ra ,count(rp) as cnt from pamounttemp  group by snum,rp;") ;


 }
 



}
