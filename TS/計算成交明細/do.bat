java -Xmx1000M CulMoney %1 > %1_1r.txt
java stock.tool.FilterFile %1_1r.txt > %1_1.txt
java DoSql01 %1
del %1_1r.txt
del %1_1.txt

java -Xmx1000M CulMoney2 %1 > %1_2.txt         
java DoSql02 %1 
del %1_2.txt  
                              
java -Xmx1000M CulMoney3 %1 > %1_3.txt        
java DoSql03 %1            
del %1_3.txt  

java DoSql05          
         
rem java -Xmx1000M CulMoney4 %1 > %1_4.txt          
rem java DoSql04 %1       
rem del %1_4.txt  
rem                        
rem java -Xmx1000M CulMoney4A %1 >%1_4a.txt           
rem java DoSql04A %1                               
rem del %1_4a.txt  


