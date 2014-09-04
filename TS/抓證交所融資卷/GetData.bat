:START_GETPAGE1
ping 10.0.0.5 -n 2 -w 500
java GetPage2 %1 > %1mg.txt 2>error1.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1
move %1mg.txt  %TSDATA%\%1\Margin.%1.txt 
move error1.log  %TSROOT%\log\%MyDate%\Margin.log

:START_GETPAGE2
ping 10.0.0.5 -n 2 -w 500
java GetPage3 %1 > %1mgm.txt 2>error2.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE2
move %1mgm.txt  %TSDATA%\%1\MarginM.%1.txt 
move error2.log  %TSROOT%\log\%MyDate%\MarginM.log

:START_GETPAGE3
ping 10.0.0.5 -n 2 -w 500
java GetPage4 %1 > %1mgm.txt 2>error2.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE3
move %1mgm.txt  %TSDATA%\%1\MarginOTC.%1.txt 
move error2.log  %TSROOT%\log\%MyDate%\MarginOTC.log
