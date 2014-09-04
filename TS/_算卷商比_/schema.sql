create table pMaxAmount (
snum    char(5),
date    char(8),
rp         float,
max_ra      int,
av5_ra        int,
av_ra      int   
);

create table pMRate (
snum    char(5),
date    char(8),
mnumB     char(5),
SumB    int,
mnumS      char(5),
SumS    int,
ratio   float
);

create index ipmrate on pMRate(snum,date);

create table pMRate5 (
snum    char(5),
date    char(8),
mnumB     char(5),
SumB    int,
mnumS      char(5),
SumS    int,
ratio   float
);
create index ipmrate on pMRate5(snum,date);


load data infile "C:\\新資料夾\\上市\\算最大成交量\\data.txt" into table pMaxAmount FIELDS  TERMINATED by '|' ;
