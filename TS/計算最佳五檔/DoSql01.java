import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql01 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

//     dbstmt.executeUpdate("delete from pAmountTemp") ;
                       
     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\�p��̨Τ���\\\\"+ arg[0]+".txt\"  into table pfive_info FIELDS  TERMINATED by '|';") ;



 }
 



}
