package stock.app;
import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class  FiveElement extends AbstractValue{

   private PFive_Rec[] _buydata; 
   private PFive_Rec[] _selldata; 
   private PFive_Rec   _rpdata; 
   private int date_value = -1;
   private int time_value = -1;
   private double rp_value = -1;
   FiveElement(PFiveRaw_Rec srcdata)
   {
      _buydata = new PFive_Rec[5];
      _selldata = new PFive_Rec[5];
      for(int i=0; i < 5; i++)
      {
          _buydata[i] = null;  
          _selldata[i] = null;  
      }
      if(Integer.parseInt(srcdata.b1ra) > 0)
      {
      	 _buydata[0] = new  PFive_Rec();
         _buydata[0].snum = srcdata.snum;
         _buydata[0].date = srcdata.date;
         _buydata[0].time = srcdata.time;
         _buydata[0].type = "0";
         _buydata[0].rp = srcdata.b1rp;
         _buydata[0].ra = srcdata.b1ra;
      } 
      if(Integer.parseInt(srcdata.b2ra) > 0)
      {
       	 _buydata[1] = new  PFive_Rec();
         _buydata[1].snum = srcdata.snum;
         _buydata[1].date = srcdata.date;
         _buydata[1].time = srcdata.time;
         _buydata[1].type = "0";
         _buydata[1].rp = srcdata.b2rp;
         _buydata[1].ra = srcdata.b2ra;
      } 
      if(Integer.parseInt(srcdata.b3ra) > 0)
      {
      	 _buydata[2] = new  PFive_Rec();
         _buydata[2].snum = srcdata.snum;
         _buydata[2].date = srcdata.date;
         _buydata[2].time = srcdata.time;
         _buydata[2].type = "0";
         _buydata[2].rp = srcdata.b3rp;
         _buydata[2].ra = srcdata.b3ra;
      } 
      if(Integer.parseInt(srcdata.b4ra) > 0)
      {
      	 _buydata[3] = new  PFive_Rec();
         _buydata[3].snum = srcdata.snum;
         _buydata[3].date = srcdata.date;
         _buydata[3].time = srcdata.time;
         _buydata[3].type = "0";
         _buydata[3].rp = srcdata.b4rp;
         _buydata[3].ra = srcdata.b4ra;
      } 
      if(Integer.parseInt(srcdata.b5ra) > 0)
      {
      	 _buydata[4] = new  PFive_Rec();
         _buydata[4].snum = srcdata.snum;
         _buydata[4].date = srcdata.date;
         _buydata[4].time = srcdata.time;
         _buydata[4].type = "0";
         _buydata[4].rp = srcdata.b5rp;
         _buydata[4].ra = srcdata.b5ra;
      } 
      
      if(Integer.parseInt(srcdata.s1ra) > 0)
      {
      	 _selldata[0] = new  PFive_Rec();
         _selldata[0].snum = srcdata.snum;
         _selldata[0].date = srcdata.date;
         _selldata[0].time = srcdata.time;
         _selldata[0].type = "2";
         _selldata[0].rp = srcdata.s1rp;
         _selldata[0].ra = srcdata.s1ra;
      } 
      if(Integer.parseInt(srcdata.s2ra) > 0)
      {
      	 _selldata[1] = new  PFive_Rec();
         _selldata[1].snum = srcdata.snum;
         _selldata[1].date = srcdata.date;
         _selldata[1].time = srcdata.time;
         _selldata[1].type = "2";
         _selldata[1].rp = srcdata.s2rp;
         _selldata[1].ra = srcdata.s2ra;
      } 
      if(Integer.parseInt(srcdata.s3ra) > 0)
      {
      	 _selldata[2] = new  PFive_Rec();
         _selldata[2].snum = srcdata.snum;
         _selldata[2].date = srcdata.date;
         _selldata[2].time = srcdata.time;
         _selldata[2].type = "2";
         _selldata[2].rp = srcdata.s3rp;
         _selldata[2].ra = srcdata.s3ra;
      } 
      if(Integer.parseInt(srcdata.s4ra) > 0)
      {
      	 _selldata[3] = new  PFive_Rec();
         _selldata[3].snum = srcdata.snum;
         _selldata[3].date = srcdata.date;
         _selldata[3].time = srcdata.time;
         _selldata[3].type = "2";
         _selldata[3].rp = srcdata.s4rp;
         _selldata[3].ra = srcdata.s4ra;
      } 
      if(Integer.parseInt(srcdata.s5ra) > 0)
      {
      	 _selldata[4] = new  PFive_Rec();
         _selldata[4].snum = srcdata.snum;
         _selldata[4].date = srcdata.date;
         _selldata[4].time = srcdata.time;
         _selldata[4].type = "2";
         _selldata[4].rp = srcdata.s5rp;
         _selldata[4].ra = srcdata.s5ra;
      } 
      if(Integer.parseInt(srcdata.ra) > 0)
      {
         _rpdata = new PFive_Rec();
         _rpdata.snum = srcdata.snum;
         _rpdata.date = srcdata.date;
         _rpdata.time = srcdata.time;
         _rpdata.type = "1";
         _rpdata.rp = srcdata.rp;
         _rpdata.ra = srcdata.ra2;
      }
      if(_rpdata != null)
      {
         rp_value = (double)Float.parseFloat(_rpdata.rp);
      }
/*      if(_rpdata == null)
      {
         System.out.println(srcdata.dump());
      }*/
   }

   FiveElement(PFive_Rec[] srcdata)
   {
      _buydata = new PFive_Rec[5];
      _selldata = new PFive_Rec[5];
      for(int i=0; i < 5; i++)
      {
          _buydata[i] = null;  
          _selldata[i] = null;  
      }
      int bi = 0;
      int si = 0;
      for(int i=0; i < srcdata.length;i++)
      {
         if("0".equals(srcdata[i].type))
         {
            _buydata[bi] = srcdata[i];
            bi ++;
         }else if("1".equals(srcdata[i].type))
         {
            _rpdata = srcdata[i];
         }else if("2".equals(srcdata[i].type))
         {
            _selldata[si] = srcdata[i];
            si ++;
         }
      }
      setSell();
      setBuy();
      if(_rpdata != null)
      {
         rp_value = (double)Float.parseFloat(_rpdata.rp);
      }
   }
   
   FiveElement(Vector<PFive_Rec> v)
   {
      _buydata = new PFive_Rec[5];
      _selldata = new PFive_Rec[5];
      for(int i=0; i < 5; i++)
      {
          _buydata[i] = null;  
          _selldata[i] = null;  
      }
      int bi = 0;
      int si = 0;
      Enumeration<PFive_Rec> e = v.elements();
      while(e.hasMoreElements())
      {
      	 PFive_Rec pf = e.nextElement();
         if("0".equals(pf.type))
         {
            _buydata[bi] = pf;
            bi ++;
         }else if("1".equals(pf.type))
         {
            _rpdata = pf;
         }else if("2".equals(pf.type))
         {
            _selldata[si] = pf;
            si ++;
         }
      }
      setSell();
      setBuy();
      if(_rpdata != null)
      {
         rp_value = (double)Float.parseFloat(_rpdata.rp);
      }
   }

   private void setSell() // 0 -> 5  小到大
   {
   	  for( int i = 0; i < _selldata.length; i++) 
      {
         PFive_Rec minData = null;
         PFive_Rec tempData = null;
         int minIdx = -1;
         for(int j = i; j < _selldata.length; j++)
         {
            if( (_selldata[j]!= null) && ((minData == null) || (Float.parseFloat(minData.rp) > Float.parseFloat(_selldata[j].rp))))
            {
               minData = _selldata[j];
               minIdx = j;
            }
         }
         if (minIdx >= 0 )
         {
            tempData = _selldata[i];
            _selldata[i] =  minData;
            _selldata[minIdx] =  tempData;
         }
      }
   }
   
   private void setBuy()  // 0 -> 5  大到小
   {
   	  for( int i = 0; i < _buydata.length; i++) 
      {
         PFive_Rec maxData = null;
         PFive_Rec tempData = null;
         int maxIdx = -1;
         for(int j = i; j < _buydata.length; j++)
         {
            if( (_buydata[j]!= null) && ((maxData == null) || (Float.parseFloat(maxData.rp) < Float.parseFloat(_buydata[j].rp))))
            {
               maxData = _buydata[j];
               maxIdx = j;
            }
         }
         if (maxIdx >= 0 )
         {
            tempData = _buydata[i];
            _buydata[i] =  maxData;
            _buydata[maxIdx] =  tempData;
         }
      }
   }

   public int getDateValue()
   {
   	    if(date_value > 0)
   	    {
   	         return date_value;
   	    }
        PFive_Rec tempData = null;
        if(_buydata[0] != null) 
            tempData = _buydata[0] ;
        else if(_selldata[0] != null) 
            tempData = _selldata[0] ;
        else
            tempData = _rpdata ;
        
        date_value = Integer.parseInt(tempData.date);
        return date_value;
  
   }
   
   public int getTimeValue()
   {
   	   if(time_value > 0)
   	   {
   	        return time_value;
   	   }
       PFive_Rec tempData = null;
       if(_buydata[0] != null) 
           tempData = _buydata[0] ;
       else if(_selldata[0] != null) 
           tempData = _selldata[0] ;
       else
           tempData = _rpdata ;
           
       time_value =  Integer.parseInt(tempData.time);   
       return time_value;
 
   }
   
   public PFive_Rec[] getBuyData()
   {
       return _buydata;
   }
   public PFive_Rec[] getSellData()
   {
       return _selldata;
   }
   public PFive_Rec getRpData()
   {
       return _rpdata;
   }
   public double getValue()
   {
      return rp_value;
   }
   
   public boolean isNoData()
   {
      return ((_buydata[0] == null) && (_selldata[0] == null) && (_rpdata == null));
   }
   public String getSnum()
   {
       PFive_Rec tempData = null;
       if(_buydata[0] != null) 
           tempData = _buydata[0] ;
       else if(_selldata[0] != null) 
           tempData = _selldata[0] ;
       else
           tempData = _rpdata ;
       return  tempData.snum;
  }
  
  public void   dump()
  {
      System.out.println(_rpdata.dump());
      for(int j = _selldata.length - 1; j >= 0; j--) 
      {
        if(_selldata[j] != null)
        {
           System.out.println(_selldata[j].rp + "/  "+_selldata[j].ra);
        }
      }
      for(int j = 0 ; j < _buydata.length; j++) 
      {
         if(_buydata[j]!=null)
         {
           System.out.println( _buydata[j].rp+"/  "+_buydata[j].ra);
         }  
      }
  }

}