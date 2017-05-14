var px = -2;

function bewege() {
  setInterval(animating, 50);
}

function animating() {
    if(Math.round(px) == 100)
      px = -2;
    var logo = document.getElementById('logo');
    logo.style.marginLeft = px + "%";
    px += 0.2;
}
