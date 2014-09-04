package stock.app;
import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.tool.*;

public class  StockSeries extends StockKD{

 String  _snum;
 public  StockSeries(String snum,String dateStr)throws Exception
 {
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
/*
     dbstmt.executeUpdate("delete from pAmountTemp") ;
     dbstmt.executeUpdate("load data infile\""+ GlobleSetting.getTSDataPath()+"\\\\"+
                       dateStr+"\\\\amount."+ dateStr+".txt\" into table pAmountTemp FIELDS  TERMINATED by '|';") ;
*/
     _snum = snum;
     PAmountTemp_Rec par= new PAmountTemp_Rec();
     Enumeration e = par.SelectBySQL("select * from  pamounttemp where   snum='"+snum+"' order by date,time "  );    
     while(e.hasMoreElements())
     {
   	    par = (PAmountTemp_Rec)e.nextElement();
        PLDayk_Rec pkr= new PLDayk_Rec();
        pkr.snum = par.snum;
        pkr.date = par.date;
        pkr.time = par.time;
        pkr.open = par.rp;
        pkr.high = par.ra;
        pkr.low = par.rp;
        pkr.close = par.rp;
        pkr.volume = par.ra;
        pkr.money = par.ra;
        add(pkr);
     }
 }

 public float getBasePrice() throws Exception
 {
   PLast2_Rec  pls2 = new PLast2_Rec();
   KValue  kv = (KValue)dayKdata.first() ;
   pls2.SelectBySQL("select * from plast2 where snum='"+kv.getName()+"' and date='"+kv.getDateValue()+"' ");
   return Float.parseFloat(pls2.pclose);
 }

}