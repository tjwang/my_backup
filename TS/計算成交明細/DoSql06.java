import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DoSql06 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("load data infile \"c:\\\\money\\\\dataM6\\\\"+
                       arg[0]+".txt\" into table pamountinfo6 FIELDS  TERMINATED by '|';") ;


 }
 



}
