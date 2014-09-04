import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.tool.*;

public class CulView2 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
static public void main(String[] arg)throws Exception{

    PamountNew_Rec pmar = null;
    PFive_Rec pfive = new PFive_Rec();
    PFive_Rec pfivetemp = null;
    Ds000_Rec dsr = new Ds000_Rec();
    ht = new Hashtable();
    float f = Float.parseFloat(arg[2]);
    Enumeration  se = dsr.SelectBySQL("select ds000.snum, ds000.sname, capital from ds000attr,ds000 where tcode = '1' and ds000.snum=ds000attr.snum and ds000.snum='"+arg[1]+"' order by snum;");
//    Enumeration  se = dsr.SelectBySQL("select ds000.snum, ds000.sname, capital from ds000attr,ds000 where tcode = '1' and ds000.snum=ds000attr.snum  order by snum;");

   while(se.hasMoreElements())
   {
   	 dsr = (Ds000_Rec)se.nextElement();
//   	 System.out.println(dsr.snum);
   	 pmar = new  PamountNew_Rec();
     Enumeration e1 = pmar.SelectBySQL("select * from  Pamounttemp where  date = '"+arg[0]+"' and snum='"+dsr.snum+"' order by time "  ); 
     Enumeration e2 = pfive.SelectBySQL("select * from  pfive where  date = '"+arg[0]+"' and snum='"+dsr.snum+"' order by time,type,rp "  ); 
     Vector data1 = new Vector();
     Vector data2 = new Vector();
     Vector Prevdata1 = null;
     pmar = null;
     pfivetemp =null;
     while(e2.hasMoreElements()){
     	    if (pfivetemp == null)
     	    {
            pfive = (PFive_Rec)e2.nextElement();
          }else{
            pfive = pfivetemp;
          }
          data1 = new Vector(); 
          PFive_Rec[] pbf =new  PFive_Rec[5];
          int i = 0;
          for(i = 0; i < 5 ; i++)
          {
             pbf[i] = null;
          }
          i = 0;
          //if(pfive.type.equals("0"))
          //{
          //  pbf[i] = pfive;
          //  i++;
          //} else
          //{
          //   data1.add(pfive);
          //}
          //pfivetemp = (PFive_Rec)e2.nextElement();
          pfivetemp = pfive;
          while(pfivetemp.time.equals(pfive.time))
          {
             PFive_Rec pfivetemp2 = null; 
             if(e2.hasMoreElements())
                pfivetemp2 = (PFive_Rec)e2.nextElement();
             while((pfivetemp2!=null) &&pfivetemp2.type.equals(pfivetemp.type) && pfivetemp2.rp.equals(pfivetemp.rp))
             {
                  pfivetemp = pfivetemp2;
                 if(e2.hasMoreElements())
                    pfivetemp2 = (PFive_Rec)e2.nextElement();
                 else
                    pfivetemp2 = null;   
             }   
             if(pfivetemp.type.equals("0"))
             {
                pbf[i] = pfivetemp;
                i++;
             } else{
             	  for(i--;i>=0;i--)
             	  {
             	     data1.add(pbf[i]);
             	  }
//             	  for(int j=0;j<i;j++)
//             	  {
//             	     data1.add(pbf[j]);
//             	  }
             	  i = 0;
                data1.add(pfivetemp);
             }
             if(pfivetemp.type.equals("1"))
             {
                 pfive = pfivetemp;
             }
             if(pfivetemp2!= null)
             {
             	 pfivetemp = pfivetemp2;
             } else {
                break;
             }
             /*if(e2.hasMoreElements()){   
                pfivetemp = (PFive_Rec)e2.nextElement();    
             }else{
                break;  
             }*/
          }
          data2 = new Vector();
           if((pmar!=null) && checkTime(pfive, pmar))
          {
             data2.add(pmar);
          }
          if((pmar == null) || (checkTime(pfive, pmar)))
          {
             while(e1.hasMoreElements())
             {
                pmar =  (PamountNew_Rec)e1.nextElement();  
                if(checkTime(pfive, pmar))
                {
                   data2.add(pmar);
                }else{
                   break;
                }
             }
          }
 //         culGroup(Prevdata1, data1, data2); 
          dumpGroup(data1, data2, f); 
 //System.out.println("=============================================");
          Prevdata1 = data1;
     }
   }
}

static boolean checkTime(PFive_Rec pfive, PamountNew_Rec pmar)
{
	 int fivetime = Integer.parseInt(pfive.time);
	 int pmtime = Integer.parseInt("1"+pmar.time.replaceAll(":",""))%1000000;
//	 System.out.println("fivetime: "+ fivetime+" pmtime: "+pmtime); 
	 if(pmtime <= fivetime) return true;
	 return false;
}

static void  dumpGroup(Vector fiveV, Vector amountV, float f)
{
   System.out.println("======================dumpGroup======================");
   Enumeration e1 = amountV.elements();
   int i = 1;
   while(e1.hasMoreElements())
   {
      PamountNew_Rec pmar = (PamountNew_Rec)e1.nextElement();
      if(FEquals(f,Float.parseFloat(pmar.rp)))
      {  
         System.out.println(pmar.dump());
         i++;
      }   
   }
   for(;i<4;i++) System.out.println();
   Enumeration e2 = fiveV.elements();
   while(e2.hasMoreElements())
   {
      PFive_Rec pfive = (PFive_Rec)e2.nextElement();
      if (FEquals(f,Float.parseFloat(pfive.rp)))
      {  
         System.out.println(pfive.dump());
         i++;
      }
   }
//   for(;i<31;i++) System.out.println();
   
}

static void  culGroup(Vector preFiveV, Vector fiveV, Vector amountV) throws Exception
{
//   System.out.println("======================culGroup======================");
   Enumeration e1 = amountV.elements();
   Vector preV = preFiveV;
   if(preV == null) preV = new Vector();
   Hashtable ht0 = new  Hashtable();
   Hashtable ht = new  Hashtable();
   Hashtable ht2 = new Hashtable();
   Vector data = new Vector();
  
   Enumeration e = fiveV.elements();
   while(e.hasMoreElements())
   {
      PFive_Rec pfive = (PFive_Rec)e.nextElement();
      if(!pfive.type.equals("1"))
        ht2.put(pfive.rp,pfive);
   }
   if(preFiveV != null)
   {
      e = preFiveV.elements();
      while(e.hasMoreElements())
      {
         PFive_Rec pfive = (PFive_Rec)e.nextElement();
         if(!pfive.type.equals("1"))
           ht0.put(pfive.rp,pfive);
      }     
      e = preFiveV.elements();
      while(e.hasMoreElements())
      {
         PFive_Rec pfive = (PFive_Rec)e.nextElement();
         if(!pfive.type.equals("1"))
           ht.put(pfive.rp,pfive.ra);
      }
   }
   while(e1.hasMoreElements())
   {
      PamountNew_Rec pmar = (PamountNew_Rec)e1.nextElement();  
      Enumeration e2 = preV.elements();
      preV = new Vector();
      float f1 = Float.parseFloat(pmar.rp); 
      int ra = Integer.parseInt(pmar.ra);
      int startflag = 0;  
      System.out.println(pmar.dump());
      PFive_Rec pfive = null;
      boolean inflag = false;
      while(e2.hasMoreElements())
      {
         pfive = (PFive_Rec)e2.nextElement();
         float f2 =  Float.parseFloat(pfive.rp);
         int ra2 = Integer.parseInt(pfive.ra);
        // System.out.println("f1 :"+ f1+" f2:"+ f2+ " ra:"+ra+" ra2:"+ra2);
         PFive_Rec pfiveX = (PFive_Rec)ht2.get(pfive.rp);
         if (((!FLarger(f1,f2)) && pfive.type.equals("0")) ||
              ((!FLarger(f2,f1)) && pfive.type.equals("2")))
         {
         	  if(ra >= ra2)
         	  {
               data.add(pfive);
               if(ht.get(pfive.rp) == null)
               {  
                   throw new Exception ("ht.get(pfive.rp) == null");
               }
               if(pfiveX == null)
               {
                  System.out.println(pfive.dump()+" -- "+ht.get(pfive.rp)+ "[ null, null]");
               } else {
                  System.out.println(pfive.dump()+" -- "+ht.get(pfive.rp)+ "[ "+pfiveX.type+", "+pfiveX.ra+"]");
               }
               ra = ra - ra2;
            }else{
            	 //if(!FEquals(f1,f2) )
            	 //{
            	 //	   dumpGroup( fiveV, amountV);
            	 //	  if(pfiveX!= null && pfiveX.type.equals(pfive.type))
            	 //	  {
            	 //	     throw new Exception("!FEquals(f1,f2)");
            	 //	  }
            	 //}
            	 if (ra > 0)
            	 {
                  PFive_Rec pfive2 = (PFive_Rec)pfive.Clone();
                  pfive2.ra = String.valueOf(ra);
                  data.add(pfive2);
                  if(ht.get(pfive.rp) == null)
                  {
                  	  throw new Exception ("ht.get(pfive.rp) == null");
                  }
                  if(pfiveX == null)
                  {
                     System.out.println(pfive2.dump()+" -- "+ht.get(pfive.rp)+ "[ null, null]");
                  } else {
                     System.out.println(pfive2.dump()+" -- "+ht.get(pfive.rp)+ "[ "+pfiveX.type+", "+pfiveX.ra+"]");
                  }
                  pfive.ra = String.valueOf(ra2-ra);
                  ra = 0;
               }
               preV.add(pfive);
            }
         
         }else
         {
            preV.add(pfive);
         }
      }   
      if(ra > 0)
      { 
      	 dumpGroup( fiveV, amountV, 0);
         System.out.print("pmar.rp : "+pmar.rp);
      	 String pmarrp = converFloat(Float.parseFloat(pmar.rp));
      	 pfive = (PFive_Rec)ht0.get(pmarrp);
      	 if(pfive == null) {
      	 	  if(inflag)  throw new Exception("Non logical condtion!!");
      	 	  pfive = (PFive_Rec)ht2.get(pmarrp);
       	 }
       	 if(pfive != null)
       	 {
        	 pfive = (PFive_Rec)pfive.Clone();
        	 
       	 }
       	 if(pfive!=null)
       	 {
      	   pfive.rp = pmarrp;
      	   pfive.ra = String.valueOf(ra);
      	   if(ht.get(pfive.rp) == null) ht.put(pfive.rp,pfive.ra);
      	   data.add(pfive);
      	 }
        System.out.println("Extra : "+pfive.dump());
      }
   }
   e = data.elements();
   Hashtable ht3 = new Hashtable();
   while(e.hasMoreElements())
   {
      PFive_Rec pfive = (PFive_Rec)e.nextElement();
      if(ht3.get(pfive.rp) == null)
      {
         ht3.put(pfive.rp,pfive);
      } else {
        PFive_Rec pfivetemp =  (PFive_Rec)ht3.get(pfive.rp);
        pfivetemp.ra = String.valueOf(Integer.parseInt(pfive.ra)+ Integer.parseInt(pfivetemp.ra));
      }
   }
   e = ht3.elements();
   while(e.hasMoreElements()) {
      PFive_Rec pfive = (PFive_Rec)e.nextElement();
      PFive_Rec pfiveX = (PFive_Rec)ht2.get(pfive.rp);
      if(ht.get(pfive.rp) == null)
      {
          throw new Exception ("ht.get(pfive.rp) == null");
      }
      if(pfiveX == null)
      {
      	 int ora = Integer.parseInt((String)ht.get(pfive.rp));
      	 int rra = Integer.parseInt(pfive.ra);
      	 char typeBS ='X';
      	 if(pfive.type.equals("0")) typeBS='B';
      	 if(pfive.type.equals("2")) typeBS='S';
      	 if(ora > rra)
      	 {
//      	 	 System.out.println(pfive.snum+"|"+pfive.date+"|"+pfive.time+"|"+typeBS+"|D|"+pfive.rp+"|"+pfive.ra+"|"+ora+"|");
      	 } else {
//      	 	 System.out.println(pfive.snum+"|"+pfive.date+"|"+pfive.time+"|"+typeBS+"|D|"+pfive.rp+"|"+pfive.ra+"|"+pfive.ra+"|");
     	   }
         System.out.println("sum: "+pfive.dump()+" -- "+ht.get(pfive.rp)+ "[ null, null]");
      } else {
      	 int ora = Integer.parseInt((String)ht.get(pfive.rp));
      	 int rra = Integer.parseInt(pfive.ra);
      	 int xra = Integer.parseInt(pfiveX.ra);
      	 char typeBS ='X';
      	 if(pfive.type.equals("0")) typeBS='B';
      	 if(pfive.type.equals("2")) typeBS='S';
      	 if(ora > rra)
      	 {
      	 	 if(pfiveX.type.equals(pfive.type))
      	   {
//      	 	   System.out.println(pfive.snum+"|"+pfive.date+"|"+pfive.time+"|"+typeBS+"|D|"+pfive.rp+"|"+pfive.ra+"|"+(rra+xra)+"|");
      	   } else
      	   {
//      	 	   System.out.println(pfive.snum+"|"+pfive.date+"|"+pfive.time+"|"+typeBS+"|D|"+pfive.rp+"|"+pfive.ra+"|"+(ora)+"|");
      	   }
      	 } else {
      	 	 if(pfiveX.type.equals(pfive.type))
      	   {
//      	 	   System.out.println(pfive.snum+"|"+pfive.date+"|"+pfive.time+"|"+typeBS+"|D|"+pfive.rp+"|"+pfive.ra+"|"+(rra+xra)+"|");
      	   } else
      	   {
//      	 	   System.out.println(pfive.snum+"|"+pfive.date+"|"+pfive.time+"|"+typeBS+"|D|"+pfive.rp+"|"+pfive.ra+"|"+(rra)+"|");
      	   }
     	   }

//         System.out.println("sum: "+pfive.dump()+" -- "+ht.get(pfive.rp)+ "[ "+pfiveX.type+", "+pfiveX.ra+"]");
      }
   }
   e = amountV.elements();
   while(e.hasMoreElements()) {
      PamountNew_Rec pmar = (PamountNew_Rec)e.nextElement();  
    	String pmarrp = converFloat(Float.parseFloat(pmar.rp));
//    	System.out.println(pmarrp);
      PFive_Rec pfive = (PFive_Rec)ht0.get(pmarrp);
      if(pfive == null) {
      	  pfive = (PFive_Rec)ht2.get(pmarrp);
       }
      if(pfive != null)
      {
     	   char typeBS ='X';
         if(pfive == null)  throw new Exception("PamountNew_Rec Non logical condtion!!");
         if(pfive.type.equals("0")) typeBS='S';
         if(pfive.type.equals("2")) typeBS='B';
//         System.out.println(pmar.snum+"|"+pmar.date+"|"+converTimeSting(pmar.time)+"|"+typeBS+"|A|"+pmarrp+"|"+pmar.ra+"|"+(pmar.ra)+"|");
      }   
   }
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