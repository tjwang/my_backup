public class OrderRec {
   String buy_or_sell;
   String code;
   String price;
   String amount;
   String price_type;
   String ordtype;
   String octtype;
   String seq_no;
   public OrderRec()
   {
    	 buy_or_sell = "S";
       code = "MXFC3";
       price = null;
       amount = "1" ;
       price_type ="LMT";
       ordtype = "ROD";
       octtype = " ";
   }
   public void dump()
   {
    	System.out.println("buy_or_sell = " +buy_or_sell);
    	System.out.println("code = "+code);
    	System.out.println("price = "+price);
      System.out.println("amount = "+amount);
      System.out.println("price_type = "+price_type);
      System.out.println("ordtype = "+ordtype);
      System.out.println("octtype = "+octtype); 
   }
}