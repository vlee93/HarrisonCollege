<jsp:include page="./header.jsp" />

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
 <nav class="navbar navbar-default" style="background-color:#D5D5D5">
	<div class="panel-heading">Courses for this Department</div>
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

		<form class="form-inline" role="form" action="MajorSearch" method="POST">
			
			<div class="form-group"  >
				
				<label for="departments" >Departments:</label> 
				<select class="form-control" name="departments" >
					<option value="all">All Departments</option>
					<c:forEach var="departments" items="${departments}">
						<option value="${departments}">${departments}</option>
					</c:forEach>
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
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Major Name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="majorList" items="${majors}">
				<tr>
					<td>${majorList.majorName}</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

</div>

</body>
</html>