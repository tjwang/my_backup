:START_GETPAGE1
java GetPage %1 >error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1
move OptionsDaily_*.zip %TSROOT%"\TS\_�����_\����v�C��"
move error.log  %TSROOT%\log\%MyDate%\OptionsDaily.log

:START_GETPAGE2
java GetPage2 %1 >error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE2
move Daily_*.zip %TSROOT%"\TS\_�����_\���f�C��\"
move error.log  %TSROOT%\log\%MyDate%\FUDaily.log

:START_GETPAGE3
java GetPage3 %1 >error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE3
move Daily_FUT*.csv %TSROOT%"\TS\_�����_\���f�����"
move error.log  %TSROOT%\log\%MyDate%\FUDailyEx.log

:START_GETPAGE4
java GetPage4 %1 >error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE4
move Daily_OPT*.csv %TSROOT%"\TS\_�����_\����v�����"
move error.log  %TSROOT%\log\%MyDate%\OpDailyEx.log


