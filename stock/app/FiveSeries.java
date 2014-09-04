package stock.app;
import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.tool.*;

public class  FiveSeries extends SimpleLineFactory{

  String  _snum;
 
  public  FiveSeries(String dateStr)throws Exception
  {
       this(null, dateStr);
  }
  
  public  FiveSeries(String snum,String dateStr)throws Exception
  {
  	/*  if(dateStr != null)
  	  {
         DBConnection dbc = new DBConnection();
         Connection dc = dbc.getConnection();
         Statement dbstmt= dc.createStatement();
         dbstmt.executeUpdate("delete from pfiveraw ");
         dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSDataPath()+"\\\\"+
                           dateStr+"\\\\fiveRaw."+ dateStr+".txt\"   into table pfiveraw FIELDS  TERMINATED by '|' ;");
      }*/
      _snum = snum;
      PFiveRaw_Rec pf5= new PFiveRaw_Rec();
      Enumeration e = null;
//      System.out.println("Start Query....");
      if(snum == null)
      {
          e = pf5.SelectBySQL("select * from  pfiveraw order by snum,time "  );    
      } else 
      {
          e = pf5.SelectBySQL("select * from  pfiveraw where   snum='"+snum+"' order by time "  );    
      }
//      System.out.println("Start set FiveSeries....");
      while(e.hasMoreElements())
      {
      	  pf5 = (PFiveRaw_Rec)e.nextElement();
          FiveElement fe = new FiveElement(pf5); 
          if(!fe.isNoData())
             add(fe);
      }
//      System.out.println("End set FiveSeries....");
  }

  public  FiveSeries(String snum, String dateStr, boolean notRaw)throws Exception
  {
  	  if(dateStr != null)
  	  {
         DBConnection dbc = new DBConnection();
         Connection dc = dbc.getConnection();
         Statement dbstmt= dc.createStatement();
         dbstmt.executeUpdate("delete from pfive ");
         dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSDataPath()+"\\\\"+
                           dateStr+"\\\\five."+ dateStr+".txt\"   into table pfive FIELDS  TERMINATED by '|' ;");
      }
      _snum = snum;
      PFive_Rec pf5= new PFive_Rec();
      PFive_Rec PrevPf5 = null;
      Enumeration e = null;
//      System.out.println("Start Query....");
      if(snum == null)
      {
          e = pf5.SelectBySQL("select * from  pfive order by snum,time,type,rp "  );    
      } else 
      {
          e = pf5.SelectBySQL("select * from  pfive where   snum='"+snum+"' order by time,type,rp "  );    
      }
      Vector v = new Vector();
//      System.out.println("Start set FiveSeries....");
      while(e.hasMoreElements())
      {
      	  pf5 = (PFive_Rec)e.nextElement();
          if((PrevPf5 != null) && (!PrevPf5.time.equals(pf5.time)))
          {
             add(new FiveElement(v));
             v = new Vector();
          }
          v.add(pf5);
          PrevPf5 = pf5;
      }
      add(new FiveElement(v));
//      System.out.println("End set FiveSeries....");
  }


}