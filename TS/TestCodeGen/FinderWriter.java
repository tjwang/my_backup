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

public class FinderWriter extends BasicWriter{

  String[] _factoryNames;
  String _pname;
  public FinderWriter(OutputStream out, String mn,String[] fnames, String pname)throws Exception
  {
      _tbl_name="FinderTPL.tbl"; 
  	  _pname = pname;
      _factoryNames = new String[3];
      setup(out, mn);
      for(int i=0;i<3;i++)
      {
         if(i<fnames.length)
         {
            _factoryNames[i] = fnames[i];
         } else
         {
            _factoryNames[i] = fnames[fnames.length-1];
         }
      }
  }
  
  public void output() throws Exception
  {
     String lineData = null;
     while((lineData=tbl_in.readLine())!=null)
     {
         lineData = lineData.replaceAll("###ModuleName###",moudule_name);
         lineData = lineData.replaceAll("###Factory1###",_factoryNames[0]);
         lineData = lineData.replaceAll("###Factory2###",_factoryNames[1]);
         lineData = lineData.replaceAll("###Pattern1###",_pname);
         osp.println(lineData);
     }
  }

}