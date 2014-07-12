<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/table.css" type="text/css" />
<link rel="stylesheet" href="../css/heading.css" type="text/css" />
<title>Student Login</title>
<script src="../js/validate.js"></script>
</head>
<body>
	<h1>Online Test</h1>
	<form action="../StudentLogin" method="POST" name="studentLogin" onSubmit="return validateStudentLogin()">
			<table align="center">
				<tr><th colspan="2" align="center">Student Login</th></tr>
				<tr>
					<td>Userame</td>
					<td><input id="username" name="username" type="text" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input id="password" name="password" type="password" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input name="submit" type="submit" value="Login"/></td>					
				</tr>
			</table>
			<p><a href="/OnlineExam">Home</a></p>
	</form>
</body>
</html>