import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class RevertDoSql01 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
 	   int dint = Integer.parseInt(arg[0]); 
     java.util.Date d = new java.util.Date(dint/10000- 1900,(dint %10000)/100 -1,dint % 100);
     d = new java.util.Date(d.getTime() +86400000);
     String enddate = String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());

     dbstmt.executeUpdate("delete from  pmamountinfo_week where date='"+enddate+"' ;") ;

 }
 



}
