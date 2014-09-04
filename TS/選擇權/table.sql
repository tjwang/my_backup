DROP TABLE IF EXISTS `pCPPrice`;
CREATE TABLE `pCPPrice` (
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `date` char(8) default NULL,
  `price` int default NULL,
  `rp` float default NULL,
  `buy` float default NULL,
  `sell` float default NULL,
  `diff` float default NULL,
  `ra` int default NULL,
   KEY `ipcp` (`cp_month`,`type`)
) ;

rename table pcp1 to pcp1_201004 ;
rename table pcp2 to pcp2_201004 ;
rename table pcp3 to pcp3_201004 ;

CREATE TABLE `pcp1` (
  `date` char(8) default NULL,
  `t0001`   float,
   `t9999`  float,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int default NULL,
  `rp` float default NULL,
  `ra` int default NULL,
  `price2` int default NULL,
  `rp2` float default NULL,
  `ra2` int default NULL,
   KEY `ipcp` (`date`,`cp_month`,`type`)
) ;
CREATE TABLE `pcp2` (
  `date` char(8) default NULL,
  `t0001`   float,
   `t9999`  float,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `price` int default NULL,
  `rp` float default NULL,
  `ra` int default NULL,
  `price2` int default NULL,
  `rp2` float default NULL,
  `ra2` int default NULL,
   KEY `ipcp` (`date`,`cp_month`,`type`)
) ;

CREATE TABLE `pcp3` (
  `date` char(8) default NULL,
  `t0001`   float,
   `t9999`  float,
  `cp_month` char(7) default NULL,
  `type` char(1) default NULL,
  `rprice` float default NULL,
  `ra` int default NULL,
   KEY `ipcp` (`date`,`cp_month`,`type`)
) ;

load data infile "X:\\Working\\TS\\ºâ¿ï¾ÜÅv\\data.txt" into table pcp1 FIELDS  TERMINATED by '|' ;
--------------------------------------------------------------------------------

¶m