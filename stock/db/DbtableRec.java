package stock.db;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.lang.reflect.*;
import javax.naming.NamingException;
public class DbtableRec implements Cloneable,Serializable {

 private static final int EXECSQL_QUERY_RESULTSET=0;
 private static final int EXECSQL_QUERY_COUNT=1;
 private static final int EXECSQL_DELETE=2;
 private static final int EXECSQL_UPDATE=3;
 private static final int EXECSQL_INSERT=4;

 private String tabname=null;
 private ResultSet rs=null;
 private Connection conn = null;
 private Statement stmt = null;
 private int errCode=0;
 private boolean isrollback=true;
 private boolean callforself=false;
 private int rollback_op=0;
 private DbtableRec rollback_rec=null;
 DbtableRec(){


 }

 public String getTabName(){
     if(tabname==null){
                String s=this.getClass().getName();
                s=s.substring( s.lastIndexOf((int)'.')+1 );
           tabname=s.substring(0,s.lastIndexOf((int)'_'));
     }
     return tabname;
 }

 public int getErrorCode(){
       return errCode;
 }

 public String getPK()throws DBException{
   try{
        Class c=this.getClass();
        Field[] fds=c.getFields();
        StringBuffer pkstr=new StringBuffer();
        pkstr.append(" ");
        for(int i=0;i<fds.length;i++){
                      String s=fds[i].getName();
                      if(s.startsWith("pk_")){
                        s=s.substring(3);
                              String cv=(String)c.getField(s).get(this);
                        int vl=this.getClass().getField("ln_"+s).getInt(this);
                        if((cv==null)||(cv.length()==0)) cv=" ";
                        //if(vl < cv.length()){
                        //     cv=cv.substring(0,vl);
                        //}
                        pkstr.append(s);
                        pkstr.append("='");
                        pkstr.append(cv);
                        pkstr.append("' and ");
                      }
        }
        pkstr.delete(pkstr.length()-5,pkstr.length());
        return pkstr.toString();
    }catch(Exception e){
          throw new DBException(isrollback,this,e);
    }
 }

 boolean isPK(String key_name)throws Exception{
    Class c=this.getClass();
    Object o=c.getField("pk_"+key_name).get(this);
    if(o!=null) return true;
     return false;
 }


 public boolean SelectInto()throws DBException{
   DBConnection dbc = new DBConnection();
   try{
          return SelectInto(getPK(),dbc.getConnection());
   }catch(Exception e){
          if(e instanceof DBException)   throw (DBException)e ;
          throw new DBException(isrollback,this,e);
   }finally{
     dbc.CloseDB();
   }
 }

 public boolean SelectInto(String Cond)throws DBException{
   DBConnection dbc = new DBConnection();
   try{
          return SelectInto(Cond,dbc.getConnection());
   }catch(Exception e){
          if(e instanceof DBException)   throw (DBException)e ;
          throw new DBException(isrollback,this,e);
   }finally{
     dbc.CloseDB();
   }
 }

 public boolean SelectInto(Connection conn2)throws DBException{
    return SelectInto(getPK(),conn2);
 }

 public boolean SelectInto(String Cond,Connection conn2)throws DBException{
           return SelectByCondition(Cond,conn2).hasMoreElements();
 }

 public Enumeration SelectByCondition(String condition)throws DBException{
   DBConnection dbc = new DBConnection();
   try{
       return SelectByCondition(condition,dbc.getConnection());
   }catch(Exception e){
          if(e instanceof DBException)   throw (DBException)e ;
          throw new DBException(isrollback,this,e);
   }finally{
     dbc.CloseDB();
   }
 }

 public Enumeration SelectBySQL(String condition)throws DBException{
   DBConnection dbc = new DBConnection();
   try{
       return SelectBySQL(condition,dbc.getConnection());
   }catch(Exception e){
          if(e instanceof DBException)   throw (DBException)e ;
          throw new DBException(isrollback,this,e);
   }finally{
     dbc.CloseDB();
   }
 }

 public Enumeration SelectBySQL(String SQLstr,Connection conn2)throws DBException{
   try{
        Field[] fds=this.getClass().getFields();
        stmt = conn2.createStatement();
        ResultSet rs2 = (ResultSet)execSQL(SQLstr,stmt,EXECSQL_QUERY_RESULTSET);
        Vector v=new Vector();
        while((rs2!=null)&&(rs2.next())){
           for(int i=0;i<fds.length;i++){
               String s=fds[i].getName();
               if((!s.startsWith("pk_"))&&(!s.startsWith("ln_"))){
                    String ss=rs2.getString(s);
                    if(ss==null) ss="";
                    fds[i].set(this,ss);
               }
           }
          v.add(this.clone());
        }
        rs2.close();
        stmt.close();
        return v.elements();
   }catch(Exception sqe){
          if(sqe instanceof DBException)   throw (DBException)sqe ;
          throw new DBException(isrollback,this,sqe);
   }
 }


 public Enumeration SelectByCondition(String condition,Connection conn2)throws DBException{
   try{
        StringBuffer SQLstr=new StringBuffer();
        SQLstr.append("select ");
        Field[] fds=this.getClass().getFields();
        for(int i=0;i<fds.length;i++){
           String s=fds[i].getName();
           if((!s.startsWith("pk_"))&&(!s.startsWith("ln_"))){
              SQLstr.append(fds[i].getName());
              SQLstr.append(",") ;
           }
        }
        SQLstr.delete(SQLstr.length()-1,SQLstr.length());
        SQLstr.append(" from ");
        SQLstr.append(getTabName());
        SQLstr.append( " where ");
        SQLstr.append( condition);
        stmt = conn2.createStatement();
        ResultSet rs2 = (ResultSet)execSQL(SQLstr.toString(),stmt,EXECSQL_QUERY_RESULTSET);
        Vector v=new Vector();
        while((rs2!=null)&&(rs2.next())){
           for(int i=0;i<fds.length;i++){
               String s=fds[i].getName();
               if((!s.startsWith("pk_"))&&(!s.startsWith("ln_"))){
                    String ss=rs2.getString(s);
                    if(ss==null) ss="";
                    fds[i].set(this,ss);
               }
           }
          v.add(this.clone());
        }
        rs2.close();
        stmt.close();
        return v.elements();
   }catch(Exception sqe){
          sqe.printStackTrace();
          if(sqe instanceof DBException)   throw (DBException)sqe ;
          throw new DBException(isrollback,this,sqe);
   }
 }


 public int SelectCount()throws DBException{
     return SelectCountByCondition(getPK());
 }

 public int SelectCountByCondition(String condition)throws DBException{
   DBConnection dbc = new DBConnection();
   try{
       return SelectCountByCondition(condition,dbc.getConnection());
   }catch(Exception e){
       if(e instanceof DBException)   throw (DBException)e ;
       throw new DBException(isrollback,this,e);
   }finally{
       dbc.CloseDB();
   }
 }

 public int SelectCountByCondition(String condition,Connection con2)throws DBException{
  try{
      StringBuffer SQLstr=new StringBuffer();
      SQLstr.append("select count(*) ");
      SQLstr.append(" from ");
      SQLstr.append(getTabName());
      SQLstr.append( " where ");
      SQLstr.append( condition);
      return Integer.parseInt((String)execSQL(SQLstr.toString(),con2.createStatement(),EXECSQL_QUERY_COUNT));
  }catch(Exception e){
       if(e instanceof DBException)   throw (DBException)e ;
       throw new DBException(isrollback,this,e);
  }
}

 private Object execSQL(String SQLcmd,Statement stmt,int type)throws DBException{
   if(DBConnection.debug){
           System.out.println(SQLcmd);
   }
   try{
       switch(type){
                   case EXECSQL_QUERY_RESULTSET :
                              ResultSet rs2 = stmt.executeQuery(SQLcmd);
                              return rs2;
                   case EXECSQL_QUERY_COUNT:
                              ResultSet rs3 = stmt.executeQuery(SQLcmd);
                              rs3.next();
                              String query_count=rs3.getString(1);
                              rs3.close();
                              stmt.close();
                              return  query_count;
        }
    }catch(Exception e){
          throw new DBException(isrollback,this,e);
    }
    throw new DBException(isrollback,this,"no SQL cammand is executed!?");
  }


private String insert_stringValue()throws DBException{
  try{
       StringBuffer SQLstr=new StringBuffer();
       StringBuffer SQLstr2=new StringBuffer();
       SQLstr.append("insert into ");
       SQLstr.append(getTabName());
       SQLstr.append("(");
       Field[] fds=this.getClass().getFields();
       for(int i=0;i<fds.length;i++){
           String s=fds[i].getName();
           if((!s.startsWith("pk_"))&&(!s.startsWith("ln_"))){

                 String vv=(String)fds[i].get(this);
                  int vl=this.getClass().getField("ln_"+s).getInt(this);
                 if((vv==null)||(vv.length()==0)) vv=" ";
                 if (vl > vv.getBytes().length) {
                   vv = vv.replaceAll("'", "''");
                   SQLstr2.append(vv + "','");
                 }
                 else {
                   vv=new String(vv.getBytes(), 0, vl);
                   vv = vv.replaceAll("'", "''");
                   SQLstr2.append( vv+ "','");
                 }
                 //           if(vl>0)                         SQLstr2.append( vv.substring(0,vl)+"','" );
      //           else                             SQLstr2.append("','") ;

                 SQLstr.append(fds[i].getName());
                 SQLstr.append("," );
           }
       }
       SQLstr.delete(SQLstr.length()-1,SQLstr.length());
       SQLstr.append(") values ('");
       SQLstr2.delete(SQLstr2.length()-3,SQLstr2.length());
       SQLstr2.append("')");
       SQLstr.append(SQLstr2.toString());
       return SQLstr.toString();
    }catch(Exception e){
        throw new DBException(isrollback,this,e);
    }
}



private String update_stringValue()throws DBException{
   try{
        StringBuffer sb=new StringBuffer();
         sb.append("update ");
         sb.append(getTabName());
         sb.append("  set ");
        boolean haschangedfild=false;
        Field[] fds=this.getClass().getFields();
        for(int i=0;i<fds.length;i++){
            String s=fds[i].getName();
            if((!s.startsWith("pk_"))&&(!s.startsWith("ln_"))){
                    String vv=(String)fds[i].get(this);
                    if(rollback_rec !=null){
                      String vv2=(String)fds[i].get(rollback_rec);
                      if((vv!=null)&&vv.equals(vv2))  continue;
                    }
                haschangedfild=true;
                    int vl=this.getClass().getField("ln_"+s).getInt(this);
                    if((vv==null)||(vv.length()==0)) vv=" ";
               byte[] tmpb=vv.getBytes();
                    if(vl < tmpb.length)    {
                 vv= new String(tmpb,0,vl);
                    }
                vv = vv.replaceAll("'","''");
                sb.append( fds[i].getName());
                sb.append("='");
                sb.append(vv);
                sb.append("'," );
            }
        }
        sb.delete(sb.length()-2,sb.length());
        sb.append("' where ");
        if(haschangedfild)
         return sb.toString();
        return "NotUpdate";
   }catch(Exception e){
        throw new DBException(isrollback,this,e);
   }
 }

public boolean isEmpty(){
  try{
      Field[] fds=this.getClass().getFields();
      for(int i=0;i<fds.length-1;i++){
         String s=fds[i].getName();
         if((!s.startsWith("pk_"))&&(!s.startsWith("ln_"))){
             if(!isPK(s)){
                if(!((String)fds[i].get(this)).trim().equals(""))
                    return false;
             }
         }
      }
   }catch(Exception e){
          return true;
   }
   return true;
 }


 public DbtableRec Clone(){
    try{
       return (DbtableRec)this.clone();
    }catch(Exception e){
       return null;
    }
 }

 public void copyField(DbtableRec copyRec)throws DBException{
  try{
     Class c2=copyRec.getClass();
     Field[] fds=this.getClass().getFields();
     for(int i=0;i<fds.length;i++){
           if(!String.valueOf(fds[i].getType()).equals("int"))
             fds[i].set(this,c2.getField(fds[i].getName()).get(copyRec));
     }
  }catch(Exception e){
      throw new DBException(isrollback,this,e);
  }
 }



 private void readObject(java.io.ObjectInputStream stream)     throws IOException, ClassNotFoundException{
     Field[] fds=this.getClass().getFields();
    try{
     for(int i=0;i<fds.length;i++){
           if(!String.valueOf(fds[i].getType()).equals("int")){
               fds[i].set(this,stream.readObject());
           }
     }
   }catch(IllegalAccessException ia){
         throw new IOException("IllegalAccessException in wrtie Object DbtableRec ");
   }
 }

 private void writeObject(java.io.ObjectOutputStream stream)     throws IOException{
    Field[] fds=this.getClass().getFields();
    try{
        for(int i=0;i<fds.length;i++){
           if(!String.valueOf(fds[i].getType()).equals("int")){
               stream.writeObject(fds[i].get(this));
           }
        }
    }catch(IllegalAccessException ia){
         throw new IOException("IllegalAccessException in wrtie Object DbtableRec ");
    }
 }

public String dump(){
    StringBuffer sb=new StringBuffer();
    try{
        Field[] fds=this.getClass().getFields();
        for(int i=0;i<fds.length;i++){
            String s=fds[i].getName();
            if((!s.startsWith("pk_"))&&(!s.startsWith("ln_"))){
                    String vv=(String)fds[i].get(this);
                StringBuffer sb2=new StringBuffer();
                    int vl=this.getClass().getField("ln_"+s).getInt(this);
                sb.append(vv+"|");
            }
        }
    }catch(Exception ia){

    }
    return sb.toString();
}



}
