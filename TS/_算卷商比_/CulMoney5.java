import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CulMoney5 {

 static public void main(String[] arg)throws Exception{
    PMAmount_Rec pmar = new  PMAmount_Rec();
    Enumeration e = pmar.SelectBySQL("select snum,'"+arg[1]+"' as date,mnum,sum(inAmount) as inAmount,sum(outAmount) as outAmount,sum(sum) as sum from pmamount  where"+
                                     " date > '"+arg[0]+"' and date < '"+arg[1]+"' group by snum,mnum order by snum,sum;"); 
    String snum = null; 
    int  c = 0;
    int  c2= 0;
    PMAmount_Rec[]  BuyTop5= new PMAmount_Rec[5];
    PMAmount_Rec[]  SellTop5= new PMAmount_Rec[5];
       while(e.hasMoreElements()){
          pmar = (PMAmount_Rec)e.nextElement();
      	  if ((snum != null) && (!snum.equals(pmar.snum)) ){
              System.out.println( snum+"|"+ arg[1]+"|"+ BuyTop5[0].mnum +"|"+countSum(BuyTop5)+"|"+
                                                      SellTop5[0].mnum +"|"+countSum(SellTop5)+"|"+ 
                                                      ((float)countSum(BuyTop5)/(float)countSum(SellTop5))+"|"   );
              c = 0;
      	  }
      	  snum = pmar.snum;
      	  if (c < 5) SellTop5[c] =  pmar;
      	  
      	  BuyTop5[4] = BuyTop5[3] ;  
      	  BuyTop5[3] = BuyTop5[2] ;  
      	  BuyTop5[2] = BuyTop5[1] ;  
      	  BuyTop5[1] = BuyTop5[0] ;  
      	  BuyTop5[0] = pmar ;  
      	  
      	  c ++;
      	  if (!e.hasMoreElements()){
              System.out.println( snum+"|"+ arg[1]+"|"+ BuyTop5[0].mnum +"|"+countSum(BuyTop5)+"|"+
                                                      SellTop5[0].mnum +"|"+countSum(SellTop5)+"|"+ 
                                                      ((float)countSum(BuyTop5)/(float)countSum(SellTop5))+"|"   );
              c = 0;
      	  }
       }
 }
 private static int countSum(PMAmount_Rec[]  Top5) throws Exception
 {
    int vSum = 0;
    for(int i=0;i < Top5.length; i++)
    {
       vSum += Integer.parseInt(Top5[i].sum);
    }
    return vSum;
 }





}
