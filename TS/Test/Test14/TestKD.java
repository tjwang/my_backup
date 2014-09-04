import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.validate.*	;
public class TestKD extends StockKD{
/*   public  TestKD(String snum,String dateStr)throws Exception
    {
      super(new Vector());
      PLast_Rec plr= new PLast_Rec();
      dayKdata = new Vector();
      Enumeration e = null;
      if(dateStr == null){
         e = plr.SelectBySQL("select * from  plast where   snum='"+snum+"' order by date,time "  ); 
      }else {
         e = plr.SelectBySQL("select * from  plast where   snum='"+snum+"' and date > '"+dateStr+"' order by date,time "  );    
      }
      while(e.hasMoreElements()){
      	plr = (PLast_Rec)e.nextElement();
      	PLDayk_Rec  pkr = new PLDayk_Rec();
      	pkr.snum = plr.snum;
      	pkr.date = plr.date; 
      	pkr.time = "0";
      	pkr.open = plr.rp;
      	pkr.high = plr.rp;
      	pkr.low  = plr.rp;
      	pkr.close= plr.rp;
      	pkr.volume= plr.ra;
      	pkr.money= plr.ra;
        dayKdata.add(pkr);
      }
    }*/
   public  TestKD(String snum,String dateStr)throws Exception
    {
      PLDayk_Rec plr= new PLDayk_Rec();
      Enumeration e = null;
      if(dateStr == null){
         e = plr.SelectBySQL("select * from  pldayksnum where   snum='"+snum+"' order by date,time "  ); 
      }else {
         e = plr.SelectBySQL("select * from  pldayksnum where   snum='"+snum+"' and date > '"+dateStr+"' order by date,time "  );    
      }
      while(e.hasMoreElements()){
      	plr = (PLDayk_Rec)e.nextElement();
        add(plr);
      }
    }
	
}
