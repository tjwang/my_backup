import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test10VL extends OneFieldBySQL{

public  Test10VL(String dateStr)throws Exception
 {
      loadData("select date , 0 as time, close as f1  from pldayk where  snum='0001' and date > '"+dateStr+"' ;");
 }


}
