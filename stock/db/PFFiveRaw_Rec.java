package stock.db;
import java.util.*;
import stock.tool.*;

public class PFFiveRaw_Rec extends DbtableRec{
  public final int pk_date = 8;
  public final int pk_snum = 8;
  public final int ln_date = 8;
  public final int ln_snum = 8;
  public final int ln_s_open = 10;
  public final int ln_s_buy  = 10;
  public final int ln_s_sell = 10;
  public final int ln_s_high = 10;
  public final int ln_s_low  = 10;
  public final int ln_s_amp = 10;
  public final int ln_s_rp = 10;
  public final int ln_s_diff = 10;
  public final int ln_s_ra = 10;
  public final int ln_s_try_rp = 10;
  public final int ln_s_try_ra = 10;
  public final int ln_s_tra = 10;
  public final int ln_s_uncommit = 10;
  public final int ln_s_lclose = 10;
  public final int ln_s_buy_tcount = 10;
  public final int ln_s_buy_tra = 10;
  public final int ln_s_sell_tcount = 10;
  public final int ln_s_sell_tra = 10;
  public final int ln_time    = 10;
  public final int ln_b1rp = 10;
  public final int ln_b1ra = 10;
  public final int ln_b2rp = 10;
  public final int ln_b2ra = 10;
  public final int ln_b3rp = 10;
  public final int ln_b3ra = 10;
  public final int ln_b4rp = 10;
  public final int ln_b4ra = 10;
  public final int ln_b5rp = 10;
  public final int ln_b5ra = 10;
  public final int ln_bNrp = 10;
  public final int ln_bNra = 10;
  public final int ln_s1rp = 10;
  public final int ln_s1ra = 10;
  public final int ln_s2rp = 10;
  public final int ln_s2ra = 10;
  public final int ln_s3rp = 10;
  public final int ln_s3ra = 10;
  public final int ln_s4rp = 10;
  public final int ln_s4ra = 10;
  public final int ln_s5rp = 10;
  public final int ln_s5ra = 10;
  public final int ln_sNrp    = 10;
  public final int ln_sNra    = 10;

  int idx_buf = 0;
  byte[] buf = null;
  public String date    = "";
  public String snum    = "";
  public  String s_open = "";
  public  String s_buy  = "";
  public  String s_sell = "";
  public  String s_high = "";
  public  String s_low  = "";
  public  String s_amp = "";
  public  String s_rp = "";
  public  String s_diff = "";
  public  String s_ra = "";
  public  String s_try_rp = "";
  public  String s_try_ra = "";
  public  String s_tra = "";
  public  String s_uncommit = "";
  public  String s_lclose = "";
  public  String s_buy_tcount = "";
  public  String s_buy_tra = "";
  public  String s_sell_tcount = "";
  public  String s_sell_tra = "";
  public  String time    = "";
  public  String b1rp    = "";
  public String b1ra    = "";
  public String b2rp    = "";
  public String b2ra    = "";
  public String b3rp    = "";
  public String b3ra    = "";
  public String b4rp    = "";
  public String b4ra    = "";
  public String b5rp    = "";
  public String b5ra    = "";
  public String bNrp    = "";
  public String bNra    = "";
  public String s1rp    = "";
  public String s1ra    = "";
  public String s2rp    = "";
  public String s2ra    = "";
  public String s3rp    = "";
  public String s3ra    = "";
  public String s4rp    = "";
  public String s4ra    = "";
  public String s5rp    = "";
  public String s5ra    = "";
  public String sNrp    = "";
  public String sNra    = "";

  public PFFiveRaw_Rec()
  {
  
  }
  
  String nextToken()
  {
     int start_idx = idx_buf;
     while(buf[idx_buf]!='|' && idx_buf < buf.length)
     {
        idx_buf++;
     }
     
     if(idx_buf - start_idx > 0)
     {
        String s = new String(buf,start_idx,idx_buf - start_idx);
     	  if(buf[idx_buf]=='|') idx_buf++;
     	  return s;
     } else
     {
     	  if(buf[idx_buf]=='|' && idx_buf < buf.length) idx_buf++;
        return "";
     }
  }
  
  public PFFiveRaw_Rec(String sn,String ddf,byte[] data,int offset)
  {
     buf = data;
     idx_buf = offset;
  	 snum = sn;
  	 date = ddf;
     s_open = nextToken();
     s_buy  = nextToken();
     s_sell = nextToken();
     s_high = nextToken();
     s_low  = nextToken();
     s_amp = nextToken();
     s_rp = nextToken();
     s_diff = nextToken();
     s_ra = nextToken();
     s_try_rp = nextToken();
     s_try_ra = nextToken();
     s_tra = nextToken();
     s_uncommit = nextToken();
     s_lclose = nextToken();
     s_buy_tcount = nextToken();
     s_buy_tra = nextToken();
     s_sell_tcount = nextToken();
     s_sell_tra = nextToken();
     time    = nextToken();
     b1rp    = nextToken();
     b1ra    = nextToken();
     b2rp    = nextToken();
     b2ra    = nextToken();
     b3rp    = nextToken();
     b3ra    = nextToken();
     b4rp    = nextToken();
     b4ra    = nextToken();
     b5rp    = nextToken();
     b5ra    = nextToken();
     s1rp    = nextToken();
     s1ra    = nextToken();
     s2rp    = nextToken();
     s2ra    = nextToken();
     s3rp    = nextToken();
     s3ra    = nextToken();
     s4rp    = nextToken();
     s4ra    = nextToken();
     s5rp    = nextToken();
     s5ra    = nextToken();
     bNrp    = nextToken();
     bNra    = nextToken();
     sNrp    = nextToken();
     sNra    = nextToken();
   }
  
  public PFFiveRaw_Rec(String sn,String ddf,String data)
  {
     StringTokenizer st = new StringTokenizer(data,"|");
  	 snum = sn;
  	 date = ddf;
     s_open = st.nextToken();
     s_buy  = st.nextToken();
     s_sell = st.nextToken();
     s_high = st.nextToken();
     s_low  = st.nextToken();
     s_amp = st.nextToken();
     s_rp = st.nextToken();
     s_diff = st.nextToken();
     s_ra = st.nextToken();
     s_try_rp = st.nextToken();
     s_try_ra = st.nextToken();
     s_tra = st.nextToken();
     s_uncommit = st.nextToken();
     s_lclose = st.nextToken();
     s_buy_tcount = st.nextToken();
     s_buy_tra = st.nextToken();
     s_sell_tcount = st.nextToken();
     s_sell_tra = st.nextToken();
     time    = st.nextToken();
     b1rp    = st.nextToken();
     b1ra    = st.nextToken();
     b2rp    = st.nextToken();
     b2ra    = st.nextToken();
     b3rp    = st.nextToken();
     b3ra    = st.nextToken();
     b4rp    = st.nextToken();
     b4ra    = st.nextToken();
     b5rp    = st.nextToken();
     b5ra    = st.nextToken();
     s1rp    = st.nextToken();
     s1ra    = st.nextToken();
     s2rp    = st.nextToken();
     s2ra    = st.nextToken();
     s3rp    = st.nextToken();
     s3ra    = st.nextToken();
     s4rp    = st.nextToken();
     s4ra    = st.nextToken();
     s5rp    = st.nextToken();
     s5ra    = st.nextToken();
     bNrp    = st.nextToken();
     bNra    = st.nextToken();
     sNrp    = st.nextToken();
     sNra    = st.nextToken();
  }
  
  public int countAllBuy()
  {
      return  Integer.parseInt(b1ra)+Integer.parseInt(b2ra)+Integer.parseInt(b3ra)+Integer.parseInt(b4ra)+Integer.parseInt(b5ra);
  }	
  	
  public int countAllSell()
  {
      return  Integer.parseInt(s1ra)+Integer.parseInt(s2ra)+Integer.parseInt(s3ra)+Integer.parseInt(s4ra)+Integer.parseInt(s5ra);
  }	
  public int count1Buy()
  {
      return  Integer.parseInt(b1ra);
  }	
  	
  public int count1Sell()
  {
      return  Integer.parseInt(s1ra);
  }	
  public int count3Buy()
  {
      return  Integer.parseInt(b1ra)+Integer.parseInt(b2ra)+Integer.parseInt(b3ra);
  }	
  	
  public int count3Sell()
  {
      return  Integer.parseInt(s1ra)+Integer.parseInt(s2ra)+Integer.parseInt(s3ra);
  }	

  public float countAllBuyM()
  {
      return  Float.parseFloat(b1rp)*Integer.parseInt(b1ra)+Float.parseFloat(b2rp)*Integer.parseInt(b2ra)+
      Float.parseFloat(b3rp)*Integer.parseInt(b3ra)+Float.parseFloat(b4rp)*Integer.parseInt(b4ra)+Float.parseFloat(b5rp)*Integer.parseInt(b5ra);
  }	
  	
  public float countAllSellM()
  {
      return  Float.parseFloat(s1rp)*Integer.parseInt(s1ra)+Float.parseFloat(s2rp)*Integer.parseInt(s2ra)+
      Float.parseFloat(s3rp)*Integer.parseInt(s3ra)+Float.parseFloat(s4rp)*Integer.parseInt(s4ra)+Float.parseFloat(s5rp)*Integer.parseInt(s5ra);
  }	
  public float count1BuyM()
  {
      return  Float.parseFloat(b1rp)*Integer.parseInt(b1ra);
  }	
  	
  public float count1SellM()
  {
      return  Float.parseFloat(s1rp)*Integer.parseInt(s1ra);
  }	
  public float count3BuyM()
  {
      return  Float.parseFloat(b1rp)*Integer.parseInt(b1ra)+Float.parseFloat(b2rp)*Integer.parseInt(b2ra)+Float.parseFloat(b3rp)*Integer.parseInt(b3ra);
  }	
  	
  public float count3SellM()
  {
      return  Float.parseFloat(s1rp)*Integer.parseInt(s1ra)+Float.parseFloat(s2rp)*Integer.parseInt(s2ra)+Float.parseFloat(s3rp)*Integer.parseInt(s3ra);
  }	

  public Date getDateTime()
  {
     return GMethod.s2d(date ,time);
  }
  
  public void println()
  {
     System.out.println("date           = "+date         );
     System.out.println("snum           = "+snum         );
     System.out.println("s_open         = "+s_open       );
     System.out.println("s_buy          = "+s_buy        );
     System.out.println("s_sell         = "+s_sell       );
     System.out.println("s_high         = "+s_high       );
     System.out.println("s_low          = "+s_low        );
     System.out.println("s_amp          = "+s_amp        );
     System.out.println("s_rp           = "+s_rp         );
     System.out.println("s_diff         = "+s_diff       );
     System.out.println("s_ra           = "+s_ra         );
     System.out.println("s_try_rp       = "+s_try_rp     );
     System.out.println("s_try_ra       = "+s_try_ra     );
     System.out.println("s_tra          = "+s_tra        );
     System.out.println("s_uncommit     = "+s_uncommit   );
     System.out.println("s_lclose       = "+s_lclose     );
     System.out.println("s_buy_tcount   = "+s_buy_tcount );
     System.out.println("s_buy_tra      = "+s_buy_tra    );
     System.out.println("s_sell_tcount  = "+s_sell_tcount);
     System.out.println("s_sell_tra     = "+s_sell_tra   );
     System.out.println("time           = "+time         );
     System.out.println("b1rp           = "+b1rp         );
     System.out.println("b1ra           = "+b1ra         );
     System.out.println("b2rp           = "+b2rp         );
     System.out.println("b2ra           = "+b2ra         );
     System.out.println("b3rp           = "+b3rp         );
     System.out.println("b3ra           = "+b3ra         );
     System.out.println("b4rp           = "+b4rp         );
     System.out.println("b4ra           = "+b4ra         );
     System.out.println("b5rp           = "+b5rp         );
     System.out.println("b5ra           = "+b5ra         );
     System.out.println("bNrp           = "+bNrp         );
     System.out.println("bNra           = "+bNra         );
     System.out.println("s1rp           = "+s1rp         );
     System.out.println("s1ra           = "+s1ra         );
     System.out.println("s2rp           = "+s2rp         );
     System.out.println("s2ra           = "+s2ra         );
     System.out.println("s3rp           = "+s3rp         );
     System.out.println("s3ra           = "+s3ra         );
     System.out.println("s4rp           = "+s4rp         );
     System.out.println("s4ra           = "+s4ra         );
     System.out.println("s5rp           = "+s5rp         );
     System.out.println("s5ra           = "+s5ra         );
     System.out.println("sNrp           = "+sNrp         );
     System.out.println("sNra           = "+sNra         );
  
  }
}
