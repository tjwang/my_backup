import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test6USDrL extends SimpleLineFactory{

public  Test6USDrL(String dateStr)throws Exception
 {
      super(new OneFieldBySQL("select date, 0 as time ,100/sum(rate) as f1 from crate_ntd where  (cnum='USD' or cnum='EUR' or cnum='HKD' or cnum='JPY' or cnum='AUD') and date >'"+dateStr+"'   group by date order by date;").culSimpleLine());       
 }
 
public  Test6USDrL(String dateStr, boolean only)throws Exception
 {
      super(new OneFieldBySQL("select date, 0 as time ,100/rate as f1 from crate_ntd where  cnum='USD' and date >'"+dateStr+"'  order by date;").culSimpleLine());       
 }

}
