package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class PatternLine extends AbstractLine {
 
   Vector patterndata ;
  
  PatternLine()
  {
      patterndata = new Vector();
  }  

   void add(PatternValue ptv){
       patterndata.add(ptv);
   }

   public Line sub(int startIdx,int endIdx)
   {
       PatternLine subpLine = new PatternLine();
       for(int i = startIdx; i <= endIdx ; i++)
       {
          PatternValue ptv = (PatternValue)this.valueAt(i);
          subpLine.add(ptv);
       }
       return subpLine;
   }

//   public LineFactory genLineFactory(int type)
//   {
//       KDataLineFactory klf= new KDataLineFactory();
//       Enumeration e = patterndata.elements();
//       while(e.hasMoreElements())
//       {
//           PatternValue ptv = (PatternValue)e.nextElement();
//           klf.add(new KValue("ptn", ptv.getDateValue(),  ptv.getTimeValue(), ptv.getValue()));
//       }
//       return klf;
//   }
   
   public Enumeration elements()
   {
      return  patterndata.elements();
   }
   
   public int length()
   {
     return patterndata.size();
   }
   
   public Value valueAt(int index)
   {
     return (Value)patterndata.elementAt(index);
   }

}
