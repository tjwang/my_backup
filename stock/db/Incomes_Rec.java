package stock.db;


public class Incomes_Rec extends DbtableRec{
  public final int pk_year = 4;
  public final int pk_m = 2;
  public final int pk_snum = 8;

  public final int ln_year   = 4;
  public final int ln_m   = 2;
  public final int ln_snum   = 8;
  public final int ln_CurMon   = 20;
  public final int ln_LastMon   = 20;
  public final int ln_LastYear   = 20;

  public String year    = "";
  public String m   = "";
  public String snum   = "";
  public String CurMon    = "";
  public String LastMon   = "";
  public String LastYear   = "";
}
