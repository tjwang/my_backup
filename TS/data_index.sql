
CREATE TABLE `pindexs_offical_new` (
  `date` char(8) default NULL,
  `time` int(8) default NULL,
  `Idx_A` float default NULL,
  `Idx_B` float default NULL,
  `Idx_C` float default NULL,
  `Idx_D` float default NULL,
  `Idx_E` float default NULL,
  `Idx_F` float default NULL,
  `Idx_G` float default NULL,
  `Idx_H` float default NULL,
  `Idx_I` float default NULL,
  `Idx_J` float default NULL,
  `Idx_K` float default NULL,
  `Idx_L` float default NULL,
  `Idx_M` float default NULL,
  `Idx_N` float default NULL,
  `Idx_O` float default NULL,
  `Idx_P` float default NULL,
  `Idx_Q` float default NULL,
  `Idx_R` float default NULL,
  `Idx_S` float default NULL,
  `Idx_T` float default NULL,
  `Idx_U` float default NULL,
  `Idx_V` float default NULL,
  `Idx_W` float default NULL,
  `Idx_X` float default NULL,
  `Idx_Y` float default NULL,
  `Idx_Z` float default NULL,
  `Idx_AA` float default NULL,
  `Idx_BB` float default NULL,
  `Idx_CC` float default NULL,
  `Idx_DD` float default NULL,
  `Idx_EE` float default NULL,
  `Idx_FF` float default NULL,
  `Idx_GG` float default NULL,
  `Idx_HH` float default NULL,
  KEY `ipIndexs_offical` (`date`,`time`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO pindexs_offical_new (  date, time,  Idx_A,  Idx_B,  Idx_C,  Idx_D,  Idx_E, 
                                   Idx_F,  Idx_G,   Idx_H,  Idx_I,  Idx_J, Idx_K, Idx_L, 
                                   Idx_M,  Idx_N,   Idx_O,  Idx_P,  Idx_Q, Idx_R, Idx_S, 
                                   Idx_T,  Idx_U,  Idx_V, Idx_W,  Idx_X, Idx_Y,  Idx_Z, 
                                   Idx_AA, Idx_BB,   Idx_CC,  Idx_DD,  Idx_EE,  Idx_FF,
                                   Idx_GG,  Idx_HH)
 select date,  time ,  Idx_A,  Idx_B,  Idx_C,  Idx_D,  Idx_E, 
                                   Idx_F,  Idx_G,   Idx_H,  Idx_I,  Idx_J, Idx_K, Idx_L, 
                                   Idx_M,  Idx_N,   Idx_O,  Idx_P,  Idx_Q, Idx_R, Idx_S, 
                                   Idx_T,  Idx_U,  Idx_V, Idx_W,  Idx_X, Idx_Y,  Idx_Z, 
                                   Idx_AA, Idx_BB,   Idx_CC,  Idx_DD,  Idx_EE,  Idx_FF,
                                   Idx_GG,  Idx_HH from  pindexs_offical
  where date > '20110114' ; 
  
  CREATE TABLE `pidxrp_offical_new` (
  `date` char(8) default NULL,
  `time` int(8) default NULL,
  `BuyCnt` int(11) default NULL,
  `BuyAmt` int(11) default NULL,
  `SellCnt` int(11) default NULL,
  `SellAmt` int(11) default NULL,
  `RpCnt` int(11) default NULL,
  `RpAmt` int(11) default NULL,
  `RpMoney` int(11) default NULL,
  KEY `ipIdxRp_offical` (`date`,`time`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

INSERT INTO pidxrp_offical_new (  date, time,  BuyCnt,  BuyAmt,  SellCnt,  SellAmt,  RpCnt, 
                                   RpAmt,  RpMoney)
 select date, time * 100 as time,  BuyCnt,  BuyAmt,  SellCnt,  SellAmt,  RpCnt, 
                                   RpAmt,  RpMoney  from  pidxrp_offical
  where date < '20110117' ; 
   
   
CREATE TABLE `pldayk_ofcl_new` (
  `snum` char(8) default NULL,
  `date` char(8) default NULL,
  `time` int(11) default NULL,
  `aIdx` float default NULL,
  `eIdx` float default NULL,
  `fIdx` float default NULL,
  `BuyCnt` int(11) default NULL,
  `BuyAmt` int(11) default NULL,
  `SellCnt` int(11) default NULL,
  `SellAmt` int(11) default NULL,
  `RpCnt` int(11) default NULL,
  `RpAmt` int(11) default NULL,
  `RpMoney` int(11) default NULL,
  KEY `ipldaykofcl` (`snum`,`date`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
 
 INSERT INTO pldayk_ofcl_new (  snum, date, time,  aIdx,  eIdx,  fIdx,  BuyCnt,  BuyAmt, 
                                   SellCnt,  SellAmt, RpCnt, RpAmt, RpMoney)
 select snum, date, time,  aIdx,  eIdx,  fIdx,  BuyCnt,  BuyAmt, 
                                   SellCnt,  SellAmt, RpCnt, RpAmt, RpMoney  from  pldayk_ofcl
  where date > '20110114' ; 
                                                               