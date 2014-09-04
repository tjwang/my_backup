:START_GETPAGE1
java GetPage %1 > %1cp.txt
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1
move %1cp.txt  %TSDATA%\%1\cpprice.%1.txt 
