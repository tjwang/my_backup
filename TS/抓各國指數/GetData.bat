:START_GETPAGE2
java GetPage2 %1 %2 > %1l.txt 2>error2.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE2

move %1l.txt  %TSDATA%\%1\wld.%2.%1.txt 
move error2.log  %TSROOT%\log\%1%\wld.%2.log

