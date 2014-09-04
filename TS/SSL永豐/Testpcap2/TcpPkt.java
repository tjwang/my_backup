import java.io.*;
import java.net.*;
import java.util.*;

public class TcpPkt {
   int src_port;
   int dst_port;
   int SeqNum;
   int AckNum;
   int headerLen;
   byte[] data;
   TcpPkt()
   {
     src_port = 0;
     dst_port = 0;
     SeqNum = 0;
     AckNum = 0;
     headerLen = 0; 
   }
   public void readIn(DataInputStream os, int size)throws Exception
   {
   	  src_port = os.readInt();  size -= 4;
   	  dst_port = src_port & 0x00ffff;
   	  src_port >>= 16;
//   	  System.out.println("srcport "+ src_port + " dst_port "+ dst_port );
   	  SeqNum =PcapPkt.readInt(os);   size -= 4;
   	  AckNum = PcapPkt.readInt(os);   size -= 4;
   	  headerLen = os.readInt(); size -= 4;
   	  headerLen = 0x0f & (headerLen >> 28);
   	  for(int i =4 ; i < headerLen; i++)
   	  {
   	      os.readInt(); size -= 4;
   	  } 
//  	  System.out.println("headerLen : "+ headerLen);
//    	  System.out.println("tcpdata : "+ size);
   	  if(size > 0)
   	  {
   	    data = new byte[size];
   	    os.read(data, 0 , size);
   	  } else
   	  {
   	     data = null;
   	  }
   }
   
   public void writeData(OutputStream os)throws Exception
   {
      if(data!=null)
       os.write(data);
   }

}
