cd "X:\Working\TS\計算期貨每日"
java ExtractK %1 > ax.txt
del *.rpt

move ax.txt dataF\ptxdayk.%1.txt 
