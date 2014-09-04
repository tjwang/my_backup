set TSROOT=X:\Working\
set TSDATA=%TSROOT%Data
set TSCODE=%TSROOT%TS
set CLASSPATH=%TSROOT%;%TSROOT%stock\lib\mysql.jar;.;%CLASSPATH%

set /P MyDate=<%TSROOT%myDate.txt


rem java CheckDateKaiGi
rem IF %ERRORLEVEL% NEQ 0  GOTO EndWork

java GetPage2 2014 > 2014l.txt 
java Dofilter 2014l.txt > 2014ml.txt
move 2014ml.txt  %TSDATA%\%MyDate%\pmamount2.%MyDate%.txt 
java DoSql2 %MyDate%

rem ­pºâ
rem java DoSql1 20100710
rem call do2.bat 20100716
rem call do2.bat 20100723

rem :EndWork
