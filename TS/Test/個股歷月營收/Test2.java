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
 static JTextField jtxt;
  static Vector m ;
  static MotherPanel mp2;
  static JScrollPane jp2;
static public void main(String[] arg)throws Exception{

      m = new Vector();
      Ds000attr_Rec ds000_r = new Ds000attr_Rec();
      Enumeration e =null;
        e = ds000_r.SelectBySQL("select * from ds000attr  where ccode = '3' group by ccode");
        
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

      jtxt = new JTextField("3");
      jpp.add(jtxt);

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
	 if(mp2!=null)
	 jp2.remove(mp2);
	 Ds000attr_Rec ds000_r = (Ds000attr_Rec)m.elementAt(currentCount);
	 jl.setText(ds000_r.ccode+" "+new String(ds000_r.category.getBytes("ISO-8859-1"),"MS950"));
	  
   mp2 = new MotherPanel();
   TestLine2   tl = new TestLine2(ds000_r.ccode,1);
   mp2.addPainting(new MAPainting(tl.culMALine(1),new Color(0,150,0)));
   mp2.addPainting(new MAPainting(tl.culMALine(3),new Color(0,0,150)));
   mp2.addPainting(new MAPainting(tl.culMALine(6),new Color(0,150,150)));
   jp2.getViewport().add(mp2);
   jp2.validate();
  }catch(Exception e)
  {
     e.printStackTrace();
  }
}

static void queryData()
{
	try{
      m = new Vector();
      Ds000attr_Rec ds000_r = new Ds000attr_Rec();
      Enumeration e =null;
        e = ds000_r.SelectBySQL("select * from ds000attr  where ccode = '"+jtxt.getText()+"' group by ccode ");
        currentCount = 0;
      while(e.hasMoreElements())
      {
         m.add(e.nextElement());
      }
      setNewData();
  }catch(Exception e)
  {
     e.printStackTrace();
  }
}

}