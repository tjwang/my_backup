import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test11SKD extends OneFieldBySQL{

public  Test11SKD(String dateStr)throws Exception
 {
 	   loadData("select date ,0 as time, amountNow / 100000 as f1  from pmarginm where  date > '"+dateStr+"' and type = 'M' order by date;");
 }


}
