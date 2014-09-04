import stock.fight.*;
import stock.db.*;
import stock.sandy.*;
import stock.app.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Test62 {
 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 static JLabel jl;
  static Vector m ;
  static MotherPanel mp2;
  static JScrollPane jp2;
static public void main(String[] arg)throws Exception{

      m = new Vector();
      PMname_Rec pmr = new PMname_Rec();
      Enumeration e = pmr.SelectBySQL("select * from pmname where type='"+arg[0]+"' order by mnum");
      while(e.hasMoreElements())
      {
         m.add(e.nextElement());
      }
      JFrame jf2 = new JFrame("賺大錢卷商均買量");
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
      jl = new JLabel("      資料");
      jpp.add(jl);
      
      Container jc = jf2.getContentPane();
      jc.setLayout(new BoxLayout(jc, BoxLayout.Y_AXIS ) );
      setNewData();
      jc.add(jp2);
      jc.add(jpp);
       
      jf2.setSize(850, 700);
      jf2.show();



}
static void setNewData()
{
	try{
	 if(mp2!=null)
	 jp2.remove(mp2);
	 
	 PMname_Rec pmr = (PMname_Rec)m.elementAt(currentCount);
	 jl.setText(pmr.mnum+" "+new String(pmr.mname.getBytes("ISO-8859-1"),"MS950"));
	  
   mp2 = new MotherPanel();
   Test62FBVL   fbvl = new Test62FBVL(pmr.mnum);
//   KPainting baseLine2 = new KPainting(fbvl.culKLine());
//   baseLine2.setPaintable(false);
//   mp2.addPainting(baseLine2);
   mp2.addPainting(new MAPainting(fbvl.culMALine(1),new Color(150,0,0)));
   mp2.addPainting(new MAPainting(fbvl.culMALine(8),new Color(0,150,0)));
   mp2.addPainting(new MAPainting(fbvl.culMALine(21),new Color(0,0,150)));
   mp2.addPainting(new MAPainting(fbvl.culMALine(33),new Color(0,150,150)));
//   mp2.AutoTuneY();
//   mp2.tuneXGap(2);
   jp2.getViewport().add(mp2);
  }catch(Exception e)
  {
     e.printStackTrace();
  }
}

}