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
   public static final double UPBOUND = 9800;
   public static final double LOWBOUND = 8600;
   static double last_up_p;
   static double last_down_p;
     
   static FeatureIdxHash  up_idx_ma;
   static FeatureIdxHash  down_idx_ma;
   static FeatureIdxHash  up_idx_k;
   static FeatureIdxHash  down_idx_k;
   static FeatureIdxHash  up_idx_kd;
   static FeatureIdxHash  down_idx_kd;
   static FeatureIdxHash  up_idx_v;
   static FeatureIdxHash  down_idx_v;
   
   public static final String[] Reference_Dates={"20141128", "20141111", "20141112"};
   public SimpleTSRunPlan()
   {
      my_trans = null;
      try
      {
      	if(up_idx_ma == null)
      	{
          up_idx_ma  = stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[0],"MA","UP");
          for(int i=1;i<Reference_Dates.length;i++)
            up_idx_ma.add(stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[i],"MA","UP"));
          
          System.out.println("up_idx_ma------------");
          up_idx_ma.dump();
        }
      	if(down_idx_ma == null)
      	{
          down_idx_ma  = stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[0],"MA","DOWN");
          for(int i=1;i<Reference_Dates.length;i++)
            down_idx_ma.add(stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[i],"MA","DOWN"));

          System.out.println("down_idx_ma------------");
          down_idx_ma.dump();
        }
      	if(up_idx_k == null)
      	{
          up_idx_k  =  stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[0],"K","UP");
          for(int i=1;i<Reference_Dates.length;i++)
            up_idx_k.add(stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[i],"K","UP"));

          System.out.println("up_idx_k------------");
          up_idx_k.dump();
        }
      	if(down_idx_k == null)
      	{
          down_idx_k  = stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[0],"K","DOWN");
          for(int i=1;i<Reference_Dates.length;i++)
            down_idx_k.add(stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[i],"K","DOWN"));
 
          System.out.println("down_idx_k------------");
          down_idx_k.dump();
        }
      	if(up_idx_kd == null)
      	{
          up_idx_kd  = stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[0],"KD","UP");
          for(int i=1;i<Reference_Dates.length;i++)
            up_idx_kd.add(stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[i],"KD","UP"));

          System.out.println("up_idx_kd------------");
          up_idx_kd.dump();
        }
      	if(down_idx_kd == null)
      	{
          down_idx_kd  = stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[0],"KD","DOWN");
          for(int i=1;i<Reference_Dates.length;i++)
            down_idx_kd.add(stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[i],"KD","DOWN"));

          System.out.println("down_idx_kd------------");
          down_idx_kd.dump();
        }
      	if(up_idx_v == null)
      	{
          up_idx_v  = stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[0],"V","UP");
          for(int i=1;i<Reference_Dates.length;i++)
            up_idx_v.add(stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[i],"V","UP"));

          System.out.println("up_idx_v------------");
          up_idx_v.dump();
        }
      	if(down_idx_v == null)
      	{
          down_idx_v  = stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[0],"V","DOWN");
          for(int i=1;i<Reference_Dates.length;i++)
            down_idx_v.add(stock.tool.TimeIdxHash.gQueryByDate(Reference_Dates[i],"V","DOWN"));

          System.out.println("down_idx_v------------");
          down_idx_v.dump();
        }
      }catch(Exception xxe)
      {
         up_idx_ma = null;  
         down_idx_ma = null;
         up_idx_k = null;;   
         down_idx_k = null;; 
         up_idx_kd = null;;  
         down_idx_kd = null;;
         up_idx_v = null;;   
         down_idx_v = null;; 
         xxe.printStackTrace();
      }
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
   int  getKType(KValue kv)
   {
      int kR = (int)kv.getReal();
      int kU = (int)kv.getUp();
      int kD = (int)kv.getDown();
      
      if(  kU == 0 &&  kD == 0)
      {
          if(kR > 0 ) return 0;
          else if(kR == 0) return 1;
          else return 2;
      } else if(kU == 0)
      {
          if(kR > 0 ) return 3;
          else if(kR == 0) return 4;
          else return 5;
      }else if(kD == 0)
      {
          if(kR > 0 ) return 6;
          else if(kR == 0) return 7;
          else return 8;
      } else if(kD ==  kU)
      {
          if(kR > 0 ) return 9;
          else if(kR == 0) return 10;
          else return 11;
      } else if(kD <  kU)
      {
          if(kR > 0 ) return 12;
          else if(kR == 0) return 13;
          else return 14;
      } else
      {
           if(kR > 0 ) return 15;
          else if(kR == 0) return 16;
          else return 17;
      }
   }
   
   String culKFs(KValue kv1, KValue kv2)
   {
       char[] ptn = {'-','-'};
       ptn[0] = (char)(getKType(kv1)+0x30);
       ptn[1] = (char)(getKType(kv2)+0x30);
       return new String(ptn);
   }

   String culKDFs(Line l)
   {
       char[] ptn = {'-','-'};
       Line rsvl = l.rsv(10);
       Line kd = rsvl.macd(1,3);
       Line ks = kd.macd(1,2);
       Value kdv = kd.valueAt(kd.length()-1);
       Value kds = kd.valueAt(ks.length()-1);
       if(kdv.getValue() > 85) ptn[0] = '0';
       else if(kdv.getValue() > 60) ptn[0] = '1';
       else if(kdv.getValue() > 40) ptn[0] = '2';
       else if(kdv.getValue() > 15) ptn[0] = '3';
       else ptn[0] = '4';
       if(kdv.getValue() > kds.getValue())
       {
          ptn[1] = '0';
       } else
       {
          ptn[1] = '1';
       } 
        return new String(ptn);
   }

   public int checkStatus(FTT_sFactory fsrc) 
   {
   	  if(my_trans == null)
   	  {
           double v =  fsrc.getLastValueByCode(fsrc.current_TX);
   	       KLine kl_ex = (KLine)fsrc.getLineByCode(FTT_sFactory.current_TX, 0);
           if(kl_ex == null) return TSRunPlan.END;   
           int endIdx =  kl_ex.length()-1;
           if(endIdx < 12) return TSRunPlan.END;
           if(kl_ex.valueAt(endIdx).getTimeValue()<93000 || kl_ex.valueAt(endIdx).getTimeValue() > 132500) return TSRunPlan.END;
        
           Line vl = kl_ex.getVol().sub(endIdx-20,endIdx).diff(1);
           int v_endIdx =  vl.length()-1;
 
           double mav13  = kl_ex.sub(endIdx-13,endIdx-1).getAvg();
           double mav8   = kl_ex.sub(endIdx-8,endIdx-1).getAvg();
           double mav3   = kl_ex.sub(endIdx-3,endIdx-1).getAvg();
        	 double max = kl_ex.sub(5,endIdx-1).getMax();
        	 double min = kl_ex.sub(5,endIdx-1).getMin();

           double v_mav8   = vl.sub(v_endIdx-8,v_endIdx-1).getAvg();
           double v_mav3   = vl.sub(v_endIdx-3,v_endIdx-1).getAvg();
        	 double v_max = vl.sub(0,vl.length()-2).getMax();
        	 double v_min = vl.sub(0,vl.length()-2).getMin();
           double v_v = vl.valueAt(vl.length()-1).getValue();
           
        	 String _sta_fs_ma = culFs(min,mav3,mav8,mav13,max,v);
        	 String _sta_fs_k = culKFs((KValue)kl_ex.valueAt(endIdx-2),(KValue)kl_ex.valueAt(endIdx-1));
        	// String _sta_fs_kd = culKDFs(kl_ex.sub(endIdx-25,endIdx-1));
        	 String _sta_fs_v = culFs(v_min,v_mav3,v_mav8,0,v_max,v_v);
        	 //String _sta_fs = culFs(min,mav3,mav8,mav13,max,v);
   	       {  
   	       	  double up_p_ma = up_idx_ma.getValue(_sta_fs_ma);
   	       	  double down_p_ma = down_idx_ma.getValue(_sta_fs_ma);
   	       	  double up_p_k = up_idx_k.getValue(_sta_fs_k);
   	       	  double down_p_k = down_idx_k.getValue(_sta_fs_k);
   	       	  double up_p_v = up_idx_v.getValue(_sta_fs_v);
   	       	  double down_p_v = down_idx_v.getValue(_sta_fs_v);
           /*   if( up_p_ma > 0.4 && up_p_ma > down_p_ma && (up_p_v) > 0)
   	          {
        	       int center = (int)((v-0+UPBOUND)/2);
        	       int buy_v = (int)(v-0);
        	       my_trans = new TM_Transaction(center, center-buy_v, "MXFA5");
   
 	          } else */if(down_p_ma > 0.4 && down_p_ma > up_p_ma  && (down_p_v) > 0)
        	    {
        	       int center = (int)((v+0+LOWBOUND)/2);
        	       int sell_v = (int)(v+0);
        	       my_trans = new TM_Transaction(fsrc, center, sell_v-center, "MXFA5");
     	          } else
   	          {
   	             return TSRunPlan.END; 
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