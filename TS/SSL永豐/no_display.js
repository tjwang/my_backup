function no_display(e) {
  if (twin.browser_type=='N' && e.which == 3) {
    alert('���l�q�������U��t�ΡA���±z�����!');
    window.document.location.reload();
    return false;
  } else if (twin.browser_type=='I' && event.button == 2) {
    alert('���l�q�������U��t�ΡA���±z�����!');
    window.location.reload();
    return false;
  } else return true;
}

document.onmousedown=no_display;
if (document.layers) window.captureEvents(Event.MOUSEDOWN);
window.onmousedown=no_display;
