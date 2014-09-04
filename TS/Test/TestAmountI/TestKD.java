import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TestKD extends StockKD{


public  TestKD(String snum,String dateStr)throws Exception
 {
 	 String queryStr = null; 
   PLDayk_Rec plr = new PLDayk_Rec();
   Enumeration e = plr.SelectBySQL("select snum,date,0 as time,open,close as high,close as low,close,0 as volume,0 as  money from pamountinfo where snum = '"+snum+"' and date>'"+dateStr+"' order by date;");
   while(e.hasMoreElements())
   {
       PLDayk_Rec pldk =   (PLDayk_Rec)e.nextElement();
       add(pldk);
   }
 }


}
