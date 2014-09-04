import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.pattern.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestCodeGen {
 
   static public void main(String[] arg)throws Exception{
       System.out.println("TestCodeGen By Tjwang");
       File my_dir = new File(arg[0]+".rt");
       System.out.println(my_dir.mkdir());
       String[] fns = new String[3];
       FileOutputStream testUI = new FileOutputStream(arg[0]+".rt"+"/"+arg[0].toUpperCase()+"_tUI.java");
       FileOutputStream testLGM = new FileOutputStream(arg[0]+".rt"+"/"+"LG_Maker.java");
       FileOutputStream testFactory = new FileOutputStream(arg[0]+".rt"+"/"+arg[0].toUpperCase()+"_sFactory.java");
       FileOutputStream testFactory2 = new FileOutputStream(arg[0]+".rt"+"/"+arg[0].toUpperCase()+"_oFactory.java");
       FileOutputStream testStati = new FileOutputStream(arg[0]+".rt"+"/"+arg[0].toUpperCase()+"_tStati.java");
       FileOutputStream testFinder = new FileOutputStream(arg[0]+".rt"+"/"+arg[0].toUpperCase()+"_tFinder.java");
       FileOutputStream testPattern = new FileOutputStream(arg[0]+".rt"+"/"+arg[0].toUpperCase()+"_Pattern.java");
       FileOutputStream snumFile = new FileOutputStream(arg[0]+".rt"+"/snum.txt");
       
       fns[0]=arg[0].toUpperCase()+"_sFactory";
       fns[1]=arg[0].toUpperCase()+"_oFactory";
       fns[2]="StockInfo5";
       UIWriter uiw = new UIWriter(testUI,arg[0].toUpperCase()+"_tUI",fns);
       LGMakerWriter lgw = new LGMakerWriter(testLGM,"LG_Maker",fns);
       
       FactoryWriter fw = new FactoryWriter(testFactory,arg[0].toUpperCase()+"_sFactory");
       FactoryWriter fw2 = new FactoryWriter(testFactory2,arg[0].toUpperCase()+"_oFactory");
       StatiWriter sw = new StatiWriter(testStati,arg[0].toUpperCase()+"_tStati",fns,arg[0].toUpperCase()+"_Pattern");
       FinderWriter findw = new FinderWriter(testFinder,arg[0].toUpperCase()+"_tFinder",fns,arg[0].toUpperCase()+"_Pattern");
       PatternWriter ptnw = new PatternWriter(testPattern,arg[0].toUpperCase()+"_Pattern");
       uiw.output();
       lgw.output();
       fw.output();
       fw2.output();
       sw.output();
       findw.output();
       ptnw.output();
       testLGM.close();
       testUI.close();
       testFactory.close();
       testFactory2.close();
       testStati.close();
       testFinder.close();
       testPattern.close();
       Thread.currentThread().sleep(1000);
       System.out.println("compile.bat "+arg[0]+".rt");

       BufferedReader bir = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("compile.bat "+arg[0]+".rt").getInputStream()));
       String lineData = null;
       while((lineData=bir.readLine())!=null)
       {
           System.out.println(lineData);
       }

      
   }
   
}   