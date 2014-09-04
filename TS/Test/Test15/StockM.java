import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class StockM extends SimpleLineFactory{
 
    Vector<Pldayk_ofcl_Rec> infodata ;
    SimpleLineFactory blf ;
    SimpleLineFactory slf ;
    public   StockM() throws Exception
    {

      blf = new SimpleLineFactory();
      slf = new SimpleLineFactory();
      Pldayk_ofcl_Rec par= new Pldayk_ofcl_Rec();
      Enumeration e = null;
      infodata = new Vector();  
      e = par.SelectBySQL("select * from pldayk_ofcl where snum='T000' order by date,time "  );  
      while(e.hasMoreElements()){
      	  par = (Pldayk_ofcl_Rec)e.nextElement();
      	  infodata.add(par);
      }

    }

    public SimpleLine culASimpleLine(){
      Vector v =  new Vector();
      Enumeration<Pldayk_ofcl_Rec> e = infodata.elements();
      long totalamt = 0;
      long buyamt = 0;
      long sellamt = 0;
      int cnt = 0;
      Domain d = Domain.getDomainByName("N");
      Pldayk_ofcl_Rec prevpar = null; 
      boolean isSell = false;
      while(e.hasMoreElements()){
     	  Pldayk_ofcl_Rec par = e.nextElement();
     	  int ra = Integer.parseInt(par.RpMoney);
     	  double rp = (double)Float.parseFloat(par.aIdx);
        totalamt += ra;
        if(prevpar != null)
        {
            if( rp > Float.parseFloat(prevpar.aIdx))
            {
               buyamt += ra;
               isSell = false;
            } else if(rp < Float.parseFloat(prevpar.aIdx))
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
        int unitCount = 100000;
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