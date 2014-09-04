import stock.fight.*;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class TRComparator implements Comparator<Try_Rec>
{
	  public TRComparator()
	  {
	  }
    
    public int compare(Try_Rec o1, Try_Rec o2) 
    {
       if(o1.getCreateTime() < o2.getCreateTime()) return -1;
       return 1;
    }
    
    public boolean equals(Object obj) 
    {
       return super.equals(obj);
    }
 
	
}