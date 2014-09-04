import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test3MKD extends ThreeFieldBySQL{

public   Test3MKD(String snum)throws Exception
 {
 	   this(snum, null);
 }
 
public  Test3MKD(String snum,String dateStr)throws Exception
 {
 	  String queryStr = null; 

    if(dateStr == null){
     	 queryStr = "select a.date as date, 0 as time, Bamount as f1,Samount as f2, '0' as f3 "+
         "from  pamountinfo3 as a where a.snum='"+snum+"' order by date";
    }else {
     	 queryStr = "select a.date as date, 0 as time, Bamount as f1,Samount as f2, '0' as f3 "+
         "from  pamountinfo3 as a where a.snum='"+snum+"' and  a.date <= '"+dateStr+"' order by date";
    }
//   System.out.println(queryStr);
    loadData(queryStr);

 }


 
}
