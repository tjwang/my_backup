load data infile "X:\\Working\\TS\\TestCodeGen\\ftt.rt\\vol.info" into table sta_vol FIELDS  TERMINATED by '|';
load data infile "X:\\Working\\TS\\TestCodeGen\\ftt.rt\\feature.dat" into table feature_value FIELDS  TERMINATED by '|';
load data infile "X:\\Working\\TS\\TestCodeGen\\ftt.rt\\vol_sta.info" into table sta_raw FIELDS  TERMINATED by '|';


select pri_n,sec_n,pri_v,sec_v,count(*) from sta_raw where time > 90500 and time < 132500 group by pri_n,sec_n,pri_v,sec_v order by pri_n,sec_n;
select pri_n,sec_n,pri_v,sec_v,count(*) from sta_raw where time > 90500 and time < 132500 and pri_v != 0 and sec_v != 0 group by pri_n,sec_n,pri_v,sec_v order by pri_n,sec_n;

INSERT INTO sta_raw_pg (pri_n,sec_n,pri_v,sec_v,vcc) select pri_n,sec_n,pri_v,sec_v,count(*) from sta_raw where time > 90500 and time < 132500 group by pri_n,sec_n,pri_v,sec_v order by pri_n,sec_n;
INSERT INTO sta_m_raw_pg (pri_n,sec_n,pri_v,sec_v,vcc) select pri_n,sec_n,pri_v,sec_v,count(*) from sta_m_raw where time > 90500 and time < 132500 group by pri_n,sec_n,pri_v,sec_v order by pri_n,sec_n;

