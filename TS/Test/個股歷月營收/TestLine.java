import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TestLine extends SimpleLineFactory{

public   TestLine(String snum)throws Exception
 {
      super(new OneFieldBySQL("select (year*10000+m*100+1) as date , 0 as time, CurMon/100000 as f1"
               +" from incomes where snum='"+snum+"'   order by date;").culSimpleLine());       
 }


}
