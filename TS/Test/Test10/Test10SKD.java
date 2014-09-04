import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test10SKD extends TwoFieldBySQL{

public  Test10SKD(String dateStr,String mnum)throws Exception
 {
     loadData("select a.date, 0 as time, a.outAmt  as f1,  avgIndexC as f2 from pmamountinfo as a,pavgindex_of as b where mnum='"+mnum+"'  and a.date = b.date and  a.date > '"+dateStr+"' ;");
 }
   public Line culAvgSIdxLine(int p)
   {
        Line il = culF2Line();
        Line ml = culF1Line();
        Line malml = culMALine(p);
        LineFactory lf = new SimpleLineFactory(ml.div(il));
        return malml.div(lf.culMALine(p));
   }


}
