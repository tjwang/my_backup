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

   public SimpleTSRunPlan()
   {
      my_trans = null;
   }
   
   public int checkStatus(FTT_sFactory fsrc) 
   {
   	  if(my_trans == null)
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
        	 my_trans = new TM_Transaction((int)mav3_1, 18, "MXFK4");
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
   
   public void setLogPs(PrintStream ps)
   {
   }

   public float getMaxLoss()
   {
       return (float)-12.0;
   }
   
   public float getMaxGet()
   {
       return 6;
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