java -Xmx1000M CulM2 %1 > %1.txt
java DoSql2 %1
del %1.txt

