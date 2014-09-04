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
                       arg[0]+"\\\\mamount."+ arg[0]+".txt\" into table pMAmount FIELDS  TERMINATED by '|' ;");
     dbstmt.executeUpdate("delete from pMAmount where snum='';");
     dbstmt.executeUpdate("delete from pMAmount where inAmount = 0 and outAmount = 0 and sum = 0;");
}

}
