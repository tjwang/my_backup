import java.io.*;
import java.net.*;
import java.util.*;

public class Dofilter2 {

 static public void main(String[] arg)throws Exception{
   DataInputStream os = new DataInputStream(new FileInputStream(arg[0]));
   int dstport = 0;
   int cidx = arg.length - 1;
   FileOutputStream fos = null;
   PcapPkt.skipPcapFileHeader(os);
   RtpPkt prertp = null;
   int predif = 0;
   int  basestamp = 0;
   long basetime_sec = 0;
   long basetime_usec = 0;
   long my_stamp = 0;
    while(os.available() > 0)
    { 
       PcapPkt pp = PcapPkt.readPcapPkt(os);
	     if(pp != null)
	     {
	       // System.out.println("pp ip Type:"+pp.getIpType());
	       // System.out.println("    ");
	       
	        if(pp.getIpType() == 17 && pp.udppkt.src_port == 6970)
	        {
	           RtpPkt rtp = new RtpPkt(pp.udppkt.data);
	           //rtp.dumpheader();
	           if(rtp.markbit)
	           {
	           	  if(basestamp == 0)
	           	  {
	           	      basestamp = rtp.timestamp;
	           	      basetime_sec = pp.tv_sec;
	           	      basetime_usec = pp.tv_usec;
	           	  }
	           	  {
 	           	      my_stamp = basestamp + ((pp.tv_usec-basetime_usec)+1000000 * (pp.tv_sec-basetime_sec)) * 90000 /1000000;
	           	  }
	           	  System.out.println("my_stamp: "+my_stamp+" pstamp:"+rtp.timestamp+ " diff:"+ (my_stamp - rtp.timestamp));
	              if(prertp!=null)
	              {
	                 int dif = rtp.timestamp - prertp.timestamp;
	                 if(dif != 0)
	                 {
	                   if(predif != dif)
	                   {
	                      System.out.println("tv_sec:"+pp.tv_sec+" tv_usec:"+pp.tv_usec+" "+dif);
	                      prertp.dumpheader();
	                      rtp.dumpheader();
	                   }
  	                 predif = dif;
  	               }
	              }
	              prertp = rtp;
	           }
	           
	        }
	     }
    }
 }
}
