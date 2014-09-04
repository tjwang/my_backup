import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test3KD extends ThreeFieldBySQL{

 public   Test3KD(String snum)throws Exception
 {
 	   this(snum, null);
 }
 
 public  Test3KD(String snum,String dateStr)throws Exception
 {
 	  String queryStr = null; 
    if(dateStr == null){
     	 queryStr = "select a.date as date, 0 as time, BViscosity as f1,SViscosity as f2, close as f3 "+
         "from  pamountinfo as a,pamountinfo3 as b where a.snum='"+snum+"' and a.date=b.date  order by date";
    }else {
     	  queryStr = "select date , 0 as time, BViscosity as f1,SViscosity as f2, 0 as f3 "+
         "from  pamountinfo3 as a where a.snum='"+snum+"' and  a.date <= '"+dateStr+"'   order by date";
    }
    loadData(queryStr);
 }


}
