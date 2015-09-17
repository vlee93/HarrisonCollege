<jsp:include page="./header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
 <nav class="navbar navbar-default" style="background-color:#D5D5D5">
	<div class="panel-heading">Class Search by Filter</div>
	<div class="panel-body">


		<c:if test="${not empty error}">
			<div class="alert alert-danger">
				<c:out value="${error}" />
			</div>
		</c:if>

		<c:if test="${not empty success}">
			<div class="alert alert-success">
				<c:out value="${success}" />
			</div>
		</c:if>

		<form class="form-inline" role="form" action="ClassSearch" method="POST">
			
			<div class="form-group"  >
				<label for="semester" >Semester:</label>
				 <select class="form-control" name="semester" >
					<option value="all">All Semesters</option>
					<c:forEach var="semester" items="${semesters}">
						<option value="${semester}">${semester}</option>
					</c:forEach>
				</select> 

				<label for="subjects">Subjects:</label> 
				<select class="form-control" name="subjects" >
					<option value="all">All Subjects</option>
					<c:forEach var="subjects" items="${subjects}">
						<option value="${subjects}">${subjects}</option>
					</c:forEach>
				</select> 

				<label for="instructors">Instructors:</label> 
				<select class="form-control" name="instructors" >
					<option value="all">All Instructors</option>
					<c:forEach var="instructor" items="${instructors}">
						<option value="${instructor}">${instructor}</option>
					</c:forEach>
				</select> 

				<label for="departments" >Departments:</label> 
				<select class="form-control" name="departments" >
					<option value="all">All Departments</option>
					<c:forEach var="departments" items="${departments}">
						<option value="${departments}">${departments}</option>
					</c:forEach>
				</select> 

				<label for="time" >Start Time:</label>
				<select class="form-control" name="time">
					<option value="all">All</option>
					<option value="6">6:00AM</option>
					<option value="7">7:00AM</option>
					<option value="8">8:00AM</option>
					<option value="9">9:00AM</option>
					<option value="10">10:00AM</option>
					<option value="11">11:00AM</option>
					<option value="12">12:00PM</option>
					<option value="13">1:00PM</option>
					<option value="14">2:00PM</option>
					<option value="15">3:00PM</option>
					<option value="16">4:00PM</option>
					<option value="17">5:00PM</option>
					<option value="18">6:00PM</option>
					<option value="19">7:00PM</option>
					<option value="20">8:00PM</option>
					<option value="21">9:00PM</option>
					<option value="22">10:00PM</option>	
				</select> 


			</div>

			<div class="form-group">
				<button type="submit" value="submit" class="btn btn-default">Search</button>

			</div>
		</form>
	</div>
</nav>
</div>
<div class="container">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>Course Name</th>
				<th>Subject Code</th>
				<th>Instructor</th>
				<th>Department</th>
				<th>Start Time</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="classList" items="${classes}">
				<tr>
					<td>${classList.HCourse.courseName}</td>
					<td>${classList.HCourse.subjectCode}</td>
					<td>${classList.HStaff.staffName}</td>
					<td>${classList.HCourse.HDepartment.deptName}</td>
					<td>${classList.startTime}:00 EST</td>

				</tr>
			</c:forEach>
		</tbody>

	</table>

</div>

</body>
</html>