:START_GETPAGE1
java GetPage %1 > %1mm.txt 2>error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1
java stock.tool.FilterFile %1mm.txt > %1m.txt
move %1m.txt  %TSDATA%\%1\mamount.%1.txt 
del %1mm.txt


:START_GETPAGE2
java GetPage2 %1 > %1l.txt 2>error2.log
rem IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE2

java stock.tool.FilterFile2 %1l.txt > %1ll.txt
move %1ll.txt  %TSDATA%\%1\last2.%1.txt 
del %1l.txt

move error.log  %TSROOT%\log\%MyDate%\pmamount_yahoo.log
move error2.log  %TSROOT%\log\%MyDate%\plast_yahoo.log

