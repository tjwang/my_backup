import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql1 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate("delete from pfive ");
     dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSDataPath()+"\\\\"+
                       arg[0]+"\\\\five."+ arg[0]+".txt\"   into table pfive FIELDS  TERMINATED by '|' ;");
 }

}
