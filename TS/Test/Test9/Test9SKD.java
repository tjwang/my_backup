import stock.fight.*;
import stock.db.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test9SKD extends TwoFieldBySQL{

public  Test9SKD(String dateStr)throws Exception
 {
       loadData("select * from pavgindex where date > '"+dateStr+"' order by date");
 }
 
 static public void main(String[] arg)throws Exception
 {
    StockKD skd  = new StockKD(arg[0],arg[1]);
    int up = 0;
    int down = 0;
    for(int i=9;i < 120;i++)
    for(int j=8;j < 31; j++)
    for(int k=5;k < 9; k++)
    {
       KDLine kdl =  skd.culKDLine(i,j,k);
       KDValue kdv = (KDValue) kdl.valueAt(kdl.length()-1);
       if(kdv.isAbove())
       {
       	 up++;
       } else {
         down++;
       }
       
    }
//    kdl.scanDiff(arg[1], true);
//    System.out.println("k value:"+kdv.getKValue()+" d value:"+kdv.getDValue());
    System.out.println("up value:"+up+" down value:"+down);
 }

}
