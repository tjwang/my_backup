package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public interface GroupCalculator {
  public double cul(GroupValue gv);
  public void init(Line ml, Line[] ls);
}