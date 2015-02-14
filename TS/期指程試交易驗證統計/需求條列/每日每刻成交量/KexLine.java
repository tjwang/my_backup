import stock.db.*;
import stock.fight.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class KexLine extends KLine{

    public void add(KValue kdv)
    {
       super.add(kdv);
    }
    public KexValue valueAtLast()
    {
    	 if(length() == 0) return null;
       return (KexValue)valueAt(length()-1);
    }    
}