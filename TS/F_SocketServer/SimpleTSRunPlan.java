import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class  SimpleTSRunPlan implements TSRunPlan {
  
   TM_Transaction my_trans;
   public static final double UPBOUND = 9400;
   public static final double LOWBOUND = 8400;

   public SimpleTSRunPlan()
   {
      my_trans = null;
   }
   
   String culFs(double v1,double v2,double v3,double v4,double v5,double v6)
   {
   	   double[] data={v1,v2,v3,v4,v5,v6};
   	   char[] ptn = {'-','-','-','-','-','-'};
   	   for(int i=0;i<6;i++)
   	   {
   	   	 int count=0;
   	   	 for(int j=0;j<6;j++)
   	     {
   	        if(i!=j && data[i] > data[j])
   	        {
   	            count++;
   	        }
   	     }
   	     ptn[count]=(char)(0x30+i);
   	   }
       return new String(ptn);
   }
   
   public int checkStatus(FTT_sFactory fsrc) 
   {
   	  if(my_trans == null)
   	  {
           double v =  fsrc.getLastValueByCode(fsrc.current_TX);
   	       Line kl_ex = fsrc.getLineByCode(FTT_sFactory.current_TX, 0);
           if(kl_ex == null) return TSRunPlan.END;   
           int endIdx =  kl_ex.length()-1;
           if(endIdx < 12) return TSRunPlan.END;
           if(kl_ex.valueAt(endIdx).getTimeValue()<93000 || kl_ex.valueAt(endIdx).getTimeValue() > 132500) return TSRunPlan.END;
        
           double mav13  = kl_ex.sub(endIdx-12,endIdx).getAvg();
           double mav8   = kl_ex.sub(endIdx-7,endIdx).getAvg();
           double mav3   = kl_ex.sub(endIdx-2,endIdx).getAvg();
        	 double max = kl_ex.sub(5,endIdx-1).getMax();
        	 double min = kl_ex.sub(5,endIdx-1).getMin();
        	 String _sta_fs = culFs(min,mav3,mav8,mav13,max,v);
        	  /*if(_sta_fs.equals("013254") || _sta_fs.equals("03215-")
   	        ||_sta_fs.equals("012354")||_sta_fs.equals("032145")||_sta_fs.equals("05-324"))
   	       {
        	    my_trans = new TM_Transaction(((int)v), 6, "MXFK4");;
   	      
   	       }  else*/ if(_sta_fs.equals("0324-5")||_sta_fs.equals("035-24")
   	        ||_sta_fs.equals("0325--")||_sta_fs.equals("03215-"))
   	       {
        	    int center = (int)((v+0+LOWBOUND)/2);
        	    int sell_v = (int)(v+0);
        	    my_trans = new TM_Transaction(center, sell_v-center, "MXFK4");
   	       } else if(_sta_fs.equals("510234")||_sta_fs.equals("015-34")||_sta_fs.equals("05-234"))
        	 {
        	    int center = (int)((v-0+UPBOUND)/2);
        	    int buy_v = (int)(v-0);
        	    my_trans = new TM_Transaction(center, center-buy_v, "MXFK4");
   	       } else
   	       {
   	          return TSRunPlan.END; 
   	       }
   	  }
   	  
      if(my_trans == null) return  TSRunPlan.INITED;
      if(my_trans.isEnd())
      {
         return TSRunPlan.END;
      }
      return TSRunPlan.TRANS;
   }
   /*
   public int checkStatus(FTT_sFactory fsrc) 
   {
   	  if(my_trans == null)
   	  {
   	     Line kl_ex = fsrc.getLineByCode(FTT_sFactory.current_TX, 0);
         if(kl_ex == null) return TSRunPlan.END;   
        int endIdx =  kl_ex.length()-1;
        if(endIdx < 12) return TSRunPlan.END;
        if(kl_ex.valueAt(endIdx).getTimeValue()<93000 || kl_ex.valueAt(endIdx).getTimeValue() > 132500) return TSRunPlan.END;
        
        double mav13  = kl_ex.sub(endIdx-12,endIdx).getAvg();
        double mav8_1   = kl_ex.sub(endIdx-7,endIdx).getAvg();
        double mav8_0   = kl_ex.sub(endIdx-8,endIdx-1).getAvg();
        double mav3_1   = kl_ex.sub(endIdx-2,endIdx).getAvg();
        double mav3_0   = kl_ex.sub(endIdx-3,endIdx-1).getAvg();
       // if(mav13 > mav8_1 && mav8_1 > mav3_1) return TSRunPlan.END;
       // if(mav13 < mav8_1 && mav8_1 < mav3_1) return TSRunPlan.END;
        if((mav3_0-mav8_0)*(mav3_1-mav8_1) < 0)
        {
        	 my_trans = new TM_Transaction((int)fsrc.getLastValueByCode(fsrc.current_TX), 10, "MXFK4");
        } else
        {        
           return TSRunPlan.END;         
   	    }
   	  }
   	  
      if(my_trans == null) return  TSRunPlan.INITED;
      if(my_trans.isEnd())
      {
         return TSRunPlan.END;
      }
      return TSRunPlan.TRANS;
   }
   */
   public void setLogPs(PrintStream ps)
   {
   }

   public float getMaxLoss()
   {
       return (float)-10.0;
   }
   
   public float getMaxGet()
   {
       return 10;
   }
   
   public int   getTimeOut()
   {
       return 0;
   }
   
   public Transaction getTransaction()
   {
       return my_trans;
   }
   
}