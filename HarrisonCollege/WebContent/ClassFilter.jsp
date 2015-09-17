<jsp:include page="./header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel panel-primary col-sm-6 col-sm-offset-3">
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

		<form role="form " action="ClassSearch" method="POST">
			<div class="form-group">
				<label for="semester">Semester:</label> <select name="semester">
					<option value="all">All Semesters</option>
					<c:forEach var="semester" items="${semesters}">
						<option value="${semester}">${semester}</option>
					</c:forEach>
				</select> 
				<br> 
				<label for="subjects">Subjects:</label> <select name="subjects">
					<option value="all">All Subjects</option>
					<c:forEach var="subjects" items="${subjects}">
						<option value="${subjects}">${subjects}</option>
					</c:forEach>
				</select> 
				<br> 
				<label for="instructors">Instructors:</label> <select name="instructors">
					<option value="all">All Instructors</option>
					<c:forEach var="instructor" items="${instructors}">
						<option value="${instructor}">${instructor}</option>
					</c:forEach>
				</select> 
				<br> 
				<label for="departments">Departments:</label> <select name="departments">
					<option value="all">All Departments</option>
					<c:forEach var="departments" items="${departments}">
						<option value="${departments}">${departments}</option>
					</c:forEach>
				</select> 
				<br> 
				<label for="time">Time:</label>
				<select name="time">
					<option value="all">all</option>
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
				<br>

			</div>

			<div class="form-group">
				<button type="submit" value="submit" class="btn btn-default">Search</button>


			</div>
		</form>
	</div>
</div>
<div class="panel panel-primary col-sm-6 col-sm-offset-3">
	<table class="table table-bordered table-striped">
		<thead>
			<tr>
				<th>Course Name</th>
				<th>Subject</th>
				<th>Instructor</th>
				<th>Department</th>
				<th>Time</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="currentClass" items="${classes}">
				<tr>
					<td>${currentClass.HCourse.name}</td>
					<td>${currentClass.HCourse.HSubject.subjectCode}</td>
					<td>${currentClass.HStaffDetail.HUser.staffName}</td>
					<td>${currentClass.HCourse.HMajor.HDepartment.name}</td>
					<td>time</td>

				</tr>
			</c:forEach>
		</tbody>

	</table>

</div>

</body>
</html>