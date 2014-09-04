import java.io.*;
import java.net.*;
import java.util.*;

public class PcapPktCmp implements Comparator<PcapPkt>
{
  int cmpIdx = 0;
  public PcapPktCmp(int ci)
  {
    cmpIdx =  ci;
  }
  public int compare(PcapPkt pp1, PcapPkt pp2) 
  {
    if(cmpIdx == 0)
	{
 	   if(pp1.tcppkt.src_port > pp2.tcppkt.src_port)
 	   {
 	        return 1;
 	   }else if(pp1.tcppkt.src_port < pp2.tcppkt.src_port)
 	   {
   	      return -1;
 	   }else if(pp1.tv_sec > pp2.tv_sec)
 	   {
 	        return 1; 
 	   } else if( pp1.tv_sec < pp2.tv_sec )
 	   {
 	        return -1;
 	   } else if ( pp1.tv_usec > pp2.tv_usec )
 	   {
 	        return 1;
 	   }else
 	   {
 	       return -1;
       } 
    }else
	{
 	   if(pp1.tcppkt.dst_port > pp2.tcppkt.dst_port)
 	   {
 	        return 1;
 	   }else if(pp1.tcppkt.dst_port < pp2.tcppkt.dst_port)
 	   {
   	      return -1;
 	   }else if(pp1.tv_sec > pp2.tv_sec)
 	   {
 	        return 1; 
 	   } else if( pp1.tv_sec < pp2.tv_sec )
 	   {
 	        return -1;
 	   } else if ( pp1.tv_usec > pp2.tv_usec )
 	   {
 	        return 1;
 	   }else
 	   {
 	       return -1;
       } 
	}
 }
 public boolean equals(Object obj) 
 {
   return super.equals(obj);
 }

}
