import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Test8 {
 
 static Vector m ;
 static String start_date = "";

 static int currentCount = 0;
 static int range = 0;
 static Hashtable ht ;
 static JLabel jl;
 static MotherPanel mp2;
 static JScrollPane jp2;

 static int display_rang = 100;
static public void main(String[] arg)throws Exception{

      TestKD   skd = new TestKD("2454",arg[0],1);
      KLine bl = skd.culKLine();
      Value v0 = bl.valueAt(bl.length()-11);
       Ds000_Rec xx000r = new Ds000_Rec();
//      Enumeration e = Test2.query(arg[0],String.valueOf(v0.getDateValue())).elements();
      Enumeration e = xx000r.SelectBySQL("select * from ds000 where snum='1715' or snum='2461' ");
      start_date = arg[0];
           System.out.println("xx--<"+start_date+">--");

      m = new Vector();
//      m2 = new Vector();
//       xx000r.snum="1715";
//       xx000r.sname="萬洲";
 //      xx000r.capital="30000";
 //      m.add(xx000r);
      while(e.hasMoreElements())
      {
           Ds000_Rec ds000r =(Ds000_Rec)e.nextElement();
        //   TestKD   skd2 = new TestKD(ds000r.snum,arg[0],1);
         //  KLine kl = skd2.culKLine();  
        // 	 Value v1 = kl.valueAt(kl.length()-11);
        // 	 Value v2 = kl.valueAt(kl.length()-1);
        //   System.out.println("snum "+ds000r.snum+" "+(new String(ds000r.sname.getBytes("ISO8859-1"),"MS950"))+ " "+
        //                ds000r.capital+" OK! -->" + v1.getDateValue());
        //     v1.dump();           
        //     v2.dump();   
        //     System.out.println("diff: "+(v2.getValue()-v1.getValue()));   
            m.add(ds000r);
//            m2.add();
      }
      
      
      JFrame jf2 = new JFrame("My Viewer");
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
//            	if(currentCount > 0)
            	if(display_rang > 50)
            	{
//            	   currentCount--;
            	   display_rang -=10;
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
          //  	if(currentCount < m.size()-1)
            	if(display_rang < 600)
            	{
            	    currentCount++;
            	    display_rang +=10;
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
      System.out.println("xx--><--");
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
	 
//	 Ds000_Rec ds000r  = (Ds000_Rec)m.elementAt(currentCount);
	 Ds000_Rec ds000r  = (Ds000_Rec)m.elementAt(0);
   TestKD   skd2 = new TestKD(ds000r.snum,start_date, "20141231", display_rang);
//     KLine kl = skd2.culKLine();  
	 jl.setText(ds000r.snum+" "+new String(ds000r.sname.getBytes("ISO-8859-1"),"MS950"));
	  
   mp2 = new MotherPanel();
 //  Test62FBVL   fbvl = new Test62FBVL(pmr.mnum);
   mp2.addPainting(new MAPainting(skd2.culMALine(8),new Color(0,150,0)));
   mp2.addPainting(new MAPainting(skd2.culMALine(21),new Color(0,0,150)));
   mp2.addPainting(new MAPainting(skd2.culMALine(33),new Color(0,150,150)));
   jp2.getViewport().add(mp2);
   mp2.restoreSetting(ms);
   jp2.validate();
  }catch(Exception e)
  {
     e.printStackTrace();
  }
}

}