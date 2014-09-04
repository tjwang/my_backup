import java.io.*;
import java.net.*;
import java.util.*;

public class CheckDate_A
{
   
   static public void main(String[] arg)throws Exception{
   	  Date d = new Date();
   	  //Sustem.out.println();
   	  if(d.getDay()==0 || d.getDay() == 1)
   	  {
   	     throw new Exception("Not Working Date");
   	  }
   	  int id = (d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate();
   }
}