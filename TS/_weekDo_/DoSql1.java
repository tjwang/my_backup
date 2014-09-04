import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DoSql1 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

     dbstmt.executeUpdate("INSERT INTO pmamountw (snum,date,mnum,inamount,outamount,sum)  select snum,'"+
                        arg[1]+"',mnum,sum(inAmount),sum(outAmount),sum(sum) from pmamount where date > '"+
                        arg[0]+"' and date < '"+arg[1]+"' group by snum,mnum;");
/*     dbstmt.executeUpdate("INSERT INTO pmamountw (snum,date,mnum,inamount,outamount,sum)  select snum,'"+
                        arg[1]+"',mnum,sum(inAmount),sum(outAmount),sum(sum) from pmamount where date > '"+
                        arg[0]+"' and date < '"+arg[1]+"' group by snum,mnum;");*/
 }

}
