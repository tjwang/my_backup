rem java GetPage2 %1 > %1l.txt 2>error.log
java stock.tool.FilterFile2 %1l.txt > %1ll.txt
java DoSql2 %1

