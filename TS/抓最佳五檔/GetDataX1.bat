cd %TSROOT%\TS\��̨Τ���
java GetXPage %1 1 1> %1.1.txt 2>error.1.log 
copy %1.1.txt  %TSDATA%\%1\fiveRaw.%1.txt 
