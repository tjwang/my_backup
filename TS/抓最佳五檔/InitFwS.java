import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;
import java.util.*;

public class InitFwS {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
     PrintStream[] fwps1 = new PrintStream[3];
     PrintStream[] fwps2 = new PrintStream[2];
     fwps1[0] = new PrintStream(new FileOutputStream("fw1.txt"));
     fwps1[1] = new PrintStream(new FileOutputStream("fw2.txt"));
     fwps1[2] = new PrintStream(new FileOutputStream("fw3.txt"));
     fwps2[0] = new PrintStream(new FileOutputStream("fw4.txt"));
     fwps2[1] = new PrintStream(new FileOutputStream("fw5.txt"));
     int ps1_count = 0;
     int ps2_count = 0;
     int ps1_idx = 0;
     int ps2_idx = 0;
     
     F1_Rec  f1 = new F1_Rec();
     f1.SelectBySQL("select date,time,snum as f1 from pamountinfo7 where snum='2454' and date > '20140501' order by date desc limit 1;");
     Ds000_Rec dsr = new Ds000_Rec();
     Ds000_Rec dsr2 = new Ds000_Rec();
     Enumeration e1 = dsr.SelectBySQL("select a.snum as snum, close as sname, (a.close * b.capital)/1000 as capital "+
         "from (select snum,close from pamountinfo7 where date = '"+f1.date+"') as a, "+
            "  (select ds000.snum as snum ,ds000.capital as capital from ds000,ds000attr where ds000.snum=ds000attr.snum and ds000attr.tcode='1' and capital != '10000000000') as b "+
         "where a.snum=b.snum     order by capital desc ;");

    Enumeration e2 = dsr.SelectBySQL("select a.snum as snum, close as sname, (a.close * b.capital)/1000 as capital "+
         "from (select snum,close from pamountinfo7 where date = '"+f1.date+"') as a, "+
            "  (select ds000.snum as snum ,ds000.capital as capital from ds000,ds000attr where ds000.snum=ds000attr.snum and ds000attr.tcode='2' and capital != '10000000000') as b "+
         "where a.snum=b.snum     order by capital desc ;");
     while(e1.hasMoreElements())
     {
        dsr=(Ds000_Rec)e1.nextElement();
        dsr2.snum = dsr.snum;dsr2.SelectInto();
        fwps1[ps1_idx].println(dsr.snum+"|"+dsr.sname+"|"+dsr2.capital+"|");
        ps1_count++;
        if(ps1_count > 99 && ps1_idx == 0) ps1_idx++;
        if(ps1_count > 300 &&  ps1_idx == 1) ps1_idx++;
     }
     while(e2.hasMoreElements())
     {
        dsr=(Ds000_Rec)e2.nextElement();
        dsr2.snum = dsr.snum;dsr2.SelectInto();
        fwps2[ps2_idx].println(dsr.snum+"|"+dsr.sname+"|"+dsr2.capital+"|");
        ps2_count++;
        if(ps2_count > 200 &&  ps2_idx == 0) ps2_idx++;
     }
     dbstmt.executeUpdate("delete from ds000fw1 ");
     dbstmt.executeUpdate("delete from ds000fw2 ");
     dbstmt.executeUpdate("delete from ds000fw3 ");
     dbstmt.executeUpdate("delete from ds000fw4 ");
     dbstmt.executeUpdate("delete from ds000fw5 ");
     dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSCodePath()
                          +"\\\\ъ程ㄎき郎\\\\fw1.txt\"   into table ds000fw1 FIELDS  TERMINATED by '|' ;");
     dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSCodePath()
                          +"\\\\ъ程ㄎき郎\\\\fw2.txt\"   into table ds000fw2 FIELDS  TERMINATED by '|' ;");
     dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSCodePath()
                          +"\\\\ъ程ㄎき郎\\\\fw3.txt\"   into table ds000fw3 FIELDS  TERMINATED by '|' ;");
     dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSCodePath()
                          +"\\\\ъ程ㄎき郎\\\\fw4.txt\"   into table ds000fw4 FIELDS  TERMINATED by '|' ;");
     dbstmt.executeUpdate("load data infile  \""+ GlobleSetting.getTSCodePath()
                          +"\\\\ъ程ㄎき郎\\\\fw5.txt\"   into table ds000fw5 FIELDS  TERMINATED by '|' ;");
 }

}
