import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test6FBVL extends OneFieldBySQL{//ma = 9

 public  Test6FBVL(String dateStr)throws Exception
 {
      loadData("select date, 0 as time, inAmount as f1  from pinoutsum where  type='F' and date > '"+dateStr+"' order by date;");
 }

 public  Test6FBVL(String dateStr,String type)throws Exception
 {
      loadData("select date, 0 as time, inAmount as f1  from pinoutsum where  type='"+type+"' and date > '"+dateStr+"' order by date;");
 }

}