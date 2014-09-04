import stock.db.*;
import stock.fight.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class StockF extends SimpleLineFactory{
 
    Vector<PLDayk_Rec> infodata ;
    String  _snum;
    SimpleLineFactory blf ;
    SimpleLineFactory slf ;
    Line VLine = null;
    Line fourieL = null;
    Line fourieSL = null;
    Line fourieCL = null;
    public   StockF(String snum) throws Exception
    {
    	   this(snum, null, null);
    }
 
    public  StockF(String snum,String dateStart, String dateEnd)throws Exception
    {
      _snum = snum;
      PLDayk_Rec par= new PLDayk_Rec();
      Enumeration e = null;
      if((dateStart != null) && (dateEnd != null))
        e = par.SelectBySQL("select * from  pldayk where   snum='"+snum+"' and date >='"+dateStart+"' and date <= '"+dateEnd+"'  order by date "  );  
      else if(dateStart != null)
      {
        e = par.SelectBySQL("select * from  pldayk where   snum='"+snum+"' and date >='"+dateStart+"' order by date "  );  
        System.out.println("select * from  pldayk where   snum='"+snum+"' and date >='"+dateStart+"' order by date "  );
      }else
        e = par.SelectBySQL("select * from  pldayk where   snum='"+snum+"'  order by date "  );  
      infodata = new Vector();  
      blf = new SimpleLineFactory();
      slf = new SimpleLineFactory();
      while(e.hasMoreElements()){
      	  par = (PLDayk_Rec)e.nextElement();
      	  infodata.add(par);
      }
    }
    public Line culVLine(){
      Vector v =  new Vector();
      Enumeration<PLDayk_Rec> e = infodata.elements();
      long totalamt = 0;
      long buyamt = 0;
      long sellamt = 0;
      int cnt = 0;
      Domain d = Domain.getDomainByName("N");
      PLDayk_Rec prevpar = null; 
      boolean isSell = false;
      dataPool = new Vector<Value>();
      while(e.hasMoreElements()){
     	  PLDayk_Rec par = e.nextElement();
     	  int ra = (int)(Float.parseFloat(par.money)*100);
     	  double rp = (double)Float.parseFloat(par.close);
        totalamt += ra;
        if(prevpar != null)
        {
            if( rp > Float.parseFloat(prevpar.close))
            {
               buyamt += ra;
               isSell = false;
            } else if(rp < Float.parseFloat(prevpar.close))
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
        int unitCount = 20000;
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
      VLine = culSimpleLine();
      return VLine;
    }

    public Line culFSimpleLine(double ratio){
      dataPool = new Vector<Value>();
      int N = infodata.size(); 
      int K = (int)(ratio * N);
      Domain f = Domain.getDomainByName("N");
      Vector sv = new Vector();
      Vector cv = new Vector();
      double dw = 2 * Math.PI / (double)K;
      
      for(int fi = 0; fi < K ; fi ++)
      {
         double cosf = 0;
         double sinf = 0;
         double kw = dw * fi;
         for(int n=0;n<N;n++)
         {
             PLDayk_Rec par =  (PLDayk_Rec) infodata.elementAt(n);
             cosf += Float.parseFloat(par.close) * Math.cos(kw*n);
             sinf += Float.parseFloat(par.close) * Math.sin(kw*n);
         }
         cv.add(new SimpleValue(new DomainValue(f,fi),cosf));
         sv.add(new SimpleValue(new DomainValue(f,fi),sinf));
         add(new SimpleValue(new DomainValue(f,fi),Math.pow(sinf * sinf + cosf*cosf,0.5)));
      }
      fourieL = culSimpleLine();
      dataPool = cv;
      fourieCL = culSimpleLine();
      dataPool = sv;
      fourieSL = culSimpleLine();
      return  fourieL;
    }    

    public Line culRSimpleLine(){
        int N = infodata.size(); 
        Domain f = Domain.getDomainByName("N");
        dataPool = new Vector<Value>();
      
         for(int n=0;n<N;n++)
         {
             PLDayk_Rec par =  (PLDayk_Rec) infodata.elementAt(n);
             add(new SimpleValue(new DomainValue(f,n),Float.parseFloat(par.close)));
         }
         return culSimpleLine();
    }    
    public KLine culRawKLine(){
    	   Enumeration<PLDayk_Rec> e = infodata.elements();
    	   StockKD skd = new StockKD();
    	   while(e.hasMoreElements())
    	   {
             skd.add(e.nextElement());
         }
         return  skd.culKLine();
    }    

    public Line culXSimpleLine(){
      dataPool = new Vector<Value>();
      int K = fourieL.length(); 
      int N = infodata.size(); 
      Domain x = Domain.getDomainByName("N");
      double dw = 2 * Math.PI / (double)K;
      
      for(int xi = 0; xi < N ; xi ++)
      {
         double cosf = 0;
         for(int k=K/2;k<K;k++)
         {
             Value cv =  fourieCL.valueAt(k);
             Value sv =  fourieSL.valueAt(k);
             double dk = dw*k;
             cosf += cv.getValue() * Math.cos(dk*xi);
             cosf += sv.getValue() * Math.sin(dk*xi);
         }
         add(new SimpleValue(new DomainValue(x,xi),cosf/N));
      }
      return  culSimpleLine();
    }    

    public Line culXSimpleLine2(){
      dataPool = new Vector<Value>();
      int K = fourieL.length(); 
      int N = infodata.size(); 
      Domain x = Domain.getDomainByName("N");
      double dw = 2 * Math.PI / (double)K;
      
      for(int xi = 0; xi < N ; xi ++)
      {
         double cosf = 0;
         for(int k=0;k<K/2;k++)
         {
             Value cv =  fourieCL.valueAt(k);
             Value sv =  fourieSL.valueAt(k);
             double dk = dw*k;
             cosf += cv.getValue() * Math.cos(dk*xi);
             cosf += sv.getValue() * Math.sin(dk*xi);
         }
         add(new SimpleValue(new DomainValue(x,xi),cosf/N));
      }
      return  culSimpleLine();
    }    
    
    public SimpleLineFactory getBLF(){
      return blf;
    }
    
    public SimpleLineFactory getSLF(){
      return slf;
    }
}