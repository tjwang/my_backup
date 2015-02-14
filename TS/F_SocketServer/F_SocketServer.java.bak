import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class F_SocketServer extends Thread{

  int myport ;
  FileOutputStream log_ps = null;
  FTT_sFactory _fac = null;
  static public void main(String[] arg)throws Exception{

     System.out.println("Hello F_Socket Server ");
     F_SocketServer fss = new F_SocketServer(9999);
     F_SocketServer fss2 = new F_SocketServer(8888);
     FTT_sFactory  fss_fac = new FTT_sFactory();
  //   FTT_sFactory  fss2_fac = new FTT_sFactory();
     fss.setFactory(fss_fac);
     fss2.setFactory(fss_fac);
     fss.start();
     fss2.start();
     T4_TransactionManager.SkypeRecving();
     for(;;)
     {
     //	  System.out.println("I am sleeping");
        Thread.currentThread().sleep(3000);
     /*
        Date xdate = new Date();
        if((xdate).getHours() > 14 || (xdate).getHours() < 5)
        {
        	try{
           fss_fac.dump();
           fss2_fac.dump();
          }catch(Exception xe)
          {
             xe.printStackTrace();
          } 
          return ;
        }
        */
     }
    
 }
 
  public F_SocketServer(int pport)
  {
     myport = pport;
     
  }

  void setFactory(FTT_sFactory fac)
  {
     _fac = fac;
  }
  public void run()
  {
     try
     {
        ServerSocket ss = new ServerSocket(myport);
        System.out.println("wait connection");
        Socket cs=null;
//        log_ps = new PrintStream("fss_"+myport+".log."+GMethod.d2s(new Date()));FileOutputStream
        log_ps = new FileOutputStream("fss_"+myport+".log."+GMethod.d2s(new Date()));
        while((cs=ss.accept())!= null)
        {
            System.out.println("connection accpet ");
            InputStream is = cs.getInputStream();
            byte[] rbuffer = new byte[512];
            int rlen = 0;
            try
            {
               while((rlen=is.read(rbuffer))>0)
               {
          //        System.out.println("rlen :"+rlen);
           //       log_ps.println((new String(rbuffer)).trim());
                  log_ps.write(rbuffer);
                  if(_fac != null)
                  {
                     try{
                       _fac.setReadData(rbuffer);
                     }catch(Exception xxe)
                     {
                        xxe.printStackTrace();
                     }
                  }
                  rbuffer = new byte[512]; 
               }
            }catch(Exception xe)
            {
                xe.printStackTrace();
            }
            System.out.println("connection end ");
            System.out.println("wait connection");
        }
     }catch(Exception xxe)
     {
        xxe.printStackTrace();
     } 
   
  }

}