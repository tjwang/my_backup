package stock.db;

import java.sql.*;
import java.util.*;
import java.lang.*;
import javax.sql.*;
import javax.naming.*;

public class DBConnection{
  private Connection con = null;
  static private Connection JDBCModeConnection=null;
  static public final int DataSourceMode=0;
  static public final int JDBCMode=1;
  static private  String  jdbcUrl="jdbc:mysql://localhost/test";
  
  static private int connectMode=DataSourceMode;
 // static private int connectMode=JDBCMode;
  static public boolean debug=false;

  static public int getConnectMode(){
                return  connectMode;
  }

  public DBConnection(){}

  public void CloseDB(){

  }

  public Connection getConnection() throws NamingException,SQLException{
         try{
       //      System.out.println("get JDBC....");
             if(JDBCModeConnection==null){
            	   Class.forName("com.mysql.jdbc.Driver");
                 JDBCModeConnection=java.sql.DriverManager.getConnection(jdbcUrl,"root","123456");
             }
          }catch(Exception e){
               System.out.println(e.getMessage());
          }
             return JDBCModeConnection;
  }


}
