load data infile "X:\\Working\\TS\\抓證交所每分資料\\Icmap.txt" into table pICMap FIELDS  TERMINATED by ',' ;
load data infile "X:\\Working\\TS\\抓證交所每分資料\\20070702.rpx" into table pIndexs_offical FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\抓證交所每分資料\\ra.txt" into table pIdxRp_offical FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\抓證交所每分資料\\data.txt" into table pIdxDifRp_offical FIELDS  TERMINATED by '|' ;
load data infile "X:\\Working\\TS\\計算證交所每分資料\\pavgindex_of.dat" into table pavgindex_of FIELDS  TERMINATED by '|' ;
