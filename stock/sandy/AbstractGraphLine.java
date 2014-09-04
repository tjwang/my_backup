package stock.sandy;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public abstract class AbstractGraphLine implements GraphLine
{
	     boolean isDataSeted = false;

       protected long enhance_SIdx = -1;
       protected long enhance_EIdx = -1;
       protected Stroke fstroke = null;
       protected PaintData[] _paintdata;
       protected double maxValue = -999999999;
       protected double minValue = 999999999;
       protected String name = "AbstractGraphLine";
	     
	     
       abstract public void paint(Graphics2D g, Rectangle rtv);
       
//	     public boolean isDataSeted()
//	     {
//	        return isDataSeted;
//	     }
//	     
//	     public void setDataSeted(boolean b)
//	     {
//	        isDataSeted = b;
//	     }

	     public void setElementPaintData(long xIdxValue, int x, int Zy, int xGap, double yratio, double ybase)
	     {
	       //PaintData pd = getPaintData(xIdxValue);
	       //if (pd!=null)
	       //{
	       //   pd.setPaintData(x, Zy, xGap, yratio, ybase);
	       //}

	       PaintData[] pda = getAllPaintData(xIdxValue);
	       if(pda != null)
	       {
	           for(int i = 0; i < pda.length; i++)
	           {
	               pda[i].setPaintData(x, Zy, xGap, yratio, ybase);
	           }
	       
	       }
	     }
       
       public PaintData getPaintData(long xIdxValue) 
       {
            int vIdx = getPaintDataIdx(xIdxValue);
            if(vIdx == -1) return null;
            return _paintdata[vIdx];
       }
       	     
       public PaintData[] getAllPaintData(long xIdxValue) 
       {
            int vIdx = getPaintDataIdx(xIdxValue);
            if(vIdx == -1) return null;
            int s = vIdx;
            while((s >= 0) && (_paintdata[s].getxIdxValue() == xIdxValue))
              s--;
            int e = vIdx;  
            while((e < _paintdata.length) && (_paintdata[e].getxIdxValue() == xIdxValue))
              e++;
              
            PaintData[] result = new PaintData[e-s-1];
            for(int i=s+1,j=0; i < e; i++,j++)
            {
               result[j] =  _paintdata[i];
            }  
            return result;
       }

       public int getPaintDataIdx(long xIdxValue) 
       {
           if(_paintdata.length < 1) return -1;
           int vRightPos = _paintdata.length - 1;
           int vLeftPos =  0;
           int vIdx = (vLeftPos + vRightPos) / 2;
           while(_paintdata[vIdx].getxIdxValue() != xIdxValue)
           {
     //      	   System.out.println(vLeftPos + " " + vRightPos + " "+ vIdx + " " +_paintdata[vIdx].getxIdxValue()+" " + xIdxValue);
               if(_paintdata[vIdx].getxIdxValue() > xIdxValue)
               {
                   if(vRightPos == vIdx)
                   {
         //          	 System.out.println( vIdx + " " +new Date(_paintdata[vIdx].getxIdxValue())+" "+ _paintdata[vIdx].getxIdxValue()+" " + new Date(xIdxValue)+ " "+xIdxValue);
                     return -1;
                   } else{
                     vRightPos = vIdx;
                   }
               } else
               {
                  if(vLeftPos != vIdx)
                   {
                      vLeftPos = vIdx;
                   } else {
                      if(_paintdata[vRightPos].getxIdxValue() != xIdxValue)
                      {
           //        	     System.out.println( vIdx + " " +new Date(_paintdata[vIdx].getxIdxValue())+" "+ _paintdata[vIdx].getxIdxValue()+" " + new Date(xIdxValue)+ " "+xIdxValue);
                         return -1;
                      }else
                      {
                         return vRightPos;
                      }
                      
                   }
               }
               vIdx = (vLeftPos + vRightPos) / 2;
           }
       	   return vIdx;
       }
       
       public PaintData getPaintDataByXPos(int x) 
       {
           int vRightPos = _paintdata.length - 2;
           int vLeftPos =  0;
           int vIdx = (vLeftPos + vRightPos) / 2;
           if(vIdx < 0) return null;
           while(_paintdata[vIdx].getX() > x || x >= _paintdata[vIdx+1].getX())
           {
               if(_paintdata[vIdx].getX() > x)
               {
                     if(vRightPos == vIdx)
                        return null;	
                     vRightPos = vIdx;
               } else
               {
                  if(vLeftPos != vIdx)
                   {
                      vLeftPos = vIdx;
                   } else {
                      if(_paintdata[vRightPos].getX() > x || x >= _paintdata[vRightPos+1].getX())
                      {
                         if(_paintdata[_paintdata.length-1].getX() <= x) 
                         {
                            return _paintdata[_paintdata.length-1];
                         }
                         return null;
                      } else {
                          return  _paintdata[vRightPos];
                      }
                   }
               }
               vIdx = (vLeftPos + vRightPos) / 2;
               //System.out.println(x+":"+ vIdx + " : "+vLeftPos + " : "+vRightPos + " : "+_paintdata[vLeftPos].getX() + " : "+_paintdata[vRightPos].getX() + " : "+_paintdata[vIdx].getX());
           }
       	   return _paintdata[vIdx];
       }
       
       public PaintData getPaintDataByIdx(int Idx) 
       {
            if(Idx >=0 && Idx < _paintdata.length)
              return _paintdata[Idx];
            
            return null;
       }
       
       public int getSize() 
       {
           return   _paintdata.length;
       }

	     public double getMaxValue()
	     {
	         return maxValue;
	     }
	     
	     public double getMinValue()
	     {
	         return minValue;
	     }
	     
	     public String getName()
	     {
	         return name;
	     }
	     
	     public void setName(String n)
	     {
	     	   if(n.length() > 12)
   	          name = n.substring(0,15);	
	          else
	            name = n;
	     }
	     
	     public void setEnhanceS(long idx)
	     {
	         enhance_SIdx = idx;
	     }
	     
	     public void setEnhanceE(long idx)
	     {
	         enhance_EIdx = idx;
	     }
 	     
 	     public void setFocusStroke(Stroke sk)
 	     {
 	         fstroke = sk;
 	     }

}