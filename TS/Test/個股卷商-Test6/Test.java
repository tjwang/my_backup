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
public class Test {
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
        e = ds000_r.SelectBySQL("select * from ds000  where snum = '2454' order by snum");
      while(e.hasMoreElements())
      {
         s.add(e.nextElement());
      }
      e = mnr.SelectBySQL("select * from pmname2  where mnum like '9800'order by mnum");
      while(e.hasMoreElements())
      {
         m.add(e.nextElement());
      }
      JFrame jf2 = new JFrame("個股營收");
//      mp2.setDisplayParameters(50,5000,0.2,25,100,13);
      jp2= new JScrollPane();
      JPanel jpp= new JPanel();
      jpp.setLayout(new BoxLayout(jpp, BoxLayout.X_AXIS ) );
      JButton jb1 = new JButton("上一筆");
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
      JButton jb2 = new JButton("下一筆");
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
      
      jtxt_mnum = new JTextField("9800");
      jpp.add(jtxt_mnum);

      JButton jb3 = new JButton("查詢");
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

      jl = new JLabel("      資料");
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
   PMname_Rec mnr = (PMname_Rec)m.elementAt(currentCount);
	 jl.setText(ds000_r.snum+" "+new String(ds000_r.sname.getBytes("ISO-8859-1"),"MS950"));
	 String mname = new String(mnr.mname.getBytes("ISO-8859-1"),"MS950");
	 
	  
   mp2 = new MotherPanel2();
   TestLine   tl = new TestLine(ds000_r.snum,"20050101");
   TestBVLine   fbvl = new TestBVLine("20050101", mnr.mnum, ds000_r.snum);
   TestSVLine   fsvl = new TestSVLine("20050101", mnr.mnum, ds000_r.snum);
   mp2.addPainting(new MAPainting(tl.culMALine(1),new Color(0,150,0)),"價均線 1",0);
   mp2.addPainting(new MAPainting(tl.culMALine(5),new Color(0,0,150)),"價均線 5",0);
   mp2.addPainting(new MAPainting(tl.culMALine(8),new Color(0,150,150)),"價均線 8",0);
   mp2.addPainting(new MAPainting(fbvl.culMALine(10),new Color(150,0,0)),mname+"均買量",1);
   mp2.addPainting(new MAPainting(fsvl.culMALine(10),new Color(0,150,0)),mname+"均賣量",1);
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
      m = new Vector();
      PMname_Rec mnr = new PMname_Rec();
      Ds000_Rec ds000_r = new Ds000_Rec();
      Enumeration e =null;
      e = ds000_r.SelectBySQL("select * from ds000  where snum like '"+jtxt_snum.getText()+"%'order by snum");
      while(e.hasMoreElements())
      {
         s.add(e.nextElement());
      }
      e = mnr.SelectBySQL("select * from pmname2  where mnum like '"+jtxt_mnum.getText()+"%'order by mnum");
      while(e.hasMoreElements())
      {
         m.add(e.nextElement());
      }
      currentCount = 0;
      setNewData();
  }catch(Exception e)
  {
     e.printStackTrace();
  }
}

}