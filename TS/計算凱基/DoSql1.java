import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DoSql1 {

 static Date s2d(String s)
 {
 	   int dint = Integer.parseInt(s);
     return  new Date(dint/10000- 1900,(dint %10000)/100 -1,dint % 100);
 }

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     DBConnection.debug = false;
   //  int  dateint = Integer.parseInt(arg[0]);
   //  java.util.Date d = new java.util.Date(dateint/10000 -1900 ,((dateint%10000)/100) - 1,dateint % 100);
     F1_Rec f1 = new F1_Rec();
     f1.SelectBySQL("select max(date) as date, 0 as time, 0 as f1 from pmamount2info "); 
     java.util.Date d = new java.util.Date(s2d(f1.date).getTime() + 86400000);

     System.out.println(""+d.getYear()+" m:"+d.getMonth()+" d:"+d.getDate());

    java.util.Date d2 = new java.util.Date();
    long d2long = d2.getTime();
    
    while(d.getTime() < d2long){
      String ds = String.valueOf((1900+d.getYear())*10000+(d.getMonth()+1)*100+d.getDate());

     dbstmt.executeUpdate("INSERT INTO pmamount2info (mnum,date,inAmt,outAmt,totalAmt)   "+
                          " select mnum, '"+ds+"',sum(b.avg * a.inAmount), sum(b.avg * a.outAmount), sum(b.avg * a.sum) from  "+ 
                          " (select * from pmamount2 where date='"+ds+"') as a,    (select * from pamountinfo where date='"+
                          ds+"') as b where a.snum = b.snum group by mnum order by mnum;");
       d = new java.util.Date(d.getTime()+86400000);
    } 

}

}
