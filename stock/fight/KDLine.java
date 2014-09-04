package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class KDLine extends AbstractLine{

   Vector data ;
   static final public int Ktype = 1;
   static final public int Dtype = 2;
     
   KDLine(){
       data = new Vector();
   }

   void add(KDValue kdv){
      data.add(kdv);
   }

   public Line sub(int startIdx,int endIdx)
   {
      KDLine subKdLine = new KDLine();
      for(int i = startIdx; i<=endIdx ; i++)
      {
         subKdLine.add((KDValue)valueAt(i));
      }
      return subKdLine;
   }
   
   public Line diff(Line l)
   {
       return null;
   }
   
//   public LineFactory genLineFactory(int type)
//   {
//      KDataLineFactory klf= new KDataLineFactory();
//      Enumeration e =   data.elements();
//      KDValue kdv = null;
//   	  if(type == Ktype)
//   	  {
//         while(e.hasMoreElements()){
//            kdv = (KDValue)e.nextElement();
//            klf.add(kdv.getName(),kdv.getDateValue(),kdv.getTimeValue(),kdv.getKValue());
//         }
//   	  }
//   	  if(type == Dtype)
//   	  {
//          while(e.hasMoreElements()){
//            kdv = (KDValue)e.nextElement();
//            klf.add(kdv.getName(),kdv.getDateValue(),kdv.getTimeValue(),kdv.getDValue());
//         }
//   	  }
//    	return klf;
//   }
   
   public Enumeration elements(){
      return  data.elements();
   }
   
   public int length(){
     return data.size();
   }

   public Value valueAt(int index){
     return (Value)data.elementAt(index);
   }

   public Line getKLine()
   {
       Enumeration<KDValue> e = data.elements();
       SimpleLine kvalueline = new SimpleLine(); 
       while(e.hasMoreElements())
       {
           KDValue kdv = e.nextElement();
           kvalueline.add(new SimpleValue(kdv.getDateValue(), kdv.getTimeValue(), kdv.getKValue()));
       }
       return kvalueline;
   }
   
   public Line getDLine()
   {
       Enumeration<KDValue> e = data.elements();
       SimpleLine kvalueline = new SimpleLine(); 
       while(e.hasMoreElements())
       {
           KDValue kdv = e.nextElement();
           kvalueline.add(new SimpleValue(kdv.getDateValue(), kdv.getTimeValue(), kdv.getDValue()));
       }
       return kvalueline;
 
   }
 
}