import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;

public class CulMoney {
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
             openPrice = Float.parseFloat(pmar.rp);
           }
           if((pmar2 !=null) &&(!pmar.snum.equals(pmar2.snum))){
           	  
//           	  System.err.println(pmar.snum);
           	  closePrice =  Float.parseFloat(pmar2.rp);
           	  Ds000_Rec   ds000r = new Ds000_Rec();
           	  ds000r.snum = pmar2.snum;
              if(ds000r.SelectInto())
              {
                  float capital = Float.parseFloat(ds000r.capital);
                  /*            
                   System.out.println(pmar2.snum+"|"+openPrice+"|"+closePrice+"|"+(Float.parseFloat(pmar2.total)/capital)+"|"
                                     +CulAvg(data)+"|"
                                     +CulHigherPriceAvg(data,openPrice,(closePrice>=openPrice))+"|"+CulLowerPriceAvg(data,openPrice,(closePrice<openPrice))+"|"
                                     +CulHigherPriceAvg(data,closePrice,(closePrice>openPrice))+"|"+CulLowerPriceAvg(data,closePrice,(closePrice<=openPrice))+"|"
                                     +CulHigherPriceAmount(data,openPrice,(closePrice>=openPrice))+"|"+CulLowerPriceAmount(data,openPrice,(closePrice<openPrice))+"|"
                                     +CulHigherPriceAmount(data,closePrice,(closePrice>openPrice))+"|"+CulLowerPriceAmount(data,closePrice,(closePrice<=openPrice))+"|"
                                     );
                    */
                    ht.put(pmar2.snum, pmar2.snum+"|"+arg[0]+"|"+openPrice+"|"+closePrice+"|"+(Float.parseFloat(pmar2.total)/capital)+"|"
                                     +CulAvg(data)+"|"
                                     +CulHigherPriceAvg(data,openPrice,(closePrice>=openPrice))+"|"+CulLowerPriceAvg(data,openPrice,(closePrice<openPrice))+"|"
                                     +CulHigherPriceAvg(data,closePrice,(closePrice>openPrice))+"|"+CulLowerPriceAvg(data,closePrice,(closePrice<=openPrice))+"|"
                                     +CulHigherPriceAmount(data,openPrice,(closePrice>=openPrice))+"|"+CulLowerPriceAmount(data,openPrice,(closePrice<openPrice))+"|"
                                     +CulHigherPriceAmount(data,closePrice,(closePrice>openPrice))+"|"+CulLowerPriceAmount(data,closePrice,(closePrice<=openPrice))+"|"
                                     );
              }
 	            openPrice = Float.parseFloat(pmar.rp);
 	            data = new Vector();
 	         }
           data.add(pmar);
           pmar2 = pmar;
     }
     {
           closePrice =  Float.parseFloat(pmar2.rp);
           Ds000_Rec   ds000r = new Ds000_Rec();
           ds000r.snum = pmar2.snum;
           ds000r.SelectInto();
           float capital = Float.parseFloat(ds000r.capital);
      	   ht.put(pmar2.snum, pmar2.snum+"|"+arg[0]+"|"+openPrice+"|"+closePrice+"|"+(Float.parseFloat(pmar2.total)/capital)+"|"
                           +CulAvg(data)+"|"
                           +CulHigherPriceAvg(data,openPrice,(closePrice>=openPrice))+"|"+CulLowerPriceAvg(data,openPrice,(closePrice<openPrice))+"|"
                           +CulHigherPriceAvg(data,closePrice,(closePrice>openPrice))+"|"+CulLowerPriceAvg(data,closePrice,(closePrice<=openPrice))+"|"
                           +CulHigherPriceAmount(data,openPrice,(closePrice>=openPrice))+"|"+CulLowerPriceAmount(data,openPrice,(closePrice<openPrice))+"|"
                           +CulHigherPriceAmount(data,closePrice,(closePrice>openPrice))+"|"+CulLowerPriceAmount(data,closePrice,(closePrice<=openPrice))+"|"
                  );
     
     }
     CulMData(arg[0]);
}

static float CulAvg(Vector data)
{
   Enumeration e = data.elements();
   double moneyAmount = 0;
   PamountNew_Rec pmar = null;
   while(e.hasMoreElements())
   {
      pmar = (PamountNew_Rec)e.nextElement();
//      System.out.println(pmar.dump());
      moneyAmount += Float.parseFloat(pmar.rp) *  Integer.parseInt(pmar.ra);
   }
   if(Integer.parseInt(pmar.total) == 0)
     return 0;

   return (float)(moneyAmount / Integer.parseInt(pmar.total));
}

static float CulHigherPriceAvg(Vector data,float price, boolean eqFlag)
{
   Enumeration e = data.elements();
   double moneyAmount = 0;
   PamountNew_Rec pmar = null;
   int total = 0;
   while(e.hasMoreElements())
   {
      pmar = (PamountNew_Rec)e.nextElement();
      if ((Float.parseFloat(pmar.rp) > price) || ((eqFlag) && 
          ((Float.parseFloat(pmar.rp)-price)<0.01) && ((Float.parseFloat(pmar.rp)-price)>-0.01)))
      {
        moneyAmount += Float.parseFloat(pmar.rp) *  Integer.parseInt(pmar.ra);
        total += Integer.parseInt(pmar.ra);
      }
   }
   if(total == 0)
     return 0;
   return (float)(moneyAmount / total);

}


static float CulLowerPriceAvg(Vector data,float price,boolean eqFlag)
{
   Enumeration e = data.elements();
   double moneyAmount = 0;
   PamountNew_Rec pmar = null;
   int total = 0;
   while(e.hasMoreElements())
   {
      pmar = (PamountNew_Rec)e.nextElement();
      if ((Float.parseFloat(pmar.rp) < price) || ((eqFlag) && 
          ((Float.parseFloat(pmar.rp)-price)<0.01) && ((Float.parseFloat(pmar.rp)-price)>-0.01)))
      {
        moneyAmount += Float.parseFloat(pmar.rp) *  Integer.parseInt(pmar.ra);
        total += Integer.parseInt(pmar.ra);
      }
   }
   if(total == 0)
     return 0;
   return (float)(moneyAmount / total);

}

static int CulHigherPriceAmount(Vector data,float price,boolean eqFlag)
{
   Enumeration e = data.elements();
   double moneyAmount = 0;
   PamountNew_Rec pmar = null;
   int total = 0;
   while(e.hasMoreElements())
   {
      pmar = (PamountNew_Rec)e.nextElement();
      if ((Float.parseFloat(pmar.rp) > price) || ((eqFlag) && 
          ((Float.parseFloat(pmar.rp)-price)<0.01) && ((Float.parseFloat(pmar.rp)-price)>-0.01)))
      {
        total += Integer.parseInt(pmar.ra);
      }
   }
   return  total;

}

static int CulLowerPriceAmount(Vector data,float price,boolean eqFlag)
{
   Enumeration e = data.elements();
   double moneyAmount = 0;
   PamountNew_Rec pmar = null;
   int total = 0;
   while(e.hasMoreElements())
   {
      pmar = (PamountNew_Rec)e.nextElement();
      if ((Float.parseFloat(pmar.rp) < price) || ((eqFlag) && 
          ((Float.parseFloat(pmar.rp)-price)<0.01) && ((Float.parseFloat(pmar.rp)-price)>-0.01)))
      {
         total += Integer.parseInt(pmar.ra);
      }
   }
   return total;
}
static void CulMData(String datestr)throws Exception {

    PMAmount_Rec pcar = new  PMAmount_Rec();
///   Enumeration e = pcar.SelectBySQL("select snum,date,'0' as mnum, sum(inAmount) as inAmount, sum(outAmount) as outAmount, sum(sum) as sum "+
//                    "from pMAmount  where  date = '"+datestr+"' and sum > 0 group by snum,date order by snum "); 
      Enumeration e = ht.elements();  
      while(e.hasMoreElements()){
      	String s  = (String)e.nextElement();
      	 System.out.println(s+"0|");
//         pcar = (PMAmount_Rec)e.nextElement();
//         PLast_Rec pr = new PLast_Rec();
//         pr.snum = pcar.snum ;
//         pr.date = datestr ;
//         if(pr.SelectBySQL("select * from PLast where snum='"+pcar.snum+"' and date='"+datestr+"'").hasMoreElements()){
//           try{
//           	  String s = (String)ht.get(pr.snum);
//              System.out.println(s+(Float.parseFloat(pcar.sum) / Float.parseFloat(pr.total))+"|");
//           }catch(Exception ee){
//         
//           }
//         }else {
//            System.err.println("snum:"+pr.snum+" pr.ran:"+pr.total+" pcar.sum:" + pcar.sum);
//         }  
//         
       }
     }    


}