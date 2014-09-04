import stock.db.*;
import stock.app.*;
import stock.fight.*;
import stock.tool.*;
import stock.sandy.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ClassCuler{

    double feature_max;
    double feature_min;
    String _name;
    ClassCuler(String SQLStr,double threshold, int level_count,String name)throws Exception
    { 
       OneFieldBySQL  of1 = new OneFieldBySQL(SQLStr);
       Line l = of1.culF1Line();
//       l.dump();
       /*
       CulClass_Pattern cp = new CulClass_Pattern(level_count,(float)l.getMax(),(float)l.getMin());
       PatternLine pl = l.pattern(cp);
       feature_max = getFeatureValue(pl,threshold);
       feature_min = getFeatureValue(pl,1-threshold);
       */
       feature_max = l.valueAt((int)(l.length()*threshold)+1).getValue();
       feature_min = l.valueAt((int)(l.length()*(1-threshold)-1)).getValue();
      _name = name;
    }
    public String toString()
    {
        return (_name+"|"+String.format("%1$.2f",feature_max)+"|"+String.format("%1$.2f",feature_min)+"|");
    }    

    public double getFeatureValue(PatternLine pl,double threshold)
    {
        PatternValue v = null;
        for(int i = 0; i<pl.length(); i++)
        {
           v = (PatternValue)pl.valueAt(i);
           if(v.getValue() >= threshold)
           {
               break;
           }
        }
        return v.getStartValue().getValue();
    }

    
}
