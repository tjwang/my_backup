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

public class  SimpleKRunPlan implements TSRunPlan {
  
   F_TSM_Transaction my_trans;
   boolean _isEasy;
   PrintStream log_ps;
   public static final int S_BAR = 0;
   public static final int S_STAR = 1;
   public static final int S_UP_BEETLE = 2;
   public static final int S_DOWN_BEETLE = 3;
   public static final int S_COMM = 4;
   
   public static final int D_M_STAR = 0;
   public static final int D_N_STAR = 1;
   public static final int D_B_C_R = 2;
   public static final int D_R_C_B = 3;
   public static final int D_R_R = 4;
   public static final int D_B_B = 5;
   public static final int D_COMM = 6;
   
   double vl_var;
   double ul_var;
   double dl_var;
   double rl_var;

   double vl_avg;
   double ul_avg;
   double dl_avg;
   double rl_avg;

   int last_set_time;
   KValue prevK ;
   double prevVol;
   public SimpleKRunPlan(boolean iEy)
   {
      my_trans = null;
      _isEasy = iEy;
      last_set_time = 0;
      prevK = null;
   }
   
   public int checkStatus(FTT_sFactory fsrc) 
   {
   	  if(my_trans == null)
   	  {
   	  	 
   	     KLine kl_ex = (KLine)fsrc.getLineByCode(FTT_sFactory.current_TX, 0);
         if(kl_ex == null) return TSRunPlan.END;   
         int endIdx =  kl_ex.length()-1;
         if(endIdx < 13) return TSRunPlan.END;
         if(kl_ex.valueAt(endIdx).getTimeValue()<93000) return TSRunPlan.END;
        
         int crt = kl_ex.valueAt(endIdx).getTimeValue();
          if(crt != last_set_time)
         {
            KLine kl_sub = (KLine)kl_ex.sub(endIdx-13,endIdx-1);
            Line vl = ((KLine)kl_ex.sub(endIdx-14,endIdx-1)).getVol().diff(1).sub(1,13);
            Line ul = kl_sub.getUp();
            Line dl = kl_sub.getDown();
            Line rl = kl_sub.getReal().abs();
            prevK = (KValue)kl_ex.valueAt(endIdx-1);
            prevVol =  vl.valueAt(12).getValue();
            last_set_time = crt;
            vl_var = vl.getVar();
            ul_var = ul.getVar();
            dl_var = dl.getVar();
            rl_var = rl.getVar();
            vl_avg = vl.getAvg();
            ul_avg = ul.getAvg();
            dl_avg = dl.getAvg();
            rl_avg = rl.getAvg();
            vl_avg = vl_avg*vl_avg;
            ul_avg = ul_avg*ul_avg;
            dl_avg = dl_avg*dl_avg;
            rl_avg = rl_avg*rl_avg;
            if(log_ps!=null)
            {
            	 rl.dump();
              log_ps.println("test Time ===>"+kl_ex.valueAt(endIdx).getTimeValue());
              log_ps.println("vl_var-->"+vl_var);
              log_ps.println("ul_var-->"+ul_var);
              log_ps.println("dl_var-->"+dl_var);
              log_ps.println("rl_var-->"+rl_var);
              log_ps.println("vl_avg-->"+vl_avg);
              log_ps.println("ul_avg-->"+ul_avg);
              log_ps.println("dl_avg-->"+dl_avg);
              log_ps.println("rl_avg-->"+rl_avg);
            }
         }else
         {
             KValue curK = (KValue)kl_ex.valueAt(endIdx);
             if(isKValid(curK.getVolume()-prevK.getVolume()))
             {
             	  if(getKType_S(curK)!=S_COMM && getKType_S(prevK)!=S_COMM)
             	  {
        	         float lv = (float)curK.getClose();
 	                 if(curK.getReal() < 0)
 	                 {
 	                 	   if(getKType_S(curK) != S_STAR)
 	                 	   {
        	                if(log_ps != null)
        	                {
        	                   log_ps.println("create transaction "+curK.getTimeValue()+" curK.getClose():"+lv);
        	                   log_ps.println("k1:"+getKType_S(prevK)+" k2:"+getKType_S(curK));
        	                   log_ps.println("k1 real:"+prevK.getReal()+" k2 real:"+curK.getReal());
        	                   log_ps.println("k1 vol:"+prevK.getVolume()+" k2 vol:"+curK.getVolume());
        	                   log_ps.println("k1 dvol:"+prevVol+" k2 dvol:"+(curK.getVolume()-prevK.getVolume()));
        	                }
 	                 	      my_trans = new F_TSM_Transaction(fsrc,(int)lv-6, 3, "MXFJ4",_isEasy);
 	                 	   }/* else
 	                 	   {
 	                        my_trans = new F_TSM_Transaction(fsrc,(int)lv+6, 3, "MXFJ4",_isEasy);
                       }*/
                   }else
                   {
 	                 	   if(getKType_S(curK) != S_STAR)
 	                 	   {
        	                  if(log_ps != null)
        	                  {
        	                     log_ps.println("create transaction "+curK.getTimeValue()+" curK.getClose():"+lv);
        	                     log_ps.println("k1:"+getKType_S(prevK)+" k2:"+getKType_S(curK));
        	                     log_ps.println("k1 real:"+prevK.getReal()+" k2 real:"+curK.getReal());
        	                     log_ps.println("k1 vol:"+prevK.getVolume()+" k2 vol:"+curK.getVolume());
        	                     log_ps.println("k1 dvol:"+prevVol+" k2 dvol:"+(curK.getVolume()-prevK.getVolume()));
        	                  }
 	                 	      my_trans = new F_TSM_Transaction(fsrc,(int)lv+6, 3, "MXFJ4",_isEasy);
 	                 	   }/* else
 	                 	   {
                          my_trans = new F_TSM_Transaction(fsrc,(int)lv-6, 3, "MXFJ4",_isEasy);
                       }*/
                   }    
                }
             }
         }
      }  
       
      if(my_trans == null) return  TSRunPlan.INITED;
      if(my_trans.isEnd())
      {
         return TSRunPlan.END;
      }
      return TSRunPlan.TRANS;
        
   }

   int getKType_S(KValue k)
   {
      double kr0 = k.getReal();
      if(kr0<0) kr0 = -1*kr0;
      double kr = kr0*kr0;
      if(kr < rl_avg && rl_avg-kr > rl_var*2 && k.getUp()>kr0 && k.getDown() > kr0) {
      
       	 return S_STAR;
      }
      
      if(kr < rl_avg && rl_avg-kr < rl_var)
      {
        if(k.getUp()>k.getDown() && k.getUp() > kr0) return S_DOWN_BEETLE;
        if(k.getDown()>k.getUp() && k.getDown() > kr0) return S_UP_BEETLE;
      } 
      if(kr > rl_avg && kr - rl_avg > rl_var) return S_BAR;
      
      return S_COMM;
   }
   
   boolean isKValid(double vk)
   {
      double vol0 = vk;
      double vol = vol0*vol0;
      if(vol > vl_avg && vol - vl_avg > 4*vl_var) return true;
      return false;
   }   
   
   int getKType_D(KValue k, KValue k1)
   {
       int st1 = getKType_S(k);
       int st2 = getKType_S(k1);
       switch(st1)
       {    
         case S_STAR: 
         {
        
         }
         break;
         case S_DOWN_BEETLE: 
         {
        
         }
         break;
         case S_UP_BEETLE: 
         {
        
         }
         break;
         case S_BAR: 
         {
        
         }
         break;
       }
       return D_COMM;
   }
   
   public void setLogPs(PrintStream ps)
   {
   	  log_ps = ps;
   }

   public float getMaxLoss()
   {
       return (float)-8.0;
   }
   
   public float getMaxGet()
   {
       return 0;
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