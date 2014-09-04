package stock.db;

public class Level_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date  = 8 ;

  public final int ln_snum   = 8;
  public final int ln_date   = 8;
  public final int ln_samples     = 10;
  public final int ln_measure     = 10;
  public final int ln__error       = 10;
  public final int ln_level       = 10;

  public String snum           = ""; 
  public String date           = "";
  public String samples        = "";
  public String measure        = "";
  public String _error         = "";
  public String level          = "";
  }
