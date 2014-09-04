import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class WSKD extends OneFieldBySQL{

public  WSKD(String wcode,String dateStr)throws Exception
 {
 	   System.out.println("select date ,0 as time, close as f1  from pwdayk where  date > '"+dateStr+"' and wcode = '"+wcode+"' order by date;");
 	   loadData("select date ,0 as time, close as f1  from pwdayk where  date > '"+dateStr+"' and wcode = '"+wcode+"' order by date;");
 }


}
