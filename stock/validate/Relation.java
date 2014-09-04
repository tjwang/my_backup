package stock.validate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;
import stock.pattern.*;

public interface Relation {
  
  public boolean  test(PatternValue srcPtv, Line dstl, Pattern dstPattern, Range r);
  
}     