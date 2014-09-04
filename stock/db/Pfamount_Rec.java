package stock.db;

public class Pfamount_Rec extends DbtableRec{
  public final int pk_fcode = 8;
  public final int pk_date  = 8 ;
  public final int pk_time  = 8 ;

  public final int ln_fcode   = 8;
  public final int ln_date   = 8;
  public final int ln_time   = 8;
  public final int ln_contact   = 8;
  public final int ln_price  = 8;
  public final int ln_count     = 10;
  public final int ln_ra     = 10;

  public String fcode     = "";
  public String date    = "";
  public String time    = "";
  public String contact    = "";
  public String price  = "";
  public String count     = "";
  public String ra      = "";
}
