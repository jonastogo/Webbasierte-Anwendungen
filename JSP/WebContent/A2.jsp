<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="me.alex.UserID" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aufgabe2</title>
</head>
<body>
<h1>JSP AUfgabe 2</h1>
<h3>aus Parameter string gelesen 'UserId' <%=request.getParameter("UserId") %></h3>
<jsp:useBean id="UserID" class="me.alex.UserID" />
<jsp:setProperty name="UserID" property="id" value="<%=request.getParameter(\"UserId\") %>" />
<div>
	<%
		String id = request.getParameter("UserId");
		if(request.getHeader("Accept-Language").equals("de-DE,de;q=0.8,en-US;q=0.6,en;q=0.4")){
			out.println("Hallo User: "+ id + "!");
		}else{

			out.println("Hello User: "+ id + "!");
		}
		
	%>
</div>

<div>
Ihre User ID ist:<br>


<jsp:getProperty property="id" name="UserID"/>


</div>
<%@ include file = "Footer.jsp" %>
</body>
</html>