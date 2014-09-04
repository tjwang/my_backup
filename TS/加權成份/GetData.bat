:START_GETPAGE1
java GetPage %1 >  %TSDATA%\%1\cmain.%1.txt 2>error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1
move error.log  %TSROOT%\log\%MyDate%\cmain.log



