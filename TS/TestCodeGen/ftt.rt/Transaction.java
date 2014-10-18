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

public interface Transaction {
   public static final int OP_FAIL = -1;
   public static final int INITED = 0;
   public static final int OPENING = 1;
   public static final int OPENED = 2;
   public static final int CANCELING = 3;
   public static final int OPEN_COVERING = 4;
   public static final int COVERING = 5;
   public static final int CLOSED = 6;
   public static final int CLOSED_ERROR = 7;
   public static final int OP_BUY = 1;
   public static final int OP_SELL = 2;
   
   public int open()throws Exception;
   
   public int cancel()throws Exception;
   
   public int cover(int price)throws Exception;
   
   public float getLossMoney() throws Exception;
   
   public float getLossPoint() throws Exception;
   
   public UnSettledRec getUnSettled() throws Exception;
   
   public int getStatus()throws Exception;
   
   public int getOp();

   public void dump();
}
