import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test62FBVL extends SimpleLineFactory{

public  Test62FBVL(String mnum)throws Exception
 {
   super(new OneFieldBySQL("select date, 0 as time ,totalAmt/10000  as f1 from pmamountinfo where mnum='"+mnum+"'   order by date;").culSimpleLine().summation());       
 }

}
