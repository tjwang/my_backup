import stock.db.*;
import stock.fight.*;
import stock.tool.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class FTT_sFactory extends KDataLineFactory{
 
    static public String  current_TX = "TX114";
    static public String  current_Method= "not_easy";
    Vector<F2_Rec> infodata ;
    String  _sqlStr;
    String  _snum;
    String  _startdate;
    Hashtable datapool_m;
    Hashtable wdataBound ;
    Hashtable<String,SRec> _g100snums;  
    double totalM;
    float tx_last_close = -1;
    boolean realTime = false;
    int fail_count;
    int match_count;
    int _testTime;
    double setValue;
    double setValue1;
    double setValue2;
    boolean ts_start ;
    TSRunPlan currentPlan;
double convertUni(String code,double value)
{
	try
	{
     double[] bounds = (double[])wdataBound.get(code);
     if(bounds == null)
     {
        bounds = new double[2];
        PLDayk_Rec plxxk = new PLDayk_Rec();
        plxxk.SelectBySQL(" select wcode as snum, date, time,open, high,low,close,volume,money from pwdayk where wcode='"+code+"' order by date desc limit 1 ");
        double lclose = Double.parseDouble(plxxk.close);
        bounds[0] = lclose * 0.85;
        bounds[1] = lclose * 1.15;
        wdataBound.put(code,bounds);
     }
     return ((value - bounds[0]) / ( bounds[1]-bounds[0])) * 100; 
  }catch(Exception xe)
  {
  	 xe.printStackTrace();
     return value;
  }
  
}
    
static 	F2_Rec parseOneField(String code,String ds,String data)
{
      	 F2_Rec f2r = new F2_Rec();
      	 StringTokenizer st = new StringTokenizer(data,"|");
      	 st.nextToken();
         f2r.date = ds;
         f2r.time = st.nextToken();
         f2r.f1 = st.nextToken();
         f2r.f2 = f2r.f1;
        return f2r;
}

static 	F2_Rec parseTwoField(String code,String ds,String data)
{
      	 F2_Rec f2r = new F2_Rec();
      	 StringTokenizer st = new StringTokenizer(data,"|");
      	 st.nextToken();
         f2r.date = ds;
         f2r.time = st.nextToken();
         f2r.f1 = st.nextToken();
         f2r.f2 = st.nextToken();
        return f2r;
}
static 	PLDayk_Rec parseWDKField(String code,String ds,String data)
{
      	 PLDayk_Rec plk = new PLDayk_Rec();
      	 StringTokenizer st = new StringTokenizer(data,"|");
      	 plk.snum = st.nextToken();
         plk.date = st.nextToken();
         plk.time = st.nextToken();
         plk.open = st.nextToken();
         plk.high = st.nextToken();
         plk.low = st.nextToken();
         plk.close = st.nextToken();
         plk.volume = st.nextToken();
         plk.money = st.nextToken();
         return plk;
}

   void loadSnumRatio()
   {
//       totalM = 0;
//       _g100snums = new Hashtable<String,SRec>();
//      try
//      {
//           Ds000_Rec dsr = new Ds000_Rec();
//           Enumeration  e = dsr.SelectBySQL("select snum,sname,capital from ds000fw1 ;");
//           while(e.hasMoreElements())
//           {
//               dsr = (Ds000_Rec)e.nextElement();
//               SRec sr =new SRec();
//               sr.snum = dsr.snum;
//               sr.lastc = Float.parseFloat(dsr.sname);
//               sr.ratio = sr.lastc * Double.parseDouble(dsr.capital);
////               totalR[idx] = Double.parseDouble()*Double.parseDouble(dsr.capital);   
//               totalM += sr.ratio;
// //              System.out.println(dsr.dump()+" --> totalM:"+totalM);
//               _g100snums.put(sr.snum,sr);
//           }
//           e = _g100snums.keys();
//           while(e.hasMoreElements())
//           {
//               String skey = (String)e.nextElement();
//               SRec sr =_g100snums.get(skey);;
//                sr.ratio =  (sr.ratio / totalM);
//  //             System.out.println( sr.snum+"("+sr.lastc+") --> "+  sr.ratio);
//           }
//        }catch(Exception xxe)
//        {
//           xxe.printStackTrace();
//        }
   }
   
   double selfIndex_current()
   {
      
      Enumeration<String> e = _g100snums.keys();
      double idx_sum = 0;
      while(e.hasMoreElements())
      {
          String skey = (String)e.nextElement();
          KexLine kl_ex = (KexLine)datapool_m.get(skey);
          SRec sr =_g100snums.get(skey);
          double _idx = sr.ratio;
          if(kl_ex!=null && kl_ex.length() > 0)
          {
             KexValue kxv = kl_ex.valueAtLast();
             double diff = kxv.getValue() - sr.lastc;
             _idx = (kxv.getValue() / sr.lastc) * sr.ratio ;
    //         System.out.println( sr.snum+"("+sr.lastc+") --> "+  sr.ratio+"current --<"+diff+" idx:"+_idx);
          }
          idx_sum += _idx;
      }
      return idx_sum;
   }
   
   double selfIndex(DomainValue dv)
   {
      return selfIndex(dv,false);
   }
   
   double selfIndex(DomainValue dv,boolean debug)
   {
      
      Enumeration<String> e = _g100snums.keys();
      double idx_sum = 0;
      double idx_base = 0;
      while(e.hasMoreElements())
      {
          String skey = (String)e.nextElement();
          KexLine kl_ex = (KexLine)datapool_m.get(skey);
          SRec sr =_g100snums.get(skey);
          double _idx = sr.ratio;
          if(debug)
          {
             idx_base += _idx;
          }
          if(kl_ex!=null)
          {
             KexValue kxv = (KexValue)kl_ex.valueAt(dv);
             if(kxv!= null)
             {
                double diff = kxv.getValue() - sr.lastc;
                _idx = (kxv.getValue() / sr.lastc) * sr.ratio ;
                if(debug)
                {
                   System.out.println( sr.snum+"("+sr.lastc+") --> "+  sr.ratio+"current --<"+diff+" idx:"+_idx);
                }
             } 
          }
          idx_sum += _idx;
      }
      if(debug)
       {
          System.out.println( "idx_sum --> "+ idx_sum +" idx_base --< "+ idx_base);
       }
      return idx_sum;
   }
   
   int allmaIndex(DomainValue dv)
   {
      return allmaIndex(dv, false);
   }
   
   int allmaIndex(DomainValue dv,boolean debug)
   {
//      Enumeration<String> e = _g100snums.keys();
      Enumeration<String> e = datapool_m.keys();
      int idx_sum_u = 0;
      int idx_sum_d = 0;
      while(e.hasMoreElements())
      {
          String skey = (String)e.nextElement();
          if(skey.length() != 4 || skey.charAt(0) == '^')
          {
              continue;
          }
          Line kl_ex = (KexLine)datapool_m.get(skey);
       //   System.out.println(skey+"-->"+kl_ex);
          int endIdx =  kl_ex.length()-1;
          if(dv != null)
          {
              endIdx = kl_ex.mappingIdx(kl_ex.valueAt(dv));
          } 
          if(endIdx < 12) continue;
          double mav13  = kl_ex.sub(endIdx-12,endIdx).getAvg();
          double mav8   = kl_ex.sub(endIdx-7,endIdx).getAvg();
          double mav3   = kl_ex.sub(endIdx-2,endIdx).getAvg();
         //   System.out.println("m13:"+l1.getAvg()+ " m8:"+l2.getAvg()+" m3:"+l3.getAvg());
          if(mav3-mav8 > 0 && mav8 -mav13 > 0)
          {
             idx_sum_u ++;
          }         
          if(mav3-mav8 < 0 && mav8 -mav13 < 0)
          {
             idx_sum_d ++;
          }         
          /*
          if(kl_ex.length() < 13) continue;
          LineGroup gl = new LineGroup(kl_ex);
          gl.addLine(kl_ex.avg(3));
          gl.addLine(kl_ex.avg(8));
          gl.addLine(kl_ex.avg(13));
          gl.setupLine();
          Line[][] result = gl.diff();
          if(result[0][1].valueAt(result[0][1].length()-1).getValue() > 0 && result[1][2].valueAt(result[1][2].length()-1).getValue() > 0)
          {
             idx_sum ++;
          }*/
     }
      return idx_sum_u*1000+idx_sum_d;
   }
   
   void makeIndexBySelf(DomainValue dv, int xtime_i)
   {
       double self_idx = 0;
       if(realTime)
       {
           self_idx = selfIndex_current();
       } else
       {
           self_idx = selfIndex(dv);
       }
       KexLine kx_tx_self = (KexLine)datapool_m.get("TX_SELF");
       if(kx_tx_self == null)
       {
          kx_tx_self = new KexLine();
          datapool_m.put("TX_SELF",kx_tx_self);
       }
 	     kx_tx_self.add(new KexValue("TX_SELF", Integer.parseInt(_startdate), xtime_i/100 * 100, self_idx, self_idx, self_idx, self_idx, 0 , 0));
   
   }
   void makeMaStatisic(DomainValue dv, int xtime_i)
   {
       int allma_idx = 0;
       double allma_idx_u = 0;
       double allma_idx_d = 0;
      if(realTime)
       {
           allma_idx = allmaIndex(null);
       } else
       {
           allma_idx = allmaIndex(dv);
       }
       allma_idx_u = allma_idx/1000;
       allma_idx_d = allma_idx%1000;
       KexLine kx_ma_self = (KexLine)datapool_m.get("MA_ALL");
       if(kx_ma_self == null)
       {
          kx_ma_self = new KexLine();
          datapool_m.put("MA_ALL",kx_ma_self);
       }
       KexLine kx_mad_self = (KexLine)datapool_m.get("MA_ALL_D");
       if(kx_mad_self == null)
       {
          kx_mad_self = new KexLine();
          datapool_m.put("MA_ALL_D",kx_mad_self);
       }
	     kx_ma_self.add(new KexValue("MA_ALL", Integer.parseInt(_startdate), xtime_i/100 * 100, allma_idx_u, allma_idx_u, allma_idx_u, allma_idx_u, 0 , 0));
	     kx_mad_self.add(new KexValue("MA_ALL_D", Integer.parseInt(_startdate), xtime_i/100 * 100, allma_idx_d, allma_idx_d, allma_idx_d, allma_idx_d, 0 , 0));
   
   }
   boolean testConditionMAm(String code)// ma3 between ma8~ma13
   {
   	   
        Line kl_ex = (KexLine)datapool_m.get(code);
        if(kl_ex == null) return false;   
        int endIdx =  kl_ex.length()-1;
        if(endIdx < 12) return false;
//        if(kl_ex.valueAt(endIdx).getTimeValue()>=125900) return false;
        if(kl_ex.valueAt(endIdx).getTimeValue()<93000) return false;
        
        setValue = kl_ex.valueAt(endIdx).getValue();
        return true;
/*        
        double mav13  = kl_ex.sub(endIdx-12,endIdx).getAvg();
        double mav8_1   = kl_ex.sub(endIdx-7,endIdx).getAvg();
        double mav8_0   = kl_ex.sub(endIdx-8,endIdx-1).getAvg();
        double mav3_1   = kl_ex.sub(endIdx-2,endIdx).getAvg();
        double mav3_0   = kl_ex.sub(endIdx-3,endIdx-1).getAvg();
       if(mav13 > mav8_1 && mav8_1 > mav3_1) return false;
         if(mav13 < mav8_1 && mav8_1 < mav3_1) return false;
        if((mav3_0-mav8_0)*(mav3_1-mav8_1) < 0)
        {
        	 setValue = mav3_1;
           return true;
        }         
        return false;         
  */
   }

   TSRunPlan findBestPlan()
   {
      if(S4_TransactionManager.one_instance == null)
      {
           S4_TransactionManager.one_instance =new S4_TransactionManager(_startdate);
           S4_TransactionManager.one_instance.start();
      }

      if(current_Method.equals("not_easy"))
      {
//          TSRunPlan trp = new S2TSRunPlan(false);
//          TSRunPlan trp = new SimpleKRunPlan(false);
          TSRunPlan trp = new SimpleTSRunPlan(false);
          trp.setLogPs(S4_TransactionManager.one_instance.getDumpPs());
          return  trp;
      }else
      {
//          TSRunPlan trp = new S2TSRunPlan(true);
//          TSRunPlan trp = new SimpleKRunPlan(true);
          TSRunPlan trp = new SimpleTSRunPlan(true);
          trp.setLogPs(S4_TransactionManager.one_instance.getDumpPs());
          return  trp;
      }
   }
   
   void statisicStock(DomainValue dv, int xtime_i)
   {
       Line kl_ex = getLineByCode(FTT_sFactory.current_TX, 0);
       if(kl_ex.length() < 2) return ;

       if(!(_testTime - kl_ex.valueAt(kl_ex.length()-2).getTimeValue() < 100 &&  _testTime - kl_ex.valueAt(kl_ex.length()-2).getTimeValue() >= 0)) return ;
       if(currentPlan != null && currentPlan.checkStatus(this)==TSRunPlan.END)
       {
           if(S4_TransactionManager.one_instance != null)
           {
              if(S4_TransactionManager.one_instance.getTransactionStatus() == Transaction.CLOSED)
              {
                 currentPlan = null;
              } else 
              {
                 S4_TransactionManager.one_instance.cancel();
              }
           } else
           {
              currentPlan = null;
           }
       }     
       
       if(currentPlan == null)
       {
           currentPlan = findBestPlan();
       }
       
       if(
        // testConditionMAm(current_TX)/* && testConditionMAm("TX") && testConditionMAm("200") */
       //&&  (testConditionMAm("2330") || testConditionMAm("2412") || testConditionMAm("2454"))
         currentPlan!=null && currentPlan.checkStatus(this)==TSRunPlan.TRANS
       )
       {   
       	   ts_start = true;
           if(S4_TransactionManager.one_instance.getTransactionStatus() < 0 ||S4_TransactionManager.one_instance.getTransactionStatus() == Transaction.CLOSED)
           {
              if(S4_TransactionManager.one_instance.isFail()) fail_count++;
              //if(fail_count < 3)
              {
               //  T4_TransactionManager.one_instance.open(new TM_Transaction((int)getLastValueByCode("TX104"), 3, "MXFJ4"));
                 S4_TransactionManager.one_instance.open(currentPlan.getTransaction());
/*
                 if(current_Method.equals("not_easy"))
                 {
                    S4_TransactionManager.one_instance.open(new F_TSM_Transaction(this,(int)setValue, 18, "MXFJ4"));
                 }else
                 {
                    S4_TransactionManager.one_instance.open(new F_TSM_Transaction(this,(int)setValue, 18, "MXFJ4", true));
                 }
*/
                 S4_TransactionManager.one_instance.setMaxLoss(currentPlan.getMaxLoss());
                 if(currentPlan.getMaxGet() != 0)
                 {
                   S4_TransactionManager.one_instance.setMaxGet(currentPlan.getMaxGet());
                 }
                 S4_TransactionManager.one_instance.setTimeOut(currentPlan.getTimeOut());
                 S4_TransactionManager.one_instance.sRun();
              }
           } else
           {
               if(S4_TransactionManager.one_instance.getMaxLoss() != currentPlan.getMaxLoss())
               {
                  S4_TransactionManager.one_instance.setMaxLoss(currentPlan.getMaxLoss());
               }
               if(S4_TransactionManager.one_instance.getMaxGet() != currentPlan.getMaxGet())
               {
                  S4_TransactionManager.one_instance.setMaxGet(currentPlan.getMaxGet());
               }
               if(S4_TransactionManager.one_instance.getTimeOut() != currentPlan.getTimeOut())
               { 
                  S4_TransactionManager.one_instance.setTimeOut(currentPlan.getTimeOut());
               }
           }
          
       }
       /*
        else if(currentPlan.checkStatus(this)==TSRunPlan.END)
       {
         currentPlan = null;
       }
       */
   }
   
   public  double getLastValueByCode(String code)
   {
      KexLine kl_ex = (KexLine)datapool_m.get(code);
      if(kl_ex!=null &&kl_ex.length()>0)
      {
         return kl_ex.valueAt(kl_ex.length()-1).getValue();
      }
      return 0;
   }

   public  int getLastValueTime(String code)
   {
      KexLine kl_ex = (KexLine)datapool_m.get(code);
      if(kl_ex!=null &&kl_ex.length()>0)
      {
         return kl_ex.valueAt(kl_ex.length()-1).getTimeValue();
      }
      return 0;
   }

   public  int getLastValueDate(String code)
   {
      KexLine kl_ex = (KexLine)datapool_m.get(code);
      if(kl_ex!=null &&kl_ex.length()>0)
      {
         return kl_ex.valueAt(kl_ex.length()-1).getDateValue();
      }
      return 0;
   }
    
    
    public    FTT_sFactory(String snum,String stardate) throws Exception
    {
        this(snum,stardate,90000);
    }
    public    FTT_sFactory(String snum,String stardate,int testTime) throws Exception
    {
        this(snum,stardate,testTime,null); 
    }
    public    FTT_sFactory(String snum,String stardate,int testTime,InputStream is) throws Exception
    {
        _snum = snum;
//        tis = is;
        datapool_m = new Hashtable();
        wdataBound = new Hashtable();
        _startdate = stardate;
        _testTime = testTime;
     	   fail_count = 0;
     	   ts_start = false;
        currentPlan = null;
//        loadSnumRatio();
        StockKD  skd = new StockKD("0001","20141001",stardate);
    	  Line kl = skd.culKLine();
    	  datapool_m.put("D_K_Line",kl);
    	  loadKData(snum, stardate);
    	  realTime = false;
    	  if(S4_TransactionManager.one_instance!=null)
    	  {
//    	     System.out.println("fail_count: "+S4_TransactionManager.one_instance.fail_count+ " match_count: "+S4_TransactionManager.one_instance.do_count+" ssesP:"+(1-(float)S4_TransactionManager.one_instance.fail_count/(float)S4_TransactionManager.one_instance.do_count));
//    	     System.err.println("fail_count: "+S4_TransactionManager.one_instance.fail_count+ " match_count: "+S4_TransactionManager.one_instance.do_count+" ssesP:"+(1-(float)S4_TransactionManager.one_instance.fail_count/(float)S4_TransactionManager.one_instance.do_count));
    	  }
    }
    
   synchronized void setReadData(byte[] databuf)
    {
        String code = (new String(databuf,0,12)).trim();
//        if(code.equals("TX074")||code.equals("TX064")||code.equals("TX094")||code.equals("TX124")||code.equals("TX")) 
        if(code.indexOf("TX") == 0) 
        {
             PFFiveRaw_Rec ffrr = new PFFiveRaw_Rec(code, _startdate, databuf, 12);
      	     KexLine kl_ex = (KexLine)datapool_m.get(code);
      	     if(kl_ex == null)
      	     {
      	        kl_ex = new KexLine();
      	        datapool_m.put(code,kl_ex);
      	     }
      	     setFiveRaw(kl_ex,ffrr);
             
             if(code.equals(current_TX) && currentPlan != null)
             {  
              	  statisicStock(null, 0);// currentPlan.checkStatus(this);
      	     }

      	     if(code.equals(current_TX))
      	     {
      	       if(S4_TransactionManager.one_instance != null) 
      	       {
//      	          System.out.println("S4_TransactionManager.one_instance.getTransactionStatus() " +S4_TransactionManager.one_instance.getTransactionStatus() +" x "+  Transaction.INITED +" y"+Transaction.CLOSED);
      	          if(S4_TransactionManager.one_instance.getTransactionStatus() >= Transaction.INITED && S4_TransactionManager.one_instance.getTransactionStatus() < Transaction.CLOSED)
      	          {
  //    	             System.out.println("xxxxxZx");
      	             S4_TransactionManager.one_instance.sRun();
      	          }
      	       }
      	     }
         }else if(code.equals("1"))/*發行量加權股價指數*/
         {
             
         }else if(code.equals("2"))/*未含金融保險股指數*/
         {
         }else if(code.equals("200"))/*成交金額*/
         {
              String data= (new String(databuf,12,500)).trim();
//              System.out.println(data);
    	        F2_Rec f2 = parseOneField(code, _startdate, data);
      	      KexLine kl_ex = (KexLine)datapool_m.get(code);
      	      if(kl_ex == null)
      	      {
      	         kl_ex = new KexLine();
      	         datapool_m.put(code,kl_ex);
      	      }
      	      setTwoField2(code,kl_ex,f2,1);
         }else if(code.equals("201")/*成交數量,成交筆數*/
                ||code.equals("221")/*成交數量,成交筆數<股票> */
                ||code.equals("110")/*總委買數量,總委買筆數*/
                ||code.equals("111")/*總委賣數量,總委賣筆數*/
                ||code.equals("130")/**總委買數量,總委買筆數<股票>*/
                ||code.equals("131")/*總委賣數量,總委賣筆數<股票>*/
                ||code.equals("132")/*漲停總委賣數量,漲停總委賣筆數<股票>*/
                ||code.equals("133")/*漲停總委賣數量,漲停總委賣筆數<股票>*/
                ||code.equals("134")/*跌停總委賣數量,跌停總委賣筆數<股票>*/
                ||code.equals("135"))/*跌停總委賣數量,跌停總委賣筆數<股票>*/
         {
         	 try
         	 {
    	        String data= (new String(databuf,12,500)).trim();
       //       System.out.println(data);
    	        F2_Rec f2 = parseTwoField(code, _startdate, data);
      	      KexLine kl_ex = (KexLine)datapool_m.get(code);
      	      if(kl_ex == null)
      	      {
      	         kl_ex = new KexLine();
      	         datapool_m.put(code,kl_ex);
      	      }
      	      setTwoField(code,kl_ex,f2,1);
      	      KexLine kl_ex2 = (KexLine)datapool_m.get(code+":2");
      	      if(kl_ex2 == null)
      	      {
      	         kl_ex2 = new KexLine();
      	         datapool_m.put(code+":2",kl_ex2);
      	      }
      	      setTwoField(code,kl_ex2,f2,2);
      	    }catch(java.util.NoSuchElementException ue)
      	    {
      	    }
         }else if(code.equals("220"))/*成交金額<股票>*/
         {
              String data= (new String(databuf,12,500)).trim();
//              System.out.println(data);
    	        F2_Rec f2 = parseOneField(code, _startdate, data);
      	      KexLine kl_ex = (KexLine)datapool_m.get(code);
      	      if(kl_ex == null)
      	      {
      	         kl_ex = new KexLine();
      	         datapool_m.put(code,kl_ex);
      	      }
      	      setTwoField2(code,kl_ex,f2,1);
  
         }else if(code.equals("^HSI")||code.equals("^N225")||code.equals("000001.SS")||code.equals("^KS11"))
         {
            /*
     	       String data= (new String(databuf,12,500)).trim();
     	       PLDayk_Rec plk = parseWDKField(code, _startdate, data);
      	     KexLine kl_ex = (KexLine)datapool_m.get(code);
      	     if(kl_ex == null)
      	     {
      	        kl_ex = new KexLine();
      	        datapool_m.put(code,kl_ex);
      	     }
      	      setWDKField(kl_ex,plk);
      	      */
         }else
         {
         	 try
         	 {
     	       String data= (new String(databuf,12,500)).trim();
     	       PFiveRaw_Rec plk = new PFiveRaw_Rec(data);
      	     KexLine kl_ex = (KexLine)datapool_m.get(code);
      	     if(kl_ex == null)
      	     {
      	        kl_ex = new KexLine();
      	        datapool_m.put(code,kl_ex);
      	     }
      	     setPFiveRawField(kl_ex,plk);
      	   }catch(Exception xxe)
      	   {
      	   }
        }
    }

    public void loadKData(String snum,String stardate) throws Exception
    {
         FileInputStream is = new FileInputStream("X:\\Working\\Data\\"+stardate+"\\fss_9999.log."+stardate);
         byte[] databuf = new byte[512];
         int rlen = 0;
         String s = null;
         int count = 0;
         while((rlen = is.read(databuf)) > 0)
         {
             if(rlen != 512) throw new Exception("Error reading in file format");
             setReadData(databuf);
         }
         is = new FileInputStream("X:\\Working\\Data\\"+stardate+"\\fss_8888.log."+stardate);
         while((rlen = is.read(databuf)) > 0)
         {
             if(rlen != 512) throw new Exception("Error reading in file format");
             setReadData(databuf);
             if(ts_start)
             {
                if(currentPlan != null && currentPlan.checkStatus(this)==TSRunPlan.END)
                {
                    return ;
                }
                if(getLastValueTime(FTT_sFactory.current_TX) - _testTime > 1500)
                {
                    return ;
                }
             }
         }
    }
   /* 
    public void loadMData() throws Exception
    {
        dayKdata = new TreeSet(new KdataComparator());
        PMAmount_Rec pmr = new PMAmount_Rec();
        PMAmount_Rec ppmr = null;
        String mOpen  = null;
        String mHigh  = null;
        String buyer  = null;
        String mClose = null;
        String mLow   = null;
        String seller = null;
        Enumeration e = null;
        if(_startdate == null){
            e = pmr.SelectBySQL("select * from  pmamount2 where   snum='"+_snum+"' order by date,sum "  ); 
        }else {
            e = pmr.SelectBySQL("select * from  pmamount2 where   snum='"+_snum+"' and date > '"+_startdate+"' order by date,sum "  );    
        }
        while(e.hasMoreElements()){
        	pmr = (PMAmount_Rec)e.nextElement();
        	if(mOpen == null) 
        	{
        		 mOpen = pmr.sum;
        		 mLow = "-"+pmr.outAmount;
        		 buyer = pmr.mnum;
          }
          if(ppmr!=null && !pmr.date.equals(ppmr.date))
          {
             PLDayk_Rec pkr= new PLDayk_Rec();
        		 mClose = ppmr.sum;
        		 mHigh = ppmr.inAmount;
        		 seller = ppmr.mnum;
        		 pkr.snum = ppmr.snum;
        		 pkr.date = ppmr.date;
        		 pkr.time = "0";
        		 pkr.open = mOpen;
        		 pkr.high = mHigh;
        		 pkr.close = mClose;
        		 pkr.low = mLow;
        		 pkr.volume = buyer;
        		 pkr.money = seller;
             add(pkr);
             mOpen  = null;
             mHigh  = null;
             buyer  = null;
             mClose = null;
             mLow   = null;
             seller = null;
          }
          ppmr = pmr;
       }
        
    }
*/
    void   setFiveRaw(KexLine kx, PFFiveRaw_Rec pr)
    {
     	      if(pr.time == null || pr.time.equals("null")) return;
     	      if("".equals(pr.s_rp) || Float.parseFloat(pr.s_rp) < 1)
     	      {
     	         return ;
     	      }
     	      KexValue kxv = kx.valueAtLast();
            int xtime_i = Integer.parseInt("1"+pr.time)-1000000;
            if(kxv == null)
            {
 	            kxv = new KexValue(pr.snum, Integer.parseInt(_startdate), xtime_i/100 * 100, -1, -1, 999999, 0, 0 , 0);
 	            kx.add(kxv);
 	          }
     	      if(pr.snum.equals("TX"))
     	      {
     	     //    System.out.println("pr.s_lclose -->"+pr.s_lclose);
//               if(tx_last_close < 0)
//               {
//                  tx_last_close = Float.parseFloat(pr.s_lclose);
//                  Enumeration e = _g100snums.keys();
//                  while(e.hasMoreElements())
//                  {
//                       String skey = (String)e.nextElement();
//                       SRec sr =_g100snums.get(skey);;
//                       sr.ratio =  sr.ratio * tx_last_close;
////                       System.out.println( sr.snum+"("+sr.lastc+") --> "+  sr.ratio);
//                  }
//         //         System.out.println("tx_last_close:"+tx_last_close+"  "+selfIndex(kxv.getDomainValue(),true));
//               }
     	      }
            int time_i = kxv.getTimeValue();
            double val = Double.parseDouble(pr.s_rp);
            while(xtime_i - time_i >= 100)
            {    	      
               if(time_i % 10000 == 5900)
               {
                 time_i += 4000;
               }
               time_i += 100;
               {
                 if(val < kxv.getLow()) kxv.setLow(val);
                 if(val > kxv.getHigh()) kxv.setHigh(val);
                 kxv.setClose(val);
               }
               if(pr.snum.equals(current_TX))
               {
  //                kxv.dump();
                  statisicStock(kxv.getDomainValue(), xtime_i);
               }
               KexValue nkxv = new KexValue(kxv.getName(),kxv.getDateValue(), time_i, 
                              kxv.getClose(), kxv.getClose(), kxv.getClose(), kxv.getClose(), 
                              kxv.getVolume() , kxv.getMoney());
               kx.add(nkxv);
               kxv = nkxv;              
               if(xtime_i - time_i < 100 )
               {
                  kxv.setOpen(-1);
                  kxv.setHigh(-1);
                  kxv.setLow(999999);
                  kxv.setClose(0);
                  kxv.setVolume(0);
                  kxv.setMoney(0);
               }
            }
            if(kxv.getOpen() < 0)
            {
               kxv.setOpen(val);
            }
            if(val < kxv.getLow()) kxv.setLow(val);
            if(val > kxv.getHigh()) kxv.setHigh(val);
            kxv.setClose(val);
            if(pr.s_tra != null && !"".equals(pr.s_tra) && !"null".equals(pr.s_tra))
            {
               float x_ra = Float.parseFloat(pr.s_tra);
               if(kxv.getVolume() == 0)
               {
                 kxv.setVolume(x_ra);
               }
               kxv.setMoney(x_ra);
            }
    
    }
    
    void   setPFiveRawField(KexLine kx, PFiveRaw_Rec pr)
    {
     	      if(pr.time == null || pr.time.equals("null")) return;
     	      if("".equals(pr.rp)|| Float.parseFloat(pr.rp) < 1)
     	      {
     	         return ;
     	      }
     	      KexValue kxv = kx.valueAtLast();
            int xtime_i = Integer.parseInt("1"+pr.time)-1000000;
            if(kxv == null)
            {
 	            kxv = new KexValue(pr.snum, Integer.parseInt(_startdate), xtime_i/100 * 100, -1, -1, 999999, 0, 0 , 0);
 	            kx.add(kxv);
 	          }
            int time_i = kxv.getTimeValue();
            while(xtime_i - time_i >= 100)
            {    	      
               if(time_i % 10000 == 5900)
               {
                 time_i += 4000;
               }
               time_i += 100;
               KexValue nkxv = new KexValue(kxv.getName(),kxv.getDateValue(), time_i, 
                              kxv.getOpen(), kxv.getHigh(), kxv.getLow(), kxv.getClose(), 
                              kxv.getVolume() , kxv.getMoney());
               kx.add(nkxv);
               kxv = nkxv;              
               if(xtime_i - time_i < 100 )
               {
                  kxv.setOpen(-1);
                  kxv.setHigh(-1);
                  kxv.setLow(999999);
                  kxv.setClose(0);
                  kxv.setVolume(0);
                  kxv.setMoney(0);
               }
            }
            double val = Double.parseDouble(pr.rp);
            if(kxv.getOpen() < 0)
            {
               kxv.setOpen(val);
            }
            if(val < kxv.getLow()) kxv.setLow(val);
            if(val > kxv.getHigh()) kxv.setHigh(val);
            kxv.setClose(val);
            if(pr.ra != null && !"".equals(pr.ra) && !"null".equals(pr.ra))
            {
               float x_ra =  Float.parseFloat(pr.ra);
               kxv.setVolume(x_ra);
               kxv.setMoney(x_ra);
            }
    
    }
    void   setTwoField(String code,KexLine kx, F2_Rec pr, int idx)
    {
        if(pr.time == null || pr.time.equals("null")) return;
        if("".equals(pr.f1) || "".equals(pr.f2))
        {
   	         return;
        }
        KexValue prev_kxv = null;
 	      KexValue kxv = kx.valueAtLast();
        int xtime_i = Integer.parseInt("1"+pr.time)-1000000;
        if(kxv == null)
        {
 	        kxv = new KexValue(code, Integer.parseInt(_startdate), xtime_i/100 * 100, -1, -1, 999999, 0, 0 , 0);
 	        kx.add(kxv);
 	      }
        double val = -1;
        if(idx == 1)
           val = Double.parseDouble(pr.f1);
        else
           val = Double.parseDouble(pr.f2);
        int time_i = kxv.getTimeValue();
        while(xtime_i - time_i >= 100)
        {    	      
            if(time_i % 10000 == 5900)
            {
               time_i += 4000;
            }
            time_i += 100;   
            {
              if(val < kxv.getLow()) kxv.setLow(val);
              if(val > kxv.getHigh()) kxv.setHigh(val);
              kxv.setClose(val);
            }
            KexValue nkxv = new KexValue(kxv.getName(),kxv.getDateValue(), time_i, 
                                 kxv.getClose(), kxv.getClose(), kxv.getClose(), kxv.getClose(), 
                                 kxv.getVolume() , kxv.getMoney());
            kx.add(nkxv);
            kxv = nkxv;              
            if(xtime_i - time_i < 100 )
            {
                kxv.setOpen(-1);
                kxv.setHigh(-1);
                kxv.setLow(999999);
                kxv.setClose(0);
                kxv.setVolume(0);
                kxv.setMoney(0);
            }
        }
        if(kxv.getOpen() < 0)
        {
             kxv.setOpen(val);
        }
        if(val < kxv.getLow()) kxv.setLow(val);
        if(val > kxv.getHigh()) kxv.setHigh(val);
        kxv.setClose(val);
    
    }
    
    void   setTwoField2(String code,KexLine kx, F2_Rec pr, int idx)
    {
   	    double diff = 0;
   	    double prevClose = 0;
   	    KexValue prev_kxv = null;
        if(pr.time == null || pr.time.equals("null")) return;
        if("".equals(pr.f1) || "".equals(pr.f2) || "null".equals(pr.f1) || "null".equals(pr.f2))
        {
   	         return;
        }
 	      KexValue kxv = kx.valueAtLast();
        int xtime_i = Integer.parseInt("1"+pr.time)-1000000;
        if(kxv == null)
        {
 	        kxv = new KexValue(code, Integer.parseInt(_startdate), xtime_i/100 * 100, -1, -1, 999999, 0, 0 , 0);
 	        kx.add(kxv);
 	      }
        int time_i = kxv.getTimeValue();
        while(xtime_i - time_i >= 100)
        {    	      
            if(time_i % 10000 == 5900)
            {
               time_i += 4000;
            }
            time_i += 100;
            prevClose = kxv.getClose();
            prev_kxv = kxv;
            KexValue nkxv = new KexValue(kxv.getName(),kxv.getDateValue(), time_i, 
                                 kxv.getOpen(), kxv.getHigh(), kxv.getLow(), kxv.getClose(), 
                                 kxv.getVolume() , kxv.getMoney());
            kx.add(nkxv);
            kxv = nkxv;              
            if(xtime_i - time_i < 100 )
            {
                kxv.setOpen(-1);
                kxv.setHigh(-1);
                kxv.setLow(0);
                kxv.setClose(0);
                kxv.setVolume(0);
                kxv.setMoney(0);
            }
        }
        double val = -1;
        if(idx == 1)
           val = Double.parseDouble(pr.f1);
        else
           val = Double.parseDouble(pr.f2);
       
        if(kxv.getOpen() < 0)
        {
             kxv.setOpen(val);
             if(prev_kxv!=null)
             {
                diff = val - prevClose;
                if(diff > prev_kxv.getHigh()) prev_kxv.setHigh(diff);
                 prev_kxv.setClose(val);
             }else
             {
                 if(diff > kxv.getHigh()) kxv.setHigh(diff);
             }
             
        } else
        {
             diff = (val-kxv.getClose());
             if(diff > kxv.getHigh()) kxv.setHigh(diff);
        }
  //      if(val < kxv.getLow()) kxv.setLow(val);
        
        kxv.setClose(val);
    
    }
    void   setWDKField(KexLine kx, PLDayk_Rec pldk)
    {
	      KexValue kxv = kx.valueAtLast();
	      String code = pldk.snum;
        if(kxv == null)
        {
 	         kxv = new KexValue(pldk.snum, Integer.parseInt(_startdate), 90000,
 	                  convertUni(code,Double.parseDouble(pldk.open)), convertUni(code,Double.parseDouble(pldk.high)), 
 	                  convertUni(code,Double.parseDouble(pldk.low)), convertUni(code,  Double.parseDouble(pldk.close)), 
 	                  Float.parseFloat(pldk.volume), Float.parseFloat(pldk.money));
 	         kx.add(kxv);
 	      } else
 	      {
           int time_i = kxv.getTimeValue();
           if(time_i % 10000 == 5900)
            {
               time_i += 4000;
            }
            time_i += 100;
 	          kxv = new KexValue(pldk.snum, Integer.parseInt(_startdate), time_i,
 	                   	       convertUni(code,Double.parseDouble(pldk.open)), convertUni(code,Double.parseDouble(pldk.high)), 
 	                           convertUni(code,Double.parseDouble(pldk.low)), convertUni(code,  Double.parseDouble(pldk.close)), 
                             Float.parseFloat(pldk.volume), Float.parseFloat(pldk.money));
 	          kx.add(kxv);
 	      }
    }
    
    public Line getLineByCode(String code,int idx)
    {
      	 Line kl_ex = (Line)datapool_m.get(code);
//      	 System.out.println(kl_ex);
         return kl_ex;
    }    
    
    public void add(PLDayk_Rec kv){
     int date_value;
     int time_value;
     try{
        date_value = Integer.parseInt(kv.date);
     }catch(Exception ne)
     {
        date_value = 0;
     }
     try{
        if(kv.time.indexOf(":") > 0)
        {
           time_value = Integer.parseInt(kv.time.substring(0,2)) * 10000 + 
                        Integer.parseInt(kv.time.substring(3,5)) * 100 +
                        Integer.parseInt(kv.time.substring(6,8)) ;
        } else
        {
        	 time_value = Integer.parseInt(kv.time);
        }
     }catch(Exception ne)
     {
        time_value = 0;
     }
	 try{
        add(kv.snum, date_value, time_value, Float.parseFloat(kv.open), Float.parseFloat(kv.high), Float.parseFloat(kv.low), Float.parseFloat(kv.close), Float.parseFloat(kv.volume), Float.parseFloat(kv.money));
	 }catch(Exception e2)
	 {
		  add(kv.snum, date_value, time_value, 0,0, 0,0,0, 0);
	 }
}  
    public void dump()
    {
        Enumeration<String> keys = datapool_m.keys();
        int count  = 0 ;
        System.out.println("FTT Factory Line Data Dump Start:");
         
        while(keys.hasMoreElements())
        {
           String code = keys.nextElement();
           System.out.println(code+"--->Line Data:");
           System.out.println("===============================================================================");
           KexLine kl_ex = (KexLine)datapool_m.get(code);
           if(kl_ex != null)
           {
              kl_ex.dump();
           }
           System.out.println("===============================================================================");
           count ++;
        }
       System.out.println("FTT Factory Line Data Dump End:"+count);
    }
}
