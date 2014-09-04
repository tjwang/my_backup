import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class StockF extends SimpleLineFactory{
 
    Vector<PamountNew_Rec> infodata ;
    String  _snum;
    SimpleLineFactory blf ;
    SimpleLineFactory slf ;
    public   StockF(String snum) throws Exception
    {
      _snum = snum;

      blf = new SimpleLineFactory();
      slf = new SimpleLineFactory();
      PamountNew_Rec par= new PamountNew_Rec();
      Enumeration e = null;
      infodata = new Vector();  
      e = par.SelectBySQL("select * from pamountnew200909 where snum='"+snum+"'   order by date,time "  );  
      while(e.hasMoreElements()){
      	  par = (PamountNew_Rec)e.nextElement();
      	  infodata.add(par);
      }
      e = par.SelectBySQL("select * from pamountnew200910 where snum='"+snum+"'   order by date,time "  );  
      while(e.hasMoreElements()){
      	  par = (PamountNew_Rec)e.nextElement();
      	  infodata.add(par);
      }
      e = par.SelectBySQL("select * from pamountnew200911_12 where snum='"+snum+"'   order by date,time "  );  
      while(e.hasMoreElements()){
      	  par = (PamountNew_Rec)e.nextElement();
      	  infodata.add(par);
      }
      e = par.SelectBySQL("select * from pamountnew201001_04 where snum='"+snum+"'   order by date,time "  );  
      while(e.hasMoreElements()){
      	  par = (PamountNew_Rec)e.nextElement();
      	  infodata.add(par);
      }
      e = par.SelectBySQL("select * from pamountnew where snum='"+snum+"'   order by date,time "  );  
      while(e.hasMoreElements()){
      	  par = (PamountNew_Rec)e.nextElement();
      	  infodata.add(par);
      }

    }

    public SimpleLine culASimpleLine(){
      Vector v =  new Vector();
      Enumeration<PamountNew_Rec> e = infodata.elements();
      long totalamt = 0;
      long buyamt = 0;
      long sellamt = 0;
      int cnt = 0;
      Domain d = Domain.getDomainByName("N");
      PamountNew_Rec prevpar = null; 
      boolean isSell = false;
      while(e.hasMoreElements()){
     	  PamountNew_Rec par = e.nextElement();
     	  int ra = Integer.parseInt(par.ra);
     	  double rp = (double)Float.parseFloat(par.rp);
        totalamt += ra;
        if(prevpar != null)
        {
            if( rp > Float.parseFloat(prevpar.rp))
            {
               buyamt += ra;
               isSell = false;
            } else if(rp < Float.parseFloat(prevpar.rp))
            {
               sellamt += ra;
               isSell = true;
            } else
            {
                if(isSell)
                   sellamt += ra;
                else
                   buyamt += ra;
            }
        }
        int unitCount = 2000;
        long x = totalamt /unitCount;
        for(;cnt <x;cnt++)
        {
           if(sellamt > unitCount)
           {
              slf.add(new SimpleValue(new DomainValue(d,cnt),unitCount));
              sellamt -= unitCount;
           } else
           {
              slf.add(new SimpleValue(new DomainValue(d,cnt),(double)sellamt));
              sellamt = 0; 
           }
           if(buyamt > unitCount)
           {
              blf.add(new SimpleValue(new DomainValue(d,cnt),unitCount));
              buyamt -= unitCount;
           } else
           {
              blf.add(new SimpleValue(new DomainValue(d,cnt),(double)buyamt));
              buyamt = 0; 
           }
           add(new SimpleValue(new DomainValue(d,cnt),rp));
        }
        prevpar  = par;
      }
      return culSimpleLine();
    }
        
    public SimpleLineFactory getBLF(){
      return blf;
    }
    
    public SimpleLineFactory getSLF(){
      return slf;
    }
}