import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DoSql02 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     dbstmt.executeUpdate("INSERT INTO pldayk (snum,date,time,open,high,low,close,volume,money) " +
                          "select snum,date,time as time, aIdx as open "+
                          ", aIdx as high , aIdx as low , aIdx as close , RpAmt as volume "+
                          ", RpMoney/100 as money from pldayk_ofcl where date='"+arg[0]+"';  ");
     dbstmt.executeUpdate("INSERT INTO pldayk (snum,date,time,open,high,low,close,volume,money) "+
                          "select snum,date,time,open,high,low,close,volume,money from "+
                          "(select aIdx as close  from pldayk_ofcl where date='"+arg[0]+"' order by time desc limit 1) as a, "+
                          "(select aIdx as open  from pldayk_ofcl where date='"+arg[0]+"' order by time limit 1) as b, "+
                          "(select '0001' as snum ,date,'0' as time, max(aIdx) as high , min(aIdx) as low  , sum(RpAmt) as volume , sum(RpMoney)/100 as money "+
                          "from pldayk_ofcl where date='"+arg[0]+"'   group by snum ) as c ;");
//     dbstmt.executeUpdate("load data infile \"C:\\\\Mime\\\\·s¥@¥N\\\\phase\\\\1mi.txt\" into table pldayk FIELDS  TERMINATED by ',' ;");



 }
 



}
