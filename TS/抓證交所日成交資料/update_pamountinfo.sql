insert into pamountinfo (snum, date, open, close, rate, Avg, Open_upAvg, Open_downAvg, Close_upAvg,Close_downAvg,
Open_upAmt, Open_downAmt,Close_upAmt,Close_downAmt,hand_rate) 
select snum, date, open, rp, 0, (high+low)/2, 0, 0, 0, 0, 0, 0, 0, 0, 0
from plast2 where date='20100107';
 
 
 
 
 
 
 
 
 
 
 
 