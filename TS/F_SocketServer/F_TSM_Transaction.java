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

public class F_TSM_Transaction extends TSM_Transaction{
	 
	 FTT_sFactory fc;
	 
	 public F_TSM_Transaction(FTT_sFactory ffc, int price, int range, String which_code)
	 {
        fc  = ffc;
        init(price, range, which_code);
	 }
   public double getCurrentPrice()
   {
      return fc.getLastValueByCode("TX104");
   }
   

}
