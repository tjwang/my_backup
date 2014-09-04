package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public interface LineFactory
{
    public KLine  culKLine();
    public MALine culMALine(int s);
    public KDLine culKDLine(int k, int sk, int sd);
    public SimpleLine culSimpleLine();
    public Line genTimeLine(int seconds);
    public Line genDomainLine(int samples);
    public Line genDomainLineByCount(int count);

}      