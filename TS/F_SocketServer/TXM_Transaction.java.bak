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

public class TXM_Transaction extends TM_Transaction{
   public TXM_Transaction(int price, int range1, int range2, String which_code)
   {
       super(price, range1, which_code);
       my_or_b.price = String.valueOf((price-range1)); 
       my_or_s.price = String.valueOf((price+range2)); 
   }
   
}
