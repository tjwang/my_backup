package stock.pricer;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import stock.db.*;
import stock.fight.*;

public class Pricer {
     
     PPPModel _ppm; // product price probability  Model
     double _initp;
     
     public Pricer(PPPModel ppm,double initP)
     {
         _ppm = ppm;
         _initp =  initP;
     }
     
     public PPDMap  estimate(int times)  // price probability Distribution 
     {
        double atomP = _ppm.getAtomGrap();
        double[]  bidPrice = new double[times+1];
        bidPrice[0] = _initp;
        PPDMap ppdMap = new PPDMap();
        runEstim( bidPrice, _initp + atomP, 1.0, ppdMap, 0, times);
        return ppdMap;
     }
     
     void runEstim(double[] bidPrice, double nextPrice, double weight, PPDMap ppdMap, int deep, int leafPos)
     {
           int bpIdx = deep;
           int nbpIdx = ++deep;
           if(nextPrice > bidPrice[bpIdx])
           {
                if(deep < leafPos)
               {
                 bidPrice[nbpIdx] = nextPrice;
                 runEstim(bidPrice, bidPrice[bpIdx], weight * _ppm.getUpProb(bidPrice), ppdMap, deep, leafPos);
                 bidPrice[nbpIdx] = bidPrice[bpIdx];
                 runEstim(bidPrice, bidPrice[bpIdx] - _ppm.getAtomGrap(), weight*(1-_ppm.getUpProb(bidPrice)), ppdMap, deep, leafPos);
               } else
               {
                   bidPrice[nbpIdx] = nextPrice;
                   printPricePath(bidPrice);
                   ppdMap.putPriceProb(nextPrice, weight * _ppm.getUpProb(bidPrice));
                   bidPrice[nbpIdx] = bidPrice[bpIdx];
                   printPricePath(bidPrice);
                   ppdMap.putPriceProb(bidPrice[bpIdx], weight*(1-_ppm.getUpProb(bidPrice)));
               }
           } else
           { 
               if(deep < leafPos)
               {
                   bidPrice[nbpIdx] = bidPrice[bpIdx];
                   runEstim(bidPrice, bidPrice[bpIdx] + _ppm.getAtomGrap(), weight * _ppm.getUpProb(bidPrice), ppdMap, deep, leafPos);
                   bidPrice[nbpIdx] = nextPrice;
                   runEstim(bidPrice, bidPrice[bpIdx], weight * (1 - _ppm.getUpProb(bidPrice)), ppdMap, deep, leafPos);
               } else
               {
                   bidPrice[nbpIdx] = bidPrice[bpIdx];
                   printPricePath(bidPrice);
                   ppdMap.putPriceProb(bidPrice[bpIdx], weight * _ppm.getUpProb(bidPrice));
                   bidPrice[nbpIdx] = nextPrice;
                   printPricePath(bidPrice);
                   ppdMap.putPriceProb(nextPrice, weight*(1-_ppm.getUpProb(bidPrice)));
               }
           }
   
     }
     
     static void printPricePath(double[] p)
     {
  /*
        for(int i = 0; i < p.length; i++)
        {
            System.out.print((float)p[i]+" ");
        }
        System.out.println();
 */
     }
     
     static public void main(String[] arg) throws Exception 
     {
        Pricer  prc = new Pricer(new SimplePPPModel(), 21.6);
        PPDMap pdm =   prc.estimate(20);
        double[] prcdata = pdm.dumpPrice();
        for(int i = 0; i<prcdata.length; i++)
        {
           System.out.println("price :"+(float)prcdata[i]+ " prob:"+(float)pdm.getPriceProb(prcdata[i]));
        }
     }
   
}      

