java -Xmx1000M CulMoney %1 > %1cc.txt
java stock.tool.FilterFile %1cc.txt > %1c.txt
move  %1c.txt %TSDATA%\dataM 
java DoSql01 %1
del %1cc.txt

