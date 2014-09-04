import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test6OVL extends SimpleLineFactory{

public  Test6OVL(String dateStr)throws Exception
 {
   super(new OneFieldBySQL("select date, 0 as time ,(totalAmount/100000000) as f1 from pinoutsum_official where  type='F'  order by date;").culSimpleLine().summation());       
 }


}
