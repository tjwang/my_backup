rem :START_GETPAGE
rem java GetPage %1 1> %1c.txt  2>error.log
rem IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE
rem 
rem move  %1c.txt X:\Working\Data\data 
rem java DoSql01 %1

call getData.bat %1
call loadData.bat %1