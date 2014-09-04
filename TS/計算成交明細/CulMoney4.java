import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;

public class CulMoney4 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

    PamountNew_Rec pmar = new  PamountNew_Rec();
    PamountNew_Rec pmar2 = null;
    ht = new Hashtable();
    Enumeration e = pmar.SelectBySQL("select * from  Pamounttemp where  date = '"+arg[0]+"'  order by snum,date,time "  ); 
    Vector data = new Vector();
    float openPrice = -1;
    float closePrice = -1;
    while(e.hasMoreElements()){
           pmar = (PamountNew_Rec)e.nextElement();
           if(openPrice == -1)
           {
             openPrice = Float.parseFloat(pmar.rp)+Float.parseFloat(pmar.diff) ;
           }
           if((pmar2 !=null) &&(!pmar.snum.equals(pmar2.snum))){
           	  
           	  closePrice =  Float.parseFloat(pmar2.rp);
           	  Ds000_Rec   ds000r = new Ds000_Rec();
           	  ds000r.snum = pmar2.snum;
              if(ds000r.SelectInto())
              {
                 float capital = Float.parseFloat(ds000r.capital);
                 Enumeration ev =  CulLevelweight(data);      
                 while(ev.hasMoreElements())
                 {
                 	VocRec2 vr2 = (VocRec2)ev.nextElement();
                   System.out.println(pmar2.snum+"|"+arg[0]+"|"+vr2.rangeS+"|"+vr2.timeS+"|"+vr2.rangeE+"|"+(vr2.timeE)+"|"+vr2.amount+"|" );
                 }  
              }                
 	            data = new Vector();
 	         }
           data.add(pmar);
           pmar2 = pmar;
     }
     Enumeration ev =  CulLevelweight(data);      
     while(ev.hasMoreElements())
     {
     	   VocRec2 vr2 = (VocRec2)ev.nextElement();
         System.out.println(pmar2.snum+"|"+arg[0]+"|"+vr2.rangeS+"|"+vr2.timeS+"|"+vr2.rangeE+"|"+(vr2.timeE)+"|"+vr2.amount+"|" );
     }                  
}

static Enumeration CulLevelweight(Vector data)
{
   double moneyAmount = 0;
   PamountNew_Rec pmar = null;
   PamountNew_Rec ppmar = null;
   VocRec2 vr =null;
   Vector v =new Vector();
   Vector tempData = null;
   double range = 0;
   Enumeration e = data.elements();
   Enumeration tempe = (new Vector()).elements();
   while(e.hasMoreElements() || tempe.hasMoreElements())
   {
      if(tempe.hasMoreElements())
      {
         pmar = (PamountNew_Rec)tempe.nextElement();
      }else
      {
         pmar = (PamountNew_Rec)e.nextElement();
      }
      if(ppmar != null) 
      {
         if((Float.parseFloat(pmar.rp) != Float.parseFloat(ppmar.rp)) && (tempData == null))
         {
         	 tempData = new Vector();
         }
         if( ((Float.parseFloat(pmar.rp) - Float.parseFloat(ppmar.rp)) < range) && 
             ((Float.parseFloat(pmar.rp) - Float.parseFloat(ppmar.rp)) > -range) &&
             e.hasMoreElements()
               )  
         {
               vr.amount += Integer.parseInt(pmar.ra);
//               System.out.println(pmar.time+" "+range+" - -"+pmar.rp);
         }else 
         {
            vr.amount += Integer.parseInt(pmar.ra);
            vr.rangeE = Float.parseFloat(pmar.rp);
            vr.timeE = pmar.time;
            v.add(vr);
            vr = new VocRec2();
            if (tempData!=null)
            {
               tempData.add(pmar);
            }
            while(tempe.hasMoreElements())
            {
               if (tempData==null)
               {
                   tempData = new Vector();
               }
               tempData.add(tempe.nextElement());
            }
            if(tempData != null)
            {
              if(e.hasMoreElements())
                tempe = tempData.elements();
              else
                e = tempData.elements();
            }
            ppmar = null;
            tempData = null;
         } 
      
      } else {
         if(vr==null)
         {
            vr = new VocRec2();
         }
         vr.amount = Integer.parseInt(pmar.ra);
         vr.rangeS = Float.parseFloat(pmar.rp);
         vr.timeS = pmar.time;
         if(vr.rangeS  > 500)
         {
           range = 1 * 3;
         }else if(vr.rangeS  > 100){
           range = 0.5 * 3;
         }else if(vr.rangeS  > 50){
           range = 0.1 * 3;
         }else {
           range = 0.05 * 3;
         }
         ppmar = pmar;
      }      
      if (tempData!=null)
      {
         tempData.add(pmar);
      }
   }
//   if(vr.amount > 0)  v.add(vr);
   return v.elements();
}
/*
static VocRec CulDViscosity(Vector data)
{
   Enumeration e = data.elements();
   double moneyAmount = 0;
   PamountNew_Rec pmar = null;
   PamountNew_Rec ppmar = null;
   VocRec vr =null;
   Vector v =new Vector();
   while(e.hasMoreElements())
   {
      pmar = (PamountNew_Rec)e.nextElement();
      if(ppmar != null)
      {
         if(Float.parseFloat(pmar.rp) < Float.parseFloat(ppmar.rp))
         {
            if(vr == null)
            {
               vr = new VocRec();
               vr.amount = Integer.parseInt(pmar.ra);
               vr.range = (Float.parseFloat(ppmar.rp) - Float.parseFloat(pmar.rp));
            }else{
               vr.amount += Integer.parseInt(pmar.ra);
               vr.range += (Float.parseFloat(ppmar.rp) - Float.parseFloat(pmar.rp));
          
            }
         }  else 
         {
             if(vr != null)
             { 
                 v.add(vr);
                 vr = null;
             }
         } 
      
      } else {
         if(Float.parseFloat(pmar.diff) < 0)
         {
               vr = new VocRec();
               vr.amount = Integer.parseInt(pmar.ra);
               vr.range = 0 - Float.parseFloat(pmar.diff);
         }
      }        
      ppmar = pmar;
   }
   if(vr != null)
   {    
   	 v.add(vr);
   }
   VocRec vr2 =new VocRec();;
   vr2.amount = 0;
   vr2.range = 0;
   e = v.elements();
   while(e.hasMoreElements())
   {
      vr = (VocRec)e.nextElement();
      vr2.amount += vr.amount;
      vr2.range += vr.range;
   }
   return vr2;

}
*/

}