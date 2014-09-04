import java.io.*;
import java.net.*;
import java.util.*;

public class Dofilter {

 static public void main(String[] arg)throws Exception{
   DataInputStream os = new DataInputStream(new FileInputStream(arg[0]));
   int dstport = 0;
   int cidx = arg.length - 1;
   FileOutputStream fos = null;
   PcapPkt.skipPcapFileHeader(os);
   TreeSet ts = new TreeSet(new PcapPktCmp(cidx));
    while(os.available() > 0)
    { 
       PcapPkt pp = PcapPkt.readPcapPkt(os);
	   if(pp != null)
       ts.add(pp);
       //pp.tcppkt.writeData(fos);
    }
    Iterator<PcapPkt> i = ts.iterator() ;
    while(i.hasNext())
    {
       PcapPkt pp = i.next();
	   if(cidx == 0)
	   {
          if (dstport != pp.tcppkt.src_port)
          {
             if(fos != null)
             {
                fos.close();
             }
             dstport= pp.tcppkt.src_port;
             fos = new FileOutputStream("filter_"+dstport+".txt");
          }
	   } else
	   {
          if (dstport != pp.tcppkt.dst_port)
          {
             if(fos != null)
             {
                fos.close();
             }
             dstport= pp.tcppkt.dst_port;
             fos = new FileOutputStream("filter_"+dstport+".txt");
          }
	   }
       pp.tcppkt.writeData(fos); 
    }
    fos.close();
 }

}
