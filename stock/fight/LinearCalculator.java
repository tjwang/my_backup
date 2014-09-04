package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import Jama.*;

public class LinearCalculator implements GroupCalculator {
  double[] covs = null;
  public LinearCalculator()
  {
  }
  
  public double cul(GroupValue gv)
  {
     if(covs == null) return 0;
     double rv = covs[0];
     Enumeration<Value> e = gv.elements();
     for(int j = 1; j < covs.length; j++)
     {
     	   Value v = e.nextElement(); 
         rv += covs[j] * v.getValue();
     }
     return rv;

  }
  
  public void init(Line ml, Line[] ls)
  {
   	   int n = ml.length();
   	   int m = ls.length+1;
   	   double[][] mData = new double[m][m];
   	   double[][] yData = new double[m][1];
   	   Line[] xLines = new Line[m];

   	   
   	   xLines[0] = ml.constant(1);
   	   for(int i=1; i < m; i++)
   	   {
   	     xLines[i]=ls[i-1];
   	   }
   	   
   	   for(int i = 0; i < m; i++)
   	   {  
 	   	   Line l = ml.mul(xLines[i]);
 	   	   yData[i][0] = l.summation().valueAt(n-1).getValue();
   	   	 for(int j=0; j < m; j++)
   	   	 {
   	   	 	  mData[i][j] = xLines[i].mul(xLines[j]).summation().valueAt(n-1).getValue();
   	   	 }
   	   }
   	   
       Matrix xM = new Matrix(mData);
       Matrix yM = new Matrix(yData);       
       Matrix sM = xM.solve(yM);
     //  sM.print(10,10);
       covs = sM.getColumnPackedCopy();
  }

}