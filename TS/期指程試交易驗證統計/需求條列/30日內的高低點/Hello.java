import stock.db.*;
import stock.fight.*;
import stock.tool.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.lang.*;

public class Hello
{
   public static void main(String[] arg)throws Exception
   {
       StockKD  skd = new StockKD("0001","20140101");
       Date startd = GMethod.s2d(arg[0]);
       startd = new Date(startd.getTime()-86400000*30);
       Line kl = skd.culKLine();
       for(int i=0; i<kl.length(); i++)
       {
          Value v = kl.valueAt(i);
          if(v.getDateTime() >= startd.getTime() && i+30 < kl.length())
          {
              Line l2 = kl.sub(i,i+30);
              System.out.println("================================================");
              l2.dump();
              System.out.println(l2.getMax()+"|"+l2.getMin());
              System.out.println("================================================\r\n\r\n");
          }
       }       
       System.out.println("Hello");
   }

}
