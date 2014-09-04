import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql22 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\計算證交所每分資料\\\\"+ arg[0]+"avgIdx.txt\" into table pavgindex_of FIELDS  TERMINATED by '|' ;");
//     dbstmt.executeUpdate("load data infile \"C:\\\\Mime\\\\新世代\\\\計算證交所每分資料\\\\"+
//                        arg[0]+"Difra.txt\" into table pIdxDifRp_offical FIELDS  TERMINATED by '|' ;");
 }

}
