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

public class CulClass_Pattern implements Pattern {

    int _len;
    Domain nd;
    float class_weight;
    float _max_value;
    float _min_value;
    int _count ;
    int line_len;
    CulClass_Pattern(int len,float max_value, float min_value)
    { 
    	 nd = Domain.getDomainByName("N");
    	 _len = len;
    	 _max_value = max_value;
    	 _min_value = min_value;
    	 class_weight = (max_value-min_value)/_len;
    	 _count = 0;
    }
    
    public PatternValue find(Line l, int startIdx)
    {
    	  int level_value = (int)((l.valueAt(startIdx).getValue()-_min_value)/class_weight);
    	  DomainValue dv = new DomainValue(nd,level_value);
    	  int i=startIdx+1;
    	  line_len = l.length();
    	  for(i = startIdx+1; i<line_len;i++)
    	  {
    	     if((int)((l.valueAt(i).getValue()-_min_value)/class_weight) != level_value)
    	      break ;
    	  }
    	  _count ++;
        return new  ClassPatternValue(this, l, startIdx, i-1, dv);
    }
    
    public double culValue(PatternValue ptv)
    {
          return (double)(ptv.getEndIdx()+1) / (double)line_len;
    
    }
    
    public void dump(PatternValue ptv)
    {
         Line l = ptv.getMotherLine();
         Value vs = l.valueAt(ptv.getStartIdx());
         Value ve = l.valueAt(ptv.getEndIdx());
         System.out.println(ptv.getDomainValue()+" value: "+
         culValue(ptv)+" range:"+String.format("%1$.2f",vs.getValue())+
         "~"+String.format("%1$.2f",ve.getValue())+" count : "+_count);
    }
    

    
}
