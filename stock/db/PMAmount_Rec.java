package stock.db;

public class PMAmount_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date = 8;

  public final int ln_snum       = 8;
  public final int ln_date       = 8;
  public final int ln_mnum       = 10;
  public final int ln_inAmount   = 10;
  public final int ln_outAmount   = 10;
  public final int ln_sum       = 10;

  public String snum         = "";
  public String date      = "";
  public String mnum     = "";
  public String inAmount  = "";
  public String outAmount= "";
  public String sum      = "";
}
