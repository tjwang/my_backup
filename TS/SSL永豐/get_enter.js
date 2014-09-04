function get_enter(e) {
  var event=e||window.event;
  var Key=event.which||event.keyCode||event.charCode||0;
  if (Key==13) send(document.forms[0]);
  if (document.captureEvents) document.captureEvents(e.keypress);
}

document.onkeypress=get_enter;
window.onkeypress = get_enter;
