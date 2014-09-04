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
     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSDataPath()+"\\\\"+
                       arg[0]+"\\\\cmain."+ arg[0]+".txt\" into table cmain FIELDS  TERMINATED by '|' ;");
 }

}
