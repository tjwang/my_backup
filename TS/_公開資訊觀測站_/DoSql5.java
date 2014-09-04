import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql5 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+
                          "\\\\_公開資訊觀測站_\\\\incomes."+ arg[0]+".txt\" into table incomes FIELDS  TERMINATED by '|' ;");
 }

}
