package stock.validate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;

public interface Range {
  public Line  range(PatternValue srcptv,Line dstl);
}     