import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class SinKD extends SimpleLineFactory{

 
public  SinKD(int samples)throws Exception
 {
      double dr = 4*(Math.PI*2) / samples;
      double sr = 0;
      Date d = new Date(101,01,01);
      for(int i = 0; i<samples ; i++)
      {
         d = new Date(d.getTime()+86400000);
         add((1900+d.getYear())*10000+((d.getMonth()+1)*100)+d.getDate(), 0, Math.cos(sr)*200 + Math.cos(sr*2)*100 + Math.cos(sr*4)*50 +Math.cos(sr*8)*200);
         sr += dr;
      }
 }


 
}
