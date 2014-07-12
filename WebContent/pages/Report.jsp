<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/heading.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<title>Online Test</title>
</head>
<body>
	<h1>Online Test</h1>
	<table align="center">
		<tr>
			<th>Name</th>
			<td><%=request.getAttribute("name") %></td>			
		</tr>
		<tr>
			<th>Correct Answers</th>
			<td><%=request.getAttribute("correct") %></td>			
		</tr>
		<tr>
			<th>Incorrect Answers</th>
			<td><%=request.getAttribute("incorrect") %></td>			
		</tr>
		<tr>
			<th>Marks</th>
			<td><%=request.getAttribute("marks") %></td>			
		</tr>
	</table>
	<p><a href="/OnlineExam">Home</a></p>
</body>
</html>