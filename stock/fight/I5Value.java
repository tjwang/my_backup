package stock.fight;
import java.io.*;
import stock.db.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class I5Value extends AbstractValue{
 
//    PLDayk_Rec k = null;
    String snum;
    int    date_value;
    ADayInfo5_Field[] _data;
    i5Field[] _idata;
    
    double  avg_value;
    double  mid_value;
    double  m_cnt_value;
    double  m_ra_value;
    double  max_value;
    double  min_value;
    double  var_value;
    double  var_value_ra;
    
    public I5Value(ADayInfo5_Field[] data)
    {
         _data = new ADayInfo5_Field[data.length];
         System.arraycopy(data, 0, _data, 0, _data.length);
         date_value=Integer.parseInt(_data[_data.length-1].date_str);
         setup();
    }
  
    public I5Value(ADayInfo5_Field[] data,int pos,int len)
    {
         _data = new ADayInfo5_Field[len];
         System.arraycopy(data, pos, _data, 0, len);
         date_value=Integer.parseInt(_data[_data.length-1].date_str);
         setup();
    }
    
    public I5Value(Vector<ADayInfo5_Field> data,int pos,int len)
    {
         _data = new ADayInfo5_Field[len];
         for(int i = 0; i<len; i++)
         {
            _data[i] = data.elementAt(pos+i);
         }
         date_value=Integer.parseInt(_data[_data.length-1].date_str);
         setup();
    }

    private void setup()
    {
       Hashtable<Float,i5Field> ht = new Hashtable<Float,i5Field>();
       for(int i = 0;i < _data.length;i++)
       {
       	  Enumeration<PAmountinfo5_Rec> xe = _data[i].info5_Rec();
          while(xe.hasMoreElements())
          {
             PAmountinfo5_Rec fr = xe.nextElement();
             Float fa=new Float(fr.rp);
             i5Field i5f = ht.get(fa);
             if(i5f == null)
             {
                 i5f = new i5Field();
                 i5f.rp = Float.parseFloat(fr.rp);
                 i5f.ra = Integer.parseInt(fr.ra);
                 i5f.cnt = Integer.parseInt(fr.cnt);
                 ht.put(fa,i5f);
             }else
             {
                 i5f.ra += Integer.parseInt(fr.ra);
                 i5f.cnt += Integer.parseInt(fr.cnt);
             }
          }
       } 
       //System.out.println(ht.size());
       _idata = new i5Field[ht.size()];
       Enumeration<i5Field> e = ht.elements();
       int i = 0;
       while(e.hasMoreElements())
       {
         _idata[i] = e.nextElement();
         i++;
       } 
       qsort(_idata,0,_idata.length);
       cul();
    }    

    public int getDateValue(){
         return date_value;
    }
    
    public int getTimeValue(){
         return 0;
    }
    
    public  void dump(){
       System.out.println("date_value:"+date_value+" length:"+_idata.length);
       for(int i = 0;i < _data.length;i++)
       {
          _data[i].dump();        
       } 
       for(int i = 0; i < _idata.length;i++)
       {
          System.out.println("rp:"+_idata[i].rp+" ra:"+_idata[i].ra+" cnt:"+_idata[i].cnt);
       }
       System.out.print("avg_value:"+avg_value);
       System.out.print(" mid_value:"+mid_value);
       System.out.println(" m_cnt_value:"+m_cnt_value);
       System.out.print("m_ra_value:"+m_ra_value);
       System.out.print(" max_value:"+max_value);
       System.out.println(" min_value:"+min_value);
       System.out.print("var_value:"+var_value);
       System.out.println(" var_value_ra:"+var_value_ra);

       System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
    }

    public double getValue(){
         return get_avg_value();    	  
    }

    
    private void cul()
    {
        int total_cnt = 0;
        int total_ra = 0;
        int max_cnt = 0;
        int max_ra = 0;
        double total_money = 0;
        double max_cnt_rp = 0;
        double max_ra_rp = 0;

        min_value = _idata[0].rp;
        max_value = _idata[_idata.length-1].rp;

        for(int i=0;i<_idata.length;i++)
        {
            total_cnt += _idata[i].cnt;
            total_ra += _idata[i].ra;
            total_money += (_idata[i].rp*_idata[i].ra);
            if(_idata[i].cnt > max_cnt)
            {
               max_cnt = _idata[i].cnt;
               max_cnt_rp = _idata[i].rp;
            }
            if(_idata[i].ra > max_ra)
            {
               max_ra = _idata[i].ra;
               max_ra_rp = _idata[i].rp;
            }
        }
        avg_value = total_money / total_ra;
        m_cnt_value = max_cnt_rp;
        m_ra_value = max_ra_rp;
        int mid_cnt = total_cnt/2;
        total_cnt = 0;
        boolean mid_notset_flag = true;
        double sq_dis = 0;
        double sq_dis_ra = 0;
        for(int i=0;i<_idata.length;i++)
        {
            total_cnt += _idata[i].cnt;
            if(total_cnt >= mid_cnt && mid_notset_flag)
            {
               mid_value = _idata[i].rp;
               mid_notset_flag = false;
            }
            sq_dis += (_idata[i].rp - avg_value) * (_idata[i].rp - avg_value) * _idata[i].cnt;
            sq_dis_ra += (_idata[i].rp - avg_value) * (_idata[i].rp - avg_value) * _idata[i].ra;
        }
        var_value = sq_dis / total_cnt;
        var_value_ra = sq_dis / total_ra;
   }
   
    static private void qsort(i5Field[] src_data,int pos,int len)
    {
         int l_idx = 1;
         int r_idx = len-1;
         if(len <= 1) return;
         if(pos >= src_data.length) return;
         while(l_idx<r_idx)
         {
             if(src_data[pos+l_idx].rp < src_data[pos].rp)
             {
                l_idx++;
             }else if(src_data[pos+r_idx].rp > src_data[pos].rp)
             {
                r_idx--;
             }else
             {
                 i5Field tmp_data = src_data[pos+l_idx];
                 src_data[pos+l_idx] = src_data[pos+r_idx];
                 src_data[pos+r_idx] = tmp_data;
                 r_idx--;
                 l_idx++;
             }
         } 
         if(l_idx < len && src_data[pos+l_idx].rp < src_data[pos].rp)
         {
            i5Field tmp_data = src_data[pos];
            src_data[pos] = src_data[pos+l_idx];
            src_data[pos+l_idx] = tmp_data;
            qsort(src_data,pos,l_idx);
            qsort(src_data,pos+l_idx+1,len-l_idx-1);
         } else if(l_idx > 0 && src_data[pos+l_idx-1].rp < src_data[pos].rp)
         {
            i5Field tmp_data = src_data[pos];
            src_data[pos] = src_data[pos+l_idx-1];
            src_data[pos+l_idx-1] = tmp_data;
            qsort(src_data,pos,l_idx-1);
            qsort(src_data,pos+l_idx,len-l_idx);
         }else
         {
            qsort(src_data,pos+1,len-1);
         }
         
    }
    static private class i5Field
    {
        double rp;
        int ra ;
        int cnt;
    }	

    public double  get_avg_value()
    {
        return  avg_value;
    }
    public double  get_mid_value()
    {
        return  mid_value;
    }
    public double  get_m_cnt_value()
    {
        return  m_cnt_value;
    }
    public double  get_m_ra_value()
    {
        return  m_ra_value;
    }
    public double  get_max_value()
    {
        return  max_value;
    }
    public double  get_min_value()
    {
        return  min_value;
    }
    public double  get_var_value()
    {
        return  var_value;
    }
    public double  get_var_value_ra()
    {
        return  var_value_ra;
    }    
}