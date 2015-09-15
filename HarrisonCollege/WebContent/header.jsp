<!DOCTYPE html>
<html lang="en">
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="model.*"%>

<%
	HStudent userStudent = (HStudent) session
			.getAttribute("userStudent");
%>
<%
	HStaff userStaff = (HStaff) session.getAttribute("userStaff");
%>
<head>
<title>Harrison College</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">Harrison College</a>
			</div>
			<div class="collapse navbar-collapse" name="myNavbar" id="myNavbar">
				<%
					if (userStudent != null) {
				%>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#">Enroll<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="sAddClass">Add Class</a></li>
							<li><a href="sDropClass">Drop Class</a></li>
						</ul></li>
					<li><a href="sViewSchedule">View Schedule</a></li>
					<li><a href="sTranscript">Transcript</a></li>
				</ul>

				<%
					} else if (userStaff != null
							&& userStaff.getType().equalsIgnoreCase("Instructor")) {
				%>
				<ul class="nav navbar-nav">
					<li><a href="iFunctions">Instructor Functions</a></li>
				</ul>
				<%
					} else if (userStaff != null
							&& userStaff.getType().equalsIgnoreCase("Advisor")) {
				%>
				<ul class="nav navbar-nav">
					<li><a href="aTranscript">View Transcript</a></li>
					<li><a href="aEnrollment">Enrollment</a></li>
				</ul>
				<%
					}
				%>
				<%
					if (userStudent == null && userStaff == null) {
				%>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="studentLogin.jsp"><span class="glyphicon glyphicon-log-in"></span>
							Student Login</a></li>
					<li><a href="staffLogin.jsp"><span class="glyphicon glyphicon-log-in"></span>
							Staff Login</a></li>
				</ul>
				<%
					} else {
				%>
				<ul class="nav navbar-nav">
					<li><a href="classes">Classes</a></li>
					<li><a href="department">Departments</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout"><span class="glyphicon glyphicon-log-in"></span>
							Logout</a></li>
				</ul>
				<%
					}
				%>
			</div>
		</div>
	</nav>
