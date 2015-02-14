import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.pattern.*	;
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
   public static final int XX_STA = 3;
   public static final int XX_STA_2 = 4;
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
           
           if(tMode == XX_STA)
   	       {
              if(kl_ex.valueAt(endIdx).getTimeValue() >= 93000)
              {
    	           KLine txl = (KLine)fsrc.getLineByCode("TX", 0);
    	           SimpleLenPattern slp1 = new SimpleLenPattern(45);
    	           SimpleLenPattern slp2 = new SimpleLenPattern(30);
    	           PatternLine pl1 = kl_ex.pattern(slp1);
    	           PatternLine pl2 = txl.pattern(slp2);
    	           PatternValue ptnv1 = (PatternValue)pl1.valueAt(0);
     	           PatternValue ptnv2 = (PatternValue)pl2.valueAt(0);
                 System.out.print(culKFs(ptnv1._kv,ptnv2._kv)+"|");
                 KLine dkl = (KLine)fsrc.getLineByCode("D_K_Line", 0);
                 Line svl = (KLine)fsrc.getLineByCode("200", 0);
                 Line svl_201 = (KLine)fsrc.getLineByCode("201", 0);/*Θユ计秖,Θユ掸计<布> */
                 Line svl_201_1 = (KLine)fsrc.getLineByCode("201:2", 1);/*Θユ计秖,Θユ掸计<布> */
                 Line svl_130 = (KLine)fsrc.getLineByCode("130", 0);/*羆〆禦计秖,羆〆禦掸计<布>*/
                 Line svl_130_1 = (KLine)fsrc.getLineByCode("130:2", 1);
                 Line svl_131 = (KLine)fsrc.getLineByCode("131", 0);/*羆〆芥计秖,羆〆芥掸计<布*/
                 Line svl_131_1 = (KLine)fsrc.getLineByCode("131:2", 1);
             //    dkl.valueAt(dkl.length()-1).dump();
             //    dkl.valueAt(dkl.length()-2).dump();
                 System.out.println(culKFs((KValue)dkl.valueAt(dkl.length()-1),(KValue)dkl.valueAt(dkl.length()-2))+"|");
                /*
                 System.out.print(svl.valueAt(svl.length()-1).getValue()+"|");
                 System.out.print(svl_201.valueAt(svl_201.length()-1).getValue()+"|");
                 System.out.print(svl_201_1.valueAt(svl_201_1.length()-1).getValue()+"|");
                 System.out.print(svl_130.valueAt(svl_130.length()-1).getValue()+"|");
                 System.out.print(svl_130_1.valueAt(svl_130_1.length()-1).getValue()+"|");
                 System.out.print(svl_131.valueAt(svl_131.length()-1).getValue()+"|");
                 System.out.println(svl_131_1.valueAt(svl_131_1.length()-1).getValue()+"|");
                */ 
                 //ptnv1._kv.dump();
                 //ptnv2._kv.dump();
                 my_trans = new F_TSM_Transaction(fsrc, 9000, 100, "MXFJ4",_isEasy);
                return TSRunPlan.END;
   	          } else
   	          {
   	             return TSRunPlan.END;
   	          }
   	       }else if(tMode == XX_STA_2)
   	       {
    	           KLine txl = (KLine)fsrc.getLineByCode("TX", 0);
                 Line svl = (KLine)fsrc.getLineByCode("200", 0);
                 Line svl_201 = (KLine)fsrc.getLineByCode("201", 0);/*Θユ计秖,Θユ掸计<布> */
                 Line svl_201_1 = (KLine)fsrc.getLineByCode("201:2", 1);/*Θユ计秖,Θユ掸计<布> */
                 Line svl_130 = (KLine)fsrc.getLineByCode("130", 0);/*羆〆禦计秖,羆〆禦掸计<布>*/
                 Line svl_130_1 = (KLine)fsrc.getLineByCode("130:2", 1);
                 Line svl_131 = (KLine)fsrc.getLineByCode("131", 0);/*羆〆芥计秖,羆〆芥掸计<布*/
                 Line svl_131_1 = (KLine)fsrc.getLineByCode("131:2", 1);
                 System.out.print(svl.valueAt(svl.length()-1).getValue()+"|");
                 System.out.print(svl_201.valueAt(svl_201.length()-1).getValue()+"|");
                 System.out.print(svl_201_1.valueAt(svl_201_1.length()-1).getValue()+"|");
                 System.out.print(svl_130.valueAt(svl_130.length()-1).getValue()+"|");
                 System.out.print(svl_130_1.valueAt(svl_130_1.length()-1).getValue()+"|");
                 System.out.print(svl_131.valueAt(svl_131.length()-1).getValue()+"|");
                 System.out.println(svl_131_1.valueAt(svl_131_1.length()-1).getValue()+"|");
                 my_trans = new F_TSM_Transaction(fsrc, 9000, 100, "MXFJ4",_isEasy);
                return TSRunPlan.END;
   	      
   	       }
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
           
        	 _sta_ds = String.valueOf(fsrc.getLastValueDate(fsrc.current_TX));
        	 _sta_ti = fsrc.getLastValueTime(fsrc.current_TX);
        	// _sta_fs = culFs(min,mav3,mav8,mav13,max,v);
        	// _sta_fs = culKFs((KValue)kl_ex.valueAt(endIdx-2),(KValue)kl_ex.valueAt(endIdx-1));
        	// _sta_fs = culKDFs(kl_ex.sub(endIdx-25,endIdx-1));
        	 _sta_fs = culFs(v_min,v_mav3,v_mav8,0,v_max,v_v);
        	 S4_TransactionManager.one_instance.setFeature(_sta_ds,_sta_ti,_sta_fs);
        	 if(log_ps != null)
        	 {
        //	    System.out.println("create transaction "+kl_ex.valueAt(endIdx).getTimeValue());
        //	    log_ps.println("create transaction "+kl_ex.valueAt(endIdx).getTimeValue());
        	 }
        	 if(tMode == UP)
        	 {
        	    int center = (int)((v-tRange+UPBOUND)/2);
        	    int buy_v = (int)(v-tRange+1);
        	    my_trans = new F_TSM_Transaction(fsrc,center , center-buy_v, "MXFJ4",_isEasy);
   	       } else if(tMode == DOWN)
   	       {
        	    int center = (int)((v+tRange+LOWBOUND)/2);
        	    int sell_v = (int)(v+tRange-1);
        	    my_trans = new F_TSM_Transaction(fsrc, center, sell_v-center, "MXFJ4",_isEasy);
   	       }  else
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