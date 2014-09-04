set /P MyDate=<X:\Working\myDate.txt

java CheckDate_A
IF %ERRORLEVEL% NEQ 0  GOTO EndWork

call GetData.bat %MyDate% A
call loadData.bat %MyDate% A

:EndWork

rem cd X:\Working\TS\¶×²v
rem call do.bat %MyDate%
