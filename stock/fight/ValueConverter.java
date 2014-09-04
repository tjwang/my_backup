package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public interface ValueConverter {
  public double getConvertValue(Value v);
}