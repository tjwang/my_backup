import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DoSql1 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("INSERT INTO pmamountinfo (mnum,date,inAmt,outAmt,totalAmt)   "+
                          " select mnum, '"+arg[0]+"',sum(b.avg * a.inAmount), sum(b.avg * a.outAmount), sum(b.avg * a.sum) from  "+ 
                          " (select * from pmamount where date='"+arg[0]+"') as a,    (select * from pamountinfo where date='"+
                          arg[0]+"') as b where a.snum = b.snum group by mnum order by mnum;");
 }

}
