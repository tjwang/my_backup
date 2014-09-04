java CheckDate_E
IF %ERRORLEVEL% NEQ 0  GOTO EndWork

set /P MyDate=<X:\Working\myDate.txt
call GetData.bat %MyDate% E
call loadData.bat %MyDate% E

:EndWork
