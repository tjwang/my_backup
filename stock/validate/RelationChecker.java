package stock.validate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;
import stock.pattern.*;

public class RelationChecker {
  Pattern srcPattern ;
  Pattern dstPattern ;
  Range r;
  Relation rel; 
  double passRate;
  
  public void setPassRate(double pRate)
  {
     passRate = pRate;
  }
  
  public void setSrcPattern(Pattern ptn)
  {
     srcPattern = ptn;
  }
  
  public void setDstPattern(Pattern ptn)
  {
     dstPattern = ptn;
  }
  
  public void setRelation(Relation rl)
  {
     rel = rl;
  }
  
  public void setRange(Range range)
  {
    r = range;
  }
  
  public double checkValue(Line srcLine,Line dstLine)
  {
      PatternLine srcpl = srcLine.pattern(srcPattern);
      int testOk = 0;
      for(int i = 0; i < srcpl.length(); i++)
      {
          PatternValue srcptv = (PatternValue)srcpl.valueAt(i);
          if(rel.test(srcptv, dstLine, dstPattern, r)==true)
          {
             testOk++;
          }
      }
//      System.out.print("HitRate: "+((float)testOk/srcpl.length()));
      System.out.print(" try: "+srcpl.length()+ " testOk:"+testOk);
      
      return ((float)testOk/srcpl.length()) ;
  }

  public boolean check(Line srcLine,Line dstLine)
  {
      if(checkValue(srcLine, dstLine) > passRate)
         return true;
      else
         return false;  
  }

}      
