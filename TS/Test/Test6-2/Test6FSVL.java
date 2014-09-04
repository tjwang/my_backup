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

}
