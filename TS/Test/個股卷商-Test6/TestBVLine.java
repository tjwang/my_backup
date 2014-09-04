import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TestBVLine extends OneFieldBySQL{//ma = 9


public  TestBVLine(String dateStr, String mnum, String snum)throws Exception
 {
      loadData("select date ,0 as time, inAmount as f1  from pmamount2 where snum='"+snum+"' and mnum='"+mnum+"' and date > '"+dateStr+"' order by date;");
 }
}
