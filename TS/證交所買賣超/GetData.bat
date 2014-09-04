:START_GETPAGE1
java GetPage %1 > %1.txt
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1
move %1.txt  %TSDATA%\%1\snuminoutsum.%1.txt 
