<!DOCTYPE html>
<html lang="en">
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="model.*"%>

<% HStudent userStudent = (HStudent) session.getAttribute("userStudent"); %>
<% HStaff userStaff = (HStaff) session.getAttribute("userStaff"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
<style type="text/css">
/* background */
.navbar-inverse {
	background-color: #1E6912;
	
}
/* title */
.navbar-inverse .navbar-brand {
    color: #96D38D;
}
/* text */
.navbar-inverse .navbar-collapse .navbar-nav > li > a {
	color: #96D38D;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<img src="Harrisonlogo.png" class="img-rounded" alt="Harrison College" width="44" height="44">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				
				<a class="navbar-brand" href="index.jsp">Harrison College</a>
			</div>
			<div class="collapse navbar-collapse" name="myNavbar" id="myNavbar">
				<% if (userStudent != null) { %>
				<ul class="nav navbar-nav">
					<li><a href="sAddClass">Enroll</a></li>
					<li><a href="sViewSchedule">View Schedule</a></li>
					<li><a href="sTranscript">Transcript</a></li>
				</ul>

				<% } else if (userStaff != null	&& userStaff.getType().equalsIgnoreCase("Instructor")) { %>
				<ul class="nav navbar-nav">
					<li><a href="iClasses.jsp">Schedule</a></li>
					<li><a href="iStudents.jsp">Students</a></li>
					<li><a href="iGrades">Grades</a></li>
				</ul>
				<% } else if (userStaff != null && userStaff.getType().equalsIgnoreCase("Advisor")) { %>
				<ul class="nav navbar-nav">
					<li><a href="aTranscript.jsp">View Transcript</a></li>
					<li><a href="enrollment.jsp">Enrollment</a></li>
				</ul>
			<% } else if (userStaff != null && userStaff.getType().equalsIgnoreCase("Admin")) { %>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin Edit<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="adminCourses">Courses</a></li>
							<li><a href="adminClassrooms">Classrooms</a></li>
							<li><a href="adminMajors">Majors</a></li>
							<li><a href="adminDepartments">Departments</a></li>
							<li><a href="adminStaff">Staff</a></li>
						</ul>
        			</li>
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin Search<span class="caret"></span></a>
         				<ul class="dropdown-menu">
            				<li><a href="studentF">Students</a></li>
            				<li><a href="instructorF">Instructors</a></li>
            				<li><a href="courseF">Courses</a></li>
          				</ul>
        			</li>
				</ul>
				<% }
				
					if (userStudent == null && userStaff == null) { %>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="studentLogin.jsp"><span class="glyphicon glyphicon-log-in"></span>
							Student Login</a></li>
					<li><a href="staffLogin.jsp"><span class="glyphicon glyphicon-log-in"></span>
							Staff Login</a></li>
				</ul>
				<% } else {	%>
				<ul class="nav navbar-nav">
					<li><a href="ClassSearch">Classes</a></li>
					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Departments<span class="caret"></span></a>
         				<ul class="dropdown-menu">
            				<li><a href="CourseSearch">Courses</a></li>
            				<li><a href="MajorSearch">Majors</a></li>
          				</ul>
        			</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout"><span class="glyphicon glyphicon-log-in"></span>
							Logout</a></li>
				</ul>
				<% } %>
			</div>
		</div>
	</nav>