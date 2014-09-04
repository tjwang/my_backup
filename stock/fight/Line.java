package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.pattern.*;

public  interface Line {
   public Enumeration elements();
   public int length();
   public Value valueAt(int index);
   public Value valueAt(DomainValue dv)  ;//throws Exception;
   public Value valueAfter(DomainValue dv)  ;//throws Exception;
   
   public Line sub(int startIdx,int endIndex);
   public Line sub(DomainValue startV,DomainValue endV);
//   public void setMapper(Mapper mpr);
//   public Mapper getMapper();
   
//   public Line Sub(int startIdx,int endIndex);
   
   public Line diff(Line l);
   public Line div(Line l);
   public Line mul(Line l);
   public Line add(Line l);
   
   public Line diff(Line l, int shift);
   public Line div(Line l, int shift);
   public Line mul(Line l, int shift);
   public Line add(Line l, int shift);
   public Line constant(double v);
   public Line convert(ValueConverter vc);
   
   public Line summation();

   public Line diff(int n);
   public Line abs();
   public Line sign();
   public Line complete();
   public Line pos();
   public Line neg();
   public Line and(Line l);
   public Line or(Line l);
   public Line avg();
   public Line avg(int s);
   public Line avg(Line sl);
   public Line rsv(int k);
   public Line percent(double upbound, double lowbound);
    public Line macd(int range, int ratio);
    public Line macd(double initV, int range, int ratio);
   public Domain getDomain();

   public Line inverse() ;
   public Line inverse(double fg, double lg) ;    
   public Line transTo(Line l2);
   public Line cross(Line l, int shift);
   public ClusterLine cluster(Line l);
   public Line expands(Line l);
   public PatternLine pattern(Pattern ptn);
   public PatternLine patternByStep(Pattern ptn);
   public Line shadow();

//   public CurveLine curve(int period, double lowBound) throws Exception;

   public Line normal(double avg, double var);
   public Line[] dft(double ratio);
   
   public Line idft(Line cosL, Line sinL,  int N);

   public double getAvg();
   public double getVar();
   public double getMax();
   public Value getMaxValue();
   public double getMin();
   public Value getMinValue();

   public double statistics();
   public double statistics(double threshold);
   
   public void dump();
   public int mappingIdx(Value v);
}
