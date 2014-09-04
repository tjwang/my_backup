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

public class CM_tUI {
  static int currentCount = 0;
  static int range = 0;
  static Hashtable ht ;
  static JLabel jl;
  static Vector ss ;
  static MotherPanel3 mp;
  static JScrollPane jp2;
  static String dss;
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
      
      
      JFrame jf2 = new JFrame("CM_tUI");
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
      
      Container jc = jf2.getContentPane();
      jc.setLayout(new BoxLayout(jc, BoxLayout.Y_AXIS ) );
      setNewData();
      jc.add(jp2);
      jc.add(jpp);
       
      jf2.setSize(850, 700);
      jf2.show();
   /*   
      JScrollPane jp= new JScrollPane();
      jp.getViewport().add(mp);
      jf.getContentPane().add(jp);
      jf.setSize(850, 700);
      jf.show();
      */

}
static void setNewData() throws Exception
{
	   String snum =(String)ss.elementAt(currentCount);
	   Ds000_Rec ds000r = new Ds000_Rec();
	   ds000r.snum=snum;
	   ds000r.SelectInto();
	   MPanelSetting ms = null;
	   if(mp!=null)
	   {
	     ms = mp.getSetting();
	     jp2.remove(mp);
	   }
  	 jl.setText(snum+" "+new String(ds000r.sname.getBytes("ISO-8859-1"),"MS950"));
	   mp = new MotherPanel3(2);

/******************* Source Data Select******************/
      CM_sFactory  fac1 = new CM_sFactory(snum,dss);
      CM_oFactory  fac2 = new CM_oFactory(snum,dss);

      LineGroup basicGroup =  LG_Maker.basic(fac1,fac2);
      Line markLine =   LG_Maker.markLine(basicGroup);
      Line mainLine =   LG_Maker.mainLine(basicGroup);

/****************User prepare do *****************/
//  To do something
//      ski5.setupI5(5);
//      ski5_2.setupI5(20);
//      Line var_l = ski5.getVarLine();


// Line data building
      Line[] fac1_lines = new Line[3];
      fac1_lines[0] = mainLine;
      fac1_lines[1] = mainLine.avg(5);
      fac1_lines[2] = mainLine.avg(20);

      Line[] fac2_lines = new Line[3];
      Line[] ray = basicGroup.getSubLineArray();
      for(int i=0; i<ray.length&&i<3; i++)
      {
         fac2_lines[i] = ray[i];
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
      mp.addPainting(new MAPainting(fac1_lines[1],new Color(150,0,0)),"MA5",0);
      mp.addPainting(new MAPainting(fac1_lines[2],new Color(0,150,0)),"MA20",0);
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
      mp.addPainting(new MAPainting(fac2_lines[0].div(fac2_lines[0].constant(fac2_lines[0].getMax())),new Color(150,0,0)),"fac2-1",1);
      mp.addPainting(new MAPainting(fac2_lines[2].div(fac2_lines[0].constant(fac2_lines[0].getMax())),new Color(0,150,0)),"fac2-2",1);
/*
//  Region  3 <Factory1>    
      mp.addPainting(new KPainting((KLine)fac3_lines[0]),"K", 2);
      mp.addPainting(new MAPainting(fac3_lines[1],new Color(150,0,0)),"MA5",2);
      mp.addPainting(new MAPainting(fac3_lines[2],new Color(0,150,0)),"MA20",2);
//    mp.addPainting(new VPainting(i5f.culKLine().getVol(),new Color(0,150,0)),"Val",2);
*/
    jp2.getViewport().add(mp);
    mp.restoreSetting(ms);
    jp2.validate();
}

}
