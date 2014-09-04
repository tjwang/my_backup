set TSROOT=X:\Working\
set TSDATA=%TSROOT%Data
set TSCODE=%TSROOT%TS

call GetData.bat %1 
call loadData.bat %1 

:EndWork
