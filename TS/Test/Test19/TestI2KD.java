import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TestI2KD extends StockKD{


public  TestI2KD(String snum,String dateStr,int type)throws Exception
 {
   	
 	 String queryStr = null; 
   PLDayk_Rec plr = new PLDayk_Rec();
   if(type == 1)
   {
      queryStr = "select snum,date,0 as time,Bamount as open,Bamount as high,Bamount as low,Bamount  as close,Bamount as volume,Bamount as money from pamountinfo2 where snum = '"+snum+"' and date>'"+dateStr+"' order by date;";
   }else if(type == 2)
   {
      queryStr = "select snum,date,0 as time,Samount as open,Samount as high,Samount as low,Samount  as close,Samount as  volume,Samount as   money from pamountinfo2 where snum = '"+snum+"' and date>'"+dateStr+"' order by date;";
   }else if(type == 3)
   {
      queryStr = "select snum,date,0 as time,BViscosity as open,BViscosity as high,BViscosity as low,BViscosity  as close,BViscosity as  volume,BViscosity as   money from pamountinfo2 where snum = '"+snum+"' and date>'"+dateStr+"' order by date;";
   }else if(type == 4)
   {
      queryStr = "select snum,date,0 as time,SViscosity as open,SViscosity as high,SViscosity as low,SViscosity  as close,SViscosity as  volume,SViscosity as   money from pamountinfo2 where snum = '"+snum+"' and date>'"+dateStr+"' order by date;";
   }else if(type == 5)
   {
      queryStr = "select snum,date,0 as time,BViscosity as open,BViscosity as high,BViscosity as low,Bamount*Bamount/BViscosity  as close,BViscosity as  volume,BViscosity as   money from pamountinfo2 where snum = '"+snum+"' and date>'"+dateStr+"' order by date;";
   }else if(type == 6)
   {
      queryStr = "select snum,date,0 as time,SViscosity as open,SViscosity as high,SViscosity as low,Samount*Samount/SViscosity  as close,SViscosity as  volume,SViscosity as   money from pamountinfo2 where snum = '"+snum+"' and date>'"+dateStr+"' order by date;";
   } 
   Enumeration e = plr.SelectBySQL(queryStr);
   while(e.hasMoreElements())
   {
         add((PLDayk_Rec)e.nextElement());
   }
 }


}
