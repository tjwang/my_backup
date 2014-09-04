package stock.db;
import java.util.*;
import stock.tool.*;

public class PFiveRaw_Rec extends DbtableRec{
  public final int pk_date = 8;
  public final int pk_snum = 8;
  public final int ln_date = 8;
  public final int ln_snum = 8;
  public final int ln_diff = 10;
  public final int ln_time = 10;
  public final int ln_rp   = 10;
  public final int ln_ra   = 10;
  public final int ln_ra2  = 10;
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
  public String date    = "";
  public String snum    = "";
  public String diff    = "";
  public String time    = "";
  public String rp      = "";
  public String ra      = "";
  public String ra2     = "";
  public String b1rp    = "";
  public String b1ra    = "";
  public String b2rp    = "";
  public String b2ra    = "";
  public String b3rp    = "";
  public String b3ra    = "";
  public String b4rp    = "";
  public String b4ra    = "";
  public String b5rp    = "";
  public String b5ra    = "";
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

  public PFiveRaw_Rec()
  {
  
  }
  
  public PFiveRaw_Rec(String data)
  {
     StringTokenizer st = new StringTokenizer(data,"|");
     date = st.nextToken();
     snum = st.nextToken();
     diff    = st.nextToken();
     time    = st.nextToken();
     rp      = st.nextToken();
     ra      = st.nextToken();
     ra2     = st.nextToken();
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
  
}
