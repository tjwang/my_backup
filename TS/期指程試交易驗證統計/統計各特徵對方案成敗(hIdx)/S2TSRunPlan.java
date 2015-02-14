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

public class  S2TSRunPlan implements TSRunPlan {
  
   F_TSM_Transaction my_trans;
   boolean _isEasy;
   float _target_price_pos = -1;
   float _target_price_neg = -1;
   int _target_set_time = 0;
   PrintStream log_ps;
   
   public S2TSRunPlan(boolean iEy)
   {
      my_trans = null;
      _isEasy = iEy;
      _target_price_pos = -1;
      _target_price_neg = -1;
      _target_set_time = 0;
   }
   
   public void setLogPs(PrintStream ps)
   {
     log_ps = ps;
   }
   public int checkStatus(FTT_sFactory fsrc) 
   {
   	  if(my_trans == null && _target_price_pos > 0)
   	  {
   	     float lv = (float)fsrc.getLastValueByCode(FTT_sFactory.current_TX);
   	     if(lv >= _target_price_pos)
   	     {
            if(log_ps!=null)
            {
              log_ps.println("lv >= _target_price_pos-->"+lv+" "+_target_price_pos+" t:"+fsrc.getLastValueTime(FTT_sFactory.current_TX));
            }
  	         my_trans = new F_TSM_Transaction(fsrc,(int)lv+6, 3, "MXFJ4",_isEasy);
   	     } else if(lv <= _target_price_neg)
   	     {
            if(log_ps!=null)
            {
              log_ps.println("lv <= _target_price_neg -->"+lv+" "+_target_price_neg+" t:"+fsrc.getLastValueTime(FTT_sFactory.current_TX));
            }
   	        my_trans = new F_TSM_Transaction(fsrc,(int)lv-6, 3, "MXFJ4",_isEasy);
   	     } else if(fsrc.getLastValueTime(FTT_sFactory.current_TX) - _target_set_time >= 300)
   	     {
             if(log_ps!=null)
             {
               log_ps.println("fsrc.getLastValueTime(FTT_sFactory.current_TX) - _target_set_time >= 300 -->"+fsrc.getLastValueTime(FTT_sFactory.current_TX)+" "+_target_set_time);
             }
   	          _target_price_pos = -1;
   	          _target_price_neg = -1;
   	          _target_set_time = 0;
   	     }
   	  } else  if(my_trans == null)
   	  {
   	     Line kl_ex = fsrc.getLineByCode(FTT_sFactory.current_TX, 0);
         if(kl_ex == null) return TSRunPlan.END;   
        int endIdx =  kl_ex.length()-1;
        if(endIdx < 12) return TSRunPlan.END;
        if(kl_ex.valueAt(endIdx).getTimeValue()<93000) return TSRunPlan.END;
        
        double mav13  = kl_ex.sub(endIdx-12,endIdx).getAvg();
        double mav8_1   = kl_ex.sub(endIdx-7,endIdx).getAvg();
        double mav8_0   = kl_ex.sub(endIdx-8,endIdx-1).getAvg();
        double mav3_1   = kl_ex.sub(endIdx-2,endIdx).getAvg();
        double mav3_0   = kl_ex.sub(endIdx-3,endIdx-1).getAvg();
        if(mav13 > mav8_1 && mav8_1 > mav3_1) return TSRunPlan.END;
        if(mav13 < mav8_1 && mav8_1 < mav3_1) return TSRunPlan.END;
        if((mav3_0-mav8_0)*(mav3_1-mav8_1) < 0)
        {
        	// my_trans = new F_TSM_Transaction(fsrc,(int)mav3_1, 18, "MXFJ4",_isEasy);
          double llv = fsrc.getLastValueByCode(FTT_sFactory.current_TX);
          if(llv > mav3_1)
          {
             _target_price_pos = (float)llv+6;
             _target_price_neg = (float)mav3_1-6;
         } else
          {
             _target_price_pos = (float)mav3_1+6;
             _target_price_neg = (float)llv-6;
          }
          _target_set_time = fsrc.getLastValueTime(FTT_sFactory.current_TX);
          if(log_ps!=null)
          {
              log_ps.println("_target_price_pos-->"+_target_price_pos);
              log_ps.println("_target_price_neg-->"+_target_price_neg);
              log_ps.println("_target_set_time-->"+_target_set_time);
          }
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
   
   public float getMaxLoss()
   {
       return (float)-12.0;
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