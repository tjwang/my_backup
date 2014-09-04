import stock.fight.*;
import stock.pattern.*;
import stock.db.*;
import stock.app.*	;
import stock.sandy.*	;
import stock.tool.*	;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ReportV extends AbstractValue {
   
   PReport_Rec _priv_data;
   
   public ReportV(PReport_Rec prr)
   {
      _priv_data = prr;
      
   }
   public int getDateValue()
   {
       return Integer.parseInt(_priv_data.date);
   }

   public int getTimeValue()
   {
       return Integer.parseInt(_priv_data.time);
   }

   public double getValue()
   {
   	   return 0;
   }
   
   public void dump()
   {
      System.out.println(_priv_data.dump());
   }
}
