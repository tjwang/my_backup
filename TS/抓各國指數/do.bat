java CheckDate
IF %ERRORLEVEL% NEQ 0  GOTO EndWork

set /P MyDate=<X:\Working\myDate.txt
call GetData.bat %MyDate% S
call loadData.bat %MyDate% S

:EndWork
