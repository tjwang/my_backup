import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.sql.*;
import stock.db.*;

public class GetList2 {
static URL u1 ; 
static String cookieValue;


 static public void main(String[] arg)throws Exception{
//  System.out.println("Hello!! Java !! Show Me The Momey!!!");
       DBConnection.debug = false;
     	    getData(arg[0]);
  }
 

  static boolean getData(String filename) throws Exception{
    boolean startflag = false;
    boolean trflag = false;
    int count = 0;
     DBConnection dbc = new DBConnection();
     Connection dc = dbc.getConnection();
     Statement dbstmt= dc.createStatement();

    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
    String s;
    String snum = null;
    String sname =null;
    String type=null;
    String ttcode =null;
    int state = 0;
    while((s=br.readLine())!=null){
    	char c = s.charAt(0);
    	if (c >='0' && c<='9')
    	{
    		 if((state == 1) || (state == 2) || (state == 3))
    		 {
             int f =0;
             StringBuffer sb = new  StringBuffer();
             for(int i = 0; i < s.length(); i++)
             {
                char c2 = s.charAt(i);
                if((c2 ==' ')||(c2 =='　'))
                {
                  String data = sb.toString();
                  if(data.length()>0)
                  {
                     //System.out.print(data+"|");
                     if(f==0)
                     {
                     	  snum = data;
                     } else if(f==2)
                     {
                     	  sname = data;
                     } else if(f==6)
                     {
                     	  type = data;
                     	  if(type.equals("上市"))
                     	  {
                     	  	ttcode="1";
                     	  } else if(type.equals("上櫃"))
                     	  {
                     	  	ttcode="2";
                     	  } else if(type.equals("管理股票"))
                     	  {
                     	  	ttcode="3";
                     	  } else{
                           System.err.println("err at type code"); 	
                     	  }
                     	  if(state == 2)
                     	  {
                          System.out.print(
                           "insert into ds000attr(snum,sname,type,category,CCODE,TCODE) values ( '"+
                         snum+"','"+sname+"','"+type+"','臺灣存託憑證','29','"+ttcode+"');");                
     dbstmt.executeUpdate(new String( (
                     "insert into ds000attr(snum,sname,type,category,CCODE,TCODE) values ( '"+
                           snum+"','"+sname+"','"+type+"','臺灣存託憑證','29','"+ttcode+"');").getBytes("MS950"),"ISO-8859-1")
                           );
                     	  }
                     } else   if(f==7)
                     {
                        Mmap_Rec mr = new Mmap_Rec();
                        if(mr.SelectBySQL(new String( (
                        "select distinct ccode as mnum1,\"2\" as mnum2 from ds000attr_Old where category='"+
                                        data+"' ").getBytes("MS950"),"ISO-8859-1")).hasMoreElements())
                        {
                           System.out.print(
                           "insert into ds000attr(snum,sname,type,category,CCODE,TCODE) values ( '"+
                           snum+"','"+sname+"','"+type+"','"+data+"','"+mr.mnum1+"','"+ttcode+"');");                
     dbstmt.executeUpdate(new String( (
                           "insert into ds000attr(snum,sname,type,category,CCODE,TCODE) values ( '"+
                           snum+"','"+sname+"','"+type+"','"+data+"','"+mr.mnum1+"','"+ttcode+"');").getBytes("MS950"),"ISO-8859-1")
                           );
                        }else{
                           System.err.println("err at category code"); 	
                        }
                     }
                  }
                  sb = new  StringBuffer();
                  f++;
                }else{
                  if((f==0) || (f==2) || (f==6) ||(f==7))
                   sb.append(c2);
                }
             }
              System.out.println();
         }

    	}else
    	{
         System.out.println(s);
         if(s.trim().equals("股票"))
         {
         	  state = 1;
         } else if(s.trim().equals("臺灣存託憑證"))
         {
         	System.out.println(s);
         	  state = 2;
         } else if(s.trim().equals("上櫃股票及權利證書"))
         {
        	  state = 3;
         }else{
            state = 4;
         }
      }
      
    }
    return true;
  }
}
