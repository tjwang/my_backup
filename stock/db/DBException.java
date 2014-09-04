package stock.db;

import java.util.*;
import java.io.*;
import java.sql.*;

public class DBException extends Exception
{
   String msg;
   DbtableRec dbr;
   Exception pe;
  static Vector ErrorQueue=new Vector();
   
   public DBException(boolean isrollback,DbtableRec src,String m){
        dbr=src;
        msg=m;
  }

   public DBException(boolean isrollback,DbtableRec src,Exception e){
        dbr=src;
        msg=e.getMessage();
  }
  
  public String getMessage(){
        return "DBException:"+msg;
  }
  
  public String  toString(){
        return getMessage();
  }
  
  public int getSQLErrorCode(){
       if(pe instanceof SQLException){
           return ((SQLException)pe).getErrorCode();
       }
       return 0;
  }
  
  static public Enumeration getErrorItems(){
        return ErrorQueue.elements();
  }

  
  public void printStackTrace(){
          if(pe!=null){
             pe.printStackTrace();
             super.printStackTrace();
          }else{
             super.printStackTrace();
          }
  }
  
  public void printStackTrace(PrintStream ps){
          if(pe!=null){
             pe.printStackTrace(ps);
             super.printStackTrace(ps);
          }else{
             super.printStackTrace(ps);
          }

  }
  
  public DbtableRec getDbtableRec(){
       return dbr;
  }

}