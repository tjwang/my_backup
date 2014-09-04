import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql2 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate("load data infile\""+ GlobleSetting.getTSCodePath()+"\\\\"+
                       "¶×²v\\\\aps.txt\"  into table crate_usd FIELDS  TERMINATED by '|' ;");
     dbstmt.executeUpdate("load data infile\""+ GlobleSetting.getTSCodePath()+"\\\\"+
                       "¶×²v\\\\nps.txt\"  into table crate_ntd FIELDS  TERMINATED by '|' ;");
 }

}
