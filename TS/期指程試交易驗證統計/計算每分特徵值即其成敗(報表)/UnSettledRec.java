public class UnSettledRec {
    boolean ord_bs;
    String tdate;
    String code;
    float vol;
    float avg_price;
    float cur_price;
    float loss;
    public UnSettledRec()
    {
    	 ord_bs = false;
    	 tdate = null;
    	 vol = -1;
       avg_price = -1;
       cur_price = -1;
       loss = -1;
       code = null;
    }
   public void dump()
   {
    	System.out.println("ord_bs = "+ord_bs);
    	System.out.println("tdate = "+tdate);
    	System.out.println("code = "+code);
    	System.out.println("vol = "+vol);
      System.out.println("avg_price = "+avg_price);
      System.out.println("cur_price = "+cur_price);
      System.out.println("loss = "+loss); 
  
   }
}