java -Xmx1000M CulMoney3 %1 > %1.txt
java DoSql03 %1                               
del %1.txt
java -Xmx1000M CulMoney15M %1 > %1_15m.txt
java DoSql03_15M %1 
del %1_15m.txt  
java -Xmx1000M CulMoney4 %1 > %1_av.txt 
java DoSql04 %1                               
del %1_av.txt                            


