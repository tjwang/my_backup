import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import stock.fight.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import stock.db.*;
import stock.sandy.*	;
public class Test2 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 static JLabel jl;
 static JTextField jtxt_snum;
 static JTextField jtxt_mnum;
  static Vector s ;
  static Vector m ;
  static MotherPanel2 mp2;
  static JScrollPane jp2;
static public void main(String[] arg)throws Exception{

      m = new Vector();
      s = new Vector();
       Ds000_Rec ds000_r = new Ds000_Rec();
       PMname_Rec mnr = new PMname_Rec();
      Enumeration e =null;
        e = ds000_r.SelectBySQL("select * from ds000  where snum = '8039' order by snum");
      while(e.hasMoreElements())
      {
         s.add(e.nextElement());
      }
      JFrame jf2 = new JFrame("犁Μ");
      jp2= new JScrollPane();
      JPanel jpp= new JPanel();
      jpp.setLayout(new BoxLayout(jpp, BoxLayout.X_AXIS ) );
      JButton jb1 = new JButton("掸");
      jb1.addActionListener(
         new  ActionListener()
         {
            public void actionPerformed(ActionEvent e) 
            {
            	//System.out.println(e.getActionCommand());
            	if(currentCount > 0)
            	{
            	   currentCount--;
            	   setNewData();
            	}
            }
         }
      );
      jpp.add(jb1);
      JButton jb2 = new JButton("掸");
      jb2.addActionListener(
         new  ActionListener()
         {
            public void actionPerformed(ActionEvent e) 
            {
            	if(currentCount < m.size()-1)
            	{
            	    currentCount++;
            	    setNewData();
            	}
            }
         }
      );
      jpp.add(jb2);

      jtxt_snum = new JTextField("2454");
      jpp.add(jtxt_snum);
      

      JButton jb3 = new JButton("琩高");
      jb3.addActionListener(
         new  ActionListener()
         {
            public void actionPerformed(ActionEvent e) 
            {
            	    queryData();
            }
         }
      );
      jpp.add(jb3);

      jl = new JLabel("      戈");
      jpp.add(jl);
      
      Container jc = jf2.getContentPane();
      jc.setLayout(new BoxLayout(jc, BoxLayout.Y_AXIS ) );
      jc.add(jp2);
      jc.add(jpp);
      setNewData(); 
      jf2.setSize(850, 700);
      jf2.show();



}
static void setNewData()
{
	try{
	 MPanelSetting ms = null;
	 if(mp2!=null)
	 {
	   ms = mp2.getSetting();
	   jp2.remove(mp2);
	 }
	 
	 Ds000_Rec ds000_r = (Ds000_Rec)s.elementAt(0);
 //  
	 jl.setText(ds000_r.snum+" "+new String(ds000_r.sname.getBytes("ISO-8859-1"),"MS950"));
  
	 
	  
   mp2 = new MotherPanel2();
   TestLine   tl = new TestLine(ds000_r.snum,"20100101");
 //  
 //  TestSVLine   fsvl = new TestSVLine("20100101", mnr.mnum, ds000_r.snum);
   mp2.addPainting(new MAPainting(tl.culMALine(1),new Color(0,150,0)),"基А絬 1",0);
   mp2.addPainting(new MAPainting(tl.culMALine(5),new Color(0,0,150)),"基А絬 5",0);
   mp2.addPainting(new MAPainting(tl.culMALine(8),new Color(0,150,150)),"基А絬 8",0);
   
   PMname2_Rec mnr = new PMname2_Rec();  
   Enumeration e = mnr.SelectBySQL("select * from pmname2 ");
   while(e.hasMoreElements())
   {
      mnr = (PMname2_Rec)e.nextElement();
      String mname = new String(mnr.mname.getBytes("ISO-8859-1"),"MS950");
      TestXLine   fbvl = new TestXLine("20100101", mnr.mnum, ds000_r.snum);
      if(mnr.type.equals("F"))
      {
         mp2.addPainting(new MAPainting(fbvl.culMALine(1),new Color(150,0,0)),mname,1);
      } else
      {
         mp2.addPainting(new MAPainting(fbvl.culMALine(1),new Color(0,150,0)),mname,1);
      }
   }
   jp2.getViewport().add(mp2);
   jp2.validate();
   mp2.restoreSetting(ms);
  }catch(Exception e)
  {
     e.printStackTrace();
  }
}

static void queryData()
{
	try{
      s = new Vector();
  //    m = new Vector();
  //    PMname_Rec mnr = new PMname_Rec();
      Ds000_Rec ds000_r = new Ds000_Rec();
      Enumeration e =null;
      e = ds000_r.SelectBySQL("select * from ds000  where snum like '"+jtxt_snum.getText()+"%'order by snum");
      while(e.hasMoreElements())
      {
         s.add(e.nextElement());
      }
      /*
      e = mnr.SelectBySQL("select * from pmname2  where mnum like '"+jtxt_mnum.getText()+"%'order by mnum");
      while(e.hasMoreElements())
      {
         m.add(e.nextElement());
      }
      */
      currentCount = 0;
      setNewData();
  }catch(Exception e)
  {
     e.printStackTrace();
  }
}

}