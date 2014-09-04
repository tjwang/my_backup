package stock.db;

public class Pidxrp_offical_Rec extends DbtableRec{
  public final int pk_date  = 8 ;

  public final int ln_date     = 8;
  public final int ln_time     = 8;
  public final int ln_BuyCnt = 6;
  public final int ln_BuyAmt   = 10;
  public final int ln_SellCnt  = 10;
  public final int ln_SellAmt  = 10;
  public final int ln_RpCnt   = 10;
  public final int ln_RpAmt      = 10;
  public final int ln_RpMoney    = 10;

  public String date    = "";
  public String time    = "";
  public String BuyCnt     = "";
  public String BuyAmt   = "";
  public String SellCnt  = "";
  public String SellAmt  = "";
  public String RpCnt    = "";
  public String RpAmt    = "";
  public String RpMoney  = "";
}
