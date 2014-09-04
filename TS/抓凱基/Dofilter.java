import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Dofilter {

 static public void main(String[] arg)throws Exception{
   BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(arg[0])));
   String s = "";
   Date d = new Date((new Date()).getTime()+86400000);
   PMAmount_Rec pmr = new PMAmount_Rec();
   pmr.SelectBySQL("select * from pmamount2 where snum='2454' and date>'20140101' order by date");
 
   int basedate = Integer.parseInt(pmr.date);
   int basedate2 = (d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate();
//   int basedate = 20140606;
//   int basedate2 = 20140615;
   while((s=br.readLine())!=null){
      if((s.indexOf("|0|0|0|") < 0) && (s.indexOf("|0|0|-0|") < 0))
      {
      	 if((Integer.parseInt(s.substring(s.indexOf('|')+1,s.indexOf('|')+9)) > basedate) && (Integer.parseInt(s.substring(s.indexOf('|')+1,s.indexOf('|')+9))< basedate2))
      	 {
            System.out.println(s);
         }
      }
   }
 }

}
