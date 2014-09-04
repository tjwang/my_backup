import stock.fight.*;
import stock.db.*;
import stock.app.*;
import stock.sandy.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test6CKD extends TwoFieldBySQL{
	
   public  Test6CKD(String dateStr)throws Exception
   {
        loadData("select a.date, 0 as time ,a.inAmount/100000 as f1, avgIndexM as f2 from pinoutsum_official as a,pavgindex_of as b where type ='F' and a.date = b.date  and  a.date > '"+dateStr+"' order by date;");
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
