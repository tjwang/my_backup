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

public class ABC_tUI {
  static int currentCount = 0;
  static String currentSnum = "1476";
  static int range = 0;
  static Hashtable ht ;
  static JLabel jl;
  static Vector ss ;
  static MotherPanel3 mp;
  static MotherPanel3 mp_xd;
  static JScrollPane jp2;
  static JScrollPane jp_xd;
  static String dss;
  static JTextField jtxtf;
 static void loadTargets(String filename)  throws Exception
{
	    ss = new Vector();
	    try
	    {
	       if(Integer.parseInt(filename) > 1000)
	       {
	    	    ss.add(filename); 
	          return ;
	      }
	    }catch(Exception xxe)
	    {
	    }
      BufferedReader d
          = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
      String snum = null;
      while((snum=d.readLine())!=null)
      {
         ss.add(snum.trim());
      }
}
 static public void main(String[] arg)throws Exception{

      loadTargets(arg[0]);
      dss = arg[1];
      
      
      JFrame jf2 = new JFrame("ABC_tUI");
      jp2= new JScrollPane();
 
      JFrame jf_xd = new JFrame("ABC_tUI_Xd");
      jp_xd= new JScrollPane();

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
           	      currentSnum = (String)ss.elementAt(currentCount);
            	    try{
            	      setNewData();
            	    }catch(Exception xe)
            	    {
            	      xe.printStackTrace();
            	    }
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
            	if(currentCount < ss.size()-1)
            	{
            	    currentCount++;
           	      currentSnum = (String)ss.elementAt(currentCount);
            	    try{
            	      setNewData();
            	    }catch(Exception xe)
            	    {
            	      xe.printStackTrace();
            	    }
            	}
            }
         }
      );
      jpp.add(jb2);
      
      jl = new JLabel("      資料");
      jpp.add(jl);
      
      JButton jb3 = new JButton("代號查");
      jb3.addActionListener(
         new  ActionListener()
         {
            public void actionPerformed(ActionEvent e) 
            {
           	    currentSnum = jtxtf.getText();
            	  try{
            	    setNewData();
            	  }catch(Exception xe)
            	  {
            	    xe.printStackTrace();
            	  }
            }
         }
      );
      jpp.add(jb3);

      jtxtf = new JTextField("1476");
      jpp.add(jtxtf);

      Container jc = jf2.getContentPane();
      jc.setLayout(new BoxLayout(jc, BoxLayout.Y_AXIS ) );
      Container jc_xd = jf_xd.getContentPane();
      jc_xd.setLayout(new BoxLayout(jc_xd, BoxLayout.Y_AXIS ) );
 
      currentSnum = (String)ss.elementAt(currentCount);
      setNewData();
      
      jc.add(jp2);
      jc.add(jpp);
      jc_xd.add(jp_xd);
       
      jf2.setSize(850, 700);
 //     jf_xd.setSize(850, 700);

 //     jf_xd.show();
      jf2.show();
}

static void setNewData() throws Exception
{
	   String snum = currentSnum;
	   Ds000_Rec ds000r = new Ds000_Rec();
	   ds000r.snum=snum;
	   ds000r.SelectInto();
	   MPanelSetting ms = null;
	   MPanelSetting ms_xd = null;

	   if(mp!=null)
	   {
	     ms = mp.getSetting();
	     jp2.remove(mp);
	   }

	   if(mp_xd!=null)
	   {
	     ms_xd = mp_xd.getSetting();
	     jp_xd.remove(mp_xd);
	   }
  	 jl.setText(snum+" "+new String(ds000r.sname.getBytes("ISO-8859-1"),"MS950"));

	   mp = new MotherPanel3(2);
	   mp_xd = new MotherPanel3(1);

/******************* Source Data Select******************/
      ABC_sFactory  fac1 = new ABC_sFactory(snum,dss);
      ABC_oFactory  fac2 = new ABC_oFactory(snum,dss);


      LineGroup basicGroup =  LG_Maker.basic(fac1,fac2);
      Line markLine =   LG_Maker.markLine(basicGroup);
      Line mainLine =   LG_Maker.mainLine(basicGroup);

/****************User prepare do *****************/
//  To do something
//      ski5.setupI5(5);
//      ski5_2.setupI5(20);
//      Line var_l = ski5.getVarLine();


// Line data building
      Line[] fac1_lines = new Line[4];
      fac1_lines[0] = mainLine;
      //fac1_lines[1] = mainLine.avg(5);
      //fac1_lines[2] = mainLine.avg(20);

      Line[] fac2_lines = new Line[3];
      Line[] ray = basicGroup.getSubLineArray();
      for(int i=0; i<ray.length&&i<4; i++)
      {
         fac1_lines[i+1] = ray[i];
      }



/*****************Display*************************/

//  Region  1 <Factory1>   
     if( fac1_lines[0] instanceof KLine)
     {
         mp.addPainting(new KPainting((KLine)fac1_lines[0]),"Main", 0);
     } else
     {
         mp.addPainting(new MAPainting(fac1_lines[0],new Color(0,0,150)),"Main",0);
     }
      mp.addPainting(new MAPainting(fac1_lines[1],new Color(150,0,0)),"MA3",0);
      mp.addPainting(new MAPainting(fac1_lines[2],new Color(0,150,0)),"MA8",0);
      mp.addPainting(new MAPainting(fac1_lines[3],new Color(0,150,0)),"MA13",0);
//    mp.addPainting(new VPainting(i5f.culKLine().getVol(),new Color(0,150,0)),"Val",0);

//  Region  2 <Factory1>    
/*
      if( fac2_lines[0] instanceof KLine)
     {
        mp.addPainting(new KPainting((KLine)fac2_lines[0]),"fac2-0", 1);
     } else
     {
         mp.addPainting(new MAPainting(fac2_lines[0],new Color(0,0,150)),"fac2-0",1);
     }
*/
      mp.addPainting(new VPainting(markLine,new Color(200,200,200)),"fac2-1",1);
//      mp.addPainting(new MAPainting(fac2_lines[0].div(fac2_lines[0].constant(fac2_lines[0].getMax())),new Color(150,0,0)),"fac2-1",1);
//      mp.addPainting(new MAPainting(fac2_lines[2].div(fac2_lines[0].constant(fac2_lines[0].getMax())),new Color(0,150,0)),"fac2-2",1);


//  Region  3 <X Domain> 
/*   
    KLine mkl = (KLine)fac1_lines[0];
    Line cl = mkl.getClose();
    Line vl = mkl.getVol();
    mp_xd.addPainting(new PoPainting(cl.transTo(vl),new Color(200,0,0)),"XD",0);
*/

    jp2.getViewport().add(mp);
    mp.restoreSetting(ms);
    jp2.validate();
/*
    jp_xd.getViewport().add(mp_xd);
    mp_xd.restoreSetting(ms_xd);
    jp_xd.validate();
*/
}
 

}
