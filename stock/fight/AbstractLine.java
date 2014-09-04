package stock.fight;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.pattern.*;

public  abstract class AbstractLine implements Line {
	
//	 Mapper mapper;

//   abstract public LineFactory genLineFactory(int type);
   abstract public Enumeration elements();
   abstract public int length();
   abstract public Value valueAt(int index);
   abstract public Line sub(int startIdx,int endIndex);
   private int last_find_idx;
   	
   public Value valueAt(DomainValue dv)  //throws Exception
   {
      Domain d = dv.getDomain();
      
      if(d != getDomain())
      {
          return null;
      }
	  if(last_find_idx < length())
	  {
         Value lv = valueAt(last_find_idx);
	     if(lv == null || lv.getDomainValue().getScale() > dv.getScale())
	     {
		     last_find_idx = 0;
	     }
	  } else
	  {
          last_find_idx = 0;
	  }
      if(d.isCountable())
      {
		 for(int i = last_find_idx; i < length(); i ++)
         {
              Value v1 = valueAt(i);
              if(dv.getIntValue() == v1.getDomainValue().getIntValue())
              {
                  last_find_idx = i;
				  return v1;
              }
         } 
      }else{
         for(int i = last_find_idx; i < length(); i ++)
         {
              Value v1 = valueAt(i);
              if(dv.nears(v1.getDomainValue()))
              {
                  last_find_idx = i;
                  return v1;
              }
         } 
      
      }
      return null;
   }

   public Value valueAfter(DomainValue dv)  
   {
      if(dv == null)
      {
           return valueAt(0);
	  }
	  Domain d = dv.getDomain();
      
      if(d != getDomain())
      {
          return null;
      }
      
      for(int i = 0; i < length(); i ++)
      {
           Value v1 = valueAt(i);
           if(dv.getScale() <= v1.getDomainValue().getScale())
           {
               return v1;
           }
      } 
      return null;
  
   }
   
   public Line sub(DomainValue startV,DomainValue endV)
   {
      SimpleLine subSimpleLine = new SimpleLine();
      Value v = valueAfter(startV);
      int idx = mappingIdx(v);
      long endScale = endV.getScale();
      while(v.getDomainValue().getScale() <= endScale)
      {
         subSimpleLine.add(v);
         idx++;
         v = valueAt(idx);
      }
      return subSimpleLine;
      
   }
//   public void setMapper(Mapper mpr)
//   {
//       mapper = mpr;
//   }
   
//   public Mapper getMapper()
//   {
//       return mapper;
//   }

//   public Line Sub(int startIdx,int endIndex)
//   {
//   	   Line l = this.sub(startIdx, endIndex);
// //  	   l.setMapper(mapper);
//       return l;
//   }

   public Line diff(Line l)
   {
       return diff(l, 0);
   }
   
   public Line diff(int n)
   {
      SimpleLine result = new SimpleLine(); 
      for(int i = 0; i < length(); i ++)
      {
           if(i-n >=0 && i-n < length())
           {
		      Value v1 = valueAt(i-n);
              Value v2 = valueAt(i);
              result.add(new SimpleValue(v2.getDomainValue(),v2.getValue()-v1.getValue()));
           } else
           {
              Value v2 = valueAt(i);
              result.add(new SimpleValue(v2.getDomainValue(),v2.getValue()));
		   }
	  }
       return result;
   }

   public Line diff(Line l, int shift)
   {
      SimpleLine result = new SimpleLine(); 
      for(int i = 0; i < length(); i ++)
      {
           if(i-shift >= 0 && i-shift < l.length())
           {
		      Value v1 = valueAt(i);
              Value v2 = l.valueAt(i-shift);
              result.add(new SimpleValue(v1.getDomainValue(),v1.getValue()-v2.getValue()));
		   } else
		   {
              Value v1 = valueAt(i);
              result.add(new SimpleValue(v1.getDomainValue(),v1.getValue()));
		   }
      }
       return result;
   }
   
   public Line div(Line l)
   {
       return div(l, 0);
   }
   
   public Line div(Line l, int shift)
   {
      SimpleLine result = new SimpleLine(); 
      for(int i = shift; i < length(); i ++)
      {
           Value v1 = valueAt(i);
           Value v2 = l.valueAt(i-shift);
           result.add(new SimpleValue(v1.getDomainValue(),v1.getValue()/v2.getValue()));
       }
       return result;
   }

   public Line mul(Line l)
   {
       return mul(l, 0);
   }
   
   public Line mul(Line l, int shift)
   {
      SimpleLine result = new SimpleLine(); 
      for(int i = shift; i < length(); i ++)
      {
           Value v1 = valueAt(i);
           Value v2 = l.valueAt(i-shift);
           result.add(new SimpleValue(v1.getDomainValue(),v1.getValue()*v2.getValue()));
       }
       return result;
   }
   
   public Line normal(double avg, double var)
   {
      SimpleLine result = new SimpleLine(); 
      double concov = Math.pow(2 * Math.PI* var,0.5) ;
      for(int i = 0; i < length(); i ++)
      {
           Value v1 = valueAt(i);
           DomainValue dv = v1.getDomainValue();
           double dvd =dv.getDoubleValue();
           double nv = Math.pow(Math.E,-0.5 * (dvd -avg)* (dvd -avg) / var) / concov ;
           nv = nv * 100;
           if(i > 0)
           {
               Value v2 = valueAt(i-1);
               nv = (dvd - v2.getDomainValue().getDoubleValue())*nv;
           }
           System.out.println("normal:"+concov+ " " + nv);
           result.add(new SimpleValue(dv,nv));
       }
       return result;
   }

   public Line add(Line l)
   {
       return add(l, 0);
   }
   
   public Line add(Line l, int shift)
   {
      SimpleLine result = new SimpleLine(); 
      for(int i = 0; i < length(); i ++)
      {
          if(i-shift < l.length() && i-shift >= 0 )
          {
		     Value v1 = valueAt(i);
             Value v2 = l.valueAt(i-shift);
             result.add(new SimpleValue(v1.getDomainValue(),v1.getValue()+v2.getValue()));
		  } else 
		  {
              Value v1 = valueAt(i);
  	          result.add(new SimpleValue(v1.getDomainValue(),v1.getValue()));
		  }
      }
       return result;
   }

  public Line convert(ValueConverter vc)
  {
	 SimpleLine result = new SimpleLine(); 
	 for(int i = 0; i < length(); i ++)
	 {
		  Value v1 = valueAt(i);
		  result.add(new SimpleValue(v1.getDomainValue(),vc.getConvertValue(v1)));
	  }
	  return result;
  }
  
    public Line constant(double v)
   {
      SimpleLine result = new SimpleLine(); 
      for(int i = 0; i < length(); i ++)
      {
           Value v1 = valueAt(i);
           result.add(new SimpleValue(v1.getDomainValue(),v));
       }
       return result;
   }
   
    public Line summation()
   {
       double sum = 0;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
            sum += v1.getValue();
            result.add(new SimpleValue(v1.getDomainValue(),sum));
        }
        return result;
   }
   
   public Line avg(int s){
      SimpleLine result = new SimpleLine();
      Enumeration<Value> e = elements();
      double[] prices = new double[s];
      int count = 0;
      while(e.hasMoreElements()){
         Value v= e.nextElement();
         prices[count%s] = v.getValue();
         count ++ ;
         if(count >= s){
            result.add(new SimpleValue(v.getDomainValue(), getAverage(prices)));
         }
      }
      return result;
   }    

   public Line avg(Line sl)
   {
       
       int slIdx = 0;
 	   
	   if(sl.getDomain() != getDomain())
	   {
		   return null;
	   }
 	   Value  v = sl.valueAt(slIdx);
	   slIdx++;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
			if(v.getDomainValue().getScale() == v1.getDomainValue().getScale())
			{
               int count = (int)v.getValue();
			   double sum = 0;
			   int j = 0;
			   for(j=0;j<count;j++)
			   {
                   if(i-j>=0)
 				   {
 				      sum+=valueAt(i-j).getValue();
                   }else
                   {
				   	  break;
                   }
			   }
			   result.add(new SimpleValue(v1.getDomainValue(),sum/j));
			   if(slIdx >= sl.length())
               {
                   return result;
			   }
			   v = sl.valueAt(slIdx);
	           slIdx++;
			   
			}
            
        }
        return result;
   }
   public Line avg()
   {
       double sum = 0;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
            sum += v1.getValue();
            result.add(new SimpleValue(v1.getDomainValue(),sum/(i+1)));
        }
        return result;
   }

   public Line abs()
   {
       double vd = 0;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
            vd = v1.getValue();
			if(vd >= 0)
		    {
                result.add(new SimpleValue(v1.getDomainValue(), vd));
		    } else
		    {
                result.add(new SimpleValue(v1.getDomainValue(), vd *-1));
			}
        }
        return result;
   }
   
   public Line sign()
   {
       double vd = 0;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
            vd = v1.getValue();
			if(vd > 0)
		    {
                result.add(new SimpleValue(v1.getDomainValue(), 1));
		    } else if(vd < 0)
		    {
                result.add(new SimpleValue(v1.getDomainValue(), -1));
			} else
			{
                result.add(new SimpleValue(v1.getDomainValue(), 0));
			}
        }
        return result;
   }
   public Line complete()
   {
       double vd = 0;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
            vd = v1.getValue();
			if(vd == 0)
		    {
                result.add(new SimpleValue(v1.getDomainValue(), 1));
		    } else 
		    {
                result.add(new SimpleValue(v1.getDomainValue(), 0));
			} 
        }
        return result;
   }

   public Line pos()
   {
       double vd = 0;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
            vd = v1.getValue();
			if(vd > 0)
		    {
                result.add(new SimpleValue(v1.getDomainValue(), vd));
		    } else
		    {
                result.add(new SimpleValue(v1.getDomainValue(), 0));
			}
        }
        return result;
   }
   
   public Line neg()
   {
       double vd = 0;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
            vd = v1.getValue();
			if(vd < 0)
		    {
                result.add(new SimpleValue(v1.getDomainValue(), vd));
		    } else
		    {
                result.add(new SimpleValue(v1.getDomainValue(), 0));
			}
        }
        return result;
   }

   public Line and(Line l)
   {
       double vd = 0;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
			Value v2 = l.valueAt(i);
            vd = v1.getValue();
			if(vd == 0)
		    {
                result.add(new SimpleValue(v1.getDomainValue(), 0));
		    } else if(vd > 0)
		    {
		        if(v2.getValue() > 0)
		        {
                   result.add(new SimpleValue(v1.getDomainValue(), vd));
		      	}else
		     	{
                   result.add(new SimpleValue(v1.getDomainValue(), 0));
				}
			} else
			{
                  if(v2.getValue() < 0)
                 {
	                 result.add(new SimpleValue(v1.getDomainValue(), vd));
                 }else
                 {
	               result.add(new SimpleValue(v1.getDomainValue(), 0));
                 }
 			}
        }
        return result;
   }
   
   public Line or(Line l)
   {
       double vd = 0;
       SimpleLine result = new SimpleLine(); 
       for(int i = 0; i < length(); i ++)
       {
            Value v1 = valueAt(i);
			Value v2 = l.valueAt(i);
            vd = v1.getValue();
			if(vd != 0)
		    {
                result.add(new SimpleValue(v1.getDomainValue(), vd));
		    } else if(v2.getValue() != 0)
		    {
                result.add(new SimpleValue(v1.getDomainValue(), v2.getValue()));
			} else
			{
                result.add(new SimpleValue(v1.getDomainValue(), 0));
  			}
        }
        return result;
   }
   
   public Line rsv(int k){
     SimpleLine result = new SimpleLine();
     Enumeration<Value> e = elements();
     double[] priceHs = new double[k];
     double   price = 0;
     int count = 0;
     while(e.hasMoreElements()){
        Value v= e.nextElement();
        price =  v.getValue();
        priceHs[count%k] = price;
        count ++ ;
        if(count >= k){
           result.add(new SimpleValue(v.getDomainValue(), getRSV(priceHs, price)));
        }
     }
     return result;
  }    

   public Line percent(double upbound, double lowbound)
   {
     SimpleLine result = new SimpleLine();
     Enumeration<Value> e = elements();
     double   price = 0;
     while(e.hasMoreElements()){
        Value v= e.nextElement();
        price =  v.getValue();
        result.add(new SimpleValue(v.getDomainValue(), ((price - lowbound) / (upbound - lowbound)) * 100 ));
     }
     return result;
   }
   
   public Line macd(int range, int ratio){
     SimpleLine result = new SimpleLine();
     double preV = valueAt(0).getValue();
     Enumeration<Value> e = elements();
     while(e.hasMoreElements()){
        Value v= e.nextElement();
        preV = (preV / range) * (range - ratio) +  (v.getValue() / range) * ratio  ;
        result.add(new SimpleValue(v.getDomainValue(), preV));
     }
     return result;
  }    

   public Line macd(double initV, int range, int ratio){
     SimpleLine result = new SimpleLine();
     double preV = initV;
     Enumeration<Value> e = elements();
     while(e.hasMoreElements()){
        Value v= e.nextElement();
        preV = (preV / range) * (range - ratio) +  (v.getValue() / range) * ratio  ;
        result.add(new SimpleValue(v.getDomainValue(), preV));
     }
     return result;
  }    
   public Domain getDomain()
   {
   	  if(length() > 0)
         return valueAt(0).getDomain();
      
      return null;
   }

   
   public Line cross(Line l, int shift)
   {
      CLine crossLine = new CLine(this); 
      double sv1 = -9999999;
      double fv1 = -9999999;
      for(int i = shift; i < length(); i ++)
      {
           Value v1 = valueAt(i);
           Value v2 = l.valueAt(i-shift);
           double sv2 = v2.getValue();
           double fv2 = v1.getValue();
           if( i > shift)
           {
              if((fv1-sv1) * (fv2-sv2) < 0)
                crossLine.add(new CValue(v1.getDomainValue(), sv1, fv1, sv2, fv2, i ));
           }
           sv1 = sv2;
           fv1 = fv2;
       }
       return crossLine;
   }
   
   public  PatternLine pattern(Pattern ptn)
   {
       PatternLine  ptl = new PatternLine();
       int vStartIdx = 0;
       while(vStartIdx < length())
       {
          PatternValue ptv = ptn.find(this, vStartIdx);
          if(ptv != null)
          {
             ptl.add(ptv);
             vStartIdx = ptv.getEndIdx() + 1;
          }else{
             break;
          }
       }
       return ptl;
   }
   
   public  PatternLine patternByStep(Pattern ptn)
   {
       PatternLine  ptl = new PatternLine();
       int vStartIdx = 0;
       while(vStartIdx < length())
       {
          PatternValue ptv = ptn.find(this, vStartIdx);
          if(ptv != null)
          {
             ptl.add(ptv);
             vStartIdx = ptv.getStartIdx() + 1;
          }else{
             break;
          }
       }
       return ptl;
   }
   
    public  ClusterLine cluster(Line l) 
    {
        ClusterLine  clul = new ClusterLine();
        Enumeration<Value> e = l.elements();
        Domain d = getDomain();
        if(d != l.getDomain()) return null;

        int i = 0;
        boolean isTimeDomain = (d ==  Domain.getDomainByName("T"));
        while(e.hasMoreElements())
        {
            Value v = e.nextElement();
            Vector datas = new Vector();
            while(i < length())
            {
               Value v2 = valueAt(i);
              // System.out.println(v2.getDateTime() + " " +  v.getDateTime());
               if(v2.getDomainValue().getDoubleValue() <= v.getDomainValue().getDoubleValue())
               {
                  datas.add(v2);
               }else
               {
                  if(datas.size() > 0)
                  {
//                  	  if(isTimeDomain)
//                         clul.add(new TimeClusterValue(datas.elements(), v.getDateTime()));
                         clul.add(new ClusterValue(datas.elements(), v.getDomainValue()));
//                      else
  //                       clul.add(
                  }
                  break;
               }
               i++;
               
            } 
        }
        return clul;
    }

    public  Line expands(Line l) 
    {
        SimpleLine result = new SimpleLine(); 
        Enumeration<Value> e = l.elements();
        Domain d = getDomain();
        if(d != l.getDomain()) return null;
        if(length() == 0) return null;

        int i = 0;
        Value  Mv0 = null;
		    Value  Mv1 = null;
		    Mv0 = Mv1 = valueAt(i);
		    i++;
		    if(i<length())
		    {
		      Mv1 = valueAt(i);
		      i++;
		    }
		    Value v = null;
        while(e.hasMoreElements() || v!=null)
        {
            if(v == null)
            {
			          v = e.nextElement();
            }
			      if(Mv1 != Mv0)
            {
               if(v.getDomainValue().getScale() > Mv1.getDomainValue().getScale())
               {
                    Mv0 = Mv1;
				           	if(i<length())
					          {
					             Mv1 = valueAt(i);
						           i++;
					          }
					          continue;
			         }else if( v.getDomainValue().getScale() > Mv0.getDomainValue().getScale())
               {
                    result.add(new SimpleValue(v.getDomainValue(),Mv1.getValue()));
			         } else
			         {
                    result.add(new SimpleValue(v.getDomainValue(),Mv0.getValue()));
			         }
            } else
            { 
 			  // if( v.getDomainValue().getScale() >= Mv0.getDomainValue().getScale())
               {
                   result.add(new SimpleValue(v.getDomainValue(),Mv0.getValue()));
			         } 
			      }
			      v=null;
		   }
        return result;
    }

//    public  CurveLine curve(int period, double lowBound) throws Exception
//    {
//        CurveLine  crl = new CurveLine(this);
//        CurveValue Precrv = null;
//        for(int i = 0; i < length()-period+1; i++)
//        {
//            Vector v = new Vector();
//            for(int j = 0; j < period; j++)
//            {
//                v.add(valueAt(i+j));
//            } 
//            CurveValue crv = new CurveValue(v.elements());
//            
//            if(crv.getDistance() >  lowBound)
//            {
//               if(Precrv == null)
//               {
//                  Precrv = crv;
//               } else if (Precrv.getApex() == crv.getApex())
//               {
//                   if(crv.getDistance() > Precrv.getDistance())
//                   {
//                       Precrv  = crv;
//                   }
//               } else {
//                   crl.add(Precrv);
//                   Precrv = crv;
//               }
//            }
//        }
//        if (Precrv != null)
//        {
//            crl.add(Precrv);
//        }
//        return crl;
//    }
   
    public double getAvg()
    {
        Enumeration e = elements();
        Value v = null;
        int n = 0;
        double sum = 0;
        while(e.hasMoreElements())
        {
           v = (Value) e.nextElement();
           sum += v.getValue();
           n++;
        }
        if( n > 0)
           return sum/n;
        else
           return 0;   
    }
 
    public double getVar()
    {
    	double d = getAvg();
        Enumeration e = elements();
        Value v = null;
        double sum = 0;
        int n = 0;
        while(e.hasMoreElements())
        {
           v = (Value) e.nextElement();
           sum += (v.getValue() - d) * (v.getValue() - d);
           n++;
        }
        return sum / n;
    }
   
   public double getMax()
   {
      return getMaxValue().getValue();
   }
   
   public Value getMaxValue()
   {
	   Enumeration e = elements();
	   Value v = null;
	   Value maxv = null;
	   maxv = valueAt(0);
	   double max = maxv.getValue();
	   while(e.hasMoreElements())
	   {
		  v = (Value) e.nextElement();
		  if( v.getValue() > max)
		  {
			  max = v.getValue();
			  maxv = v;
		  }
	   }
	   return maxv;

   }
   public Value getMinValue()
   {
	   Enumeration e = elements();
	   Value v = null;
	   Value minv = valueAt(0);
	   double min = minv.getValue();
	   while(e.hasMoreElements())
	   {
		  v = (Value) e.nextElement();
		  if( v.getValue() < min)
		  {
			  min = v.getValue();
			  minv = v;
		  }
	   }
	   return minv;

   }
   
   public double getMin()
   {
      return getMinValue().getValue();
   }

    
    public void dump()
    {
      for(int i = 0; i < length(); i ++)
      {
           Value v1 = valueAt(i);
        //   System.out.println("Value: ["+v1.getDateValue()+" "+v1.getTimeValue()+ "]"+ v1.getValue());
           v1.dump();
       }
    }
    
    public int mappingIdx(Value v)
    {
      //if(mapper == null)
      //{
      //   mapper = new DefaultMapper();
      //}
      //int Idx = mapper.mappingIdx(this,v);
      //if (Idx < 0)
      //{
      //   System.err.println("1 mappingIdx:"+v.getDateValue());
      //   System.err.println("2 mappingIdx:"+new Date(v.getDateTime()));
      //}
      for(int i = 0; i < length(); i ++)
      {
           Value v1 = valueAt(i);
           if(v1 == v)
           {
               return i;
           }
      }
      return -1;
    }
    public Line inverse() //throws Exception
    {
         return inverse(0, 1);
    }
    
    public Line inverse(double fg, double lg) //throws Exception
    {
        Domain d = Domain.getDomainByLine(this);
        if(d == null)
        {
            return null;
        }
        SimpleLine result = new SimpleLine(); 
        Enumeration<Value> e = elements();
        TreeSet vts = new TreeSet(new ValueComparator());
        int n = length();
        int startin = (int)(n * fg);
        int endin = (int)(n * lg);
        
        while(e.hasMoreElements())
        {
            vts.add(e.nextElement());
        }
        Iterator<Value> i = vts.iterator();
        
   //     if(d.isCountable())
        {
           int idx = 0;
		   ImageValue iv = null;
           while(i.hasNext())
           {
           	   Value v = i.next();
           	   if(idx >= startin && idx < endin)
           	   {
                 DomainValue dv = new DomainValue(d, v.getValue()); 
				 if(iv != null && iv.getDomainValue().getScale() == dv.getScale())
				 {
					  iv.addValue(v);
				 } else
				 {
				     iv = new ImageValue(dv, v.getDomainValue().getDoubleValue(), v);
				     result.add(iv);
				 }
               }
               idx ++;
           }   
        }/* else
        {
           int idx = 0;
           while(i.hasNext())
           {
           	   Value v = i.next();
           	   if(idx >= startin && idx < endin)
           	   {
                  DomainValue dv = new DomainValue(d, v.getValue()); 
                  result.add(new ImageValue(dv, v.getDomainValue().getDoubleValue(), v));  
               }
               idx ++;
           }   
        }*/
         return result;
    }
   
    public Line transTo(Line l2) //throws Exception
    {
        Line dr = inverse();
		SimpleLine result = new SimpleLine(); 
		int i = 0;
		for(i=0; i<dr.length();i++)
		{
           ImageValue iv = (ImageValue)dr.valueAt(i);
		   DomainValue dv = iv.getDomainValue();
		  // Value miv = l2.valueAt(iv.getImage().getDomainValue());
		   ImageValue iv2 = null;
		   Enumeration e = iv.getImages();
		   while(e.hasMoreElements())
		   {
               Value v2 = (Value)e.nextElement();
			   Value vv = l2.valueAt(v2.getDomainValue());
			   if(iv2 == null)
			   {
 				  iv2 = new ImageValue(dv,vv.getValue(),vv);
			   } else
			   {
			      iv2.addValue(vv);
			   }
		   }
		   result.add(iv2);
		}
		return result;
    }
    
    public Line[] dft(double ratio)
    {
    	  SimpleLine[] lary = new SimpleLine[2];
    	  lary[0] = new SimpleLine();
    	  lary[1] = new SimpleLine();
        int N = length(); 
        int K = (int)(ratio * N);
        Domain f = Domain.getDomainByName("N");
        double dw = 2 * Math.PI / (double)K;
        for(int fi = 0; fi < K ; fi ++)
        {
           double cosf = 0;
           double sinf = 0;
           double kw = dw * fi;
           for(int n=0;n<N;n++)
           {
               double v =  valueAt(n).getValue();
               cosf += v * Math.cos(kw*n);
               sinf += v * Math.sin(kw*n);
           }
           lary[0].add(new SimpleValue(new DomainValue(f,fi),cosf));
           lary[1].add(new SimpleValue(new DomainValue(f,fi),sinf));
        }
        return lary;
    }
   
    public Line idft(Line cosL, Line sinL, int N)
    {
       SimpleLine result = new SimpleLine();
       int K = cosL.length(); 
       Domain x = Domain.getDomainByName("N");
       double dw = 2 * Math.PI / (double)K;
      
       for(int xi = 0; xi < N ; xi ++)
       {
          double cosf = 0;
          for(int k = 0;k<K;k++)
          {
             Value cv =  cosL.valueAt(k);
             Value sv =  sinL.valueAt(k);
             double dk = dw*k;
             cosf += cv.getValue() * Math.cos(dk*xi);
             cosf += sv.getValue() * Math.sin(dk*xi);
          }
          result.add(new SimpleValue(new DomainValue(x,xi),cosf/N));
        }
         return  result;
    }
    
	public double statistics()
	{
		 return statistics(0);
	}
	
	public double statistics(double threshold )
	{
		 double ok_count = 0;
		 if(length()==0) return 0;
		 for(int i = 0;i< length();i++)
		 {
			 if(valueAt(i).getValue() > threshold)
                ok_count++;
		 }
		 return ok_count/length();
	}
    
    static private double getRSV(double[] priceS, double price)
    {
       double[] result = new double[2]; 
       result[0] = priceS[0];
       result[1] = priceS[0];
       for(int i = 0; i < priceS.length ; i++){
          if (priceS[i] > result[0]){
             result[0] = priceS[i];
          }
          if (priceS[i] < result[1]){
             result[1] = priceS[i];
          }
       }
       return ((price - result[1]) / (result[0] - result[1])) * 100; 
    } 
    
    static private double getAverage(double[] prices)
    {
        double sum = 0;
        for(int i = 0; i < prices.length; i++){
            sum += prices[i];
        }
        return sum /  prices.length ;
    }
    
    private class ValueComparator implements Comparator<Value>
    {
        public int compare(Value v1, Value v2) 
       {
          if(v1.getValue() < v2.getValue()) return -1;
          return 1;
       }
       
       public boolean equals(Object obj) 
       {
          return super.equals(obj);
       }
    }
	
	public Line shadow()
	{
		int i = 0;
		Hashtable ht = new Hashtable();
		Line ml = null;
		for(i=0;i<length();i++)
		{
			 Value v0 = valueAt(i);
			 if(v0 instanceof PatternValue)
			 { 
                 PatternValue pv = (PatternValue)v0;
				 if(ml == null) ml = pv.getMotherLine();
			     for(int j = 0; j<pv.length();j++)
			     {
				    Value v = ml.valueAt(pv.getStartIdx()+j);
				    ht.put(v,pv);
			     }
			 }
		}
		if(ml == null) return this;
		SimpleLine shadowSimpleLine = new SimpleLine();
		for(i=0;i<ml.length();i++)
		{
		   Value v = ml.valueAt(i);
		   PatternValue pv = (PatternValue)ht.get(v);
		   if(pv == null)
		   {
			   shadowSimpleLine.add(new SimpleValue(v.getDomainValue(),0));  
		   }else
		   {   
		       if(v instanceof PatternValue)
			   {
				   shadowSimpleLine.add(v);  
			   } else
			   {
				   shadowSimpleLine.add(new SimpleValue(v.getDomainValue(), pv.getValue()));  
			   }
		   }
		}
		return shadowSimpleLine;
		
	}
     
}
