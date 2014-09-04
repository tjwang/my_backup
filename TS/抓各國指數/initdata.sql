DROP TABLE IF EXISTS `wldcode`;
CREATE TABLE `wldcode` (
  `wcode` char(20) default NULL,
  `name` char(20) default NULL,
  `type` char(1) default NULL,
  KEY `wldcode` (`wcode`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
load data infile "X:\\Working\\TS\\抓各國指數\\code.txt" into table wldcode FIELDS  TERMINATED by '|' ;
DROP TABLE IF EXISTS `pwdayk`;
CREATE TABLE `pwdayk` (
  `wcode` char(20) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `open` float default NULL,
  `high` float default NULL,
  `low` float default NULL,
  `close` float default NULL,
  `volume` float default NULL,
  `money` float default NULL,
  KEY `iwldayk` (`wcode`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
 load data infile "X:\\Working\\TS\\抓各國指數\\sdata.txt" into table pwdayk FIELDS  TERMINATED by '|' ;
 