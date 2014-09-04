import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import stock.fight.*;

public interface Order {
  static public final int SEND_ORDER_NOP = 0;
  static public final int SEND_ORDER_BUY = 1;
  static public final int SEND_ORDER_SELL = 2;

  public boolean setOrder(int op, int price, int qty, Commit ct);

}