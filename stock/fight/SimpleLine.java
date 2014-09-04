package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class SimpleLine extends AbstractLine implements Line {
	
//	 Mapper mapper;
   protected Vector<Value> dataPool = null;

   public SimpleLine()
   {
      dataPool = new Vector<Value>();
   }
 
   public Enumeration elements()
   {
       return dataPool.elements();
   }
   
   public int length()
   {
       return dataPool.size();
   }
   
   public Value valueAt(int index)
   {
        return dataPool.elementAt(index);
   }
   
   public Line sub(int startIdx,int endIndex)
   {
      SimpleLine subSimpleLine = new SimpleLine();
      for(int i = startIdx; i<=endIndex ; i++)
      {
         subSimpleLine.add(valueAt(i));
      }
      return subSimpleLine;
   }
   
   public void add(Value sv)
   {
       dataPool.add(sv);
   }      
   
}