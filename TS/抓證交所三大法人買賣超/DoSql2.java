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
     dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSDataPath()+"\\\\"+
                       arg[0]+"\\\\inoutsum_official."+ arg[0]+".txt\"  into table pinoutsum_official FIELDS  TERMINATED by '|' ;");
 }

}
