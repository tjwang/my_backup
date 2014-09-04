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

public class Test3 {
static public void main(String[] arg)throws Exception{
    System.out.println("Test");
    Ds000_Rec dsr = new Ds000_Rec();
    Enumeration e = dsr.SelectBySQL("select snum,sname,'0' as capital from ds000attr where tcode='1' order by snum;");
    Pattern srcp = new FixedDistance2Pattern(0.14,20);
    StockKD stk2330 = new TestKD("2330","20050101");
    Line l2330 = stk2330.culSimpleLine();
    Line  pl = l2330.patternByStep(srcp);
    pl.dump();
}
}