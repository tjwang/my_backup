import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DoSql2 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate("load data infile \""+GlobleSetting.getTSCodePath()+"\\\\抓證交所日成交資料\\\\"+
                        arg[0]+"ll.txt\" into table plast2 FIELDS  TERMINATED by '|' ;");
 }

}
