import java.io.*;
import java.net.*;
import java.util.*;

public class CheckDateKaiGi
{
   static public void main(String[] arg)throws Exception{
   	  Date d = new Date();
   	  //Sustem.out.println();
   	  if(d.getDay() != 5)
   	  { 
   	     throw new Exception("Not Working Date");
   	  }
      //String ds = String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());
      //System.out.println(ds);
   }
}