package stock.tool;
import java.util.*;

public class GlobleSetting {
	static public final String ListPath="C:\\Mime\\stock\\stocknum.txt";

  static public String getTSDataPath()
  {
     String s = System.getenv("TSDATA");
     StringBuffer sb = new StringBuffer();
     for(int i = 0; i < s.length();i++)
     {
        char c = s.charAt(i);
        sb.append(c);
        if(c=='\\')
        {
          sb.append(c);
        }
     }
     return sb.toString();
  }
  static public String getTSCodePath()
  {
     String s = System.getenv("TSCODE");
     StringBuffer sb = new StringBuffer();
     for(int i = 0; i < s.length();i++)
     {
        char c = s.charAt(i);
        sb.append(c);
        if(c=='\\')
        {
          sb.append(c);
        }
     }
     return sb.toString();
  }
}
