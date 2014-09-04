DROP TABLE IF EXISTS `pmamountinfo_week`;
CREATE TABLE `pmamountinfo_week` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `mnum` char(4) default NULL,
  `Amount` bigint(20) default NULL,
  `MoneyAmt` float default NULL,
  `Avg` float default NULL,
  `Rate` float default NULL,
  KEY `ipsnuminfo` (`snum`,`date`)
) ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080608.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080615.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080622.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080629.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080706.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080713.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080720.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080727.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080803.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080810.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080817.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080824.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080831.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080907.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080914.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080921.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20080928.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081005.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081012.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081019.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081026.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081102.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081109.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081116.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081123.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081130.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081207.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081214.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081221.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20081228.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090104.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090111.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090118.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090125.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090201.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090208.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090215.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090222.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090301.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090308.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090315.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090322.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090329.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090405.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090412.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090419.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090426.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090503.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090510.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090517.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090524.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090531.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090607.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090614.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090621.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090628.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090705.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090712.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090719.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090726.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090802.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090809.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算算卷商資訊\\data20090816.txt" into table pmamountinfo_week FIELDS  TERMINATED by '|' ;

CREATE TABLE `pmamountinfo_week` (
  `date` char(8) default NULL,
  `snum` char(5) default NULL,
  `mnum` char(4) default NULL,
  `Amount` bigint(20) default NULL,
  `MoneyAmt` float default NULL,
  `Avg` float default NULL,
  `Rate` float default NULL,
  KEY `ipsnuminfo` (`snum`,`date`)
) ;
1. 
select *, a.t/ds000.capital as rr from ds000,( select  snum,mnum,sum(Amount) as t,sum(MoneyAmt) as mt , sum(MoneyAmt)/sum(Amount) as avt,avg(Rate) as avr
 from pmamountinfo_week where date > '20090522'  and snum!='9105' group by snum,mnum) as a where ds000.snum=a.snum order by rr desc limit 100;

2.
select aa.*,mname from pmname,(select a.*, ds000.sname,a.t/ds000.capital as rr from ds000,( select  snum,mnum,sum(Amount) as t,sum(MoneyAmt) as mt , sum(MoneyAmt)/sum(Amount) as avt,avg(Rate) as avr
 from pmamountinfo_week where date > '20090522'  and snum='2382' group by snum,mnum) as a where ds000.snum=a.snum) as aa
 where pmname.mnum=aa.mnum order by rr desc;


