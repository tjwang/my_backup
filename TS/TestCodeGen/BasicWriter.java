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

public abstract class BasicWriter {
    PrintStream osp;
    String moudule_name;
    String _tbl_name; 
    BufferedReader tbl_in; 
    public void setup(OutputStream out, String mn) throws Exception
    {
       osp = new PrintStream(out);
       tbl_in = new BufferedReader(new InputStreamReader(new FileInputStream(_tbl_name)));
       moudule_name = mn;
    }
   
    abstract public void output()throws Exception;
}