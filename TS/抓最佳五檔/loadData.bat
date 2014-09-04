java Dofilter %TSDATA%\%1\fiveRaw.%1.txt > %1.ok
move %1.ok %TSDATA%\%1\five.%1.txt
move error.log %TSDATA%\%1\five.%1.log
java DoSql1 %1
