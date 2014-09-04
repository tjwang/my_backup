import java.io.*;
import java.net.*;
import java.util.*;

public class RtpPkt {
   int version;
   boolean markbit;
   byte[] data;
   int payload_type;
   int seqno;
   int timestamp; 
   RtpPkt(byte[] d)
   {
      data = d;
      version = ((int)0xff & (int)d[0]) >> 6;
      markbit = ((((int)0xff & (int)d[1]) >> 7) > 0);
      payload_type =  (((int)0xff & (int)d[1]) & 0x7f);
      seqno = ((int)0xff & (int)d[2]);
      seqno <<= 8;
      seqno |= ((int)0xff & d[3]);
      timestamp = ((int)0xff & d[4]);
      timestamp <<= 8;
      timestamp |= ((int)0xff & d[5]);
      timestamp <<= 8;
      timestamp |= ((int)0xff & d[6]);
      timestamp <<= 8;
      timestamp |= ((int)0xff & d[7]);
  }

   public void dumpheader()
   {
       System.out.println("payload_type :"+payload_type);
       System.out.println("markbit :"+markbit);
       System.out.println("seqno :"+seqno);
       System.out.println("timestamp :"+timestamp);
  //      System.out.println(data[0] + " "+ data[1] +" "+data[2]+ " " +data[3]);
   }
   
   public byte[] getJPEGBytes()
   {
       byte[] jdata=new byte[data.length-20];
       System.arraycopy(data,20,jdata,0,data.length-20);
       return jdata;
   }
   
}
