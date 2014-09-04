package stock.db;

public class Psnuminoutsum_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date  = 8 ;

  public final int ln_date     = 10;     
  public final int ln_type     = 10;     
  public final int ln_snum     = 10;     
  public final int ln_sname    = 10;     
  public final int ln_in       = 10;     
  public final int ln_out      = 10;     
  public final int ln_total    = 10;     

  public String date  = "";
  public String type  = "";
  public String snum  = "";
  public String sname = "";
  public String in    = "";
  public String out    = "";
  public String total = "";
  }
