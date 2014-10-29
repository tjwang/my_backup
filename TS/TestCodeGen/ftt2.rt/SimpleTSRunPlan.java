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
  
   F_TSM_Transaction my_trans;
   boolean _isEasy;
   PrintStream log_ps;
   static public int tRange;
   static public float tMaxGet;
   static public float tMaxLoss;
   
   public SimpleTSRunPlan(boolean iEy)
   {
      my_trans = null;
      _isEasy = iEy;
   }
   
   public int checkStatus(FTT_sFactory fsrc) 
   {
   	  if(my_trans == null)
   	  {
           double v =  fsrc.getLastValueByCode(fsrc.current_TX);
        	 if(log_ps != null)
        	 {
        	    //log_ps.println("create transaction "+kl_ex.valueAt(endIdx).getTimeValue()+" mav3_1:"+mav3_1);
        	 }
        	 my_trans = new F_TSM_Transaction(fsrc, ((int)v), tRange, "MXFJ4",_isEasy);
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