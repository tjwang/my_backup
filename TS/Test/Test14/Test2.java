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

public class Test2 {
static public void main(String[] arg)throws Exception{


//    StockKD stk0001 = new StockKD("0001","20050101");
    StockKD stk0001 = new TestKD("2330","20070101");
    KLine kl0001 = stk0001.culKLine();
    StockKD stk2330 = new TestKD("2330","20070101");
    KLine kl2330 = stk2330.culKLine();

    Pattern srcP = new SimplePattern();
    MappingRelation mr = new MappingRelation();

    RelationChecker rc = new RelationChecker();
   rc.setRelation(mr);

    RelationChecker rc2 = new RelationChecker();
    rc2.setRelation(mr);

    System.out.println("kl0001:"+kl0001.length());
    System.out.println("kl2330:"+kl2330.length());
    FixedAvgPattern dstP = new FixedAvgPattern(0.04, 5, 10);
    FixedRange fr = new FixedRange(0, 1, 15);
    rc.setRange(fr);
    rc2.setRange(fr);
    rc.setSrcPattern(srcP);
    rc.setDstPattern(dstP);
    rc2.setDstPattern(dstP);
    double passRate1 = rc.checkValue(kl2330, kl0001);
    System.out.println();

 for(int i = -15; i < 15; i++)
 {
    for(int j = 1; j < 15; j++)
    {
      Pattern srcP2 = new FixedDistancePattern(0.01 * i, j);

      rc2.setSrcPattern(srcP2);
 
      double passRate2 = rc2.checkValue(kl2330, kl0001);
      System.out.print(" test i: "+ i);
      System.out.println(" passRate1 : "+passRate1+" passRate2:"+passRate2);
    }
}

}
}