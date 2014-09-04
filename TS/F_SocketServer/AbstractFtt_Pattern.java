import stock.fight.*;
import stock.db.*;
import stock.app.*	;
import stock.pattern.*	;
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

public abstract class AbstractFtt_Pattern implements Pattern {

    protected PrintStream info_ps;
    protected boolean isOnlyLogInfo ;
    protected String ptn_name;
    protected int _len;

    abstract public PatternValue find(Line l, int startIdx);
    
    abstract public double culValue(PatternValue ptv);
    
    protected void init(int len,String fn_info)
    {
     	 _len = len;
    	 try{
    	 	  ptn_name = fn_info;
    	    info_ps = new PrintStream(fn_info+".rpt");
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       isOnlyLogInfo = false;

    }
    protected void dump2(PatternValue ptv)
    {
        System.out.println("FixedDistance PatternValue dump:");
        Line l = ptv.getMotherLine();
        for(int i = ptv.getStartIdx(); i <= ptv.getEndIdx(); i++)
        {
             l.valueAt(i).dump();
        }
        System.out.println("Diff :"+(culValue(ptv)));
        System.out.println("Rate :"+(culValue(ptv)/ptv.getMotherLine().valueAt(ptv.getStartIdx()).getValue()));
    }
    
    abstract void info_log(PatternValue ptv);
    
    public void dump(PatternValue ptv)
    {
    	  if(!isOnlyLogInfo)
    	  {
          dump2(ptv);
        } 
        info_log(ptv);
    }
    
    public void setOnlyLogInfo(boolean op)
    {
        isOnlyLogInfo = op;
    }
}
