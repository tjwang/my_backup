import stock.db.*;
import stock.tool.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import stock.fight.*;
//import org.apache.commons.codec.binary.Base64;

public class UIOrder extends JFrame implements Order{

  
  JTextField jprice_real; 
  JLabel jTitle; 
  int sug_price ;
  int cur_price ;
  int option;
  int status;
  Commit cut_cmt;
  
  protected  void Commit()
  {
//    option = ++option%3;
     String data = jprice_real.getText();
     String price = data.substring(0,data.indexOf(','));
     String qty = data.substring(data.indexOf(',')+1);
     cut_cmt.commit(Integer.parseInt(price),Integer.parseInt(qty));
  }
  
  protected void setLabel()
  {
      switch(option)
      {
      	 case Order.SEND_ORDER_NOP:
          jTitle.setText("<html><H1 align=\"center\">   目前價格 <font color=\"black\">"+cur_price
                            +"</font>[<font color=\"black\">觀望</font>]</H1><br></html>");
          return ; 
      	 case Order.SEND_ORDER_BUY:
          jTitle.setText("<html><H1 align=\"center\">   建議價格 <font color=\"red\">"+sug_price
                            +"</font>[<font color=\"red\">買進</font>]</H1><br></html>");
          return ; 
      	 case Order.SEND_ORDER_SELL:
          jTitle.setText("<html><H1 align=\"center\">   建議價格 <font color=\"blue\">"+sug_price
                            +"</font>[<font color=\"blue\">賣出</font>]</H1><br></html>");
          return ;                  
      }
  }
  
  public UIOrder()
  {
      super("下單建議");
      
      sug_price = 7800;
      cur_price = 7850;
      option = 1;
      status = 0;
      JPanel jpp= new JPanel();
      jpp.setLayout(new BoxLayout(jpp, BoxLayout.Y_AXIS ));
      
      jTitle = new JLabel();
      setLabel();
      jpp.add(jTitle);
     
      jprice_real = new JTextField("7800,1");
      jpp.add(jprice_real);
 
      jpp.add(new JLabel("<html><br></html>"));
      
      JButton jb1 = new JButton("確定成交");
      jb1.addActionListener(
         new  ActionListener()
         {
            public void actionPerformed(ActionEvent e) 
            {
            	//System.out.println(e.getActionCommand());
              Commit();
            }
         }
      );
      jpp.add(jb1);
     jpp.add(new JLabel("<html><br></html>"));
      
      Container jc = getContentPane();
      jc.setLayout(new BoxLayout(jc, BoxLayout.Y_AXIS ) );
      jc.add(jpp);
      setSize(300,200);
      setResizable(false); 
  }

  public boolean setOrder(int op, int price, int qty, Commit ct)
  {
     switch(op)
     {
      	 case Order.SEND_ORDER_NOP:
      	   option = op;
           setLabel();
           this.invalidate();
           cut_cmt = ct;
      	   return true;
      	 case Order.SEND_ORDER_BUY:
      	  if(price > cur_price * 0.93 && price < cur_price * 1.07)
      	  {
      	     option = op;
      	     sug_price = price;
             setLabel();
             this.invalidate();
             cut_cmt = ct;
      	     return true;
      	  } else
      	  {
      	  
      	  }  
      	 case Order.SEND_ORDER_SELL:
      	   option = op;
      	   sug_price = price;
           setLabel();
           this.invalidate();
           cut_cmt = ct;
      	   return true;
     }
     return false;
  }
  
  public boolean setCurPrice(int price)
  {
      if(price > 1000 && price < 10000)
      {
         cur_price = price;
      }
      return false;
  }
  
  static public void main(String[] arg)throws Exception
  {
      System.out.println("sender order test");
      UIOrder sof = new UIOrder();
      UICommit cmt = new UICommit();
      sof.setOrder(Order.SEND_ORDER_BUY, 7754, 1, cmt);
      sof.show();
  }
}