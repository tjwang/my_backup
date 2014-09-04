:START_GETPAGE1
java GetPage2 %1 > %1t.txt 2>error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1

move %1t.txt  %TSDATA%\%1\inoutsum_official.%1.txt 

move error.log  %TSROOT%\log\%MyDate%\inoutsum_official.log
