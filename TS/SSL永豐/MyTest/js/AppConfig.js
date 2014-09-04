I18N||alert("Cannot initialize i18n script base.");
var AppConfig={
pageSize:10,
defaultReloadTime:2E4
,path:"/data/",
ext:".csv",
gridPageSize:[10,20,50,100,99999],
getCategoryList:function(b){var a=new Map;
if(b=="1"){

a.put("A0",i18n.getText("SECTOR_TWSE_A0"));

a.put("B0",i18n.getText("SECTOR_TWSE_B0"));

a.put("C0",i18n.getText("SECTOR_TWSE_C0"));

a.put("01",i18n.getText("SECTOR_TWSE_01"));

a.put("02",i18n.getText("SECTOR_TWSE_02"));

a.put("03",i18n.getText("SECTOR_TWSE_03"));

a.put("04",i18n.getText("SECTOR_TWSE_04"));

a.put("05",i18n.getText("SECTOR_TWSE_05"));

a.put("06",i18n.getText("SECTOR_TWSE_06"));

a.put("21",i18n.getText("SECTOR_TWSE_21"));

a.put("22",i18n.getText("SECTOR_TWSE_22"));

a.put("08",i18n.getText("SECTOR_TWSE_08"));

a.put("09",i18n.getText("SECTOR_TWSE_09"));

a.put("10",i18n.getText("SECTOR_TWSE_10"));

a.put("11",i18n.getText("SECTOR_TWSE_11"));

a.put("12",i18n.getText("SECTOR_TWSE_12"));

a.put("24",i18n.getText("SECTOR_TWSE_24"));

a.put("25",i18n.getText("SECTOR_TWSE_25"));

a.put("26",i18n.getText("SECTOR_TWSE_26"));

a.put("27",i18n.getText("SECTOR_TWSE_27"));

a.put("28",i18n.getText("SECTOR_TWSE_28"));

a.put("29",i18n.getText("SECTOR_TWSE_29"));

a.put("30",i18n.getText("SECTOR_TWSE_30"));

a.put("31",i18n.getText("SECTOR_TWSE_31"));

a.put("14",i18n.getText("SECTOR_TWSE_14"));

a.put("15",i18n.getText("SECTOR_TWSE_15"));

a.put("16",i18n.getText("SECTOR_TWSE_16"));

a.put("17",i18n.getText("SECTOR_TWSE_17"));

a.put("18",i18n.getText("SECTOR_TWSE_18"));

a.put("19",i18n.getText("SECTOR_TWSE_19"));

a.put("23",i18n.getText("SECTOR_TWSE_23"));

a.put("20",i18n.getText("SECTOR_TWSE_20"));

a.put("E0",i18n.getText("SECTOR_TWSE_E0"));

a.put("D3",i18n.getText("SECTOR_TWSE_D3"));

a.put("D4",i18n.getText("SECTOR_TWSE_D4"));

a.put("D5",i18n.getText("SECTOR_TWSE_D5"));

a.put("D6",i18n.getText("SECTOR_TWSE_D6"));

a.put("D7",i18n.getText("SECTOR_TWSE_D7"));

a.put("D8",i18n.getText("SECTOR_TWSE_D8"));

a.put("H0",i18n.getText("SECTOR_TWSE_H0"));

a.put("I0",i18n.getText("SECTOR_TWSE_I0"));

a.put("J0",i18n.getText("SECTOR_TWSE_J0"));

a.put("O0",i18n.getText("SECTOR_TWSE_O0"));

a.put("P0",i18n.getText("SECTOR_TWSE_P0"))
}else
{
a.put("B0",i18n.getText("SECTOR_OTC_B0"));

a.put("C0",i18n.getText("SECTOR_OTC_C0"));

a.put("02",i18n.getText("SECTOR_OTC_02"));

a.put("03",i18n.getText("SECTOR_OTC_03"));

a.put("04",i18n.getText("SECTOR_OTC_04"));

a.put("05",i18n.getText("SECTOR_OTC_05"));

a.put("06",i18n.getText("SECTOR_OTC_06"));

a.put("21",i18n.getText("SECTOR_OTC_21"));

a.put("22",i18n.getText("SECTOR_OTC_22"));

a.put("08",i18n.getText("SECTOR_OTC_08"));

a.put("10",i18n.getText("SECTOR_OTC_10"));

a.put("11",i18n.getText("SECTOR_OTC_11"));

a.put("24",i18n.getText("SECTOR_OTC_24"));

a.put("25",i18n.getText("SECTOR_OTC_25"));

a.put("26",i18n.getText("SECTOR_OTC_26"));

a.put("27",i18n.getText("SECTOR_OTC_27"));

a.put("28",i18n.getText("SECTOR_OTC_28"));

a.put("29",i18n.getText("SECTOR_OTC_29"));

a.put("30",i18n.getText("SECTOR_OTC_30"));

a.put("31",i18n.getText("SECTOR_OTC_31"));

a.put("14",i18n.getText("SECTOR_OTC_14"));

a.put("15",i18n.getText("SECTOR_OTC_15"));

a.put("16",i18n.getText("SECTOR_OTC_16"));

a.put("17",i18n.getText("SECTOR_OTC_17"));

a.put("18",i18n.getText("SECTOR_OTC_18"));

a.put("23",i18n.getText("SECTOR_OTC_23"));

a.put("20",i18n.getText("SECTOR_OTC_20"));

a.put("80",i18n.getText("SECTOR_OTC_80"));

a.put("D0",i18n.getText("SECTOR_OTC_D0"))
}
return a}
,
getWarrantList:
function(b){
var a=new Map;
if(b=="1")
{

a.put("D3",i18n.getText("SECTOR_TWSE_D3"));

a.put("D4",i18n.getText("SECTOR_TWSE_D4"));

a.put("D5",i18n.getText("SECTOR_TWSE_D5"));

a.put("D6",i18n.getText("SECTOR_TWSE_D6"));

a.put("D7",i18n.getText("SECTOR_TWSE_D7"));

a.put("D8",i18n.getText("SECTOR_TWSE_D8"))
}else 
a.put("D0",
i18n.getText("SECTOR_OTC_D0"));
return a
},
getIndexList:function(b)
{
var a=new Map;
if(b=="1"){

a.put("00",i18n.getText("INDEX_TITLE_TWSE"));
a.put("01",i18n.getText("INDEX_TI01"));
a.put("50",i18n.getText("INDEX_TI50"));
a.put("51",i18n.getText("INDEX_TI51"));
a.put("52",i18n.getText("INDEX_TI52"));
a.put("53",i18n.getText("INDEX_TI53"));
a.put("54",i18n.getText("INDEX_TI54"));
a.put("02",i18n.getText("INDEX_TI02"));
a.put("03",i18n.getText("INDEX_TI03"));
a.put("34",i18n.getText("INDEX_TI34"));
a.put("06",i18n.getText("INDEX_TI06"));

a.put("08",i18n.getText("INDEX_TI08"));
a.put("10",i18n.getText("INDEX_TI10"));
a.put("15",i18n.getText("INDEX_TI15"));
a.put("16",i18n.getText("INDEX_TI16"));
a.put("17",i18n.getText("INDEX_TI17"));
a.put("09",i18n.getText("INDEX_TI09"));
a.put("19",i18n.getText("INDEX_TI19"));
a.put("20",i18n.getText("INDEX_TI20"));
a.put("21",i18n.getText("INDEX_TI21"));
a.put("04",i18n.getText("INDEX_TI04"));
a.put("05",i18n.getText("INDEX_TI05"));
a.put("22",i18n.getText("INDEX_TI22"));
a.put("23",i18n.getText("INDEX_TI23"));

a.put("24",i18n.getText("INDEX_TI24"));
a.put("25",i18n.getText("INDEX_TI25"));
a.put("26",i18n.getText("INDEX_TI26"));
a.put("27",i18n.getText("INDEX_TI27"));
a.put("36",i18n.getText("INDEX_TI36"));
a.put("37",i18n.getText("INDEX_TI37"));
a.put("38",i18n.getText("INDEX_TI38"));
a.put("39",i18n.getText("INDEX_TI39"));
a.put("40",i18n.getText("INDEX_TI40"));
a.put("41",i18n.getText("INDEX_TI41"));
a.put("42",i18n.getText("INDEX_TI42"));
a.put("43",i18n.getText("INDEX_TI43"));
a.put("28",i18n.getText("INDEX_TI28"));

a.put("29",i18n.getText("INDEX_TI29"));
a.put("30",i18n.getText("INDEX_TI30"));
a.put("31",i18n.getText("INDEX_TI31"));
a.put("32",i18n.getText("INDEX_TI32"));
a.put("35",i18n.getText("INDEX_TI35"));
a.put("33",i18n.getText("INDEX_TI33"))
}else{
a.put("00",i18n.getText("INDEX_TITLE_OTC"));
a.put("01",i18n.getText("INDEX_OI01"));
a.put("50",i18n.getText("INDEX_OI50"));
a.put("51",i18n.getText("INDEX_OI51"));
a.put("02",i18n.getText("INDEX_OI02"));
a.put("05",i18n.getText("INDEX_OI05"));
a.put("06",i18n.getText("INDEX_OI06"));

a.put("16",i18n.getText("INDEX_OI16"));
a.put("17",i18n.getText("INDEX_OI17"));
a.put("09",i18n.getText("INDEX_OI09"));
a.put("19",i18n.getText("INDEX_OI19"));
a.put("20",i18n.getText("INDEX_OI20"));
a.put("21",i18n.getText("INDEX_OI21"));
a.put("22",i18n.getText("INDEX_OI22"));
a.put("23",i18n.getText("INDEX_OI23"));
a.put("24",i18n.getText("INDEX_OI24"));
a.put("25",i18n.getText("INDEX_OI25"));
a.put("11",i18n.getText("INDEX_OI11"));
a.put("12",i18n.getText("INDEX_OI12"));
a.put("13",i18n.getText("INDEX_OI13"))}
a.put("200",i18n.getText("INDEX_SUM200"));
a.put("201",i18n.getText("INDEX_SUM201"));
a.put("110",i18n.getText("INDEX_SUM110"));
a.put("111",i18n.getText("INDEX_SUM111"));
a.put("120",i18n.getText("INDEX_SUM120"));
a.put("121",i18n.getText("INDEX_SUM121"));
a.put("130",i18n.getText("INDEX_SUM130"));
a.put("131",i18n.getText("INDEX_SUM131"));
a.put("140",i18n.getText("INDEX_SUM140"));
a.put("141",i18n.getText("INDEX_SUM141"));
a.put("150",i18n.getText("INDEX_SUM150"));
a.put("151",i18n.getText("INDEX_SUM151"));
a.put("160",i18n.getText("INDEX_SUM160"));
a.put("161",i18n.getText("INDEX_SUM161"));
return a},
getLargeUrlByFuncId:function(b)
{
var a=i18n&&i18n.getLanguage().toLowerCase().substring(0,2)=="zh";
b=mapLarge?mapLarge.get(b):null;
return{title:b.title,url:"http://clear.twse.com.tw:9080/{0}".format(a?b.cht:b.eng)}
},
getEtfUrlByStockId:function(b){
return mapETF?mapETF.get(b):null
},
getAllStocksInfo:function(b,a){
for(var d=[],c=this.getStocksInCategory(b,a),e=0;e<c.length;e++)
{
var h=this.getStockInfo(c[e]);
d.push(h)
}
return d
},

getStocksInCategory:
function(b,a){
var d=[];
if(!a||$.trim(a)=="")
return d;
for(var c=readFile(AppConfig.path+(b=="1"?"TC":"OC")+a+AppConfig.ext).split("\n"),e=0;e<c.length;e++)
{
var h=$.trim(c[e].replace(/\"/g,""));
if(isStockIdFormatValid(h))
d[e]=h
}
return d
},
getStockInfo:function(b)
{
b=readFile(AppConfig.path+b+AppConfig.ext).split(",");
if(b.length<=1)
   return null;
for(var a=0;a<b.length;a++)
b[a]=$.trim(b[a].replace(/\"/g,""));
a=parseFloat(b[8]).toFixed(2);
var d=parseFloat(b[3]).toFixed(2),
    c=parseFloat(b[4]).toFixed(2),
   e=parseFloat(b[1]).toFixed(2),
   h;
   h=a==d&&d!=0?"<span class='raiseUp'>{0}</span>".format(i18n.getText("LIMIT_UP"))
   :a==c&&c!=0?"<span class='goDown'>{0}</span>".format(i18n.getText("LIMIT_DOWN"))
   :getSymbol(e);
   var j=parseFloat(b[5]).toFixed(2),k=parseFloat(b[6]).toFixed(2),i=parseFloat(b[7]).toFixed(2);
   return{stockId:b[0],stockName:b[36],displayTime:b[2],latestTradePrice:a==0?"--":a,upPrice:d,downPrice:c,diffPrice:e,direction:h,change:e==0?"0.00":Math.abs(e).toString(),tradeVolume:b[10],accumulativeTradeVolume:b[9],
bestBidPrice:parseFloat(b[11]).toFixed(2),bestBidVolume:parseFloat(b[12]),bestAskPrice:parseFloat(b[21]).toFixed(2),bestAskVolume:parseFloat(b[22]),openingPrice:j==0?"--":j,highestPrice:k==0?"--":k,lowestPrice:i==0?"--":i,market:b[32],buyInfo:[{price:checkPrice(parseFloat(b[11]).toFixed(2),d,c),quantity:checkQty(parseInt(b[12]))},{price:checkPrice(parseFloat(b[13]).toFixed(2),d,c),quantity:checkQty(parseInt(b[14]))},{price:checkPrice(parseFloat(b[15]).toFixed(2),d,c),quantity:checkQty(parseInt(b[16]))},
{price:checkPrice(parseFloat(b[17]).toFixed(2),d,c),quantity:checkQty(parseInt(b[18]))},{price:checkPrice(parseFloat(b[19]).toFixed(2),d,c),quantity:checkQty(parseInt(b[20]))}],sellInfo:[{price:checkPrice(parseFloat(b[21]).toFixed(2),d,c),quantity:checkQty(parseInt(b[22]))},{price:checkPrice(parseFloat(b[23]).toFixed(2),d,c),quantity:checkQty(parseInt(b[24]))},{price:checkPrice(parseFloat(b[25]).toFixed(2),d,c),quantity:checkQty(parseInt(b[26]))},{price:checkPrice(parseFloat(b[27]).toFixed(2),d,c),
quantity:checkQty(parseInt(b[28]))},{price:checkPrice(parseFloat(b[29]).toFixed(2),d,c),quantity:checkQty(parseInt(b[30]))}]}},getMarketChartUrl:function(b){var a=["http://mrktchrt.twse.com.tw/chart/"];i18n&&i18n.getLanguage().toLowerCase().substring(0,2)=="zh"?a.push(b=="1"?"TSE.png":"OTC.png"):a.push(b=="1"?"TSE_E.png":"OTC_E.png");a.push("?sid="+(new Date).getTime());return a.join("")},

getIndexRawData:function(b){
var a=readFile(AppConfig.path+(b=="1"?"TSEIndex":"OTCIndex")+AppConfig.ext),
d=AppConfig.getIndexList(b);
a=a.split("\r\n");
for(var c=[],e=[],h=[],j=[],k=[],i=0;i<a.length;i++)
if($.trim(a[i]).length!=0){
for(var f=a[i].split('","'),g=0;g<f.length;g++)
f[g]=$.trim(f[g].replace(/\"/g,""));
g=parseInt(f[0]);
var l=d.findIt(g)!=-1?d.get(g):"[undefined]";
f={id:g,name:l,time:f[1],latestPrice:f[2],direction:getSymbol(f[3]),change:f[3].replace(/-/g,""),market:b};
if(g==0)c.push(f);
else if(g>=1&&g<=99){
f.latestPrice=parseFloat(f.latestPrice).toFixed(2);
f.change=parseFloat(f.change).toFixed(2);
b=="2"&&g==51?k.push(f):
e.push(f)
}else 
if(g>=110&&g<=161)
j.push(f);
else g>=200&&g<=201&&h.push(f)
}
return{tradingDate:c[0],marketIndexes:e,tradeStatistics:h,orderStatistics:j,bondIndexes:k}},
getTradingDate:function()
{
return AppConfig.getIndexRawData(true).tradingDate.time
},getSBLS_H33:function(){
for(var b=[],a=readFile(AppConfig.path+"SBLS_H33"+AppConfig.ext).split("\n"),d=0;
d<a.length;d++)
{
var c=a[d];
if(!($.trim(c).length<=0))
{c=c.split('","');
for(var e=0;e<c.length;e++)c[e]=$.trim(c[e].replace(/\"/g,""));
b.push({firmId:c[0],
stockId:c[1],firmName:c[2],stockName:c[3],availableShares:c[4],rateSBL:c[5],rateFee:c[6],comments:c[7]})}}return b},
getSBLSData:function(
){for(var b=[],
a=readFile(AppConfig.path+"SBLS"+AppConfig.ext).split("\n"),d=0;d<a.length;d++){var c=a[d];if(!($.trim(c).length<=0)){c=c.split('","');for(var e=0;e<c.length;e++)c[e]=$.trim(c[e].replace(/\"/g,""));b.push({stockId:c[0],volume:c[1],modifyTime:c[2]})}}return b}};
function getSymbol(b){return b>0?"<span class='raiseUp'>+</span>":"<span class='goDown'>-</span>"}function checkPrice(b,a,d){var c="";b=parseFloat(b).toFixed(2);return c=b==0?"":b==a?"<span class='raiseUp'>{0}</span>&nbsp; {1}".format(i18n.getText("LIMIT_UP"),b):b==d?"<span class='goDown'>{0}</span>&nbsp; {1}".format(i18n.getText("LIMIT_DOWN"),b):b}function checkQty(b){return parseInt(b)>0?b:"&nbsp;"}document.write("<!-- Start of StatCounter Code --\>");document.write("<script type='text/javascript'>");
document.write("var sc_project=3932023;");document.write("var sc_invisible=1;");document.write("var sc_partition=47;");document.write("var sc_click_stat=1;");document.write("var sc_security='44690da6';");document.write("<\/script>");document.write("<script type='text/javascript' src='http://www.statcounter.com/counter/counter.js'><\/script><noscript><div class='statcounter'><a href='http://www.statcounter.com/' target='_blank'><img class='statcounter' src='http://c.statcounter.com/3932023/0/44690da6/1/' alt='free web hit counter' ></a></div></noscript>");
document.write("<!-- End of StatCounter Code --\>");