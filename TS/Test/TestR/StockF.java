import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class StockF extends SimpleLineFactory{
 
    Vector<PLDaykSnum_Rec> infodata ;
    String  _snum;

    public   StockF(String snum) throws Exception
    {
    	   this(snum, null, null);
    }
 
    public  StockF(String snum,String dateStart, String dateEnd)throws Exception
    {
      _snum = snum;
      PLDaykSnum_Rec par= new PLDaykSnum_Rec();
      Enumeration e = null;
      if((dateStart != null) && (dateEnd != null))
        e = par.SelectBySQL("select * from  pldayksnum where   snum='"+snum+"' and date >='"+dateStart+"' and date <= '"+dateEnd+"'  order by date "  );  
      else if(dateStart != null)
      {
        e = par.SelectBySQL("select * from  pldayksnum where   snum='"+snum+"' and date >='"+dateStart+"' order by date "  );  
        System.out.println("select * from  pldayksnum where   snum='"+snum+"' and date >='"+dateStart+"' order by date "  );
      }else
        e = par.SelectBySQL("select * from  pldayksnum where   snum='"+snum+"'  order by date "  );  
      infodata = new Vector();  
      while(e.hasMoreElements()){
      	  par = (PLDaykSnum_Rec)e.nextElement();
      	  infodata.add(par);
      }
    }

    public SimpleLine culASimpleLine(){
      Vector v =  new Vector();
      Enumeration<PLDaykSnum_Rec> e = infodata.elements();
      double vt = 0;
      while(e.hasMoreElements()){
     	  PLDaykSnum_Rec par = e.nextElement();
     	  double pprice = Float.parseFloat(par.close) + Float.parseFloat(par.diff);
     	  double upbound = pprice * 1.07;
     	  double lowbound = pprice * 0.93;
     	  double openp = ((Float.parseFloat(par.open) - pprice) / (pprice * 0.07)) * 100;
     	  double closep = ((Float.parseFloat(par.close) - pprice) / (pprice * 0.07)) * 100;
     	  double highp = ((Float.parseFloat(par.high) - pprice) / (pprice * 0.07)) * 100;
     	  double lowp = ((Float.parseFloat(par.low) - pprice) / (pprice * 0.07)) * 100;
     	//  System.out.println(par.dump());
     	  if(closep > openp)
     	  {
     	  	 vt = (closep-openp)*(closep+openp) - (highp-closep)*(highp-closep)/2 + (openp-lowp)*(openp-lowp)/2;
        } else
        {
     	  	 vt = (openp-closep)*(closep+openp) - (highp-openp)*(highp-openp)/2 + (closep-lowp)*(closep-lowp)/2;
        }
        add(Integer.parseInt(par.date), 0, vt);
      }
      return culSimpleLine();
    }    

}