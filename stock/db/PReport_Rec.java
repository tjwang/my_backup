package stock.db;
import java.util.*;
import stock.tool.*;

public class PReport_Rec extends DbtableRec{
  public final int pk_date = 8;
  public final int pk_name = 8;
  public final int ln_date = 8;
  public final int ln_name = 8;
  public final int ln_time = 10;
  public final int ln_bf1 = 10;
  public final int ln_sf1 = 10;
  public final int ln_bf2 = 10;
  public final int ln_sf2 = 10;
  public final int ln_r1 = 10;
  public final int ln_r2 = 10;
  public String date    = "";
  public String name    = "";
  public String time    = "";
  public String bf1    = "";
  public String sf1    = "";
  public String bf2    = "";
  public String sf2    = "";
  public String r1    = "";
  public String r2    = "";

  public PReport_Rec()
  {
  
  }
  
  public Date getDateTime()
  {
     return GMethod.s2d(date ,time);
  }
  
}
