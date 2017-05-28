<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<%
  response.setContentType("text/xml");
  response.setHeader("Cache-Control", "no-cache");
  String zahl = request.getParameter( "zahl" );
  String text = request.getParameter( "text" );
  if( null == zahl || 0 >= zahl.trim().length() ) {
    zahl = "-";
  } else {
    try {
      long i = Long.parseLong( zahl );
      zahl = "" + i * i;
    } catch( Exception ex ) {}
  }
  if( null == text || 0 >= text.trim().length() ) {
    text = "-";
  } else {
    char[] c = text.toCharArray();
    java.util.Arrays.sort( c );
    text = new String( c );
  }
%>
<MeinErgebnis>
  <zahl><%= zahl %></zahl>
  <text><%= text %></text>
  <ip><%= request.getRemoteHost() %></ip>
</MeinErgebnis>