import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TestLine extends SimpleLineFactory{

public   TestLine(String snum, String d)throws Exception
 {
      super(new OneFieldBySQL("select  date , 0 as time, Avg as f1 from pamountinfo where snum='"+snum+
                "' and date>'"+d+"' order by date;").culSimpleLine());       
 }


}
