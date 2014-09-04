prepare work:
1. list all info we want to sta, each field give a name

2. partion these field names to several tables (sta table) and use these name as the col name of sta table

3. for each sta table implements a pair pattern, one raw sta pattern , the other sta pattern (extends AbstractSta_Pattern)
  
4. give startdate do follow work: 

Loop 1: for each sta table tlb

java CulReportByPattern 20140701 raw_sta_pattern_for_tlb raw_fd.rpt  
Do Sql: load data infile "X:\\Working\\TS\\TestCodeGen\\ftt.rt\\raw_fd.rpt" into table sta_vol FIELDS  TERMINATED by '|';

Loop 2: for each col of tlb
  java CulReportFeature tabname colname condStr colfunc  >> feature.dat
end Loop2

end Loop 1:
Do Sql: load data infile "X:\\Working\\TS\\TestCodeGen\\ftt.rt\\feature.dat" into table feature_value FIELDS  TERMINATED by '|';

java StaReportByPattern  20140701 pri_pattern_name sec_pattern_name raw_sta.rpt
load data infile "X:\\Working\\TS\\TestCodeGen\\ftt.rt\\vol_sta.info" into table sta_raw FIELDS  TERMINATED by '|';


use some sql to sta probility
select pri_n,sec_n,pri_v,sec_v,count(*) from sta_raw where time > 90500 and time < 132500 group by pri_n,sec_n,pri_v,sec_v order by pri_n,sec_n;
select pri_n,sec_n,pri_v,sec_v,count(*) from sta_raw where time > 90500 and time < 132500 and pri_v != 0 and sec_v != 0 group by pri_n,sec_n,pri_v,sec_v order by pri_n,sec_n;
