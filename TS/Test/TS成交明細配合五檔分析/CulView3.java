import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.tool.*;
import stock.fight.*;

public class CulView3 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

    PamountNew_Rec pmar = null;
    PFive_Rec pfive = new PFive_Rec();
    PFive_Rec pfivetemp = null;
    Ds000_Rec dsr = new Ds000_Rec();
    Enumeration  se = dsr.SelectBySQL("select ds000.snum, ds000.sname, capital from ds000attr,ds000 where tcode = '1' and ds000.snum=ds000attr.snum and ds000.snum='"+arg[1]+"' order by snum;");
   while(se.hasMoreElements())
   {
   	 dsr = (Ds000_Rec)se.nextElement();
   	 pmar = new  PamountNew_Rec();
     Enumeration e1 = pmar.SelectBySQL("select * from  Pamounttemp where  date = '"+arg[0]+"' and snum='"+dsr.snum+"' order by time "  ); 
     FiveSeries fs = new FiveSeries(dsr.snum);
     FiveElement fe = null;
     FiveElement fe2 = null;
     Vector data1 = new Vector();
     while(e1.hasMoreElements())
     {     
          pmar = (PamountNew_Rec)e1.nextElement();
          fe2 = fs.getNearElement(pmar.date,pmar.time);
          if(fe == null) fe = fe2;
          if(fe == fe2)
          {
             data1.add(pmar);
          } else {
             dumpGroup(fe , data1); 
             data1 = new Vector();
          	 FiveElement feTemp = fs.getNextElement(fe);
             while((feTemp != null) && (feTemp != fe2))
             {
                 dumpGroup(feTemp , data1); 
                 feTemp = fs.getNextElement(feTemp);
             }
             fe = fe2;
             data1.add(pmar);
          }
     }
     dumpGroup(fe , data1); 
   }
}


static void  dumpGroup(FiveElement fe, Vector amountV)
{
   System.out.println("======================dumpGroup======================");
   Enumeration e1 = amountV.elements();
   int i = 1;
   while(e1.hasMoreElements())
   {
      PamountNew_Rec pmar = (PamountNew_Rec)e1.nextElement();  
      System.out.println(pmar.dump());
      i++;
   }
   for(;i<4;i++) System.out.println();
   
   if(fe != null)
   {
       PFive_Rec pfive = fe.getRpData();
       
       if(pfive!=null)
       {
          System.out.println(pfive.dump());
          i++;
       }  
       
       System.out.println("Detail:");
       i++;
       PFive_Rec[] pfiveSells = fe.getSellData();
       
       for(int j = pfiveSells.length - 1; j >= 0; j--) 
       {
         if(pfiveSells[j] != null)
         {
             System.out.format(" %1$5s/%2$5s\n", pfiveSells[j].rp, pfiveSells[j].ra);
             i++;
         }
       }
       PFive_Rec[] pfiveBuys = fe.getBuyData();
       for(int j = 0 ; j < pfiveBuys.length; j++) 
       {
          pfive = pfiveBuys[j];
          if(pfive!=null)
          {
             System.out.format("              %1$5s/%2$5s\n", pfive.rp, pfive.ra);
             i++;
          }  
       }
   }    
   for(;i<31;i++) System.out.println();
   
}


static String  converFloat(float f)
{
   int v = (int) ((f+0.001) * 100) ;
   if(v%100 == 0)
   {
     return String.valueOf(v/100) ;
   }else{
   	 int x = v % 100;
     if(x % 10 == 0)
     {
        return String.valueOf(v/100)+"."+String.valueOf(x/10);
     } else {
        return String.valueOf(v/100)+"."+String.valueOf(x/10)+String.valueOf(x%10);
     }
   
   }
}
static String  converTimeSting(String pmarTimeS)
{
    return  String.valueOf(Integer.parseInt("1"+pmarTimeS.substring(0,2)+pmarTimeS.substring(3,5)+pmarTimeS.substring(6,8))%1000000);
}

static boolean FLarger(float f1, float f2)
{
    if((f1 - f2) > 0.001) return true;
    return false;
} 
static boolean FEquals(float f1, float f2)
{
    if(((f1 - f2) < 0.001) && ((f1 - f2) > -0.001)) return true;
    return false;
} 
static boolean FLess(float f1, float f2)
{
    if((f1 - f2) < -0.001) return true;
    return false;
}
}