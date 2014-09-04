import stock.fight.*;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class VRecord
{
    PAmountTemp_Rec starter;	
    PAmountTemp_Rec ender;
    double diff;	
    double diff_ab;	
    int  rpCount;
	  double rpMoney;
	  double diff_TAB;
    int  capital;
	  
	  void dump()
	  {
	      System.out.println("VRecord Dump --");
	      System.out.println(" Starter: "+starter.dump());
	      System.out.println(" Ender: "+ ender.dump());
	      System.out.println(" rpCount: "+ rpCount);
	      System.out.println(" rpMoney: "+ rpMoney);
	      System.out.println(" diff_ab: "+ diff_ab);
	      System.out.println(" diff: "+ diff);
	      System.out.println(" diff_TAB: "+ diff_TAB);
	  }
	  
}