set RPTROOT=X:\\Working\\TS\\TestCodeGen\\run_rpt
set classpath=%classpath%;./classes/;.;..

java DoSql "delete from sta_price;"
java CulReportByPattern %1 tx TX084 FTTPrice_Pattern 
java DoSql "load data infile \"%RPTROOT%\\tx.rpt\" into table sta_price FIELDS  TERMINATED by '|';"

del  sta_price.feature 
del  sta_price.ifeature 
java CulReportFeature max_high sta_price 90500 132500 max_high max_hp 1>> sta_price.feature 2>>sta_price.ifeature
java CulReportFeature avg_dif sta_price 90500 132500 avg_dif avg_dif 1>> sta_price.feature 2>>sta_price.ifeature
java CulReportFeature max_dif sta_price 90500 132500 max_dif max_dif 1>> sta_price.feature 2>>sta_price.ifeature
java CulReportFeature min_dif sta_price 90500 132500 min_dif min_dif 1>> sta_price.feature 2>>sta_price.ifeature
java CulReportFeature dif sta_price 90500 132500 dif dif 1>> sta_price.feature 2>>sta_price.ifeature

java DoSql "load data infile \"%RPTROOT%\\sta_price.feature\" into table feature_value FIELDS  TERMINATED by '|';"
java DoSql "load data infile \"%RPTROOT%\\sta_price.ifeature\" into table afeature_value FIELDS  TERMINATED by '|';"
rrem =====================================================================================================================

java DoSql "delete from sta_price;"
java CulReportByPattern %1 tx15 TX084 FTTPrice15M_Pattern 
java DoSql "load data infile \"%RPTROOT%\\tx15.rpt\" into table sta_price FIELDS  TERMINATED by '|';"

del  sta_price.feature 
del  sta_price.ifeature 
java CulReportFeature max_high sta_price 90500 132500 max_high mhp_15 1>> sta_price.feature 2>>sta_price.ifeature
java CulReportFeature avg_dif sta_price 90500 132500 avg_dif adif_15 1>> sta_price.feature 2>>sta_price.ifeature
java CulReportFeature max_dif sta_price 90500 132500 max_dif mdif_15 1>> sta_price.feature 2>>sta_price.ifeature
java CulReportFeature min_dif sta_price 90500 132500 min_dif ndif_15 1>> sta_price.feature 2>>sta_price.ifeature
java CulReportFeature dif sta_price 90500 132500 dif dif_15 1>> sta_price.feature 2>>sta_price.ifeature

java DoSql "load data infile \"%RPTROOT%\\sta_price.feature\" into table feature_value FIELDS  TERMINATED by '|';
java DoSql "load data infile \"%RPTROOT%\\sta_price.ifeature\" into table afeature_value FIELDS  TERMINATED by '|';


==============================================================================================
java DoSql "delete from sta_vol;"
java CulReportByPattern %1 220 220 FTTFD_Pattern 
java DoSql "load data infile \"%RPTROOT%\\220.rpt\" into table sta_vol FIELDS  TERMINATED by '|';"

del  220.feature 
del  220.ifeature 
java CulReportFeature max_high sta_vol 90500 132500 max_high max_hv 1>> 220.feature 2>> 220.ifeature
java CulReportFeature avg_vol sta_vol 90500 132500 avg_vol avg_vol 1>> 220.feature  2>> 220.ifeature
java CulReportFeature max_vol sta_vol 90500 132500 max_vol max_vol 1>> 220.feature  2>> 220.ifeature
java CulReportFeature min_vol sta_vol 90500 132500 min_vol min_vol 1>> 220.feature  2>> 220.ifeature
 
java DoSql "load data infile \"%RPTROOT%\\220.feature\" into table feature_value FIELDS  TERMINATED by '|';"
java DoSql "load data infile \"%RPTROOT%\\220.ifeature\" into table afeature_value FIELDS  TERMINATED by '|';"



java StaReportByPattern %1 200_TX FTTSta_Pattern 200  FTTPriceSta_Pattern TX084
java StaReportByPattern %1 200_TX15 FTTSta_Pattern 200  FTTPriceSta15M_Pattern TX084
java StaReportByPattern %1 TX_TX15 FTTPriceSta_Pattern TX084  FTTPriceSta15M_Pattern TX084
 
java DoSql "delete from sta_raw;"
java DoSql "load data infile \"%RPTROOT%\\200_TX.srpt\" into table sta_raw FIELDS  TERMINATED by '|';"
java DoSql "load data infile \"%RPTROOT%\\200_TX15.srpt\" into table sta_raw FIELDS  TERMINATED by '|';"
java DoSql "load data infile \"%RPTROOT%\\TX_TX15.srpt\" into table sta_raw FIELDS  TERMINATED by '|';"

java DoSql "delete from sta_m_raw;"
java DoSql "load data infile \"%RPTROOT%\\200_TX.srpt_2\" into table sta_m_raw FIELDS  TERMINATED by '|';"
java DoSql "load data infile \"%RPTROOT%\\200_TX15.srpt_2\" into table sta_m_raw FIELDS  TERMINATED by '|';"
java DoSql "load data infile \"%RPTROOT%\\TX_TX15.srpt_2\" into table sta_m_raw FIELDS  TERMINATED by '|';"

java DoSql "delete from sta_raw_pg;"
java DoSql "INSERT INTO sta_raw_pg (pri_n,sec_n,pri_v,sec_v,vcc) select pri_n,sec_n,pri_v,sec_v,count(*) from sta_raw where time > 90500 and time < 132500 group by pri_n,sec_n,pri_v,sec_v order by pri_n,sec_n;"
java DoSql "delete from sta_m_raw_pg;"
java DoSql "INSERT INTO sta_m_raw_pg (pri_n,sec_n,pri_v,sec_v,vcc) select pri_n,sec_n,pri_v,sec_v,count(*) from sta_m_raw where time > 90500 and time < 132500 group by pri_n,sec_n,pri_v,sec_v order by pri_n,sec_n;"
