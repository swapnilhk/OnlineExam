<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/table.css" type="text/css" />
<link rel="stylesheet" href="css/heading.css" type="text/css" />
<title>Student Details</title>
</head>
<body>
<h1>Online Test</h1>
<h2>Student Details</h2>
<table align="center">
<tr>
<td width="119"><b>Student Id</b></td>
<td width="168"><b>Name</b></td>
<td width="200"><b>Username</b></td>
<td width="250"><b>Password</b></td>
</tr>
<%Iterator itr;%>
<% List data= (List)request.getAttribute("data");
for (itr=data.iterator(); itr.hasNext(); )
{
%>
<tr>
<td width="119"><%=itr.next()%></td>
<td width="168"><%=itr.next()%></td>
<td width="200"><%=itr.next()%></td>
<td width="250"><%=itr.next()%></td>
</tr>
<%}%>
</table>
</body>
</html>