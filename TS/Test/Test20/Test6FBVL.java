import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test6FBVL extends OneFieldBySQL{

public  Test6FBVL(String dateStr)throws Exception
 {
       loadData("select date ,0 as time, inAmount/100000000 as f1  from pinoutsum_official where  type='F' and date > '"+dateStr+"' order by date;");
 }

public  Test6FBVL(String dateStr, int type)throws Exception
 {
 	   switch(type)
 	   {
 	   	  case 1:
          loadData("select date ,0 as time, Tcall as f1  from pxfsum where kind='TXF' and type='F' and date > '"+dateStr+"' order by date;");
          break;
 	   	  case 2:
          loadData("select date ,0 as time, TcallM*5/Tcall as f1  from pxfsum where kind='TXF' and type='F' and date > '"+dateStr+"' order by date;");
          break;
 	   	  case 3:
          loadData("select date ,0 as time,  pxfsum.callM*5/pxfsum.call as f1  from pxfsum where kind='TXF' and type='F' and date > '"+dateStr+"' order by date;");
          break;
     }
 }

}
