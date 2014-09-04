package stock.db;

public class PLast_Rec extends DbtableRec{
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

  public String snum    = "";
  public String date    = "";
  public String time    = "";
  public String pbuy    = "";
  public String psell   = "";
  public String rp      = "";
  public String diff    = "";
  public String ra      = "";
  public String total   = "";
}
