package stock.fight;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class MALine extends AbstractLine{

   Vector data ;
   int _p;  
   MALine(int p){
   	   _p = p;
       data = new Vector();
   }
     
   void add(MAValue mav){
      data.add(mav);
   }

   public Line sub(int startIdx,int endIdx)
   {
      MALine subMALine = new MALine(_p);
      for(int i = startIdx; i<=endIdx ; i++)
      {
         subMALine.add((MAValue)valueAt(i));
      }
      return subMALine;
   }

   public int getPeriod(){
      return _p ;
   }
   
   Enumeration getCrossPoints(){
      Vector result = new Vector();
      Enumeration e =   data.elements();
      MAValue mav = null;
      MAValue premav = null;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         if( mav.isCross()){
            result.add(mav);
         } else if(premav!=null) {
          
           if(mav.isAbove()&&premav.isBelow()){
              result.add(mav);
           }
           if(premav.isAbove()&&mav.isBelow()){
              result.add(mav);
           }
         }
         premav = mav;
      }
      return result.elements();
   }
   
    public Enumeration getCrossSPoints(){
      Vector result = new Vector();
      Enumeration e =   data.elements();
      MAValue mav = null;
      MAValue premav = null;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         if( mav.isSCross()){
            result.add(mav);
         } else if(premav!=null){
           if(mav.isSAbove()&&premav.isSBelow()){
              result.add(mav);
           }
           if(premav.isSAbove()&&mav.isSBelow()){
              result.add(mav);
           }
         
         }
         premav = mav;
      }
      return result.elements();
   }  

   public Enumeration getVPoints(){
      Vector result = new Vector();
      Enumeration e =   data.elements();
      MAValue mav = null;
      MAValue premav = null;
      double prediff = 0;
      double diff = 0;
      boolean lowflag = false;
      boolean crossflag = false;
      int  period = 0;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         diff = mav.getDiff();
         period ++;
         if(prediff > diff) {
         	  lowflag = true ;
         	  if (premav != null){
            	 if (((mav.kclose-mav.value) * (premav.kclose-premav.value) < 0)||(mav.isSCross())){
          	      if(!crossflag){
                    if(period > 3){
           	           result.add(mav);
           	           period = 0;
           	        }  
          	      }
          	      crossflag = true;
         	        lowflag = false;
         	     }else {
//         	        if (crossflag){
//          	         if(period > 2){
//         	       	      result.add(premav);
//         	              period = 0;   
//         	           } 
//         	        }
                  if(!((crossflag)&&(mav.getDiff()<10))){
         	           crossflag = false;
         	        }
         	     }
         	  }   
         }else{ 
         	  if(premav !=null){
         	     if (((mav.kclose-mav.value) * (premav.kclose-premav.value) < 0)||(mav.isSCross())){
          	      if(!crossflag){
          	         if(period > 3){
          	         	  result.add(mav);
                        period = 0;
                      }
                   }  
          	      crossflag = true;
         	        lowflag = false;
         	     } else {
         	        //if(crossflag){
          	         //if(period > 2){
         	       	      //result.add(premav);
         	              //period = 0;   
         	          // } 
          	       // lowflag = false;
                  if(!((crossflag)&&(mav.getDiff()<10))){
         	           crossflag = false;
         	        }
       	         //}
         	     }
//         	     if(premav.getDate().equals("20080124")){
//         	        premav.dump();
//         	     }
         	  }   
            if((lowflag)&&(premav.getDiff()<10)) {
          	      if(period > 3){
         	       	      result.add(mav);
         	              period = 0;   
         	        } 
         	  }
         	  lowflag = false;  
         }
         prediff = diff; 
         premav = mav;
      }
      return result.elements();
    }  
    
   public MAValue getMaxDistance(MAValue startp,MAValue endp){
      Vector result = new Vector();
      Enumeration e =   data.elements();
      MAValue mav = null;
      MAValue maxmav = null;
      double maxdiff = 0;
      boolean startf = false;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         if(mav == startp) startf = true;
         if(!startf) continue;
         double diff = mav.getDiff();
         if(diff > maxdiff) {
         	  maxdiff = diff ;
            maxmav = mav;
         }
         if(mav == endp) break;
      }
      return maxmav;
    }        

   public double getVolume(MAValue startp,MAValue endp){
      Vector result = new Vector();
      Enumeration e =   data.elements();
      MAValue mav = null;
      double  v = 0;
      boolean startf = false;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         if(mav == startp) startf = true;
         if(!startf) continue;
         v +=mav.getVolume();
         if(mav == endp) break;
      }
      return v;
    }        
    
   public double getSlope(MAValue startp,MAValue endp){
      Vector result = new Vector();
      Enumeration e =   data.elements();
      MAValue mav = null;
//      MAValue maxmav = null;
      int timev = 0;
      boolean startf = false;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         if(mav == startp) startf = true;
         if(!startf) continue;
            timev++;
         if(mav == endp) break;
      }
      return (endp.value - startp.value)/timev;
    }        
  
   public double getTSlope(MAValue p){
      Vector result = new Vector();
      Enumeration e =   data.elements();
      MAValue mav = null;
      MAValue premav = null;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         if(mav == p){
            return (p.value - premav.value);
         } 
         premav = mav ;
      }
      return (p.value - premav.value);
    }        

   public Line culTLine(){
      MALine TLine = new MALine(1); 
      Vector result = new Vector();
      Enumeration e =   data.elements();
      MAValue mav = null;
      MAValue premav = null;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         if(premav!=null)
         {
            TLine.add(new MAValue(mav,mav.value - premav.value,1));
         }
         premav = mav ;
      }
      return TLine;
    }        

   public int getTimePeriod(MAValue startp,MAValue endp){
      Vector result = new Vector();
      Enumeration e =   data.elements();
      MAValue mav = null;
      MAValue maxmav = null;
      int timev = 0;
      boolean startf = false;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         if(mav == startp) startf = true;
         if(!startf) continue;
            timev++;
         if(mav == endp) break;
      }
      return timev;
    }        
   
   public  MAValue getMaValueByK(KValue kv)
   {
     Enumeration e =   data.elements();
      MAValue mav = null;
      while(e.hasMoreElements()){
         mav = (MAValue)e.nextElement();
         if(mav.getDateTime() == kv.getDateTime())
         {
            return mav;
         }
      }
      return null;
   }

   public Line diff(Line l)
   {

        return diff(l, 0);  
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
    
    
    
}