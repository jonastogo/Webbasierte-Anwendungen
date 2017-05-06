var schiff1;
var schiff2;

init();
output();

function init(){
    schiff1 = new Schiff("Elvira", "Segler", new Person("Käptn", "Blaubär", "männlich"), "Bärig");
    schiff2 = new Schiff("Fliegender Holländer", "Gruselschiff", new Person("Richard", "Wagner", "männlich"), "Deutsch");
}

function output(){
  document.write("<h3>Schiffe versenken und fliegen lassen</h3>");
  outputSchiff(schiff1);
  outputSchiff(schiff2);
  document.write("<h2>Beide Schiffe sind auf hoher See:</h2>");

  changeHeight(schiff1, 50);
  changeHeight(schiff2, 1000);
  changeHeight(schiff1, 200);
  changeHeight(schiff2, -500);
  changeHeight(schiff1, -100);
  changeHeight(schiff1, -150);
  changeHeight(schiff2, 400);
  changeHeight(schiff2, -1000);
}


function outputSchiff(schiff){
  document.write("<br><b>Schiffsname:</b> " + schiff.name + "<br><b>Schiffmodell:</b> " + schiff.modell + "<br><b>Kapitän:</b> " + schiff.kapitaen.vorname + " " + schiff.kapitaen.nachname + ", " +  schiff.kapitaen.geschlecht + "<br><b>Nationalität:</b> " + schiff.nationalitaet);
  document.write("<br><br>");
}

function changeHeight(schiff, value){
  schiff.hoehe += value;
  document.write(schiff.name + " ==> Tiefenveränderung: " + value + " Meter -> Neue Tiefe: " + schiff.hoehe + " Meter<br>");
}
