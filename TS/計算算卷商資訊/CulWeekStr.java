import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CulWeekStr {

 static public void main(String[] arg)throws Exception{
    Date d = new Date(108,5,1);
    Date d2 = new Date(108,5,8);
    Date td = new Date();
    while(d2.getTime() < td.getTime())
    {
       System.out.println("java -Xmx1000M CulM "+d2s(d)+" "+d2s(d2)+" > data"+d2s(d2)+".txt");
       d = new Date(d.getTime()+86400000*7);	
       d2 = new Date(d2.getTime()+86400000*7);	
    } 
    
 }
 static String d2s(Date d)
 {
  return  String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());
 }

}
