package stock.validate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;

public class FixedRange implements Range{
  int minBound;
  int maxBound;
  int dstPatternLen;
  
  public FixedRange(int minB, int maxB, int dstPLen)
  {
     minBound = minB;
     maxBound = maxB;
     dstPatternLen = dstPLen;
  }
  
  public Line  range(PatternValue srcptv,Line dstl)
  {
       Value v = srcptv.getEndValue();
       int checkIdx = dstl.mappingIdx(v);
       if( checkIdx+maxBound+dstPatternLen > dstl.length())
       {
//           System.err.println("dstl.sub("+(checkIdx+minBound)+", "+(checkIdx+maxBound+srcptv.length() - 1)+"));");
           return dstl.sub(checkIdx+minBound, dstl.length() - 1);
       }
//       System.err.println("dstl.sub("+(checkIdx+minBound)+", "+(checkIdx+maxBound+srcptv.length() - 1)+"));");
       return dstl.sub(checkIdx+minBound, checkIdx+maxBound+dstPatternLen - 1);
 }
}     