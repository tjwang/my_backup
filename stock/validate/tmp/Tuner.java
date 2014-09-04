package stock.validate;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;

public abstract class  Tuner extends BaseVObj  {

   int _range ;
   public abstract Line staticTune(Line L) throws Exception;
   public abstract Line dynamicTune(Line L) throws Exception;
   
   public void setTuneBase(int range)
   {
      _range = range;
   }

}      

