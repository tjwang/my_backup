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

public class NRec {
	
   String name;
   double value;
	 public NRec(String n,double v)
	 {
	     name = n;
	     value = v;
	 }
	 public String toString()
	 {
	    return name +":"+String.format("%1$.2f",value);
	 }
}