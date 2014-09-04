:START_GETPAGE1
java GetPage %1 >  %TSDATA%\%1\XFSum.%1.txt 2>error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1
move error.log  %TSROOT%\log\%MyDate%\XFSum.log

:START_GETPAGE2
java GetPage2 %1 >  %TSDATA%\%1\XOSum.%1.txt 2>error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE2
move error.log  %TSROOT%\log\%MyDate%\XOSum.log


