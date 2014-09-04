package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public interface Value {
  public int getDateValue();
  public int getTimeValue();
  public long getDateTime();
  public double getValue();
  public DomainValue getDomainValue();// throws Exception;
  public Domain getDomain();
  public String getName();
  public void dump();
}