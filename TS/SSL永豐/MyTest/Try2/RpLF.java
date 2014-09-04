import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class RpLF extends OneFieldBySQL{

 static public final int BuyCnt = 0;
 static public final int BuyAmt = 1;
 static public final int SellCnt = 2;
 static public final int SellAmt = 3;
 static public final int RpCnt = 4;
 static public final int RpAmt = 5;
 static public final int RpMoney = 6;
 public  RpLF(int type, String dateStr)throws Exception
 {
 	  switch(type)
 	  {
 	      case BuyCnt:
 	         loadData("select date , time*100 as time, BuyCnt as f1  from pidxrp_offical where  date = '"+dateStr+"' order by time;");
           break;
 	      case BuyAmt:
 	         loadData("select date , time*100 as time, BuyAmt as f1  from pidxrp_offical where  date = '"+dateStr+"' order by time;");
           break;
 	      case SellCnt:
 	         loadData("select date , time*100 as time, SellCnt as f1  from pidxrp_offical where  date = '"+dateStr+"' order by time;");
           break;
 	      case SellAmt:
 	         loadData("select date , time*100 as time, SellAmt as f1  from pidxrp_offical where  date = '"+dateStr+"' order by time;");
           break;
 	      case RpCnt:
 	         loadData("select date , time*100 as time, RpCnt as f1  from pidxrp_offical where  date = '"+dateStr+"' order by time;");
           break;
 	      case RpAmt:
 	         loadData("select date , time*100 as time, RpAmt as f1  from pidxrp_offical where  date = '"+dateStr+"' order by time;");
           break;
 	      case RpMoney:
 	         loadData("select date , time*100 as time, RpMoney as f1  from pidxrp_offical where  date = '"+dateStr+"' order by time;");
           break;
    }
 }

 public  RpLF(int type, String sdate, String edate)throws Exception
 {
 	  switch(type)
 	  {
 	      case BuyCnt:
 	         loadData("select date , time*100 as time, BuyCnt as f1  from pidxrp_offical where  date >= '"+sdate+"' and date <= '"+edate+"' order by date,time;");
           break;
 	      case BuyAmt:
 	         loadData("select date , time*100 as time, BuyAmt as f1  from pidxrp_offical where  date >= '"+sdate+"' and date <= '"+edate+"' order by date,time;");
           break;
 	      case SellCnt:
 	         loadData("select date , time*100 as time, SellCnt as f1  from pidxrp_offical where  date >= '"+sdate+"' and date <= '"+edate+"' order by date,time;");
           break;
 	      case SellAmt:
 	         loadData("select date , time*100 as time, SellAmt as f1  from pidxrp_offical where  date >= '"+sdate+"' and date <= '"+edate+"' order by date,time;");
           break;
 	      case RpCnt:
 	         loadData("select date , time*100 as time, RpCnt as f1  from pidxrp_offical where  date >= '"+sdate+"' and date <= '"+edate+"' order by date,time;");
           break;
 	      case RpAmt:
 	         loadData("select date , time*100 as time, RpAmt as f1  from pidxrp_offical where  date >= '"+sdate+"' and date <= '"+edate+"' order by date,time;");
           break;
 	      case RpMoney:
 	         loadData("select date , time*100 as time, RpMoney as f1  from pidxrp_offical where  date >= '"+sdate+"' and date <= '"+edate+"' order by date,time;");
           break;
    }
 }

}
