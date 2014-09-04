import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;

public class CulMoney6 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static private int getTimeLabel(PamountNew_Rec pmar)
{
    return Integer.parseInt("1"+pmar.time.substring(0,2)+pmar.time.substring(3,5));

}
static public void main(String[] arg)throws Exception{

    PamountNew_Rec pmar = new  PamountNew_Rec();
    PamountNew_Rec pmar2 = null;
    ht = new Hashtable();
    Enumeration e = pmar.SelectBySQL("select * from  Pamountnew where  date = '"+arg[0]+"'  order by snum,date,time "  ); 
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
           	  
           	  
           	  Ds000_Rec   ds000r = new Ds000_Rec();
           	  ds000r.snum = pmar2.snum;
              if(ds000r.SelectInto())
              {
                 float capital = Float.parseFloat(ds000r.capital);
                 if(data.size() > 0)
                 {
                    VocRec3 vr3 = CulLevelweight(data);
                    System.out.println(pmar2.snum+"|"+arg[0]+"|"+vr3.open+"|"+vr3.close+"|"+vr3.inAmount+"|"+(vr3.inMoneyAmt)+"|"+vr3.outAmount+"|"+(vr3.outMoneyAmt)+"|"+vr3.Amount+"|"+(vr3.MoneyAmt)+"|"+(vr3.diff)+"|" );
 	                  data = new Vector();
 	                }
 	             } else
 	             {
 	                  data = new Vector();
 	             }
 	         }
 	         if(getTimeLabel(pmar) == 11330)
           {
             closePrice =  Float.parseFloat(pmar.rp);
           	 data.add(pmar);
           }
           pmar2 = pmar;
     }
     if(data.size() > 0)
     {
        VocRec3 vr3 = CulLevelweight(data);
        System.out.println(pmar2.snum+"|"+arg[0]+"|"+vr3.open+"|"+vr3.close+"|"+vr3.inAmount+"|"+(vr3.inMoneyAmt)+"|"+vr3.outAmount+"|"+(vr3.outMoneyAmt)+"|"+vr3.Amount+"|"+(vr3.MoneyAmt)+"|"+(vr3.diff)+"|" );
      }
}

static VocRec3 CulLevelweight(Vector data)
{
   double moneyAmount = 0;
   PamountNew_Rec pmar = null;
   PamountNew_Rec ppmar = null;
   VocRec3 vr =new VocRec3();
   Vector tempData = null;
   double range = 0.001;
   Enumeration e = data.elements();
   vr.outAmount = 0;
   vr.outMoneyAmt = 0;
   vr.inAmount = 0;
   vr.inMoneyAmt = 0;
   vr.Amount = 0;
   vr.MoneyAmt = 0;
   vr.open = 0;
   vr.outMoneyAmt = 0;
   while(e.hasMoreElements())
   {
      pmar = (PamountNew_Rec)e.nextElement();
      if(ppmar != null)
      {
         if(Float.parseFloat(pmar.rp) == Float.parseFloat(ppmar.rp))
         {
         	  if(pmar.rp.equals(pmar.psell))
         	  {
         	     vr.outAmount += Integer.parseInt(pmar.ra);
         	     vr.outMoneyAmt += Float.parseFloat(pmar.rp) * Integer.parseInt(pmar.ra);
         	  } else {
         	     vr.inAmount += Integer.parseInt(pmar.ra);
         	     vr.inMoneyAmt += Float.parseFloat(pmar.rp) * Integer.parseInt(pmar.ra);
         	  }
         }else  if(Float.parseFloat(pmar.rp) > Float.parseFloat(ppmar.rp))
         {
         	     vr.inAmount += Integer.parseInt(pmar.ra);
         	     vr.inMoneyAmt += Float.parseFloat(pmar.rp) * Integer.parseInt(pmar.ra);
         }else 
         {
          	   vr.outAmount += Integer.parseInt(pmar.ra);
         	     vr.outMoneyAmt += Float.parseFloat(pmar.rp) * Integer.parseInt(pmar.ra);
         } 
      
      } else {
         if(Float.parseFloat(pmar.diff) >= 0)
         {
         	     vr.inAmount += Integer.parseInt(pmar.ra);
         	     vr.inMoneyAmt += Float.parseFloat(pmar.rp) * Integer.parseInt(pmar.ra);
         }else{
          	   vr.outAmount += Integer.parseInt(pmar.ra);
         	     vr.outMoneyAmt += Float.parseFloat(pmar.rp) * Integer.parseInt(pmar.ra);
         }
         vr.open = Float.parseFloat(pmar.rp);
      }      
      vr.Amount += Integer.parseInt(pmar.ra);
      vr.MoneyAmt += Float.parseFloat(pmar.rp) * Integer.parseInt(pmar.ra);
      ppmar = pmar;
   }
   vr.close = Float.parseFloat(pmar.rp);
   vr.diff = Float.parseFloat(pmar.diff);
   return vr;
}

}