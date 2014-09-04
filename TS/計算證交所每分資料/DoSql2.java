import stock.db.*;
import java.io.*;
import java.net.*;
import java.sql.*;
import stock.tool.*;

public class DoSql2 {

 static public void main(String[] arg)throws Exception{
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();
//     dbstmt.executeUpdate("load data infile \"C:\\\\Mime\\\\新世代\\\\計算證交所每分資料\\\\"+
//                        arg[0]+"avgIdx.txt\" into table pavgindex_of FIELDS  TERMINATED by '|' ;");
     dbstmt.executeUpdate("load data infile \""+ GlobleSetting.getTSCodePath()+"\\\\計算證交所每分資料\\\\"+ arg[0]+"Difra.txt\" into table pIdxDifRp_offical FIELDS  TERMINATED by '|' ;");
     dbstmt.executeUpdate(
           "INSERT INTO pldayk_ofcl (snum,date,time,aIdx,eIdx,fIdx,BuyCnt,BuyAmt,SellCnt,SellAmt,RpCnt,RpAmt,RpMoney) "+ 
           "select \"T000\",pIndexs_offical.date,pIndexs_offical.time,Idx_A,Idx_B,Idx_C,BuyCnt,BuyAmt,SellCnt,SellAmt,RpCnt,RpAmt,RpMoney "+ 
           " from pIndexs_offical,pIdxDifRp_offical where pIndexs_offical.date = pIdxDifRp_offical.date and pIndexs_offical.time = pIdxDifRp_offical.time and pIdxDifRp_offical.date='"+ arg[0]+"';");
     dbstmt.executeUpdate("delete from pldayk_ofcl where RpMoney is NULL;");

 }

}
