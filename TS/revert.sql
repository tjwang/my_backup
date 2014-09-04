delete from pAmountNew where date='20100607';
delete from pLast where date='20100607';
delete from pmK where date='20100607';
delete from pamountinfo where date='20100607';
delete from pamountinfo4A where date='20100607';
delete from pamountinfo4 where date='20100607';
delete from pamountinfo3 where date='20100607';
delete from pamountinfo2 where date='20100607';
delete from pmamountinfo_week where date='20100608';
delete from pmamountinfo where date='20100607';
delete from pfive_info where date='20100607';
delete from pfive_infoA where date='20100607';
delete from pfive_infoD where date='20100607';

cd %TSCODE%\抓成交明細
call loadData.bat 20100607  

cd %TSCODE%\當日最大買超除成交量
call do.bat 20100607     

cd %TSCODE%\計算成交明細
call do.bat 20100607  

cd %TSCODE%\計算卷商買賣金額
call do.bat 20100607

cd %TSCODE%\計算算卷商資訊
call do.bat 20100607  

cd %TSCODE%\計算最佳五檔 
call do.bat 20100607
call doAD.bat 20100607

cd %TSCODE%\計算大盤資訊
java CulMoney3 20100607 > 20100607.txt
java DoSql03 20100607                               
del 20100607.txt
