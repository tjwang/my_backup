package stock.db;


public class Wldcode_Rec extends DbtableRec{
  public final int pk_wcode = 20;

  public final int ln_wcode   = 20;
  public final int ln_name   = 20;
  public final int ln_type   = 1;

  public String wcode    = "";
  public String name   = "";
  public String type    = "";
}
