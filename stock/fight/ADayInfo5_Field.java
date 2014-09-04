package stock.fight;
import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ADayInfo5_Field {
 
    Vector<PAmountinfo5_Rec> infodata ;
    String date_str ;
    String snum;
//    aField[] _fielddata;
    public   ADayInfo5_Field()
    {
    	  date_str = null;
    	  infodata = new Vector<PAmountinfo5_Rec>();
    //	  _fielddata = null;
    }
    
    public boolean add(PAmountinfo5_Rec data)
    {
        if(date_str!=null)
        {
        	 if((!date_str.equals(data.date)) || (!snum.equals(data.snum)))
        	 {
        	     return false;
        	 }
        } else 
        {
           date_str = data.date;
           snum = data.snum;
        }
        infodata.add(data);
        return true;
    }  
    
    public void toFieldData()
    {
        if(infodata.size() <= 0) return;
        
       // _fielddata = new aField[infodata.size()];
        Enumeration<PAmountinfo5_Rec> e = infodata.elements();
       /*
        int i = 0;
        while(e.hasMoreElements())
        {
           PAmountinfo5_Rec fr = e.nextElement();
           _fielddata[i] = new aField();
           _fielddata[i].rp = Float.parseFloat(fr.rp);
           _fielddata[i].diff =Float.parseFloat(fr.diff);
           _fielddata[i].ra = Integer.parseInt(fr.ra);
           _fielddata[i].cnt = Integer.parseInt(fr.cnt);
           i++;
        }
        */
    }
    
    Enumeration<PAmountinfo5_Rec> info5_Rec()
    {
       return infodata.elements();
    }
    
    public void dump()
    {
        Enumeration<PAmountinfo5_Rec> e = infodata.elements();
        System.out.println("Date:"+date_str+" count:"+infodata.size());
        while(e.hasMoreElements())
        {
           PAmountinfo5_Rec fr = e.nextElement();
           System.out.println(fr.dump());
        }
        System.out.println("============================================");
    }
 /*   
    static class aField
    {
        float rp;
        float diff;
        int ra ;
        int cnt;
    }	
*/
 }
