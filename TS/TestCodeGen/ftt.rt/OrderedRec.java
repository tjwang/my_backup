public class OrderedRec {
   String ord_seq;
   String ord_no;
   String add_date;
   String add_time;
   String match_time;
   String stock_id;
   String ord_bs;
   int ord_qty;
   int cancel_qty;
   int ord_match_qty;
   String octype;
   String preorder;
   String err_code;
   OrderRec or_source;
   public OrderedRec()
   {
        ord_seq = null;       
        ord_no = null;      
        add_date = null;       
        add_time = null;       
        match_time = null;    
        stock_id = null;   
        ord_bs = null;    
        ord_qty = 0;        
        cancel_qty = 0;    
        ord_match_qty= 0;  
        octype = " ";
        preorder = null;
        err_code = null;
        or_source = null;
   }
   
   boolean queryValid()
   {
      if(ord_qty - cancel_qty - ord_match_qty <= 0)
        return false;
        
       return err_code.substring(0,2).equals("00") || err_code.substring(0,2).equals("PR");
   }
   
   public void dump()
   {
    	System.out.println("ord_seq = " +ord_seq);
    	System.out.println("ord_no = "+ord_no);
    	System.out.println("add_date = "+add_date);
      System.out.println("add_time = "+add_time);
      System.out.println("match_time = "+match_time);
      System.out.println("ord_bs = "+ord_bs);
      System.out.println("ord_qty = "+ord_qty);
      System.out.println("cancel_qty = "+cancel_qty); 
      System.out.println("ord_match_qty = "+ord_match_qty); 
      System.out.println("octype = "+octype); 
      System.out.println("preorder = "+preorder); 
      System.out.println("err_code = "+err_code); 
      System.out.println("------order source--------");
      if(or_source!=null)
      or_source.dump();
  }
}