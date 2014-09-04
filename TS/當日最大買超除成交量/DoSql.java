import java.io.*;
import java.net.*;
import java.sql.*;
import stock.db.*;
import stock.tool.*;

public class DoSql {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\當日最大買超除成交量\\\\"+ arg[0]+".txt\"  into table pmK FIELDS  TERMINATED by '|' ;");



 }
 



}
