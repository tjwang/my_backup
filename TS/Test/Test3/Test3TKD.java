import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test3TKD extends ThreeFieldBySQL{

public   Test3TKD(String snum)throws Exception
 {
 	   this(snum, null);
 }
 
public  Test3TKD(String snum,String dateStr)throws Exception
 {
  	 String queryStr = null; 
     if(dateStr == null){
      	 queryStr = "select date , time , (BViscosity * 20) as f1, (SViscosity * 20) as f2,  0 as f3 "+
         " from  pamountinfo3_15m where snum='"+snum+"'  and time = '91600' order by date, time";
     }else {
      	 queryStr = "select date , time , (BViscosity * 20) as f1, (SViscosity * 20) as f2,  0 as f3 "+
         "from  pamountinfo3_15m where snum='"+snum+"' and date <= '"+dateStr+"'  and time = '91600' order by date, time";
     }
     loadData(queryStr);
 }


}
