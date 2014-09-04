import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.validate.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import stock.pattern.*;

public class Test {
static public void main(String[] arg)throws Exception{
    System.out.println("Test");
   Ds000_Rec dsr = new Ds000_Rec();
   Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr where tcode='1' order by snum;");
    Pattern srcP = new FixedDistancePattern(0.05,10);
//  Pattern srcP = new SimplePattern();
    FixedDistancePattern dstP = new FixedDistancePattern(0.03,10);
    MappingRelation mr = new MappingRelation();
    RelationChecker rc = new RelationChecker();
    FixedRange fr = new FixedRange(0, 5, 10);
    rc.setPassRate(0.5);
    rc.setSrcPattern(srcP);
    rc.setDstPattern(dstP);
    rc.setRelation(mr);
    rc.setRange(fr);
    StockKD stk0001 = new StockKD("0001","20050101");
    KLine kl0001 = stk0001.culKLine();
    if( srcP instanceof SimplePattern )
    {
          StockKD stk2330 = new TestKD("2330","20050101");
          KLine kl2330 = stk2330.culKLine();
          System.out.println("kl0001:"+kl0001.length());
          System.out.println("kl2330:"+kl2330.length());
          rc.check(kl2330, kl0001);
    } else
    {
       while(e.hasMoreElements())
       {
          dsr = (Ds000_Rec)e.nextElement();
          StockKD stk2330 = new TestKD(dsr.snum,"20050101");
          KLine kl2330 = stk2330.culKLine();
   //       System.out.println("kl0001:"+kl0001.length());
   //       System.out.println("kl2330:"+kl2330.length());
          rc.check(kl2330, kl0001);
          System.out.println("-"+dsr.snum+"-");
       }
    }

}
}