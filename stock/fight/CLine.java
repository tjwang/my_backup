package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class CLine extends AbstractLine{

   Vector data ;
   Line Bline ;
     
   CLine(Line bl){
       data = new Vector();
       Bline = bl;
   }
     
   void add(CValue cdv){
      data.add(cdv);
   }

   
   public Line sub(int startIdx,int endIdx)
   {
       CValue cvs = (CValue)this.valueAt(startIdx);
       CValue cve = (CValue)this.valueAt(endIdx);
       Line subBline = Bline.sub(cvs.getMIdx(), cve.getMIdx());
       CLine subcLine = new CLine(subBline);
       for(int i = startIdx; i <= endIdx ; i++)
       {
          CValue cv = (CValue)this.valueAt(i);
          if(cv != null)
          {  
              subcLine.add(new CValue(cv.getDomainValue(), cv._sv1, cv._fv1, cv._sv2, cv._fv2, cv._mIdx-cvs.getMIdx()));
          }
       }
       return subcLine;
   }

   public Line diff(Line l)
   {
      return diff(l,0);
   }
   
//   public LineFactory genLineFactory(int type)
//   {
//       KDataLineFactory klf= new KDataLineFactory();
//       if(type == 0) //AbstractLineFactory.CXTYPE
//       {
//           Enumeration e =   LinearExtends();
//           while(e.hasMoreElements()){
//              klf.add((KValue)e.nextElement());
//           }
//       }else
//       {
//           Enumeration e =   data.elements();
//           while(e.hasMoreElements()){
//              CValue cv = (CValue)e.nextElement();
//              klf.add(cv.toKValue());
//           }
//       }
//       return klf;
//   }
   
   public Enumeration elements(){
      return  data.elements();
   }
   
   public Line LinearExtends()
   {
       SimpleLine sline = new SimpleLine(); 
       for(int i = 1; i < length(); i ++)
       {
           CValue v1 = (CValue)valueAt(i-1);
           CValue v2 = (CValue)valueAt(i);
           int intervals = v2.getMIdx() - v1.getMIdx();
           double dif = (v2.getValue() - v1.getValue())/intervals;
           double base = v1.getValue();
           for(int j = 0; j < intervals; j++)
           {
               Value _cv = Bline.valueAt(j+v1.getMIdx());
               sline.add(new SimpleValue(_cv.getDomainValue(),base));
               base += dif;
           }
       }
       return sline;
   }
   
   public int length(){
     return data.size();
   }
   
   public Value valueAt(int index){
     return (Value)data.elementAt(index);
   }
    
 
}