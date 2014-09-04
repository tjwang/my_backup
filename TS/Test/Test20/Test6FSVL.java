import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test6FSVL extends OneFieldBySQL{

public  Test6FSVL(String dateStr)throws Exception
 {
      loadData("select date ,0 as time, outAmount/100000000 as f1  from pinoutsum_official where  type='F' and date > '"+dateStr+"' order by date;");
 }

public  Test6FSVL(String dateStr, int type)throws Exception
 {
 	   switch(type)
 	   {
 	   	  case 1:
          loadData("select date ,0 as time, Tput as f1  from pxfsum where kind='TXF' and type='F' and date > '"+dateStr+"' order by date;");
          break;
 	   	  case 2:
          loadData("select date ,0 as time, TputM*5/Tput as f1  from pxfsum where kind='TXF' and type='F' and date > '"+dateStr+"' order by date;");
          break;
 	   	  case 3:
          loadData("select date ,0 as time,  pxfsum.putM*5/pxfsum.put as f1  from pxfsum where kind='TXF' and type='F' and date > '"+dateStr+"' order by date;");
          break;
     }
 }
}
