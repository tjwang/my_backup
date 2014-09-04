package stock.db;

public class Pamountinfo_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date  = 8 ;

  public final int ln_snum             = 8;
  public final int ln_date             = 8;
  public final int ln_open             = 8;
  public final int ln_close             = 10;
  public final int ln_rate              = 10;
  public final int ln_Avg             = 10;
  public final int ln_Open_upAvg        = 10;
  public final int ln_Open_downAvg        = 10;
  public final int ln_Close_upAvg      = 10;
  public final int ln_Close_downAvg        = 10;
  public final int ln_Open_upAmt        = 10;
  public final int ln_Open_downAmt      = 10;
  public final int ln_Close_upAmt     = 10;
  public final int ln_Close_downAmt   = 10;
  public final int ln_hand_rate      = 10;
                      
  public String snum            = "";
  public String date            = "";
  public String open            = "";
  public String close           = "";
  public String rate            = "";
  public String Avg             = "";
  public String Open_upAvg    = "";
  public String Open_downAvg    = "";
  public String Close_upAvg     = "";
  public String Close_downAvg   = "";
  public String Open_upAmt    = "";
  public String Open_downAmt    = "";
  public String Close_upAmt     = "";
  public String Close_downAmt   = "";
  public String hand_rate       = "";
}  
