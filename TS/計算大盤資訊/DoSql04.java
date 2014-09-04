import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql04 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\計算大盤資訊\\\\"+ arg[0]+"_av.txt\" into table pavgIndex FIELDS  TERMINATED by '|';") ;


 }
 



}
