package stock.pricer;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;

public interface PPPModel {
     
   public double getAtomGrap();
//   public double getUpProb();
   public double getUpProb(double[] data);
   
}      

