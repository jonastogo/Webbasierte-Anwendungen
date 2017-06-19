<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP</title>
</head>
<body>
<h1>JSP Aufgabe 1</h1>
<h2>Request-Headers</h2>
user-agent 		 <%= request.getHeader("User-Agent") %><br> 
host 			 <%= request.getHeader("Host") %><br> 
accept			 <%= request.getHeader("Accept") %><br> 
accept-language	 <%= request.getHeader("Accept-Language") %><br> 
accept-encoding	 <%= request.getHeader("Accept-Encoding") %><br>
cookie			 <%= request.getHeader("Cookie") %><br>
connection		 <%= request.getHeader("Connection") %><br>

<h2>Alle Request Parameter</h2>

Parameter1 <%= request.getParameter("Parameter1") %><br>
Parameter2 <%= request.getParameter("Parameter1") %><br>
Parameter3 <%= request.getParameter("Parameter1") %><br>
Parameter4 <%= request.getParameter("Parameter1") %><br>
<br>
Komletter QueryString <%=request.getQueryString()%><br>
<%@ include file = "Footer.jsp" %>
</body>
</html>