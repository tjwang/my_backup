INSERT INTO pldayk_ofcl (snum,date,time,aIdx,eIdx,fIdx,BuyCnt,BuyAmt,SellCnt,SellAmt,RpCnt,RpAmt,RpMoney)  
select "T000",pIndexs_offical.date,pIndexs_offical.time,Idx_A,Idx_B,Idx_C,BuyCnt,BuyAmt,SellCnt,SellAmt,RpCnt,RpAmt,RpMoney
from pIndexs_offical,pIdxDifRp_offical 
where pIndexs_offical.date = pIdxDifRp_offical.date and pIndexs_offical.time = pIdxDifRp_offical.time;
