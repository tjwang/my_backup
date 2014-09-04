import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.tool.*;

public class CulMinData {
  static public void main(String[] arg)throws Exception
  {
    if(arg.length == 2)
    {
       DBConnection dbc = new DBConnection();
       Connection dc = dbc.getConnection();
       Statement dbstmt= dc.createStatement();
       dbstmt.executeUpdate("delete from pfiveraw ");
       dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSDataPath()+"\\\\"+
                       arg[0]+"\\\\fiveRaw."+ arg[0]+".txt\"   into table pfiveraw FIELDS  TERMINATED by '|' ;");
    } else
    {
     culMinBK(arg[0]);
     culMinSK(arg[0]);
     culMinMBK(arg[0]);
     culMinMSK(arg[0]);
    }
  }
  static public void culMinK(String cDate)throws Exception
  {
        PFiveRaw_Rec pfr = new PFiveRaw_Rec();
        Enumeration e = pfr.SelectBySQL("select * from pfiveraw where date='"+cDate+"' order by snum,date,time");
        PFiveRaw_Rec prev_pfr = null;
        float open_v = -100;
        float close_v = 0;
        float high_v=-100;
        float low_v=999999;
        int rrp = 0;
        while(e.hasMoreElements())
        {
            pfr=(PFiveRaw_Rec)e.nextElement();
            if(prev_pfr!=null && (!prev_pfr.snum.equals(pfr.snum) || 
              (Integer.parseInt(prev_pfr.time)%1000000/100!=Integer.parseInt(pfr.time)%1000000/100)))
           {
                System.out.println(prev_pfr.snum+"|"+cDate+"|"+(Integer.parseInt(prev_pfr.time)%1000000/100*100)+"|"+
                         open_v+"|"+close_v+"|"+high_v+"|"+low_v+"|"+(Integer.parseInt(prev_pfr.ra)-rrp)+"|");
                open_v = -100;
                close_v = 0;
                high_v=-100;
                low_v=999999;
                rrp = Integer.parseInt(prev_pfr.ra);
           }
            float v = Float.parseFloat(pfr.rp);
            if(open_v < 0) open_v = v;
            if(v<low_v) low_v = v;
            if(v>high_v) high_v = v;
            close_v = v;
            prev_pfr = pfr;
        }
  }
  
  static public void culMinBK(String cDate)throws Exception
  {
        PFiveRaw_Rec pfr = new PFiveRaw_Rec();
        Enumeration e = pfr.SelectBySQL("select * from pfiveraw where date='"+cDate+"' order by snum,date,time");
        PFiveRaw_Rec prev_pfr = null;
        int open_v = -100;
        int close_v = 0;
        int high_v=-100;
        int low_v=999999;
        int rrp = 0;
        while(e.hasMoreElements())
        {
            pfr=(PFiveRaw_Rec)e.nextElement();
            if(prev_pfr!=null && (!prev_pfr.snum.equals(pfr.snum) || 
              (Integer.parseInt(prev_pfr.time)%1000000/100!=Integer.parseInt(pfr.time)%1000000/100)))
           {
                System.out.println(prev_pfr.snum+"|"+cDate+"|"+(Integer.parseInt(prev_pfr.time)%1000000/100*100)+"|"+
                         open_v+"|"+close_v+"|"+high_v+"|"+low_v+"|"+(Integer.parseInt(prev_pfr.ra)-rrp)+"|");
                open_v = -100;
                close_v = 0;
                high_v=-100;
                low_v=999999;
                rrp = Integer.parseInt(prev_pfr.ra);
           }
            int v = Integer.parseInt(pfr.b1ra) + Integer.parseInt(pfr.b2ra) + Integer.parseInt(pfr.b3ra) +
                    Integer.parseInt(pfr.b4ra) + Integer.parseInt(pfr.b5ra);
            if(open_v < 0) open_v = v;
            if(v<low_v) low_v = v;
            if(v>high_v) high_v = v;
            close_v = v;
            prev_pfr = pfr;
        }
                System.out.println(prev_pfr.snum+"|"+cDate+"|"+(Integer.parseInt(prev_pfr.time)%1000000/100*100)+"|"+
                         open_v+"|"+close_v+"|"+high_v+"|"+low_v+"|"+(Integer.parseInt(prev_pfr.ra)-rrp)+"|");
  }

  static public void culMinSK(String cDate)throws Exception
  {
        PFiveRaw_Rec pfr = new PFiveRaw_Rec();
        Enumeration e = pfr.SelectBySQL("select * from pfiveraw where date='"+cDate+"' order by snum,date,time");
        PFiveRaw_Rec prev_pfr = null;
        int open_v = -100;
        int close_v = 0;
        int high_v=-100;
        int low_v=999999;
        int rrp = 0;
        while(e.hasMoreElements())
        {
            pfr=(PFiveRaw_Rec)e.nextElement();
            if(prev_pfr!=null && (!prev_pfr.snum.equals(pfr.snum) || 
              (Integer.parseInt(prev_pfr.time)%1000000/100!=Integer.parseInt(pfr.time)%1000000/100)))
           {
                System.out.println(prev_pfr.snum+"|"+cDate+"|"+(Integer.parseInt(prev_pfr.time)%1000000/100*100)+"|"+
                         open_v+"|"+close_v+"|"+high_v+"|"+low_v+"|"+(Integer.parseInt(prev_pfr.ra)-rrp)+"|");
                open_v = -100;
                close_v = 0;
                high_v=-100;
                low_v=999999;
                rrp = Integer.parseInt(prev_pfr.ra);
           }
            int v = Integer.parseInt(pfr.s1ra) + Integer.parseInt(pfr.s2ra) + Integer.parseInt(pfr.s3ra) +
                    Integer.parseInt(pfr.s4ra) + Integer.parseInt(pfr.s5ra);
            if(open_v < 0) open_v = v;
            if(v<low_v) low_v = v;
            if(v>high_v) high_v = v;
            close_v = v;
            prev_pfr = pfr;
        }
                System.out.println(prev_pfr.snum+"|"+cDate+"|"+(Integer.parseInt(prev_pfr.time)%1000000/100*100)+"|"+
                         open_v+"|"+close_v+"|"+high_v+"|"+low_v+"|"+(Integer.parseInt(prev_pfr.ra)-rrp)+"|");
  }

  static public void culMinMBK(String cDate)throws Exception
  {
        PFiveRaw_Rec pfr = new PFiveRaw_Rec();
        Enumeration e = pfr.SelectBySQL("select * from pfiveraw where date='"+cDate+"' order by snum,date,time");
        PFiveRaw_Rec prev_pfr = null;
        float open_v = -100;
        float close_v = 0;
        float high_v=-100;
        float low_v=999999;
        int rrp = 0;
        while(e.hasMoreElements())
        {
            pfr=(PFiveRaw_Rec)e.nextElement();
            if(prev_pfr!=null && (!prev_pfr.snum.equals(pfr.snum) || 
              (Integer.parseInt(prev_pfr.time)%1000000/100!=Integer.parseInt(pfr.time)%1000000/100)))
           {
                System.out.println(prev_pfr.snum+"|"+cDate+"|"+(Integer.parseInt(prev_pfr.time)%1000000/100*100)+"|"+
                         open_v+"|"+close_v+"|"+high_v+"|"+low_v+"|"+(Integer.parseInt(prev_pfr.ra)-rrp)+"|");
                open_v = -100;
                close_v = 0;
                high_v=-100;
                low_v=999999;
                rrp = Integer.parseInt(prev_pfr.ra);
           }
            float v = Integer.parseInt(pfr.b1ra)* Float.parseFloat(pfr.b1rp) + Integer.parseInt(pfr.b2ra) * Float.parseFloat(pfr.b2rp)
                    + Integer.parseInt(pfr.b3ra)* Float.parseFloat(pfr.b3rp) +
                    Integer.parseInt(pfr.b4ra)* Float.parseFloat(pfr.b4rp) + Integer.parseInt(pfr.b5ra)* Float.parseFloat(pfr.b5rp);
            if(open_v < 0) open_v = v;
            if(v<low_v) low_v = v;
            if(v>high_v) high_v = v;
            close_v = v;
            prev_pfr = pfr;
        }
                System.out.println(prev_pfr.snum+"|"+cDate+"|"+(Integer.parseInt(prev_pfr.time)%1000000/100*100)+"|"+
                         open_v+"|"+close_v+"|"+high_v+"|"+low_v+"|"+(Integer.parseInt(prev_pfr.ra)-rrp)+"|");
  }

  static public void culMinMSK(String cDate)throws Exception
  {
        PFiveRaw_Rec pfr = new PFiveRaw_Rec();
        Enumeration e = pfr.SelectBySQL("select * from pfiveraw where date='"+cDate+"' order by snum,date,time");
        PFiveRaw_Rec prev_pfr = null;
        float open_v = -100;
        float close_v = 0;
        float high_v=-100;
        float low_v=999999;
        int rrp = 0;
        while(e.hasMoreElements())
        {
            pfr=(PFiveRaw_Rec)e.nextElement();
            if(prev_pfr!=null && (!prev_pfr.snum.equals(pfr.snum) || 
              (Integer.parseInt(prev_pfr.time)%1000000/100!=Integer.parseInt(pfr.time)%1000000/100)))
           {
                System.out.println(prev_pfr.snum+"|"+cDate+"|"+(Integer.parseInt(prev_pfr.time)%1000000/100*100)+"|"+
                         open_v+"|"+close_v+"|"+high_v+"|"+low_v+"|"+(Integer.parseInt(prev_pfr.ra)-rrp)+"|");
                open_v = -100;
                close_v = 0;
                high_v=-100;
                low_v=999999;
                rrp = Integer.parseInt(prev_pfr.ra);
           }
            float v = Integer.parseInt(pfr.s1ra) * Float.parseFloat(pfr.s1rp) + Integer.parseInt(pfr.s2ra)* Float.parseFloat(pfr.s2rp)
                     + Integer.parseInt(pfr.s3ra)* Float.parseFloat(pfr.s3rp) + Integer.parseInt(pfr.s4ra)* Float.parseFloat(pfr.s4rp) 
                     + Integer.parseInt(pfr.s5ra)* Float.parseFloat(pfr.s5rp);
            if(open_v < 0) open_v = v;
            if(v<low_v) low_v = v;
            if(v>high_v) high_v = v;
            close_v = v;
            prev_pfr = pfr;
        }
                System.out.println(prev_pfr.snum+"|"+cDate+"|"+(Integer.parseInt(prev_pfr.time)%1000000/100*100)+"|"+
                         open_v+"|"+close_v+"|"+high_v+"|"+low_v+"|"+(Integer.parseInt(prev_pfr.ra)-rrp)+"|");
  }

}