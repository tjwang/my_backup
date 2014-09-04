import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test2KD extends ThreeFieldBySQL{
   String _snum = null;
   String _dateStr = null;
 public   Test2KD()
 {
 }

 public   Test2KD(String snum)throws Exception
 {
 	   this(snum, null);
 }
 
public  Test2KD(String snum,String dateStr)throws Exception
 {
 	  _snum = snum;
 	  _dateStr = dateStr;
 	  String queryStr = null; 
    if(dateStr == null){
     	 queryStr = "select date , 0 as time total as f1,totalM as f2,avg as f3 "+
        "from  pTest2Minfo2 where snum='"+snum+"' and type = '1' order by date";
    }else {
      	 queryStr = "select date , 0 as time total as f1,totalM as f2,avg as f3 "+
        "from  pTest2Minfo2 where snum='"+snum+"' and date > '"+dateStr+"' and type='1' order by date";
    }
    loadData(queryStr);
 }

 public Test2KD genClosePriceData() throws Exception
 {
    Test2KD t2 = new Test2KD();
    t2.loadData("select date , 0 as time, rp as f1,0 as f2,0 as f3 from plast where date<='"+_dateStr+"' and snum='"+_snum+"' order by date;");
    return t2;
 }
 
}
