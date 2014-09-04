import stock.db.*;
import stock.fight.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class FTT_sFactory extends KDataLineFactory{
 
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
       totalM = 0;
       _g100snums = new Hashtable<String,SRec>();
      try
      {
           Ds000_Rec dsr = new Ds000_Rec();
           Enumeration  e = dsr.SelectBySQL("select snum,sname,capital from ds000fw1 ;");
           while(e.hasMoreElements())
           {
               dsr = (Ds000_Rec)e.nextElement();
               SRec sr =new SRec();
               sr.snum = dsr.snum;
               sr.lastc = Float.parseFloat(dsr.sname);
               sr.ratio = sr.lastc * Double.parseDouble(dsr.capital);
//               totalR[idx] = Double.parseDouble()*Double.parseDouble(dsr.capital);   
               totalM += sr.ratio;
 //              System.out.println(dsr.dump()+" --> totalM:"+totalM);
               _g100snums.put(sr.snum,sr);
           }
           e = _g100snums.keys();
           while(e.hasMoreElements())
           {
               String skey = (String)e.nextElement();
               SRec sr =_g100snums.get(skey);;
                sr.ratio =  (sr.ratio / totalM);
  //             System.out.println( sr.snum+"("+sr.lastc+") --> "+  sr.ratio);
           }
        }catch(Exception xxe)
        {
           xxe.printStackTrace();
        }
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
   void makeTX_200_pattern_Statisic(DomainValue dv, int xtime_i)
   {
       if(xtime_i >= 91500)
       {
          KexLine kx_tx_084 = (KexLine)datapool_m.get("TX094");  
          KexLine kx_200 = (KexLine)datapool_m.get("200");  
          FTTSta_Pattern p_200 = new FTTSta_Pattern("");
          FTTPriceSta_Pattern p_tx = new FTTPriceSta_Pattern("");
          Line l_200 = kx_200.sub(kx_200.length()-15,kx_200.length()-1);
          Line l_084 = kx_tx_084.sub(kx_tx_084.length()-15,kx_tx_084.length()-1);
          Line pl_200 = l_200.pattern(p_200);
          Line pl_084 = l_084.pattern(p_tx);
          PatternValue ptv_200 = (PatternValue)pl_200.valueAt(2);
          PatternValue ptv_084 = (PatternValue)pl_084.valueAt(2);
          Vector<NRec> pn_200 = p_200.getNameValue(ptv_200);
          Vector<NRec> pn_084 = p_tx.getNameValue(ptv_084);
          Enumeration<NRec> e =  pn_200.elements();
          double p_up = 0;
          double p_down = 0;
          while(e.hasMoreElements())
          {
             NRec nr = e.nextElement();
             if(FConverter.getFvofName(nr) != 0)
             {
         //      System.out.println(nr + "-->"+FConverter.getFvofName(nr));
               PRec[] pv = FConverter.getPvofNR(nr,"sta_raw_pg", "dif_15");
//               FConverter.dumpPRecs(pv);
               for(int xi=0; xi<pv.length; xi++)
               {
                   if(pv[xi].ncase == 1)
                   {
                      p_up += pv[xi].pvalue;
                   }else if(pv[xi].ncase == -1)
                   {
                      p_down += pv[xi].pvalue;
                   }
               }
             }
          }
          e =  pn_084.elements();
          while(e.hasMoreElements())
          {
             NRec nr = e.nextElement();
             if(FConverter.getFvofName(nr) != 0)
             {
  //             System.out.println(nr + "-->"+FConverter.getFvofName(nr));
               PRec[] pv = FConverter.getPvofNR(nr,"sta_raw_pg", "dif_15");
 //              FConverter.dumpPRecs(pv);
               for(int xi=0; xi<pv.length; xi++)
               {
                   if(pv[xi].ncase == 1)
                   {
                      p_up += pv[xi].pvalue;
                   }else if(pv[xi].ncase == -1)
                   {
                      p_down += pv[xi].pvalue;
                   }
               }
             }
           }
          pn_200 = p_200.getNameValue2(ptv_200);
          pn_084 = p_tx.getNameValue2(ptv_084);
          e =  pn_200.elements();
          while(e.hasMoreElements())
          {
             NRec nr = e.nextElement();
             if((int)(nr.value)!= 13)
             {
            //   System.out.println(nr);
               PRec[] pv = FConverter.getPvofNR_NonConvert(nr,"sta_raw_pg", "dif_15");
             //  FConverter.dumpPRecs(pv);
               for(int xi=0; xi<pv.length; xi++)
               {
                   if(pv[xi].ncase == 1)
                   {
                      p_up += pv[xi].pvalue;
                   }else if(pv[xi].ncase == -1)
                   {
                      p_down += pv[xi].pvalue;
                   }
               }
             }
          }
          e =  pn_084.elements();
          while(e.hasMoreElements())
          {
             NRec nr = e.nextElement();
             if((int)(nr.value)!= 13)
             {
        //       System.out.println(nr);
               PRec[] pv = FConverter.getPvofNR_NonConvert(nr,"sta_raw_pg", "dif_15");
        //       FConverter.dumpPRecs(pv);
               for(int xi=0; xi<pv.length; xi++)
               {
                   if(pv[xi].ncase == 1)
                   {
                      p_up += pv[xi].pvalue;
                   }else if(pv[xi].ncase == -1)
                   {
                      p_down += pv[xi].pvalue;
                   }
               }
             }
          }

//          System.out.println("p_up-->"+p_up+" p_down-->"+p_down);
          p_up = p_up * 100;
          KexLine kx_tx_200_up_sta = (KexLine)datapool_m.get("TX_200_Up_Sta");
          if(kx_tx_200_up_sta == null)
          {
             kx_tx_200_up_sta = new KexLine();
             datapool_m.put("TX_200_Up_Sta",kx_tx_200_up_sta);
          }
	        kx_tx_200_up_sta.add(new KexValue("TX_200_Up_Sta", Integer.parseInt(_startdate), xtime_i/100 * 100, p_up, p_up, p_up, p_up, 0 , 0));
          p_down = p_down * 100;
          KexLine kx_tx_200_down_sta = (KexLine)datapool_m.get("TX_200_Down_Sta");
          if(kx_tx_200_down_sta == null)
          {
             kx_tx_200_down_sta = new KexLine();
             datapool_m.put("TX_200_Down_Sta",kx_tx_200_down_sta);
          }
	        kx_tx_200_down_sta.add(new KexValue("TX_200_Down_Sta", Integer.parseInt(_startdate), xtime_i/100 * 100, p_down, p_down, p_down, p_down, 0 , 0));
          int endIdx = kx_tx_200_down_sta.length()-1;
          if(endIdx >= 7)
          {
             double mav3_d   = kx_tx_200_down_sta.sub(endIdx-3,endIdx).getAvg();
             double mav8_d   = kx_tx_200_down_sta.sub(endIdx-7,endIdx).getAvg();
             endIdx = kx_tx_200_up_sta.length()-1;
             double mav3_u   = kx_tx_200_up_sta.sub(endIdx-3,endIdx).getAvg();
             double mav8_u   = kx_tx_200_up_sta.sub(endIdx-7,endIdx).getAvg();
             if(mav3_u - mav8_u > 0 && mav3_u > 40)
             {
                 System.out.println("To Buy..."+ (xtime_i/100 * 100));
                 KexLine kx_ma_self = (KexLine)datapool_m.get("MA_ALL");
                 KexLine kx_mad_self = (KexLine)datapool_m.get("MA_ALL_D");
                 if(kx_ma_self != null && kx_mad_self != null)
                 {
                     Value up_v = kx_ma_self.valueAt(kx_ma_self.length()-1);
                     Value down_v = kx_mad_self.valueAt(kx_mad_self.length()-1);
                     System.out.print("up v :");
                     up_v.dump();
                     System.out.print("down v :");
                     down_v.dump();
                 }
                 kx_tx_084.valueAt(kx_tx_084.length()-1).dump();
             }
          }
      }
//       System.out.println("kx_tx_084 : "+kx_tx_084.length()+" -- "+ dv + " "+xtime_i);
//       System.out.println("kx_200 : "+kx_200.length()+" -- "+ dv + " "+xtime_i);
   }
   
   void statisicStock(DomainValue dv, int xtime_i)
   {
 //      makeIndexBySelf(dv,xtime_i);
  //      makeMaStatisic(dv,xtime_i);
  //      makeTX_200_pattern_Statisic(dv,xtime_i);
   }
   
   public    FTT_sFactory()
   {
        _snum = "1476";
        datapool_m = new Hashtable();
        wdataBound = new Hashtable();
        _startdate = GMethod.d2s(new Date());
        loadSnumRatio();
        realTime = true;
    }
    
    public    FTT_sFactory(String snum,String stardate) throws Exception
    {
        _snum = snum;
        datapool_m = new Hashtable();
        wdataBound = new Hashtable();
        _startdate = stardate;
        loadSnumRatio();
    	  loadKData(snum, stardate);
    	  realTime = false;
    }
    
    void setReadData(byte[] databuf)
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
     	       String data= (new String(databuf,12,500)).trim();
     	       PLDayk_Rec plk = parseWDKField(code, _startdate, data);
      	     KexLine kl_ex = (KexLine)datapool_m.get(code);
      	     if(kl_ex == null)
      	     {
      	        kl_ex = new KexLine();
      	        datapool_m.put(code,kl_ex);
      	     }
      	      setWDKField(kl_ex,plk);
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
               if(tx_last_close < 0)
               {
                  tx_last_close = Float.parseFloat(pr.s_lclose);
                  Enumeration e = _g100snums.keys();
                  while(e.hasMoreElements())
                  {
                       String skey = (String)e.nextElement();
                       SRec sr =_g100snums.get(skey);;
                       sr.ratio =  sr.ratio * tx_last_close;
//                       System.out.println( sr.snum+"("+sr.lastc+") --> "+  sr.ratio);
                  }
         //         System.out.println("tx_last_close:"+tx_last_close+"  "+selfIndex(kxv.getDomainValue(),true));
               }
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
               if(pr.snum.equals("TX"))
               {
  //                kxv.dump();
                  statisicStock(kxv.getDomainValue(), xtime_i);
               }
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
      	 KexLine kl_ex = (KexLine)datapool_m.get(code);
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
