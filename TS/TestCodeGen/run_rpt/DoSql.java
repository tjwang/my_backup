import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     System.out.println(arg[0]);
     dbstmt.executeUpdate(arg[0]);
 }

}
