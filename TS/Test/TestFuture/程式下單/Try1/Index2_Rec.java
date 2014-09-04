import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import stock.fight.*;

public class Index2_Rec extends KValue implements Try_Rec {
   String date = null;
   String num = null;
   int creat_time = 0;
   int time = 0;
   int Stime = 0;
   int SItime = 0;
   int Ftime = 0;
   float Svalue = 0;
   float SIvalue = 0;
   float Fvalue = 0;
   float Schange = 0;
   float SIchange = 0;
   float Fchange = 0;
   Index2_Rec(String n, String d, int t , int st , int sit , int ft , float sv , float siv, float fv, 
             float sc , float sic, float fc, int ct)
   {
      super(n, Integer.parseInt(d), t, sv);
   	  num = n;
   	  date = d;
   	  time = t;
      Stime = st;
      SItime = sit;
      Ftime = ft;
      Svalue = sv;
      SIvalue = siv;
      Fvalue = fv;
      Schange = sc;
      SIchange = sic;
      Fchange = fc;
   	  creat_time = ct;
   }
   void setCTime(int ctime)
   {
      creat_time = ctime;
   }
   
   public void setCulField(int f)
   {
   	   double dv = Svalue;
       switch(f)
       {
          case 1:
              dv = Svalue; 
              break;
          case 2:
              dv = SIvalue; 
              break;
          case 3:
              dv = Fvalue; 
              break;
          case 4:
              dv = Schange; 
              break;
          case 5:
              dv = SIchange; 
              break;
          case 6:
              dv = Fchange ;
              break;
       }
       kopen = dv;
       khigh = dv;
       klow = dv;
       kclose = dv;
       volume = (float)dv;
       money = (float)dv;
   }
   public int getCreateTime()
   {
       return creat_time;
   }
  public  void dump(){
       System.out.println(" date:"+date+" - "+creat_time+ " :");
       System.out.println(" SIvalue:"+SIvalue+"["+SItime+"]");
       System.out.println(" Svalue:"+Svalue +"["+Stime+"]");
       System.out.println(" Fvalue"+Fvalue+"["+Ftime+"]");
       System.out.println(" SIchange:"+SIchange);
       System.out.println(" Schange:"+Schange);
       System.out.println(" Fchange:"+Fchange);
       System.out.println(" ===========================================");
  }

 }
      
