<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/table.css" type="text/css" />
<link rel="stylesheet" href="../css/heading.css" type="text/css">

<title>Students List</title>
<script src="../js/validate.js"></script>
</head>
<body>
	<h2>Online Test</h2>
	<h2>Student Details</h2>
	<form action="../AddStudent" method="POST" name="addStudent" onSubmit="return validateAddStudentForm()">
		<table align="center">
			<tr>
				<td>Enter Name</td>
				<td><input id="name2" name="name" type="text" /></td>
			</tr>
			<tr>
				<td>Enter Username</td>
				<td><input id="username2" name="username" type="text" /></td>
			</tr>
			<tr>
				<td>Choose Password</td>
				<td><input id="password2" name="password" type="password" /></td>
			</tr>
			<tr>
				<td colspan="2" align="middle"><input name="submit" type="submit" value="Add" /></td>				
			</tr>
		</table>
	</form>
</body>
</html>