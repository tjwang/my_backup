package stock.db;

public class PCPPrice_Rec extends DbtableRec{
  public final int pk_cp_month = 8;
  public final int pk_type  = 8 ;
  public final int pk_date  = 8 ;

  public final int ln_cp_month   = 10;
  public final int ln_type       = 10;
  public final int ln_date       = 10;
  public final int ln_price       = 10;
  public final int ln_rp         = 10;
  public final int ln_buy         = 10;
  public final int ln_sell       = 10;
  public final int ln_diff       = 10;
  public final int ln_ra         = 10;

  public String cp_month    = "";
  public String type        = "";
  public String date        = "";
  public String price       = "";
  public String rp          = "";
  public String buy          = "";
  public String sell        = "";
  public String diff         = "";
  public String ra          = "";
  }
