DROP TABLE IF EXISTS `cmain`;
CREATE TABLE `cmain` (
  `date` char(8) default NULL,
  `snum` char(8) default NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

load data infile "X:\\Working\\TS\\�[�v����\\data.txt" into table cmain FIELDS  TERMINATED by '|' ;
--------------------------------------------------------------------------------

