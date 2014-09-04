import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import stock.fight.*;

public class UICommit implements Commit{

  public void commit(int price, int qty)
  {
     System.out.println("price:"+price+" qty:"+qty);  
  }
}