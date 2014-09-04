java -Xmx1000M CulM2 %1 > data%1.txt
java DoSql2 %1
del data%1.txt
java DoSql1

