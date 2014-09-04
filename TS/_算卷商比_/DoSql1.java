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

     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSDataPath()+"\\\\算卷商比\\\\"+
                        arg[0]+".txt\" into table pMRate FIELDS  TERMINATED by '|' ;");
     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSDataPath()+"\\\\算卷商比\\\\"+
                        arg[0]+".txt\" into table pMRate5 FIELDS  TERMINATED by '|' ;");
 }

}
