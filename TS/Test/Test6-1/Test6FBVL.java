import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test6FBVL extends SimpleLineFactory{

public  Test6FBVL(String dateStr)throws Exception
 {
      super(new OneFieldBySQL("select date, 0 as time ,totalAmount as f1 from pinoutsum where  type='F'  order by date;").culSimpleLine().summation());       
 }

}
