package stock.db;

public class PLDaykSnum_Rec extends DbtableRec{
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
  public final int ln_volC     = 10;
  public final int ln_diff     = 10;
                      
  public String snum    = "";
  public String date    = "";
  public String time    = "";
  public String open    = "";
  public String high    = "";
  public String low     = "";
  public String close   = "";
  public String volume  = "";
  public String money   = "";
  public String volC   = "";
  public String diff   = "";
}
