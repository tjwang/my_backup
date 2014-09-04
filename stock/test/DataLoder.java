package stock.test;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;

public class DataLoder {

  static Connection dc = null;
  static Statement dbstmt = null;
 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
      dc = dbc.getConnection();
      dbstmt= dc.createStatement();
     dbstmt.executeUpdate("delete from pLDayK2") ;
     dataDo("0001","DATA(5MI_001).csv","0001.csv");
     dataDo("9999","DATA(5MI_MXFB8).csv","9999.csv");
 }
 
static void dataDo(String snum,String infn,String outfn)throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(
                           new FileInputStream("C:\\Program Files\\eleader\\data\\UserDef\\Chart\\CSV\\"+infn)));

    PrintStream ps = new PrintStream(new FileOutputStream("C:\\�s��Ƨ�\\�W��\\tmpdata\\"+outfn));
    br.readLine();
    String linedata = null;
    while((linedata = br.readLine())!=null){
        ps.println(snum+","+linedata+",");
    }
    dbstmt.executeUpdate("load data infile \"C:\\\\�s��Ƨ�\\\\�W��\\\\tmpdata\\\\"+outfn+
                          "\" into table pLDayK2 FIELDS  TERMINATED by ',';") ;
}


}
