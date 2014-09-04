import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test6VL extends OneFieldBySQL{

  public  Test6VL(String dateStr)throws Exception
  {
       System.out.println("select date , 0 as time,  as f1  from pindexs_offical where   date > '"+dateStr+"' and time='1330' group by date order by date;");
       loadData("select date , 0 as time, (sum(Idx_A) / count(date)) as f1  from pindexs_offical where   date > '"+dateStr+"' and time='133000' group by date order by date;");
  }

}
	