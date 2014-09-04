java -Xmx1000M CulMoney %1 > %1.txt
java DoSql %1 
del %1.txt
