cd %TSROOT%\TS\§ì³Ì¨Î¤­ÀÉ
java MergeData %1 1> %1.txt 2>error.log &
copy %1.txt  %TSDATA%\%1\fiveRaw.%1.txt 
