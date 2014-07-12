<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="js/clock.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/test.css" type="text/css" />

<title>Online Test</title>
</head>
<body onload="initTime()">
<h1>Online Test</h1>
<div class="navtop">
 <%
	int totalQuestions = (Integer)request.getAttribute("totalQuestions");
	for(int i = 1; i <= totalQuestions; i++){
		if(request.getAttribute("qno").equals(i)){
%>
			<a href=test?qno=<%=i %>><div class="navtopelmsel"><%=i %></div></a>
<%
		}else{
%>
			<a href=test?qno=<%=i %>><div class="navtopelm"><%=i %></div></a>
<%
		}
	}
%>  <div align="right" id="clock"></div>
</div>
<div class="headright"><a href="/OnlineExam/ProcessResult">Logout</a></div><br/>
<p id="clock"></p>
<%
	String message = (String)request.getAttribute("message");
	if(message != null){			
%>
		<div class="message"><%=message %></div>
<%
	}
%>
<div class="block"><form action="test?qno=<%= request.getAttribute("qno")%>" method="POST">
		<input type="hidden" id="hiddenmin" name="min"></input>
		<input type="hidden" id="hiddensec" name="sec"></input>
		<div class="question"><%= request.getAttribute("qno")%>. <%=request.getAttribute("question") %></div> 

<% 
	boolean attempted = (Boolean)request.getAttribute("attempted");
	String checkedOid = (String)request.getAttribute("checkedOid");
	HashMap<String, String>h = (HashMap<String, String>)request.getAttribute("options");
	Set<String> s = h.keySet();
	Iterator<String> i = s.iterator(); 
	while(i.hasNext()){
		String oid = i.next();
		if(attempted && oid.equals(checkedOid)){
%>			
			<br/><div class="option"><input type="radio" name="option" value="<%= oid%>" checked><%= h.get(oid)%></input></div>		
<%
		}
		else{
%>
			<br/><div class="option"><input type="radio" name="option" value="<%= oid%>"><%= h.get(oid)%></input></div>
<%
		}
	}
%>
<br/>
<div class="navbot">
<%
	if(request.getAttribute("first") == null){
%>
		<a href="/OnlineExam/test?qno=<%= request.getAttribute("prevQno") %>"><-Prev</a>
<%
	}
%>	
<input type="submit" value="Save"></input>
<%
	if(request.getAttribute("last") == null){
%>
		<a href="/OnlineExam/test?qno=<%= request.getAttribute("nextQno") %>">Next-></a>
<%
	}
%>
</div>
</form>
</div>
</body>
</html>