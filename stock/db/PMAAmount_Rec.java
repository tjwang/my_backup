package stock.db;

public class PMAAmount_Rec extends DbtableRec{
  public final int pk_snum = 8;
  public final int pk_date  = 8 ;
  public final int pk_mnum    = 4;

  public final int ln_date    = 8;
  public final int ln_snum    = 8;
  public final int ln_sname   = 18;
  public final int ln_mnum    = 4;
  public final int ln_mname   = 20;
  public final int ln_amount  = 10;
  public final int ln_amountP = 10;
  public final int ln_avgp    = 10;
  public final int ln_nowp    = 10;


  public String date    = "";
  public String snum    = "";
  public String sname   = "";
  public String mnum    = "";
  public String mname   = "";
  public String amount  = "";
  public String amountP = "";
  public String avgp    = "";
  public String nowp    = "";

}
