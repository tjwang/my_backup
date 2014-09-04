package stock.db;

public class PLast2_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date  = 8 ;

  public final int ln_snum   = 8;
  public final int ln_date   = 8;
  public final int ln_time   = 8;
  public final int ln_pbuy   = 10;
  public final int ln_psell  = 10;
  public final int ln_rp     = 10;
  public final int ln_diff   = 10;
  public final int ln_ra     = 10;
  public final int ln_total  = 10;
  public final int ln_pclose = 10;
  public final int ln_open   = 10;
  public final int ln_high   = 10;
  public final int ln_low    = 10;

  public String snum      = "";    
  public String date      = "";
  public String time      = "";
  public String pbuy      = "";
  public String psell     = "";
  public String rp        = "";
  public String diff      = "";
  public String ra        = "";
  public String total     = "";
  public String pclose    = "";
  public String open      = "";
  public String high      = "";
  public String low       = "";
}
