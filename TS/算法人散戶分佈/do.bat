java  CulMoney %1 > %1_in.txt
java DoSql %1 
java  CulMoneyOut %1 > %1_out.txt
java DoSqlO %1 
del %1_out.txt
del %1_in.txt


