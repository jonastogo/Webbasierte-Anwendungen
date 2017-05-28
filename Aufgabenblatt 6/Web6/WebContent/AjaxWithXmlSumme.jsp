<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<%
  response.setContentType("text/xml");
  response.setHeader("Cache-Control", "no-cache");
  String zahl1 = request.getParameter( "zahl1" );
  String zahl2 = request.getParameter( "zahl2" );
  String summe = "-";
  long longsumme;

            
  //trim() entfernt alle Leer- und Controllzeichen von einer Zahl
  if( null == zahl2 || 0 >= zahl2.trim().length() ) { 
    zahl2 = "-";
  } else {
    try {
      long i1 = Long.parseLong( zahl1 );
      long i2 = Long.parseLong( zahl2 );
      longsumme = i1 + i2;
      summe = "" +longsumme;
    } catch( Exception ex ) {}
  }
%>
<MeinErgebnis>
  <summe><%= summe %></summe>
</MeinErgebnis>
