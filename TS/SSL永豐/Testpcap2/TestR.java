import java.io.*;
import java.net.*;
import java.util.*;

public class TestR {

 static public void main(String[] arg)throws Exception{
   DataInputStream os = new DataInputStream(new FileInputStream(arg[0]));
   int dstport = 0;
   int cidx = arg.length - 1;
   FileOutputStream fos = new FileOutputStream("testw.bin");
   PcapPkt.skipPcapFileHeader(os);
   int wcount = 0;
    while(os.available() > 0)
    { 
       PcapPkt pp = PcapPkt.readPcapPkt(os);
	     if(pp != null && pp.tcppkt!= null && pp.tcppkt.src_port == 443)
	     {
          fos.write(("pp write "+wcount+" : \n\n\n\n\n").getBytes());
          pp.tcppkt.writeData(fos); 
          fos.write(("\n\n\n\n\n").getBytes());
          wcount++;
       }
       //pp.tcppkt.writeData(fos);
    }
      
     fos.close();
 }

}
