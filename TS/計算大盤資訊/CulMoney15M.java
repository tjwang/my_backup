import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class CulMoney15M {
 static int currentCount = 0;
 static int range = 0;
 static float total = 0;
 static Hashtable ht ;
 
 static String d2s(Date d)
 {
  return  String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());
 }
 
 static Date s2d(String ds)
 {
  return  new Date(Integer.parseInt(ds.substring(0,4))-1900,
                   Integer.parseInt(ds.substring(4,6))-1,
                   Integer.parseInt(ds.substring(6,8)));
 }
 
 static String getBWorkDay(String ds,String snum)throws Exception
 {
     Date d = s2d(ds);
     int i = 0;
     PLDayk_Rec plr = new PLDayk_Rec();
     while(i<1)
     {
     	  d = new Date(d.getTime()-86400000);	
//     	  System.err.println("select * from pldayk where snum = '"+snum+"' and date ='"+d2s(d)+"' order by date;");
        if( plr.SelectBySQL("select * from pldayk where snum = '"+snum+"' and date ='"+d2s(d)+"' order by date;").hasMoreElements())
        {
           i++;
        }
        if(Integer.parseInt(d2s(d)) < 20090211)
        {
           return d2s(d);
        }
     }
     return d2s(d);
 }
 static public void main(String[] arg)throws Exception{

    PamountNew_Rec pmar = new  PamountNew_Rec();
    PamountNew_Rec pmar2 = null;
    ht = new Hashtable();
    Enumeration e = pmar.SelectBySQL("select snum,date,time,open as pbuy, close as psell, close as rp, 0 as diff,"+
                                     " money as ra, money as total  from  pldayk where  snum='T000'  and date > '"+
                                     getBWorkDay(arg[0],"0001")+"' and time !='0' and date <='"+arg[0]+"' order by date,time "  ); 
    Vector data = new Vector();
    float openPrice = -1;
    float closePrice = -1;
    int  startTime = 0;
    while(e.hasMoreElements()){
           pmar = (PamountNew_Rec)e.nextElement();
           if(pmar2!=null)
           {
              pmar.diff = String.valueOf(Float.parseFloat(pmar.rp) - Float.parseFloat(pmar.rp));
           }
           if (arg[0].equals(pmar.date))
           {
              if(openPrice == -1)
              {
                openPrice = Float.parseFloat(pmar.rp);
                startTime = Integer.parseInt(pmar.time);
              }
              if((Integer.parseInt(pmar.time) - startTime >= 1500) && (startTime!= 131500))
              {
                  VocRec vr1 =  CulUViscosity(data);      
                  VocRec vr2 =  CulDViscosity(data);      
                  float range1 = vr1.range + 1;  
                  float range2 = vr2.range + 1;  

                data = new Vector();
                openPrice = Float.parseFloat(pmar.rp);
                startTime = Integer.parseInt(pmar.time);
                System.out.println("0000|"+arg[0]+"|"+startTime+"|"+vr1.amount+"|"+(vr1.amount/range1)+"|"
                                 +vr2.amount+"|"+(vr2.amount/range2)+"|"+((vr1.amount*range2)/(vr2.amount*range1))+"|"+
                               (vr1.amount/total)+"|"+
                               (vr2.amount/total)+"|"+((int)total)+"|"
                               );
                
              }
              data.add(pmar);
           } else {
              if(openPrice != -1)
              {
                  break;
              }
           }
           pmar2 = pmar;
     }
     {
           closePrice =  Float.parseFloat(pmar2.rp);
           Ds000_Rec   ds000r = new Ds000_Rec();
           ds000r.snum = pmar2.snum;
           ds000r.SelectInto();
           float capital = 1;
           VocRec vr1 =  CulUViscosity(data);      
           VocRec vr2 =  CulDViscosity(data);      
           float range1 = vr1.range + 1;  
           float range2 = vr2.range + 1;  
                startTime = Integer.parseInt(pmar.time);
           System.out.println("0000|"+arg[0]+"|"+startTime+"|"+vr1.amount+"|"+(vr1.amount/range1)+"|"
                                 +vr2.amount+"|"+(vr2.amount/range2)+"|"+((vr1.amount*range2)/(vr2.amount*range1))+"|"+
                               (vr1.amount/total)+"|"+
                               (vr2.amount/total)+"|"+((int)total)+"|"
                              );
     }
}

static VocRec CulUViscosity(Vector data)
{
   Enumeration e = data.elements();
   double moneyAmount = 0;
   PamountNew_Rec pmar = null;
   PamountNew_Rec ppmar = null;
   VocRec vr =null;
   Vector v =new Vector();
   total = 0;
   while(e.hasMoreElements())
   {
      pmar = (PamountNew_Rec)e.nextElement();
      total += Float.parseFloat(pmar.ra) * 10000;
      if(ppmar != null)
      {
         if(Float.parseFloat(pmar.rp) > Float.parseFloat(ppmar.rp))
         {
            if(vr == null)
            {
               vr = new VocRec();
               vr.amount = (int)(Float.parseFloat(pmar.ra) * 100);
               vr.range = (Float.parseFloat(pmar.rp) - Float.parseFloat(ppmar.rp));
            }else{
               vr.amount += (int)(Float.parseFloat(pmar.ra) * 100);
               vr.range += (Float.parseFloat(pmar.rp) - Float.parseFloat(ppmar.rp));
          
            }
         }else 
         {
             if(vr != null)
             { 
                 v.add(vr);
                 vr = null;
             }
         } 
      
      } else {
         if(Float.parseFloat(pmar.diff) > 0)
         {
               vr = new VocRec();
               vr.amount = (int)(Float.parseFloat(pmar.ra) * 100);
               vr.range = Float.parseFloat(pmar.diff);
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
//      System.out.println("vr.amount:"+vr.amount+ " vr.range:"+vr.range);
      vr2.amount += vr.amount;
      vr2.range += vr.range;
   }
   
   return vr2;
}

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
               vr.amount = (int)(Float.parseFloat(pmar.ra) * 100);
               vr.range = (Float.parseFloat(ppmar.rp) - Float.parseFloat(pmar.rp));
            }else{
               vr.amount += (int)(Float.parseFloat(pmar.ra) * 100);
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
               vr.amount = (int)(Float.parseFloat(pmar.ra) * 100);
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


}