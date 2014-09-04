import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DoSql3 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate("delete from pmamount3;");
     dbstmt.executeUpdate("load data infile \""+GlobleSetting.getTSCodePath()+"\\\\§ì³Í°ò\\\\3.txt\" into table pmamount3 FIELDS  TERMINATED by '|' ;");
 }

}
