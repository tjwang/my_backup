rem  sunday , saturday
java DoSql1 %1 %2

cd %TSROOT%\TS\算卷商比
call do.bat %1 %2 

cd %TSROOT%\TS\計算每週成交量
call do.bat %1 %2

cd %TSROOT%\TS\計算算卷商資訊
call do.bat %1 %2

cd %TSROOT%\TS\weekDo