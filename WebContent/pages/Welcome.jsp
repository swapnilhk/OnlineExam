<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
<link rel="stylesheet" type="text/css" href="css/heading.css">
<link rel="stylesheet" type="text/css" href="css/test.css">
<script src="js/clock.js"></script>
<title>Welcome</title>
</head>
<body onload="saveTime('10','0','0')">	
	<h1>Online Test</h1>
	<h2>Welcome <%=	session.getAttribute("name")%></h2>
	<div class="block">
	<h2>Instructions</h2>
	<ol>
		<li>Test will begin after you press 'Begin Test' link at the bottom of this page</li>
		<!-- <li>Test spans 30 minutes</li> -->
		<li>The list of questions can be seen on the top of the page</li>
		<li>You may navigate to any question at any time</li>
		<li>You may use 'prev' and 'next' links to navigate to previous or next question respectively</li>
		<li><b>Do not forget to press 'Save' button. Pressing the radio button only will not save your response.</b></li>		
		<li>You can press logout any time to exit the test and view your result.</li>
		<li>On the event of student logging out, result of the test taken will be shown</li>
		<li>You may see your result any time by logging in again</li>
	</ol>
	</div>
	<h2>Wish You All The Best!</h2>
	<p><a href="/OnlineExam/test?qno=1">Begin Test</a></p>
</body>
</html>
