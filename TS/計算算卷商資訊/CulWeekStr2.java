import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CulWeekStr2 {

 static public void main(String[] arg)throws Exception{
 	  int dint = Integer.parseInt(arg[0]); 
    Date d = new Date(dint/10000- 1900,(dint %10000)/100 -1,dint % 100);
    System.out.println(d2s(new Date(d.getTime() - (d.getDay()*86400000))));
 }
 static String d2s(Date d)
 {
  return  String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());
 }

}
