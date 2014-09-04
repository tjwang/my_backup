java CulMoney3 %1 > %1Difra.txt 
java DoSql2 %1
del %1Difra.txt
java CulMoney4 %1 > %1avgIdx.txt 
java DoSql22 %1
del %1avgIdx.txt

