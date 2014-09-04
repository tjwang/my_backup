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

     dbstmt.executeUpdate("delete from pAmountTemp") ;

     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSDataPath()+"\\\\"+
                       arg[0]+"\\\\amount."+ arg[0]+".txt\" into table pAmountNew FIELDS  TERMINATED by '|';") ;
                       
     dbstmt.executeUpdate("load data infile\""+ GlobleSetting.getTSDataPath()+"\\\\"+
                       arg[0]+"\\\\amount."+ arg[0]+".txt\" into table pAmountTemp FIELDS  TERMINATED by '|';") ;

     dbstmt.executeUpdate("INSERT INTO pLast (snum,date ,time ,pbuy ,psell,rp,diff ,ra,total)  select * from ( select *  from pAmountTemp where date='"+
                         arg[0]+"' order by snum,time desc) as a  group by snum;");

//     dbstmt.executeUpdate("INSERT INTO pCAmount (snum,date,rp,ra)  SELECT snum,date,rp,sum(ra)   FROM pAmountTemp WHERE date='"+
//                         arg[0]+"' group by snum,rp;");


 }
 



}
