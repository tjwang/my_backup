package stock.validate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;
import stock.pattern.*;

public class MappingRelation implements Relation {
  
  public MappingRelation()
  {
  }
  
  public boolean  test(PatternValue srcPtv, Line dstl, Pattern dstPattern, Range r)
  {
      Line subLine = r.range(srcPtv, dstl);
      PatternValue dstPtv = dstPattern.find(subLine, 0);
      if(dstPtv != null)
      {
  //       srcPtv.dump();
  //       dstPtv.dump();
         return true;
      }
      return false;   
  }
  
}     