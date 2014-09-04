package stock.db;

public class PAmountWeek2_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date = 8;

  public final int ln_snum     = 8;
  public final int ln_date     = 8;
  public final int ln_ramount  = 10;
  public final int ln_tamount  = 10;
  public final int ln_mamount   = 10;
  public final int ln_average = 10;
  public final int ln_startp   = 10;
  public final int ln_endp     = 10;

  public String snum    ="";
  public String date    ="";
  public String ramount ="";
  public String tamount ="";
  public String mamount ="";
  public String average ="";
  public String startp  ="";
  public String endp    ="";
}
