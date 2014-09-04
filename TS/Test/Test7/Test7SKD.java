import stock.fight.*;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class Test7SKD {


 static public void main(String[] arg)throws Exception
 {
   PLast_Rec plr = new PLast_Rec();
   Enumeration e = plr.SelectBySQL("select * from plast where snum = '2454' and date>'"+arg[0]+"' order by date;");
   PCPPrice_Rec pcpr = new PCPPrice_Rec();
   while(e.hasMoreElements())
   {
   	   plr = (PLast_Rec)e.nextElement();
       Enumeration e2 = pcpr.SelectBySQL("select * from pcpprice where date='"+plr.date+"' and cp_month='"+arg[1]+"' order by type,price");
       Vector v = new Vector();
       int Hp = 0 ; 
       int Lp = 99999;
       PCPPrice_Rec Crp = null;
       PCPPrice_Rec Prp = null;
       while(e2.hasMoreElements())
       {
           pcpr = (PCPPrice_Rec)e2.nextElement();
           if(Integer.parseInt(pcpr.price) > Hp)
           {
              Hp = Integer.parseInt(pcpr.price);
           }
           if(Integer.parseInt(pcpr.price) < Lp)
           {
              Lp = Integer.parseInt(pcpr.price);
           }
           if(pcpr.type.equals("C"))
           {
              if((Crp == null) || (Integer.parseInt(Crp.ra) < Integer.parseInt(pcpr.ra)))
              {
                 Crp =  pcpr;
              }
           }
            if(pcpr.type.equals("P"))
           {
              if((Prp == null) || (Integer.parseInt(Prp.ra) < Integer.parseInt(pcpr.ra)))
              {
                 Prp =  pcpr;
              }
          
           }
           v.add(pcpr);
       }
       Hp += 200 ;
       Lp -= 200 ;
 //      System.out.println("Hp: "+ Hp +" Lp: "+Lp);
 //      System.out.println("Crp: "+ Crp.dump());
//       System.out.println("Prp: "+ Prp.dump());
       int ChitCount = 0;
       float ChitP = 0;
       int PhitCount = 0;
       float PhitP = 0;
       for(int price = Lp ; price <= Hp ; price+=25)
       {
           e2 = v.elements();
           v = new Vector();
           PCPPrice_Rec OmCrp = null;
           PCPPrice_Rec OmPrp = null;
           while(e2.hasMoreElements())
           {
              pcpr = (PCPPrice_Rec)e2.nextElement();
              if(pcpr.type.equals("C"))
              {
                 if((OmCrp == null) || (getProfit(OmCrp,price) < getProfit(pcpr,price)))
                 {
                    OmCrp =  pcpr;
                 }
              }
               if(pcpr.type.equals("P"))
              {
                 if((OmPrp == null) || (getProfit(OmPrp,price) < getProfit(pcpr,price)))
                 {
                    OmPrp =  pcpr;
                 }
             
              }
              v.add(pcpr);
          }
          if (OmCrp == Crp)
          {
             ChitP += price;
             ChitCount ++;
          }
          if (OmPrp == Prp)
          {
             PhitP += price;
             PhitCount ++;
          }
       }
       System.out.println("Get Call price :"+(ChitP/ChitCount)+" -- "+ Crp.dump());
       System.out.println("Get Put price :"+(PhitP/PhitCount)+" -- "+ Prp.dump());
   } 
 }
 static float getProfit(PCPPrice_Rec pcpr,int price)
 {
     int cprPrice = Integer.parseInt(pcpr.price);
     float rp = Float.parseFloat(pcpr.rp);
     try{ 
       int irp = Integer.parseInt(pcpr.rp);
       if(irp == 0)
       {
          return -1;
       }
     }catch(Exception e)
     {
     }
     if("C".equals(pcpr.type))
     {
     	   return ((float)(price - cprPrice)) / rp ; 
     }
     if("P".equals(pcpr.type))
     {
     	   return ((float)(cprPrice - price)) / rp ; 
     }
     return -1;
 }
}
