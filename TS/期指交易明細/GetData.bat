:START_GETPAGE1
java GetPage %1 >error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE1
move OptionsDaily_*.zip %TSROOT%"\TS\_期交所_\選擇權每日"
move error.log  %TSROOT%\log\%MyDate%\OptionsDaily.log

:START_GETPAGE2
java GetPage2 %1 >error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE2
move Daily_*.zip %TSROOT%"\TS\_期交所_\期貨每日\"
move error.log  %TSROOT%\log\%MyDate%\FUDaily.log

:START_GETPAGE3
java GetPage3 %1 >error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE3
move Daily_FUT*.csv %TSROOT%"\TS\_期交所_\期貨日報表"
move error.log  %TSROOT%\log\%MyDate%\FUDailyEx.log

:START_GETPAGE4
java GetPage4 %1 >error.log
IF %ERRORLEVEL% NEQ 0  GOTO START_GETPAGE4
move Daily_OPT*.csv %TSROOT%"\TS\_期交所_\選擇權日報表"
move error.log  %TSROOT%\log\%MyDate%\OpDailyEx.log


