import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql0X {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("delete from pAmountTemp") ;

     dbstmt.executeUpdate("load data infile\""+ GlobleSetting.getTSDataPath()+"\\\\"+
                       arg[0]+"\\\\amount."+ arg[0]+".txt\" into table pAmountTemp FIELDS  TERMINATED by '|';") ;


 }
 



}
