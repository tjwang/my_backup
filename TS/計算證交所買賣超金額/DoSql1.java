import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DoSql1 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate("INSERT INTO psnuminoutmoney (date,type,snum,sname,inM,outM,totalM)  "+
                          "select psnuminoutsum.date,psnuminoutsum.type,psnuminoutsum.snum,psnuminoutsum.sname "+
                          ",psnuminoutsum.in * pamountinfo.avg ,psnuminoutsum.out * pamountinfo.avg ,psnuminoutsum.total * pamountinfo.avg "+
                          " from  psnuminoutsum,pamountinfo where  psnuminoutsum.snum = pamountinfo.snum and  "+
                          " psnuminoutsum.date = pamountinfo.date and psnuminoutsum.date ="+ arg[0]+";");
 }

}
