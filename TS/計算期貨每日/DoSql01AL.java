import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql01AL {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("load data infile \""+ "X:\\\\Working\\\\TS\\\\計算期貨每日\\\\dataF"+
                       "\\\\ptxdayk."+ arg[0]+".txt\" into table ptxdayk FIELDS  TERMINATED by '|';") ;

 }
 



}
