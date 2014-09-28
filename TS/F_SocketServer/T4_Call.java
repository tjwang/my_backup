public class T4_Call {
    public native  void init_Env();
    public native  String init_t4(); 
    public native  String add_acc_ca();
    public native  String future_order(String buy_or_sell, String future_id, 
                                        String price , String amount , String price_type, String ordtype, String octtype);

    public native  String future_cancel(String future_id, String ord_seq , String ord_no, String octtype , String pre_order);

    public String cancelOrdered(OrderedRec or)
    {
       return future_cancel(or.stock_id, or.ord_seq, or.ord_no, or.octype, or.preorder);
    }
    
    //ord_match_flag : 成交類別  0:全部明細 1:未成交 3:委託失敗  5:委託彙總
    public native  String fo_order_qry2(String ord_match_flag);  

    public OrderedRec[] queryOrdered()
    {
       OrderedRec[] data_rec = null;
       String s = fo_order_qry2("0");
       
       
       byte[] respdata = null;
       try
       {
         respdata = s.getBytes("ISO-8859-1");
 //        System.out.println(new String(respdata,"MS950"));
        } catch(Exception xe)
       {
           respdata = s.getBytes();
     //     System.out.println(new String(respdata));
        }
       int base = 44;
       byte[] record_count = new byte[3];
       System.arraycopy(respdata, 40, record_count, 0, 3);
       int array_len = Integer.parseInt("1"+new String(record_count))%1000;
       data_rec = new OrderedRec[array_len];
       for(int i=0;i<array_len;i++)
       {
          data_rec[i] = new OrderedRec();
          base += 14;
          {
             byte[] seq = new byte[6];
             System.arraycopy(respdata, base, seq, 0, 6);
             data_rec[i].ord_seq = (new String(seq)).trim();
             base += 6;
          }
          {
             byte[] ord_no = new byte[6];
             System.arraycopy(respdata, base, ord_no, 0, 6);
             data_rec[i].ord_no = (new String(ord_no)).trim();
             base += 6;
          }
          {
             byte[] add_date = new byte[8];
             System.arraycopy(respdata, base, add_date, 0, 8);
             data_rec[i].add_date = (new String(add_date)).trim();
             base += 8;
          }
             base += 8;//preord_date
             
          {
             byte[] add_time = new byte[6];
             System.arraycopy(respdata, base, add_time, 0, 6);
             data_rec[i].add_time = (new String(add_time)).trim();
             base += 6;
          }

          {
             byte[] match_time = new byte[6];
             System.arraycopy(respdata, base, match_time, 0, 6);
             data_rec[i].match_time = (new String(match_time)).trim();
             base += 6;
          }
          {
             byte[] stock_id = new byte[10];
             System.arraycopy(respdata, base, stock_id, 0, 10);
             data_rec[i].stock_id = (new String(stock_id)).trim();
             base += 10;
          }
          {
             byte[] ord_bs = new byte[1];
             System.arraycopy(respdata, base, ord_bs, 0, 1);
             data_rec[i].ord_bs = new String(ord_bs);
             base += 1;
          }
            base += 3; //trade_type
          {
             byte[] ord_qty = new byte[6];
             System.arraycopy(respdata, base, ord_qty, 0, 6);
             data_rec[i].ord_qty = Integer.parseInt("1"+new String(ord_qty))%100000;
             base += 6;
          }
          {
             byte[] cancel_qty = new byte[6];
             System.arraycopy(respdata, base, cancel_qty, 0, 6);
             data_rec[i].cancel_qty = Integer.parseInt("1"+new String(cancel_qty))%100000;
             base += 6;
          }
          {
             byte[] ord_match_qty = new byte[6];
             System.arraycopy(respdata, base, ord_match_qty, 0, 6);
             data_rec[i].ord_match_qty = Integer.parseInt("1"+new String(ord_match_qty))%100000;
             base += 6;
          }
          base += 12;
          base += 12;
          base += 60;
          {
      //       byte[] type = new byte[1];
    //         System.arraycopy(respdata, base, type, 0, 1);
     //        System.out.println("type:"+new String(type));
             base += 1;
          }
          {
    //         byte[] ord_type = new byte[3];
     //        System.arraycopy(respdata, base, ord_type, 0, 3);
    //         System.out.println("ord_type:"+new String(ord_type));
             base += 3;
          }
          {
             byte[] octype = new byte[1];
             System.arraycopy(respdata, base, octype, 0, 1);
   //          System.out.println("octype:"+new String(octype));
             data_rec[i].octype = new String(octype);
             base += 1;
          }
          {
             byte[] preorder = new byte[1];
             System.arraycopy(respdata, base, preorder, 0, 1);
             data_rec[i].preorder = new String(preorder);
             base += 1;
          }
          {
             byte[] chg_ord_price = new byte[12];
             System.arraycopy(respdata, base, chg_ord_price, 0, 6);
//             System.out.println("chg_ord_price:"+new String(chg_ord_price));
             base += 12;
          }
          {
             byte[] err_code = new byte[4];
             System.arraycopy(respdata, base, err_code, 0, 4);
//             System.out.println("err_code:"+new String(err_code));
             data_rec[i].err_code = new String(err_code);
             base += 4;
          }
//          data_rec[i].dump();
          String ss = new String(respdata, base, respdata.length - base);
          base += ss.indexOf("F0020000664316");
          if(respdata.length - base < 192) break;
       }
       return data_rec;
      
    }

    //type_1 : 0:所有, 1:期貨, 2:選擇權
    //type_2 : 0:明細, 1:彙整
    public native  String fo_unsettled_qry(String type_1, String type_2);
    public UnSettledRec queryUnSettled(String type_1,String type_2)
    {
          UnSettledRec usr = new UnSettledRec();
          byte[] respdata = fo_unsettled_qry("1","0").getBytes();
          int base = 208 + 15;
          if(respdata == null || respdata.length < base)
             return null;
          byte[] btdate = new byte[8];
          System.arraycopy(respdata, base, btdate, 0, 8);
          usr.tdate = new String(btdate);
          base += 8;
          
          byte[] bcode = new byte[10];
          System.arraycopy(respdata, base, bcode, 0, 10);
          usr.code = (new String(bcode)).trim();
          base += 10;
          
          base += 6;
          usr.ord_bs = (respdata[base]=='B');
          base += 1;
          base += 2;
          base += 3;
          base += 3;
          byte[] bvol = new byte[16];
          System.arraycopy(respdata, base, bvol, 0, 16);
          usr.vol = Float.parseFloat((new String(bvol)).trim());
          base += 16;
          byte[] bap = new byte[16];
          System.arraycopy(respdata, base, bap, 0, 16);
          usr.avg_price = Float.parseFloat((new String(bap)).trim());
          base += 16;
          base += 16;
          System.arraycopy(respdata, base, bap, 0, 16);
          usr.cur_price = Float.parseFloat((new String(bap)).trim());
          base += 16;
          System.arraycopy(respdata, base, bap, 0, 16);
          usr.loss = Float.parseFloat((new String(bap)).trim());
          return usr;
    }

   public  String orderFuture(OrderRec or)
   {
   	  or.dump();
      return future_order(or.buy_or_sell, or.code, 
                          or.price, or.amount, or.price_type, or.ordtype, or.octtype);
   }

    static {
       // System.loadLibrary("X:\\Working\\TS\\Test\\TestJNI\\MyJNI.dll");
        System.load("X:\\Working\\TS\\Test\\TestFuture\\TestJNI\\MyJNI.dll");

    }
    public T4_Call()
    {
       init_Env();
       System.out.println(init_t4());
       System.out.println(add_acc_ca());
    }
    
    public static void main(String[] args) {
    	 T4_Call t4_c = new T4_Call();
       UnSettledRec uns_rec=t4_c.queryUnSettled("1","0");
       uns_rec.dump();
       OrderRec my_or = new OrderRec();
       if(!uns_rec.ord_bs)
       {
          my_or.buy_or_sell = "B";
          my_or.price = String.valueOf((int)(uns_rec.avg_price-3));
       } else
       {
          my_or.buy_or_sell = "S";
          my_or.price = String.valueOf((int)(uns_rec.avg_price+3));
       }
       my_or.code =   uns_rec.code;
       my_or.amount = String.valueOf((int)uns_rec.vol);
       System.out.println(t4_c.orderFuture(my_or));
//     	 System.out.println(t4_c.fo_unsettled_qry("1","0"));
//     	 System.out.println(t4_c.future_order("S","","","MXFC3","8015","1","LMT","ROD"," "));
   }
}

