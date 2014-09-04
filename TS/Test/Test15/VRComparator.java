import stock.fight.*;
import stock.db.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class VRComparator implements Comparator<VRecord>
{
	  int type ;
	  public VRComparator(int t)
	  {
	     type = t;
	  }
    
    public int compare(VRecord o1, VRecord o2) 
    {
       double v1 = 0;
       double v2 = 0;
       switch(type)
       {
          case 0:
            v1 = o1.diff_TAB;
            v2 = o2.diff_TAB;
            break;
          case 1:
            v1 = o1.diff;
            v2 = o2.diff;
            break;
          case 2:
            v1 = o1.diff_ab;
            v2 = o2.diff_ab;
            break;
          case 3:
            v1 = o1.rpCount;
            v2 = o2.rpCount;
            break;
          case 4:
            v1 = o1.rpMoney;
            v2 = o2.rpMoney;
            break;
          default:  
            v1 = o1.diff_TAB;
            v2 = o2.diff_TAB;
            break;
       }
       if((v1 - v2 < 0.000001) && (v1 - v2 > -0.000001))
       {
           return 0;
       }
       if(v1 < v2) return -1;
       return 1;
    }
    
    public boolean equals(Object obj) 
    {
       return super.equals(obj);
    }
 
	
}