package stock.tool;
import java.util.*;
import stock.db.*;

public class GMethod {
  static public Date s2d(String ds)
  {
       return  new Date(Integer.parseInt(ds.substring(0,4))-1900,
                        Integer.parseInt(ds.substring(4,6))-1,
                        Integer.parseInt(ds.substring(6,8)));
  }
  
  static public Date s2d(String ds,String ts)
  {
       int time_i = 0;
	   if(ts.length() == 6)
       {
           time_i = Integer.parseInt("1"+ts)-1000000;
	   } else if(ts.length() == 5)
       {
           time_i = Integer.parseInt(ts);
	   }
	   return  new Date(Integer.parseInt(ds.substring(0,4))-1900,
						Integer.parseInt(ds.substring(4,6))-1,
						Integer.parseInt(ds.substring(6,8)),
						time_i/10000,
						(time_i%10000)/100,
						time_i%100);
	    
  }

  static public String d2s(Date d)
  {
       return  String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());
  }
  
  static public String getBWorkDay(String ds, String snum, int count)throws Exception
  {
     Date d = s2d(ds);
     int i = 0;
     PLast_Rec plr = new PLast_Rec();
     while(i<count)
     {
     	  d = new Date(d.getTime()-86400000);	
        if( plr.SelectBySQL("select * from plast where snum = '"+snum+"' and date ='"+d2s(d)+"' order by date;").hasMoreElements())
        {
           i++;
        }
        if(Integer.parseInt(d2s(d)) < 20090811)
        {
           return d2s(d);
        }
     }
     return d2s(d);
 }
}
