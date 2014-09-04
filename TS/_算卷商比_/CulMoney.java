import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class CulMoney {

 static public void main(String[] arg)throws Exception{
    PMAmount_Rec pmar = new  PMAmount_Rec();
    Enumeration e = pmar.SelectBySQL("select snum,'"+arg[1]+"' as date,mnum,sum(inAmount) as inAmount,sum(outAmount) as outAmount,sum(sum) as sum from pmamount  where"+
                                     " date > '"+arg[0]+"' and date < '"+arg[1]+"' group by snum,mnum order by snum,sum;"); 
    String snum = null; 
    int  c = 0;
    int  c2= 0;
    PMAmount_Rec[]  BuyTop3= new PMAmount_Rec[3];
    PMAmount_Rec[]  SellTop3= new PMAmount_Rec[3];
       while(e.hasMoreElements()){
          pmar = (PMAmount_Rec)e.nextElement();
      	  if ((snum != null) && (!snum.equals(pmar.snum)) ){
              System.out.println( snum+"|"+ arg[1]+"|"+ BuyTop3[0].mnum +"|"+countSum(BuyTop3)+"|"+
                                                      SellTop3[0].mnum +"|"+countSum(SellTop3)+"|"+ 
                                                      ((float)countSum(BuyTop3)/(float)countSum(SellTop3))+"|"   );
              c = 0;
      	  }
      	  snum = pmar.snum;
      	  if (c < 3) SellTop3[c] =  pmar;
      	  
      	  BuyTop3[2] = BuyTop3[1] ;  
      	  BuyTop3[1] = BuyTop3[0] ;  
      	  BuyTop3[0] = pmar ;  
      	  
      	  c ++;
      	  if (!e.hasMoreElements()){
              System.out.println( snum+"|"+ arg[1]+"|"+ BuyTop3[0].mnum +"|"+countSum(BuyTop3)+"|"+
                                                      SellTop3[0].mnum +"|"+countSum(SellTop3)+"|"+ 
                                                      ((float)countSum(BuyTop3)/(float)countSum(SellTop3))+"|"   );
              c = 0;
      	  }
       }
 }
 private static int countSum(PMAmount_Rec[]  Top3) throws Exception
 {
    int vSum = 0;
    for(int i=0;i < Top3.length; i++)
    {
       vSum += Integer.parseInt(Top3[i].sum);
    }
    return vSum;
 }





}
