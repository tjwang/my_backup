set CLASSPATH=%TSROOT%;%TSROOT%stock\lib\mysql.jar;.;%CLASSPATH%
set path="C:\Program Files\WinRAR\";%path%
rem "C:\Program Files\WinRAR\WinRAR.exe" 
rem unzip -o "X:\Working\TS\_�����_\���f�C��\Daily_2012_04_17.zip"
java ExtractK %1 > ax.txt
del *.rpt

move ax.txt  %TSDATA%\%1\ptxdayk.%1.txt 
