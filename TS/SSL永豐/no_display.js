function no_display(e) {
  if (twin.browser_type=='N' && e.which == 3) {
    alert('正泓電腦網路下單系統，謝謝您的支持!');
    window.document.location.reload();
    return false;
  } else if (twin.browser_type=='I' && event.button == 2) {
    alert('正泓電腦網路下單系統，謝謝您的支持!');
    window.location.reload();
    return false;
  } else return true;
}

document.onmousedown=no_display;
if (document.layers) window.captureEvents(Event.MOUSEDOWN);
window.onmousedown=no_display;
