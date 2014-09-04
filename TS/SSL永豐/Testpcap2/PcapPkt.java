import java.io.*;
import java.net.*;
import java.util.*;

public class PcapPkt {
   long tv_sec;
   long tv_usec;
   int caplen;
   int len;
   int EtherNetType;
   int IpType;
   TcpPkt  tcppkt;
   UdpPkt  udppkt;
   PcapPkt()
   {
  
   }
   static public PcapPkt readPcapPkt(DataInputStream os)throws Exception
   {
      int tcpdataLen;
      PcapPkt pp= new PcapPkt();
      pp.tv_sec = PcapPkt.readInt(os);
      pp.tv_usec = PcapPkt.readInt(os);
      pp.caplen = PcapPkt.readInt(os);
      pp.len = PcapPkt.readInt(os);
      tcpdataLen = pp.caplen;
      tcpdataLen -= pp.skipEtherNetHeader(os);
	    if(pp.EtherNetType != 0x000800)
      {	  
	       for(int i =0; i < tcpdataLen ; i++)
		     {
		        os.read();
		     }
  	     return null;
	    }
      tcpdataLen -= pp.skipIpHeader(os);
	    if(pp.IpType == 0x00000006)
  	  {
	       pp.tcppkt = new TcpPkt();
	       pp.IpType = 0x00000006;
    //     System.out.println("tcpdataLen: "	+tcpdataLen );
         pp.tcppkt.readIn(os, tcpdataLen);
         return pp;
      }else if(pp.IpType == 17)
      {
	       pp.udppkt = new UdpPkt();
	       pp.IpType = 17;
   //      System.out.println("udpdataLen: "	+tcpdataLen );
         pp.udppkt.readIn(os, tcpdataLen);
         return pp;
      } else
      {
	       for(int i =0; i < tcpdataLen ; i++)
		     {
		        os.read();
		     }
	       return null;
      }
   }
   
   static public int skipPcapFileHeader(DataInputStream os)throws Exception
   {
      int magic_num = PcapPkt.readInt(os);
      int major_minor = PcapPkt.readInt(os);
      int thiszone = PcapPkt.readInt(os);
      int sigfigs = PcapPkt.readInt(os);
      int snaplen  = PcapPkt.readInt(os);
      int linktype  = PcapPkt.readInt(os);
      return 24;
   }

   int skipEtherNetHeader(DataInputStream os)throws Exception
   {
   	   int i=0;
       for(i=0;i<12;i++)
         os.read();
	   EtherNetType = 0x00ffff & (os.readShort());
	 //  System.out.println("EtherNetType: "+EtherNetType);
       return 14;
   }
   
   int skipIpHeader(DataInputStream os)throws Exception
   {
   	   int l = os.read();
   	   l = 4 * (0x0f & l);
       for(int i=1;i<l;i++)
	   {
         if(i == 9)
         {         
		    IpType = 0x0000ff & (os.read());
		 } else
         {		 
		    os.read();
		 }
       }
	//   System.out.println("IpType: "+IpType);
	   return l;  
   }
   
   static public int  readInt(DataInputStream os)throws Exception
   {
       return (os.read() & 0x00ff) | ((os.read() & 0x00ff)<<8) | ((os.read() & 0x00ff)<<16) | ((os.read() & 0x00ff)<<24) ;
   }
   
   public int getIpType()
   {
       return IpType;
   }
}
