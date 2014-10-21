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

public interface TSRunPlan {
  
   public static final int INITED = 0;
   public static final int TRANS = 1;
   public static final int END = 2;

   public int checkStatus(FTT_sFactory fsrc);
   public float getMaxLoss();
   public float getMaxGet();
   public int   getTimeOut();
   public Transaction getTransaction();
   public void setLogPs(PrintStream ps);

}