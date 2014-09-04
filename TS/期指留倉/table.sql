DROP TABLE IF EXISTS `pXFSum`;
CREATE TABLE `pXFSum` (
  `date` char(8) default NULL,
  `kind` char(5) default NULL,
  `type` char(2) default NULL,
  `call` int default NULL,
  `callM` bigint(20) default NULL,
  `put` int default NULL,
  `putM` bigint(20) default NULL,
  `cpsum` int default NULL,
  `cpsumM` bigint(20) default NULL,
  `Tcall` int default NULL,
  `TcallM` bigint(20) default NULL,
  `Tput` int default NULL,
  `TputM` bigint(20) default NULL,
  `Tcpsum` int default NULL,
  `TcpsumM` bigint(20) default NULL,
   KEY `ipXFSum` (`date`,`kind`, type)
) ;

DROP TABLE IF EXISTS `pXOSum`;
CREATE TABLE `pXOSum` (
  `date` char(8) default NULL,
  `kind` char(5) default NULL,
  `type` char(2) default NULL,
  `call` int default NULL,
  `callM` bigint(20) default NULL,
  `put` int default NULL,
  `putM` bigint(20) default NULL,
  `cpsum` int default NULL,
  `cpsumM` bigint(20) default NULL,
  `Tcall` int default NULL,
  `TcallM` bigint(20) default NULL,
  `Tput` int default NULL,
  `TputM` bigint(20) default NULL,
  `Tcpsum` int default NULL,
  `TcpsumM` bigint(20) default NULL,
   KEY `ipXOSum` (`date`,`kind`, type)
) ;

load data infile "X:\\Working\\TS\\ºâ¿ï¾ÜÅv\\data.txt" into table pcp1 FIELDS  TERMINATED by '|' ;
--------------------------------------------------------------------------------

