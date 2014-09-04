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

public class PatternWriter extends BasicWriter{

  public PatternWriter(OutputStream out, String mn)throws Exception
  {
     _tbl_name="PatternTPL.tbl"; 
     setup(out, mn);
  }
  
  public void output() throws Exception
  {
     String lineData = null;
     while((lineData=tbl_in.readLine())!=null)
     {
         osp.println(lineData.replaceAll("###ModuleName###",moudule_name));
     }
  }

}