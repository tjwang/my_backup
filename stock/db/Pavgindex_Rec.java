package stock.db;

public class Pavgindex_Rec extends DbtableRec{
  public final int pk_date  = 8 ;

  public final int ln_date   = 8;
  public final int ln_avgIndexP   = 8;
  public final int ln_avgIndexC   = 10;
  public final int ln_Amount  = 10;
  public final int ln_Product     = 10;
  public final int ln_Credits   = 10;

  public String date     = "";
  public String avgIndexP= "";
  public String avgIndexC= "";
  public String Amount   = "";
  public String Product  = "";
  public String Credits  = "";
}
