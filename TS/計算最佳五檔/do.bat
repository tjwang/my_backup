cd %TSCODE%\抓最佳五檔
java DoSql1 %1
cd %TSCODE%\計算最佳五檔
java DoSql0X %1
java Cul %1 > %1.txt
java DoSql01 %1

