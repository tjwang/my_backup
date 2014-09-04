:START_GETPAGE
java GetPage %1 1> %1c.txt  2>error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE

move %1c.txt  %TSDATA%\%1\amount.%1.txt 
move error.log  %TSROOT%\log\%MyDate%\pamount.log
