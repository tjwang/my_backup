import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CulM2 {
 static String d2s(Date d)
 {
  return  String.valueOf((d.getYear()+1900)*10000+(d.getMonth()+1)*100+d.getDate());
 }

 static public void main(String[] arg)throws Exception{
 	  int dint = Integer.parseInt(arg[0]); 
    Date d = new Date(dint/10000- 1900,(dint %10000)/100 -1,dint % 100);
    String enddate = d2s(new Date(d.getTime() +86400000));
    PMAmount_Rec pmar = new  PMAmount_Rec();
    System.err.println("select * from pmamount  where"+
                                     " date > '"+d2s(new Date(d.getTime() - (d.getDay()*86400000)))+"' and date < '"+
                                       enddate+"' order by snum,mnum;");
    Enumeration e = pmar.SelectBySQL("select * from pmamount  where"+
                                     " date > '"+d2s(new Date(d.getTime() - (d.getDay()*86400000)))+"' and date < '"+
                                       enddate+"' order by snum,mnum;"); 
    String snum = null; 
    String mnum = null; 
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
            if(ds000r.SelectInto())
            {
               float capital = Float.parseFloat(ds000r.capital);
               if(total == 0)
               {
         	  	    System.out.println(enddate+"|"+snum+"|"+mnum+"|"+total+"|"+tmoney+"|"+0+"|"+
      	  	                   (total/capital)+"|");
                }else{
         	  	     System.out.println(enddate+"|"+snum+"|"+mnum+"|"+total+"|"+tmoney+"|"+(tmoney/total)+"|"+
      	  	                   (total/capital)+"|");
                }
            }
      	    total = 0;
      	    tmoney = 0;
      	  }
      	  try {
      	  	int amount = Integer.parseInt(pmar.sum);
            if (amount != 0)
            {
        	      total += amount;
//      	        PAmount_Rec amr = new PAmount_Rec();
//      	        amr.date=pmar.date;
//      	        amr.snum = pmar.snum;
//      	        if(amr.SelectInto())
//      	        {
//      	          tmoney += Float.parseFloat(amr.close) * amount;
//      	        }else{
      	          Pamountinfo_Rec aminfo = new Pamountinfo_Rec();
      	          aminfo.date=pmar.date;
      	          aminfo.snum = pmar.snum;
      	          if(aminfo.SelectBySQL("select * from  Pamountinfo where date='"+pmar.date+"' and snum ='"+pmar.snum+"' ").hasMoreElements())
      	          {
      	          	tmoney += Float.parseFloat(aminfo.Avg) * amount;
      	          }else{
      	              System.err.println("err select -- snum:"+pmar.snum+ " date:"+ pmar.date + "mnum:"+pmar.mnum+" ");
//                      PLast_Rec pr = new PLast_Rec();
//      	              pr.date=pmar.date;
//      	              pr.snum = pmar.snum;
//      	              if(pr.SelectInto())
//      	              {
//     	                  tmoney += Float.parseFloat(pr.rp) * amount;
//      	              }else {
//      	                 if(pr.SelectBySQL("select snum,date,time,pbuy,psell,rp,diff,ra,total from plast2 where snum ='"+pr.snum+
//      	                                    "' and date='"+pr.date+"' ;").hasMoreElements())
//      	                  {
//      	                      tmoney += Float.parseFloat(pr.rp) * amount;
//      	                  }else {
//           	                  System.err.println("err not found-- snum:"+pmar.snum+ " date:"+ pmar.date + "mnum:"+pmar.mnum+" ");
//                          }
//      	              }
      	          }   
//      	        }
      	     }  
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
            float capital = Float.parseFloat(ds000r.capital);
            if(total == 0)
            {
         	  	System.out.println(enddate+"|"+snum+"|"+mnum+"|"+total+"|"+tmoney+"|"+0+"|"+
      	  	                   (total/capital)+"|");
            }else{
         	  	System.out.println(enddate+"|"+snum+"|"+mnum+"|"+total+"|"+tmoney+"|"+(tmoney/total)+"|"+
      	  	                   (total/capital)+"|");
            }
      	    total = 0;
      	    tmoney = 0;
      	  }

 }


}
