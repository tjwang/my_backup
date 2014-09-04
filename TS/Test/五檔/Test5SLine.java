import stock.db.*;
import stock.sandy.*	;
import stock.fight.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test5SLine extends OneFieldBySQL{
 
public  Test5SLine(String snum, String type)throws Exception
 {
   loadData("select date  , 0 as time, sum(ra) as f1,"
               +"from pfive_infoA where snum='"+snum+"' and type='"+type+"' group by snum,date order by date");
 }

}
