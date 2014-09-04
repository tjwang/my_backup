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


}
