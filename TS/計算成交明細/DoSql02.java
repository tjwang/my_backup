import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql02 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

//     dbstmt.executeUpdate("delete from pAmountTemp") ;
                       
     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\計算成交明細\\\\"+ arg[0]+"_2.txt\" into table pamountinfo2 FIELDS  TERMINATED by '|';") ;




 }
 



}
