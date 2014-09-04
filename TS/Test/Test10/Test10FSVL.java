import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test10FSVL extends OneFieldBySQL{

public  Test10FSVL(String dateStr,String mnum)throws Exception
 {
      loadData("select date ,0 as time, outAmt as f1  from pmamountinfo where  mnum='"+mnum+"' and date > '"+dateStr+"' order by date;");
 }


}
