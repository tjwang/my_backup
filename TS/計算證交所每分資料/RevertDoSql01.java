import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class RevertDoSql01 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("delete from  pIdxDifRp_offical where date='"+arg[0]+"' ;") ;
     dbstmt.executeUpdate("delete from  pldayk_ofcl where date='"+arg[0]+"' ;") ;
     dbstmt.executeUpdate("delete from  pavgindex_of where date='"+arg[0]+"' ;") ;

 }
 



}
