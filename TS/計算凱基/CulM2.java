import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CulM2 {
 static String d2s(Date d)
 {
  return  String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());
 }

 static Date s2d(String s)
 {
 	   int dint = Integer.parseInt(s);
     return  new Date(dint/10000- 1900,(dint %10000)/100 -1,dint % 100);
 }

 static public void main(String[] arg)throws Exception{
 	  int dint = Integer.parseInt(arg[0]); 
    Date d = new Date(dint/10000- 1900,(dint %10000)/100 -1,dint % 100);
    String enddate = d2s(new Date(d.getTime() +86400000));
    PMAmount_Rec pmar = new  PMAmount_Rec();
    System.err.println("select * from pmamount2  where"+
                                     " date > '"+d2s(new Date(d.getTime() - (d.getDay()*86400000)))+"' and date < '"+
                                       enddate+"' order by snum,mnum;");
//    F1_Rec f1 = new F1_Rec();
//    f1.SelectBySQL("select max(date) as date, 0 as time, 0 as f1 from pmamountinfo_week2 "); 
//    Date d1 = new Date(s2d(f1.date).getTime() + 86400000);
    
//    enddate = d2s(new Date(d1.getTime() - ((d1.getDay()-6)*86400000)));
//    System.err.println("select * from pmamount2  where"+
//                                     " date > '"+d2s(new Date(d1.getTime() - (d1.getDay()*86400000)))+"' and date < '"+
//                                       enddate+"' order by snum,mnum;");

    
    Enumeration e = pmar.SelectBySQL("select * from pmamount2  where"+
                                     " date > '"+d2s(new Date(d.getTime() - (d.getDay()*86400000)))+"' and date < '"+
                                       enddate+"' order by snum,mnum;"); 
/*                                       
    Enumeration e = pmar.SelectBySQL("select * from pmamount2  where"+
                                     " date > '"+d2s(new Date(d1.getTime() - (d1.getDay()*86400000)))+"' and date < '"+
                                       enddate+"' order by snum,mnum;"); 
 */                                      
    String snum = null; 
    String mnum = null; 
    int   stotal = 0;
    float stmoney = 0;
    int   btotal = 0;
    float btmoney = 0;
    int   total = 0;
    float tmoney = 0;
    
     while(e.hasMoreElements()){
          pmar = (PMAmount_Rec)e.nextElement();
      	  if ( ((snum != null) && (!snum.equals(pmar.snum))) || 
      	       ((mnum != null) && (!mnum.equals(pmar.mnum)))
      	      )
      	  {
            Ds000_Rec   ds000r = new Ds000_Rec();
            ds000r.snum = snum;
            ds000r.SelectInto();
             float capital = 0;
            try{
               capital = Float.parseFloat(ds000r.capital);
            }catch(Exception ee2)
            {
                System.err.println("snum no capital : "+snum);
      	        stotal = 0;
      	        stmoney = 0;
      	        btotal = 0;
      	        btmoney = 0;
      	        total = 0;
      	        tmoney = 0;
                continue;
            }
            if(total == 0)
            {
            	 total = 1;
            }
            if(stotal == 0)
            {
            	 stotal = 1;
            }
            if(btotal == 0)
            {
            	 btotal = 1;
            }
            
         	  System.out.println(enddate+"|"+snum+"|"+mnum+"|"+
         	                     btotal+"|"+btmoney+"|"+(btmoney/btotal)+"|"+(btotal/capital)+"|"+
         	                     stotal+"|"+stmoney+"|"+(stmoney/stotal)+"|"+(stotal/capital)+"|"+
         	                      total+"|"+ tmoney+"|"+  (tmoney/total)+"|"+ (total/capital)+"|");
      	    stotal = 0;
      	    stmoney = 0;
      	    btotal = 0;
      	    btmoney = 0;
      	    total = 0;
      	    tmoney = 0;
      	  }
      	  try {
      	  	int samount = Integer.parseInt(pmar.outAmount);
      	  	int bamount = Integer.parseInt(pmar.inAmount);
      	  	int amount = Integer.parseInt(pmar.sum);
//            if (amount != 0)
//            {
      	          Pamountinfo_Rec aminfo = new Pamountinfo_Rec();
      	          aminfo.date=pmar.date;
      	          aminfo.snum = pmar.snum;
      	          if(aminfo.SelectBySQL("select * from  Pamountinfo where date='"+pmar.date+"' and snum ='"+pmar.snum+"' ").hasMoreElements())
      	          {
        	          stotal += samount;
        	          btotal += bamount;
        	          total += amount;
      	          	stmoney += Float.parseFloat(aminfo.Avg) * samount;
      	          	btmoney += Float.parseFloat(aminfo.Avg) * bamount;
      	          	tmoney += Float.parseFloat(aminfo.Avg) * amount;
      	          }else{
      	                System.err.println("err select -- snum:"+pmar.snum+ " date:"+ pmar.date + "mnum:"+pmar.mnum+" ");
      	          }
//      	          }else{
//                      PLast_Rec pr = new PLast_Rec();
//      	              pr.date=pmar.date;
//      	              pr.snum = pmar.snum;
//      	              if(pr.SelectInto())
//      	              {
//     	                  stmoney += Float.parseFloat(pr.rp) * samount;
//     	                  btmoney += Float.parseFloat(pr.rp) * bamount;
//     	                  tmoney += Float.parseFloat(pr.rp) * amount;
//      	              }else {
//      	                 if(pr.SelectBySQL("select snum,date,time,pbuy,psell,rp,diff,ra,total from plast2 where snum ='"+pr.snum+
//      	                                    "' and date='"+pr.date+"' ;").hasMoreElements())
//      	                  {
//      	                      stmoney += Float.parseFloat(pr.rp) * samount;
//      	                      btmoney += Float.parseFloat(pr.rp) * bamount;
//      	                      tmoney += Float.parseFloat(pr.rp) * amount;
//      	                  }else {
//           	                  System.err.println("err not found-- snum:"+pmar.snum+ " date:"+ pmar.date + "mnum:"+pmar.mnum+" ");
//                          }
//      	              }
//      	          }   
//      	        }
//      	    }else 
//      	    {
//    //  	      System.err.println("amount == 0" + pmar.dump());
//      	    }
      	      
      	  }catch(Exception e2)
      	  {
      	     System.err.println("err parse-- snum:"+pmar.snum+ " date:"+ pmar.date + "mnum:"+pmar.mnum+" ");
      	     e2.printStackTrace();
      	  }
      	  snum = pmar.snum;
      	  mnum = pmar.mnum;
     }
// ----- last ----
      	  {
            Ds000_Rec   ds000r = new Ds000_Rec();
            ds000r.snum = snum;
            ds000r.SelectInto();
            float capital =0;
            try
            {
               capital = Float.parseFloat(ds000r.capital);
            }catch(Exception ee3)
            {
               System.err.println("snum no capital : "+snum);
               return;
            }
            if(total == 0)
            {
            	 total = 1;
            }
            if(stotal == 0)
            {
            	 stotal = 1;
            }
            if(btotal == 0)
            {
            	 btotal = 1;
            }
            
         	  System.out.println(enddate+"|"+snum+"|"+mnum+"|"+
         	                     btotal+"|"+btmoney+"|"+(btmoney/btotal)+"|"+(btotal/capital)+"|"+
         	                     stotal+"|"+stmoney+"|"+(stmoney/stotal)+"|"+(stotal/capital)+"|"+
         	                      total+"|"+ tmoney+"|"+  (tmoney/total)+"|"+ (total/capital)+"|");
      	    total = 0;
      	    tmoney = 0;
      	  }
 }


}
