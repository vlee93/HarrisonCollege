<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="header.jsp"></jsp:include>
<div class="container">
<form class="form" role="form" action="iViewClasses" method="post">
	<label style="display: inline;">Semester: </label> <select
		class="form-control" name="college_semester"
		style="margin-left: 20px; display: inline-block; width: 10%; display: inline;">
		<c:if test="${empty semesters}">
			<option>Fall 2015</option>
			<option>Spring 2016</option>
			<option>Summer 2016</option>
		</c:if>
		<c:if test="${not empty semesters}">
			<c:forEach var="semester" items="${semesters}">
				<option>${semester}</option>
			</c:forEach>
		</c:if>
	</select>
	<button class="form-control" type="submit" value="View"
		style="margin-left: 300px; display: inline-block; width: 10%; display: inline;">View</button>
	<br>
</form>

<c:if test="${not empty classes}">
	<div class="container">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Course Code</th>
					<th>Credits</th>
					<th>Class days</th>
					<th>Start time</th>
					<th>End time</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="class" items="${classes}" varStatus="status">
					<tr>
						<td>${Courses_info.get(status.index).getSubjectCode()}
							${Courses_info.get(status.index).getCourseNo()}</td>
						<td>${Courses_info.get(status.index).getCredits()}</td>
						<td>${classes.get(status.index).getClassDays()}</td>
						<td>${classes.get(status.index).getStartTime()}</td>
						<td>${classes.get(status.index).getEndTime() }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</c:if>
<c:if test="${empty classes}">
	<p style="color: red">No results</p>
</c:if>
</div>

</body>
</html>