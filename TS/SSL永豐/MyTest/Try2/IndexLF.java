import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class IndexLF extends OneFieldBySQL{

 public  IndexLF(String dateStr)throws Exception
 {
 	   loadData("select date , time*100 as time, Idx_A as f1  from pindexs_offical where  date = '"+dateStr+"' order by time;");
 }


}
