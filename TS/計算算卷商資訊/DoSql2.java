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
     dbstmt.executeUpdate("delete from pmamountinfo_week where date='"+arg[0]+"' ;");
     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\�p�����Ӹ�T\\\\"+ arg[0]+".txt\" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;");
 }

}
