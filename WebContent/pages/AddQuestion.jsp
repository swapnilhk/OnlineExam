<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../css/table.css" type="text/css" />
<link rel="stylesheet" href="../css/heading.css" type="text/css">
<script src="../js/validate.js"></script>
<title>Question List</title>
</head>
<body>
	<h1>Online Test</h1>
	<h2>Add Question</h2>
	<form action="../AddQuestion" method="POST" onsubmit="return validateAddQuestionForm()" name="addQuestion">
		<table align="center">
			<tr>
				<td>Enter Question</td>
				<td><input id="question" name="question" type="text" /></td>
			</tr>
			<tr>
				<td>Enter Option A</td>
				<td><input id="option1" name="option1" type="text" /></td>
			</tr>
			<tr>
				<td>Enter Option B</td>
				<td><input id="option2" name="option2" type="text" /></td>
			</tr>
			<tr>
				<td>Enter Option C</td>
				<td><input id="option3" name="option3" type="text" /></td>
			</tr>
			<tr>
				<td>Enter Option D</td>
				<td><input id="option4" name="option4" type="text" /></td>
			</tr>
			<tr>
				<td>Enter CorrectAnswer</td>
				<td>
					<select name="correctanswer">
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
						<option value="D">D</option>
				</select>
				</td>
			</tr>
			<tr>
				<td>Give Marks</td>
				<td><input id="marks" name="marks" type="text" /></td>
			</tr>
			<tr>
				<td colspan="2" align="middle"><input name="submit" type="submit" value="Add"/></td>				
			</tr>
		</table>
	</form>
</body>
</html>

