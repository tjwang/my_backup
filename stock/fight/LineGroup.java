package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import Jama.*;

public class LineGroup extends AbstractLine{
	 Line  mainLine;
	 Vector linePool;
	 GroupCalculator gvc;
    
   public  LineGroup(Line l)
   {
       mainLine = l;
       linePool = new Vector();
       gvc = new LinearCalculator();
   }
   
   public  LineGroup(Line l, GroupCalculator gvc1)
   {
       mainLine = l;
       linePool = new Vector();
       gvc = gvc1;
   }
    
   public void setCalculator(GroupCalculator gvc1)
   {
       gvc = gvc1;
   }

   public Line[][] diff()
   {
	   Line[] la = getSubLineArray();
       Line[][] result = new Line[la.length][la.length];
	   
	   for(int i=0;i<result.length;i++)
	   	for(int j=0;j<result.length;j++)
	    {
            result[i][j]=la[i].diff(la[j]);
		}
	   return result;	
   }

   public void setupLine()
   {
	  Enumeration e = linePool.elements();
      int min_len = 999999999;
	  while(e.hasMoreElements())
	  {
		 Line l = (Line)e.nextElement();
         if(l.length() < min_len)
		 	 min_len = l.length();
	  }
	  Vector NlinePool = new Vector();
	  e = linePool.elements();
 	  while(e.hasMoreElements())
	  {
		 Line l = (Line)e.nextElement();
		 NlinePool.add(l.sub(l.length()-min_len,l.length()-1));
	  }
	  linePool = NlinePool;

   }
   
   public void addLine(Line l)//, Mapper mpr)
   {
   //    if(mpr!=null)
  ////     {
  //        l.setMapper(mpr);
  //     }
       linePool.add(l);
   }

   public Enumeration elements()
   {
      Vector v = new Vector();
      for(int i = 0; i < length(); i++)
      {
         v.add(valueAt(i));
      }
      return v.elements();
   }
   
   public int length()
   {
       return mainLine.length();
   }
   
   public Value valueAt(int index)
   {
       Value mValue = mainLine.valueAt(index);
       GroupValue  gv = new GroupValue(mValue,gvc);
       Enumeration e = linePool.elements();
       while(e.hasMoreElements())
       {
           Line l = (Line)e.nextElement();
//           Value v = l.valueAt(mValue.getDomainValue());
           if(l.length() > index)
           {
              Value v = l.valueAt(index);
              if( v!=null )
              {
                 gv.addValue(v);
              }
           }
       }
       return gv;
   }
   
   public Line sub(int startIdx,int endIndex)
   {
       Line mainSubLine = mainLine.sub(startIdx, endIndex);
       Value SValue = mainLine.valueAt(startIdx);
       Value EValue = mainLine.valueAt(endIndex);
       Enumeration e = linePool.elements();
       LineGroup  sublg = new LineGroup(mainSubLine,gvc);
       while(e.hasMoreElements())
       {
          Line l = (Line)e.nextElement();
      //    Value sv = l.valueAt(SValue.getDomainValue());
      //    Value ev = l.valueAt(EValue.getDomainValue());
     //     int subStartIdx = l.mappingIdx(sv);
     //     int subEndIdx = l.mappingIdx(ev);
      //    Line subl = l.sub(subStartIdx, subEndIdx);
          Line subl = l.sub(startIdx, endIndex);
//          sublg.addLine(subl, l.getMapper());
          sublg.addLine(subl);
       }
       return sublg;
   }
/*   
   public void culMSLParameter(int i)
   {
   	   int n = mainLine.length();
   	   Line xl = (Line)linePool.elementAt(i);
   	   Line smx = xl.summation();
   	   Line smy = mainLine.summation();
   	   Line ml = xl.mul(mainLine);
   	   Line xxl = xl.mul(xl);
   	   Line smxx = xxl.summation();
   	   Line smml = ml.summation();
   	   double sumX = smx.valueAt(n-1).getValue();
   	   double sumY = smy.valueAt(n-1).getValue();
       double sumXY = smml.valueAt(n-1).getValue();
       double sumXX = smxx.valueAt(n-1).getValue();
       double b1 = (n * sumXY - sumX * sumY)/(n * sumXX - sumX * sumX);
       double a = (sumY  - b1 * sumX) /n;
       
       System.out.println("Y = "+a+" + " + b1+"X");
       
       double[] b = new double[2];
       b[0] = a;
       b[1] = b1;
       Line[] ls = new Line[1];
       ls[0] = xl;
       Line cl = genConvLine(b,ls);
       Line yvarl = mainLine.diff(mainLine.constant(sumY/n));
       Line ycll = mainLine.diff(cl);
       double yavgVar = yvarl.mul(yvarl).summation().valueAt(n-1).getValue()/n;
       double ycovVar = ycll.mul(ycll).summation().valueAt(n-1).getValue()/n;
       System.out.println("yavgVar:"+yavgVar+"  ycovVar:"+ycovVar+"   cov:"+(1-ycovVar/yavgVar));
   }
   
   public void culMSLParameter(int i, int j)
   {
   	   int n = mainLine.length();
   	   Line yl = mainLine;
   	   Line xl1 = (Line)linePool.elementAt(i);
   	   Line xl2 = (Line)linePool.elementAt(j);
   	   double avgX1 = xl1.summation().valueAt(n-1).getValue()/n;
   	   double avgX2 = xl2.summation().valueAt(n-1).getValue()/n;
       double avgY = yl.summation().valueAt(n-1).getValue()/n;
   	   Line dl1 = xl1.diff(xl1.constant(avgX1));
   	   Line dl2 = xl2.diff(xl2.constant(avgX2));
   	   
       double d1yS = dl1.mul(yl).summation().valueAt(n-1).getValue();
       double d1d1S = dl1.mul(dl1).summation().valueAt(n-1).getValue();
       double d2yS = dl2.mul(yl).summation().valueAt(n-1).getValue(); 
       double d1d2S = dl1.mul(dl2).summation().valueAt(n-1).getValue();
       double d2d2S = dl2.mul(dl2).summation().valueAt(n-1).getValue();
       
       double b1 = (d1yS * d2d2S - d2yS * d1d2S)/(d1d1S * d2d2S - d1d2S * d1d2S);
       double b2 = (d2yS * d1d1S - d1yS * d1d2S)/(d1d1S * d2d2S - d1d2S * d1d2S);
       double a = (avgY  - b1 * avgX1 - b2 * avgX2);
       
       System.out.println("Y = "+a+" + " + b1+"X1"+" + " + b2+"X2"  );
       double[] b = new double[3];
       b[0] = a;
       b[1] = b1;
       b[2] = b2;
       Line[] ls = new Line[2];
       ls[0] = xl1;
       ls[1] = xl2;
       Line cl = genConvLine(b,ls);
       Line yvarl = yl.diff(yl.constant(avgY));
       Line ycll = yl.diff(cl);
       double yavgVar = yvarl.mul(yvarl).summation().valueAt(n-1).getValue()/n;
       double ycovVar = ycll.mul(ycll).summation().valueAt(n-1).getValue()/n;
       System.out.println("yavgVar:"+yavgVar+"  ycovVar:"+ycovVar+"   cov:"+(1-ycovVar/yavgVar));
   }

*/
   public Line[]  getSubLineArray()
   {
  	   int m = linePool.size();
   	   Line[] xLines = new Line[m];
   	   for(int i=0; i < m; i++)
   	   {
    	     xLines[i]=(Line)linePool.elementAt(i);
   	   }
       return  xLines; 
   }
   
   public Line[] getSubLineArray(int sIdx, int eIdx)
   {
  	   int m = linePool.size();
   	   Line[] xLines = new Line[m];
   	   for(int i=0; i < m; i++)
   	   {
    	     xLines[i]=((Line)linePool.elementAt(i)).sub(sIdx,eIdx);
   	   }
       return  xLines; 
   }
   public void initCalculator(int sIdx, int n)
   {
   	   if(sIdx+n > mainLine.length())
   	   {
   	      n = mainLine.length()-sIdx;
   	   }
       gvc.init(mainLine.sub(sIdx,sIdx+n-1), getSubLineArray(sIdx,sIdx+n-1));
       Line cl = genConvLine();
   }
   
   public void culMSLParameter()
   {
   	   int n = mainLine.length();
   	   int m = linePool.size()+1;
   	   double[][] mData = new double[m][m];
   	   double[][] yData = new double[m][1];
   	   Line[] xLines = new Line[m];
   	   xLines[0] = mainLine.constant(1);
	   System.out.println("n:"+n+" m:"+m);
   	   for(int i=1; i < m; i++)
   	   {
   	     xLines[i]=(Line)linePool.elementAt(i-1);
   	   }
   	   
   	   for(int i = 0; i < m; i++)
   	   {  
 	   	   Line l = mainLine.mul(xLines[i]);
 	   	   yData[i][0] = l.summation().valueAt(n-1).getValue();
   	   	 for(int j=0; j < m; j++)
   	   	 {
   	   	 	  mData[i][j] = xLines[i].mul(xLines[j]).summation().valueAt(n-1).getValue();
   	   	 }
   	   }
       Matrix xM = new Matrix(mData);
       Matrix yM = new Matrix(yData);       
       Matrix sM = xM.solve(yM);
       sM.print(10,10);
       double[] b = sM.getColumnPackedCopy();
 //      Line[] ls = new Line[m-1];
 //      for(int i = 0; i < m-1; i++)
 //      {
 //        ls[i] = xLines[i+1];
 //      }
 //      Line cl = genConvLine(b,ls);
       Line cl = genConvLine(b);
       Line yvarl = mainLine.diff(mainLine.constant(mainLine.summation().valueAt(n-1).getValue()/n));
       Line ycll = mainLine.diff(cl);
       Line ymcl = mainLine.mul(cl);
      // mainLine.dump();
      // cl.dump();
      // ymcl.dump();
       double yavgVar = yvarl.mul(yvarl).summation().valueAt(n-1).getValue()/n;
       double ycovVar = ycll.mul(ycll).summation().valueAt(n-1).getValue()/n;
       System.out.println("yavgVar:"+yavgVar+"  ycovVar:"+ycovVar+"   cov:"+(1-ycovVar/yavgVar));

   }
   
   public Line genConvLine(double[] b)
   {
   	    int n = mainLine.length();
        SimpleLine result = new SimpleLine(); 
        Line[] ls = getSubLineArray();
        for(int i = 0; i < length(); i ++)
        {
             Value v1 = mainLine.valueAt(i);
             double rv = b[0];
             for(int j = 0; j < ls.length; j++)
             {
                rv += b[j+1] * ls[j].valueAt(i).getValue();
             }
             result.add(new SimpleValue(v1.getDomainValue(),rv));
         }
         return result;
   }
   
   public Line genConvLine()
   {
   	    int n = mainLine.length();
        SimpleLine result = new SimpleLine(); 
        for(int i = 0; i < length(); i ++)
        {
             GroupValue v1 = (GroupValue)valueAt(i);
             result.add(new SimpleValue(v1.getDomainValue(),v1.getValue()));
        }
        return result;
   }

   public Line getMainLine()
   {
       return mainLine;
   }
  

}
