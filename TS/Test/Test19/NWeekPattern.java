import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;
import stock.pattern.*;

public class NWeekPattern extends WeekPattern {

    static double my_count = 0;
    public double culValue(PatternValue ptv)
    {
          my_count ++;
          return my_count;
    }
    
}