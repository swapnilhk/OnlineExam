<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/heading.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<title>Marks Info</title>
</head>
<body>

<h1>Online Test</h1>
<h2>Marks Info</h2>
<table align="center">
<tr>
<td width="119"><b>Name</b></td>
<td width="168"><b>Marks</b></td>

</tr>
<%Iterator itr;%>
<% List data= (List)request.getAttribute("data");
for (itr=data.iterator(); itr.hasNext(); )
{
%>
<tr>
<td width="119"><%=itr.next()%></td>
<%
	String marks = (String)itr.next();
	if(marks != null){		
%>
		<td width="168"><%=marks%></td>
<%
	}
	else{
%>
		<td width="168">Not taken test</td>
<%
	}
%>
</tr>
<%}%>
</table>
</body>
</html>