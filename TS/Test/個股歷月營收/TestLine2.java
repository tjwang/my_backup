import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TestLine2 extends SimpleLineFactory{

public   TestLine2(String snum)throws Exception
 {
 //     super(new OneFieldBySQL("select (year*10000+m*100+1) as date , 0 as time, CurMon/100000 as f1"
 //              +" from incomes where snum='"+snum+"'   order by date;").culSimpleLine());       
 
 //       String SQLstr = "select (year*10000+m*100+1) as date , 0 as time, CurMon/LastYear as f1"
 //                        +" from incomes where snum='"+snum+"'   order by date";
 //       Incomes_Rec incomR = new Incomes_Rec(); 
 //       Enumeration e = incomR.SelectBySQL("select * from incomes where snum='"+snum+"' order by date");                
        F2_Rec f2 = new F2_Rec();
        Enumeration<F2_Rec> e = f2.SelectBySQL("select (year*10000+m*100+1) as date , 0 as time, CurMon/LastYear as f1, m as f2"
                  +" from incomes where snum='"+snum+"'   order by date;");
        double[] basedata={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        while(e.hasMoreElements())
        {
            f2 = e.nextElement();
            int idx = Integer.parseInt(f2.f2);
            basedata[idx] = basedata[idx] * Double.parseDouble(f2.f1);
         //   System.out.println(f2.dump()+" com-->"+basedata[idx]);
            add(Integer.parseInt(f2.date), 0, basedata[idx]);
        }
 }

public   TestLine2(String snum,int mode)throws Exception
 {
        if(mode == 1)
        {
            F2_Rec f2 = new F2_Rec();
            Enumeration<F2_Rec> e = f2.SelectBySQL("select (year*10000+m*100+1) as date , 0 as time, (sum(CurMon)/count(*))/100000 as f1,count(*)  as f2 from ds000attr as a,incomes as b "
                  +"where a.snum=b.snum and a.ccode='"+snum+"'  group by year,m ;");
            while(e.hasMoreElements())
            {
                f2 = e.nextElement();
                add(Integer.parseInt(f2.date), 0, Double.parseDouble(f2.f1));
            }
            return;
        }
        F2_Rec f2 = new F2_Rec();
        Enumeration<F2_Rec> e = f2.SelectBySQL("select (year*10000+m*100+1) as date , 0 as time, CurMon/LastYear as f1, m as f2"
                  +" from incomes where snum='"+snum+"'   order by date;");
        double[] basedata={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        while(e.hasMoreElements())
        {
            f2 = e.nextElement();
            int idx = Integer.parseInt(f2.f2);
            add(Integer.parseInt(f2.date), 0, Double.parseDouble(f2.f1));
        }
 }

}
