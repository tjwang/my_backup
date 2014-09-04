import stock.db.*;
import stock.sandy.*	;
import stock.fight.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test52 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 static JLabel jl;
 static JTextField jtf;
//  static Vector m ;
  static MotherPanel mp2;
  static JScrollPane jp2;
static public void main(String[] arg)throws Exception{

     // m = new Vector();
     // PMname_Rec pmr = new PMname_Rec();
     // Enumeration e =null;
     // if(arg.length > 0)
     //    e = pmr.SelectBySQL("select * from pmname2 where type='"+arg[0]+"' order by mnum");
     // else 
     //   e = pmr.SelectBySQL("select * from pmname2  order by mnum");
     //   
     // while(e.hasMoreElements())
     // {
     //    m.add(e.nextElement());
     // }
      JFrame jf2 = new JFrame("Five");
//      mp2.setDisplayParameters(50,5000,0.2,25,100,13);
      jp2= new JScrollPane();
      JPanel jpp= new JPanel();
      jpp.setLayout(new BoxLayout(jpp, BoxLayout.X_AXIS ) );
      jtf = new JTextField("0180");
      jpp.add(jtf);
      JButton jb2 = new JButton("¬d¸ß");
      jb2.addActionListener(
         new  ActionListener()
         {
            public void actionPerformed(ActionEvent e) 
            {
            	    setNewData(jtf.getText());
            }
         }
      );
      jpp.add(jb2);
      jl = new JLabel("      ¸ê®Æ");
      jpp.add(jl);
      
      Container jc = jf2.getContentPane();
      jc.setLayout(new BoxLayout(jc, BoxLayout.Y_AXIS ) );
      setNewData(jtf.getText());
      jc.add(jp2);
      jc.add(jpp);
       
      jf2.setSize(850, 700);
      jf2.show();

}
static void setNewData(String snum)
{
	try{
	 if(mp2!=null)
	 jp2.remove(mp2);
	 
	 Ds000_Rec dsr = new Ds000_Rec();
	 dsr.SelectBySQL("select * from ds000 where snum = '"+snum+"'");
	 jl.setText(dsr.snum+" "+new String(dsr.sname.getBytes("ISO-8859-1"),"MS950"));
	  
   mp2 = new MotherPanel();
   Test5SLine   bl = new Test5SLine(snum,"B");
   Test5SLine   sl = new Test5SLine(snum,"S");
 //  KPainting baseLine2 = new KPainting(bl.culKLine());
//   baseLine2.setPaintable(false);
 //  mp2.addPainting(baseLine2);
   mp2.addPainting(new MAPainting(bl.culMALine(3),new Color(150,0,0)));
   mp2.addPainting(new MAPainting(sl.culMALine(3),new Color(0,150,0)));
//   mp2.addPainting(new MAPainting(fbvl.culMALine(21),new Color(0,0,150)));
//   mp2.addPainting(new MAPainting(fbvl.culMALine(33),new Color(0,150,150)));
 //  mp2.AutoTuneY();
//   mp2.tuneXGap(2);
   jp2.getViewport().add(mp2);
  }catch(Exception e)
  {
     e.printStackTrace();
  }
}

}