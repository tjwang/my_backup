package stock.db;

public class PMAmountMM_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date = 8;
  public final int pk_mnum = 8;

  public final int ln_snum       = 8;
  public final int ln_date       = 8;
  public final int ln_mnum       = 10;
  public final int ln_amount   = 10;
  public final int ln_money       = 10;

  public String snum         = "";
  public String date      = "";
  public String mnum     = "";
  public String amount  = "";
  public String money    = "";
}
