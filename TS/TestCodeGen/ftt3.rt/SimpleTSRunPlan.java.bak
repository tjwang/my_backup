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
  
   public static final int ADHOC = 0;
   public static final int UP = 1;
   public static final int DOWN = 2;
   public static final double UPBOUND = 9400;
   public static final double LOWBOUND = 8180;
   F_TSM_Transaction my_trans;
   boolean _isEasy;
   PrintStream log_ps;
   static public int tRange;
   static public float tMaxGet;
   static public float tMaxLoss;
   static public int tMode = ADHOC;
   public String _sta_fs;
   public String _sta_ds;
   public int _sta_ti;
   public SimpleTSRunPlan(boolean iEy)
   {
      my_trans = null;
      _isEasy = iEy;
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
        	 _sta_ds = String.valueOf(fsrc.getLastValueDate(fsrc.current_TX));
        	 _sta_ti = fsrc.getLastValueTime(fsrc.current_TX);
        	 _sta_fs = culFs(min,mav3,mav8,mav13,max,v);
        	 S4_TransactionManager.one_instance.setFeature(_sta_ds,_sta_ti,_sta_fs);
        	 if(log_ps != null)
        	 {
        	    //log_ps.println("create transaction "+kl_ex.valueAt(endIdx).getTimeValue()+" mav3_1:"+mav3_1);
        	 }
        	 if(tMode == UP)
        	 {
        	    int center = (int)((v-tRange+UPBOUND)/2);
        	    int buy_v = (int)(v-tRange);
        	    my_trans = new F_TSM_Transaction(fsrc,center , center-buy_v, "MXFJ4",_isEasy);
   	       } else if(tMode == DOWN)
   	       {
        	    int center = (int)((v+tRange+LOWBOUND)/2);
        	    int sell_v = (int)(v+tRange);
        	    my_trans = new F_TSM_Transaction(fsrc, center, sell_v-center, "MXFJ4",_isEasy);
   	       } else
   	       {
        	    my_trans = new F_TSM_Transaction(fsrc, ((int)v), tRange, "MXFJ4",_isEasy);
   	      
   	       }
   	  }
   	  
      if(my_trans == null) return  TSRunPlan.INITED;
      if(my_trans.isEnd())
      {
         return TSRunPlan.END;
      }
      return TSRunPlan.TRANS;
   }
   
   public void setLogPs(PrintStream ps)
   {
   	  log_ps = ps;
   }

   public float getMaxLoss()
   {
       return (float)tMaxLoss;
   }
   
   public float getMaxGet()
   {
       return tMaxGet;
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