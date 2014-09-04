package stock.sandy;
import java.util.*;

public class XaxisFuncByGLIdx implements XaxisFunc
{
	   GraphLine _gl;
     long[] xValueArray;
     
	   public XaxisFuncByGLIdx(GraphLine gl)
	   {
	       _gl = gl;
   	    TreeSet vts = new TreeSet();
        int gs = gl.getSize();
        for(int i=0;i<gs;i++)
        {
           vts.add(new Long(_gl.getPaintDataByIdx(i).getxIdxValue()));
        }
        xValueArray = new long[vts.size()];
        Iterator<Long> i = vts.iterator();
        int idx = 0;
        while(i.hasNext())
        {
            xValueArray[idx]= i.next().longValue();
            idx ++;
        }
	   }

     public long getXValue(int xIdx)
     {
         PaintData pd = _gl.getPaintDataByIdx(xIdx);
         if(pd != null) return pd.getxIdxValue();
         return  0;
     }

     public int  getSize()
     {
         return _gl.getSize();
     } 

     public String getXMark(int xIdx)
     {
     	    PaintData pd = _gl.getPaintDataByIdx(xIdx);
       	  return pd.getXMark();
     }

}