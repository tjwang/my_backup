package stock.db;
import java.util.*;
import stock.tool.*;

public class PLDayk_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date  = 8 ;

  public final int ln_snum    = 8;
  public final int ln_date    = 8;
  public final int ln_time  = 6;
  public final int ln_open    = 10;
  public final int ln_high    = 10;
  public final int ln_low    = 10;
  public final int ln_close     = 10;
  public final int ln_volume    = 10;
  public final int ln_money     = 10;
                      
  public String snum    = "";
  public String date    = "";
  public String time    = "";
  public String open    = "";
  public String high    = "";
  public String low     = "";
  public String close   = "";
  public String volume  = "";
  public String money   = "";
  
  public PLDayk_Rec()
  {
  }
  public PLDayk_Rec(String data)
  {
     StringTokenizer st = new StringTokenizer(data,"|");
     snum = st.nextToken();
     date = st.nextToken();
     time = st.nextToken();
     open = st.nextToken();
     high = st.nextToken();
     low = st.nextToken();
     close = st.nextToken();
     volume = st.nextToken();
     money = st.nextToken();
  }
}
