import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test6CKD extends TwoFieldBySQL{ //1 ,ma = 8

   public  Test6CKD(String dateStr)throws Exception
   {
        loadData("select a.date, 0 as time, a.inAmount*1000 as f1,  avgIndexC as f2 from pinoutsum as a, pavgindex_of as b where type ='F' and a.date = b.date and  a.date > '"+dateStr+"' order by date;");
   }

   public  Test6CKD(String dateStr,String type)throws Exception
   {
        loadData("select a.date, 0 as time, a.inAmount*1000 as f1,  avgIndexC as f2 from pinoutsum as a, pavgindex_of as b where type ='"+type+"' and a.date = b.date and  a.date > '"+dateStr+"' order by date;");
   }
   
   public Line culAvgBIdxLine(int p)
   {
        Line il = culF2Line();
        Line ml = culF1Line();
        Line malml = culMALine(p);
        LineFactory lf = new  SimpleLineFactory(ml.div(il));
        return malml.div(lf.culMALine(p));
   }

}
