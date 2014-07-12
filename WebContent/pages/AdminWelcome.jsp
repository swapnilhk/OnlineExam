<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/heading.css">
<link rel="stylesheet" type="text/css" href="css/test.css">
<title>Welcome</title>
</head>
<body>
	<h1>Online Test</h1>
	<h2>Welcome <%= session.getAttribute("username")%></h2>
	<%
		String message = (String)request.getAttribute("message");
		if(message != null){			
	%>
			<div class="message"><%=message %></div>
	<%
		}
	%>
	<p>
		<a href="pages/AddStudent.jsp">Add Student</a><br/>
		<!-- <a href="/OnlineExam/StudentScore">Student Score</a><br/> -->
		<a href="/OnlineExam/AdminReport">Admin Report</a><br/>
		<a href="/OnlineExam/StdDetails">Student Info</a><br/>
		<a href="pages/AddQuestion.jsp">Add a Question</a><br/>
		<a href="/OnlineExam/AdminLogout">Logout</a><br/>
	</p>		
</body>
</html>