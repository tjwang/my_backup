var reloadTime=6E4,
commentList=[i18n.getText("MARKET_INDEX_COMMENT_01"),
i18n.getText("MARKET_INDEX_COMMENT_02")],
layoutIndex=[{title:i18n.getText("GRID_INDEX_NAME"),format:"TEXT",property:"name"},
{title:i18n.getText("GRID_LATEST_INDEX"),format:"NUMERIC",property:"latestPrice"},
{title:i18n.getText("GRID_DIRECTION"),format:"SYMBOL",property:"direction"},
{title:i18n.getText("GRID_INDEX_CHANGE"),format:"NUMERIC",property:"change"}],
layoutTrades=[{title:"&nbsp;",format:"TEXT",property:"name"},{title:"&nbsp;",
format:"NUMERIC",property:"latestPrice"}];
$(document).ready(function(){LoadingTag.show();initLayout();var b=getURLParam("market").replace(/#/g,"");b||(b="1");$("#gridTitle1").html(i18n.getText("MARKET_INDEX_TITLE"));$("#gridTitle2").html(i18n.getText("MARKET_STATISTICS_TITLE"));$("#tradingUnit2").html(i18n.getText("TRADING_UNIT"));$("#gridTitle3").html(i18n.getText("BONDS_INDEX_TITLE"));$("#spnReload").html(i18n.getText("RELOAD_INTERVAL").format(reloadTime/1E3));var c=AppConfig.getIndexRawData(b),a=c.tradingDate;$("#title").html(a.time+"&nbsp;&nbsp;"+
a.name);var d=document.getElementById("tblIndex");if((a=document.getElementById("tblStatistics"))&&d){d=new GridRender(d,layoutIndex);d.clear();d.insertMarketIndexes(c.marketIndexes,true);a=new GridRender(a,layoutTrades);a.clear();d=document.createElement("tr");var e=document.createElement("th");e.colSpan=2;e.innerHTML=i18n.getText("MARKET_TRADES_TITLE");d.appendChild(e);a.getTBody().appendChild(d);a.insertTradesData(c.tradeStatistics);d=document.createElement("tr");e=document.createElement("th");
e.colSpan=2;e.innerHTML=i18n.getText("MARKET_ORDERS_TITLE");d.appendChild(e);a.getTBody().appendChild(d);a.insertOrdersData(c.orderStatistics);if(b=="2"){b=c.bondIndexes;if(b.length&&b.length>0){a=document.getElementById("tblBondIndex");a.style.display="";a=new GridRender(a,layoutIndex);a.clear();a.insertMarketIndexes(b,true)}}c=c.marketIndexes[0].time;$(".displayTime").html(c);if(c=document.getElementById("comment")){b=["<span style='font-weight: bold; width: 50%;'>"];b.push(["<ol>"]);for(a=0;a<
commentList.length;a++)b.push("<li>{0}</li>".format(commentList[a]));b.push("</ol>");b.push("</span>");c.innerHTML=b.join(" ")+c.innerHTML}LoadingTag.hide()}});
function setPageReload(){
if(document.getElementById("chkAutoUpdate").checked)
{window.onload();reloadId=setTimeout("setPageReload()",reloadTime)}else clearTimeout(reloadId)};