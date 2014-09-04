import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class QueryMTop10
{
  static public void main(String[] arg) throws Exception
  {
      System.out.println("TestQueryTop10: ");
      PMname_Rec mnr= new PMname_Rec();
      Enumeration<PMname_Rec> me = mnr.SelectBySQL("select * from pmname2 ;");
       
      while(me.hasMoreElements())
      {
         mnr = me.nextElement();
         F3_Rec pr= new F3_Rec();                                          
         Enumeration<F3_Rec> e = pr.SelectBySQL("select snum as date,sname as time, mname as f1, avt as f2, rr as f3 from pmname2 as pmname,"+
                                                             "(select a.*, ds000.sname,a.t/ds000.capital as rr from ds000,"+ /*mt as rr */
                                                              "( select  snum,mnum,sum(Amount) as t,sum(MoneyAmt) as mt , sum(MoneyAmt)/sum(Amount) as avt,avg(Rate) as avr "+
                                                       " from pmamountinfo_week2 where mnum='"+mnr.mnum+"' and date > '"+arg[0]+"'  and date < '"+arg[1]+"' group by snum,mnum) as a where ds000.snum=a.snum) as aa"+
                                                  " where pmname.mnum=aa.mnum order by rr desc limit 3");

         while(e.hasMoreElements()){
           	 pr = e.nextElement();
           	 pr.time = new String(pr.time.getBytes("ISO-8859-1"),"MS950");
          	 pr.f1 = new String(pr.f1.getBytes("ISO-8859-1"),"MS950");
             System.out.println(pr.dump());
         }
      }
    
  }


}