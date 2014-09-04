package stock.db;

public class PAmount_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date  = 8 ;

  public final int ln_snum   = 8;
  public final int ln_date   = 8;
  public final int ln_close   = 8;
  public final int ln_diff   = 10;
  public final int ln_ra    = 10;
  public final int ln_pbuy  = 10;
  public final int ln_abuy  = 10;
  public final int ln_psell     = 10;
  public final int ln_asell   = 10;
  public final int ln_open     = 10;
  public final int ln_high     = 10;
  public final int ln_low   = 10;
  public final int ln_lclose  = 10;
                      
  public String snum     = "";
  public String date     = "";
  public String close    = "";
  public String diff     = "";
  public String ra       = "";
  public String pbuy     = "";
  public String abuy     = "";
  public String psell    = "";
  public String asell    = "";
  public String open     = "";
  public String high     = "";
  public String low      = "";
  public String lclose   = "";
}  
