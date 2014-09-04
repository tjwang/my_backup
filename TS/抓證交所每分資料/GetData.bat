:START_GETPAGE1
java GetPage2 %1 > %1rp.txt 2>error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1
move %1rp.txt  %TSDATA%\%1\Indexs_offical.%1.txt 
move error.log  %TSROOT%\log\%MyDate%\Indexs_offical.log

:START_GETPAGE2
java GetPage3 %1 > %1ra.txt 2>error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE2
move %1ra.txt  %TSDATA%\%1\IdxRp_offical.%1.txt 
move error.log  %TSROOT%\log\%MyDate%\IdxRp_offical.log
