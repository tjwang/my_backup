package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class KLine extends AbstractLine{

   Vector data ;
     
   protected KLine(){
       data = new Vector();
   }
     
   protected void add(KValue kdv){
      data.add(kdv);
   }

   
   public Line sub(int startIdx,int endIdx)
   {
      KLine subKLine = new KLine();
      for(int i = startIdx; i<=endIdx ; i++)
      {
         subKLine.add((KValue)valueAt(i));
      }
      return subKLine;
   }
   
   public Line sub(DomainValue startV,DomainValue endV)
   {
      KLine subSimpleLine = new KLine();
      Value v = valueAfter(startV);
	  if(v==null) return subSimpleLine;
      int idx = mappingIdx(v);
      long endScale = endV.getScale();
	  int linelen = length();
      while(v.getDomainValue().getScale() <= endScale)
      {
         subSimpleLine.add((KValue)v);
         idx++;
		 if(idx >= linelen) break;
         v = valueAt(idx);
      }
      return subSimpleLine;
      
   }
   public Line getVol()
   {
	   SimpleLine result = new SimpleLine(); 
	   for(int i = 0; i < length(); i ++)
	   {
			KValue v1 = (KValue)valueAt(i);
			result.add(new SimpleValue(v1.getDomainValue(),v1.getVolume()));
	   }
	   return result;
          
   }
   public Line getUp()
   {
	   SimpleLine result = new SimpleLine(); 
	   for(int i = 0; i < length(); i ++)
	   {
			KValue v1 = (KValue)valueAt(i);
			result.add(new SimpleValue(v1.getDomainValue(),v1.getUp()));
	   }
	   return result;
          
   }
   public Line getLow()
   {
	   SimpleLine result = new SimpleLine(); 
	   for(int i = 0; i < length(); i ++)
	   {
			KValue v1 = (KValue)valueAt(i);
			result.add(new SimpleValue(v1.getDomainValue(),v1.getLow()));
	   }
	   return result;
          
   }
   public Line getDown()
   {
	   SimpleLine result = new SimpleLine(); 
	   for(int i = 0; i < length(); i ++)
	   {
			KValue v1 = (KValue)valueAt(i);
			result.add(new SimpleValue(v1.getDomainValue(),v1.getDown()));
	   }
	   return result;
          
   }
   public Line getHigh()
   {
	   SimpleLine result = new SimpleLine(); 
	   for(int i = 0; i < length(); i ++)
	   {
			KValue v1 = (KValue)valueAt(i);
			result.add(new SimpleValue(v1.getDomainValue(),v1.getHigh()));
	   }
	   return result;
          
   }
   public Line getOpen()
   {
	   SimpleLine result = new SimpleLine(); 
	   for(int i = 0; i < length(); i ++)
	   {
			KValue v1 = (KValue)valueAt(i);
			result.add(new SimpleValue(v1.getDomainValue(),v1.getOpen()));
	   }
	   return result;
          
   }
   public Line getClose()
   {
	   SimpleLine result = new SimpleLine(); 
	   for(int i = 0; i < length(); i ++)
	   {
			KValue v1 = (KValue)valueAt(i);
			result.add(new SimpleValue(v1.getDomainValue(),v1.getClose()));
	   }
	   return result;
          
   }
   public Line getMoney()
   {
	   SimpleLine result = new SimpleLine(); 
	   for(int i = 0; i < length(); i ++)
	   {
			KValue v1 = (KValue)valueAt(i);
			result.add(new SimpleValue(v1.getDomainValue(),v1.getMoney()));
	   }
	   return result;
          
   }
   public Line getReal()
   {
	   SimpleLine result = new SimpleLine(); 
	   for(int i = 0; i < length(); i ++)
	   {
			KValue v1 = (KValue)valueAt(i);
			result.add(new SimpleValue(v1.getDomainValue(),v1.getReal()));
	   }
	   return result;
          
   }
   public Line diff(Line l)
   {
      KLine kl = (KLine)l;
      Enumeration e =   data.elements();
      Enumeration e2 =  kl.elements();
      KValue kv = null;
      KValue kv2 = null;
      KLine difLine = new KLine(); 
      long kvDate = 0;
      long kvDate2 = 0;
      while(e.hasMoreElements()&&e2.hasMoreElements()){
         if((kv == null) || (kvDate <= kvDate2)) {
            kv = (KValue)e.nextElement();
         }
         if((kv2 == null) || (kvDate >= kvDate2)) {
            kv2 = (KValue)e2.nextElement();
         }
         kvDate = kv.getDateTime();   
         kvDate2 = kv2.getDateTime();   
         if(kvDate == kvDate2)
         {
             difLine.add(new KValue(kv.getName(),kv.getDateValue(),kv.getTimeValue(),
                              kv.getOpen()-kv2.getOpen(),
                              kv.getHigh()-kv2.getHigh(),
                              kv.getLow()-kv2.getLow(),
                              kv.getClose()-kv2.getClose(),
                              kv.getVolume()-kv2.getVolume(),
                              kv.getMoney()-kv2.getMoney()));
         } 
      }
      return difLine;
   }
   
   public Enumeration elements(){
      return  data.elements();
   }
   
   public int length(){
     return data.size();
   }
   public Value valueAt(int index){
     return (Value)data.elementAt(index);
   }
    
   public  ClusterLine clusterByValue(int interv) 
   {
        ClusterLine  clul = new ClusterLine();
        Line il =  inverse(0,1,1);
        Line il2 =  inverse(0,1,0);
        SimpleLineFactory slf = new SimpleLineFactory(il);
        Line dl = slf.genDomainLineByCount(interv);
        Enumeration<Value> e = dl.elements();

        int i = 0;
        int j = 0;
        double inter0 = 0;
        double inter1 = 0;
        while(e.hasMoreElements())
        {
            Value v = e.nextElement();
            TreeSet datas = new TreeSet(new KValueComparator(1));
            inter0 = inter1;
            inter1 = v.getDomainValue().getDoubleValue();
            int si = i;
            int sj = j;
   //         System.out.println("inter " +  inter0 +" - "+inter1);
            while(si < il.length())
            {
               KValue v2 = (KValue)((ImageValue)il.valueAt(si)).getImage();
               if(v2.getHigh() < inter0 || v2.getLow()> inter1)
               {
                  if(si == i ) 
                  {
                  	 i++;
                  } else
                  {
                     break;
                  }
               }else
               {
                  datas.add(v2);
               }
               si++;
            }
            while(sj < il2.length())
            {
               KValue v2 = (KValue)((ImageValue)il2.valueAt(sj)).getImage();
               if(v2.getHigh() < inter0 || v2.getLow()> inter1)
               {
                  if(sj == j ) 
                  {
                  	 j++;
                  } else
                  {
                     break;
                  }
               }else
               {
                  datas.add(v2);
               }
               sj++;
            }
            if(datas.size() > 0)
            {
                Iterator<KValue> ir = datas.iterator();
                Vector datav = new Vector();
                while(ir.hasNext()) datav.add(ir.next());
                ClusterValue cv = new ClusterValue(datav.elements(), v.getDomainValue());
              //  cv.dump();
                clul.add(cv);
            }
       }
        return clul;
    }

    public Line inverse(double fg, double lg, int lh) //throws Exception
    {
        Domain d = Domain.getDomainByLine(this);
        if(d == null)
        {
            return null;
        }
        SimpleLine result = new SimpleLine(); 
        Enumeration<KValue> e = elements();
        TreeSet vts = new TreeSet(new KValueComparator(lh));
        int n = length();
        int startin = (int)(n * fg);
        int endin = (int)(n * lg);
        
        while(e.hasMoreElements())
        {
            vts.add(e.nextElement());
        }
        Iterator<KValue> i = vts.iterator();
        
        {
           int idx = 0;
           while(i.hasNext())
           {
           	   KValue v = i.next();
           	   if(idx > startin && idx < endin)
           	   {
                  DomainValue dv = null;
                  if(lh == 1)
                  {
                     dv = new DomainValue(d, v.getHigh()); 
                  } else
                  {
                     dv = new DomainValue(d, v.getLow()); 
                  }
                  result.add(new ImageValue(dv, v.getDomainValue().getDoubleValue(), v));  
               }
               idx ++;
           }   
        }
        return result;
    }

    private class KValueComparator implements Comparator<KValue>
    {
       int T ;
       KValueComparator(int t)
       {
           T = t;
       }
       public int compare(KValue v1, KValue v2) 
       {
          if(v1.getDateTime() == v2.getDateTime()) return 0;
          if(T == 1)
          {
             if(v1.getHigh() < v2.getHigh()) return -1;
             return 1;
          } else 
          {
             if(v1.getLow() < v2.getLow()) return -1;
             return 1;
          }
       }
       
       public boolean equals(Object obj) 
       {
          return super.equals(obj);
       }
    }
 
}
