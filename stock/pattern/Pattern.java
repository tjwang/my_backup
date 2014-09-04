package stock.pattern;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.fight.*;

public interface Pattern {
    public PatternValue find(Line l, int startIdx);
    public double culValue(PatternValue ptv);
    public void dump(PatternValue ptv);
}