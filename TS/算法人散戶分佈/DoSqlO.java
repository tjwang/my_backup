import java.io.*;
import java.net.*;
import java.sql.*;
import stock.db.*;
import stock.tool.*;

public class DoSqlO {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\ºâªk¤H´²¤á¤À§G\\\\"+ arg[0]+"_out.txt\" into table psnumoutdis FIELDS  TERMINATED by '|' ;");



 }
 



}
